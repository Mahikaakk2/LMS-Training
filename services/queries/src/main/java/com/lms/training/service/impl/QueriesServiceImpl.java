package com.lms.training.service.impl;

import com.lms.training.dto.QueriesDto;
import com.lms.training.dto.QueriesMsgDto;
import com.lms.training.entity.Queries;
import com.lms.training.exception.QueryAlreadyResolvedException;
import com.lms.training.exception.ResourceNotFoundException;
import com.lms.training.mapper.QueriesMapper;
import com.lms.training.repository.QueriesRepository;
import com.lms.training.service.IQueriesService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang.ArrayUtils.isEquals;

@Service
@AllArgsConstructor

public class QueriesServiceImpl implements IQueriesService {




    private static final Logger logger = LoggerFactory.getLogger(QueriesServiceImpl.class);

    private final QueriesRepository queriesRepository;
    private final StreamBridge streamBridge;

    @Override
    public void createQuery(QueriesDto queriesDto){

        Queries queries = QueriesMapper.mapToQueries(queriesDto, new Queries());
//        customer.setCreatedAt(LocalDateTime.now());
//        customer.setCreatedBy("Anonymous");
       Queries savedQueries= queriesRepository.save(queries);
         createQueries(savedQueries);
    }
    private void createQueries(Queries savedQueries) {
        QueriesMsgDto queriesMsgDto = new QueriesMsgDto(savedQueries.getMentorId());
        boolean isSend = streamBridge.send("sendCommunication-out-0", queriesMsgDto);
        logger.info("Is Communication send? - {}", isSend);
    }


    @Override
    public boolean updateQuery(String responseText, Long queryId){
        Optional<Queries> foundQuery = queriesRepository.findById(queryId);
        if (foundQuery.isPresent()) {
            Queries query = foundQuery.get();
            if (!query.isStatus()) {
                query.setResponseText(responseText);
                query.setStatus(true);
                queriesRepository.save(query);
                return true;
            } else {
                throw new QueryAlreadyResolvedException("Query already resolved for the QueryId " + queryId);
            }
        } else {
            throw new ResourceNotFoundException("Query", "queryId", queryId.toString());
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
    public List<QueriesDto> fetchByMentorId(Long mentorId){

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

    @Override
    public List<QueriesDto> fetchByMentorIdStatus(Long mentorId,Boolean status) {
        List<Queries> allQueries= queriesRepository.findAll();
        List<QueriesDto> allQueriesDto= new ArrayList<>();
        for(Queries queries:allQueries) {
            if(queries.getMentorId()==mentorId && queries.isStatus() == status) {
                QueriesDto queryDto = QueriesMapper.mapToQueriesDto(queries, new QueriesDto());
                allQueriesDto.add(queryDto);
            }
        }
        return allQueriesDto;
    }

    @Override
    public List<QueriesDto> fetchByNewJoinerId(Long newJoinerId) {
        List<Queries> allQueries= queriesRepository.findAll();
        List<QueriesDto> allQueriesDto= new ArrayList<>();
        for(Queries queries:allQueries) {
            if(queries.getNewJoinerId()==newJoinerId) {
                QueriesDto queryDto = QueriesMapper.mapToQueriesDto(queries, new QueriesDto());
                allQueriesDto.add(queryDto);
            }
        }
        return allQueriesDto;

    }

    @Override
    public List<QueriesDto> fetchByStatus(Boolean status) {
        List<Queries> allQueries= queriesRepository.findAll();
        List<QueriesDto> allQueriesDto= new ArrayList<>();
        for(Queries queries:allQueries) {
            if(queries.isStatus()== status) {
                QueriesDto queryDto = QueriesMapper.mapToQueriesDto(queries, new QueriesDto());
                allQueriesDto.add(queryDto);
            }
        }
        return allQueriesDto;
    }

}
