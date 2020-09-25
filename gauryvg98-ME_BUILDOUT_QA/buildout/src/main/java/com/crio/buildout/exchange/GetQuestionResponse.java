package com.crio.buildout.exchange;

import com.crio.buildout.dto.Question;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetQuestionResponse {
  @NotNull 
  @JsonProperty("questions")
  private List<Question> questions = new ArrayList<Question>();
}