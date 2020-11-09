/**
 * Copyright (c)  2019 BFD-SoftwareStudio. All rights reserved.
 *
 * Function: 
 * @author: Allen Van
 * @date: 2019年12月26日 下午8:49:31
 */
package doctorlm.MedicalHealth.test;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.handle.MinePageHandle;
import doctorlm.MedicalHealth.page.PageBase;

/**
 * @ClassName:TestHandle.java
 * @author： Allen Van
 * @date: 2019年12月26日 下午8:49:31
 * @Description: 
 */
public class TestHandle extends CaseBase{
	
	public DriverBase driver;
	public PageBase pageBase;
	public MinePageHandle mpHandle;
	
	@BeforeTest
	public void before() {
		driver = InitDriverBase("AppChrome");
		mpHandle = new MinePageHandle(driver);
		driver.setWindowSize(1000, 1000);
		driver.implicitlyWait(5);
	}
	
	@Test
	public void test() {
		
		String url_head = "http://test.runtvip.com";
//				+ "/UserCenter/Person/index";
		String url_last = "?userinfo=8nrxdqggr4sW0ZTKr7fqEB6i8zZQ3RIwfhKN7M8ajgrQLvBtRgKLLC6bvgL/gi+/B41on+3jpBI/TzQXEq7rMVRyUAAmo/7tyfGt72EUZlPyFn5+z/XxQ8Y75LQx3XcTREPs5mrhAHm0uNJuzsQeQS11/hWdNelbggwRzgOusdjS4pKF0anA4A==";
		String url = url_head+url_last;
		
		driver.get(url);
		//我的  首页、购物车页面
		pageBase.actionClick(pageBase.getElement(By.xpath("//*[contains(@src,'/image/tabBar/userCenter.png')]")));
		driver.sleep(1);
		//购物车
//		driver.actionClick(driver.getElement(By.xpath("//*[contains(@src,'/image/tabBar/tabber-car.png')]")));
//		driver.sleep(2);
		//我的  首页、购物车页面
//		driver.actionClick(driver.getElement(By.xpath("//*[contains(@src,'/image/tabBar/userCenter.png')]")));
		//医药健康
//		driver.actionClick(driver.getElement(By.xpath("//*[contains(@src,'/image/tabBar/index.png')]")));
//		driver.sleep(2);
		
//		mpHandle.clickMyOrder();
//		driver.actionClick(driver.getElement(GetData.getBy("doctor", "myOrder")));
//		driver.actionClick(driver.getElements(By.className("img-go")).get(0));
//		driver.actionClick(driver.getElement(By.xpath("//*[contains(@onclick,'javascript:window.location.href='/Order/MyOrder/List\'')]")));
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("$('.align_center')[0].click");
//		js.executeScript("document.getElementsByClassName(\"myOrder\")[0].click");
//		js.executeScript("document.getElementsByClassName(\"titem all_center active\")[0].click");
//		driver.actionClick(driver.getElement(By.xpath("//*[text()='我的订单']")));
//		driver.actionClick(driver.getElement(By.xpath("/html/body/div[1]/div[3]")));
//		driver.actionClick(driver.getElement(By.cssSelector("body>div.person-header>div.myOrder.align_center")));
//		WebElement element = driver.getElement(By.className("myOrder"));
//		element.click();
//		System.out.println(element);
		driver.sleep(2);
//		driver.back();
//		mpHandle.clickMyAddress();
		pageBase.click(pageBase.getElements(By.className("img-go")).get(0));
		driver.sleep(2);
		driver.back();
		//收货地址
		pageBase.click(pageBase.getElements(By.className("img-go")).get(1));
		driver.sleep(2);
		driver.back();
		//我的收藏
		pageBase.click(pageBase.getElements(By.className("img-go")).get(2));
//		mpHandle.clickMyStore();
		driver.sleep(2);
		driver.back();
		//在线客服
		pageBase.click(pageBase.getElements(By.className("img-go")).get(3));
//		mpHandle.clickZaiXianKeFu();
		driver.sleep(2);
		driver.back();
//		mpHandle.clickYiYaoJianKang();
		driver.sleep(2);
		driver.back();
		
	}
	
	@AfterTest
	public void after() {
		driver.sleep(10);
		driver.stopDriver();
	}
	
}
