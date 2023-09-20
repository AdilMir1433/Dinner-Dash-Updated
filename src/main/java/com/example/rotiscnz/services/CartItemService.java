package com.example.rotiscnz.services;

import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.dtos.cartItemDTOs.CartItemCreateDTO;
import com.example.rotiscnz.dtos.cartItemDTOs.CartItemListDTO;
import com.example.rotiscnz.dtos.cartItemDTOs.CartItemMapperDTO;
import com.example.rotiscnz.dtos.cartItemDTOs.CartItemResponseDTO;
import com.example.rotiscnz.dtos.orderDTOs.OrderCreateDTO;
import com.example.rotiscnz.entities.CartItemEntity;
import com.example.rotiscnz.enums.OrderType;
import com.example.rotiscnz.mappers.CartItemMapper;
import com.example.rotiscnz.repositories.CartItemRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository repository;
    private final CartItemMapper mapper;
    private final OrderService orderService;

    public ResponseDTO<CartItemResponseDTO> addItemToCart(CartItemCreateDTO cartItemCreateDTO){
        CartItemEntity cartItemEntity = CartItemMapperDTO.toCartItemEntityFromCartItemCreateDTO(cartItemCreateDTO);
        repository.save(cartItemEntity);
        OrderCreateDTO orderCreateDTO = new OrderCreateDTO();
        orderCreateDTO.setOrderTime(new Timestamp(System.currentTimeMillis()));
        orderCreateDTO.setStatus(String.valueOf(OrderType.ORDERED));
        orderCreateDTO.setCartID(cartItemCreateDTO.getCartByCartId());
        orderService.saveOrder(orderCreateDTO);
        ResponseDTO<CartItemResponseDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setResponseCode(0);
        responseDTO.setData(null);
        return responseDTO;
    }
    public ResponseDTO<List<CartItemResponseDTO>> getAllItems(Long id){
        List<CartItemEntity> items = repository.findAll();
        List<CartItemEntity> filteredItems = items.stream().filter(i-> {
            return i.getCartByCartId() == id;
        }).toList();
        List<CartItemResponseDTO> cartItemResponseDTOS = new ArrayList<>();
        for (CartItemEntity cartItem:filteredItems) {
            cartItemResponseDTOS.add(mapper.toCartItemResponseDTOFromCartItemEntity(cartItem));
        }
        ResponseDTO<List<CartItemResponseDTO>> responseDTO = new ResponseDTO<>();
        responseDTO.setData(cartItemResponseDTOS);
        responseDTO.setResponseCode(0);
        return responseDTO;
    }
}
