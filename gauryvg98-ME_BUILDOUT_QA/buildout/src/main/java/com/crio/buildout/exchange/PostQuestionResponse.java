package com.crio.buildout.exchange;

import com.crio.buildout.dto.QuestionAnswer;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostQuestionResponse {
  @JsonProperty("questions")
  private List<QuestionAnswer> questions = new ArrayList<QuestionAnswer>();
  @JsonProperty("summary")
  private Map<String,Integer> summary;
}