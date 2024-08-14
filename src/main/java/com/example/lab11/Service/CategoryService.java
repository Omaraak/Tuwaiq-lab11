package com.example.lab11.Service;

import com.example.lab11.Api.ApiException;
import com.example.lab11.Model.Category;
import com.example.lab11.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public void add(Category category) {
        categoryRepository.save(category);
    }

    public void update(int id, Category category) throws ApiException {
        Category c = categoryRepository.getCategoryById(id);
        if (c == null) {
            throw new ApiException("Category not found");
        }
        c.setName(category.getName());
        categoryRepository.save(c);
    }

    public void delete(int id) throws ApiException {
        Category c = categoryRepository.getCategoryById(id);
        if (c == null) {
            throw new ApiException("Category not found");
        }
        categoryRepository.delete(c);
    }
}
