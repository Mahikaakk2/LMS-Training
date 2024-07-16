package com.lms.training.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CourseDto {

    private int courseId;
    private String courseTitle;
    private String courseDescription;
    private Time courseDuration;
    private String courseResource;
    private String courseCategory;
    private boolean isApproved;
    private int createdBy;
}
