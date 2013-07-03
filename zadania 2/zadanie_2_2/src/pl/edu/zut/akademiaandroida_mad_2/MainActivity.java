package pl.edu.zut.akademiaandroida_mad_2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private Button btn1;
	private Button btn2;
	private Button btn3;
	private Button btn4;
	private String color = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn1 = (Button)findViewById(R.id.button1);
		btn2 = (Button)findViewById(R.id.button2);
		btn3 = (Button)findViewById(R.id.button3);
		btn4 = (Button)findViewById(R.id.button4);
		
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				color = btn3.getBackground().toString();
				btn1.setBackgroundResource(R.drawable.green_button);
				btn3.setBackgroundResource(R.drawable.red_button);
				Toast.makeText(getApplicationContext(), color, Toast.LENGTH_SHORT).show();
			}
		});
		
		btn2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				color = btn3.getBackground().toString();
				btn2.setBackgroundResource(R.drawable.blue_button);
				btn4.setBackgroundResource(R.drawable.yellow_button);
				Toast.makeText(getApplicationContext(), color, Toast.LENGTH_SHORT).show();
			}
		});
		
		btn3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				color = btn3.getBackground().toString();
				btn1.setBackgroundResource(R.drawable.red_button);
				btn3.setBackgroundResource(R.drawable.green_button);
				Toast.makeText(getApplicationContext(), color, Toast.LENGTH_SHORT).show();
			}
		});
		
		btn4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				color = btn3.getBackground().toString();
				btn2.setBackgroundResource(R.drawable.yellow_button);
				btn4.setBackgroundResource(R.drawable.blue_button);
				Toast.makeText(getApplicationContext(), color, Toast.LENGTH_SHORT).show();
			}
		});	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
