package com.example.TMS.Controller;

import com.example.TMS.Entity.Enrollment;
import com.example.TMS.Entity.Users;
import com.example.TMS.Repository.EnrollementInterface;
import com.example.TMS.Repository.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
public class UserController {

    @Autowired
    private UserInterface userInterface;

    @Autowired
    private EnrollementInterface enrollementInterface;

    @GetMapping("/viewList/byCourse/{courseId}")
    public ResponseEntity<List<Users>> getUsersByCourseId(@PathVariable String courseId) {
        // Find all enrollments for the given course ID
        List<Enrollment> enrollments = enrollementInterface.findByCourseId(courseId);

        if (enrollments.isEmpty()) {
            return ResponseEntity.noContent().build(); // No students found
        }

        // Get student IDs from enrollments
        List<String> studentIds = enrollments.stream()
                .map(Enrollment::getStudentId) // Corrected method reference
                .collect(Collectors.toList());

        // Fetch users by student IDs
        List<Users> users = userInterface.findByStudentIdIn(studentIds); // Corrected method

        return ResponseEntity.ok(users); // Return the list of users enrolled in the course
    }
}
