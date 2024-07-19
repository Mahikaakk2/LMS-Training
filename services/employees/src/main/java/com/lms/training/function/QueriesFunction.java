package com.lms.training.function;

import com.lms.training.dto.QueriesMsgDto;
import com.lms.training.service.IUserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Function;


@AllArgsConstructor
@Configuration
public class QueriesFunction {

    private final IUserService iUserService;

    private static final Logger logger = LoggerFactory.getLogger(QueriesFunction.class);

    private final StreamBridge streamBridge;

    @Bean
    public Function<QueriesMsgDto, String> email(){
        return queriesMsgDto -> {
            if (iUserService == null) {
                throw new IllegalStateException("iUserService is null!");
            }
            String userEmail=iUserService.fetchEmailByID(queriesMsgDto.mentorID());

            logger.info("Sending Email with details - {}", userEmail);
//            streamBridge.send("email-out-0", userEmail);
            return userEmail;
        };
    }



}

