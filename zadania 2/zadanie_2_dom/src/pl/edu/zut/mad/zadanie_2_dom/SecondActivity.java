package pl.edu.zut.mad.zadanie_2_dom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondActivity extends Activity implements OnClickListener {

	private Button btnFirstActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		btnFirstActivity = (Button) findViewById(R.id.btnFirstActivity);
		btnFirstActivity.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnFirstActivity:
			Intent i = new Intent(this, MainActivity.class);
			startActivity(i);
			finish();
			break;
		}

	}
}
