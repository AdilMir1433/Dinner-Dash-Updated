package com.example.rotiscnz.repositories;

import com.example.rotiscnz.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
    List<OrderEntity> findAllByCartID(Long id);
}
