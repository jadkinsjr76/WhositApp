package com.example.jackadkins.whosit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TakeQuizActivity extends AppCompatActivity
{
    private Quiz quiz = null;
    private TextView questionText;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);

        getSupportActionBar().hide();

        // create button
        questionText = (TextView) findViewById(R.id.quiz_question_textView);

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

        // go off to db and get quiz and questions
        quiz = db.getQuiz(quizId);
        ArrayList<Question> qu = db.getQuestions(quizId);
        if(qu.size() == 1)
            questionText.setText("1");
        if(qu.size() == 2)
            questionText.setText("2");
        if(qu.size() == 3)
            questionText.setText("3");
       // quiz.setAllQuestions(db.getQuestions(quizId));

        // loop through each question
        for(int j = 0; j < quiz.getNumQuestions(); j++)
        {
            // get all the answers
            quiz.getQuestion(j).setAllAnswers(db.getAnswers(quiz.getQuestion(j).getQuestionID()));
        }
        // loop through each question in quiz object
        for(int j = 0; j < quiz.getNumQuestions(); j++)
        {
            // create ArrayList to store every answer for the jth question
            ArrayList<Answer> answers = quiz.getQuestion(j).returnAllAnswers();

            // loop through the answers list
            for(int i = 0; i < answers.size(); i++)
            {
                // if the result isn't in the list already then add it.  else do nothing
                if(quiz.doesResultExist(answers.get(i).getResult()) == false)
                {
                    quiz.insertResult(answers.get(i).getResult());
                }
            }
        }
        //questionText.setText(quiz.getQuestion(1).getQuestionText());
    }

}
