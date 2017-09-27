package com.techzei.www;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {

	String title = "title";
	String desc = "desc";
	String date = "date";
	String author = "author";

	public MyDatabaseHelper(Context context) {
		super(context, "PostDatabase.db", null, 8);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("CREATE TABLE PostTable ( _id INTEGER PRIMARY KEY, title TEXT, date TEXT,author TEXT, desc TEXT);");

		for (int i = 0; i < 20; i++) {
			ContentValues cv = new ContentValues();
			cv.put("title", title);
			cv.put("date", date);
			cv.put("author", author);
			cv.put("desc", desc);
			db.insert("PostTable", null, cv);

		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE IF EXISTS PostTable;");
		onCreate(db);
	}

}
