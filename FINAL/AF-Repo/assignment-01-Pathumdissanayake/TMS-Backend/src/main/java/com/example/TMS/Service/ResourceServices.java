package com.example.TMS.Service;

import com.example.TMS.Dto.Resource_Dto;

import java.util.List;

public interface ResourceServices {
    Resource_Dto createResources(Resource_Dto resourceDto);
    Resource_Dto getResourceById(String resourceId);
    List<Resource_Dto> getAllResources();
    Resource_Dto updateResources(String resourceId, Resource_Dto updatedResources);
    void deleteResources(String resourceID);
}
