package com.fgear.webapp.dao;

import com.fgear.webapp.domain.Component;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ComponentMapper {
    List<Component> getAllComponent();
    List<Component> getComponentByName(String componentName);
    List<Component> getComponentByBrand(String brandID);
    Component getComponentDetail(int componentID);
    List<Component> getComponentListByCategoryTypeID(String categoryTypeID);

    List<Component> getComponentByCategoryID(String categoryID);
}
