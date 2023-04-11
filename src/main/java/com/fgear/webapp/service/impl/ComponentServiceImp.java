package com.fgear.webapp.service.impl;

import com.fgear.webapp.dao.ComponentMapper;
import com.fgear.webapp.domain.Component;
import com.fgear.webapp.service.interf.ComponentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ComponentServiceImp implements ComponentService {

    private final ComponentMapper componentMapper;
    @Override
    public List<Component> getAllComponent() {
        log.debug("get all componnent");
        return componentMapper.getAllComponent();
    }

    @Override
    public List<Component> getComponentByName(String componentName) {
        log.debug("get component by name");
        return componentMapper.getComponentByName(componentName);
    }

    @Override
    public List<Component> getComponentByBrand(String brandID) {
        log.debug("get component by brand");
        return componentMapper.getComponentByBrand(brandID);
    }

    @Override
    public Component getComponentDetail(int componentID) {
        log.debug("get component detail by id");
        return componentMapper.getComponentDetail(componentID);
    }

    @Override
    public List<Component> getComponentListByCategoryTypeID(String categoryTypeID) {
        log.debug("get component by list category type");
        return componentMapper.getComponentListByCategoryTypeID(categoryTypeID);
    }

    @Override
    public List<Component> getComponentByCategoryID(String categoryID) {
        log.debug("get component by category ");
        return componentMapper.getComponentByCategoryID(categoryID);
    }
}
