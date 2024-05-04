package com.example.TMS.Controller;

import com.example.TMS.Dto.Resource_Dto;
import com.example.TMS.Service.ResourceServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/resources")
public class ResourceController {
    private ResourceServices resourceServices;

    //Add Resources Rest API
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/add")
    public ResponseEntity<Resource_Dto> createResources(@RequestBody Resource_Dto resourceDto) {
        Resource_Dto savedResources = resourceServices.createResources(resourceDto);
        return new ResponseEntity<>(savedResources, HttpStatus.CREATED);
    }

    //Get Resources Rest API
    @GetMapping("/user/get/{_id}")
    public ResponseEntity<Resource_Dto> getResourcesById(@PathVariable("_id") String resourceId) {
        Resource_Dto resources = resourceServices.getResourceById(resourceId);
        return ResponseEntity.ok(resources);
    }

    //Get All Resources Rest API
    @GetMapping("/user/getAll")
    public ResponseEntity<List<Resource_Dto>> getAllResources() {
        List <Resource_Dto> Resources = resourceServices.getAllResources();
        return ResponseEntity.ok(Resources);
    }

    //Update Resource Rest API
//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/update/{_id}")
    public ResponseEntity<Resource_Dto> updateResources(@PathVariable("_id") String resourceId,
                                                        @RequestBody Resource_Dto updatedResources) {
        Resource_Dto resources = resourceServices.updateResources(resourceId, updatedResources);
        return ResponseEntity.ok(resources);
    }

    //Delete Resources Rest API
//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/delete/{_id}")
    public ResponseEntity<String> deleteResources(@PathVariable("_id") String resourceId) {
        resourceServices.deleteResources(resourceId);
        return ResponseEntity.ok("Resource Deleted Successfully");
    }
}
