package com.example.TMS.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "Classrooms")
public class Classroom {
    @Id
    private String roomId;

    @Field("building")
    private String building;

    @Field("floor")
    private String floor;

    @Field("availability")
    private boolean availability;
}
