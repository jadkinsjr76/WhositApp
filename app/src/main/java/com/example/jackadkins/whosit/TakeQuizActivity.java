package com.example.jackadkins.whosit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TakeQuizActivity extends AppCompatActivity implements View.OnClickListener
{
    private Quiz quiz = null;

    private TextView questionText;
    RadioGroup rgp;
    private RadioButton rbAnswer1;
    private RadioButton rbAnswer2;
    private RadioButton rbAnswer3;
    private RadioButton rbAnswer4;
    private Button nextBtn;
    private int currentQuestion = 0;

    private int    userID;
    private int    quizId;
    private String usernameString;

    private WhosItDB db = new WhosItDB(this);


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);

//        getSupportActionBar().hide();

        // create button
        questionText = (TextView) findViewById(R.id.quiz_question_textView);
        rgp = (RadioGroup) findViewById(R.id.rg_group);
        rbAnswer1 = (RadioButton) findViewById(R.id.answer_radio1);
        rbAnswer2 = (RadioButton) findViewById(R.id.answer_radio2);
        rbAnswer3 = (RadioButton) findViewById(R.id.answer_radio3);
        rbAnswer4 = (RadioButton) findViewById(R.id.answer_radio4);
        nextBtn   = (Button) findViewById(R.id.buttonNext);

        nextBtn.setOnClickListener(this);

        Intent intent = getIntent();
        quizId = intent.getIntExtra("QUIZ_ID", -1);
        userID = intent.getIntExtra("USER_ID", -1);
        usernameString = intent.getStringExtra("USER_NAME");

        if(quizId >= 0)
        {
            // go to database and get quiz info and build the quiz object
            getQuizById(quizId);
            runQuiz();
        }
    }

    private void getQuizById(int quizId)
    {

        // create db object
        //WhosItDB db = new WhosItDB(this);

        // go off to db and get quiz and questions
        quiz = db.getQuiz(quizId);
       // set all the questions up
        quiz.setAllQuestions(db.getQuestions(quizId));

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

    }

    public void displayQuestion()
    {
        questionText.setText(quiz.getQuestion(currentQuestion).getQuestionText());
    }

    public void displayAnswers()
    {
        rbAnswer1.setChecked(true);
        rbAnswer1.setText(quiz.getQuestion(currentQuestion).getAnswer(0).getAnswerName());
        rbAnswer2.setText(quiz.getQuestion(currentQuestion).getAnswer(1).getAnswerName());
        rbAnswer3.setText(quiz.getQuestion(currentQuestion).getAnswer(2).getAnswerName());
        rbAnswer4.setText(quiz.getQuestion(currentQuestion).getAnswer(3).getAnswerName());
    }

    public void runQuiz()
    {
        displayQuestion();
        displayAnswers();
    }

    @Override
    public void onClick(View v)
    {
        // take care of the result increments
        String result = "";
        // get the selected radio button
        int choice = rgp.getCheckedRadioButtonId();

        // find out which one was selected and get the result
        if(choice == rbAnswer1.getId())
        {
            result = quiz.getQuestion(currentQuestion).getAnswer(0).getResult();
        }

        else if(choice == rbAnswer2.getId())
        {
            result = quiz.getQuestion(currentQuestion).getAnswer(1).getResult();
        }

        else if(choice == rbAnswer3.getId())
        {
            result = quiz.getQuestion(currentQuestion).getAnswer(2).getResult();
        }

        else if(choice == rbAnswer4.getId())
        {
            result = quiz.getQuestion(currentQuestion).getAnswer(3).getResult();
        }

      //  if(!result.equals(""))
        {
            // look through ArrayList until we get a match
            int key = quiz.searchResult(result);
            if(key != -1)
            {
                Log.d("Tag", "Key = " + key);
                quiz.incrementKey(key);
            }
        }

        if(currentQuestion < quiz.getNumQuestions() - 1)
        {
            if(currentQuestion == quiz.getNumQuestions() - 2)
            {
                nextBtn.setText("Submit");
            }

            currentQuestion++;
            runQuiz();
        }
        else
        {
            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            intent.putExtra("QUIZNAME", quiz.getName());
            intent.putExtra("RESULT", quiz.findMaxResult());
            intent.putExtra("USER_ID", userID);
            intent.putExtra("USER_NAME", usernameString);
            startActivity(intent);
        }

    }

    /**
     * This overridden method will handle and display the menu for the Profile Activity.
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_result, menu);
        return true;
    }

    /**
     * This method will handle when something in the menu is clicked.
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Variable initialization:
        Intent intent;

        // Switch statement to handle cases:
        switch (item.getItemId()) {
            case R.id.action_backToProfile:
                intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.putExtra("USER_ID", userID);
                intent.putExtra("USER_NAME", usernameString);
                startActivity(intent);
        }
        return true;
    }
}
