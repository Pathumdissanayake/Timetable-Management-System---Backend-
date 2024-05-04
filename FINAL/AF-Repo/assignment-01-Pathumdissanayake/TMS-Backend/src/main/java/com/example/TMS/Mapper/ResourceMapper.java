package com.example.TMS.Mapper;

import com.example.TMS.Dto.Resource_Dto;
import com.example.TMS.Entity.Resources;

public class ResourceMapper {

    public static Resource_Dto mapToResource_Dto(Resources resources) {
        return new Resource_Dto(
                resources.getResourceId(),
                resources.getResourceName(),
                resources.getResourceType(),
                resources.getFaculty(),
                resources.isAvailability()
        );
    }

    public static Resources mapToResources(Resource_Dto resourceDto) {
        return new Resources(
                resourceDto.getResourceId(),
                resourceDto.getFaculty(),
                resourceDto.getResourceName(),
                resourceDto.getResourceType(),
                resourceDto.isAvailability()
        );
    }
}
