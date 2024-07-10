package com.lms.training.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "enrollments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Enrollments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int EnrollmentID;

    @NotNull(message="Account number cannot be empty")
    private int MentorID;

    @NotNull(message="Account number cannot be empty")
    private int CourseID;

    @NotNull(message="Account number cannot be empty")
    private int NewJoinID;

    @CreatedBy
    @Column(updatable=false)
    private String createdBy;

    @CreatedDate
    @Column(updatable=false)
    private LocalDateTime createdAt;
}
