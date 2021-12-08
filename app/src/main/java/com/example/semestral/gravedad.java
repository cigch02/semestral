package com.example.semestral;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.semestral.ui.main.GravedadFragment;

public class gravedad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gravedad_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, GravedadFragment.newInstance())
                    .commitNow();
        }
    }
}