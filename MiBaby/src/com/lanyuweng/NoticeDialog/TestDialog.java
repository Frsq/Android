package com.lanyuweng.NoticeDialog; 

import java.util.zip.Inflater;

import com.lanyuweng.mibaby.R;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TestDialog extends DialogFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.dialog_notice_choice, container,false);
		return super.onCreateView(inflater, container, savedInstanceState);
		
	}
}
 