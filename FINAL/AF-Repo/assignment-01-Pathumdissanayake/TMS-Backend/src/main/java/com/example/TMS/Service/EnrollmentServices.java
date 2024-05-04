package com.example.TMS.Service;

import com.example.TMS.Dto.Enrollment_Dto;

import java.util.List;

public interface EnrollmentServices {
    Enrollment_Dto Enrollment(Enrollment_Dto enrollmentDto);
    Enrollment_Dto getEnrolledById(String Id);
    List<Enrollment_Dto> getAllEnrolled();
    Enrollment_Dto updateEnrollment(String Id, Enrollment_Dto updatedEnrollment);
    void Unenroll(String Id);
}
