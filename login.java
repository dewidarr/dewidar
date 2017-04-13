package com.example.monko.foreach;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class login extends AppCompatActivity  implements View.OnClickListener{
    private Button login;
    private EditText useremail;
    private EditText pass;
    private TextView regis;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null) {
            //start profile activity
            finish();
            startActivity(new Intent(getApplicationContext(),profile.class));
        }
        progressDialog=new ProgressDialog(this);
        login = (Button)findViewById(R.id.login);
        useremail = (EditText)findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.password);
        regis = (TextView)findViewById(R.id.textViewSignin);

        login.setOnClickListener(this);
        regis.setOnClickListener(this);

    }
    private void userlogin()
    {
        String email = useremail.getText().toString().trim();
        String password = pass.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            //email is empaty
            Toast.makeText(this,"please enter email",Toast.LENGTH_SHORT).show();
            //stop function excution
            return;
        }
        if(TextUtils.isEmpty(password)){
            //password is empity
            Toast.makeText(this,"please enter password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering User...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()) {
                    //start the profile activity
                    finish();
                    startActivity(new Intent(getApplicationContext(), profile.class));

                }else {
                    Toast.makeText(getApplicationContext(),"login error",Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();

            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v == login)
            userlogin();

        if (v==regis)
        {
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}
