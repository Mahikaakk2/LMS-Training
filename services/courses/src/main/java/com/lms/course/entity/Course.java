package com.lms.course.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)

public class Course {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int courseId;

    @NotNull(message = "Course title can not be empty")
    private String courseTitle;

    @NotNull(message = "Course Description can not be empty")
    private String courseDescription;

    @NotNull(message = "Course Duration can not be empty")
    private Time courseDuration;

    @NotNull(message = "Course Resource can not be empty")
    private String courseResource;

    @NotNull(message = "Course Category can not be empty")
    private String courseCategory;

    @CreatedDate
    @Column(updatable = false)
    @NotNull(message = "Created at can not be empty")
    private LocalDateTime createdAt;

    @CreatedDate
    @Column(updatable = false)
    @NotNull(message = "Created by can not be empty")
    private int createdBy;

    @LastModifiedDate
    @Column(insertable = false)
    @NotNull(message = "Updated at can not be empty")
    private LocalDateTime updatedAt;

    @LastModifiedDate
    @Column(insertable = false)
    @NotNull(message = "Updated by can not be empty")
    private Integer updatedBy; // Use Integer for nullable int

    private boolean isApproved;
}
