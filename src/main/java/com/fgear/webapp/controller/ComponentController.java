package com.fgear.webapp.controller;

import com.fgear.webapp.domain.Component;
import com.fgear.webapp.responseType.ComponentResponse;
import com.fgear.webapp.responseType.ComponentResponseForUser;
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
    public ResponseEntity<List<ComponentResponseForUser>> getAllComponent(){
        return ResponseEntity.ok().body(componentService.getAllComponent());
    }

    @GetMapping("/componentByName")
    public ResponseEntity<ComponentResponse> getComponentByName(@RequestParam String componentName){
        List<ComponentResponseForUser> components = componentService.getComponentByName(componentName);
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
        List<ComponentResponseForUser> components = componentService.getComponentByBrand(brandID);
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
        List<ComponentResponseForUser> components = componentService.getComponentByCategoryID(categoryTypeID);
        ComponentResponse componentResponse = new ComponentResponse();
        if(components.isEmpty() || components.size()==0){
            componentResponse.setErrorMessage(("Component at category type: "+ categoryTypeID +" is not found!"));
            return ResponseEntity.ok().body(componentResponse);
        }
        componentResponse.setComponents(components);
        return ResponseEntity.ok().body(componentResponse);
    }
    @GetMapping("/componentListByCategoryID")
    public ResponseEntity<ComponentResponse> getComponentListByCategoryID(@RequestParam String categoryID){
        List<ComponentResponseForUser> components = componentService.getComponentByCategoryID(categoryID);
        ComponentResponse componentResponse = new ComponentResponse();
        if(components.isEmpty() || components.size()==0){
            componentResponse.setErrorMessage(("Component at category type: "+ categoryID +" is not found!"));
            return ResponseEntity.ok().body(componentResponse);
        }
        componentResponse.setComponents(components);
        return ResponseEntity.ok().body(componentResponse);
    }
    @GetMapping("/componentDetail")
    public ResponseEntity<ComponentResponseForUser> getDetailComponent(@RequestParam int componentID){
        return ResponseEntity.ok().body(componentService.getComponentDetail(componentID));
    }
}
