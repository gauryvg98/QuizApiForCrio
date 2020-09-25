# QuizApiForCrio
Basic Question Answer API which supports Quiz Fetch and Response Validation

>>>>>>> MOTIF BEHIND THE APPLICATION : APPLY 3 WEEKS OF LEARNING TO BUILD TWO APIs FROM SCRATCH, USING SPRING BOOT <<<<<<<<<

>> BASIC INFO:
DB currently has two quizes with moduleId = 1 and 2
Each moduleId has question with questionId, title, description, options and current answer

----->>>> Supports a GET Quiz Request based on the moduleId supplied in the URL (format : <link:port>/quiz?moduleId=n )
          Gets a series of questions with questionId, title, description and options (in form of DTO > Question.java)

----->>>> Supports a POST User Response Request which get a quiz supplied by the moduleId in the user reponse object (field : moduleId)     
          Gets quiz and adds user response properties to it, also calculates score and boolean for whether user answered correctly
          Check DTO > QuestionAnswer.java and UserResponse
          
>> Folders:
- exchange has data used for Request and Response of GET AND POST respectively.
- dto has dtos used
- mongodb entities in repository folder

>>>> FOLLOWS MVCS ARCHITECTURE <<<<

>> Tests:
- Total of 6 tests, 2 for repostiory and 4 for service layer

>>>>>>> TODOs :        - ADD CONTROLLER MOCKITO TESTS
               <<IMP>> - IMPLEMENT PUT OR ANOTHER POST METHOD FOR ADDING QUIZZES TO THE DB <<IMP>>
               <<IMP>> - MAKE A FRONTEND APP USING FLUTTER WHICH UTILIZES THESE API <<IMP>>
