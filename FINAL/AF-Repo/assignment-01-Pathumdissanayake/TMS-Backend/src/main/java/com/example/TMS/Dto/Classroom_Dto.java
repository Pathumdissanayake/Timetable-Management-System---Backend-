package com.example.TMS.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Classroom_Dto {

    private String roomId;
    private String building;
    private String floor;
    private boolean availability;
}
