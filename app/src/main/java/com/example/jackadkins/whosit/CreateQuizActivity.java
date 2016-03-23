package com.example.jackadkins.whosit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateQuizActivity extends AppCompatActivity {

    class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v){
            switch (v.getId()){
                //launch nameActivity
                case R.id.enterNameButton:
                    launchNameActivity();
                    break;
                //launch questionActivity
                case R.id.enterQuestionsButton:
                    launchQuestionActivity();
                    break;
                //launch resultsActivity
                case R.id.enterResultsButton:

                    break;
                //launch answerActivity
                case R.id.enterAnswersButton:

                    break;
            }
        }
    }


    private Button enterNameButton;
    private Button enterQuestionButton;
    private Button enterResultButton;
    private Button enterAnswerButton;
    private String[] questionArray;
    private String quizName;

    private ButtonListener mButtonListener = new ButtonListener();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        enterNameButton = (Button) findViewById(R.id.enterNameButton);
        enterQuestionButton = (Button) findViewById(R.id.enterQuestionsButton);
        enterResultButton = (Button) findViewById(R.id.enterResultsButton);
        enterAnswerButton = (Button) findViewById(R.id.enterAnswersButton);

        enterNameButton.setOnClickListener(mButtonListener);
        enterQuestionButton.setOnClickListener(mButtonListener);
        enterResultButton.setOnClickListener(mButtonListener);
        enterAnswerButton.setOnClickListener(mButtonListener);

        Intent backIntent = getIntent();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String questionArray = extras.getString("questionArray");
        }

    }

    private void launchNameActivity(){
        Intent createNameIntent = new Intent(this, CreateNameActivity.class);
        createNameIntent.putExtra("quizName", quizName);
        startActivity(createNameIntent);
        //send name over
    }

    private void launchQuestionActivity(){
        Intent createQuestionIntent = new Intent(this, CreateQuestionActivity.class);
        createQuestionIntent.putExtra("questionArray", questionArray);
        startActivity(createQuestionIntent);
        //send question array over
    }

    private void launchResultsActivity(){

    }

    private void launchAnswerActivity(){

    }
}
