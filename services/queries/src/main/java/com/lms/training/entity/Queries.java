package com.lms.training.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "queries")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Queries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int queryId;
    @NotNull(message = "Query Text can not be empty")
    private String queryText;
    @NotNull(message = "New Joiner ID can not be empty")
    private int newJoinerId;
    @NotNull(message = "Mentor ID can not be empty")
    private int mentorId;
    @NotNull(message = "Course ID can not be empty")
    private int courseId;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(insertable = false)
    private String updatedBy;

    private boolean status;
}
