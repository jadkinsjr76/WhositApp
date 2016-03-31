package com.example.jackadkins.whosit;

/**
 * Created by Jack Adkins on 3/21/2016.
 */
// quiz class used for creating and displaying a quiz
public class Quiz
{
    // data members quiz name, id
    private String quizTitle;
    private String quizID;
    private Question question[] = new Question[20];

    public Quiz(String quizTitle, String quizID)
    {
        this.quizTitle = quizTitle;
        this.quizID = quizID;
    }

    // adds question and returns true on success false on failure
    public boolean addQuestion(Question q)
    {
        if(question.length < 20)
        {
            question[question.length] = new Question(q.getQuestionTitle());
            return true;
        }
        return false;
    }

    public Question getQuestion(int index)
    {
        return question[index];
    }
}
