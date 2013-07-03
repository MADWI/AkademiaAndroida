package pl.edu.zut.mad.zadanie_5_dom;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class ProximityExample extends Activity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor proximity;
    private View view;

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_proximity_example);

	view = findViewById(R.id.view1);
	view.setBackgroundColor(Color.GREEN);

	mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
	if ((proximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)) == null) {
	    Toast.makeText(getApplicationContext(),
		    "Nie znaleziono czujnika zbli¿eniowego", Toast.LENGTH_LONG).show();
	    finish();
	}
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
	// TODO Auto-generated method stub

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
	float prox = event.values[0];
	makeSomeColor(prox);
    }

    private void makeSomeColor(float prox) {

	if (prox == 0) {
	    view.setBackgroundColor(Color.RED);
	    count++;

	    if (count == 4) {
		Toast.makeText(getApplicationContext(),
			"Zaraz aplikacja się wyłączy", Toast.LENGTH_SHORT)
			.show();
	    } else if (count == 5) {
		Toast.makeText(getApplicationContext(), "No i się wyłączyła",
			Toast.LENGTH_SHORT).show();
		finish();
	    } else {
		Toast.makeText(getApplicationContext(),
			"Za " + (5 - count) + " zmiany aplikacja się wyłączy",
			Toast.LENGTH_SHORT).show();
	    }

	} else if (prox == 1) {
	    view.setBackgroundColor(Color.GREEN);
	}
    }

    protected void onResume() {
	super.onResume();
	mSensorManager.registerListener(this, proximity,
		SensorManager.SENSOR_DELAY_FASTEST);
	Toast.makeText(getApplicationContext(), "Czujnik zbliżeniowy podłączony",
		Toast.LENGTH_SHORT).show();
    }

    protected void onPause() {
	super.onPause();
	mSensorManager.unregisterListener(this);
	Toast.makeText(getApplicationContext(),
		"Czujnik zbliżeniowy wyłączony", Toast.LENGTH_SHORT).show();
    }
}
