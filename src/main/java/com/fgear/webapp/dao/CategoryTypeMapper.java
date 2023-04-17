package com.fgear.webapp.dao;

import com.fgear.webapp.domain.CategoryType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryTypeMapper {
    List<CategoryType> getAllCategoryType();
}
