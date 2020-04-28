package com.example.coldstore.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.coldstore.R;

public class AdminDashBoardActivity extends AppCompatActivity {

    ImageButton search;
    ImageButton add_record;
    ImageButton view_record, receipt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        search = (ImageButton) findViewById(R.id.searchbutton);
        add_record = (ImageButton) findViewById(R.id.addrecordbutton);
        view_record = (ImageButton) findViewById(R.id.viewrecordbutton);

        add_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), addRecord.class);
                startActivity(i);
            }
        });
        view_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ViewActivity.class);
                startActivity(i);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), search.class);
                startActivity(i);
            }
        });



    }
}