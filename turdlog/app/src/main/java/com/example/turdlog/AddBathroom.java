
package com.example.turdlog;


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
    private EditText editTextDate;
    private EditText editTextAddress;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bathroom);

        db = FirebaseFirestore.getInstance();

        editTextName = findViewById(R.id.brname);
        editTextDate = findViewById(R.id.add_brdatedate);
        editTextAddress = findViewById(R.id.address);

        findViewById(R.id.btn_save).setOnClickListener(this);
    }

    private boolean validateInputs(String name, String Date, String Address) {
        if (name.isEmpty()) {
            editTextName.setError("Name required");
            editTextName.requestFocus();
            return true;
        }

        if (Date.isEmpty()) {
            editTextDate.setError("Brand required");
            editTextDate.requestFocus();
            return true;
        }

        if (Address.isEmpty()) {
            editTextAddress.setError("Description required");
            editTextAddress.requestFocus();
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {

        String name = editTextName.getText().toString().trim();
        String date = editTextDate.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();


        if (!validateInputs(name, date, address)) {

            CollectionReference dbProducts = db.collection("Bathrooms");

            Bathroom br = new Bathroom(
                    name,
                    date,
                    address
            );

            dbProducts.add(br)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(AddBathroom.this, "Product Added", Toast.LENGTH_LONG).show();
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