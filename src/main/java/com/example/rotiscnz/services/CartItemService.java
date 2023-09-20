package com.example.rotiscnz.services;

import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.dtos.cartItemDTOs.CartItemCreateDTO;
import com.example.rotiscnz.dtos.cartItemDTOs.CartItemListDTO;
import com.example.rotiscnz.dtos.cartItemDTOs.CartItemMapperDTO;
import com.example.rotiscnz.dtos.cartItemDTOs.CartItemResponseDTO;
import com.example.rotiscnz.entities.CartItemEntity;
import com.example.rotiscnz.mappers.CartItemMapper;
import com.example.rotiscnz.repositories.CartItemRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository repository;
    private final CartItemMapper mapper;

    public ResponseDTO<CartItemResponseDTO> addItemToCart(CartItemCreateDTO cartItemCreateDTO){
//        for (CartItemCreateDTO cartItemDTO:cartItemCreateDTO.getCartItemCreateDTO()) {
            CartItemEntity cartItemEntity = CartItemMapperDTO.toCartItemEntityFromCartItemCreateDTO(cartItemCreateDTO);
            repository.save(cartItemEntity);
      //  }
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
