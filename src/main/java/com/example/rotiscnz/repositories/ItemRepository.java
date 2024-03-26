package com.example.rotiscnz.repositories;

import com.example.rotiscnz.entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity,Long> {

    Optional<ItemEntity> findByTitle(String title);
}
