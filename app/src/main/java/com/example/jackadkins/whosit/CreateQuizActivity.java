package com.example.jackadkins.whosit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

                    break;
            }
        }
    }


    private Button enterNameButton;
    private Button enterQuestionButton;
    private Button enterResultButton;
    private Button enterAnswerButton;
    private Quiz newQuiz = new Quiz();
    private Question[] newQuestionArray = new Question[20];
    private String[] questionArray = new String[20];
    private String[] answerArray;
    private String[] resultArray;
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

        quizName = getIntent().getStringExtra("quizName");
        newQuiz.setName(quizName);
        questionArray = getIntent().getStringArrayExtra("questionArray");
       /* if(questionArray != null){
            int questionCount = 0;
            for(int i = 0; i < questionArray.length; i++){
                if(!questionArray[i].equals(" ") || questionArray[i]!= null){
                    newQuiz.getQuestion(questionCount).setQuestionText(questionArray[i]);
                    Toast.makeText(CreateQuizActivity.this, newQuiz.getQuestion(questionCount).getQuestionText(), Toast.LENGTH_SHORT).show();
                    questionCount++;

                }
            }
        }*/
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
        Intent createResultIntent = new Intent(this, CreateResultsActivity.class);
        createResultIntent.putExtra("resultArray", resultArray);
        startActivity(createResultIntent);
    }

    private void launchAnswerActivity(){
        Intent createAnswerIntent = new Intent(this, CreateAnswersActivity.class);
        createAnswerIntent.putExtra("answerArray", answerArray);
        startActivity(createAnswerIntent);
    }
}
