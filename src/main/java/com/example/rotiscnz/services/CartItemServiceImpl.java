package com.example.rotiscnz.services;

import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.dtos.cartItemDTOs.CartItemCreateDTO;
import com.example.rotiscnz.dtos.cartItemDTOs.CartItemMapperDTO;
import com.example.rotiscnz.dtos.cartItemDTOs.CartItemResponseDTO;
import com.example.rotiscnz.dtos.orderDTOs.OrderCreateDTO;
import com.example.rotiscnz.dtos.orderDTOs.OrderResponseDTO;
import com.example.rotiscnz.entities.CartItemEntity;
import com.example.rotiscnz.enums.OrderType;
import com.example.rotiscnz.repositories.CartItemRepository;
import com.example.rotiscnz.serviceinterfaces.CartItemServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemServiceInterface {
    private final CartItemRepository repository;
    private final OrderServiceImpl orderServiceImpl;


    @Override
    public ResponseDTO<CartItemResponseDTO> addItemToCart(CartItemCreateDTO cartItemCreateDTO) {
        OrderCreateDTO orderCreateDTO = new OrderCreateDTO();
        orderCreateDTO.setOrderTime(new Timestamp(System.currentTimeMillis()));
        orderCreateDTO.setStatus(String.valueOf(OrderType.ORDERED));
        orderCreateDTO.setCartID(cartItemCreateDTO.getCartByCartId());
        ResponseDTO<OrderResponseDTO> orderResponseDTOResponseDTO = orderServiceImpl.saveOrder(orderCreateDTO);
        OrderResponseDTO orderResponseDTO = orderResponseDTOResponseDTO.getData();
        CartItemEntity cartItemEntity = CartItemMapperDTO.toCartItemEntityFromCartItemCreateDTO(cartItemCreateDTO);
        cartItemEntity.setOrderID(orderResponseDTO.getId());
        repository.save(cartItemEntity);
        ResponseDTO<CartItemResponseDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setResponseCode(0);
        responseDTO.setData(null);
        return responseDTO;
    }

    @Override
    public ResponseDTO<List<CartItemResponseDTO>> getAllItems(Long id) {
        List<CartItemEntity> items = repository.findAll();
        List<CartItemEntity> filteredItems = items.stream().filter(i -> Objects.equals(i.getCartByCartId(), id)).toList();
        List<CartItemResponseDTO> cartItemResponseDTOS = new ArrayList<>();
        for (CartItemEntity cartItem : filteredItems) {
            cartItemResponseDTOS.add(CartItemMapperDTO.toCartItemResponseDTOFromCartItemEntity(cartItem));
        }
        ResponseDTO<List<CartItemResponseDTO>> responseDTO = new ResponseDTO<>();
        responseDTO.setData(cartItemResponseDTOS);
        responseDTO.setResponseCode(0);
        return responseDTO;
    }

    public ResponseDTO<CartItemResponseDTO> addItemToTheCart(CartItemCreateDTO cartItemCreateDTO) {
        ResponseDTO<CartItemResponseDTO> responseDTO = new ResponseDTO<>();
        CartItemResponseDTO cartItemResponseDTO = new CartItemResponseDTO();
        cartItemResponseDTO.setCartByCartId(cartItemCreateDTO.getCartByCartId());
        cartItemResponseDTO.setItemByItemId(1L);
        cartItemResponseDTO.setOrderID(1L);
        cartItemResponseDTO.setId(cartItemCreateDTO.getCartByCartId());
        responseDTO.setData(cartItemResponseDTO);
        responseDTO.setResponseCode(0);
        return responseDTO;
    }

    //Hehe Boi
    public ResponseDTO<Boolean> removeItemFromCart(Long ItemId, Long cartId){
        ResponseDTO<Boolean> responseDTO = new ResponseDTO<>();
        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setCartByCartId(cartId);
        repository.delete(cartItemEntity);
        responseDTO.setData(true);
        responseDTO.setResponseCode(0);
        return responseDTO;
    }
}
