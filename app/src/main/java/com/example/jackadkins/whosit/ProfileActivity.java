package com.example.jackadkins.whosit;

import android.content.Context;
import android.content.Intent;
import android.drm.DrmStore;

//import android.drm.DrmStore.Action;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< Updated upstream
=======
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
>>>>>>> Stashed changes
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
<<<<<<< Updated upstream
    private Context context;


    ArrayList<String>  quizList;
    ArrayList<Integer> quizIDs;
    ArrayList<Integer> viewIDs;
    ArrayList<Quiz>    quizzes;

=======
    private ArrayList<String>  quizList;
    private ArrayList<Integer> quizIDs;
    private ArrayList<Quiz>    quizzes;

    /**
     * This initializes and creates the instance of the profile activity.
     *
     * @param savedInstanceState
     */
>>>>>>> Stashed changes
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

        // Create and populate a List of planet names.
<<<<<<< Updated upstream
        ArrayList<String>  quizList = new ArrayList<String>();
        ArrayList<Integer> quizIDs = new ArrayList<Integer>();
        ArrayList<Integer> viewIDs = new ArrayList<Integer>();
        ArrayList<Quiz>    quizzes = db.getQuizzes(userID);

=======
        quizList = new ArrayList<String>();
        quizIDs  = new ArrayList<Integer>();
        quizzes  = db.getQuizzes(userID);
>>>>>>> Stashed changes

        // Iterate through 'quizzes' and add each to the profile:
        for (int i = 0; i < quizzes.size(); i++) {
            quizList.add(quizzes.get(i).getName());
            quizIDs.add(quizzes.get(i).getQuizID());
        }

        // Create ArrayAdapter using the planet list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, quizList);

        // Set the ArrayAdapter as the ListView's adapter, and add the respective listeners:
        profileQuizzesListView.setAdapter(listAdapter);
<<<<<<< Updated upstream

=======
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

<<<<<<< Updated upstream
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
=======
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

        // Switch statement to handle cases:
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
        }
        return true;
>>>>>>> Stashed changes
    }
}
