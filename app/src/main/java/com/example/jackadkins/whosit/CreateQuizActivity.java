package com.example.jackadkins.whosit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

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
                    launchResultsActivity();
                    break;
                //launch answerActivity
                case R.id.enterAnswersButton:
                    launchAnswerActivity();
                    break;
                //launch profileActivity
                case R.id.finishButton:
                    launchProfileActivity();
            }
        }
    }

    private Button enterNameButton;
    private Button enterQuestionButton;
    private Button enterResultButton;
    private Button enterAnswerButton;
    private Button finishButton;
    private Quiz newQuiz;
    private int quizid = -1;
    private int questionid = -1;
    private ArrayList<Integer> questionIDArrayList = new ArrayList<>();
    private String[] questionArray = new String[20];
    private String[] answerArray = new String[80];
    private String[] resultMap = new String[80];
    private String[] resultArray = new String[8];
    private ArrayList<String> questionArrayList = new ArrayList<>();
    private String quizName = "";
    private ButtonListener mButtonListener = new ButtonListener();

    private int userid = -1;
    private String usernameString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        enterNameButton = (Button) findViewById(R.id.enterNameButton);
        enterQuestionButton = (Button) findViewById(R.id.enterQuestionsButton);
        enterResultButton = (Button) findViewById(R.id.enterResultsButton);
        enterAnswerButton = (Button) findViewById(R.id.enterAnswersButton);
        finishButton = (Button) findViewById(R.id.finishButton);


        enterNameButton.setOnClickListener(mButtonListener);
        enterQuestionButton.setOnClickListener(mButtonListener);
        enterResultButton.setOnClickListener(mButtonListener);
        enterAnswerButton.setOnClickListener(mButtonListener);
        finishButton.setOnClickListener(mButtonListener);

        //Get userID
        userid = getIntent().getIntExtra("USER_ID", -1);
        quizid = getIntent().getIntExtra("QUIZ_ID", -1);
        usernameString = getIntent().getStringExtra("USER_NAME");
        questionIDArrayList = getIntent().getIntegerArrayListExtra("QUESTION_IDS");

        // get results
        resultArray = getIntent().getStringArrayExtra("resultArray");

        //Downsize Results
        if(resultArray != null) {
            int resultCount = 0;
            for (int i = 0; i < resultArray.length; i++) {
                if(resultArray[i] != null && !resultArray[i].equals("") && !resultArray[i].equals(" ")){
                    resultCount++;
                }
            }
            String[] newResultArray = new String[resultCount];
            for(int i = 0; i < resultCount; i++){
                newResultArray[i] = resultArray[i];
            }
            resultArray = newResultArray;
        }

        // get answer array
        answerArray = getIntent().getStringArrayExtra("answerArray");

        //get mapped results array
        resultMap = getIntent().getStringArrayExtra("resultsMap");


    }

    private void launchNameActivity(){
        Intent createNameIntent = new Intent(this, CreateNameActivity.class);
        createNameIntent.putExtra("quizName", quizName);
        if (userid != -1) {
            createNameIntent.putExtra("USER_ID", userid);
        }

        createNameIntent.putExtra("USER_NAME", usernameString);

        createNameIntent.putExtra("questionArray", questionArray);
        createNameIntent.putExtra("answerArray", answerArray);
        createNameIntent.putExtra("resultArray", resultArray);
        createNameIntent.putExtra("resultsMap", resultMap);
        startActivity(createNameIntent);
    }

    private void launchQuestionActivity(){
        Intent createQuestionIntent = new Intent(this, CreateQuestionActivity.class);
        if(quizid != -1){
            createQuestionIntent.putExtra("QUIZ_ID", quizid);
        }

        createQuestionIntent.putExtra("USER_NAME", usernameString);
        createQuestionIntent.putExtra("USER_ID", userid);

        createQuestionIntent.putExtra("quizName", quizName);
        createQuestionIntent.putExtra("questionArray", questionArray);
        createQuestionIntent.putExtra("answerArray", answerArray);
        createQuestionIntent.putExtra("resultArray", resultArray);
        createQuestionIntent.putExtra("resultsMap", resultMap);
        startActivity(createQuestionIntent);
    }

    private void launchResultsActivity(){
        Intent createResultIntent = new Intent(this, CreateResultsActivity.class);
        createResultIntent.putExtra("QUESTION_IDS", questionIDArrayList);
       // createResultIntent.putExtra("QUESTION_ID", questionid);
        createResultIntent.putExtra("resultArray", resultArray);
        createResultIntent.putExtra("quizName", quizName);
        createResultIntent.putExtra("questionArray", questionArray);
        createResultIntent.putExtra("answerArray", answerArray);
        createResultIntent.putExtra("resultsMap", resultMap);

        createResultIntent.putExtra("USER_NAME", usernameString);
        createResultIntent.putExtra("USER_ID", userid);

        startActivity(createResultIntent);
    }

    private void launchAnswerActivity(){
        Intent createAnswerIntent = new Intent(this, CreateAnswersActivity.class);
        /*if(questionid != -1){
            createAnswerIntent.putExtra("QUESTION_ID", questionid);
        }*/
        if(questionIDArrayList != null){
            createAnswerIntent.putExtra("QUESTION_IDS", questionIDArrayList);
        }

        createAnswerIntent.putExtra("USER_NAME", usernameString);
        createAnswerIntent.putExtra("USER_ID", userid);

        createAnswerIntent.putExtra("answerArray", answerArray);
        createAnswerIntent.putExtra("resultArray", resultArray);
        createAnswerIntent.putExtra("resultsMap", resultMap);
        createAnswerIntent.putExtra("quizName", quizName);
        createAnswerIntent.putExtra("questionArray", questionArray);
        startActivity(createAnswerIntent);
    }

    private void launchProfileActivity(){
        Intent profileIntent = new Intent(this, ProfileActivity.class);
        profileIntent.putExtra("USER_NAME", usernameString);
        profileIntent.putExtra("USER_ID", userid);
        startActivity(profileIntent);
    }
}