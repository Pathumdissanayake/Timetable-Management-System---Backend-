package com.example.TMS.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "Courses")
public class Courses {

    @Field("courseName")
    private String courseName;

    @Id
    private String code;

    @Field("description")
    private String description;

    @Field("credits")
    private String credits;

    @Field("faculty")
    private String faculty;
}
