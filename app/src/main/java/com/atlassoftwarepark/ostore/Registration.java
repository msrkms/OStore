package com.atlassoftwarepark.ostore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.atlassoftwarepark.ostore.BackEnd.AllUrls;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {

    TextView goLoginPage;
    private TextInputEditText textInputEditTextFirstName,textInputEditTextLastName,textInputEditTextPhoneNumber,textInputEditTextPassword,textInputEditTextConfirmPassword;
    private MaterialButton materialButtonRegistration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        goLoginPage=(TextView)findViewById(R.id.gotoLoginPage);
        textInputEditTextFirstName=(TextInputEditText) findViewById(R.id.textInputEditTextRegFirstName);
        textInputEditTextLastName=(TextInputEditText) findViewById(R.id.textInputEditTextRegLastName);
        textInputEditTextPhoneNumber=(TextInputEditText) findViewById(R.id.textInputEditTextRegPhone);
        textInputEditTextPassword=(TextInputEditText) findViewById(R.id.textInputEditTextRegPassword);
        textInputEditTextConfirmPassword=(TextInputEditText) findViewById(R.id.textInputEditTextRegPasswordConfirm);
        materialButtonRegistration=(MaterialButton) findViewById(R.id.registrationButton);


        goLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this,LogIn.class));
                finish();
            }
        });


        materialButtonRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String F_Name=textInputEditTextFirstName.getText().toString();
                String L_Name=textInputEditTextLastName.getText().toString();
                String PhoneNumber=textInputEditTextPhoneNumber.getText().toString();
                String Password=textInputEditTextPassword.getText().toString();
                String PasswordConfirm=textInputEditTextConfirmPassword.getText().toString();


                Registration(F_Name,L_Name,PhoneNumber,Password,PasswordConfirm);
            }
        });


    }




    private void Registration(final String F_Name, final String L_Name, final String PhoneNumber, final String Password, final String PasswordConfirm){

        final RequestQueue registrationQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AllUrls.Registraion,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int data=Character.getNumericValue(response.charAt(1));
                        if(data==0){
                            new MaterialAlertDialogBuilder(Registration.this)
                                    .setTitle("Warning")
                                    .setMessage("Already  Registered")
                                    .setNegativeButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    })
                                    .show();

                        }else if(data==1){
                            new MaterialAlertDialogBuilder(Registration.this)
                                    .setTitle("Registration Successful")
                                    .setMessage("Thank you for be a part of out family")
                                    .setNegativeButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    })
                                    .show();

                        } else if(data==2){
                            new MaterialAlertDialogBuilder(Registration.this)
                                    .setTitle("Warning")
                                    .setMessage("Password not matched")
                                    .setNegativeButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    })
                                    .show();
                            }else{
                                    new MaterialAlertDialogBuilder(Registration.this)
                                            .setTitle("Warning")
                                             .setMessage("Server Down")
                                            .setNegativeButton("ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {

                                                }
                                            }).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("frist_name",F_Name);
                params.put("last_name",L_Name);
                params.put("number",PhoneNumber);
                params.put("password",Password);
                params.put("password_confirm",PasswordConfirm);
                return params;
            }
        };

        registrationQueue.add(stringRequest);

    }
}