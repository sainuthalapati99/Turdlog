
package com.example.turdlog;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.turdlog.Bathroom;
import com.example.turdlog.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddBathroom extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName;
    private EditText editTextAddress;
    private double lat, lang;
    private int rating;
    private String name, r;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bathroom);
        getSupportActionBar().hide();

        Intent intent = getIntent();

        lat = intent.getDoubleExtra("EXTRA_LAT", 0.0);
        lang = intent.getDoubleExtra("EXTRA_LON", 0.0);


        db = FirebaseFirestore.getInstance();

        editTextName = findViewById(R.id.brname);
        editTextAddress = findViewById(R.id.address);

        findViewById(R.id.btn_save).setOnClickListener(this);
    }

    private boolean validateInputs(String name, int rating) {
        if (name.isEmpty()) {
            editTextName.setError("Name required");
            editTextName.requestFocus();
            return true;
        }

        if (rating > 5 || rating < 0){
            editTextAddress.setError("Number 1-5");
            editTextAddress.requestFocus();
            return true;
        }

        return false;
    }

    @Override
    public void onClick(View v) {

        name = editTextName.getText().toString().trim();
        r = editTextAddress.getText().toString().trim();

        rating = Integer.parseInt(r);


        if (!validateInputs(name, rating)) {

            CollectionReference dbProducts = db.collection("Bathrooms");

            Bathroom br = new Bathroom(
                    name,
                    rating,
                    lat,
                    lang
            );

            dbProducts.add(br)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(AddBathroom.this, "Bathroom Added", Toast.LENGTH_LONG).show();
                            Intent intent = getIntent();
                            intent.putExtra("EXTRA_NAME", name);
                            intent.putExtra("EXTRA_RATING", r);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddBathroom.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

        }
    }
}