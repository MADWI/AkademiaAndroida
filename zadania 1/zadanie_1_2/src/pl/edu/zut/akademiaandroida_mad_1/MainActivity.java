package pl.edu.zut.akademiaandroida_mad_1;

import pl.edu.zut.mad.zadanie_1_2.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tvExample;
	private String strLogTextView = "";
	private String logTag = "Akademia Androida M.A.D";
	private Button btnKill;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tvExample = (TextView) findViewById(R.id.tvExample);

		btnKill = (Button) findViewById(R.id.btnKill);
		btnKill.setVisibility(View.GONE);

		strLogTextView = "Jestem w onCreate()\n";
		tvExample.setText(strLogTextView);
		Log.d(logTag, "jestem OnCreate()");

		btnKill.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	protected void onStart() {
		super.onStart();
		strLogTextView += "Jestem w onStart()\n";
		tvExample.setText(strLogTextView);
		Log.d(logTag, "jestem OnStart()");
	}

	@Override
	protected void onResume() {
		super.onResume();
		strLogTextView += "Jestem w onResume()\n";
		tvExample.setText(strLogTextView);

		Log.d(logTag, "jestem OnResume()");
	}

	@Override
	protected void onPause() {
		super.onPause();
		strLogTextView += "Jestem w onPause()\n";
		tvExample.setText(strLogTextView);
		btnKill.setVisibility(View.VISIBLE);
		Log.d(logTag, "jestem OnPause()");
	}

	@Override
	protected void onStop() {
		super.onStop();
		strLogTextView += "Jestem w onStop()\n---------------------\n";
		tvExample.setText(strLogTextView);
		Log.d(logTag, "jestem OnStop()");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		strLogTextView += "Jestem w onDestroy()\n";
		tvExample.setText(strLogTextView);
		Log.d(logTag, "jestem OnDestroy()");
	}

}
