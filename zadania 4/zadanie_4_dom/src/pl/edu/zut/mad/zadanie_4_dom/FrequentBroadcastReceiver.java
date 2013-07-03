package pl.edu.zut.mad.zadanie_4_dom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class FrequentBroadcastReceiver extends BroadcastReceiver {
	private static final String TAG = "MyAirplaneModeReceiver";

	@Override
	public void onReceive(Context ctx, Intent i) {
		Log.d(TAG, "onReceive");
		ctx.startService(new Intent(ctx, MyUselessService.class));
	}
}
