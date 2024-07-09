package com.lms.training.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QueriesDto {
    private String queryText;
    private int newJoinerId;
    private int mentorId;
    private int courseId;
    private boolean status;

}
