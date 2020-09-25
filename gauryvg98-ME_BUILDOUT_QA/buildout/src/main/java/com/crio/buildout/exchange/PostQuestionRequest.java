package com.crio.buildout.exchange;

import com.crio.buildout.dto.UserResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostQuestionRequest {
  
  @JsonProperty("moduleId")
  private String moduleId;

  @JsonProperty("responses")
  private List<UserResponse> responses;
  
  public PostQuestionRequest(List<UserResponse> responses) {
    this.responses = responses;
  }
}