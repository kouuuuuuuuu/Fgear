package com.fgear.webapp.service.interf;

import com.fgear.webapp.domain.Component;

import java.util.List;

public interface ComponentService {
    List<Component> getAllComponent();

    List<Component> getComponentByName(String componentName);

    List<Component> getComponentByBrand(String brandID);
    Component getComponentDetail(int componentID);
    List<Component> getComponentListByCategoryTypeID(String categoryTypeID);

    List<Component> getComponentByCategoryID(String categoryID);
}
