package com.lanyuweng.mibaby.fragment; 

import com.lanyuweng.mibaby.MainActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {

	public final static int TAB_COUNT = 4;
	public FragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {

		switch (arg0) {
		case MainActivity.TAB_CALENDAR:
			CalendarFragment calendarFragment = new CalendarFragment();
			return calendarFragment;
			
		case MainActivity.TAB_REMINDER:
			ReminderFragment reminderFragment = new ReminderFragment();
			return reminderFragment;
			
		case MainActivity.TAB_NOTE:
			NoteFragment noteFragment = new NoteFragment();
			return noteFragment;
		case MainActivity.TAB_MORE:
			MoreFragment moreFragment = new MoreFragment();
			return moreFragment;
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return TAB_COUNT;
	}

}
 