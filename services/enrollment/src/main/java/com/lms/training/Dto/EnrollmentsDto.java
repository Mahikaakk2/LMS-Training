package com.lms.training.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentsDto {

    //private int EnrollmentID;
    private int MentorID;
    private int CourseID;
    private int NewJoinID;

}
