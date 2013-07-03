package pl.edu.zut.mad.zadanie_4_dom;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

	private static final int REPEAT_TIME = 60; // uruchamianie us³ugi co 1 min.

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Intent i = new Intent(this, FrequentBroadcastReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, i,
				PendingIntent.FLAG_UPDATE_CURRENT);
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		alarmManager.setRepeating(AlarmManager.RTC, System.currentTimeMillis(),
				REPEAT_TIME * 1000, pendingIntent);

	}
}
