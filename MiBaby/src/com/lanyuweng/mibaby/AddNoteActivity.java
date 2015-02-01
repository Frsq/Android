package com.lanyuweng.mibaby; 

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.lanyuweng.Config.Config;
import com.lanyuweng.mibaby.DataUtil.DatabaseManager;

public class AddNoteActivity extends Activity implements OnClickListener{
	
	private static final String TAG = "AddNoteActivity----";

	private EditText etNoteTitle;
	private EditText etNoteContent;
	private Button btnSaveContent;

	private DatabaseManager db_manager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_additem);
		Log.i(TAG,"AddNoteAcitivity onCreate----");
		init();
	}

	private void init() {

		db_manager = new DatabaseManager(getBaseContext());
		etNoteTitle 	= (EditText)findViewById(R.id.etNoteTitle);
		etNoteContent 	= (EditText)findViewById(R.id.etNoteContent);
		btnSaveContent	= (Button)	findViewById(R.id.btnSaveNote);
		
		btnSaveContent.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {

		switch (arg0.getId()) {
		case R.id.btnSaveNote:
			
			String new_note_title 	= etNoteTitle.getText().toString();
			String new_note_content 	= etNoteContent.getText().toString();
			Log.i(TAG, "addNoteTitle:"+new_note_title+"addNoteContent:"+new_note_content);
			
			if(!new_note_title.isEmpty()){
				//添加到数据库
				db_manager.add_NoteItem(new_note_title, new_note_content, Config.getStringDateShort().toString());
				finish();
			}
			break;

		default:
			break;
		}
	}
}
