package com.lms.training.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;


@Entity
@Table(name="Employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull(message = "First name can not be empty")
    @Size(min = 4, max = 30, message = "Customer name should be minimum 4 characters and maximum 30 characters long")
    private String firstName;

    @NotNull(message = "Last name can not be empty")
    @Size(min = 4, max = 30, message = "Customer name should be minimum 4 characters and maximum 30 characters long")
    private String lastName;

    @NotNull(message = "Email can not be empty")
    @Email(message = "Email should be in proper format")
    private String email;

    @NotNull(message = "Mobile Number can not be empty")
    @Pattern(regexp = "^$|[0-9]{10}", message = "Mobile number should have ten digits")
    private String mobileNumber;

    @NotNull(message = "Password can not be empty")
    @NotBlank(message = "New password is mandatory")
    private String password;

    @NotNull(message = "Role can not be empty")
    private String role;

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

}
