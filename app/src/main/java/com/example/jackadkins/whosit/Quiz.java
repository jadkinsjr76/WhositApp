package com.example.jackadkins.whosit;

import java.util.ArrayList;

public class Quiz
{
    private int quizID;
    private int userID;
    private String name;
    private ArrayList<Question> questions = new ArrayList<Question>();

    public Quiz()
    {

    }

    public Quiz(String name)
    {
        this.name = name;
//        for(int i = 0; i < questions.size(); i++)
//        {
//            //question[i].setQuestionText(" ");
//            questions.get(i).setQuestionText(" ");
//        }
    }

    public Quiz(String name, int quizID, int userID)
    {
        this.name = name;
        this.quizID = quizID;
        this.userID = userID;
    }

    // adds question and returns true on success false on failure
    public boolean addQuestion(Question q)
    {
        if(questions.size() < 20)
        {
            //What about question[question.length] = q; ?
            //question[question.length] = new Question(q.getQuestionText());
            questions.add(q);
            return true;
        }
        return false;
    }

    public void changeQuestion(Question q, int index)
    {
        //questions[index].setQuestionText(q.getQuestionText());
        questions.set(index, q);
    }

    public void changeAllQuestions(ArrayList<Question> questionArray)
    {
        questions = (ArrayList<Question>)questionArray.clone();
        
    }

    public Question getQuestion(int index)
    {
        return questions.get(index);
    }

    public ArrayList<Question> getAllQuestions()
    {
        return questions;
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

    public void setAllQuestions(ArrayList<Question> q)
    {
       questions = q;
    }

    public int getNumQuestions()
    {
        return questions.size();
    }
}
