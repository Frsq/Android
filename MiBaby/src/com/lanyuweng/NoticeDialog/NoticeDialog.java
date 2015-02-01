package com.lanyuweng.NoticeDialog; 

import com.lanyuweng.mibaby.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class NoticeDialog extends Dialog implements OnClickListener{
	
	Context context;
	private NoticeDialogListener listener;
	
	public interface NoticeDialogListener{
		public void onClick(View view);
	}

	public NoticeDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public NoticeDialog(Context context, int theme) {
		super(context, theme);
		this.context = context;
	}

	protected NoticeDialog(Context context, int theme,
			NoticeDialogListener listener) {
		super(context, theme);
		this.context = context;
		this.listener = listener;
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.dialog_notice_choice);
		TextView notice_dialog_share 	= (TextView) findViewById(R.id.notice_dialog_share);
		TextView notice_dialog_delete	= (TextView) findViewById(R.id.notice_dialog_delete);
		TextView notice_dialog_details	= (TextView) findViewById(R.id.notice_dialog_details);
		TextView notice_dialog_edit		= (TextView) findViewById(R.id.notice_dialog_edit);
		
		notice_dialog_delete.setOnClickListener(this);
		notice_dialog_details.setOnClickListener(this);
		notice_dialog_edit.setOnClickListener(this);
		notice_dialog_share.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		listener.onClick(v);
	}

}
 