package com.example.jackadkins.whosit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity
{
    private TextView quizNameTextView;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        quizNameTextView = (TextView) findViewById(R.id.quiz_name_tv);
        resultTextView = (TextView) findViewById(R.id.result_text_view);


        Intent intent = getIntent();
        String result = intent.getStringExtra("RESULT");
        String quizName = intent.getStringExtra("QUIZNAME");

        quizNameTextView.setText(quizName);
        resultTextView.setText(result);


    }
}
