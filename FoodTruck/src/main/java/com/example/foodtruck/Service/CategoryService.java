package com.example.foodtruck.Service;

import com.example.foodtruck.ApiException.ApiException;
import com.example.foodtruck.Model.Category;
import com.example.foodtruck.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
    public void addCategory(Category category){

        categoryRepository.save(category);
    }
    public void updateCategory(Integer auth , Category category) {
        Category cat =categoryRepository.findCategoriesById(auth);
        if (cat == null) {
            throw new ApiException("the id nt found");
        }
        cat.setName(category.getName());
        categoryRepository.save(cat);
    }
    public void deleteCategory(Integer auth){
        Category cat =categoryRepository.findCategoriesById(auth);
        if (cat == null) {
            throw new ApiException("the id nt found");
        }
        categoryRepository.delete(cat);
    }
}
