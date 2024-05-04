package com.example.TMS.Mapper;

import com.example.TMS.Dto.Enrollment_Dto;
import com.example.TMS.Entity.Enrollment;

public class EnrollementMapper {
    public static Enrollment_Dto maptToEnrollment_Dto(Enrollment enrollment) {
        return new Enrollment_Dto(
                enrollment.getStudentId(),
                enrollment.getCourseId()
        );
    }

    public static Enrollment mapToEnrollement(Enrollment_Dto enrollmentDto) {
        return new Enrollment(
                enrollmentDto.getStudentId(),
                enrollmentDto.getCourseId()
        );
    }
}
