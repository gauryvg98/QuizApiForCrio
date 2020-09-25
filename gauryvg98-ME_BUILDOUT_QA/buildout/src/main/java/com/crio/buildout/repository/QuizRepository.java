package com.crio.buildout.repository;

import com.google.common.base.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface QuizRepository extends MongoRepository<ActualQuestionEntity, String> {
    @Query
    public Optional<ActualQuestionEntity> findByModuleId(String moduleId);
}