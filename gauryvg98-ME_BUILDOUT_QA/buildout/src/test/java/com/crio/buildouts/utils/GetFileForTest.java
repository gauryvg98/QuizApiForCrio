package com.crio.buildouts.utils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.crio.buildout.exchange.GetQuestionResponse;
import com.crio.buildout.exchange.PostQuestionRequest;
import com.crio.buildout.exchange.PostQuestionResponse;
import com.crio.buildouts.repository.QuestionEntityListFromDb;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;

public class GetFileForTest {

    // basic layout of this helper util :
    // -> objectmapper init
    // -> URL for the corresponding dummy file to be loaded for the data for the tests
    // -> get File as string 
    // -> map string to DTO/POJO/exchange object using object mapper
    // -> return pojo/dto/exchange object
    // PS :::: some objects have been created only for the purpose of the data loading so that 
    //         jackson mapping errors dont occur. :::::: (SEE QuestionEntityListFromDb object in test/repository) 

    public static GetQuestionResponse loadQuestion() {
        ObjectMapper objectMapper = new ObjectMapper();
        final URL resource = Resources.getResource("fixtures/sample_get_questions_response.json");

        String fixture = "";
        try {
            fixture = Resources.toString(resource, StandardCharsets.UTF_8).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            return objectMapper.readValue(fixture, new TypeReference<GetQuestionResponse>() {
            });
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
      }
    
      public static PostQuestionResponse loadQuestionAnswer() {
        ObjectMapper objectMapper = new ObjectMapper();
        final URL resource = Resources.getResource("fixtures/sample_submit_question_response.json");
        
        String fixture="";
		try {
			fixture = Resources.toString(resource, StandardCharsets.UTF_8).trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        try {
            return objectMapper.readValue(fixture, PostQuestionResponse.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
      }
    
      public static PostQuestionRequest loadUserResponses() {
        ObjectMapper objectMapper = new ObjectMapper();
        final URL resource = Resources.getResource("fixtures/sample_submit_question_request.json");
        
        String fixture="";
        try {
            fixture = Resources.toString(resource, StandardCharsets.UTF_8).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            return objectMapper.readValue(fixture,PostQuestionRequest.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
      }
    
      public static QuestionEntityListFromDb loadQuestionEntityforMock() {
        ObjectMapper objectMapper = new ObjectMapper();
        final URL resource = Resources.getResource("fixtures/sample_load_question_entity.json");
        
        String fixture="";
        try {
            fixture = Resources.toString(resource, StandardCharsets.UTF_8).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(fixture);
        QuestionEntityListFromDb quest = new QuestionEntityListFromDb();
        try {
            quest = objectMapper.readValue(fixture, QuestionEntityListFromDb.class); 
            System.out.println(quest.toString());
            return quest;
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
      }
    
}