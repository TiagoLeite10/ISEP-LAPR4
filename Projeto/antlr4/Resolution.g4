grammar Resolution;

@header {
package eapli.base.takenexammanagement.lprog;
}

//Parser rules

resolution: section+;

section: SECTION NEWLINE
         answer+
         ;

answer: answerToMatching | answerToMultipleChoice | answerToShortAnswer | answerToNumerical | answerToSelectMissingWords | answerToTrueFalse;

answerToMatching: ANSWER matchingOption* NEWLINE?
                  ;

matchingOption: NUMERICAL_OPTION DASH (SPACE? | ALPHABETICAL_OPTION);

answerToMultipleChoice: ANSWER CHAR? NEWLINE?
                        ;

answerToShortAnswer: ANSWER phrase? NEWLINE?
                     ;

answerToNumerical: ANSWER FLOAT? NEWLINE?
                   ;

answerToSelectMissingWords: ANSWER phrase? NEWLINE?
                            ;

answerToTrueFalse: ANSWER TRUE_OR_FALSE? NEWLINE?
                   ;

phrase : words+ PUNCTUATION_MARKS?;

words : WORD | NUMS;

//Lexer rules
TRUE_OR_FALSE: 'true' | 'false';

IDENTIFIER: TWO_DOTS SPACE?;
SECTION: 'Section' IDENTIFIER;
ANSWER: 'Answer' IDENTIFIER;

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

FLOAT: NUMS DOT NUMS;
NUMERICAL_OPTION: [1-9]NUM? RIGHT_PARENTHESES;
ALPHABETICAL_OPTION: [a-z] RIGHT_PARENTHESES SPACE?;
WORD: (CHAR | SPECIAL_CHAR | SPACE | COMMA)+;

WS: [ \t\r\n]+ -> skip;