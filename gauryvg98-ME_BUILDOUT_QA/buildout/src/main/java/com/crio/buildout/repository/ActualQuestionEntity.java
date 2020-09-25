package com.crio.buildout.repository;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "modulecollection")
@NoArgsConstructor
public class ActualQuestionEntity {
    @Field("_id")
    String id;

    @NotNull @Field("moduleId")
    Integer moduleId;

    @NotNull @Field("questions")
    List<QuestionEntity> questions;

    public ActualQuestionEntity(List<QuestionEntity> questions) {
        this.questions = questions;
    }

}