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
//    private int queryId;
    private int newJoinerId;
    private int mentorId;
    private int courseId;
    private String queryText;
    private boolean status;

}
