package com.example.semestral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Orientacion extends AppCompatActivity implements SensorEventListener {

    TextView x, y, z;
    Sensor giroscopio;
    SensorManager sm;
    Intent i;
    Button menu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientacion);

        x = (TextView) findViewById(R.id.x);
        y = (TextView) findViewById(R.id.y);
        z = (TextView) findViewById(R.id.z);
        menu = (Button) findViewById(R.id.menu);

        i = new Intent(this, MainActivity.class);
        sm = (SensorManager) getSystemService((SENSOR_SERVICE));
        giroscopio = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sm.registerListener(this,giroscopio,SensorManager.SENSOR_DELAY_NORMAL);



        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(i);
            }
        });

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        x.setText(String.valueOf(sensorEvent.values[0]));
        y.setText(String.valueOf(sensorEvent.values[1]));
        z.setText(String.valueOf(sensorEvent.values[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }



}