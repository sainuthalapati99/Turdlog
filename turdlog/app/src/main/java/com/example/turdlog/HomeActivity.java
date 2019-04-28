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
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity implements OnMapReadyCallback {

    private FirebaseAuth firebaseAuth;
    private NavigationView navView;
    private DrawerLayout dLayout;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setNavigationDrawer();
        getSupportActionBar().hide();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // initiate a DrawerLayout
        dLayout = (DrawerLayout) findViewById(R.id.Drawer_Layout);
        //Button to open drawer
        final ImageButton btnOpenDrawer = (ImageButton) findViewById(R.id.drawerButton);
        btnOpenDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dLayout.openDrawer(Gravity.LEFT);
            }
        });


        //FireBase
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();


       // welcomeGreeting.setText("Welcome " + user.getEmail());

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Add marker in aderhold and move camera
        LatLng aderhold = new LatLng(33.7560837, -84.3892877);
        mMap.addMarker(new MarkerOptions().position(aderhold).title("Aderhold Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(aderhold));
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
}
