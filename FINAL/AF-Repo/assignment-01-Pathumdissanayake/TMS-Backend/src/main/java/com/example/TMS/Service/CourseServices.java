package com.example.TMS.Service;

import com.example.TMS.Dto.Courses_Dto;

import java.util.List;

public interface CourseServices {
    Courses_Dto createCourses(Courses_Dto courseDto);
    Courses_Dto getCourseByCode(String code);

    List<Courses_Dto> getAllCourses();

    Courses_Dto updateCourses(String code, Courses_Dto updatedCourses);

    void deleteCourses(String code);


}
