package com.crio.buildout.controller;

import com.crio.buildout.exchange.GetQuestionRequest;
import com.crio.buildout.exchange.GetQuestionResponse;
import com.crio.buildout.exchange.PostQuestionRequest;
import com.crio.buildout.exchange.PostQuestionResponse;
import com.crio.buildout.services.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@RequestMapping("/quiz")
public class QuizController {

  @Autowired
  QuizService quizService;

  @GetMapping("/testerstr")
  public String welcome() {
    return "bro this works";
  }

  @GetMapping("")
  public ResponseEntity<GetQuestionResponse> getQuestionsForUser(GetQuestionRequest getRequest) {

    System.out.println("GET was called just now! Request : " + getRequest.getModuleId());
    GetQuestionResponse getQuestionResponse = quizService.getQuestions(getRequest);
    return (getQuestionResponse.equals(new GetQuestionResponse())) ? ResponseEntity.badRequest().body(null)
        : ResponseEntity.ok().body(getQuestionResponse);
  }

  @PostMapping("")
  @ResponseBody
  public ResponseEntity<PostQuestionResponse> postResponseFromUser(@RequestBody PostQuestionRequest postRequest) {
    System.out.println("POST was called just now! Request : " + postRequest.getModuleId());
    PostQuestionResponse postQuestionResponse = quizService.postResponse(postRequest);
    return (postQuestionResponse.equals(new PostQuestionResponse())) ? ResponseEntity.badRequest().body(null)
        : ResponseEntity.ok().body(postQuestionResponse);
  }

  // @PutMapping("")
  // public String putQuizFromUser()
}