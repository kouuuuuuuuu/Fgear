package com.fgear.webapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Component {
    private int componentID;
    private String componentName;
    private int sellPrice;
    private int rentPrice;
    private int quantity;
    private String image;
    private String description;
    private int brandID;
    private String categoryID;
    private int status;
}
