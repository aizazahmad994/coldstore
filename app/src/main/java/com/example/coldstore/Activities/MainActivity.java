package com.example.coldstore.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.coldstore.R;

public class MainActivity extends AppCompatActivity {
    ImageView im;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        im = (ImageView)findViewById(R.id.imageView);
        im.setImageResource(R.drawable.potato);


        i = new Intent(this, login.class);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                startActivity(i);
                finish();
            }
        }, 1000);
    }
}
