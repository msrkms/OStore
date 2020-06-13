package com.atlassoftwarepark.ostore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LogIn extends AppCompatActivity {

    TextView  createAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        createAccount=(TextView)findViewById(R.id.createAnAccount);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn.this,Registration.class));
            }
        });
    }
}