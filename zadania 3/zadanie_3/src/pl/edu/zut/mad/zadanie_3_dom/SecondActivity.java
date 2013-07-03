package pl.edu.zut.mad.zadanie_3_dom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends Activity implements OnClickListener {

	private Button btnFirstActivity;
	private TextView txtName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		btnFirstActivity = (Button) findViewById(R.id.btnFirstActivity);
		btnFirstActivity.setOnClickListener(this);
		txtName = (TextView) findViewById(R.id.txtName);

		Intent intent = getIntent();
		String name = intent.getStringExtra("Name");
		txtName.setText(name);
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
