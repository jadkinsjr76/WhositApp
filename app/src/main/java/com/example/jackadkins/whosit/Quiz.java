package com.example.jackadkins.whosit;

public class Quiz
{
    private int quizID;
    private int userID;
    private String name;
    private Question[] question = new Question[20];

    public Quiz(String name)
    {
        this.name = name;
        for(int i = 0; i < question.length; i++)
        {
            question[i].setQuestionText(" ");
        }

    }

    // adds question and returns true on success false on failure
    public boolean addQuestion(Question q)
    {
        if(question.length < 20)
        {
            //What about question[question.length] = q; ?
            question[question.length] = new Question(q.getQuestionText());
            return true;
        }
        return false;
    }

    public void changeQuestion(Question q, int index)
    {
        question[index].setQuestionText(q.getQuestionText());
    }

    public void changeAllQuestions(Question[] questionArray)
    {
        question = questionArray;
    }

    public Question getQuestion(int index)
    {
        return question[index];
    }

    public Question[] getAllQuestions()
    {
        return question;
    }

    public void setQuizID(int id)
    {
        this.quizID = id;
    }

    public int getQuizID()
    {
        return quizID;
    }

    public int getUserID()
    {
        return userID;
    }

    public void setUserID(int newID)
    {
        userID = newID;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
