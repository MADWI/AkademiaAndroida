package pl.edu.zut.mad.zadanie_1_1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private TextView exampleTextView;
	private String str1 = "";
	private String logTag = "Akademia Androida M.A.D";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		exampleTextView = (TextView)findViewById(R.id.exampleTextView);
		str1 = "Jestem w onCreate()\n";
		exampleTextView.setText(str1);
		Log.d(logTag, "jestem OnCreate()");
	}

	
    @Override
    protected void onStart() {
        super.onStart();
        str1 = str1 + "Jestem w onStart()\n";
        exampleTextView.setText(str1);
        Log.d(logTag, "jestem OnStart()");
    }
    @Override
    protected void onResume() {
        super.onResume();
        str1 = str1 + "Jestem w onResume()\n";
        exampleTextView.setText(str1);
        Log.d(logTag, "jestem OnResume()");
    }
    @Override
    protected void onPause() {
        super.onPause();
        str1 = str1 + "Jestem w onPause()\n";
        exampleTextView.setText(str1);
        Log.d(logTag, "jestem OnPause()");
    }
    @Override
    protected void onStop() {
        super.onStop();
        str1 = str1 + "Jestem w onStop()\n---------------------\n";
        exampleTextView.setText(str1);
        Log.d(logTag, "jestem OnStop()");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        str1 = str1 + "Jestem w onDestroy()\n";
        exampleTextView.setText(str1);
        Log.d(logTag, "jestem OnDestroy()");
    }

}
