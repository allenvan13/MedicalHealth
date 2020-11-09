package doctorlm.MedicalHealth.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.business.BuyCarPro;
import doctorlm.MedicalHealth.handle.MyOrderHandle;
import doctorlm.MedicalHealth.page.GitOrderPage;
import doctorlm.MedicalHealth.page.OrderDetailPage;
import doctorlm.MedicalHealth.util.GetData;
import doctorlm.MedicalHealth.util.MyUtil;
import doctorlm.MedicalHealth.util.RetryListener;
import doctorlm.MedicalHealth.util.TestngListener;

@Listeners({TestngListener.class,RetryListener.class})
public class TestCode extends CaseBase{

	public DriverBase driver;
	public BuyCarPro buyCarPro;
	public MyOrderHandle myOrderHandle;
	public OrderDetailPage orderDetailPage;
	public GitOrderPage gitOrderPage;
	
	@BeforeMethod
	public void beforeMethod() {
		driver = super.driver;
		buyCarPro = new BuyCarPro(driver);
		orderDetailPage = new OrderDetailPage(driver);
		myOrderHandle = new MyOrderHandle(driver);
		gitOrderPage = new GitOrderPage(driver);
	}
	
	@AfterMethod
	public void afterMethod() {
		driver.get(GetData.getConfigData("config", "url_test"));
	}
	
	@Test(priority = 1,description = "")
	public void test001() {
		
		
//		System.out.println(driver.driver.findElement(By.className("chufang")).getText());
//		driver.driver.findElement(By.cssSelector(".car-text ")).click();
//		buyCarPro.productDetailHandle.clickAddCar();
//		buyCarPro.addDrugs(5);
		
//		driver.get("http://test.runtvip.com/Order/MyOrder/List");
//		List<String> a = myOrderHandle.myOrderPage.getCreatTimes();
//		System.out.println(a);
//		driver.get("http://test.runtvip.com/Order/MyOrder/Detail?orderNo=K15795863850400001&fromList=1");
//		String a1 = orderDetailPage.getOrderTime();
//		System.out.println(a1);
//		System.out.println(MyUtil.isContains(a, a1));
//		driver.driver.findElement(By.cssSelector("img[src=\'/image/tabBar/tabber-car.png\']")).click();
		
		
		/*
		driver.get("http://test.runtvip.com/Order/Business/Settlement?ProL=[{%22ProID%22:38727,%22Quantity%22:1},{%22ProID%22:38475,%22Quantity%22:1},{%22ProID%22:38451,%22Quantity%22:1},{%22ProID%22:38582,%22Quantity%22:1},{%22ProID%22:38906,%22Quantity%22:1}]&isCart=1");
		List<String> a = gitOrderPage.getProductNums();
		System.out.println(a.size());
		System.out.println(a);
		List<String> b = gitOrderPage.getProductPrices();
		System.out.println(b.size());
		System.out.println(b);
		*/
		
		
		/*
		driver.get("http://test.runtvip.com/Order/MyOrder/Detail?orderNo=K15794153977820001&fromList=1");
		List<String> a = orderDetailPage.getDrugNames();
		List<String> b = orderDetailPage.getDrugNums();
		List<String> c = orderDetailPage.getDrugPrices();
		List<WebElement> d = orderDetailPage.getOrderInfos();
		for (int i = 0; i < d.size(); i++) {
			System.out.println(orderDetailPage.filterString(d.get(i).getText()));
		}
		System.out.println("============================================");
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(orderDetailPage.getAddressInfo());
		System.out.println(orderDetailPage.getPayType());
		System.out.println(orderDetailPage.getRemarks());
		System.out.println(orderDetailPage.getGoPay().getText());
		System.out.println("============================================");
		System.out.println(driver.driver.findElement(By.className("interrogationStatus")).getText());
		*/
		
		
		/*
		 * 
		List<WebElement> a = driver.driver.findElements(By.className("drug-num"));
		for (int i = 0; i < a.size(); i++) {
			System.out.println(a.get(i).getText().replaceAll("x",""));
		}
		List<WebElement> b = driver.driver.findElements(By.className("price"));
		
		for (int i = 0; i < b.size(); i++) {
			System.out.println(b.get(i).getText());
		}
		
		System.out.println("产品名字："+gitOrderPage.getProductNames());
		System.out.println("优惠："+gitOrderPage.getFreePrice());
		System.out.println("邮费："+gitOrderPage.getPostPrice());
		System.out.println("合计："+gitOrderPage.getTotalPrice());
		System.out.println("小计："+gitOrderPage.getXiaoJiPrice());
		System.out.println("地址："+gitOrderPage.getAddress());
		System.out.println("支付方式："+gitOrderPage.getPayType().getText());
		
		*/
		
		driver.sleep(10);
	}
	
	
}
