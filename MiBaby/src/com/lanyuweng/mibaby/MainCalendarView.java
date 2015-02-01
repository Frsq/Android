package com.lanyuweng.mibaby; 

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CalendarView;

public class MainCalendarView extends CalendarView {

	public MainCalendarView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MainCalendarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MainCalendarView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setFirstDayOfWeek(int firstDayOfWeek) {
		// TODO Auto-generated method stub
//		super.setFirstDayOfWeek(firstDayOfWeek);
		this.setFirstDayOfWeek(3);
	}

	
}
 