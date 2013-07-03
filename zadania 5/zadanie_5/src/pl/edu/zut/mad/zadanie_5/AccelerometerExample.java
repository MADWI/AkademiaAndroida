package pl.edu.zut.mad.zadanie_5;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

public class AccelerometerExample extends Activity implements
	SensorEventListener {

    private TextView axeX;
    private TextView axeY;
    private TextView axeZ;

    private float xCoord;
    private float yCoord;
    private float zCoord;

    SensorManager mSensorManager;
    Sensor accelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_accelerometer_example);

	axeX = (TextView) findViewById(R.id.xAxis);
	axeY = (TextView) findViewById(R.id.yAxis);
	axeZ = (TextView) findViewById(R.id.zAxis);

	mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
	if ((accelerometer = mSensorManager
		.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)) == null) {
	    Toast.makeText(getApplicationContext(), "Accelerometer not found",
		    Toast.LENGTH_SHORT).show();
	    finish();
	}
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

	zCoord = event.values[0];
	xCoord = event.values[1];
	yCoord = event.values[2];

	axeX.setText(String.format("X: %.1f", xCoord));
	axeY.setText(String.format("Y: %.1f", yCoord));
	axeZ.setText(String.format("Z: %.1f", zCoord));

    }

    @Override
    protected void onResume() {
	super.onResume();
	mSensorManager.registerListener(this, accelerometer,
		SensorManager.SENSOR_DELAY_NORMAL);
	Toast.makeText(getApplicationContext(), "Accelerometer connected",
		Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
	super.onPause();
	mSensorManager.unregisterListener(this);
	Toast.makeText(getApplicationContext(), "Accelerometer disconnected",
		Toast.LENGTH_SHORT).show();
    }
}
