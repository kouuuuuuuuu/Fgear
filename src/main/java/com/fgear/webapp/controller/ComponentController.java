package com.fgear.webapp.controller;

import com.fgear.webapp.domain.Component;
import com.fgear.webapp.responeType.ComponentResponse;
import com.fgear.webapp.service.interf.ComponentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/component")
@RequiredArgsConstructor
public class ComponentController {
    private final ComponentService componentService;

    @GetMapping("/allComponent")
    public ResponseEntity<List<Component>> getAllComponent(){
        return ResponseEntity.ok().body(componentService.getAllComponent());
    }

    @GetMapping("/componentByName")
    public ResponseEntity<ComponentResponse> getComponentByName(@RequestParam String componentName){
        List<Component> components = componentService.getComponentByName(componentName);
        ComponentResponse componentResponse = new ComponentResponse();
        if(components.isEmpty() || components.size()==0){
            componentResponse.setErrorMessage(("Component with name: "+ componentName +" is not found!"));
            return ResponseEntity.ok().body(componentResponse);
        }
        componentResponse.setComponents(components);
        return ResponseEntity.ok().body(componentResponse);
    }

    @GetMapping("/componentByBrand")
    public ResponseEntity<ComponentResponse> getComponentByBrand(@RequestParam String brandID){
        List<Component> components = componentService.getComponentByBrand(brandID);
        ComponentResponse componentResponse = new ComponentResponse();
        if(components.isEmpty() || components.size()==0){
            componentResponse.setErrorMessage(("Component at brand: "+ brandID +" is not found!"));
            return ResponseEntity.ok().body(componentResponse);
        }
        componentResponse.setComponents(components);
        return ResponseEntity.ok().body(componentResponse);
    }
    @GetMapping("/componentListByCategoryTypeID")
    public ResponseEntity<ComponentResponse> getComponentListByCategoryTypeID(@RequestParam String categoryTypeID){
        List<Component> components = componentService.getComponentByCategoryID(categoryTypeID);
        ComponentResponse componentResponse = new ComponentResponse();
        if(components.isEmpty() || components.size()==0){
            componentResponse.setErrorMessage(("Component at category type: "+ categoryTypeID +" is not found!"));
            return ResponseEntity.ok().body(componentResponse);
        }
        componentResponse.setComponents(components);
        return ResponseEntity.ok().body(componentResponse);
    }
    @GetMapping("componentListByCategoryID")
    public ResponseEntity<ComponentResponse> getComponentListByCategoryID(@RequestParam String categoryID){
        List<Component> components = componentService.getComponentByCategoryID(categoryID);
        ComponentResponse componentResponse = new ComponentResponse();
        if(components.isEmpty() || components.size()==0){
            componentResponse.setErrorMessage(("Component at category type: "+ categoryID +" is not found!"));
            return ResponseEntity.ok().body(componentResponse);
        }
        componentResponse.setComponents(components);
        return ResponseEntity.ok().body(componentResponse);
    }
}
