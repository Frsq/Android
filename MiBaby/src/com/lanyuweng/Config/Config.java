package com.lanyuweng.Config; 

import java.text.SimpleDateFormat;
import java.util.Date;

public class Config {
	
	public static final String DBNAME = "lanyuweng.db";
	public static final int VERSION = 1;
	
	public static String getStringDateShort(){
		
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
		String dateString = formatter.format(currentTime);
		return dateString;
		
	}

}
 