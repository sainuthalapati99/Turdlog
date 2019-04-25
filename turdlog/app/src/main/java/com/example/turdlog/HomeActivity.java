package com.example.turdlog;

import android.accounts.Account;
import android.accounts.OnAccountsUpdateListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;

    private TextView welcomeGreeting;
    private Button buttonLogout;
    private Button buttonMap, btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        welcomeGreeting = (TextView) findViewById(R.id.welcomeGreeting);

        welcomeGreeting.setText("Welcome "+user.getEmail());

       buttonMap = (Button) findViewById(R.id.buttonMap);
       btnAdd = (Button) findViewById(R.id.buttonAdd);

        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        buttonLogout.setOnClickListener(this);
    }


    @Override
    public void onClick(View View) {
        if (View == buttonMap){

            startActivity(new Intent(this, MapsActivity.class));
        } else if(View == buttonLogout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        if (View == btnAdd) {
            startActivity(new Intent(this, AddBathroom.class));
        }
    }
}
