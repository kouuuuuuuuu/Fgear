package com.fgear.webapp.service.interf;

import com.fgear.webapp.domain.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();
    List<Category> getCategoryByCategoryTypeID(String categoryTypeID);
    Category getCategoryByCategoryID(String categoryID);
}
