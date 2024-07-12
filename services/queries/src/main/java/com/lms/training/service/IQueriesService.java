package com.lms.training.service;

import com.lms.training.dto.QueriesDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IQueriesService {
    void createAccount(QueriesDto queriesDto);

    boolean updateQuery(String responseText, Long queryId);
    List<QueriesDto> fetchAllQueries();
    List<QueriesDto> fetchByMentorId(int mentorId);
    List<QueriesDto> fetchByStatus(Boolean status);
    List<QueriesDto> fetchByNewJoinerId(int newJoinerId);
}
