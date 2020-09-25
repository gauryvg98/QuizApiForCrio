package com.crio.buildout.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Question {
  
  private String questionId;
  private String title;
  private String type;
  private Map<String,String> options;
  
  @JsonIgnore
  private String description;
  
  @JsonIgnore
  private List<String> userAnswer;
  
  @JsonIgnore
  private String explanation;
  
  @JsonIgnore
  private List<String> correctAnswer;

  @JsonIgnore
  private Boolean answerCorrect;
}