package pl.edu.zut.mad.zadanie_2_dom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button btnSecondActivity;
	private Button btnClose;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnSecondActivity = (Button) findViewById(R.id.btnFirstActivity);
		btnSecondActivity.setOnClickListener(this);

		btnClose = (Button) findViewById(R.id.btnClose);
		btnClose.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnClose:
			finish();
			break;
		case R.id.btnFirstActivity:
			Intent i = new Intent(this, SecondActivity.class);
			startActivity(i);
			finish();
			break;
		}

	}
}
