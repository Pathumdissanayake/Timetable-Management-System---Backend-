package com.example.TMS.Mapper;

import com.example.TMS.Dto.Courses_Dto;
import com.example.TMS.Entity.Courses;

public class CourseMapper {
    public static Courses_Dto mapToCourse_Dto(Courses course) {
        return new Courses_Dto(
                course.getCourseName(),
                course.getCode(),
                course.getDescription(),
                course.getCredits(),
                course.getFaculty()
        );
    }

    public static Courses mapToCourses(Courses_Dto courseDto) {
        return new Courses(
                courseDto.getCourseName(),
                courseDto.getCode(),
                courseDto.getDescription(),
                courseDto.getCredits(),
                courseDto.getFaculty()
        );
    }
}
