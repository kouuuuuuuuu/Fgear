package com.fgear.webapp.service.impl;

import com.fgear.webapp.dao.CategoryMapper;
import com.fgear.webapp.domain.Category;
import com.fgear.webapp.service.interf.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CategoryServiceImp implements CategoryService {
    private final CategoryMapper categoryMapper;
    @Override
    public List<Category> getAllCategory() {
        log.debug("get all category");
        return categoryMapper.getAllCategory();
    }

    @Override
    public List<Category> getCategoryByCategoryTypeID(String categoryTypeID) {
        log.debug("get category by category type ID");
        return categoryMapper.getCategoryByCategoryTypeID(categoryTypeID);
    }

    @Override
    public Category getCategoryByCategoryID(String categoryID) {
        log.debug("get category by category ID");
        return categoryMapper.getCategoryByCategoryID(categoryID);
    }
}
