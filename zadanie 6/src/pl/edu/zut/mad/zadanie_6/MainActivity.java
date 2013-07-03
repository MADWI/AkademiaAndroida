package pl.edu.zut.mad.zadanie_6;

import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private static final String TAG = MainActivity.class.getName();
	private static final String SHARED_PREF_KEY_NAME = "SHARED_PREF_KEY_NAME";
	private static final String SHARED_PREF_KEY_AGE = "SHARED_PREF_KEY_AGE";
	private SharedPreferences sharedPreferences;
	private Resources resources;
	private MadDatabaseHandler database;

	private Button btnSaveSharedPref;
	private Button btnClearSharedPref;
	private Button btnAddToDatabase;
	private Button btnReadFromSharedPref;
	private Button btnReadFromXml;
	private Button btnReadFromDatabase;

	private TextView txtSharedPrefContent;
	private TextView txtXmlContent;
	private TextView txtDatabaseContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sharedPreferences = getPreferences(Context.MODE_PRIVATE);
		resources = getResources();
		database = new MadDatabaseHandler(this);

		initUIElements();
		setOnClicksListeners();
	}

	private void initUIElements() {
		btnSaveSharedPref = (Button) findViewById(R.id.btnSaveSharedPref);
		btnClearSharedPref = (Button) findViewById(R.id.btnClearSharedPref);
		btnAddToDatabase = (Button) findViewById(R.id.btnAddToDatabase);
		btnReadFromSharedPref = (Button) findViewById(R.id.btnReadFromSharedPref);
		btnReadFromXml = (Button) findViewById(R.id.btnReadFromXml);
		btnReadFromDatabase = (Button) findViewById(R.id.btnReadFromDatabase);

		txtSharedPrefContent = (TextView) findViewById(R.id.txtSharedPrefContent);
		txtXmlContent = (TextView) findViewById(R.id.txtXmlContent);
		txtDatabaseContent = (TextView) findViewById(R.id.txtDatabaseContent);
	}

	private void setOnClicksListeners() {
		btnSaveSharedPref.setOnClickListener(this);
		btnClearSharedPref.setOnClickListener(this);
		btnAddToDatabase.setOnClickListener(this);
		btnReadFromSharedPref.setOnClickListener(this);
		btnReadFromXml.setOnClickListener(this);
		btnReadFromDatabase.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnSaveSharedPref:
			saveToSharedPreferences();
			break;
		case R.id.btnClearSharedPref:
			clearSharedPreferences();
			break;
		case R.id.btnAddToDatabase:
			addToDatabase();
			break;
		case R.id.btnReadFromSharedPref:
			readFromSharedPrefAndShowContent();
			break;
		case R.id.btnReadFromXml:
			readFromXmlAndShowContent();
			break;
		case R.id.btnReadFromDatabase:
			readFromDatabaseAndShowContent();
			break;
		}
	}

	private void saveToSharedPreferences() {
		SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
		sharedPreferencesEditor.putString(SHARED_PREF_KEY_NAME, "Jan Kowalski");
		sharedPreferencesEditor.putInt(SHARED_PREF_KEY_AGE, 32);
		sharedPreferencesEditor.commit();
	}

	private void clearSharedPreferences() {
		SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
		sharedPreferencesEditor.clear();
		sharedPreferencesEditor.commit();
	}

	private void readFromSharedPrefAndShowContent() {
		String sharedPrefContent = readFromSharedPreferences();
		txtSharedPrefContent.setText(sharedPrefContent);
	}

	private String readFromSharedPreferences() {
		StringBuilder sb = new StringBuilder();
		int age;

		age = sharedPreferences.getInt(SHARED_PREF_KEY_AGE, 0);
		sb.append(sharedPreferences.getString(SHARED_PREF_KEY_NAME, "brak danych ") + " ");
		sb.append(Integer.toString(age));
		return sb.toString();
	}

	private void readFromXmlAndShowContent() {
		ArrayList<Person> people = readFromXml(R.xml.people);

		if (people.size() > 0) {
			StringBuilder sb = new StringBuilder();

			for (Person person : people) {
				sb.append(person.getName() + " " + person.getAge() + "\n");
			}

			txtXmlContent.setText(sb.toString());
		}

	}

	private ArrayList<Person> readFromXml(int resourceId) {

		ArrayList<Person> people = new ArrayList<Person>();
		Person tempPerson = null;

		XmlPullParser xmlParser = resources.getXml(resourceId);
		try {
			int eventType = xmlParser.getEventType();

			while (eventType != XmlPullParser.END_DOCUMENT) {

				if (eventType == XmlPullParser.START_TAG) {
					if (xmlParser.getName().equals("person")) {
						tempPerson = new Person();
						Log.d(TAG, "<person>");
					}

					else if (xmlParser.getName().equals("name")) {
						eventType = xmlParser.next();
						if (eventType == XmlPullParser.TEXT && tempPerson != null) {
							tempPerson.setName(xmlParser.getText());
							Log.d(TAG, "<name>" + xmlParser.getText() + "</name");
						}
					}

					else if (xmlParser.getName().equals("age")) {
						eventType = xmlParser.next();
						if (eventType == XmlPullParser.TEXT && tempPerson != null) {
							tempPerson.setAge(Integer.valueOf(xmlParser.getText()));
							Log.d(TAG, "<age>" + xmlParser.getText() + "</age");
						}
					}
				}
				else if (eventType == XmlPullParser.END_TAG) {
					if (xmlParser.getName().equals("person")) {
						people.add(tempPerson);
						Log.d(TAG, "</person>");
					}
				}
				eventType = xmlParser.next();
			}
		}
		catch (XmlPullParserException e) {
			showErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		catch (IOException e) {
			showErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		return people;
	}

	private void addToDatabase() {
		database.addPerson(new Person("Jan Kowalski", 32));
		database.addPerson(new Person("Jan Nowak", 22));
		database.addPerson(new Person("Chuck Norris", 40));
	}

	private void readFromDatabaseAndShowContent() {
		ArrayList<Person> people = readFromDatabase();

		if (people.size() > 0) {
			StringBuilder sb = new StringBuilder();

			for (Person person : people) {
				sb.append(person.getName() + " " + person.getAge() + "\n");
			}

			txtDatabaseContent.setText(sb.toString());
		}

	}

	private ArrayList<Person> readFromDatabase() {
		return database.getPeople();
	}

	void showErrorMessage(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

}
