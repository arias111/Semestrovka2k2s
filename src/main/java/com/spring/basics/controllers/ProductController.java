package com.spring.basics.controllers;

import com.spring.basics.dto.ProductDto;
import com.spring.basics.dto.ProductsPage;
import com.spring.basics.models.Product;
import com.spring.basics.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/categories/{categoryId}/productsList/search")
    public ResponseEntity<ProductsPage> search(@RequestParam("size") Integer size,
                                               @RequestParam("page") Integer page,
                                               @RequestParam(value = "q", required = false) String query,
                                               @RequestParam(value = "sort", required = false) String sort,
                                               @RequestParam(value = "direction", required = false) String direction,
                                               @PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.ok(productService.search(size, page, query, sort, direction, categoryId));
    }


    @GetMapping("/product")
    @ResponseBody
    public List<Product> getProduct() {
        return productService.getAllProducts();
    }

    @PostMapping("/product")
    @ResponseBody
    public Product addProduct(@RequestBody ProductDto product) {
        return productService.addProduct(product);
    }
}


