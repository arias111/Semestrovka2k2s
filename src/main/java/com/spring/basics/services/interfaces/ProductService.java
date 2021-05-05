package com.spring.basics.services.interfaces;

import com.spring.basics.dto.ProductDto;
import com.spring.basics.dto.ProductsPage;
import com.spring.basics.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<Product> getAllProducts();

    Product addProduct(ProductDto product);

    ProductsPage search(Integer size, Integer page, String query, String sort, String direction, Long categoryId);
}
