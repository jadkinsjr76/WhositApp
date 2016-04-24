package com.example.jackadkins.whosit;

import android.content.Context;
import android.content.Intent;
import android.drm.DrmStore;

//import android.drm.DrmStore.Action;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class ProfileActivity extends AppCompatActivity implements OnClickListener {


    // Define variables for the widgets:
    private ListView profileQuizzesListView;
    private int userID;
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

        // DEMO INITIALIZATIONS:
        userID = 1;
        db = new WhosItDB(this);

        setContentView(R.layout.activity_profile);

        // Get references to the widgets:
        profileQuizzesListView = (ListView) findViewById(R.id.profile_quizzes_listView);


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
        TextView text = (TextView) v;
        String viewText = text.getText().toString();
        int index = Integer.parseInt(viewText.split(" ")[0]);
        int quizID = quizIDs.get(index);

        // Start the new activity:
        Intent intent = new Intent(this, TakeQuizActivity.class);
        intent.putExtra("QUIZ_ID", quizID);

        startActivity(intent);
    }
}
