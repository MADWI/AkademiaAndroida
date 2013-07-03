package pl.edu.zut.mad.zadanie_3_dom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private static final int SECOND_ACT_REQUEST = 1234;

	private Button btnRequestAnswer;
	private TextView txtAnswer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnRequestAnswer = (Button) findViewById(R.id.btnRequestAnswer);
		btnRequestAnswer.setOnClickListener(this);
		txtAnswer = (TextView) findViewById(R.id.txtAnswer);

	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.btnRequestAnswer:
			intent = new Intent(this, SecondActivity.class);
			startActivityForResult(intent, SECOND_ACT_REQUEST);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == SECOND_ACT_REQUEST && resultCode == RESULT_OK) {
			txtAnswer.setText(data.getStringExtra("Answer"));
		} else
			txtAnswer.setText("Nie otrzymano odpowiedzi");
	}

}
