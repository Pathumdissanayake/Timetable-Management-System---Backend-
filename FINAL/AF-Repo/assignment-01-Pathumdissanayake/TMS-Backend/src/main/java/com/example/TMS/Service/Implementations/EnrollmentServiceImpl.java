package com.example.TMS.Service.Implementations;

import com.example.TMS.Dto.Enrollment_Dto;
import com.example.TMS.Entity.Enrollment;
import com.example.TMS.Exception.ResourceNotFoundException;
import com.example.TMS.Mapper.EnrollementMapper;
import com.example.TMS.Repository.EnrollementInterface;
import com.example.TMS.Service.EnrollmentServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentServices {
    private final EnrollementInterface enrollementInterface;

    @Autowired
    public EnrollmentServiceImpl(EnrollementInterface enrollementInterface, EnrollementInterface enrollementInterface1) {
        this.enrollementInterface = enrollementInterface1;
    }

    @Override
    public Enrollment_Dto Enrollment(Enrollment_Dto enrollmentDto) {
        Enrollment enrollment = EnrollementMapper.mapToEnrollement(enrollmentDto );
        Enrollment savedEnrollment = enrollementInterface.save(enrollment);
        return EnrollementMapper.maptToEnrollment_Dto(savedEnrollment);
    }

    @Override
    public Enrollment_Dto getEnrolledById(String Id) {
        Enrollment enrollment = enrollementInterface.findById(Id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No enrolled user found with the given student id "+ Id));
        return EnrollementMapper.maptToEnrollment_Dto(enrollment);
    }

    @Override
    public List<Enrollment_Dto> getAllEnrolled() {
        List<Enrollment> enrollments = enrollementInterface.findAll();
        return enrollments.stream().map((enrollment) -> EnrollementMapper.maptToEnrollment_Dto(enrollment))
                .collect(Collectors.toList());
    }

    @Override
    public Enrollment_Dto updateEnrollment(String Id, Enrollment_Dto updatedEnrollment) {
        Enrollment enrollment = enrollementInterface.findById(Id).orElseThrow(
                () -> new ResourceNotFoundException("No enrolled user found")
        );

        enrollment.setCourseId(updatedEnrollment.getCourseId());
        enrollment.setStudentId(updatedEnrollment.getStudentId());

        Enrollment updated = enrollementInterface.save(enrollment);
        return EnrollementMapper.maptToEnrollment_Dto(updated);
    }


    @Override
    public void Unenroll(String Id) {
        Enrollment enrollment = enrollementInterface.findById(Id).orElseThrow(
                () -> new ResourceNotFoundException("No enrolled user found with the given student Id "+Id)
        );

        enrollementInterface.deleteById(Id);
    }
}
