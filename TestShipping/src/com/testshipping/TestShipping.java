package com.testshipping;

import android.test.ActivityInstrumentationTestCase2;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.robotium.solo.Solo;
//import android.widget.RelativeLayout;



@SuppressWarnings("rawtypes")
public class TestShipping extends ActivityInstrumentationTestCase2 {
	
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.ziipin.ship.ui.LauncherActivity";
	private static final String Tag = "ShippingLog";
	
	
	private static Class launcherActivityClass;
	private static String phone_no = "18589028920";
	private static String phone_sc = " ";
	private static final int ticket_pay_way = 3; //Ĭ��ʹ���ֽ��˻�֧����2Ϊ֧�������֧����3Ϊ΢��֧��
	private static int now_day;
	private boolean is_login = true;

	
//
//	public TestShipping(Class activityClass) {
//		super(activityClass);
//		// TODO Auto-generated constructor stub
//	}

	static{
		try{
			launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
		}catch(ClassNotFoundException e){
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public TestShipping() throws ClassNotFoundException{
		super(launcherActivityClass);
	}
	
	private Solo solo;
	
	@Override
	protected void setUp() throws Exception{
		solo = new Solo(getInstrumentation(),getActivity());
//		getUserInfo();
		Log.v(Tag,"get activity������");
	}
	
	@Override
	public void tearDown() throws Exception{
		try{
			solo.finalize();
		}catch (Throwable e){
			e.printStackTrace();
		}
		solo.finishOpenedActivities();
		super.tearDown();
	}
	
	public void testLogin() throws Exception{
		Log.v(Tag, "testLogin is starting");
//		final String CurrentActivity;
		
		solo.waitForActivity(LAUNCHER_ACTIVITY_FULL_CLASSNAME, 5000);

		//���������Ʊ��ҳ��
		View textView = solo.getCurrentActivity().findViewById(4);//3��4��5�ֱ������Ҫ���еĿɵ������������˳�����
		solo.clickOnView(textView);
		
		if (solo.searchText("��������")){
			Log.v(Tag,"δ��¼�������¼����");
			Log.v(Tag,"starting ooooo");
			solo.clearEditText(0);
			solo.enterText(0, phone_no);
			solo.clearEditText(1);
			solo.enterText(1, phone_sc);
			solo.clickOnButton("��¼");
		}
		
		
		if (solo.searchText("��ֵ")){
			Log.v(Tag, "�ѵ�¼����¼�ɹ�...");
			Log.v(Tag, "get_user_info is success.");
			//TODO ��ͼ����
			solo.clickOnText("������ϸ");
			solo.scrollDown();
			solo.goBack();
		}
		
		solo.clickOnText("�˳���¼");
		solo.waitForDialogToOpen(1000);
		solo.clickOnButton("ȷ��");
	}
	
	public boolean IsLogout(){
		try{
			solo.clickOnText("�˳���¼");
			solo.waitForDialogToOpen(1000);
			solo.clickOnButton("ȷ��");
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public void testLogout() throws Exception{
		solo.waitForActivity(LAUNCHER_ACTIVITY_FULL_CLASSNAME, 5000);
		View textView = solo.getCurrentActivity().findViewById(4);
		solo.clickOnView(textView);
		
		if(solo.searchText("��ֵ")){
			if(IsLogout()){
				solo.sleep(1000);
				if(solo.searchText("��������")){
					Log.v(Tag,"�ǳ��ɹ�");
				}else{
					Log.v(Tag,"�ǳ�ʧ��");
				}
			}else{
				Log.v(Tag,"�ǳ�ʧ��");
			}
		}
		
		if(solo.searchText("��������")){
			
		}
	}
	

	public void testMyOrderAndPay() throws Exception{
		Log.v(Tag,"�ҵĶ���");
		String clickorder = "���ض���";
		is_login = IsLogin();
		solo.waitForActivity(LAUNCHER_ACTIVITY_FULL_CLASSNAME, 1000);
		
		//���������Ʊ��ҳ��
		View textView = solo.getCurrentActivity().findViewById(5);//3��4��5�ֱ������Ҫ���еĿɵ������������˳�����
		solo.clickOnView(textView);		
		
		if(is_login){
			//TODO
			clickorder = "�ҵĶ���";
		}else{
			//TODO
			clickorder = "���ض���";
		}


		solo.clickOnButton(clickorder);
		solo.clickOnButton("��ѯ����");
		solo.clickOnButton(clickorder);
		
		solo.clickOnButton(3);//���ض�������ѯ��������Ʊ�������ֱ��Ӧbutton indexδ1,2,3
		
		if(solo.searchText("δʹ��")){
			solo.clickOnText("δʹ��");
			solo.sleep(1000);
			
			if(solo.searchText("����һ")){
				if(solo.searchButton("��������")){
					solo.clickOnButton("��������");
					SelectPayWay(is_login);
				}
//				
//				if(solo.searchText("��������")){
//					solo.clickOnText("��������",1);
//					solo.waitForDialogToOpen();
//					solo.enterText(0, phone_no);
//					solo.clickOnButton("����");
//				}
				
			}
//			solo.clickOnText("��ʷ");
//			solo.sleep(1000);
//			solo.goBack();
			
		}		
//		solo.sleep(1000);
//		solo.clickOnText("��ѯ����");
//		solo.enterText(0, phone_no);
////		solo.clickOnButton("��ȡ��֤��");
//		solo.goBack();		
	}
	
	@SuppressWarnings("unused")
	public void testMyOrderAndCancelPay() throws Exception{
		Log.v(Tag,"�ҵĶ���");
		final String clickorder ;
		is_login = IsLogin();
		solo.waitForActivity(LAUNCHER_ACTIVITY_FULL_CLASSNAME, 1000);
		
		//���������Ʊ��ҳ��
		View textView = solo.getCurrentActivity().findViewById(5);//3��4��5�ֱ������Ҫ���еĿɵ������������˳�����
		solo.clickOnView(textView);		
		
		if(is_login){
			//TODO
			clickorder = "�ҵĶ���";
		}else{
			//TODO
			clickorder = "���ض���";
		}
		solo.clickOnButton(3);
		if(solo.searchText("����һ")){
			if(solo.searchButton("ȡ������")){
				solo.clickOnButton("ȡ������");
				solo.waitForDialogToOpen(1000);
				solo.clickOnButton("����ȡ��");
			}
			solo.waitForDialogToClose(1000);
			
			if(solo.searchButton("ȡ������")){
				solo.clickOnButton("ȡ������");
				solo.waitForDialogToOpen(1000);
				solo.clickOnButton("ȷ��ȡ��");
			}
		}
	}
	

	public void testOrderSingleTicket() throws Exception{
		Log.v(Tag,"�򵥳̴�Ʊ");
		Log.v(Tag,"testOrderSingleTicket��������");
		solo.waitForActivity(LAUNCHER_ACTIVITY_FULL_CLASSNAME, 5000);
		is_login=IsLogin();
		
		//���������Ʊ��ҳ��
		View BuyTicket = solo.getCurrentActivity().findViewById(3);//3��4��5�ֱ������Ҫ���еĿɵ������������˳�����
		solo.clickOnView(BuyTicket);
		

		solo.clickOnRadioButton(0);//ҳ��ʹ��RadioButton(����)Ϊ0������Ϊ1
		
		//ѡ������ص�
		Log.v(Tag, "ѡ������ص�");
		solo.clickOnText("�����ص�");
		solo.clickOnText("����-��خ����ɽ");
		solo.sleep(1000);
		
		//ѡ�񵽴�ص�
		Log.v(Tag, "ѡ�񵽴�ص�");
		solo.clickOnText("����ص�");
		solo.clickOnText("���-�и���ͷ");
		solo.sleep(1000);
		
		//ѡ���������
		Log.v(Tag, "ѡ���������");
		solo.clickOnText("��������");
		//��ȡ��Ҫ�����ʱ����
		int day = GetCurrentDay();
		if(day<=10){
			//dayΪindex
			day = day + 30;
		}else{
			day = day + 39;
		}	
		View time_day = null;
		time_day = solo.getView(TextView.class, day);
		solo.clickOnView(time_day);

		
		solo.clickOnButton("ѡ�񺽰�");
		
		//��ȡ�����б�,�̶�ѡȡ��ǰ��ѡȡ�����ĺ���
//		ArrayList<TextView> list = solo.clickInList(0);
//		if (list.isEmpty()){
//			solo.clickOnText("ǰһ��");
//		}
		solo.sleep(500);
		solo.clickInList(3, 0);
		
		
		solo.clickOnText("��ͨ��");
		solo.clickOnText("ͷ�Ȳ�");
		
		solo.clickOnButton(3);
		solo.clickOnButton(5);
		solo.clickOnText("������Ʊ");
		
		if(solo.waitForDialogToOpen(1000)){
			Log.v(Tag,"����Ʊ�Ͷ�ͯƱ��һ��");
			if(solo.searchText("��ʾ")){
				solo.clickOnButton("����");
			}
		}
		
		SelectPayWay(is_login);
		Log.v(Tag,"�����Ʊ");
	}
	
	public void testOrderDoubleTicket() throws Exception{
		Log.v(Tag,"testOrderDoubleTicket starting...");
		
		solo.waitForActivity(LAUNCHER_ACTIVITY_FULL_CLASSNAME, 5000);
		is_login=IsLogin();
		
		//���������Ʊ��ҳ��
		View BuyTicket = solo.getCurrentActivity().findViewById(3);//3��4��5�ֱ������Ҫ���еĿɵ������������˳�����
		solo.clickOnView(BuyTicket);
		
		solo.clickOnRadioButton(1);//ҳ��ʹ��RadioButton(����)Ϊ0������Ϊ1
		
		solo.clickOnText("�����ص�");
		solo.clickOnText("����-��خ����ɽ");
		solo.sleep(1000);
		
		solo.clickOnText("����ص�");
		solo.clickOnText("���-�и���ͷ");
		solo.sleep(1000);
		
		solo.clickOnText("��������");
		int day = GetCurrentDay();
		if(day<=10){
			//dayΪindex
			day = day + 29;
		}else{
			day = day + 38;
		}
		
		View time_day = null;
		time_day = solo.getView(TextView.class, day);
		solo.clickOnView(time_day);
		
		
		solo.clickOnText("��������");
		solo.sleep(1000);
		solo.clickInList(3, 0);
		
	
		//��������ʹ��Ĭ�����������
		solo.clickOnText("���̺���");
		solo.clickInList(3, 0);
		solo.clickOnButton("�¶���");
//		solo.clickOnButton(5);
		solo.clickOnButton("������Ʊ");
		SelectPayWay(is_login);
		Log.v(Tag,"��Ʊ���");
	}
	
	
	//�÷�����Ҫ��һ��test������е���
	private boolean IsLogin(){
		Log.v(Tag,"IsLogin������");
		solo.waitForActivity(LAUNCHER_ACTIVITY_FULL_CLASSNAME, 5000);
		
		//���������Ʊ��ҳ��
		View BuyTicket = solo.getCurrentActivity().findViewById(4);//3��4��5�ֱ������Ҫ���еĿɵ������������˳�����
		solo.clickOnView(BuyTicket);
		if(solo.searchText("�����˻�")){
			solo.goBack();
			return true;
		}
		solo.goBack();
		return false;
		
	}
	
	@SuppressWarnings("unused")
	private void Logout(){
		Log.v(Tag,"Logout eƱͨ");
		if(IsLogin()){
			View BuyTicket = solo.getCurrentActivity().findViewById(4);//3��4��5�ֱ������Ҫ���еĿɵ������������˳�����
			solo.clickOnView(BuyTicket);
			solo.clickOnButton("�˳���¼");
		}
		solo.goBack();
	}
	
	//�����ظ�ʹ�õ��ĺ���
	private int GetCurrentDay(){
		Time now_t = new Time();
		now_t.setToNow();
		now_day = now_t.monthDay;
		
		return now_day;
	}
	
	private void SelectPayWay(final boolean is_login){
		Log.v(Tag,"Select Pay way");
		
		if(solo.searchText("ѡ��֧����ʽ")){
			if(!is_login){
				Log.v(Tag,"δ��¼");
				if(solo.searchText("��¼/ע��")){
					solo.clickOnText("��¼/ע��");
					solo.sleep(1000);
					if(solo.searchText("��Ա����")){
						//ע�����û�
						solo.clickOnButton("ע��");
						if(solo.searchText("������绰����")){
							solo.enterText(0, phone_no);
							solo.sleep(5000);
							if(solo.searchText("�����Ѵ��ڣ�����������")){
								Log.v(Tag,"ע��ҳ��OK������");
								solo.goBack();
							}
						}
					
						//�����¼ҳ���¼
						solo.clearEditText(0);
						solo.clearEditText(1);
						solo.enterText(0, phone_no);
						solo.enterText(1, phone_sc);
						solo.clickOnButton("��¼");
					}
				}
			}else{
				Log.v(Tag,"�Ѿ���¼");
				solo.clickOnText("��ֵ");
				solo.goBack();
			}
			
			switch(ticket_pay_way){
			case 2:
				solo.clickOnText("֧�������֧��");
				break;
			case 3:
				solo.clickOnText("΢��֧��");
				break;
			default:
				solo.clickOnText("�ֽ��˻�֧��");
				break;
			}
		}

		solo.clickOnButton("����֧��");
		solo.sleep(5000);
		solo.goBack();
	}
}


