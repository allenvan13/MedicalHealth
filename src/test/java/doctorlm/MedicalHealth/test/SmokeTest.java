package doctorlm.MedicalHealth.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.business.BuyDrugs;
import doctorlm.MedicalHealth.handle.CategoryHandle;
import doctorlm.MedicalHealth.handle.IndexPageHandle;
import doctorlm.MedicalHealth.util.GetData;
import doctorlm.MedicalHealth.util.RetryListener;
import doctorlm.MedicalHealth.util.TestngListener;

@Listeners({TestngListener.class,RetryListener.class})
public class SmokeTest extends CaseBase{

	public DriverBase driver;
	public IndexPageHandle indexPageHandle;
	public CategoryHandle categoryHandle;
	public BuyDrugs buyDrugs;
	
	@BeforeMethod
	public void beforeMethod() {
		driver = super.driver;
		indexPageHandle = new IndexPageHandle(driver);
		categoryHandle = new CategoryHandle(driver);
		buyDrugs = new BuyDrugs(driver);
	}
	
	@AfterMethod
	public void afterMethod() {
		driver.get(GetData.getConfigData("config", "url_test"));
	}
	
	@Test(priority = 1,description = "处方药购买流程-首页进入分类[测试分类_1_HOT]-筛选处方药-详情页点击购买")
	public void testBuyOneCFD() {
		buyDrugs.buyChuFangDrugs();
		buyDrugs.assertPaySuccess();
	}
	
	@Test(priority = 2,description = "普通药购买流程-首页进入分类[测试分类_1_HOT]-筛选非处方药-详情页点击购买")
	public void testBuyOneGD() {
		buyDrugs.buyGeneralDrugs();
		buyDrugs.assertPaySuccess();
	}
	
	@Test(priority = 3,description = "填写处方信息页面Toast提示信息验证（顺序及正确性）")
	public void testToastMessage() {
		buyDrugs.checkToastInfo();
	}
}
