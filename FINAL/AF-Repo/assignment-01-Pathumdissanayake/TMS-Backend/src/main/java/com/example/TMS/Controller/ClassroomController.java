package com.example.TMS.Controller;

import com.example.TMS.Dto.Classroom_Dto;
import com.example.TMS.Service.ClassroomServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {
    private ClassroomServices classroomServices;

    //Add Classrooms Rest API
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/add")
    public ResponseEntity<Classroom_Dto> createClassroom(@RequestBody Classroom_Dto classroomDto) {
        Classroom_Dto savedClassroom = classroomServices.createClass(classroomDto);
        return new ResponseEntity<>(savedClassroom, HttpStatus.CREATED);
    }


    //Get Classroom Rest API
    @GetMapping("/user/get/{_id}")
    public ResponseEntity<Classroom_Dto> getClassByRoomId(@PathVariable("_id") String roomId) {
        Classroom_Dto classrooms = classroomServices.getClassbyRoomId(roomId);
        return ResponseEntity.ok(classrooms);
    }

    //Get All Classroom Rest API
    @GetMapping("/user/getAll")
    public ResponseEntity<List<Classroom_Dto>> getAllClassrooms() {
        List <Classroom_Dto> classrooms = classroomServices.getAllClasses();
        return ResponseEntity.ok(classrooms);
    }

    //Update Classroom Rest API
//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/update/{_id}")
    public ResponseEntity<Classroom_Dto> updateClassrooms (@PathVariable("_id") String roomId,
                                                           @RequestBody Classroom_Dto updatedClassrooms) {
        Classroom_Dto classrooms = classroomServices.updateClasses(roomId, updatedClassrooms);
        return ResponseEntity.ok(classrooms);
    }

    //Delete Classroom Rest API
//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/delete/{_id}")
    public ResponseEntity<String> deleteClassrooms(@PathVariable("_id") String roomId) {
        classroomServices.deleteClass(roomId);
        return ResponseEntity.ok("Classroom Deleted Successfully");
    }
}
