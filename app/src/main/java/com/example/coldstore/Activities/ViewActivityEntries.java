package com.example.coldstore.Activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coldstore.AdapterClasses.EntriesAdapter;
import com.example.coldstore.ModelClasses.RecordInformation;
import com.example.coldstore.ModelClasses.UserInformation;
import com.example.coldstore.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.content.Intent;
import java.util.ArrayList;

public class ViewActivityEntries extends AppCompatActivity {

    private static final String TAG = "View activity";
    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<RecordInformation> list;
    UserInformation user_info;
    EntriesAdapter entriesAdapter;
String naaam,pata,phone;
    TextView Naam,Addr,numb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_entries);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        user_info = (UserInformation) bundle.getSerializable("value");
        naaam = user_info.getName();
        phone = user_info.getNumber();
        pata = user_info.getAddress();

        recyclerView = findViewById(R.id.myRecycler2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Naam = (TextView) findViewById(R.id.showname);
        Addr = (TextView) findViewById(R.id.showadd);
        numb = (TextView) findViewById(R.id.shownum);

        Naam.setText(naaam);
        Addr.setText(pata);
        numb.setText(phone);

        reference = FirebaseDatabase.getInstance().getReference("Entries");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange: Data changed");
                list = new ArrayList<RecordInformation>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    RecordInformation p = dataSnapshot1.getValue(RecordInformation.class);
                if (p.getNumber().equals(phone))
                    list.add(p);
                }
                entriesAdapter = new EntriesAdapter(ViewActivityEntries.this, list);
                recyclerView.setAdapter(entriesAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ViewActivityEntries.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static class UpdateActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_update);
        }
    }
}
