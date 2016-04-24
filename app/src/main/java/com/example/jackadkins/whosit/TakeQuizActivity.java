package com.example.jackadkins.whosit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TakeQuizActivity extends AppCompatActivity
{
    // Widget class variables:
    private TextView    question;
    private RadioButton answer1;
    private RadioButton answer2;
    private RadioButton answer3;
    private RadioButton answer4;

    // Class instance variables:
    private WhosItDB          db;
    private Quiz              quiz;
    private Question          questions;
    private int               quizID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);

        // Initialize variables to widgets:
        question = (TextView)    findViewById(R.id.quiz_question_textView);
        answer1  = (RadioButton) findViewById(R.id.answer_radio1);
        answer2  = (RadioButton) findViewById(R.id.answer_radio2);
        answer3  = (RadioButton) findViewById(R.id.answer_radio3);
        answer4  = (RadioButton) findViewById(R.id.answer_radio4);

        // Load in the quizID for the current quiz being taken:
        Intent intent = getIntent();
        quizID = intent.getIntExtra("QUIZ_ID", -1);

        // Get quiz from the database, load in the questions for the quiz, load all answers:
        db = new WhosItDB(this);
        quiz = db.getQuiz(quizID);
        quiz.setAllQuestions(db.getQuestions(quizID));
        for(int i = 0; i < quiz.getNumQuestions(); i++) {
            quiz.getQuestion(i).setAllAnswers(db.getAnswers(quiz.getQuestion(i).getQuestionID()));
        }

        for (int questionNum = 0; questionNum < quiz.getNumQuestions(); questionNum++) {

            // Display information:
            question.setText(quiz.getQuestion(0).getQuestionText());

//            answer1.setText(quiz.getQuestion(questionNum).getAnswer(0).getAnswerName());
//            answer2.setText(quiz.getQuestion(questionNum).getAnswer(1).getAnswerName());
//            answer3.setText(quiz.getQuestion(questionNum).getAnswer(2).getAnswerName());
//            answer4.setText(quiz.getQuestion(questionNum).getAnswer(3).getAnswerName());

            break;

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
