package com.lms.course.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name="courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int courseId;
    private String courseTitle;
    private String courseDescription;
    private Time courseDuration;
    private String courseResource;
    private String courseCategory;
    private Date createdAt;
    private int createdBy;
    private Date updatedAt;
    private Integer updatedBy; // Use Integer for nullable int
    private boolean isApproved;
}
