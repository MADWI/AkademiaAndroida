package pl.edu.zut.mad.zadanie_4;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class MyUselessService extends Service implements Runnable {

	private static final String TAG = "MyUselessService";
	private NotificationManager nm;

	@Override
	public void onCreate() {
		Log.d(TAG, "onCreate");
		super.onCreate();
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		showNotification();
		Thread aThread = new Thread(this);
		aThread.start();
	}

	public void run() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Log.d(TAG, "2 sekundy przejœciu w tryb samolotowy");
		stopSelf();
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	private void showNotification() {
		Log.d(TAG, "showNotification()");
		int NOTIFICATION_ID = 123456;

		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, MainActivity.class), 0);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				this);

		builder.setContentIntent(contentIntent)
				.setSmallIcon(R.drawable.ic_launcher)
				.setLargeIcon(
						BitmapFactory.decodeResource(this.getResources(),
								R.drawable.ic_launcher))
				.setTicker("Uruchomiono MyUselessService")
				.setWhen(System.currentTimeMillis()).setAutoCancel(true)
				.setContentTitle(TAG)
				.setContentText("Otwórz MainActivity..");
		Notification n = builder.build();

		nm.notify(NOTIFICATION_ID, n);
	}
}
