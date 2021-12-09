package com.example.semestral;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;

public class luz_activity extends AppCompatActivity {

    private SensorManager sensorManager;
    private TextView lightLevel;
    ImageView foquito_a;
    ImageView foquito_e;
    Button volver;
    Intent int_v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        volver = (Button) findViewById(R.id.volver);
        int_v = new Intent(this, MainActivity.class);
        lightLevel = (TextView) findViewById(R.id.light_level);
        foquito_a = (ImageView) findViewById(R.id.apagado);
        foquito_e = (ImageView) findViewById(R.id.encendido);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(int_v);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sensorManager != null) {
            sensorManager.unregisterListener(listener);
        }
    }
    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
// The value of the first subscript in the values array is the current light intensity
            float value = event.values[0];
            lightLevel.setText("El nivel de Luz es " + value + " lx");
            if (value == 0) {
                foquito_a.setVisibility(View.VISIBLE);
                foquito_e.setVisibility(View.INVISIBLE);
            } else {
                foquito_e.setVisibility(View.VISIBLE);
                foquito_a.setVisibility(View.INVISIBLE);
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
}