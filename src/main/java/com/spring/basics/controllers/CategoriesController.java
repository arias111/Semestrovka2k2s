package com.spring.basics.controllers;

import com.spring.basics.models.Category;
import com.spring.basics.models.Product;
import com.spring.basics.repositories.CategoriesRepository;
import com.spring.basics.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class CategoriesController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoriesRepository categoryRepository;

    @GetMapping("/categories/{categoryId}")
    public String getCategoriesPage(@PathVariable Long categoryId, Model model) {
        if (categoryId == null) {
            List<Category> categories = categoryRepository.findAll();
            model.addAttribute("categories", categories);
        } else {
            Optional<Category> category = categoryRepository.findById(categoryId);
            List<Product> products = productRepository.findProductsByCategoryId(categoryId);
            category.ifPresent(value -> model.addAttribute("name", value.getName()));
            model.addAttribute("products", products);
            return "products";
        }
        return "categories";
    }

    @GetMapping("/categories")
    public String getCategoriesPage1(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "categories";
    }

}