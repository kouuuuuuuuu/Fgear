package com.fgear.webapp.controller;

import com.fgear.webapp.domain.Category;
import com.fgear.webapp.service.interf.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/getAllCategory")
    public List<Category> getAllCategory(){
        return categoryService.getAllCategory();
    }
    @GetMapping("/getCategoryByCategoryTypeID")
    public List<Category> getCategoryByCategoryTypeID(@RequestParam String CategoryTypeID){
        return categoryService.getCategoryByCategoryTypeID(CategoryTypeID);
    }
    @GetMapping("/getCategoryByCategoryID")
    public Category getCategoryByCategoryID(@RequestParam String CategoryID){
        return categoryService.getCategoryByCategoryID(CategoryID);
    }
}
