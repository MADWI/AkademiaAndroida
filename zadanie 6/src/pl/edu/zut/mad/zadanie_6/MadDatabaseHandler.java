package pl.edu.zut.mad.zadanie_6;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MadDatabaseHandler extends SQLiteOpenHelper {

	private static final int VERSION = 1;

	private static final String NAME = "people_database";
	private static final String PEOPLE_TABLE = "people";

	private static final String ID_COLUMN = "id";
	private static final String PERSON_NAME_COULMN = "name";
	private static final String PERSON_AGE_COLUMN = "age";

	public MadDatabaseHandler(Context context) {
		super(context, NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + PEOPLE_TABLE + "(" + ID_COLUMN + " INTEGER PRIMARY KEY," + PERSON_NAME_COULMN
				+ " TEXT," + PERSON_AGE_COLUMN + " INTEGER" + ")");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + PEOPLE_TABLE);
		onCreate(db);
	}

	public void addPerson(Person person) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues newRowValues = new ContentValues();
		newRowValues.put(PERSON_NAME_COULMN, person.getName());
		newRowValues.put(PERSON_AGE_COLUMN, Integer.valueOf(person.getAge()));

		db.insert(PEOPLE_TABLE, null, newRowValues);
		db.close();
	}

	public ArrayList<Person> getPeople() {
		ArrayList<Person> people = new ArrayList<Person>();
		String getAllPeopleSelect = "SELECT  * FROM " + PEOPLE_TABLE;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(getAllPeopleSelect, null);

		if (cursor.moveToFirst()) {
			do {
				Person person = new Person();
				person.setName(cursor.getString(1));
				person.setAge(cursor.getInt(2));
				people.add(person);
			}
			while (cursor.moveToNext());
		}
		return people;
	}
}
