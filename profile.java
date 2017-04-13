package com.example.monko.foreach;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class profile extends AppCompatActivity implements View.OnClickListener {


    private Button lgo;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        lgo = (Button)findViewById(R.id.logout);

        if(firebaseAuth.getCurrentUser() == null)
        {
            finish();
            startActivity(new Intent(this, login.class));
        }


        lgo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == lgo)
        {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, login.class));
        }
    }
}
