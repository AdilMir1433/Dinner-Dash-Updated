package com.example.rotiscnz.controllers;

import com.example.rotiscnz.dtos.ResponseDTO;
import com.example.rotiscnz.dtos.orderDTOs.OrderCompleteResponseDTO;
import com.example.rotiscnz.dtos.orderDTOs.OrderResponseDTO;
import com.example.rotiscnz.services.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
//@CrossOrigin("http://localhost:3000")
@CrossOrigin({"https://d100-122-129-66-106.ngrok-free.app", "*"})
public class OrderController {
    private final OrderServiceImpl orderServiceImpl;

    @GetMapping("/get-order/{id}")
    public ResponseDTO<List<OrderCompleteResponseDTO>>OrderResponseDTOGetOrder(@PathVariable Long id){
        return orderServiceImpl.getOrderOfUser(id);

    }
    @GetMapping("/get-all-orders")
    public ResponseDTO<List<OrderResponseDTO>>getAllOrders(){
        return orderServiceImpl.getAllOrders();

    }

}
