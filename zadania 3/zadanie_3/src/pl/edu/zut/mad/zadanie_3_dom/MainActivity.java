package pl.edu.zut.mad.zadanie_3_dom;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button btnSecondActivity;
	private Button btnClose;
	private Button btnMad;
	private Button btnCall;
	private Button btnOpenCamera;
	private Button btnContacts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnSecondActivity = (Button) findViewById(R.id.btnSecondActivity);
		btnClose = (Button) findViewById(R.id.btnClose);
		btnMad = (Button) findViewById(R.id.btnMad);
		btnCall = (Button) findViewById(R.id.btnCall);
		btnOpenCamera = (Button) findViewById(R.id.btnOpenCamera);
		btnContacts = (Button) findViewById(R.id.btnContacts);

		btnSecondActivity.setOnClickListener(this);
		btnClose.setOnClickListener(this);
		btnMad.setOnClickListener(this);
		btnCall.setOnClickListener(this);
		btnOpenCamera.setOnClickListener(this);
		btnContacts.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.btnClose:
			finish();
			break;
		case R.id.btnSecondActivity:
			intent = new Intent(this, SecondActivity.class);
			intent.putExtra("Name", "Sebastian Œwierczek");
			startActivity(intent);
			finish();
			break;
		case R.id.btnMad:
			intent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://www.mad.zut.edu.pl"));
			startActivity(intent);
			break;
		case R.id.btnCall:
			intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:456"));
			startActivity(intent);
			break;
		case R.id.btnOpenCamera:
			intent = new Intent("android.media.action.IMAGE_CAPTURE");
			startActivity(intent);
			break;
		case R.id.btnContacts:
			intent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("content://contacts/people/"));
			startActivity(intent);
			break;
		}
	}
}
