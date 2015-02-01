package com.lanyuweng.mibaby.fragment; 

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lanyuweng.mibaby.R;
import com.lanyuweng.mibaby.SystemSettingActivity;

public class MoreFragment extends Fragment implements OnClickListener{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//		return super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.more_listview, container, false);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		TextView tvConfig = (TextView) getActivity().findViewById(R.id.tvConfig);
		tvConfig.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {

		switch (arg0.getId()) {
		case R.id.tvConfig:
			Intent intent = new Intent(getActivity().getBaseContext(),SystemSettingActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

	
	
}
 