package com.example.dixie.controller;


import com.example.dixie.dto.CategoryDto;
import com.example.dixie.models.Categories;
import com.example.dixie.services.CategoryService;
import com.example.dixie.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@Component
public class CategoriesController {
    @Autowired
    private CategoryService categoryService;


    @PostMapping("/new")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDto categoryDto){
        Response<Categories> response = categoryService.createCategory(categoryDto);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable (value = "id") Long id){
        Response<Categories> response = categoryService.findById(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<?> getCategoryByName(@PathVariable(value = "name")String name){
        Response<Categories> response = categoryService.findByName(name);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/all-categories")
    public ResponseEntity<?> getCategoryByName(@RequestParam(value = "page",defaultValue ="0" ) Integer page,
                                               @RequestParam(value ="size",defaultValue ="25" ) Integer size
                                               ){
        PageRequest pageRequest = PageRequest.of(page,size);

        Page<Categories> categoriesPage =  categoryService.findAllCategories(pageRequest);

        return ResponseEntity.ok().body(categoriesPage);

    }

}
