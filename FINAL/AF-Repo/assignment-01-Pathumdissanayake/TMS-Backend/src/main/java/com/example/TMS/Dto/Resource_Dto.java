package com.example.TMS.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resource_Dto {
    private String resourceId;
    private String faculty;
    private String resourceName;
    private String resourceType;
    private boolean availability;

}
