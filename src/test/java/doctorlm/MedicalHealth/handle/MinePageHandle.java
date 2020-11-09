/**
 * Copyright (c)  2019 BFD-SoftwareStudio. All rights reserved.
 *
 * Function: 
 * @author: Allen Van
 * @date: 2019年12月26日 下午8:02:48
 */
package doctorlm.MedicalHealth.handle;

import org.testng.Reporter;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.page.MinePage;

/**
 * @ClassName:MinePageHandle.java
 * @author： Allen Van
 * @date: 2019年12月26日 下午8:02:48
 * @Description: 
 */
public class MinePageHandle {

	public MinePage minePage;
	public DriverBase driver;
	
	public MinePageHandle(DriverBase driver) {
		this.driver = driver;
		minePage = new MinePage(driver);
	}
	
	/*
	#我的页面
	M_MyOrder>className>myOrder>我的订单
	M_MyAddress>linkText>收货地址
	M_MyStore>linkText>我的收藏
	M_ZaiXianKeFu>linkText>在线客服
	M_YiYaoJianKang>linkText>医药健康>底部Banner医药健康
	M_BuyCar>linkText>购物车>底部Banner购物车
	*/
	
	//点击[我的订单]
	public void clickMyOrder() {
		minePage.click(minePage.getMyOrder());
		Reporter.log("点击[我的订单]");
	}
	
	//点击[收货地址]
	public void clickMyAddress() {
		minePage.click(minePage.getMyAddress());
		Reporter.log("点击[收货地址]");
	}
	
	//点击[我的收藏]
	public void clickMyStore() {
		minePage.click(minePage.getMyStore());
		Reporter.log("点击[我的收藏]");
	}
	
	//点击[在线客服]
	public void clickZaiXianKeFu() {
		minePage.click(minePage.getZaiXianKeFu());
		Reporter.log("点击[在线客服]");
	}
	
	//点击[医药健康]
	public void clickYiYaoJianKang() {
		minePage.click(minePage.getYiYaoJianKang());
		Reporter.log("点击[医药健康]");
	}
	
	//点击[购物车]
	public void clickBuyCar() {
		minePage.click(minePage.getBuyCar());
		Reporter.log("点击[购物车]");
	}
	
}
