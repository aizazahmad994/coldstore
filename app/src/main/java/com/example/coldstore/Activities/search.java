package com.example.coldstore.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.content.Context;
import android.os.Bundle;

import com.example.coldstore.AdapterClasses.searchAdapter;
import com.example.coldstore.ModelClasses.RecordInformation;
import com.example.coldstore.ModelClasses.UserInformation;
import com.example.coldstore.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class search extends AppCompatActivity {
    RecyclerView recyclerView;
    SearchView searchView;
    ArrayList<RecordInformation> Entry_info;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference EntryRef = database.getReference("Entries");

    Context Mycontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recyclerView = findViewById(R.id.SearchRecycler);
        searchView = findViewById(R.id.searchView);
        Mycontext = getApplicationContext();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (EntryRef != null) {
            EntryRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        Entry_info = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Entry_info.add(ds.getValue(RecordInformation.class));
                        }
                        searchAdapter SA = new searchAdapter(search.this, Entry_info);
                        recyclerView.setAdapter(SA);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    Search(newText);
                    return true;
                }
            });
        }
    }

    private void Search(String str) {
        ArrayList<RecordInformation> Entries = new ArrayList<>();
        for (RecordInformation Rc : Entry_info) {
            if (Rc.getTypeOfPotato().toLowerCase().contains(str)) {
                Entries.add(Rc);
            }
            searchAdapter sc = new searchAdapter(getApplicationContext(), Entries);
            recyclerView.setAdapter(sc);
        }
    }
}
