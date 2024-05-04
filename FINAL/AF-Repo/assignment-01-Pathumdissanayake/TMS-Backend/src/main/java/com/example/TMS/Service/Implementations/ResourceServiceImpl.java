package com.example.TMS.Service.Implementations;

import com.example.TMS.Dto.Resource_Dto;
import com.example.TMS.Entity.Resources;
import com.example.TMS.Exception.ResourceNotFoundException;
import com.example.TMS.Mapper.ResourceMapper;
import com.example.TMS.Repository.ResourceInterface;
import com.example.TMS.Service.ResourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResourceServiceImpl implements ResourceServices {
    private final ResourceInterface resourceInterface;

    @Autowired
    public ResourceServiceImpl(ResourceInterface resourceInterface, ResourceInterface resourceInterface1) {
        this.resourceInterface = resourceInterface1;
    }

    @Override
    public Resource_Dto createResources(Resource_Dto resourceDto) {
        Resources resources = ResourceMapper.mapToResources(resourceDto);
        Resources savedResources = resourceInterface.save(resources);
        return ResourceMapper.mapToResource_Dto(savedResources);
    }

    @Override
    public Resource_Dto getResourceById(String resourceId) {
        Resources resources = resourceInterface.findById(resourceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Resource not found with the given resource Id : "+resourceId));
        return ResourceMapper.mapToResource_Dto(resources);
    }

    @Override
    public List<Resource_Dto> getAllResources() {
        List<Resources> resources = resourceInterface.findAll();
        return resources.stream().map((resource) -> ResourceMapper.mapToResource_Dto(resource))
                .collect(Collectors.toList());
    }

    @Override
    public Resource_Dto updateResources(String resourceId, Resource_Dto updatedResources) {
        Resources resources = resourceInterface.findById(resourceId).orElseThrow(
                () -> new ResourceNotFoundException("Resource is not found with the given resource ID : "+resourceId)
        );

        resources.setResourceId(updatedResources.getResourceId());
        resources.setResourceName(updatedResources.getResourceName());
        resources.setResourceType(updatedResources.getResourceType());
        resources.setFaculty(updatedResources.getFaculty());
        resources.setAvailability(updatedResources.isAvailability());

        Resources updated = resourceInterface.save(resources);
        return ResourceMapper.mapToResource_Dto(updated);
    }

    @Override
    public void deleteResources(String resourceID) {
        Resources resources = resourceInterface.findById(resourceID).orElseThrow(
                () -> new ResourceNotFoundException("Resource not found with the given resource ID : "+resourceID)
        );
        resourceInterface.deleteById(resourceID);
    }
}
