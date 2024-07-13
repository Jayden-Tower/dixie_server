package com.example.dixie.services;

import com.example.dixie.dto.CategoryDto;
import com.example.dixie.models.Categories;
import com.example.dixie.utils.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {

    Response<Categories> createCategory(CategoryDto categoryDto);

    Response<Categories> findById(Long id);

    Response<Categories> findByName(String name);

    Page<Categories> findAllCategories(Pageable pageable);
}
