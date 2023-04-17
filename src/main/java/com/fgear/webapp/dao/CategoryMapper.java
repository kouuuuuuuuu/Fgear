package com.fgear.webapp.dao;

import com.fgear.webapp.domain.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> getAllCategory();
    List<Category> getCategoryByCategoryTypeID(String categoryTypeID);
    Category getCategoryByCategoryID(String categoryID);

}
