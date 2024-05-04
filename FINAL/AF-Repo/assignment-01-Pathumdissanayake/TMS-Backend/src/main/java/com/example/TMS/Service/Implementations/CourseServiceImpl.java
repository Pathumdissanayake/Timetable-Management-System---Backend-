package com.example.TMS.Service.Implementations;

import com.example.TMS.Dto.Courses_Dto;
import com.example.TMS.Entity.Courses;
import com.example.TMS.Exception.ResourceNotFoundException;
import com.example.TMS.Mapper.CourseMapper;
import com.example.TMS.Repository.CoursesInterface;
import com.example.TMS.Service.CourseServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseServices {

    private final CoursesInterface courseInterface;

    @Autowired
    public CourseServiceImpl(CoursesInterface coursesInterface, CoursesInterface courseInterface) {
        this.courseInterface = courseInterface;
    }
    @Override
    public Courses_Dto createCourses(Courses_Dto courseDto) {
        Courses course = CourseMapper.mapToCourses(courseDto);
        Courses savedCourse = courseInterface.save(course);
        return CourseMapper.mapToCourse_Dto(savedCourse);
    }

    @Override
    public Courses_Dto getCourseByCode(String code) {
        Courses course = courseInterface.findById(code)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found with the give course ID : "+code));
        return CourseMapper.mapToCourse_Dto(course);
    }

    @Override
    public List<Courses_Dto> getAllCourses() {
        List<Courses> courses = courseInterface.findAll();
        return courses.stream().map((course) -> CourseMapper.mapToCourse_Dto(course))
                .collect(Collectors.toList());
    }

    @Override
    public Courses_Dto updateCourses(String code, Courses_Dto updatedCourses) {
        Courses courses = courseInterface.findById(code).orElseThrow(
                () -> new ResourceNotFoundException("Course is not existing with the given id : "+code)
        );

        courses.setCourseName(updatedCourses.getCourseName());
        courses.setCredits(updatedCourses.getCredits());
        courses.setFaculty(updatedCourses.getFaculty());
        courses.setDescription(updatedCourses.getDescription());

        Courses updated = courseInterface.save(courses);
        return CourseMapper.mapToCourse_Dto(updated);
    }

    @Override
    public void deleteCourses(String code) {
        Courses courses = courseInterface.findById(code).orElseThrow(
                () -> new ResourceNotFoundException("Course is not existing with the given id : "+code)
        );

        courseInterface.deleteById(code);
    }
}
