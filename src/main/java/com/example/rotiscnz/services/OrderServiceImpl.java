package com.example.rotiscnz.services;

import com.example.rotiscnz.dtos.ItemDTOs.ItemMapperDTO;
import com.example.rotiscnz.dtos.ItemDTOs.ItemResponseDTO;
import com.example.rotiscnz.dtos.orderDTOs.OrderCompleteResponseDTO;
import com.example.rotiscnz.dtos.orderDTOs.OrderCreateDTO;
import com.example.rotiscnz.dtos.orderDTOs.OrderMapperDTO;
import com.example.rotiscnz.dtos.orderDTOs.OrderResponseDTO;
import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.entities.CartItemEntity;
import com.example.rotiscnz.entities.OrderEntity;
import com.example.rotiscnz.repositories.CartItemRepository;
import com.example.rotiscnz.repositories.ItemRepository;
import com.example.rotiscnz.repositories.OrderRepository;
import com.example.rotiscnz.serviceinterfaces.OrderServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderServiceInterface {
    private final OrderRepository repository;
    private final CartItemRepository cartItemRepository;
    private final ItemRepository itemRepository;

    @Override
    public ResponseDTO<OrderResponseDTO> saveOrder(OrderCreateDTO orderCreateDTO){
        OrderEntity orderEntity = OrderMapperDTO.toOrderEntityFromOrderCreateDTO(orderCreateDTO);
        repository.save(orderEntity);
        OrderResponseDTO orderResponseDTO = OrderMapperDTO.toOrderResponseDTOFromOrderEntity(orderEntity);
        ResponseDTO<OrderResponseDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setData(orderResponseDTO);
        return responseDTO;
    }

    @Override
    public ResponseDTO<List<OrderCompleteResponseDTO>> getOrderOfUser(Long id){
        List<OrderEntity> orderEntity =  repository.findAllByCartID(id);
        List<OrderCompleteResponseDTO> orderCompleteResponseDTO  = new ArrayList<>();
        for (OrderEntity order: orderEntity) {
            OrderCompleteResponseDTO completeResponseDTO = new OrderCompleteResponseDTO();
            completeResponseDTO.setOrderTime(order.getOrderTime());
            completeResponseDTO.setStatus(order.getStatus());
            completeResponseDTO.setCartID(order.getCartID());
            completeResponseDTO.setId(order.getId());
            List<CartItemEntity> cartItems = cartItemRepository.findByOrderID(order.getId());
            List<Long> items = cartItems.stream().map(CartItemEntity::getItemByItemId).toList();
            List<ItemResponseDTO> itemResponseDTOS = new ArrayList<>();
            for (Long i:items) {
                itemResponseDTOS.add(ItemMapperDTO.toItemResponseDTOFromItemEntity(itemRepository.findById(i).get()));
            }
            completeResponseDTO.setItems(itemResponseDTOS);
            orderCompleteResponseDTO.add(completeResponseDTO);
        }
        ResponseDTO<List<OrderCompleteResponseDTO>> responseDTO = new ResponseDTO<>();
        responseDTO.setData(orderCompleteResponseDTO);
        responseDTO.setResponseCode(0);
        return responseDTO;
    }

    @Override
    public ResponseDTO<List<OrderResponseDTO>> getAllOrders(){
        List<OrderEntity> orderEntity =  repository.findAll();
        List<OrderResponseDTO> orderResponseDTOS = new ArrayList<>();
        for (OrderEntity o:orderEntity) {
            orderResponseDTOS.add(OrderMapperDTO.toOrderResponseDTOFromOrderEntity(o));
        }
        ResponseDTO<List<OrderResponseDTO>> responseDTO = new ResponseDTO<>();
        responseDTO.setData(orderResponseDTOS);
        responseDTO.setResponseCode(0);
        return responseDTO;
    }

}
