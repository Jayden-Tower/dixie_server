package com.example.dixie.servicesImpl;

import com.example.dixie.dto.CategoryDto;
import com.example.dixie.models.Categories;
import com.example.dixie.repositories.CategoryRepository;
import com.example.dixie.services.CategoryService;
import com.example.dixie.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Response<Categories> createCategory(CategoryDto categoryDto) {

        try {
            Categories category = new Categories();

            if (categoryDto.getCategoryName() == null) {
                return new Response<>(true, 400, "Category name can not be empty");
            }

            if (!categoryDto.getCategoryName().isBlank() && !Objects.equals(categoryDto.getCategoryName(), category.getCategoryName())) {
                category.setCategoryName(categoryDto.getCategoryName());
            }

            category.setDelete(false);

            Categories categories = categoryRepository.save(category);

            return new Response<>(false, 201, categories, "Category created");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response<>(true, 400, "Operation failed");
    }

    @Override
    public Response<Categories> findById(Long id) {
        try {
            Optional<Categories> optionalCategories = categoryRepository.findById(id);
            if (optionalCategories.isPresent()) {
                return new Response<>(false, 200, optionalCategories.get(), "record found");
            }
            return new Response<>(true, 400, "record not found");

        } catch (Exception e) {
            e.printStackTrace();

            return new Response<>(true, 400, "");
        }

    }

    @Override
    public Response<Categories> findByName (String name) {
        Optional<Categories> optionalCategories=categoryRepository.findByCategoryName(name);
        if (optionalCategories.isPresent()){
            return new Response<>(false,200,optionalCategories.get(),"record found");
        }
        return new Response<>(true,400,"record not found");
    }

    @Override
    public Page<Categories> findAllCategories(Pageable pageable) {
        Page<Categories> categoriesPage = categoryRepository.findAll(pageable);

        return  categoriesPage;
    }

}
