grammar ExamQuestion;

@header {
package eapli.base.questionmanagement.lprog;
}

//Parser rules

examQuestion: question+;

question: matching | multipleChoice | shortAnswer | numerical | selectMissingWords | trueFalse;

matching: MATCHING NEWLINE
          phrase NEWLINE
          pair
          pair+
          SCORE_PER_LINE FLOAT NEWLINE
          CORRECT_ANSWER matchingOptionCorrection+ NEWLINE
          feedback?
          ;

pair: NUMERICAL_OPTION phrase DIVISIVE ALPHABETICAL_OPTION phrase NEWLINE
      ;

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

words : WORD | NUMS;

//Lexer rules

TRUE_OR_FALSE: 'true' | 'false';
CHOSING_OPTION_SELECT_MISSING_WORDS: '<option>';

IDENTIFIER: TWO_DOTS SPACE?;
SECTION: 'Section' IDENTIFIER;
MATCHING: 'Matching' IDENTIFIER;
MULTIPLE_CHOICE: 'Multiple Choice' IDENTIFIER;
SHORT_ANSWER: 'Short Answer' IDENTIFIER;
NUMERICAL: 'Numerical' IDENTIFIER;
SELECT_MISSING_WORDS: 'Select Missing Words' IDENTIFIER;
TRUE_FALSE: 'True/False' IDENTIFIER;
SCORE: 'Score' IDENTIFIER;
SCORE_PER_LINE: 'Score per line' IDENTIFIER;
CORRECT_ANSWER: 'Correct answer' IDENTIFIER;
FEEDBACK: 'Feedback' IDENTIFIER;
ERROR_NUMERICAL: 'Error' IDENTIFIER;
POSSIBLE_CHOICES_SELECT_MISSING_WORDS: 'Possible choices' IDENTIFIER;

NUMS: NUM+;

CHAR: [a-zA-Z];
NUM: [0-9];
NEWLINE: [\r\n]+;
PUNCTUATION_MARKS: [.?!];

DOT: '.';
DASH: '-';
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
SPECIAL_CHAR: [º"%'];

WS: [ \t\r\n]+ -> skip;