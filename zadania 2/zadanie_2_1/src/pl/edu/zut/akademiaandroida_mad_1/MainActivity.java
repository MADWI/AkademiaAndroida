package pl.edu.zut.akademiaandroida_mad_1;


import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private TextView backgroundTextView;
	private String lifecycleActivityStr = "";
	private String logTag = "Akademia Androida M.A.D";
	private Button onDestroyButton;
	private Button toastButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		backgroundTextView = (TextView)findViewById(R.id.exampleTextView);
		
		onDestroyButton = (Button)findViewById(R.id.button1);
		onDestroyButton.setVisibility(View.GONE);
		
		toastButton = (Button)findViewById(R.id.button2);    

		lifecycleActivityStr = "Jestem w onCreate()\n";
		backgroundTextView.setText(lifecycleActivityStr);
		Log.d(logTag, "jestem OnCreate()");	
		
		onDestroyButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		toastButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Hello Android", Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
    @Override
    protected void onStart() {
        super.onStart();
        lifecycleActivityStr += "Jestem w onStart()\n";
        backgroundTextView.setText(lifecycleActivityStr);
        Log.d(logTag, "jestem OnStart()");
    }
    @Override
    protected void onResume() {
        super.onResume();
        lifecycleActivityStr += "Jestem w onResume()\n";
        backgroundTextView.setText(lifecycleActivityStr);
        Log.d(logTag, "jestem OnResume()");
    }
    @Override
    protected void onPause() {
        super.onPause();
        lifecycleActivityStr += "Jestem w onPause()\n";
        backgroundTextView.setText(lifecycleActivityStr);
        onDestroyButton.setVisibility(View.VISIBLE);
        Log.d(logTag, "jestem OnPause()");
    }
    @Override
    protected void onStop() {
        super.onStop();
        lifecycleActivityStr += "Jestem w onStop()\n---------------------\n";
        backgroundTextView.setText(lifecycleActivityStr);
        Log.d(logTag, "jestem OnStop()");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        lifecycleActivityStr += "Jestem w onDestroy()\n";
        backgroundTextView.setText(lifecycleActivityStr);
        Log.d(logTag, "jestem OnDestroy()");
        
        //android.os.Process.killProcess(android.os.Process.myPid());
    }

}
