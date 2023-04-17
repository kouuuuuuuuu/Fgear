package com.fgear.webapp.controller;

import com.fgear.webapp.domain.CategoryType;
import com.fgear.webapp.service.interf.CategoryTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/categoryType")
public class CategoryTypeController {
    private final CategoryTypeService categoryTypeService;
    @GetMapping("/getAllCategoryType")
    public List<CategoryType> getAllCategoryType(){
        return categoryTypeService.getAllCategoryType();
    }
}
