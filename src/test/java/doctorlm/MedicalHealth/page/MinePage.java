/**
 * Copyright (c)  2019 BFD-SoftwareStudio. All rights reserved.
 *
 * Function: 
 * @author: Allen Van
 * @date: 2019年12月26日 下午7:49:50
 */
package doctorlm.MedicalHealth.page;

import org.openqa.selenium.WebElement;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.util.GetData;

/**
 * @ClassName:MinePage.java
 * @author： Allen Van
 * @date: 2019年12月26日 下午7:49:50
 * @Description:  页面元素类  [我的]  页面
 */
public class MinePage extends PageBase{

	public MinePage(DriverBase driver) {
		super(driver);
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
	
	//获取元素 我的订单 Banner
	public WebElement getMyOrder() {
		return getElement(GetData.getBy("doctor", "M_MyOrder"));
	}
	
	//获取元素 收货地址 Banner
	public WebElement getMyAddress() {
		return getElement(GetData.getBy("doctor", "M_MyAddress"));
	}
	
	//获取元素 我的收藏 Banner
	public WebElement getMyStore() {
		return getElement(GetData.getBy("doctor", "M_MyStore"));
	}
	
	//获取元素 在线客服 Banner
	public WebElement getZaiXianKeFu() {
		return getElement(GetData.getBy("doctor", "M_ZaiXianKeFu"));
	}
	
	//获取元素 医药健康 底部Banner
	public WebElement getYiYaoJianKang() {
		return getElement(GetData.getBy("doctor", "M_YiYaoJianKang"));
	}
	
	//获取元素 购物车 底部Banner
	public WebElement getBuyCar() {
		return getElement(GetData.getBy("doctor", "M_BuyCar"));
	}
}
