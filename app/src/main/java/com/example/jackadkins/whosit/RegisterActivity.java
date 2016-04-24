package com.example.jackadkins.whosit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText userName;
    EditText password;
    EditText confirmPassword;
    Button register;
    TextView error;
    User user;
    WhosItDB db = new WhosItDB(this);

    @Override
    public void onClick(View v){
        if(password.getText().toString().equals(confirmPassword.getText().toString())){
            if(inDB(userName.getText().toString())){
                error.setText("That username is taken");
                error.setVisibility(View.VISIBLE);
            } else{
                user = new User(userName.getText().toString(), password.getText().toString());
                user.setId((int) db.insertUser(user));
            }
        } else{
            error.setText("Passwords do not match");
            error.setVisibility(View.VISIBLE);
        }
    }

    private boolean inDB(String uN) {
        user = db.getUser(uN);
        if(user != null){
            return true;
        } else{
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = (EditText) findViewById(R.id.rUsernameEditText);
        password = (EditText) findViewById(R.id.rPasswordEditText);
        confirmPassword = (EditText) findViewById(R.id.rConfirmPasswordEditText);
        register = (Button) findViewById(R.id.rRegisterButton);
        error = (TextView) findViewById(R.id.rErrorTextView);

        register.setOnClickListener(this);
    }
}
