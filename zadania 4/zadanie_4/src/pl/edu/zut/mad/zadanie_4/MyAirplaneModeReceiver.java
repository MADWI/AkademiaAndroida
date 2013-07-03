package pl.edu.zut.mad.zadanie_4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyAirplaneModeReceiver extends BroadcastReceiver {
	private static final String TAG = "MyAirplaneModeReceiver";

	@Override
	public void onReceive(Context ctx, Intent i) {
		Log.d(TAG, "onReceive");
		if (i.getAction().equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)) {
			ctx.startService(new Intent(ctx, MyUselessService.class));

		}
	}
}
