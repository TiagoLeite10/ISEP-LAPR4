grammar FormativeExam;

@header {
package eapli.base.formativeexammanagement.lprog;
}

//Parser rules

formativeExam: title header section+;

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
         TYPE_QUESTION typeQuestion+ NEWLINE
         ;

typeQuestion: SMALLER NUMS DIVISIVE question BIGGER SPACE?;

question: MATCHING | MULTIPLE_CHOICE | SHORT_ANSWER | NUMERICAL | SELECT_MISSING_WORDS | TRUE_FALSE;

phrase : words+ PUNCTUATION_MARKS?;

type : NONE | ON_SUBMISSION | AFTER_CLOSING;

words : WORD | NUMS;

//Lexer rules

NONE: 'none';
ON_SUBMISSION: 'on-submission';
AFTER_CLOSING: 'after-closing';
MATCHING: 'Matching';
MULTIPLE_CHOICE: 'Multiple Choice';
SHORT_ANSWER: 'Short Answer';
NUMERICAL: 'Numerical';
SELECT_MISSING_WORDS: 'Select Missing Words';
TRUE_FALSE: 'True/False';

IDENTIFIER: TWO_DOTS SPACE?;
TITLE: 'Title' IDENTIFIER;
OPENING_DATE_TIME: 'Opening date and time' IDENTIFIER;
CLOSING_DATE_TIME: 'Closing date and time' IDENTIFIER;
HEADER: 'Header' IDENTIFIER;
TYPE_OF_FEEDBACK: 'Type of feedback' IDENTIFIER;
TYPE_OF_GRADE: 'Type of grade' IDENTIFIER;
DESCRIPTION: 'Description' IDENTIFIER;
SECTION: 'Section' IDENTIFIER;
TYPE_QUESTION: 'Type of questions' IDENTIFIER;
SCORE: 'Score' IDENTIFIER;

DATE: ('0'[1-9] | [12]NUM | '3'[01]) SLASH ('0'[1-9] | '1'[0-2]) SLASH NUM NUM NUM NUM;
TIME: ([01]NUM | '2'[0-3]) TWO_DOTS [0-6]NUM;
NUMS: NUM+;
DIVISIVE: SPACE? DASH SPACE?;

CHAR: [a-zA-Z];
NUM: [0-9];
NEWLINE: [\r\n]+;
SPECIAL_CHAR: [º"%'];
PUNCTUATION_MARKS: [.?!];

SMALLER: '<';
BIGGER: '>';
DOT: '.';
DASH: '-';
SLASH: '/';
SPACE: ' ';
COMMA: ',';
TWO_DOTS: ':';

WORD: (CHAR | SPECIAL_CHAR | SPACE | COMMA)+;

WS: [ \t\r\n]+ -> skip;