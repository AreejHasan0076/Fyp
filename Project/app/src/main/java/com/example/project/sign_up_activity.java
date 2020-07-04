package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class sign_up_activity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText signupScreenEmail;
    private EditText signupScreenName;
    private EditText signupScreenPassword;
    private EditText signupScreenConfirmPassword;

    // private Button SGS_haveAcc;
    //private TextView emailText, passwordText, nameText, confirmPasswordText,phoneNumberText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_screen);
        signupScreenName=(EditText)findViewById(R.id.SGS_ET_PersonName);
        signupScreenEmail=(EditText)findViewById(R.id.SGS_ET_email);
        signupScreenPassword=(EditText)findViewById(R.id.SGS_ET_password);
        signupScreenConfirmPassword=(EditText)findViewById(R.id.SGS_ET_ConfirmPassword);
        //private EditText signUpScreenPhoneNumber;
        Button signupButton = (Button) findViewById(R.id.SGS_signupButton);
        mAuth= FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()!=null){
            Toast.makeText(sign_up_activity.this, "Account Already Created", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(),login_activity.class));
        }



        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=signupScreenName.getText().toString().trim();
                String email=signupScreenEmail.getText().toString().trim();
                String password=signupScreenPassword.getText().toString().trim();
                String confirmPassword=signupScreenConfirmPassword.getText().toString().trim();
                //String phoneNumber=signUpScreenPhoneNumber.getText().toString().trim();
                if(TextUtils.isEmpty(name)){
                    signupScreenName.setError("Name is required");
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    signupScreenEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    signupScreenPassword.setError("Password is required");
                    return;
                }
                if(TextUtils.isEmpty(confirmPassword)){
                    signupScreenConfirmPassword.setError("Field is Empty");
                    return;
                }
//                if(TextUtils.isEmpty(phoneNumber)){
//                    signUpScreenPhoneNumber.setError("Number is required");
//                    return;
//                }
                if(password.length()>7){
                    signupScreenPassword.setError("Length > 7");
                    return;
                }
                if(confirmPassword.length()>7){
                    signupScreenConfirmPassword.setError("Length > 7");
                    return;
                }
//                if(!password.equals(confirmPassword)){
//                    signupScreenConfirmPassword.setError("Password doesn't match");
//                }
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(sign_up_activity.this, "Account Created", Toast.LENGTH_SHORT).show();


                            startActivity(new Intent(getApplicationContext(),home_activity.class));
                        }
                        else
                            Toast.makeText(sign_up_activity.this, "Error" , Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}