package com.example.andresguzman.webde;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class bienvenida extends Activity implements SensorEventListener{

    private SensorManager sm;
    private int acum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        ActionBar barra = getActionBar();
        if (barra != null) {
            barra.hide();
        }


        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

    }

    public void hider(View v){

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        Toast.makeText(getApplicationContext(),"Pase su mano sobre el sensor de proximidad 3 veces para desbloquear.",
                       Toast.LENGTH_LONG).show();

    }

    public void activar(){

        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        Sensor s = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float valor = Float.parseFloat(String.valueOf(event.values[0]));
        Log.i("sensor inicia",""+valor+""+acum);
        if (valor < 5){
            acum++;
        }
        if (acum > 3){
            sm.unregisterListener(this);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){

        finish();

    }

    public void ingreso(View v){
        startActivity(new Intent(getApplicationContext(), Log_in.class));
        finish();
    }

}
