package com.example.vendingmachine.service;

import com.example.vendingmachine.model.Category;
import com.example.vendingmachine.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void addCategory(List<String> input) {
        Category addedCategory = input.size() == 4 ?
                categoryRepository.save(new Category(input.get(1), Double.parseDouble(input.get(2)), Integer.parseInt(input.get(3)))) :
                categoryRepository.save(new Category(input.get(1), Double.parseDouble(input.get(2)), 0));
        System.out.println(addedCategory);
    }

    public void addItem(List<String> input) {
        Category foundCategory = categoryRepository.getByName(input.get(1));
        foundCategory.setAmount(foundCategory.getAmount() + Integer.parseInt(input.get(2)));
        Category save = categoryRepository.save(foundCategory);
        System.out.println(save);
    }

    public void list(List<String> input) {
        List<Category> categories = categoryRepository.findAll().stream().sorted(Comparator.comparing(Category::getAmount).reversed()).collect(Collectors.toList());
        categories.forEach(System.out::println);
    }

    public void clear(List<String> input) {
        List<Category> categoryDeleteList = categoryRepository.findAll().stream().filter(category -> category.getAmount() == 0).collect(Collectors.toList());
        categoryDeleteList.stream().map(list -> list.getName() + " " + list.getPrice()).forEach(System.out::println);
        categoryDeleteList.clear();
    }
}
