package doctorlm.MedicalHealth.business;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.handle.BuyCarHandle;
import doctorlm.MedicalHealth.handle.CategoryHandle;
import doctorlm.MedicalHealth.handle.IndexPageHandle;
import doctorlm.MedicalHealth.handle.ProductDetailHandle;
import doctorlm.MedicalHealth.page.CategoryPage;
import doctorlm.MedicalHealth.page.ProductDetailPage;
import doctorlm.MedicalHealth.util.GetData;

public class BuyCarPro {
	
	public DriverBase driver;
	public CategoryPage categoryPage;
	public ProductDetailPage productDetailPage;
	public IndexPageHandle indexHandle;
	public CategoryHandle categoryHandle;
	public ProductDetailHandle productDetailHandle;
	public BuyCarHandle buyCarHandle;

	public BuyCarPro(DriverBase driver) {
		this.driver = driver;
		categoryPage = new CategoryPage(driver);
		productDetailPage = new ProductDetailPage(driver);
		indexHandle = new IndexPageHandle(driver);
		categoryHandle = new CategoryHandle(driver);
		productDetailHandle = new ProductDetailHandle(driver);
		buyCarHandle = new BuyCarHandle(driver);
	}
	
	public void addDrugs(int addMax) {
		indexHandle.clickCategory(0);
		categoryHandle.pageDownByKey();
		driver.sleep(1);
		List<WebElement> elements_drugName = categoryPage.getProductNames();
		List<String> drugsName = new ArrayList<>();
		
		for (int i = 0; i < addMax + 1; i++) {
			categoryPage.pageMoveToElement(i);
			driver.sleep(1);
			WebElement element = elements_drugName.get(i);
			String drugName_C = element.getText();
			element.click();
//			String drugName_P = productDetailPage.getDrugName().getText();
//			System.out.println("外"+drugName_C);
//			System.out.println("详细"+drugName_P);
//			if (drugName_C == drugName_P) {
			//判断是否为处方药，加购物车按钮定位方式不一样
			if (productDetailPage.waitForElementPresent(3, GetData.getBy("doctor", "DD_chuFangTag"), "处方药")) {
				productDetailHandle.clickAddCarChufang();
			}else {
				productDetailHandle.clickAddCarNoChufang();
			}
			
			if (productDetailPage.waitForElementPresent(3, GetData.getBy("doctor", "DD_toast"),
					GetData.getData("doctor", "DD_toastAddMessage"))) {
				System.out.println((productDetailPage.waitForElementPresent(3, GetData.getBy("doctor", "DD_toast"),
						GetData.getData("doctor", "DD_toastAddMessage"))));
				System.out.println(GetData.getData("doctor", "DD_toastAddMessage"));
				drugsName.add(drugName_C);
				driver.sleep(1);
			}
			driver.back();
//			}
			
			categoryHandle.pageDownByKey();	
			elements_drugName = categoryPage.getProductNames();
		}
		
	}
}
