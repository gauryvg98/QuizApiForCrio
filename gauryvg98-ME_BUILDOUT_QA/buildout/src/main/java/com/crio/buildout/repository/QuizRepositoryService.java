package com.crio.buildout.repository;

import com.crio.buildout.dto.Question;
import com.crio.buildout.dto.QuestionAnswer;

import java.util.List;

public interface QuizRepositoryService {
  public List<Question> getQuestionListFromDb(String moduleId);

  public List<QuestionAnswer> getQuestionAnswerListFromDb(String moduleId);
}