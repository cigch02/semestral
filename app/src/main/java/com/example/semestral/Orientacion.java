package com.example.semestral;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Orientacion extends AppCompatActivity implements SensorEventListener {

    TextView x, y, z;
    Sensor giroscopio;
    SensorManager sm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientacion);

        x = (TextView) findViewById(R.id.x);
        y = (TextView) findViewById(R.id.y);
        z = (TextView) findViewById(R.id.z);

        sm = (SensorManager) getSystemService((SENSOR_SERVICE));
        giroscopio = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sm.registerListener(this,giroscopio,SensorManager.SENSOR_DELAY_NORMAL);

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