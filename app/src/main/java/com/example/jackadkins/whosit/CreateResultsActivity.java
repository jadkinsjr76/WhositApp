package com.example.jackadkins.whosit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateResultsActivity extends AppCompatActivity {

    private Button nextButton;
    private Button backButton;
    private Button doneButton;
    private String[] resultsArray = new String[8];
    private int mcurrentResult = 0;
    private EditText resultsEditText;
    private ButtonListener mButtonListener = new ButtonListener();

    class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.nextButtonResults:
                    //Enter action here
                    enterResult();
                    if(mcurrentResult == 7){break;}
                    mcurrentResult++;
                    setUpDisplay();
                    break;

                case R.id.backButtonResults:
                    //Enter action here
                    enterResult();
                    if(mcurrentResult == 0){break;}
                    mcurrentResult--;
                    setUpDisplay();
                    break;

                //go back to CreateQuizActivity
                case R.id.doneButtonResults:
                    launchCreateQuizAct();
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_results);

        for(int i = 0; i < resultsArray.length; i++){
            resultsArray[i] = " ";
        }

        nextButton = (Button) findViewById(R.id.nextButtonResults);
        backButton = (Button) findViewById(R.id.backButtonResults);
        doneButton = (Button) findViewById(R.id.doneButtonResults);
        resultsEditText = (EditText) findViewById(R.id.enterResult);

        nextButton.setOnClickListener(mButtonListener);
        backButton.setOnClickListener(mButtonListener);
        doneButton.setOnClickListener(mButtonListener);

        resultsEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    enterResult();
                }
                return false;

            }
        });

       resultsEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_ENTER:
                    case KeyEvent.KEYCODE_DPAD_CENTER:

                        enterResult();

                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(resultsEditText.getWindowToken(), 0);
                        return true;

                }
                return false;
            }
        });

    }

    private void setUpDisplay(){
        if(resultsArray[mcurrentResult].equals(" ")){
            resultsEditText.setText("Result " + (mcurrentResult+1));
        }else{
            resultsEditText.setText(resultsArray[mcurrentResult]);
        }

    }

    private void enterResult(){
        resultsArray[mcurrentResult] = resultsEditText.getText().toString();
    }

    private void launchCreateQuizAct(){
        Intent createQuizIntent = new Intent(this, CreateQuizActivity.class);
        createQuizIntent.putExtra("resultArray", resultsArray);
        startActivity(createQuizIntent);
    }
}
