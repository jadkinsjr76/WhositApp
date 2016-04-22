package com.example.jackadkins.whosit;

public class Question
{
    private int questionID;
    private int quizID;
    private String questionText;
    private Answer[] answer = new Answer[8];

    // construction
    public Question(String questionText)
    {
        this.questionText = questionText;
        for(int i = 0; i < answer.length; i++)
        {
            answer[i].setAnswerName("  ");
            answer[i].setResult(" ");
        }
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
