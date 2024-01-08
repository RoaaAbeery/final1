package com.example.foodtruck.Controller;

import com.example.foodtruck.Model.Category;
import com.example.foodtruck.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity GetAllCategory(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategory());
    }
    @PostMapping("/add")
    public ResponseEntity AddCategory(@RequestBody @Valid Category category){
        categoryService.addCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body("added Category");
    }
    @PutMapping("/put/{id}")
    public ResponseEntity UpdateCategory(@PathVariable Integer id, @RequestBody @Valid Category category){
        categoryService.updateCategory(id, category);
        return ResponseEntity.status(HttpStatus.OK).body("update Category");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id){
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body("delete Category");
    }
}
