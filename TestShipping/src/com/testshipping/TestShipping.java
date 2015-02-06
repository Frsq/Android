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
	private static String phone_no = "**********"; //填写输入的电话号码
	private static String phone_sc = " ";
	private static final int ticket_pay_way = 3; //默认使用现金账户支付，2为支付宝快捷支付，3为微信支付
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
		Log.v(Tag,"get activity。。。");
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

		//点击进入买船票的页面
		View textView = solo.getCurrentActivity().findViewById(4);//3、4、5分别代表主要当中的可点击的三个，按顺序进行
		solo.clickOnView(textView);
		
		if (solo.searchText("忘记密码")){
			Log.v(Tag,"未登录，进入登录界面");
			Log.v(Tag,"starting ooooo");
			solo.clearEditText(0);
			solo.enterText(0, phone_no);
			solo.clearEditText(1);
			solo.enterText(1, phone_sc);
			solo.clickOnButton("登录");
		}
		
		
		if (solo.searchText("充值")){
			Log.v(Tag, "已登录，登录成功...");
			Log.v(Tag, "get_user_info is success.");
			//TODO 截图操作
			solo.clickOnText("消费明细");
			solo.scrollDown();
			solo.goBack();
		}
		
		solo.clickOnText("退出登录");
		solo.waitForDialogToOpen(1000);
		solo.clickOnButton("确认");
	}
	
	public boolean IsLogout(){
		try{
			solo.clickOnText("退出登录");
			solo.waitForDialogToOpen(1000);
			solo.clickOnButton("确认");
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
		
		if(solo.searchText("充值")){
			if(IsLogout()){
				solo.sleep(1000);
				if(solo.searchText("忘记密码")){
					Log.v(Tag,"登出成功");
				}else{
					Log.v(Tag,"登出失败");
				}
			}else{
				Log.v(Tag,"登出失败");
			}
		}
		
		if(solo.searchText("忘记密码")){
			
		}
	}
	

	public void testMyOrderAndPay() throws Exception{
		Log.v(Tag,"我的订单");
		String clickorder = "本地订单";
		is_login = IsLogin();
		solo.waitForActivity(LAUNCHER_ACTIVITY_FULL_CLASSNAME, 1000);
		
		//点击进入买船票的页面
		View textView = solo.getCurrentActivity().findViewById(5);//3、4、5分别代表主要当中的可点击的三个，按顺序进行
		solo.clickOnView(textView);		
		
		if(is_login){
			//TODO
			clickorder = "我的订单";
		}else{
			//TODO
			clickorder = "本地订单";
		}


		solo.clickOnButton(clickorder);
		solo.clickOnButton("查询订单");
		solo.clickOnButton(clickorder);
		
		solo.clickOnButton(3);//本地订单，查询订单，船票订单；分别对应button index未1,2,3
		
		if(solo.searchText("未使用")){
			solo.clickOnText("未使用");
			solo.sleep(1000);
			
			if(solo.searchText("订单一")){
				if(solo.searchButton("立即付款")){
					solo.clickOnButton("立即付款");
					SelectPayWay(is_login);
				}
//				
//				if(solo.searchText("发送朋友")){
//					solo.clickOnText("发送朋友",1);
//					solo.waitForDialogToOpen();
//					solo.enterText(0, phone_no);
//					solo.clickOnButton("发送");
//				}
				
			}
//			solo.clickOnText("历史");
//			solo.sleep(1000);
//			solo.goBack();
			
		}		
//		solo.sleep(1000);
//		solo.clickOnText("查询订单");
//		solo.enterText(0, phone_no);
////		solo.clickOnButton("获取验证码");
//		solo.goBack();		
	}
	
	@SuppressWarnings("unused")
	public void testMyOrderAndCancelPay() throws Exception{
		Log.v(Tag,"我的订单");
		final String clickorder ;
		is_login = IsLogin();
		solo.waitForActivity(LAUNCHER_ACTIVITY_FULL_CLASSNAME, 1000);
		
		//点击进入买船票的页面
		View textView = solo.getCurrentActivity().findViewById(5);//3、4、5分别代表主要当中的可点击的三个，按顺序进行
		solo.clickOnView(textView);		
		
		if(is_login){
			//TODO
			clickorder = "我的订单";
		}else{
			//TODO
			clickorder = "本地订单";
		}
		solo.clickOnButton(3);
		if(solo.searchText("订单一")){
			if(solo.searchButton("取消订单")){
				solo.clickOnButton("取消订单");
				solo.waitForDialogToOpen(1000);
				solo.clickOnButton("放弃取消");
			}
			solo.waitForDialogToClose(1000);
			
			if(solo.searchButton("取消订单")){
				solo.clickOnButton("取消订单");
				solo.waitForDialogToOpen(1000);
				solo.clickOnButton("确认取消");
			}
		}
	}
	

	public void testOrderSingleTicket() throws Exception{
		Log.v(Tag,"买单程船票");
		Log.v(Tag,"testOrderSingleTicket。。。。");
		solo.waitForActivity(LAUNCHER_ACTIVITY_FULL_CLASSNAME, 5000);
		is_login=IsLogin();
		
		//点击进入买船票的页面
		View BuyTicket = solo.getCurrentActivity().findViewById(3);//3、4、5分别代表主要当中的可点击的三个，按顺序进行
		solo.clickOnView(BuyTicket);
		

		solo.clickOnRadioButton(0);//页面使用RadioButton(单程)为0，往返为1
		
		//选择出发地点
		Log.v(Tag, "选择出发地点");
		solo.clickOnText("出发地点");
		solo.clickOnText("广州-番禺莲花山");
		solo.sleep(1000);
		
		//选择到达地点
		Log.v(Tag, "选择到达地点");
		solo.clickOnText("到达地点");
		solo.clickOnText("香港-中港码头");
		solo.sleep(1000);
		
		//选择出发日期
		Log.v(Tag, "选择出发日期");
		solo.clickOnText("出发日期");
		//获取需要点击的时间日
		int day = GetCurrentDay();
		if(day<=10){
			//day为index
			day = day + 30;
		}else{
			day = day + 39;
		}	
		View time_day = null;
		time_day = solo.getView(TextView.class, day);
		solo.clickOnView(time_day);

		
		solo.clickOnButton("选择航班");
		
		//获取航班列表,固定选取当前能选取的最后的航班
//		ArrayList<TextView> list = solo.clickInList(0);
//		if (list.isEmpty()){
//			solo.clickOnText("前一天");
//		}
		solo.sleep(500);
		solo.clickInList(3, 0);
		
		
		solo.clickOnText("普通舱");
		solo.clickOnText("头等舱");
		
		solo.clickOnButton(3);
		solo.clickOnButton(5);
		solo.clickOnText("立即购票");
		
		if(solo.waitForDialogToOpen(1000)){
			Log.v(Tag,"成人票和儿童票不一致");
			if(solo.searchText("提示")){
				solo.clickOnButton("继续");
			}
		}
		
		SelectPayWay(is_login);
		Log.v(Tag,"完成买船票");
	}
	
	public void testOrderDoubleTicket() throws Exception{
		Log.v(Tag,"testOrderDoubleTicket starting...");
		
		solo.waitForActivity(LAUNCHER_ACTIVITY_FULL_CLASSNAME, 5000);
		is_login=IsLogin();
		
		//点击进入买船票的页面
		View BuyTicket = solo.getCurrentActivity().findViewById(3);//3、4、5分别代表主要当中的可点击的三个，按顺序进行
		solo.clickOnView(BuyTicket);
		
		solo.clickOnRadioButton(1);//页面使用RadioButton(单程)为0，往返为1
		
		solo.clickOnText("出发地点");
		solo.clickOnText("广州-番禺莲花山");
		solo.sleep(1000);
		
		solo.clickOnText("到达地点");
		solo.clickOnText("香港-中港码头");
		solo.sleep(1000);
		
		solo.clickOnText("出发日期");
		int day = GetCurrentDay();
		if(day<=10){
			//day为index
			day = day + 29;
		}else{
			day = day + 38;
		}
		
		View time_day = null;
		time_day = solo.getView(TextView.class, day);
		solo.clickOnView(time_day);
		
		
		solo.clickOnText("出发航班");
		solo.sleep(1000);
		solo.clickInList(3, 0);
		
	
		//返程日期使用默认输入的日期
		solo.clickOnText("返程航班");
		solo.clickInList(3, 0);
		solo.clickOnButton("下订单");
//		solo.clickOnButton(5);
		solo.clickOnButton("立即购票");
		SelectPayWay(is_login);
		Log.v(Tag,"购票完成");
	}
	
	
	//该方法需要在一个test最初进行调用
	private boolean IsLogin(){
		Log.v(Tag,"IsLogin。。。");
		solo.waitForActivity(LAUNCHER_ACTIVITY_FULL_CLASSNAME, 5000);
		
		//点击进入买船票的页面
		View BuyTicket = solo.getCurrentActivity().findViewById(4);//3、4、5分别代表主要当中的可点击的三个，按顺序进行
		solo.clickOnView(BuyTicket);
		if(solo.searchText("个人账户")){
			solo.goBack();
			return true;
		}
		solo.goBack();
		return false;
		
	}
	
	@SuppressWarnings("unused")
	private void Logout(){
		Log.v(Tag,"Logout e票通");
		if(IsLogin()){
			View BuyTicket = solo.getCurrentActivity().findViewById(4);//3、4、5分别代表主要当中的可点击的三个，按顺序进行
			solo.clickOnView(BuyTicket);
			solo.clickOnButton("退出登录");
		}
		solo.goBack();
	}
	
	//可能重复使用到的函数
	private int GetCurrentDay(){
		Time now_t = new Time();
		now_t.setToNow();
		now_day = now_t.monthDay;
		
		return now_day;
	}
	
	private void SelectPayWay(final boolean is_login){
		Log.v(Tag,"Select Pay way");
		
		if(solo.searchText("选择支付方式")){
			if(!is_login){
				Log.v(Tag,"未登录");
				if(solo.searchText("登录/注册")){
					solo.clickOnText("登录/注册");
					solo.sleep(1000);
					if(solo.searchText("会员中心")){
						//注册新用户
						solo.clickOnButton("注册");
						if(solo.searchText("请输入电话号码")){
							solo.enterText(0, phone_no);
							solo.sleep(5000);
							if(solo.searchText("号码已存在，请重新输入")){
								Log.v(Tag,"注册页面OK。。。");
								solo.goBack();
							}
						}
					
						//进入登录页面登录
						solo.clearEditText(0);
						solo.clearEditText(1);
						solo.enterText(0, phone_no);
						solo.enterText(1, phone_sc);
						solo.clickOnButton("登录");
					}
				}
			}else{
				Log.v(Tag,"已经登录");
				solo.clickOnText("充值");
				solo.goBack();
			}
			
			switch(ticket_pay_way){
			case 2:
				solo.clickOnText("支付宝快捷支付");
				break;
			case 3:
				solo.clickOnText("微信支付");
				break;
			default:
				solo.clickOnText("现金账户支付");
				break;
			}
		}

		solo.clickOnButton("立即支付");
		solo.sleep(5000);
		solo.goBack();
	}
}


