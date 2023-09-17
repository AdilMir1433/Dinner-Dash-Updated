package com.example.rotiscnz.repositories;

import com.example.rotiscnz.entities.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity,Long> {
}
