package com.lms.training.mapper;

import com.lms.training.dto.QueriesDto;
import com.lms.training.entity.Queries;

public class QueriesMapper {

    public static QueriesDto mapToQueriesDto(Queries queries, QueriesDto queriesDto) {
        queriesDto.setQueryText(queries.getQueryText());
        queriesDto.setStatus(queries.isStatus());
        queriesDto.setCourseId(queries.getCourseId());
        queriesDto.setMentorId(queries.getMentorId());
        queriesDto.setNewJoinerId(queries.getNewJoinerId());
        return queriesDto;
    }

    public static Queries mapToQueries(QueriesDto queriesDto, Queries queries ){
        queries.setQueryText(queriesDto.getQueryText());
        queries.setStatus(queriesDto.isStatus());
        queries.setCourseId(queriesDto.getCourseId());
        queries.setMentorId(queriesDto.getMentorId());
        queries.setNewJoinerId(queriesDto.getNewJoinerId());
        return queries;
    }


}
