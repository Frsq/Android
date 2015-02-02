package com.lanyuweng.mibaby.fragment; 


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lanyuweng.mibaby.R;
import com.lanyuweng.mibaby.fragment.CalendarView.OnItemClickListener;

public class CalendarFragment extends Fragment {
	
	private Context context;

	private CalendarView calendar;
	private ImageButton calendarLeft;
	private TextView calenderCenter;
	private ImageButton calendarRight;
	private SimpleDateFormat format;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.calendar_fragment, container,false);
		format = new SimpleDateFormat("yyyy-MM-dd");
		calendar = (CalendarView) view.findViewById(R.id.calendar);
		calendar.setSelected(false);
		
		calendarLeft = (ImageButton) view.findViewById(R.id.calendarLeft);
		calendarRight = (ImageButton) view.findViewById(R.id.calendarRight);
		calenderCenter = (TextView) view.findViewById(R.id.calendarCenter);
		
		try {
			Date date = format.parse("2015-01-01");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String[] ya = calendar.getYearAndmonth().split("-");
		calenderCenter.setText(ya[0]+"��"+ya[1]+"��");
		calendarLeft.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String leftYearAndmonth = calendar.clickLeftMonth();
				String[] ya = leftYearAndmonth.split("-");
				calenderCenter.setText(ya[0]+"��"+ya[1]+"��");
			}
		});
		
		calendarRight.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//�����һ��
				String rightYearAndmonth = calendar.clickRightMonth();
				String[] ya = rightYearAndmonth.split("-"); 
				calenderCenter.setText(ya[0]+"��"+ya[1]+"��");
			}
		});
		
		//���ÿؼ����������Լ����������ÿһ�죨���Ҳ�����ڿؼ��и��������趨��
		calendar.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void OnItemClick(Date selectedStartDate,
					Date selectedEndDate, Date downDate) {
				if(calendar.isSelectMore()){
					Toast.makeText(getActivity().getApplicationContext(), format.format(selectedStartDate)+"��"+format.format(selectedEndDate), Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(getActivity().getApplicationContext(), format.format(downDate), Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		return view;
	}


}
 