package com.lms.training.service.impl;

import com.lms.training.dto.QueriesDto;
import com.lms.training.entity.Queries;
import com.lms.training.mapper.QueriesMapper;
import com.lms.training.repository.QueriesRepository;
import com.lms.training.service.IQueriesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QueriesServiceImpl implements IQueriesService {

    private final QueriesRepository queriesRepository;

    @Override
    public void createAccount(QueriesDto queriesDto){

        Queries queries = QueriesMapper.mapToQueries(queriesDto, new Queries());
//        customer.setCreatedAt(LocalDateTime.now());
//        customer.setCreatedBy("Anonymous");
        queriesRepository.save(queries);
    }

    @Override
    public boolean updateQuery(int queryId){
        Optional<Queries> optionalQuery = queriesRepository.findById(queryId);
        if (optionalQuery.isPresent()) {
            Queries query = optionalQuery.get();
            if (!query.isStatus()) {
                query.setStatus(true);
                queriesRepository.save(query);
                return true;
            } else {
                return false; //course already tue
            }
        } else {
            return false; //course not found
        }
    }

    @Override
    public List<QueriesDto> fetchAllQueries(){
        List<Queries> allQueries= queriesRepository.findAll();
        List<QueriesDto> allQueriesDto= new ArrayList<>();
        for(Queries queries:allQueries) {
            QueriesDto queryDto = QueriesMapper.mapToQueriesDto(queries,new QueriesDto());
            allQueriesDto.add(queryDto);
        }
        return allQueriesDto;
    }

    @Override
    public List<QueriesDto> fetchByMentorId(int mentorId){
        List<Queries> allQueries= queriesRepository.findAll();
        List<QueriesDto> allQueriesDto= new ArrayList<>();
        for(Queries queries:allQueries) {
            if(queries.getMentorId()==mentorId) {
                QueriesDto queryDto = QueriesMapper.mapToQueriesDto(queries, new QueriesDto());
                allQueriesDto.add(queryDto);
            }
        }
        return allQueriesDto;
    }

}
