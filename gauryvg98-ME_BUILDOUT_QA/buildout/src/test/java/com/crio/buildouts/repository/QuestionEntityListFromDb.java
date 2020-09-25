package com.crio.buildouts.repository;

import com.crio.buildout.repository.QuestionEntity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class QuestionEntityListFromDb {
    @JsonProperty("questions")
    List<QuestionEntity> questions = new ArrayList<QuestionEntity>();
}
