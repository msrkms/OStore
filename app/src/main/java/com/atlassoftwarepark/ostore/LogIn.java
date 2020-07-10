package com.atlassoftwarepark.ostore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.atlassoftwarepark.ostore.BackEnd.AllUrls;
import com.atlassoftwarepark.ostore.BackEnd.DataHold;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class LogIn extends AppCompatActivity {

    TextView  createAccount;
    private Button buttonLogin;
    private TextInputEditText textInputEditTextPhoneNumber,textInputEditTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        createAccount=(TextView)findViewById(R.id.createAnAccount);
        buttonLogin=findViewById(R.id.buttonLogin);
        textInputEditTextPhoneNumber=(TextInputEditText) findViewById(R.id.textInputEditTextLoginPhone) ;
        textInputEditTextPassword=(TextInputEditText) findViewById(R.id.textInputEditTextLoginPassword) ;

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn.this,Registration.class));
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*  String PhoneNumber=textInputEditTextPhoneNumber.getText().toString();
               String Password=textInputEditTextPassword.getText().toString();*/

                String PhoneNumber="01700000000";
                String Password="admin123.com";
               Login(PhoneNumber,Password);
            }
        });
    }



    private void Login(final String PhoneNumber, final String Password){

        final RequestQueue LoginQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AllUrls.Login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int data=Character.getNumericValue(response.charAt(1));
                        if(data==1){
                            DataHold.phn=PhoneNumber;
                            startActivity(new Intent(LogIn.this,AdminDashboard.class));
                            LogIn.this.finish();
                        }else if(data==0){
                            new MaterialAlertDialogBuilder(LogIn.this)
                                    .setTitle("Wanring")
                                    .setMessage("Email or Password incorrect")
                                    .setNegativeButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    })
                                    .show();
                        }else{
                            new MaterialAlertDialogBuilder(LogIn.this)
                                    .setTitle("Wanring")
                                    .setMessage("Server Down")
                                    .setNegativeButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    })
                                    .show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("number",PhoneNumber);
                params.put("password",Password);
                return params;
            }
        };

        LoginQueue.add(stringRequest);

    }



}