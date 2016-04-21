package com.example.jackadkins.whosit;

public class Question
{
    private int questionID;
    private int quizID;
    private String questionTitle;
    private Answer answer[] = new Answer[8];

    public Question(String questionTitle)
    {
        this.questionTitle = questionTitle;
        for(int i = 0; i < answer.length; i++)
        {
            answer[i].setAnswerName("  ");
            answer[i].setResult(" ");
        }
    }

    public String getQuestionTitle()
    {
        return questionTitle;
    }

    public void setQuestionTitle(String newTitle)
    {
        this.questionTitle = newTitle;
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
        answer[index] = newAnswer;
    }

    public Answer getAnswer(int index)
    {
        return answer[index];
    }

    public void setAllAnswers(Answer[] newAnswers)
    {
        answer = newAnswers;
    }

    public Answer[] returnAllAnswers()
    {
        return answer;
    }


}
