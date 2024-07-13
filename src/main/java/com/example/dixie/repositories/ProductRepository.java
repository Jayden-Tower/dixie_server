package com.example.dixie.repositories;

import com.example.dixie.models.Products;
import com.example.dixie.utils.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Products, Long> {

    @Override
    Optional<Products> findById(Long id);

    Optional<Products> findByProductName(String product);

    Page<Products> findAllByCategory(String name,Pageable pageable);

    Page<Products> findAllByStatus(String status, Pageable pageable);

}
