package ru.otus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.model.Product;
import ru.otus.repository.ProductRepository;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("product", product);
        return "product";
    }

    @GetMapping("/add")
    public String showAddForm(Product product) {
        return "add_product";
    }

    @PostMapping("/add")
    public String addProduct(Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add_product";
        }

        productRepository.save(product);
        model.addAttribute("successMessage", "Product has been added successfully");
        return "redirect:/products";
    }

    @GetMapping("delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }


    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "list-products";
    }
}
