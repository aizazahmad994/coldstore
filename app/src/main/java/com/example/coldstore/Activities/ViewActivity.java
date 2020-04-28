package com.example.coldstore.Activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coldstore.AdapterClasses.UserAdapter;
import com.example.coldstore.ModelClasses.UserInformation;
import com.example.coldstore.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    private static final String TAG = "View activity";
    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<UserInformation> list;
    UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        recyclerView = findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        reference = FirebaseDatabase.getInstance().getReference("clients");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange: Data changed");
                list = new ArrayList<UserInformation>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    UserInformation p = dataSnapshot1.getValue(UserInformation.class);
                    list.add(p);
                }
                adapter = new UserAdapter(ViewActivity.this, list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ViewActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}