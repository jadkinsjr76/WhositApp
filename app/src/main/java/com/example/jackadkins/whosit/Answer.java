package com.example.jackadkins.whosit;


public class Answer
{
    private int answerID;
    private int questionID;
    private String answerName;
    private String result;

    public Answer(String answerName)
    {
        this.answerName = answerName;
    }

    public Answer(String answerName, String result)
    {
        this.answerName = answerName;
        this.result = result;
    }

    public void setAnswerID(int newAnswerID)
    {
        this.answerID = newAnswerID;
    }

    public int getAnswerID()
    {
        return answerID;
    }

    public void setQuestionID(int questionID)
    {
        this.questionID = questionID;
    }

    public int getQuestionID()
    {
        return questionID;
    }

    public void setAnswerName(String newAnswerName)
    {
        this.answerName = newAnswerName;
    }

    public String getAnswerName()
    {
        return answerName;
    }

    public void setResult(String newResult)
    {
        this.result = newResult;
    }

    public String getResult()
    {
        return result;
    }
}
