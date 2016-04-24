package com.example.jackadkins.whosit;

import android.content.Context;
import android.content.Intent;
import android.drm.DrmStore;

//import android.drm.DrmStore.Action;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {


    // Define variables for the widgets:
    private ListView profileQuizzesListView;
    private TextView usernameTextView;
    private int      userID;
    private String   usernameString;
    private WhosItDB db;

    // Define additional variables for the class:
    private ArrayAdapter<String> listAdapter;
<<<<<<< Updated upstream
    private Context context;


    ArrayList<String>  quizList;
    ArrayList<Integer> quizIDs;
    ArrayList<Integer> viewIDs;
    ArrayList<Quiz>    quizzes;

=======
<<<<<<< HEAD
    private ArrayList<String>  quizList;
    private ArrayList<Integer> quizIDs;
    private ArrayList<Quiz>    quizzes;

    /**
     * This initializes and creates the instance of the profile activity.
     *
     * @param savedInstanceState
     */
=======
    private Context context;


    ArrayList<String>  quizList;
    ArrayList<Integer> quizIDs;
    ArrayList<Integer> viewIDs;
    ArrayList<Quiz>    quizzes;

>>>>>>> origin/master
>>>>>>> Stashed changes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the intent which has started the activity:
        Intent intent = getIntent();

        // Initialize the user's values using intents:
        userID = intent.getIntExtra("userId", 0);
        usernameString = intent.getStringExtra("username");

        // Start up the database:
        db = new WhosItDB(this);

        // Set the content view to ProfileActivity:
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
<<<<<<< Updated upstream
=======
<<<<<<< HEAD
        quizList = new ArrayList<String>();
        quizIDs  = new ArrayList<Integer>();
        quizzes  = db.getQuizzes(userID);
=======
>>>>>>> Stashed changes
        ArrayList<String>  quizList = new ArrayList<String>();
        ArrayList<Integer> quizIDs = new ArrayList<Integer>();
        ArrayList<Integer> viewIDs = new ArrayList<Integer>();
        ArrayList<Quiz>    quizzes = db.getQuizzes(userID);

<<<<<<< Updated upstream
=======
>>>>>>> origin/master
>>>>>>> Stashed changes

        for (int i = 0; i < quizzes.size(); i++) {
            quizList.add("1 - " + quizzes.get(i).getName());
            quizIDs.add(quizzes.get(i).getQuizID());
        }

        // Create ArrayAdapter using the planet list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, quizList);

        // Set the ArrayAdapter as the ListView's adapter.
        profileQuizzesListView.setAdapter(listAdapter);
<<<<<<< Updated upstream

=======
<<<<<<< HEAD
        profileQuizzesListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Put extra data and then start activity:
                Intent intent = new Intent(getApplicationContext(), TakeQuizActivity.class);
                intent.putExtra("QUIZ_ID", quizIDs.get(position));

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {}

                startActivity(intent);
            }
        });
=======

>>>>>>> origin/master
>>>>>>> Stashed changes
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

<<<<<<< HEAD
=======
    @Override
    public void onClick(View v) {
        // Declare and initialize variables:
        TextView text = (TextView) v;
        String viewText = text.getText().toString();
        int index = Integer.parseInt(viewText.split(" ")[0]);
        int quizID = quizIDs.get(index);

        // Debug:
        System.out.println("DEBUG - quizID: " + quizID);

        // Start the new activity:
        Intent intent = new Intent(this, TakeQuizActivity.class);
        intent.putExtra("QUIZ_ID", quizID);
        startActivity(intent);
    }

>>>>>>> origin/master
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
<<<<<<< Updated upstream
=======
<<<<<<< HEAD
        // Variable initialization:
        Intent intent;

        // Switch statement to handle cases:
=======
>>>>>>> origin/master
>>>>>>> Stashed changes
        switch (item.getItemId()) {
            case R.id.action_addQuiz:
                Intent intent = new Intent(getApplicationContext(), CreateQuizActivity.class);
                intent.putExtra("USER_ID", userID);
                startActivity(intent);
                break;
<<<<<<< Updated upstream
=======
<<<<<<< HEAD

            case R.id.action_logout:
                intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
=======
>>>>>>> Stashed changes
            default:
>>>>>>> origin/master
                break;
        }
        return true;
    }
}
