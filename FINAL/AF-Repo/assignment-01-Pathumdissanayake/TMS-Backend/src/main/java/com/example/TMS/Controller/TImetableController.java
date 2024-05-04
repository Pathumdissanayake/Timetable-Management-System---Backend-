package com.example.TMS.Controller;

import com.example.TMS.Dto.Timetable_Dto;
import com.example.TMS.Service.TimetableServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/timetables")
public class TImetableController {
    private TimetableServices timetableServices;

    //Add Courses Rest API
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/add")
    public ResponseEntity<Timetable_Dto> createTimetable(@RequestBody Timetable_Dto timetable_dto) {
        Timetable_Dto savedTimetables = timetableServices.createTimetables(timetable_dto);
        return new ResponseEntity<>(savedTimetables, HttpStatus.CREATED);
    }

    //Get Courses Rest API
    @GetMapping("/user/get/{_id}")
    public ResponseEntity<Timetable_Dto> getTimetableByTableId(@PathVariable("_id") String tableId) {
        Timetable_Dto timetable = timetableServices.getTimetableByTableId(tableId);
        return ResponseEntity.ok(timetable);
    }

    //Get All Courses Rest API
    @GetMapping("/user/getAll")
    public ResponseEntity<List<Timetable_Dto>> getAllTimeTables() {
        List <Timetable_Dto> timetables = timetableServices.getAllTimetables();
        return ResponseEntity.ok(timetables);
    }

    //Update Timetable Rest API
//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/update/{_id}")
    public ResponseEntity<Timetable_Dto> updatedTimetables(@PathVariable("_id") String tableId,
                                                           @RequestBody Timetable_Dto updatedTimetable) {
        Timetable_Dto timetable = timetableServices.updateTimetable(tableId, updatedTimetable);
        return ResponseEntity.ok(timetable);
    }

    //Delete Timetable Rest API
//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/delete/{_id}")
    public ResponseEntity<String> deleteTimetables(@PathVariable("_id") String tableId) {
        timetableServices.DeleteTimetable(tableId);
        return ResponseEntity.ok("Timetable Deleted Successfully");
    }




}
