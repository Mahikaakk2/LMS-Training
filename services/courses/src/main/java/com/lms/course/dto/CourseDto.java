package com.lms.course.dto;

import java.sql.Time;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CourseDto {

    private int courseId;
    private String courseTitle;
    private String courseDescription;
    private Time courseDuration;
    private String courseResource;
    private String courseCategory;
    private boolean isApproved;
}
