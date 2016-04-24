package com.example.jackadkins.whosit;

//enter activity
//retrieve result data
//if no result data, make user go back to enter results
//otherwise display result data
//


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CreateAnswersActivity extends AppCompatActivity {

    private String[] answersArray = new String[80];
    private int mcurrentAnswer = 0;
    private int mcurrentQuestion = 0;
    private Spinner mSpinner;
    int spinnerPosition = 0;
    private TextView enterAnswerTextView;
    private EditText enterAnswerEditText;
    private Button nextButton;
    private Button backButton;
    private Button doneButton;
    private String quizName;
    private String[] questionArray = new String[20];
    private String[] resultsArray = new String[8];
    private String[] resultsArrayMap = new String[80];
    private ButtonListener mButtonListener = new ButtonListener();
    private List<String> categories = new ArrayList<String>();

    class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.nextButtonAnswers:
                    //Enter action here
                    if(resultsArray == null || answersArray == null){break;}
                    enterAnswerResultPair();
                    if(mcurrentAnswer == 79){break;}
                    mcurrentAnswer++;
                    if(answersArray[mcurrentAnswer].equals("") || answersArray[mcurrentAnswer].equals(" ")){
                        if(mcurrentAnswer%4 == 0){
                            mcurrentQuestion++;
                            enterAnswerEditText.setText("Enter Answer A for Question " + (mcurrentQuestion + 1) + ".");
                        }else if(mcurrentAnswer%4 == 1){
                            enterAnswerEditText.setText("Enter Answer B for Question " + (mcurrentQuestion + 1) + ".");
                        }else if(mcurrentAnswer%4 == 2){
                            enterAnswerEditText.setText("Enter Answer C for Question " + (mcurrentQuestion + 1) + ".");
                        }else{
                            enterAnswerEditText.setText("Enter Answer D for Question " + (mcurrentQuestion + 1) + ".");
                        }
                    }else{
                        enterAnswerEditText.setText(answersArray[mcurrentAnswer]);
                    }
                    break;

                case R.id.backButtonAnswers:
                    //Enter action here
                    if(resultsArray == null || answersArray == null){break;}
                    enterAnswerResultPair();
                    if(mcurrentAnswer == 0){break;}
                    mcurrentAnswer--;
                    enterAnswerEditText.setText(answersArray[mcurrentAnswer]);
                    break;

                //go back to CreateQuizActivity
                case R.id.doneButtonAnswers:
                    launchActivity();
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_answers);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        enterAnswerTextView = (TextView) findViewById(R.id.enterAnswersTextView);
        enterAnswerEditText = (EditText) findViewById(R.id.a2);
        nextButton = (Button) findViewById(R.id.nextButtonAnswers);
        backButton = (Button) findViewById(R.id.backButtonAnswers);
        doneButton = (Button) findViewById(R.id.doneButtonAnswers);
        mSpinner = (Spinner) findViewById(R.id.spinner);

        quizName = getIntent().getStringExtra("quizName");
        questionArray = getIntent().getStringArrayExtra("questionArray");
        answersArray = getIntent().getStringArrayExtra("answerArray");
        resultsArrayMap = getIntent().getStringArrayExtra("resultsMap");
        resultsArray = getIntent().getStringArrayExtra("resultArray");

        if(answersArray == null){
            answersArray = new String[80];
            for(int i = 0; i < answersArray.length; i++){
                answersArray[i] = " ";
            }
        }

        setUpSpinnerList();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(dataAdapter);
        mSpinner.setSelection(spinnerPosition);

        nextButton.setOnClickListener(mButtonListener);
        backButton.setOnClickListener(mButtonListener);
        doneButton.setOnClickListener(mButtonListener);

        enterAnswerEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    enterAnswerResultPair();
                }
                return false;

            }
        });

        enterAnswerEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_ENTER:
                    case KeyEvent.KEYCODE_DPAD_CENTER:

                        enterAnswerResultPair();

                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(enterAnswerEditText.getWindowToken(), 0);
                        return true;

                }
                return false;
            }
        });

    }

    private void enterAnswerResultPair(){
        if(answersArray!= null && resultsArrayMap!= null){
            answersArray[mcurrentAnswer] = enterAnswerEditText.getText().toString();
            resultsArrayMap[mcurrentAnswer] = mSpinner.getSelectedItem().toString();
        }

    }


    public void onItemSelected(AdapterView<?> parent, View v, int position, long id){
        //handle spinner code here

    }


    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    private void setUpSpinnerList(){
        if(resultsArray != null){
            for(int i = 0; i < resultsArray.length; i++){
                categories.add(resultsArray[i]);
            }
        }else{
            Toast.makeText(CreateAnswersActivity.this, "Please go back and enter your Results first.", Toast.LENGTH_SHORT).show();
        }
    }

    private void launchActivity(){
        Intent createQuizIntent = new Intent(this, CreateQuizActivity.class);
        createQuizIntent.putExtra("answerArray", answersArray);
        createQuizIntent.putExtra("resultsMap", resultsArrayMap);
        createQuizIntent.putExtra("quizName", quizName);
        createQuizIntent.putExtra("questionArray", questionArray);
        createQuizIntent.putExtra("resultArray", resultsArray);
        startActivity(createQuizIntent);
    }

}
