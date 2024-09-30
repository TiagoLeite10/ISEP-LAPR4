# US 2008

Este documento contém a documentação relativa à US 2008.

## 1. Contexto

Esta *User Story (US)* foi introduzida neste *sprint* para ser especificada uma gramática que possa validar vários tipos 
de exames.
Esta *US* faz parte da disciplina de **LPROG**.

## 2. Requisitos

**US 2008** - As Teacher, I want to create/update automatic formative exams

### 2.1 Critérios de aceitação

**CA 1:** The specification of formative exams is similar to regular exams, but the user specifies the type of questions 
to be inserted in the sections instead of the specific questions. When generating the automatic formative exam, the system
should randomly create the questions (without repetition on a given exam). Also, formative exams do not have open and close
dates. Feedback and grades are only provided at the end of the exam.
The support for exams (its design, feedback and grading) must follow specific technical requirements, specified in LPROG.
The ANTLR tool should be used (https://www.antlr.org/).

## 3. Grámatica

```
grammar FormativeExam;

prog: title header section+;

title: TITLE words NEWLINE;

header: HEADER NEWLINE
        TYPE_OF_FEEDBACK type NEWLINE
        TYPE_OF_GRADE type NEWLINE
        DESCRIPTION words? NEWLINE;

section: SECTION NEWLINE
         DESCRIPTION words NEWLINE
         TYPE_QUESTION type_questions+;

type_questions: nums DIVISIVE question COMMA? NEWLINE?;

question: MATCHING | MULTIPLE_CHOICE | SHORT_ANSWER | NUMERICAL | SELECT_MISSING_WORDS | TRUE_FALSE;

words : (CHAR | nums | FLOAT)+;

nums : NUM+;

type : NONE | ON_SUBMISSION | AFTER_CLOSING;

TITLE : 'Title' IDENTIFIER;
HEADER : 'Header' IDENTIFIER;
TYPE_OF_FEEDBACK : 'Type of feedback' IDENTIFIER;
TYPE_OF_GRADE : 'Type of grade' IDENTIFIER;
DESCRIPTION : 'Description' IDENTIFIER;
SECTION : 'Section' IDENTIFIER;
TYPE_QUESTION: 'Type of questions' IDENTIFIER;
MATCHING : 'Matching';
MULTIPLE_CHOICE : 'Multiple Choice';
SHORT_ANSWER : 'Short Answer';
NUMERICAL : 'Numerical';
SELECT_MISSING_WORDS : 'Select Missing Words';
TRUE_FALSE : 'True/False';
NONE : 'none';
ON_SUBMISSION : 'on-submission';
AFTER_CLOSING : 'after-closing';
IDENTIFIER : ':' ' '?;
COMMA: ',' ' '?;
NUM : [0-9];
NEWLINE : [\r\n]+;
FLOAT : [0-9]+ ',' [0-9]+;
NUMERICAL_OPTION : [1-9][0-9]? ;
DIVISIVE : '-';
CHAR : [a-zA-ZàèìòùáéíóúÀÈÌÒÙÁÉÍÓÚãõÃÕâÂêÊîÎôÔûÛçÇäÄëËïÏöÖüÜº_ ,.?!"%'-];
WS : [ \t\r\n]+ -> skip;
```
