package com.crio.buildout.services;

import com.crio.buildout.dto.Question;
import com.crio.buildout.dto.QuestionAnswer;
import com.crio.buildout.dto.UserResponse;
import com.crio.buildout.exchange.GetQuestionRequest;
import com.crio.buildout.exchange.GetQuestionResponse;
import com.crio.buildout.exchange.PostQuestionRequest;
import com.crio.buildout.exchange.PostQuestionResponse;
import com.crio.buildout.repository.QuizRepositoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizServiceImpl implements QuizService {

  @Autowired
  QuizRepositoryService quizRepositoryService;
  
  @Override
  public GetQuestionResponse getQuestions(GetQuestionRequest getRequest) {
    System.out.println("GET Service called here! Request : " + getRequest.getModuleId());
    List<Question> questionList = quizRepositoryService.getQuestionListFromDb(getRequest.getModuleId());
    return (questionList.isEmpty()) ? new GetQuestionResponse() : new GetQuestionResponse(questionList);
  }

  @Override
  public PostQuestionResponse postResponse(PostQuestionRequest postRequest) {
    System.out.println("POST Service called here!");
    // System.out.println(postRequest.toString());
    List<QuestionAnswer> listOfQuestions = quizRepositoryService.getQuestionAnswerListFromDb(
          postRequest.getModuleId());
    if(listOfQuestions.isEmpty()) {
      return new PostQuestionResponse();
    }
    Integer countOfCorrect = 0;
    
    Map<String,List<String>> userResponseMap = new HashMap<String,List<String>>();
    List<UserResponse> listResponse = postRequest.getResponses();
    
    //mapping the userReponses to their corresponding questionIds

    listResponse.forEach(resp -> {
      userResponseMap.put(resp.getQuestionId(),resp.getUserResponse());
    });
    
    //checking whether userResponse is correct and alloting scores and boolean

    for (QuestionAnswer question : listOfQuestions) {
      
      //if client mysteriously sends answers for a questionId which doesnt exist in 
      //the question list, return null and throw bad request in controller

      if (!userResponseMap.containsKey(question.getQuestionId())) {
        return new PostQuestionResponse();
      }
      if (userResponseMap.get(question.getQuestionId()).equals(question.getCorrect())) {
        countOfCorrect++;
        question.setAnswerCorrect(true);
      } else {
        question.setAnswerCorrect(false);
      }
      question.setUserAnswer(userResponseMap.get(question.getQuestionId()));
    }
    
    Integer totalCount = postRequest.getResponses().size();
    Map<String,Integer> mp = new HashMap<String,Integer>();
    mp.put("score", countOfCorrect);
    mp.put("total", totalCount);
          
    return new PostQuestionResponse(listOfQuestions,mp);
  }
        
}