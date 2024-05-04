package com.example.TMS.Controller;

import com.example.TMS.Dto.Courses_Dto;
import com.example.TMS.Service.CourseServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private CourseServices courseService;

    //Add Courses Rest API
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/add")
    public ResponseEntity<Courses_Dto> createCourse(@RequestBody Courses_Dto courseDto) {
        Courses_Dto savedCourses = courseService.createCourses(courseDto);
        return new ResponseEntity<>(savedCourses, HttpStatus.CREATED);
    }

    //Get Courses Rest API
    @GetMapping("/user/get/{_id}")
    public ResponseEntity<Courses_Dto> getCourseByCode(@PathVariable("_id") String code) {
        Courses_Dto courses = courseService.getCourseByCode(code);
        return ResponseEntity.ok(courses);
    }

    //Get All Courses Rest API
    @GetMapping("/user/getAll")
    public ResponseEntity<List<Courses_Dto>> getAllCourses() {
        List <Courses_Dto> Courses = courseService.getAllCourses();
        return ResponseEntity.ok(Courses);
    }

    //Update Courses Rest API
//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/update/{_id}")
    public ResponseEntity<Courses_Dto> updateCourses(@PathVariable("_id") String code,
                                                     @RequestBody Courses_Dto updatedCourses) {
        Courses_Dto courses = courseService.updateCourses(code, updatedCourses);
        return ResponseEntity.ok(courses);
    }

    //Delete Courses Rest API
//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/delete/{_id}")
    public ResponseEntity<String> deleteCourses(@PathVariable("_id") String code) {
        courseService.deleteCourses(code);
        return ResponseEntity.ok("Course Deleted Successfully");
    }
}
