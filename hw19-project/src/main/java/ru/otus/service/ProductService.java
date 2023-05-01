package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.model.Cart;
import ru.otus.model.Product;
import ru.otus.repository.ProductRepository;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id));
    }

    public void addProductToCart(Product product, Cart cart) {
        cart.addProduct(product);
    }

    public void removeProductFromCart(Product product, Cart cart) {
        cart.removeProduct(product);
    }

    public BigDecimal calculateTotal(Cart cart) {
        return cart.getTotal();
    }

}

