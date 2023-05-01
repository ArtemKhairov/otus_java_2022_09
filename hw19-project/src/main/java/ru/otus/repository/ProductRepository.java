package ru.otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    List<Product> findByCategoryId(Long categoryId);
    List<Product> findAll();
    List<Product> findByName(String name);
}
