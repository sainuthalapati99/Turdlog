package com.example.turdlog;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private TextView welcomeGreeting;
    private Button buttonLogout;
    private Button buttonMap, btnAdd;
    private NavigationView navView;
    private DrawerLayout dLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setNavigationDrawer();
        //getSupportActionBar().hide();

        dLayout = (DrawerLayout) findViewById(R.id.Drawer_Layout); // initiate a DrawerLayout
        //Button to open drawer
        final ImageButton btnOpenDrawer = (ImageButton) findViewById(R.id.drawerButton);
        btnOpenDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dLayout.openDrawer(Gravity.LEFT);
            }
        });



        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        welcomeGreeting = (TextView) findViewById(R.id.welcomeGreeting);

        welcomeGreeting.setText("Welcome " + user.getEmail());

        //buttonMap = (Button) findViewById(R.id.buttonMap);
        //btnAdd = (Button) findViewById(R.id.buttonAdd);

        //buttonLogout = (Button) findViewById(R.id.buttonLogout);

        //buttonLogout.setOnClickListener(this);
        //buttonMap.setOnClickListener(this);
        //btnAdd.setOnClickListener(this);
    }

    //This method will set the button action for drawer menu items
    private void setNavigationDrawer() {
        navView = (NavigationView) findViewById(R.id.navigation);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id){
                    case R.id.Logout: //Logs out user on menu click
                        firebaseAuth.signOut();
                        finish();
                        Intent newAct = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(newAct);
                        break;
                    case R.id.addBathroom:

                        break;
                }

                return false;
            }
        });


    }


    /*
    @Override
    public void onClick(View View) {
        if (View == buttonMap) {

            startActivity(new Intent(this, MapsActivity.class));
        }
        if (View == buttonLogout) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        if (View == btnAdd) {
            startActivity(new Intent(this, AddBathroom.class));
        }
    }
    */
}
