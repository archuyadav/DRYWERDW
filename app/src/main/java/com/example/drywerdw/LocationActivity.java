package com.example.drywerdw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LocationActivity extends AppCompatActivity {

    private EditText pickup;
    private EditText drop;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pickup = findViewById(R.id.pickup);
        drop = findViewById(R.id.drop);
        btnNext = findViewById(R.id.btnnext);

    }
    public void notes(View v){
        startActivity(new Intent(this, NotesActivity.class));

    }

}
