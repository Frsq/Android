package com.lanyuweng.mibaby.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lanyuweng.mibaby.AddNoteActivity;
import com.lanyuweng.mibaby.R;
import com.lanyuweng.mibaby.DataUtil.DatabaseManager;

public class NoteFragment extends ListFragment implements OnScrollListener,
		OnClickListener,OnItemClickListener,OnItemLongClickListener {

	public static final String TAG = "NoteFragment";
	public Context context;
	public Activity activity;
	private Cursor cursor;

	private SimpleAdapter adapter;
	private DatabaseManager db_manager;

	private ListView listView;
	// all the notes in the database
	private int totalCount;
	private TextView tvNoteAddItem;

	List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(TAG, "------onCreateView------");

		View view = inflater.inflate(R.layout.note_listview, container, false);
		listView = (ListView) view.findViewById(android.R.id.list);
		tvNoteAddItem = (TextView) view.findViewById(R.id.tvNoteAddItem);
		registerForContextMenu(listView);
		listView.setOnScrollListener(this);
		tvNoteAddItem.setOnClickListener(this);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		Log.i(TAG, "------onActivityCreated------");


		totalCount = db_manager.getTotalCount();
		Log.i(TAG, "the note counts of the databse is:" + totalCount);
		loadAllNoteItem();

		adapter = new SimpleAdapter(getActivity(), listItems,
				R.layout.note_list_item, new String[] { "tvNoteTitle",
						"tvNoteContent", "tvNoteCreateTime" }, new int[] {
						R.id.tvNoteTitle, R.id.tvNoteContent,
						R.id.tvNoteCreateTime });
		setListAdapter(adapter);
		getListView().setOnItemLongClickListener(this);
		getListView().setOnItemClickListener(this);

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		Log.i(TAG, "------onCreate------");

		super.onCreate(savedInstanceState);
		context = getActivity();
		activity = getActivity();
		db_manager = new DatabaseManager(context);

	}


	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		totalCount = db_manager.getTotalCount();
		if(totalItemCount < totalCount){
			loadAllNoteItem();
			Log.i(TAG,"onScroll.......");
//			adapter.notifyDataSetChanged();
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView arg0, int arg1) {

	}

	@Override
	public void onClick(View arg0) {

		switch (arg0.getId()) {
		case R.id.tvNoteAddItem:
			Log.i(TAG, "click add noteitem");
			Intent intent = new Intent(getActivity().getBaseContext(),
					AddNoteActivity.class);
			startActivity(intent);
			break;

		
		default:
			break;
		}
	}

	@Override
	public void onResume() {

		Log.i(TAG, "onResume----");
		totalCount = db_manager.getTotalCount();
		loadAllNoteItem();
		adapter.notifyDataSetChanged();
		super.onResume();
	}

	@Override
	public void onDestroyView() {

		super.onDestroyView();
		Log.i(TAG,"onDestroyView------");
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		Toast.makeText(context, "handle item long click", Toast.LENGTH_LONG).show();
		arg0.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
			
			@Override
			public void onCreateContextMenu(ContextMenu arg0, View arg1,
					ContextMenuInfo arg2) {
				
				arg0.setHeaderTitle("Test");
				arg0.add(0, Menu.FIRST, 0, "Test1");
				arg0.add(0, Menu.FIRST+1, 0, "TEST2");
				arg0.add(0, Menu.FIRST+2,0, "DELETE");
				
			}
			
			
		});
		return false;
	}

	private void DeleteNoteItem(int arg2) {
		
		String NoteItemTitle = listItems.get(arg2).get("tvNoteTitle").toString();
		db_manager.del_NoteItem(NoteItemTitle);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		if (arg2 == 0){
//			Toast.makeText(context, "handle item click", Toast.LENGTH_LONG).show();
		}
	}
	
	private void loadAllNoteItem(){
		cursor = db_manager.selectAll_NoteItems();
		listItems.removeAll(listItems);
		if (cursor != null) {
			for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
					.moveToNext()) {
				Map<String, Object> listItem = new HashMap<String, Object>();
				listItem.put("tvNoteTitle", cursor.getString(0));
				listItem.put("tvNoteContent", cursor.getString(1));
				listItem.put("tvNoteCreateTime", cursor.getString(2));
				listItems.add(listItem);
			}
		} else {
			Log.i(TAG, "cursor is null");
		}
		cursor.close();		
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case Menu.FIRST:
			
			break;

		case Menu.FIRST+1:
			break;
		
		case Menu.FIRST+2:
			DeleteNoteItem(2);
			loadAllNoteItem();
			adapter.notifyDataSetChanged();
			break;
		
		default:
			break;
		}
		return super.onContextItemSelected(item);
		
	}
}
