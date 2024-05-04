package com.example.TMS.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "Timetables")
public class Timetable {
    @Id
    private String tableId;

    @Field("faculty")
    private String faculty;

    @Field("lecturer")
    private String lecturer;

    @Field("date")
    private String date;

    @Field("timeStart")
    private LocalTime timeStart;

    @Field("timeEnd")
    private LocalTime timeEnd;

    @Field("courseId")
    private String courseId;

    @Field("roomId")
    private String roomId;

    @Field("resourceId")
    private String resourceId;
}
