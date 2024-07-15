package com.lms.training.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
@Table(name = "Employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull(message = "First name cannot be empty")
    @Size(min = 4, max = 30, message = "First name should be between 4 and 30 characters")
    private String firstName;

    @NotNull(message = "Last name cannot be empty")
    @Size(min = 4, max = 30, message = "Last name should be between 4 and 30 characters")
    private String lastName;

    @NotNull(message = "Email cannot be empty")
    @Email(message = "Email should be in proper format")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@ukg\\.com$", message = "Email should end with @ukg.com")
    private String email;

    @NotNull(message = "Mobile number cannot be empty")
    @Pattern(regexp = "^$|[0-9]{10}", message = "Mobile number should have ten digits")
    private String mobileNumber;

    @NotNull(message = "Password cannot be empty")
    private String password;

    @NotNull(message = "Role cannot be empty")
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


