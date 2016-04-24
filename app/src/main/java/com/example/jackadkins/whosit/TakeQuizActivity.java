package com.example.jackadkins.whosit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class TakeQuizActivity extends AppCompatActivity
{
    Quiz quiz = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);

        getSupportActionBar().hide();

        Intent intent = getIntent();
        int quizId = intent.getIntExtra("QUIZ_ID", -1);

        if(quizId >= 0)
        {
            // go to database and get quiz info and build the quiz object
            getQuizById(quizId);
        }
    }

    private void getQuizById(int quizId)
    {
        // create db object
        WhosItDB db = new WhosItDB(this);
        // go off to db and get quiz
        if(quiz == null)
        {
            quiz = db.getQuiz(quizId);
            quiz.setAllQuestions(db.getQuestions(quizId));

            for(int j = 0; j < quiz.getNumQuestions(); j++)
            {
                quiz.getQuestion(j).setAllAnswers(db.getAnswers(quiz.getQuestion(j).getQuestionID()));
            }
        }
    }

}
