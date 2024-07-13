package com.example.dixie.servicesImpl;

import com.example.dixie.dto.ProductDto;
import com.example.dixie.models.Categories;
import com.example.dixie.models.Products;
import com.example.dixie.repositories.CategoryRepository;
import com.example.dixie.repositories.ProductRepository;
import com.example.dixie.services.ProductService;
import com.example.dixie.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Response<Products> createProduct(ProductDto productDto) {
        try {

            Products products = new Products();

            if (productDto.getProductName() == null) {
                return new Response<>(true, 400, "product name can not be empty");
            }

            if (productDto.getPrice() == null) {
                return new Response<>(true, 400, "Product price can not be null");
            }

            if (productDto.getQuantity() <= 0) {
                return new Response<>(true, 400, "Quantity can not be null");
            }

            if(productDto.getCategory() == null){
                return new Response<>(true,400,"Product can not be null");
            }


            if (!productDto.getProductName().isBlank() && !Objects.equals(productDto.getProductName(), products.getProductName())) {
                products.setProductName(productDto.getProductName());
            }

            if (!productDto.getPrice().isBlank() && !Objects.equals(productDto.getPrice(), products.getPrice())) {
                products.setPrice(productDto.getPrice());
            }

            if (!productDto.getCategory().isBlank() && !Objects.equals(productDto.getCategory(), products.getCategory())) {
                Optional<Categories> categoriesOptional =  categoryRepository.findById(Long.valueOf(productDto.getCategory()));

                if(categoriesOptional.isEmpty()){
                    return new Response<>(true,404, "No category record found with that ID");
                }

                products.setCategory(categoriesOptional.get());
            }


            products.setStatus("Available");
            products.setQuantity(10);

            Products products1 = productRepository.save(products);

            return new Response<>(false, 201, products1, "Product added successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response<Products> getProductById(Long id) {
        try {
            Optional<Products> OptionalProduct = productRepository.findById(id);

            return new Response<>(false, 200, OptionalProduct.get(), "Data found");


        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response<>(true, 400, "Operation failed");
    }


    @Override
    public Page<Products> getProductsByStatus(Pageable pageable) {
        try {
            Page<Products> productsOptional = productRepository.findAllByStatus("Available", pageable);

            return productsOptional;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PageImpl<>(new ArrayList<>());
    }

    @Override
    public Page<Products> getFeedback(String text) {
        return null;
    }

    @Override
    public Response<Products> getProductByName(String name) {
        Optional<Products> productsOptional = productRepository.findByProductName(name);

        if (productsOptional.isEmpty()) {
            return new Response<>(true, 404, "record not found");
        }

        return new Response<>(false, 200, productsOptional.get(), "Record found");

    }

    @Override
    public Page<Products> getProductByCategory(String name, Pageable pageable) {

        Page<Products> productsPage =  productRepository.findAllByCategory(name,pageable);
        if(productsPage.isEmpty()){
            return new PageImpl<>(new ArrayList<>());
        }
        return productsPage;
    }


}