package com.crio.buildout.services;

import com.crio.buildout.exchange.GetQuestionRequest;
import com.crio.buildout.exchange.GetQuestionResponse;
import com.crio.buildout.exchange.PostQuestionRequest;
import com.crio.buildout.exchange.PostQuestionResponse;

public interface QuizService {
  public GetQuestionResponse getQuestions(
        GetQuestionRequest getRequest);

  public PostQuestionResponse postResponse(
        PostQuestionRequest postRequest);
}