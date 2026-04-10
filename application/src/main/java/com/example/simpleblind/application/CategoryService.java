package com.example.simpleblind.application;

import com.example.simpleblind.application.dto.CategoryResult;
import com.example.simpleblind.common.exception.EntityNotFoundException;
import com.example.simpleblind.infra.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryResult> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryResult::from)
                .toList();
    }

    public CategoryResult findById(Long id) {
        return categoryRepository.findById(id)
                .map(CategoryResult::from)
                .orElseThrow(() -> new EntityNotFoundException("Category not found: " + id));
    }
}
