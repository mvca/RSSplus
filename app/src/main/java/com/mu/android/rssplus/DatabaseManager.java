package com.mu.android.rssplus;
 
import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
 
public class DatabaseManager
{
	Context context;
	private SQLiteDatabase db;
	private final String dbname = "RSSplus";
	private final int dbversion = 1;
	private final String name = "feeds";
	private final String rowid = "id";
	private final String row1 = "title";
	private final String row2 = "description";
 
	public DatabaseManager(Context context)
	{
		this.context = context;
		CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(context);
		this.db = helper.getWritableDatabase();
	}
 
	public void addRow(String rowStringOne, String rowStringTwo)
	{
		ContentValues values = new ContentValues();
		values.put(row1, rowStringOne);
		values.put(row2, rowStringTwo);

		try{db.insert(name, null, values);}
		catch(Exception e)
		{
			Log.e("Database error.", e.toString());
			e.printStackTrace();
		}
	}

	public void deleteRow(long rowID)
	{
		try {db.delete(name, rowid + "=" + rowID, null);}
		catch (Exception e)
		{
			Log.e("Database error.", e.toString());
			e.printStackTrace();
		}
	}

	public void updateRow(long rowID, String rowStringOne, String rowStringTwo)
	{
		ContentValues values = new ContentValues();
		values.put(row1, rowStringOne);
		values.put(row2, rowStringTwo);
 
		try {db.update(name, values, rowid + "=" + rowID, null);}
		catch (Exception e)
		{
			Log.e("Database error.", e.toString());
			e.printStackTrace();
		}
	}
 
	public ArrayList<Object> getRowAsArray(long rowID)
	{
		ArrayList<Object> rowArray = new ArrayList<Object>();
		Cursor cursor;
 
		try
		{
			cursor = db.query
			(
					name,
					new String[] { rowid, row1, row2 },
					rowid + "=" + rowID,
					null, null, null, null, null
			);
			cursor.moveToFirst();
			if (!cursor.isAfterLast())
			{
				do
				{
					rowArray.add(cursor.getLong(0));
					rowArray.add(cursor.getString(1));
					rowArray.add(cursor.getString(2));
				}
				while (cursor.moveToNext());
			}
			cursor.close();
		}
		catch (SQLException e) 
		{
			Log.e("Database error.", e.toString());
			e.printStackTrace();
		}
		return rowArray;
	}
 
	public ArrayList<ArrayList<Object>> getAllRowsAsArrays()
	{
		ArrayList<ArrayList<Object>> dataArrays = new ArrayList<ArrayList<Object>>();
		Cursor cursor;
 
		try
		{
			cursor = db.query(
					name,
					new String[]{rowid, row1, row2},
					null, null, null, null, null
			);
			cursor.moveToFirst();

			if (!cursor.isAfterLast())
			{
				do
				{
					ArrayList<Object> dataList = new ArrayList<Object>();
					dataList.add(cursor.getLong(0));
					dataList.add(cursor.getString(1));
					dataList.add(cursor.getString(2));
					dataArrays.add(dataList);
				}
				while (cursor.moveToNext());
			}
		}
		catch (SQLException e)
		{
			Log.e("Database error.", e.toString());
			e.printStackTrace();
		}
		return dataArrays;
	}
	
	private class CustomSQLiteOpenHelper extends SQLiteOpenHelper
	{
		public CustomSQLiteOpenHelper(Context context)
		{
			super(context, dbname, null, dbversion);
		}
 
		@Override
		public void onCreate(SQLiteDatabase db)
		{
			String newTableQueryString = "create table " +
										name +
										" (" +
										rowid + " integer primary key autoincrement not null," +
										row1 + " text," +
										row2 + " text" +
										");";
			db.execSQL(newTableQueryString);
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
		}
	}
}