package com.example.vendingmachine.repository;

import com.example.vendingmachine.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getByName(String categoryName);

    Optional<Category> findByName(String name);

}