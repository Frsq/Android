package com.lanyuweng.mibaby.DataUtil; 

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {

	public SQLiteDatabase sql_db;
	public DatabaseHelper sql_db_helper;
	private Cursor cursor;
	
	public DatabaseManager(Context context) {
		
		sql_db_helper = new DatabaseHelper(context);
		sql_db = sql_db_helper.getWritableDatabase();
		
	}
	
	public void add_NoteItem(String note_title,String note_content,String note_create_time){
		
		String sql = "INSERT INTO NoteItems(Note_title, Note_content, Note_create_time) values"+
									"('"+note_title+"','"+note_content+"','"+note_create_time+"')";
		sql_db.beginTransaction();
		
		try {
			sql_db.execSQL(sql);
			sql_db.setTransactionSuccessful();
		}
		finally{
			sql_db.endTransaction();			
		}
	}
	
	public void del_NoteItem(String note_title){
		
		String sql = "DELETE FROM NoteItems WHERE Note_title= '"+note_title+"'";
		sql_db.beginTransaction();
		try {
			sql_db.execSQL(sql);
			sql_db.setTransactionSuccessful();
		}
		finally{
			sql_db.endTransaction();			
		}
	}
	
	public void modify_NoteIem(String old_note_title,String new_note_title,String note_content,String note_create_time){
		
		String sql = "UPDATE TABLE NoteItems SET Note_title="+new_note_title+", Note_content="+note_content+",note_create_time="+note_create_time+"where Note_title="+old_note_title;
		sql_db.beginTransaction();
		try {
			sql_db.execSQL(sql);
			sql_db.setTransactionSuccessful();
		}
		finally{
			sql_db.endTransaction();
		}
	}
	
	public Cursor search_NoteItem(String note_title){
		
		String sql = "SELECT * FROM NoteItems WHERE Note_title LIKE"+"%"+note_title+"%";
		sql_db.beginTransaction();
		try {
			cursor = sql_db.rawQuery(sql, null);
			sql_db.setTransactionSuccessful();
		}
		finally{
			sql_db.endTransaction();			
		}
		return cursor;
	}
	
	public Cursor selectAll_NoteItems(){
		
		String sql = "SELECT * FROM NoteItems";
		sql_db.beginTransaction();
		
		try {
			cursor = sql_db.rawQuery(sql, null);
		} finally{
			sql_db.endTransaction();
		}
		return cursor;
		
	}
	
	public int getTotalCount(){	
		
		String sql = "SELECT COUNT(*) FROM NoteItems";
		int getTotalCount = 0;
		sql_db.beginTransaction();
		
		try{
			cursor = sql_db.rawQuery(sql, null);
			cursor.moveToFirst();
			getTotalCount = cursor.getInt(0);
		}finally{
			sql_db.endTransaction();
		}
		return getTotalCount;
		
	}
	
	public Cursor getLimitItems(int start,int end){
		
		String sql = "SELECT * FROM NoteItems LIMIT "+start+","+end;
		
		sql_db.beginTransaction();
		try {
			cursor = sql_db.rawQuery(sql,null);
			cursor.moveToFirst();
			
		} finally{
			sql_db.endTransaction();
		}
		return cursor;
	}
	
}
 