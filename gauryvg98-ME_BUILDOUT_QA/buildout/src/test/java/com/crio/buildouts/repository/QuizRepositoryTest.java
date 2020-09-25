package com.crio.buildouts.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import com.crio.buildout.QuizApplication;
import com.crio.buildout.dto.Question;
import com.crio.buildout.dto.QuestionAnswer;
import com.crio.buildout.repository.ActualQuestionEntity;
import com.crio.buildout.repository.QuestionEntity;
import com.crio.buildout.repository.QuizRepository;
import com.crio.buildout.repository.QuizRepositoryService;
import com.crio.buildouts.utils.GetFileForTest;
import com.google.common.base.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {QuizApplication.class})
@DirtiesContext
@ActiveProfiles("test")
public class QuizRepositoryTest {
    
    @MockBean
    private QuizRepository quizRepository;

    @Autowired
    private QuizRepositoryService quizRepositoryService;

    List<QuestionEntity> quizEntityList;
    @BeforeEach
    void setup() {
        quizEntityList = GetFileForTest.loadQuestionEntityforMock().getQuestions();
        when(quizRepository.findByModuleId(any())).thenReturn(Optional.of(new ActualQuestionEntity(quizEntityList)));
    }

    //::::::Repo Test for Get method :::::::::
    @Test
    void repositoryServiceGetMethodIsWorkingProperly() {
        assertNotNull(quizRepositoryService);
        assertNotNull(quizEntityList);
        List<Question> questionList = quizRepositoryService.getQuestionListFromDb("1");
        assertEquals(3, questionList.size());
        assertEquals("001", questionList.get(0).getQuestionId());
        assertEquals("002", questionList.get(1).getQuestionId());
        assertEquals("003", questionList.get(2).getQuestionId());
        
    }
    // ::::::: Repo Test for Post method :::::::
    @Test
    void repositoryServicePostMethodIsWorkingProperly() {
        assertNotNull(quizRepositoryService);
        assertNotNull(quizEntityList);
        
        List<QuestionAnswer> questionAnswerList = quizRepositoryService.getQuestionAnswerListFromDb("1");
        assertEquals(3, questionAnswerList.size());
        assertEquals("001", questionAnswerList.get(0).getQuestionId());
        assertEquals("002", questionAnswerList.get(1).getQuestionId());
        assertEquals("003", questionAnswerList.get(2).getQuestionId());
        assertEquals(Arrays.asList("4"), questionAnswerList.get(0).getCorrect());
        assertEquals(Arrays.asList("1","3","4"), questionAnswerList.get(1).getCorrect());
        assertEquals(Arrays.asList("throwable"), questionAnswerList.get(2).getCorrect());
        
    }

}