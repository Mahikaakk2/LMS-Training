package com.lms.training.controller;

import com.lms.training.dto.QueriesDto;
import com.lms.training.dto.ResponseDto;
import com.lms.training.service.IQueriesService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.StyledEditorKit;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class QueryController {

    private final IQueriesService iQueriesService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody QueriesDto queriesDto){
        iQueriesService.createQuery(queriesDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Created Successfully"));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateQueries(@RequestBody String responseText, @RequestParam Long queryId){
        boolean isUpdated = iQueriesService.updateQuery(responseText, queryId);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("204","Updated Sucessfully"));
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("500","Update Unsucessfully"));
        }
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<QueriesDto>> fetchAllQueries(){
        List<QueriesDto> allQueries = iQueriesService.fetchAllQueries();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allQueries);
    }

    @GetMapping("/fetchByMentorId")
    public ResponseEntity<List<QueriesDto>> fetchByMentorId(@RequestParam Long mentorId){
        List<QueriesDto> allQueriesDto = iQueriesService.fetchByMentorId(mentorId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allQueriesDto);
    }

    @GetMapping("/fetchByNewJoinerId")
    public ResponseEntity<List<QueriesDto>> fetchByNewJoinerId(@RequestParam Long newJoinerId){
        List<QueriesDto> allQueriesDto = iQueriesService.fetchByNewJoinerId(newJoinerId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allQueriesDto);
    }

    @GetMapping("/fetchByStatus")
    public ResponseEntity<List<QueriesDto>> fetchByStatus(@RequestParam Boolean status){
        List<QueriesDto> allQueriesDto = iQueriesService.fetchByStatus(status);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allQueriesDto);
    }

    @GetMapping("/fetchByMentorIdStatus")
    public ResponseEntity<List<QueriesDto>> fetchByMentorIdStatus(@RequestParam Long mentorId, @RequestParam Boolean status){
        List<QueriesDto> allQueriesDto = iQueriesService.fetchByMentorIdStatus(mentorId, status);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allQueriesDto);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello World");
    }
}
