package com.example.semestral;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.semestral.fragemto.MainMenu;
import com.example.semestral.fragemto.Presentacion;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    static int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide(); //hide the title bar.

        BottomNavigationView btn = findViewById(R.id.bottomNavigationView);
        if(i==0)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.frament_layout, new Presentacion()).commit();
            btn.setSelectedItemId(R.id.item1);
        }
        else{
            getSupportFragmentManager().beginTransaction().replace(R.id.frament_layout, new MainMenu()).commit();
            btn.setSelectedItemId(R.id.item2);
        }


        btn.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                Fragment F =null;
                switch (item.getItemId())
                {
                    case R.id.item1:
                        F = new Presentacion();
                        break;

                    case R.id.item2:
                        i=1;
                        F = new MainMenu();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frament_layout,F).commit();
            }
        });


        }
}

