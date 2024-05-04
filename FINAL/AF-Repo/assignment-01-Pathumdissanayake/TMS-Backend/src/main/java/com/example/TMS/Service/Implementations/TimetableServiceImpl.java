package com.example.TMS.Service.Implementations;

import com.example.TMS.Dto.Timetable_Dto;
import com.example.TMS.Entity.Courses;
import com.example.TMS.Entity.Enrollment;
import com.example.TMS.Entity.Timetable;
import com.example.TMS.Entity.Users;
import com.example.TMS.Exception.ResourceNotFoundException;
import com.example.TMS.Mapper.TImetableMapper;
import com.example.TMS.Repository.CoursesInterface;
import com.example.TMS.Repository.EnrollementInterface;
import com.example.TMS.Repository.TimetableInterface;
import com.example.TMS.Repository.UserInterface;
import com.example.TMS.Service.TimetableServices;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class TimetableServiceImpl implements TimetableServices {

    @Autowired
    private final TimetableInterface timetableInterface;

    @Autowired
    private CoursesInterface coursesInterface;
    @Autowired
    private UserInterface userInterface;
    @Autowired
    private EnrollementInterface enrollementInterface;
    @Autowired
    private EmailService emailService;

    @Autowired
    public TimetableServiceImpl(TimetableInterface timetableInterface, TimetableInterface timetableInterface1) {
        this.timetableInterface = timetableInterface1;
    }

    @Override
    public Timetable_Dto createTimetables(Timetable_Dto timetableDto) {

        Timetable timetable = TImetableMapper.mapToTimetable(timetableDto);
        Timetable savedTimetable = timetableInterface.save(timetable);

        Courses courses = coursesInterface.findById(timetable.getCourseId()).orElse(null);
        if(courses != null) {
            // Fetch all enrollments for the given course ID
            List<Enrollment> enrollments = enrollementInterface.findByCourseId(timetable.getCourseId());

            // Extract the student IDs
            List<String> studentIds = enrollments.stream()
                    .map(Enrollment::getStudentId)
                    .collect(Collectors.toList());

            // Fetch users based on the student IDs
            List<Users> enrolledUsers = userInterface.findByStudentIdIn(studentIds);

            //Notify each via email
            for (Users user : enrolledUsers) {
                String emailContent = "Hello " + user.getName() + ",\n\n" +
                        "A new timetable has been added for the course: " + courses.getCourseName() + ".\n" +
                        "Date: " + timetable.getDate() + "\n" +
                        "Time: " + timetable.getTimeStart() + " to " + timetable.getTimeEnd() + "\n" +
                        "Location: Room " + timetable.getRoomId() + "\n\n" +
                        "Best regards,\nSLIIT - Student Support";

                emailService.sendMail(user.getEmail(), "New Timetable Added", emailContent);
            }
        }
        return TImetableMapper.mapToTimetable_Dto(savedTimetable);
    }

    @Override
    public Timetable_Dto getTimetableByTableId(String tableId) {
        Timetable timetable = timetableInterface.findById(tableId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Timetable not found with the given Table ID "+tableId));
        return TImetableMapper.mapToTimetable_Dto(timetable);
    }

    @Override
    public List<Timetable_Dto> getAllTimetables() {
        List<Timetable> timetalbes = timetableInterface.findAll();
        return timetalbes.stream().map((timetable) -> TImetableMapper.mapToTimetable_Dto(timetable))
                .collect(Collectors.toList());
    }

    @Override
    public Timetable_Dto updateTimetable(String tableId, Timetable_Dto updatedTimetable) {
        Timetable timetables = timetableInterface.findById(tableId).orElseThrow(
                () -> new ResourceNotFoundException("Timetable not found with the given Table ID "+tableId)
        );

        timetables.setLecturer(updatedTimetable.getLecturer());
        timetables.setFaculty(updatedTimetable.getFaculty());
        timetables.setDate(updatedTimetable.getDate());
        timetables.setTimeEnd(updatedTimetable.getTimeEnd());
        timetables.setTimeStart(updatedTimetable.getTimeStart());
        timetables.setCourseId(updatedTimetable.getCourseId());
        timetables.setResourceId(updatedTimetable.getResourceId());
        timetables.setRoomId(updatedTimetable.getRoomId());

        Timetable updated = timetableInterface.save(timetables);

        // Fetch course information
        Courses courses = coursesInterface.findById(timetables.getCourseId()).orElse(null);
        if (courses != null) {
            List<Enrollment> enrollments = enrollementInterface.findByCourseId(timetables.getCourseId());

            List<String> studentIds = enrollments.stream()
                    .map(Enrollment::getStudentId)
                    .collect(Collectors.toList());

            List<Users> enrolledUsers = userInterface.findByStudentIdIn(studentIds);

            for (Users user : enrolledUsers) {
                String emailContent = "Hello " + user.getName() + ",\n\n" +
                        "The timetable for your course: " + courses.getCourseName() + " has been updated.\n" +
                        "New Details:\n" +
                        "Date: " + timetables.getDate() + "\n" +
                        "Time: " + timetables.getTimeStart() + " to " + timetables.getTimeEnd() + "\n" +
                        "Location: Room " + timetables.getRoomId() + "\n\n" +
                        "Best regards,\nSLIIT - Student Support";

                emailService.sendMail(user.getEmail(), "Timetable Updated", emailContent);
            }
        }

        return TImetableMapper.mapToTimetable_Dto(updated);
    }

    @Override
    public void DeleteTimetable(String tableId) {
        Timetable timetable = timetableInterface.findById(tableId).orElseThrow(
                () -> new ResourceNotFoundException("Timetable not found with the given Table ID "+tableId)
        );

        // Fetch course information
        Courses courses = coursesInterface.findById(timetable.getCourseId()).orElse(null);
        if (courses != null) {
            List<Enrollment> enrollments = enrollementInterface.findByCourseId(timetable.getCourseId());

            List<String> studentIds = enrollments.stream()
                    .map(Enrollment::getStudentId)
                    .collect(Collectors.toList());

            List<Users> enrolledUsers = userInterface.findByStudentIdIn(studentIds);

            for (Users user : enrolledUsers) {
                String emailContent = "Hello " + user.getName() + ",\n\n" +
                        "The timetable for your course: " + courses.getCourseName() + " has been deleted.\n" +
                        "Please check your course schedule for updates.\n\n" +
                        "Best regards,\nSLIIT - Student Support";

                emailService.sendMail(user.getEmail(), "Timetable Deleted", emailContent);
            }
        }

        timetableInterface.deleteById(tableId);
    }
}
