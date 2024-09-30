# US 2001

Este documento contém a documentação relativa à US 2001.

## 1. Contexto

Esta *User Story (US)* foi introduzida neste *sprint* para ser especificada uma gramática que possa validar vários tipos
de exames.
Esta *US* faz parte da disciplina de **LPROG**.

## 2. Requisitos

**US 2001** - As Teacher, I want to create/update an exam

### 2.1 Critérios de aceitação

**CA 1:** This includes the specification of the exam (i.e., its structure, in accordance with a grammar for exams that
is used to validate the specification of the exam).
The support for exams (its design, feedback and grading) must follow specific technical requirements, specified in LPROG.
The ANTLR tool should be used (https://www.antlr.org/).

## 3. Grámatica

```
grammar Exam;

prog: title header section+;

title: TITLE words NEWLINE;

header: HEADER NEWLINE
        TYPE_OF_FEEDBACK type NEWLINE
        TYPE_OF_GRADE type NEWLINE
        DESCRIPTION words? NEWLINE
        ;

section: SECTION NEWLINE
         DESCRIPTION words NEWLINE
         question+
         ;

question: matching | multipleChoice | shortAnswer | numerical | selectMissingWords | trueFalse;

matching: MATCHING NEWLINE
          pair+
          CORRECT_ANSWER MATCHING_OPTION_CORRECTION+ NEWLINE
          feedback?
          ;

pair: NUMERICAL_OPTION words DIVISIVE ALPHABETICAL_OPTION words NEWLINE
      SCORE FLOAT NEWLINE
      ;


multipleChoice: MULTIPLE_CHOICE NEWLINE
                words NEWLINE
                option+
                SCORE FLOAT NEWLINE
                CORRECT_ANSWER CHAR NEWLINE
                feedback?
                ;

shortAnswer: SHORT_ANSWER NEWLINE
             words NEWLINE
             SCORE FLOAT NEWLINE
             CORRECT_ANSWER words NEWLINE
             feedback?
             ;

numerical: NUMERICAL NEWLINE
           words NEWLINE
           SCORE FLOAT NEWLINE
           CORRECT_ANSWER nums NEWLINE
           ERROR_NUMERICAL nums NEWLINE
           feedback?
           ;

selectMissingWords: SELECT_MISSING_WORDS NEWLINE
                    words? CHOSING_OPTION_SELECT_MISSING_WORDS words? NEWLINE
                    POSSIBLE_CHOICES_SELECT_MISSING_WORDS words+ NEWLINE
                    SCORE FLOAT NEWLINE
                    CORRECT_ANSWER words NEWLINE
                    feedback?
                    ;

trueFalse: TRUE_FALSE NEWLINE
           words NEWLINE
           SCORE FLOAT NEWLINE
           CORRECT_ANSWER (TRUE | FALSE) NEWLINE
           feedback?
           ;

option: ALPHABETICAL_OPTION words NEWLINE;

feedback: FEEDBACK words? NEWLINE?;

words : (CHAR | nums | FLOAT)+;

nums : NUM+;

type : NONE | ON_SUBMISSION | AFTER_CLOSING;

TITLE : 'Title' IDENTIFIER;
HEADER : 'Header' IDENTIFIER;
TYPE_OF_FEEDBACK : 'Type of feedback' IDENTIFIER;
TYPE_OF_GRADE : 'Type of grade' IDENTIFIER;
DESCRIPTION : 'Description' IDENTIFIER;
SECTION : 'Section' IDENTIFIER;
MATCHING : 'Matching' IDENTIFIER;
MULTIPLE_CHOICE : 'Multiple Choice' IDENTIFIER;
SHORT_ANSWER : 'Short Answer' IDENTIFIER;
NUMERICAL : 'Numerical' IDENTIFIER;
SELECT_MISSING_WORDS : 'Select Missing Words' IDENTIFIER;
TRUE_FALSE : 'True/False' IDENTIFIER;
SCORE : 'Score' IDENTIFIER;
CORRECT_ANSWER : 'Correct answer' IDENTIFIER;
FEEDBACK : 'Feedback' IDENTIFIER;
ERROR_NUMERICAL : 'Erro' IDENTIFIER;
POSSIBLE_CHOICES_SELECT_MISSING_WORDS : 'Possible choices' IDENTIFIER;
CHOSING_OPTION_SELECT_MISSING_WORDS : '<option>';
TRUE : 'true';
FALSE : 'false';
NONE : 'none';
ON_SUBMISSION : 'on-submission';
AFTER_CLOSING : 'after-closing';
IDENTIFIER : ':' ' '?;
NUM : [0-9];
NEWLINE : [\r\n]+;
FLOAT : [0-9]+ ',' [0-9]+;
NUMERICAL_OPTION : [1-9][0-9]? ')';
ALPHABETICAL_OPTION : [a-z] ')';
MATCHING_OPTION_CORRECTION : ' '? NUM '-' CHAR ','?;
DIVISIVE : ' ' '-' ' ';
CHAR : [a-zA-ZàèìòùáéíóúÀÈÌÒÙÁÉÍÓÚãõÃÕâÂêÊîÎôÔûÛçÇäÄëËïÏöÖüÜº_ ,.?!"%'-];
WS : [ \t\r\n]+ -> skip;
```
