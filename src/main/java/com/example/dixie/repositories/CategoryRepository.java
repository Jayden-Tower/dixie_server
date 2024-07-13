package com.example.dixie.repositories;

import com.example.dixie.models.Categories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Categories,Long> {

    Optional<Categories> findById(Long id);

    Optional<Categories> findByCategoryName(String name);

    Page<Categories> findAll(Pageable pageable);
}
