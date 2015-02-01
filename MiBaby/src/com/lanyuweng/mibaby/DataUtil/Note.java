package com.lanyuweng.mibaby.DataUtil; 

import java.text.SimpleDateFormat;
import java.util.Date;

public class Note {

	public String note_title;
	public String note_content;
	public String note_create_time;
	
	public Note(){
		
	}
	
	public Note(String note_title, String note_content, String note_create_time) {
		this.note_title 		= note_title;
		this.note_content 		= note_content;
		this.note_create_time 	= note_create_time;
	}
	
	
	public String getNote_title() {
		return note_title;
	}
	public void setNote_title(String note_title) {
		this.note_title = note_title;
	}
	public String getNote_content() {
		return note_content;
	}
	public void setNote_content(String note_content) {
		this.note_content = note_content;
	}
	public String getNote_create_time() {
		return note_create_time;
	}
	public void setNote_create_time() {
		this.note_create_time = GetStringFromLong(System.currentTimeMillis());
		
	}
	
	private String GetStringFromLong(long millis) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd HH:MM");
		java.util.Date dt = new Date(millis);
		return sdf.format(dt);
	}
	
}
 