package com.example.jackadkins.whosit;

/**
 * Created by Jack Adkins on 3/21/2016.
 */
public class Question
{
    private String questionTitle;
    private Choice[] choice = new Choice[4];

    public Question(String questionTitle)
    {
        this.questionTitle = questionTitle;
    }

    public String getQuestionTitle()
    {
        return questionTitle;
    }
}
