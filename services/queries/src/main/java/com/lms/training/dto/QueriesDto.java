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
    private Long queryId;
    private int newJoinerId;
    private int mentorId;
    private int courseId;
    private String queryText;
    private String ResponseText;
    private boolean status;


}
