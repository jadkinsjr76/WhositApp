package com.example.jackadkins.whosit;

import android.content.Context;
import android.content.Intent;
import android.drm.DrmStore;

//import android.drm.DrmStore.Action;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity implements OnClickListener {


    // Define variables for the widgets:
    private ListView profileQuizzesListView;
    private TextView usernameTextView;
    private int      userID;
    private String   usernameString;
    private WhosItDB db;

    // Define additional variables for the class:
    private ArrayAdapter<String> listAdapter;
    private Context context;


    ArrayList<String>  quizList;
    ArrayList<Integer> quizIDs;
    ArrayList<Integer> viewIDs;
    ArrayList<Quiz>    quizzes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the intent which has started the activity:
        Intent intent = getIntent();

        // Initialize the user's values:
        userID = intent.getIntExtra("userId", 0);
        usernameString = intent.getStringExtra("username");
        db = new WhosItDB(this);

        setContentView(R.layout.activity_profile);

        // Get references to the widgets:
        profileQuizzesListView = (ListView) findViewById(R.id.profile_quizzes_listView);
        usernameTextView = (TextView) findViewById(R.id.username_profile_textView);

        // Display the username to the user:
        usernameTextView.setText(usernameString);

        /**
         * The code below is example code on how to add to a ListView... It is NOT permanent code.
         */

        // Create and populate a List of planet names.
        ArrayList<String>  quizList = new ArrayList<String>();
        ArrayList<Integer> quizIDs = new ArrayList<Integer>();
        ArrayList<Integer> viewIDs = new ArrayList<Integer>();
        ArrayList<Quiz>    quizzes = db.getQuizzes(userID);


        for (int i = 0; i < quizzes.size(); i++) {
            quizList.add("1 - " + quizzes.get(i).getName());
            quizIDs.add(quizzes.get(i).getQuizID());
        }

        // Create ArrayAdapter using the planet list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, quizList);

        // Set the ArrayAdapter as the ListView's adapter.
        profileQuizzesListView.setAdapter(listAdapter);

    }

    // TODO: Fill in.
//    @Override
//    public void onStart() {
//        super.onStart();
//    }
//
//    // TODO: Fill in.
//    @Override
//    public void onStop() {
//        super.onStop();
//    }

    @Override
    public void onClick(View v) {
        // Declare and initialize variables:
        Button button = (Button) v;
        String viewText = button.getText().toString();
        int index = Integer.parseInt(viewText.split(" ")[0]) - 1;
        int quizID = quizIDs.get(index);

        // Debug:
        Toast.makeText(ProfileActivity.this, "DEBUG - quizID: " + quizID, Toast.LENGTH_SHORT).show();

        // Start the new activity:
        Intent intent = new Intent(this, TakeQuizActivity.class);
        intent.putExtra("QUIZ_ID", quizID);
        startActivity(intent);
    }

    /**
     * This overridden method will handle and display the menu for the Profile Activity.
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_profile, menu);
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

        switch (item.getItemId()) {
            case R.id.action_addQuiz:
                intent = new Intent(getApplicationContext(), CreateQuizActivity.class);
                intent.putExtra("USER_ID", userID);
                startActivity(intent);
                break;

            case R.id.action_logout:
                intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
        return true;
    }
}
