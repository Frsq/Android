package com.lanyuweng.mibaby.DataUtil; 

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lanyuweng.Config.Config;

public class DatabaseHelper extends SQLiteOpenHelper{

	public DatabaseHelper(Context context) {
		super(context,Config.DBNAME,null,Config.VERSION);
	}

	//IF NOT EXISTS 
	public static final String sql_createtable = "CREATE TABLE NoteItems (Note_title varchar, Note_content varchar, Note_create_time datetime)";
	public static final String sql_deletetable = "DROP TABLE IF EXISTS NoteItems";
	
	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(sql_createtable);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		if(newVersion > oldVersion){
			db.execSQL(sql_deletetable);
		}else{
			return;
		}
		onCreate(db);
		
	}

	
}
 