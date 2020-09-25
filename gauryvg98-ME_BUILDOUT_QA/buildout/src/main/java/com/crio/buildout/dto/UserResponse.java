package com.crio.buildout.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {
  @JsonProperty("questionId")
  private String questionId;
  
  @JsonProperty("userResponse")
  private List<String> userResponse;

  public UserResponse(String questionId, List<String> userResponse) {
    this.questionId = questionId;
    this.userResponse = userResponse;
  }
} 