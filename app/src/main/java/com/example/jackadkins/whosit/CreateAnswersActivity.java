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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CreateAnswersActivity extends AppCompatActivity {

    private String[] answersArray = new String[80];
    private int currentQuestion = 0;
    private int mcurrentAnswer = 0;
    private boolean loadData = false;
    private Spinner mSpinner;
    int spinnerPosition = 0;
    private TextView enterAnswerTextView;
    private EditText answer1EditText;
    private EditText answer2EditText;
    private EditText answer3EditText;
    private EditText answer4EditText;
    private Button nextButton;
    private Button backButton;
    private Button doneButton;
    private String[] resultsArray = new String[8];
    private ButtonListener mButtonListener = new ButtonListener();
    private List<String> categories = new ArrayList<String>();

    class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.nextButtonAnswers:
                    //Enter action here
                    break;

                case R.id.backButtonAnswers:
                    //Enter action here
                    break;

                //go back to CreateQuizActivity
                case R.id.doneButtonAnswers:

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
        answer2EditText = (EditText) findViewById(R.id.a2);
        nextButton = (Button) findViewById(R.id.nextButtonAnswers);
        backButton = (Button) findViewById(R.id.backButtonAnswers);
        doneButton = (Button) findViewById(R.id.doneButtonAnswers);
        mSpinner = (Spinner) findViewById(R.id.spinner);
        //mSpinner.setOnItemSelectedListener(this);
        setUpSpinnerList();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(dataAdapter);
        mSpinner.setSelection(spinnerPosition);

        nextButton.setOnClickListener(mButtonListener);
        backButton.setOnClickListener(mButtonListener);
        doneButton.setOnClickListener(mButtonListener);

    }


    public void onItemSelected(AdapterView<?> parent, View v, int position, long id){
        //handle spinner code here

    }


    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    private void setUpSpinnerList(){
        if(loadData){
            for(int i = 0; i < resultsArray.length; i++){
                categories.add(resultsArray[i]);
            }
        }else {
            fillWithDummyData();
        }
    }

    private void fillWithDummyData(){
        for(int i = 0; i < resultsArray.length; i++){
            resultsArray[i] = ("" + i);
            categories.add(resultsArray[i]);

        }
    }

}
