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
            }
        }
    }

    private Button enterNameButton;
    private Button enterQuestionButton;
    private Button enterResultButton;
    private Button enterAnswerButton;
    private Quiz newQuiz;
    private int userid = -1;
    private String[] questionArray = new String[20];
    private String[] answerArray = new String[80];
    private String[] resultMap = new String[80];
    private String[] resultArray = new String[8];
    private ArrayList<String> questionArrayList = new ArrayList<>();
    private String quizName = "";
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

        //Get userID
        userid = getIntent().getIntExtra("USER_ID", -1);

        //Get quizname
        quizName = getIntent().getStringExtra("quizName");

        // get question array
        questionArray = getIntent().getStringArrayExtra("questionArray");

        //Downsize questions (similar to results)
        if(questionArray != null){
            int questionCount = 0;
            for(int i = 0; i < questionArray.length; i++){
                if(questionArray[i] != null && !questionArray[i].equals(" ") && !questionArray[i].equals("")) {
                    questionCount++;
                }
            }
            String[] newQuestionArray = new String[questionCount];
            for(int i = 0; i < questionCount; i++){
                newQuestionArray[i] = questionArray[i];
            }
            questionArray = newQuestionArray;
        }

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

        //Something along the line of
        if(quizName != null && questionArray != null && resultMap != null && answerArray != null){
            Toast.makeText(CreateQuizActivity.this, "We can make a quiz now.", Toast.LENGTH_SHORT).show();
            //create new quiz with quizName. create questions create Answers and put the results in the answers
            newQuiz = new Quiz();
            newQuiz.setName(quizName);
            for(int i = 0; i < questionArray.length; i++){
                Question question = new Question(questionArray[i]);
                newQuiz.addQuestion(question);
            }
            int answerCount = 0;
            for(int i = 0; i < newQuiz.getNumQuestions(); i++){
                Toast.makeText(CreateQuizActivity.this, "I made it here.", Toast.LENGTH_SHORT).show();
                for(int j = 0; j < 4; j++){
                    if(answerArray[answerCount] != null || !answerArray[answerCount].equals("") || !answerArray[answerCount].equals(" ")){
                        Answer answer = new Answer(answerArray[answerCount], resultMap[answerCount]);
                        newQuiz.getQuestion(i).setAnswer(answer, j);
                        Toast.makeText(CreateQuizActivity.this, newQuiz.getQuestion(i).getAnswer(j).getAnswerName(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

        }
    }

    private void launchNameActivity(){
        Intent createNameIntent = new Intent(this, CreateNameActivity.class);
        createNameIntent.putExtra("quizName", quizName);
        if (userid != -1) {
            createNameIntent.putExtra("USER_ID", userid);
        }


        createNameIntent.putExtra("questionArray", questionArray);
        createNameIntent.putExtra("answerArray", answerArray);
        createNameIntent.putExtra("resultArray", resultArray);
        createNameIntent.putExtra("resultsMap", resultMap);
        startActivity(createNameIntent);
    }

    private void launchQuestionActivity(){
        Intent createQuestionIntent = new Intent(this, CreateQuestionActivity.class);
        createQuestionIntent.putExtra("quizName", quizName);
        createQuestionIntent.putExtra("questionArray", questionArray);
        createQuestionIntent.putExtra("answerArray", answerArray);
        createQuestionIntent.putExtra("resultArray", resultArray);
        createQuestionIntent.putExtra("resultsMap", resultMap);
        startActivity(createQuestionIntent);
    }

    private void launchResultsActivity(){
        Intent createResultIntent = new Intent(this, CreateResultsActivity.class);
        createResultIntent.putExtra("resultArray", resultArray);
        createResultIntent.putExtra("quizName", quizName);
        createResultIntent.putExtra("questionArray", questionArray);
        createResultIntent.putExtra("answerArray", answerArray);
        createResultIntent.putExtra("resultsMap", resultMap);
        startActivity(createResultIntent);
    }

    private void launchAnswerActivity(){
        Intent createAnswerIntent = new Intent(this, CreateAnswersActivity.class);
        createAnswerIntent.putExtra("answerArray", answerArray);
        createAnswerIntent.putExtra("resultArray", resultArray);
        createAnswerIntent.putExtra("resultsMap", resultMap);
        createAnswerIntent.putExtra("quizName", quizName);
        createAnswerIntent.putExtra("questionArray", questionArray);
        startActivity(createAnswerIntent);
    }

    private void launchProfileActivity(){

    }

    private void enterIntoDB(){

    }
}