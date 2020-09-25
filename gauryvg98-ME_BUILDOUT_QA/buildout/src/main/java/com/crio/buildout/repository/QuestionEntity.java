package com.crio.buildout.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
public class QuestionEntity implements Serializable {
  
  private static final long serialVersionUID = 1L;

  @Field("_id")
  private String id;

  @NotNull @Field("questionId")
  private String questionId;
  
  @NotNull @Field("title")
  private String title;
  
  @NotNull @Field("description")
  private String description;
  
  @NotNull @Field("type")
  private String type;
  
  @NotNull @Field("options")
  private Map<String,String> options;

  @NotNull @Field("correctAnswer")
  private List<String> correct;
  
  private Boolean answerCorrect;
}