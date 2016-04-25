package com.example.jackadkins.whosit;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateQuestionActivity extends AppCompatActivity {

    class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.nextButton:
                    enterQuestion();
                    if(currentQuestion == 19){
                        break;
                    }
                    currentQuestion++;
                    if(myStringArray[currentQuestion] == null || myStringArray[currentQuestion].equals(" ")){
                        questionEditText.setText("Enter Question" + " " + (currentQuestion + 1));
                    }else{
                        questionEditText.setText(myStringArray[currentQuestion]);
                    }

                    break;

                case R.id.backButton:
                    if(currentQuestion == 0){
                        break;
                    }
                    currentQuestion--;
                    questionEditText.setText(myStringArray[currentQuestion]);
                    break;
                //go back to CreateQuizActivity
                case R.id.doneButton:
                    launchCreateQuizAct();
                    break;
            }
        }
    }

    private String[] myStringArray = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
    private String[] questionArray = new String[20];
    private int quizid = -1;
    private int questionID = -1;
    private int currentQuestion = 0;
    private ArrayList<Integer> questionIDArrayList = new ArrayList<>();
    private EditText questionEditText;
    private Button nextButton;
    private Button backButton;
    private Button doneButton;
    private ButtonListener mButtonListener = new ButtonListener();
    private WhosItDB db = new WhosItDB(this);

    private int userid = -1;
    private String usernameString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        questionEditText = (EditText) findViewById(R.id.q1);
        nextButton = (Button) findViewById(R.id.nextButton);
        backButton = (Button) findViewById(R.id.backButton);
        doneButton = (Button) findViewById(R.id.doneButton);

        nextButton.setOnClickListener(mButtonListener);
        backButton.setOnClickListener(mButtonListener);
        doneButton.setOnClickListener(mButtonListener);

        questionArray = getIntent().getStringArrayExtra("questionArray");

        quizid = getIntent().getIntExtra("QUIZ_ID", -1);
        userid = getIntent().getIntExtra("USER_ID", -1);
        usernameString = getIntent().getStringExtra("USER_NAME");

        if(questionArray != null){
            myStringArray = questionArray;
        }

        questionEditText.setOnEditorActionListener(new OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    //enterQuestion();
                }
                return false;

            }
        });

        questionEditText.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_ENTER:
                    case KeyEvent.KEYCODE_DPAD_CENTER:

                       // enterQuestion();

                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(questionEditText.getWindowToken(), 0);
                        return true;

                }
                return false;
            }
        });
    }

    private void enterQuestion(){
        myStringArray[currentQuestion] = questionEditText.getText().toString();
        if(quizid != -1){
            Question question = new Question(myStringArray[currentQuestion]);
            question.setQuizID(quizid);
            question.setQuestionID((int)db.insertQuestion(question));
            questionID = question.getQuestionID();
            questionIDArrayList.add(questionID);
        }
    }

    private void launchCreateQuizAct(){
        Intent createQuizIntent = new Intent(this, CreateQuizActivity.class);
        createQuizIntent.putExtra("questionArray", myStringArray);
        createQuizIntent.putIntegerArrayListExtra("QUESTION_IDS", questionIDArrayList);
        createQuizIntent.putExtra("USER_NAME", usernameString);
        createQuizIntent.putExtra("USER_ID", userid);
        startActivity(createQuizIntent);
    }
}
