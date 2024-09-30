grammar Exam;

@header {
package eapli.base.exammanagement.lprog;
}

//Parser rules

exam: title header section+;

title: TITLE phrase NEWLINE
       OPENING_DATE_TIME DATE SPACE TIME NEWLINE
       CLOSING_DATE_TIME DATE SPACE TIME NEWLINE
       ;

header: HEADER NEWLINE
        TYPE_OF_FEEDBACK type NEWLINE
        TYPE_OF_GRADE type NEWLINE
        DESCRIPTION phrase? NEWLINE
        ;

section: SECTION NEWLINE
         DESCRIPTION phrase? NEWLINE
         question+
         ;

question: matching | multipleChoice | shortAnswer | numerical | selectMissingWords | trueFalse;

matching: MATCHING NEWLINE
          phrase NEWLINE
          pair
          pair+
          SCORE_PER_LINE FLOAT NEWLINE
          CORRECT_ANSWER matchingOptionCorrection+ NEWLINE
          feedback?
          ;

pair: NUMERICAL_OPTION phrase DIVISIVE ALPHABETICAL_OPTION phrase NEWLINE;

matchingOptionCorrection : NUMERICAL_OPTION DASH ALPHABETICAL_OPTION;

multipleChoice: MULTIPLE_CHOICE NEWLINE
                phrase NEWLINE
                option+
                SCORE FLOAT NEWLINE
                CORRECT_ANSWER CHAR NEWLINE
                feedback?
                ;

shortAnswer: SHORT_ANSWER NEWLINE
             phrase NEWLINE
             SCORE FLOAT NEWLINE
             CORRECT_ANSWER phrase NEWLINE
             feedback?
             ;

numerical: NUMERICAL NEWLINE
           phrase NEWLINE
           SCORE FLOAT NEWLINE
           CORRECT_ANSWER FLOAT NEWLINE
           ERROR_NUMERICAL FLOAT NEWLINE
           feedback?
           ;

selectMissingWords: SELECT_MISSING_WORDS NEWLINE
                    uncompleteText NEWLINE
                    POSSIBLE_CHOICES_SELECT_MISSING_WORDS possibleChoices+ NEWLINE
                    SCORE FLOAT NEWLINE
                    CORRECT_ANSWER phrase NEWLINE
                    feedback?
                    ;

uncompleteText : phrase? CHOSING_OPTION_SELECT_MISSING_WORDS phrase?;

possibleChoices : words+ UNDERSCORE;

trueFalse: TRUE_FALSE NEWLINE
           phrase NEWLINE
           SCORE FLOAT NEWLINE
           CORRECT_ANSWER TRUE_OR_FALSE NEWLINE
           feedback?
           ;

phrase : words+ PUNCTUATION_MARKS?;

option: ALPHABETICAL_OPTION phrase NEWLINE;

feedback: FEEDBACK phrase? NEWLINE?;

type : NONE | ON_SUBMISSION | AFTER_CLOSING;

words : WORD | NUMS;

//Lexer rules
TRUE_OR_FALSE: 'true' | 'false';
NONE: 'none';
ON_SUBMISSION: 'on-submission';
CHOSING_OPTION_SELECT_MISSING_WORDS: '<option>';
AFTER_CLOSING: 'after-closing';

IDENTIFIER: TWO_DOTS SPACE?;
TITLE: 'Title' IDENTIFIER;
OPENING_DATE_TIME: 'Opening date and time' IDENTIFIER;
CLOSING_DATE_TIME: 'Closing date and time' IDENTIFIER;
HEADER: 'Header' IDENTIFIER;
TYPE_OF_FEEDBACK: 'Type of feedback' IDENTIFIER;
TYPE_OF_GRADE: 'Type of grade' IDENTIFIER;
DESCRIPTION: 'Description' IDENTIFIER;
SECTION: 'Section' IDENTIFIER;
MATCHING: 'Matching' IDENTIFIER;
MULTIPLE_CHOICE: 'Multiple Choice' IDENTIFIER;
SHORT_ANSWER: 'Short Answer' IDENTIFIER;
NUMERICAL: 'Numerical' IDENTIFIER;
SELECT_MISSING_WORDS: 'Select Missing Words' IDENTIFIER;
TRUE_FALSE: 'True/False' IDENTIFIER;
SCORE_PER_LINE: 'Score per line' IDENTIFIER;
SCORE: 'Score' IDENTIFIER;
CORRECT_ANSWER: 'Correct answer' IDENTIFIER;
FEEDBACK: 'Feedback' IDENTIFIER;
ERROR_NUMERICAL: 'Error' IDENTIFIER;
POSSIBLE_CHOICES_SELECT_MISSING_WORDS: 'Possible choices' IDENTIFIER;

DATE: ('0'[1-9] | [12]NUM | '3'[01]) SLASH ('0'[1-9] | '1'[0-2]) SLASH NUM NUM NUM NUM;
TIME: ([01]NUM | '2'[0-3]) TWO_DOTS [0-6]NUM;

NUMS: NUM+;

CHAR: [a-zA-Z];
NUM: [0-9];
NEWLINE: [\r\n]+;
SPECIAL_CHAR: [º"%'];
PUNCTUATION_MARKS: [.?!];

DOT: '.';
DASH: '-';
SLASH: '/';
SPACE: ' ';
COMMA: ',';
TWO_DOTS: ':';
UNDERSCORE: '_';
RIGHT_PARENTHESES: ')';

DIVISIVE: SPACE? DASH SPACE?;
FLOAT: NUMS DOT NUMS;
NUMERICAL_OPTION: [1-9]NUM? RIGHT_PARENTHESES;
ALPHABETICAL_OPTION: [a-z] RIGHT_PARENTHESES SPACE?;
MATCHING_OPTION_CORRECTION: SPACE? NUMS DASH CHAR COMMA?;
WORD: (CHAR | SPECIAL_CHAR | SPACE | COMMA)+;

WS: [ \t\r\n]+ -> skip;