package com.example.dixie.controller;


import com.example.dixie.dto.ProductDto;
import com.example.dixie.models.Products;
import com.example.dixie.services.ProductService;
import com.example.dixie.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @PostMapping("/post")
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto){
        Response<Products>  response =  productService.createProduct(productDto);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/all-by-status")
    public ResponseEntity<?> getAllProducts (@RequestParam(value = "page", defaultValue = "0")Integer page,
                                             @RequestParam(value = "size", defaultValue = "25")Integer size){
        PageRequest pageRequest =  PageRequest.of(page,size);
        Page<Products> quantity =  productService.getProductsByStatus(pageRequest);

        return  ResponseEntity.ok().body(quantity);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getProductById(@PathVariable(value = "id") Long id){
        Response<Products> response =  productService.getProductById(id);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/get-by-category/{name}")
    public ResponseEntity<?> getProductByCategory(@PathVariable(value = "name") String name,
                                                  @RequestParam(value = "page" , defaultValue = "0")Integer page,
                                                  @RequestParam(value = "size", defaultValue = "20")Integer size){

    PageRequest pageRequest =  PageRequest.of(page,size);
    Page<Products> response =  productService.getProductByCategory(name,pageRequest);
    return ResponseEntity.ok().body(response);
    }

    @GetMapping("get-by-name/{name}")
    public ResponseEntity<?> getProductByName(@PathVariable(value = "name")String name){
        Response<Products> response = productService.getProductByName(name);
        return ResponseEntity.ok().body(response);
    }


}
