package com.example.jackadkins.whosit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements OnClickListener {

    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    Button registerButton;
    TextView incorrect;
    WhosItDB db = new WhosItDB(this);
    User user;


    @Override
    public void onClick(View v)
    {
        switch(v.getId()) {
            case R.id.registerButton:
                String launcher = "com.example.jackadkins.whosit.RegisterActivity";
                Intent i = null;
                    i = new Intent(this, RegisterActivity.class);

                startActivity(i);
                break;
            case R.id.loginButton:
                Log.d("LoginActivity", "Login Button Clicked");
                if(inDB(usernameEditText.getText().toString(), passwordEditText.getText().toString())){
                    Intent j = new Intent(this, ProfileActivity.class);
                    j.putExtra("userId", user.getId());
                    startActivity(j);
                }else{
                    incorrect.setVisibility(View.VISIBLE);
                }
                break;
        }

    }

    private boolean inDB(String userName, String password) {
        Log.d("LoginActivity", "inDB implemented");
        user = db.getUser(userName, password);
        if(user != null){
            Log.d("LoginActivity", "user found");
            return true;
        } else{
            Log.d("LoginActivity", "user not found");
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (Button) findViewById(R.id.registerButton);
        incorrect = (TextView) findViewById(R.id.incorrectTextView);

        registerButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
    }
}
