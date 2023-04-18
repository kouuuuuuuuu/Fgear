package com.fgear.webapp.dao;

import com.fgear.webapp.domain.Component;
import com.fgear.webapp.responseType.ComponentResponseForUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ComponentMapper {
    List<ComponentResponseForUser> getAllComponent();
    List<ComponentResponseForUser> getComponentByName(String componentName);
    List<ComponentResponseForUser> getComponentByBrand(String brandID);
    ComponentResponseForUser getComponentDetail(int componentID);
    List<ComponentResponseForUser> getComponentListByCategoryTypeID(String categoryTypeID);

    List<ComponentResponseForUser> getComponentByCategoryID(String categoryID);
}
