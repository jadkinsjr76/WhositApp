package com.example.jackadkins.whosit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class TakeQuizActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);

        getSupportActionBar().hide();
    }
}
