package com.fgear.webapp.service.impl;

import com.fgear.webapp.dao.CategoryTypeMapper;
import com.fgear.webapp.domain.CategoryType;
import com.fgear.webapp.service.interf.CategoryTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CategoryTypeServiceImp implements CategoryTypeService {
    private final CategoryTypeMapper categoryTypeMapper;
    @Override
    public List<CategoryType> getAllCategoryType() {
        log.debug("get all category type");
        return categoryTypeMapper.getAllCategoryType();
    }
}
