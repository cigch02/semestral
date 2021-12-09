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
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



public class gravedad extends AppCompatActivity implements SensorEventListener{
    private TextView xT, yT, zT;
    private SensorManager sensorM;
    private Sensor Gravedad;
    private boolean isGravitySensorPresent;
    private Vibrator V;
    private Button Menu;
    private Intent i;
    private ImageView Ima;
    int []flecha = new int[]{R.drawable.flecha_arriba, R.drawable.flecha_abajo, R.drawable.flecha_drch, R.drawable.flecha_izq};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gravedad_activity);

        i = new Intent(this, MainActivity.class);
        V = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        xT = findViewById(R.id.xText);
        yT = findViewById(R.id.yText);
        zT = findViewById(R.id.zText);
        Menu = (Button) findViewById(R.id.Menu);
        Ima = (ImageView) findViewById(R.id.imagen);

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
        xT.setText("X: "+sensorEvent.values[0]+" m/s2");
        yT.setText("Y: "+sensorEvent.values[1]+" m/s2");
        zT.setText("Z: "+sensorEvent.values[2]+" m/s2");

        if(sensorEvent.values[2]<-9.7)
        {
            getWindow().getDecorView().setBackgroundColor(Color.GREEN);
            V.vibrate(5000);
        }
        else{
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);
            V.cancel();
        }

        if(sensorEvent.values[0]>0.0001 && sensorEvent.values[1]<-0.0001)
        {
            Ima.setImageResource(flecha[0]);
        }
        else if(sensorEvent.values[0]<-0.001 && sensorEvent.values[1]<-0.0001)
        {
            Ima.setImageResource(flecha[2]);
        }
        else if(sensorEvent.values[0]<-0.0001 && sensorEvent.values[1]>0.0001)
        {
            Ima.setImageResource(flecha[1]);
        }
        else if(sensorEvent.values[0]>0.0001 && sensorEvent.values[1]>0.0001)
        {
            Ima.setImageResource(flecha[3]);
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