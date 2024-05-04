package com.example.TMS.Controller;

import com.example.TMS.Dto.Enrollment_Dto;
import com.example.TMS.Service.EnrollmentServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/enrollment")
public class EnrollementController {
    private EnrollmentServices enrollmentServices;

    //Enroll Students Rest API
    @PostMapping("/admin/add")
    public ResponseEntity<Enrollment_Dto> Enroll(@RequestBody Enrollment_Dto enrollmentDto) {
        Enrollment_Dto savedEnrollment = enrollmentServices.Enrollment(enrollmentDto);
        return new ResponseEntity<>(savedEnrollment, HttpStatus.CREATED);
    }

    //Get Enrolled Student by Student Id Rest API
    @GetMapping("/user/get/{_id}")
    public ResponseEntity<Enrollment_Dto> getEnrolledStdById(@PathVariable("_id") String Id) {
        Enrollment_Dto enrollements = enrollmentServices.getEnrolledById(Id);
        return ResponseEntity.ok(enrollements);
    }

    //Get All Enrollments Rest API
    @GetMapping("/user/getAll")
    public ResponseEntity<List<Enrollment_Dto>> getAllEnrollments() {
        List<Enrollment_Dto> enrollments = enrollmentServices.getAllEnrolled();
        return ResponseEntity.ok(enrollments);
    }

    //  Update Student Enrollment
    @PutMapping("/admin/update/{_id}")
    public ResponseEntity<Enrollment_Dto> updateEnrollment(@PathVariable("_id") String Id,
                                                           @RequestBody Enrollment_Dto updatedEnrollment) {
        Enrollment_Dto enrollment = enrollmentServices.updateEnrollment(Id, updatedEnrollment);
        return ResponseEntity.ok(enrollment);
    }

    //    Unenroll Rest API
    @DeleteMapping("/admin/delete/{_id}")
    public ResponseEntity<String> Unenroll(@PathVariable("_id") String Id) {
        enrollmentServices.Unenroll(Id);
        return ResponseEntity.ok("Unenrolled Successfully");
    }
}
