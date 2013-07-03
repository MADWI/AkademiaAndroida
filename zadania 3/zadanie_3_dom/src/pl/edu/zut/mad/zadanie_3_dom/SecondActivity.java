package pl.edu.zut.mad.zadanie_3_dom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends Activity implements OnClickListener {

	private Button btnReply;
	private EditText editAnswer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		btnReply = (Button) findViewById(R.id.btnReply);
		btnReply.setOnClickListener(this);

		editAnswer = (EditText) findViewById(R.id.editAnswer);
		getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_VISIBLE);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnReply:
			if (editAnswer.getText().toString().trim().length() > 0) {
				Intent resultIntent = new Intent();
				resultIntent
						.putExtra("Answer", editAnswer.getText().toString());
				setResult(RESULT_OK, resultIntent);
				finish();
			} else
				Toast.makeText(this, "Odpowiedü pusta !", Toast.LENGTH_SHORT)
						.show();

			break;
		}
	}
}
