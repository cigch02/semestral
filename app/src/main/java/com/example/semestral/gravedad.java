package com.example.semestral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.semestral.ui.main.GravedadFragment;

public class gravedad extends AppCompatActivity implements SensorEventListener{
    private TextView xT, yT, zT;
    private SensorManager sensorM;
    private Sensor Gravedad;
    private boolean isGravitySensorPresent;
    private AudioManager aManeger;
    private Button Menu;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gravedad_activity);

        i=new Intent(this, MainActivity.class);
        aManeger = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        xT = findViewById(R.id.xText);
        yT = findViewById(R.id.yText);
        zT = findViewById(R.id.zText);
        Menu = (Button) findViewById(R.id.Menu);

        sensorM = (SensorManager) getSystemService(SENSOR_SERVICE);

        if(sensorM.getDefaultSensor(Sensor.TYPE_GRAVITY)!=null)
        {
            Gravedad =  sensorM.getDefaultSensor(Sensor.TYPE_GRAVITY);
            isGravitySensorPresent = true;
        }
        else{
            xT.setText("No se presenta el sensor");
            isGravitySensorPresent = false;
        }

        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(i);
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        xT.setText(sensorEvent.values[0]+" m/s2");
        yT.setText(sensorEvent.values[1]+" m/s2");
        zT.setText(sensorEvent.values[2]+" m/s2");

        if(sensorEvent.values[2]<-9.7)
        {
            getWindow().getDecorView().setBackgroundColor(Color.GREEN);
            aManeger.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        }
        else{
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);
            aManeger.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sensorM.getDefaultSensor(Sensor.TYPE_GRAVITY)!=null)
            sensorM.registerListener(this,Gravedad,sensorM.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(sensorM.getDefaultSensor(Sensor.TYPE_GRAVITY)!=null)
            sensorM.unregisterListener(this,Gravedad);
    }


}