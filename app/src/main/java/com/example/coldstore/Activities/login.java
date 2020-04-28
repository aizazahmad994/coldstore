package com.example.coldstore.Activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coldstore.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    EditText email;
    EditText password;
    Button show_hide;
    Button signin;
    private FirebaseAuth mAuth;
    String emaildata = "";
    String passwordata = "";
    Button signup;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        DatabaseReference myRef = database.getReference("admin_email");
        email = (EditText) findViewById(R.id.enteremailet);
        password = (EditText) findViewById(R.id.enterpasset);
        show_hide = (Button) findViewById(R.id.showhide);
        signin = (Button) findViewById(R.id.signinb);
        signup = (Button) findViewById(R.id.signup);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                value = dataSnapshot.getValue(String.class);
                Log.d("Yo", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("no yo", "Failed to read value.", error.toException());
            }
        });

        show_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (show_hide.getText().toString().equals("Show")) {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    show_hide.setText("Hide");
                } else {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    show_hide.setText("Show");
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), signup.class);
                startActivity(i);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                emaildata = email.getText().toString();
                passwordata = password.getText().toString();
                if (passwordata.isEmpty() || emaildata.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter all entries", Toast.LENGTH_LONG).show();
                } else {

                    mAuth.signInWithEmailAndPassword(emaildata, passwordata)  //adding in firebase method
                            .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {/// firebase hit hua and user check kiya
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("Login", "signInWithEmail:success");

                                        Intent i2 = new Intent(getApplicationContext(), AdminDashBoardActivity.class);
                                        startActivity(i2);
                                        Toast.makeText(getApplicationContext(), "Authentication Successful.",
                                                Toast.LENGTH_SHORT).show();

                                        //updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("Login", "signInWithEmail:failure", task.getException());
                                        Toast.makeText(getApplicationContext(), "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        //updateUI(null);
                                    }

                                }
                            });
                }
            }
        });
    }
}
