package com.example.rotiscnz.repositories;

import com.example.rotiscnz.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {
    List<CategoryEntity> findAllByIdIn(List<Integer> id);
}
