package com.fgear.webapp.service.interf;

import com.fgear.webapp.domain.Component;
import com.fgear.webapp.responseType.ComponentResponseForUser;

import java.util.List;

public interface ComponentService {
    List<ComponentResponseForUser> getAllComponent();
    List<ComponentResponseForUser> getComponentByName(String componentName);
    List<ComponentResponseForUser> getComponentByBrand(String brandID);
    ComponentResponseForUser getComponentDetail(int componentID);
    List<ComponentResponseForUser> getComponentListByCategoryTypeID(String categoryTypeID);

    List<ComponentResponseForUser> getComponentByCategoryID(String categoryID);
}
