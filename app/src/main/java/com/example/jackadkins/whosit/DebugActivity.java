package com.example.jackadkins.whosit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DebugActivity extends AppCompatActivity implements OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
        Button launch = (Button) findViewById(R.id.btnLaunch);
        launch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        TextView activity = (TextView) findViewById(R.id.editActivity);
        Class cActivity = null;
        String launcher = "com.example.jackadkins.whosit." + activity.getText();
        Intent i = null;
        try
        {
            i = new Intent(getApplicationContext(), Class.forName(launcher));
        }
        catch (ClassNotFoundException e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            return;
        }
        startActivity(i);
    }
}
