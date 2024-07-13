package com.example.dixie.services;

import com.example.dixie.dto.ProductDto;
import com.example.dixie.models.Products;
import com.example.dixie.utils.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    Response<Products> createProduct(ProductDto productDto);

    Response<Products> getProductById(Long id);

    Page<Products> getProductsByStatus(Pageable pageable);

    Page<Products> getFeedback(String text);

    Response<Products> getProductByName(String name);

    Page<Products> getProductByCategory(String name,Pageable pageable);
}
