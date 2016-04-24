package com.example.jackadkins.whosit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class TakeQuizActivity extends AppCompatActivity
{
<<<<<<< Updated upstream
    Quiz quiz = null;
=======
    // Widget class variables:
    private TextView    questionTextView;
    private RadioButton answer1;
    private RadioButton answer2;
    private RadioButton answer3;
    private RadioButton answer4;

    // Class instance variables:
    private WhosItDB          db;
    private Quiz              quiz;
    private Question          questions;
    private int               quizID;
>>>>>>> Stashed changes

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);

<<<<<<< Updated upstream
        getSupportActionBar().hide();
=======
        // Initialize variables to widgets:
        questionTextView = (TextView)    findViewById(R.id.quiz_question_textView);
        answer1          = (RadioButton) findViewById(R.id.answer_radio1);
        answer2          = (RadioButton) findViewById(R.id.answer_radio2);
        answer3          = (RadioButton) findViewById(R.id.answer_radio3);
        answer4          = (RadioButton) findViewById(R.id.answer_radio4);
>>>>>>> Stashed changes

        Intent intent = getIntent();
<<<<<<< Updated upstream
        int quizId = intent.getIntExtra("quizId", -1);

        if(quizId >= 0)
        {
            // go to database and get quiz info and build the quiz object
            getQuizById(quizId);
        }
=======
        quizID = intent.getIntExtra("QUIZ_ID", -1);

        // Get quiz from the database, load in the questions for the quiz, load all answers:
        db = new WhosItDB(this);
        quiz = db.getQuiz(quizID);
        quiz.setAllQuestions(db.getQuestions(quizID));
        for(int i = 0; i < quiz.getNumQuestions(); i++) {
            quiz.getQuestion(i).setAllAnswers(db.getAnswers(quiz.getQuestion(i).getQuestionID()));
        }

        int questionNum = 0;

        // Display information:
        Question questionObject = quiz.getQuestion(questionNum);
        questionTextView.setText(questionObject.getQuestionText());

        answer1.setText(quiz.getQuestion(questionNum).getAnswer(0).getAnswerName());
        answer2.setText(quiz.getQuestion(questionNum).getAnswer(1).getAnswerName());
        answer3.setText(quiz.getQuestion(questionNum).getAnswer(2).getAnswerName());
        answer4.setText(quiz.getQuestion(questionNum).getAnswer(3).getAnswerName());

>>>>>>> Stashed changes
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
