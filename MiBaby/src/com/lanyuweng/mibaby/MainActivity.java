package com.lanyuweng.mibaby;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CalendarView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.lanyuweng.Config.Config;
import com.lanyuweng.mibaby.DataUtil.DatabaseManager;
import com.lanyuweng.mibaby.fragment.FragmentAdapter;

public class MainActivity extends FragmentActivity implements OnClickListener{
	
	private static final String TAG = "MainActivity-----";
	
	public static final int TAB_CALENDAR = 0;
	public static final int TAB_REMINDER = 1;
	public static final int TAB_NOTE	= 2;
	public static final int TAB_MORE	= 3;

	private CalendarView calendarView;
	
	private ViewPager viewPager;
	private TextView tvCalendar,tvReminder,tvNote,tvMore;
	
	//定义适配器
	private FragmentAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		initData();
		initView();
	}

	private void initData() {

	}

	private void initView() {
		
		Log.i(TAG,"initView----");

		viewPager  = (ViewPager)findViewById(R.id.viewpager);
		tvCalendar = (TextView) findViewById(R.id.tvCalendar);
		tvReminder = (TextView) findViewById(R.id.tvReminder);
		tvNote = (TextView) findViewById(R.id.tvNote);
		tvMore = (TextView) findViewById(R.id.tvMore);

		tvCalendar.setOnClickListener(this);
		tvReminder.setOnClickListener(this);
		tvNote.setOnClickListener(this);
		tvMore.setOnClickListener(this);
		
		adapter = new FragmentAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);
	}

	@Override
	public void onClick(View arg0) {

		switch (arg0.getId()) {
		case R.id.tvCalendar:
			viewPager.setCurrentItem(TAB_CALENDAR);
			findViewById(R.id.tvConfig);
			break;

		case R.id.tvMore:
			viewPager.setCurrentItem(TAB_MORE);
			break;

		case R.id.tvNote:
			viewPager.setCurrentItem(TAB_NOTE);
			break;

		case R.id.tvReminder:
			viewPager.setCurrentItem(TAB_REMINDER);
			break;

		default:
			break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if(1000==requestCode){
			
			Log.i(TAG, "requestCode = 1000");
			String new_note_title 	= data.getExtras().getString("NoteTitle");
			String new_note_content = data.getExtras().getString("NoteContent");
			
			//添加到数据库
			Log.i(TAG,"new_note_title:"+new_note_title+"; new_note_content:"+new_note_content);
			//TODO: 未实现更新ListView
//			adapter.notifyDataSetChanged();
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
}
