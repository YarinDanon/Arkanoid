package com.yarin_haim.ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private SensorManager sensorManager;
    private Sensor orientaionSensor;
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameView = new GameView(this);
        setContentView(gameView);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        orientaionSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);  // deprecated
        if (orientaionSensor == null) {
            String sensorErrMsg = "\nOrientaion Sensor NOT exists!\nThe app will exit!";
            Toast.makeText(this, sensorErrMsg, Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        sensorManager.registerListener(gameView,orientaionSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        // disable all sensors
        sensorManager.unregisterListener(gameView);
    }

}
