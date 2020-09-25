package com.crio.buildouts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import com.crio.buildout.QuizApplication;
import com.crio.buildout.dto.Question;
import com.crio.buildout.dto.QuestionAnswer;
import com.crio.buildout.exchange.PostQuestionResponse;
import com.crio.buildout.exchange.GetQuestionRequest;
import com.crio.buildout.exchange.GetQuestionResponse;
import com.crio.buildout.exchange.PostQuestionRequest;
import com.crio.buildout.repository.QuizRepositoryService;
import com.crio.buildout.services.QuizService;
import com.crio.buildout.services.QuizServiceImpl;
import com.crio.buildouts.utils.GetFileForTest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest(classes = {QuizApplication.class})
@DirtiesContext
@ActiveProfiles("test")
public class QuizServiceTest {
    
    @MockBean
    private QuizRepositoryService  quizRepositoryService;
    
    @InjectMocks
    private QuizServiceImpl quizServiceImpl;
    
    @Autowired
    private QuizService quizService;
    
    private PostQuestionRequest postQuestionRequest;
    private List<Question> getQuestionsList;
    private List<QuestionAnswer> postQuestionList;

    @Test
    void getInServiceIsWorkingTest() {
        //::::::redundant test as of this moment:::::::::
        //::::::CAN BE MODIFIED IF IN FUTURE WE THINK OF INCORPORATING MODULEIDS 
        //              FOR MULTIPLE QUIZZES, AND SOME Logic Changes Reqd in GetMethod              
        
        getQuestionsList = GetFileForTest.loadQuestion().getQuestions();
        when(quizRepositoryService.getQuestionListFromDb(any())).thenReturn(getQuestionsList);
        GetQuestionResponse returnedFromService = quizService.getQuestions(new GetQuestionRequest(""));

        assertEquals(new GetQuestionResponse(getQuestionsList), returnedFromService);
    }

    @Test
    void postInServiceIsWorkingTest() {

        //::::::Valid Request should return correct response:::::::
        
        postQuestionRequest = GetFileForTest.loadUserResponses();
        postQuestionList = GetFileForTest.loadQuestionAnswer().getQuestions();

        when(quizRepositoryService.getQuestionAnswerListFromDb(any())).thenReturn(postQuestionList);
        PostQuestionResponse postQuestionResponse = quizService.postResponse(
            postQuestionRequest);

        assertEquals(3, postQuestionResponse.getQuestions().size());
        assertNotNull(postQuestionResponse.getSummary());
        assertNotNull(postQuestionResponse.getQuestions().get(0).getUserAnswer());
        assertNotNull(postQuestionResponse.getQuestions().get(0).getAnswerCorrect());
        assertEquals(Integer.valueOf(2), postQuestionResponse.getSummary().get("score"));
        assertEquals(Integer.valueOf(3), postQuestionResponse.getSummary().get("total"));
        
        assertEquals(postQuestionRequest.getResponses().get(0).getUserResponse()
                ,postQuestionResponse.getQuestions().get(0).getUserAnswer());

        assertEquals(postQuestionRequest.getResponses().get(1).getUserResponse()
                ,postQuestionResponse.getQuestions().get(1).getUserAnswer());
    
        assertEquals(postQuestionRequest.getResponses().get(2).getUserResponse()
                ,postQuestionResponse.getQuestions().get(2).getUserAnswer());
    }

    @Test 
    void postInServiceFailsDueToWrongResponseSize() {
        //::::::Invalid Request(different size than questionList) should return empty response::::::

        postQuestionRequest = GetFileForTest.loadUserResponses();
        postQuestionList = GetFileForTest.loadQuestionAnswer().getQuestions();
        
        //::::::Removed a response to check fail::::::::
        
        postQuestionRequest.getResponses().remove(0);
        when(quizRepositoryService.getQuestionAnswerListFromDb(any())).thenReturn(postQuestionList);
        PostQuestionResponse postQuestionResponse = quizService.postResponse(
            postQuestionRequest);

        assertEquals(0, postQuestionResponse.getQuestions().size());
        assertNull(postQuestionResponse.getSummary());
    }

    @Test
    void postInServiceFailsDueToWrongQuestionId() {
        //::::::Invalid Request(Wrong QuestionId) should return empty Reponse:::::::
        
        postQuestionRequest = GetFileForTest.loadUserResponses();
        postQuestionList = GetFileForTest.loadQuestionAnswer().getQuestions();

        //::::::Changed the questionId to check fail::::::::
        
        postQuestionRequest.getResponses().get(0).setQuestionId("NotInList");
        when(quizRepositoryService.getQuestionAnswerListFromDb(any())).thenReturn(postQuestionList);
        PostQuestionResponse postQuestionResponse = quizService.postResponse(
            postQuestionRequest);

        assertEquals(0, postQuestionResponse.getQuestions().size());
        assertNull(postQuestionResponse.getSummary());


    }
}