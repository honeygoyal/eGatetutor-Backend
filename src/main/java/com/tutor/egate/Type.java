package com.tutor.egate;

import java.io.Serializable;
import java.util.regex.Pattern;

public enum Type implements Serializable
{
    SECTION,
    MARKS,
    NEGATIVE_MARKS,
    QUESTION_TYPE,
    QUESTION_LABEL,
    ANSWER,
    SOLUTION,
    DIFFICULTY,
    VIDEO_LINK;

    public String toString(){

        String typeS = "";
        switch (this)
        {
            case SECTION:
                typeS = "SECTION";
                break;
            case MARKS:
                typeS = "MARKS";
                break;
            case NEGATIVE_MARKS:
                typeS = "NEGATIVE MARKS";
                break;
            case QUESTION_TYPE:
                typeS = "TYPE";
                break;
            case QUESTION_LABEL:
                typeS = "QUESTION LABEL";
                break;
            case ANSWER:
                typeS = "ANSWER";
                break;
            case SOLUTION:
                typeS = "SOLUTION";
                break;
            case DIFFICULTY:
                typeS = "DIFFICULTY";
                break;
            case VIDEO_LINK:
                typeS = "VIDEO LINK";
                break;
        }

      return typeS;
    }

    public static Type find(String value)
    {
        Type type = null;
        if(value.equalsIgnoreCase(SECTION.toString())) type = Type.SECTION;
        else if(Pattern.matches("\\d", value)) type = Type.QUESTION_LABEL;
        else if(value.equalsIgnoreCase(MARKS.toString())) type = Type.MARKS;
        else if(value.equalsIgnoreCase(NEGATIVE_MARKS.toString())) type = Type.NEGATIVE_MARKS;
        else if(value.equalsIgnoreCase(QUESTION_TYPE.toString())) type = Type.QUESTION_TYPE;
        else if(value.equalsIgnoreCase(ANSWER.toString())) type = Type.ANSWER;
        else if(value.equalsIgnoreCase(SOLUTION.toString())) type = Type.SOLUTION;
        else if(value.equalsIgnoreCase(DIFFICULTY.toString())) type = Type.DIFFICULTY;
        else if(value.equalsIgnoreCase(VIDEO_LINK.toString())) type = Type.VIDEO_LINK;
        return type;
    }

}
