package com.fgear.webapp.responseType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComponentResponseForUser {
    private int componentID;
    private String componentName;
    private int price;
    private int quantity;
    private String image;
    private String description;
    private String brandName;
    private String categoryID;
    private int status;
}
