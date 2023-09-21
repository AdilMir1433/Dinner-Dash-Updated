package com.example.rotiscnz.repositories;

import com.example.rotiscnz.entities.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity,Long> {
    List<CartItemEntity> findByOrderID(Long orderID);
}
