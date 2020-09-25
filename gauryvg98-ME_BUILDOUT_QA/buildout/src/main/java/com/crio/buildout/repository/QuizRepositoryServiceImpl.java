package com.crio.buildout.repository;

import com.crio.buildout.dto.Question;
import com.crio.buildout.dto.QuestionAnswer;
import com.google.common.base.Optional;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizRepositoryServiceImpl implements QuizRepositoryService {
  @Autowired
  private QuizRepository quizRepository;
  
  @Autowired
  private ModelMapper modelMapper;
  
  @Override
  public List<Question> getQuestionListFromDb(String moduleId) {
    System.out.println("Repo called here! Request : " + moduleId);
    Optional<ActualQuestionEntity> actualQuestionEntity = quizRepository.findByModuleId(moduleId);
    
    if(!actualQuestionEntity.isPresent()) {
      return new ArrayList<Question>();
    }

    List<QuestionEntity> questionEntity = actualQuestionEntity.get().getQuestions();
    List<Question> questionList = new ArrayList<Question>();

    questionEntity.forEach(entity -> {
      questionList.add(modelMapper.map(entity,Question.class));
    });
    
    return questionList;
  }

  @Override
  public List<QuestionAnswer> getQuestionAnswerListFromDb(String moduleId) {
    System.out.println("Repo called here!");
    Optional<ActualQuestionEntity> actualQuestionEntity = quizRepository.findByModuleId(moduleId);

    if(!actualQuestionEntity.isPresent()) {
      return new ArrayList<QuestionAnswer>();
    }

    List<QuestionEntity> questionEntity = actualQuestionEntity.get().getQuestions();
    List<QuestionAnswer> questionAnswerList = new ArrayList<QuestionAnswer>();

    questionEntity.forEach(entity -> {
      questionAnswerList.add(modelMapper.map(entity,QuestionAnswer.class));
    });
    
    return questionAnswerList;
  }

}