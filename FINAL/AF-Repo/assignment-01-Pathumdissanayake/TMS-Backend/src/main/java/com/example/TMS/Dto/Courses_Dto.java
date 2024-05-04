package com.example.TMS.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Courses_Dto {

    private String courseName;
    private String code;
    private String description;
    private String credits;
    private String faculty;
}
