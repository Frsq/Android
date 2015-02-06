package com.testshipping;

import com.robotium.solo.Solo;


public class API_Shipping {
	
	private Solo solo;
	//API for Shipping
	/*
	 * 
	 */
	
	public void logIn(final String userName, final String passWord){
		solo.clearEditText(0);
		solo.clearEditText(1);
		solo.enterText(0, userName);
		solo.enterText(1, passWord);
	}
	
	public void logOut(){
		solo.clickOnButton("退出登录");
		solo.sleep(2000);
		solo.clickOnButton("确认");
	}
	
	public boolean isLogin(){
		if(solo.searchText("充值")||solo.searchText("个人账户")){
			return true;
		}else{
			return false;
		}
	}
}
