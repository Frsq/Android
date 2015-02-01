package com.lanyuweng.mibaby.fragment; 

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lanyuweng.mibaby.R;
import com.lanyuweng.mibaby.DataUtil.Note;

public class AnimalListAdapter extends BaseAdapter {
	
	private LayoutInflater mInflater = null;

	public AnimalListAdapter(Context context) {
		super();
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Note note = null;
		
		if(convertView == null){
			note = new Note();
			convertView = mInflater.inflate(R.layout.note_list_item, null);
			note.note_title = convertView.findViewById(R.id.tvNoteTitle).toString();
			note.note_content = convertView.findViewById(R.id.tvNoteContent).toString();
			note.note_create_time = convertView.findViewById(R.id.tvNoteCreateTime).toString();
			
			convertView.setTag(note);
			
		}else{
			note = (Note)convertView.getTag();
		}
		
		return null;
	}

}
 