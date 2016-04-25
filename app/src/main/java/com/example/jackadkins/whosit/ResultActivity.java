package com.example.jackadkins.whosit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity
{
    private TextView quizNameTextView;
    private TextView resultTextView;

    private int    userID;
    private String usernameString;

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
        userID = intent.getIntExtra("USER_ID", -1);
        usernameString = intent.getStringExtra("USER_NAME");

        quizNameTextView.setText(quizName);
        resultTextView.setText(result);

    }

    /**
     * This overridden method will handle and display the menu for the Profile Activity.
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_result, menu);
        return true;
    }

    /**
     * This method will handle when something in the menu is clicked.
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Variable initialization:
        Intent intent;

        // Switch statement to handle cases:
        switch (item.getItemId()) {
            case R.id.action_backToProfile:
                intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.putExtra("USER_ID", userID);
                intent.putExtra("USER_NAME", usernameString);
                startActivity(intent);
        }
        return true;
    }
}
