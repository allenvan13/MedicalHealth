package doctorlm.MedicalHealth.handle;

import org.testng.Reporter;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.page.ProductDetailPage;
import doctorlm.MedicalHealth.util.GetData;

public class ProductDetailHandle {

	public DriverBase driver;
	public ProductDetailPage pdPage;
	
	public ProductDetailHandle(DriverBase driver) {
		this.driver = driver;
		pdPage = new ProductDetailPage(driver);	
	}
	
	/**
	 * @Decription: 
	 */
	public void clickAddSymbol() {
		pdPage.click(pdPage.getAddElement());
	}
	
	/**
	 * @Decription: 
	 */
	public void clickCutSymbol() {
		pdPage.click(pdPage.getCutElement());
	}
	
	/**
	 * @Decription: 
	 */
	public void clickCollect() {
		pdPage.click(pdPage.getCollect());
	}
	
	/**
	 * @Decription: 
	 */
	public void clickAddCarChufang() {
		pdPage.click(pdPage.getAddCarChufang());
		Reporter.log("点击加购物车");
	}
	
	/**
	 * @Decription: 
	 */
	public void clickAddCarNoChufang() {
		pdPage.click(pdPage.getAddCarNoChufang());
		Reporter.log("点击加购物车");
	}

	/**
	 * @Decription: 
	 */
	public void clickYuYue() {
		pdPage.click(pdPage.getYuYueButton());
		Reporter.log("点击立即预约");
		driver.sleep(1);
	}

	/**
	 * @Decription: 
	 */
	public void clickGoodCar() {
		pdPage.click(pdPage.getGoodCar());
	}

	/**
	 * @Decription: 
	 */
	public void clickKeFu() {
		pdPage.click(pdPage.getKeFu());
	}

	/**
	 * @Decription: 
	 */
	public void clickBuyButton() {
		pdPage.click(pdPage.getBuyButton());
		Reporter.log("点击购买按钮");
	}

	/**
	 * @Decription: 
	 */
	public void clickAndMoveBuyCar() {
		pdPage.clickAndMove(pdPage.getGoodCar(), -200, 600);
	}
	
	/**
	 * @Decription:  判断是否为处方药
	 * @return:  boolean isChuFangDrug 
	 */
	public boolean assertIsChuFangDrug() {
		boolean isChuFangDrug = pdPage.IsElementTextPresent(3, GetData.getBy("doctor", "DD_chuFangTag"), "处方药");
		return isChuFangDrug;
	}
}
