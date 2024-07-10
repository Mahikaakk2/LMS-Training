package com.lms.training.service;

import com.lms.training.dto.QueriesDto;

import java.util.List;

public interface IQueriesService {
    void createAccount(QueriesDto queriesDto);

    boolean updateQuery(int queryId);
    List<QueriesDto> fetchAllQueries();
    List<QueriesDto> fetchByMentorId(int mentorId);
}
