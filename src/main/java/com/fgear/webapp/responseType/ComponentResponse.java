package com.fgear.webapp.responseType;

import com.fgear.webapp.domain.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComponentResponse {
    private List<ComponentResponseForUser> Components;
    private String errorMessage;

}
