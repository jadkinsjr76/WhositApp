package com.example.jackadkins.whosit;

import java.util.ArrayList;

public class Question
{
    private int questionID;
    private int quizID;
    private String questionText;
    private ArrayList<Answer> answers = new ArrayList<Answer>();
    // construction
    public Question(String questionText)
    {
        this.questionText = questionText;
    }

    public Question(String questionText, int questionID, int quizID)
    {
        this.questionText = questionText;
        this.questionID = questionID;
        this.quizID = quizID;
    }

    public String getQuestionText()
    {
        return questionText;
    }

    public void setQuestionText(String questionText)
    {
        this.questionText = questionText;
    }

    public int getQuestionID()
    {
        return questionID;
    }

    public void setQuestionID(int newQuestionID)
    {
        this.questionID = newQuestionID;
    }

    public int getQuizID()
    {
        return quizID;
    }

    public void setQuizID(int newQuizID)
    {
        this.quizID = newQuizID;
    }

    public void setAnswer(Answer newAnswer, int index)
    {
        answers.set(index, newAnswer);
    }

    public Answer getAnswer(int index)
    {
        return answers.get(index);
    }

    public void setAllAnswers(ArrayList<Answer> newAnswers)
    {
        answers = newAnswers;
    }

    public ArrayList<Answer> returnAllAnswers()
    {
        return answers;
    }


}
