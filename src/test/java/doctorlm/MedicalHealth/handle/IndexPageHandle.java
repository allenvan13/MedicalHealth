package doctorlm.MedicalHealth.handle;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.page.IndexPage;
import doctorlm.MedicalHealth.page.ProductDetailPage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IndexPageHandle {
	
	public DriverBase driver;
	public IndexPage indexPage;
	public ProductDetailPage productPage;
	public CategoryHandle categoryHandle;
    public IndexPageHandle(DriverBase driver) {
        this.driver = driver;
        indexPage = new IndexPage(driver);
        productPage = new ProductDetailPage(driver);
        categoryHandle = new CategoryHandle(driver);
    }
    
    /*
     * 点击搜索框
     * */
    public void clickSearchInputBox() {
    	indexPage.click(indexPage.getSearchInputBox());
    	Reporter.log("点击首页搜索框");
    }

    /*
     * 点击限时活动Banner
     * */
    public void clickXianShiHuoDong() {
    	Reporter.log("点击限时活动");
        indexPage.click(indexPage.getXianShiHuoDong());
    }
    
    /*
     * 点击高血压Banner
     * */
    public void clickGaoXueYa() {
    	Reporter.log("点击高血压Banner");
        indexPage.click(indexPage.getGaoXueYa());
    }
    
    /*
     * 点击糖尿病Banner
     * */
    public void clickTangNiaoBing() {
    	Reporter.log("点击糖尿病Banner");
        indexPage.click(indexPage.getTangNiaoBing());
    }
    
    /*
     * 点击省心瘦Banner
     * */
    public void clickSouShenHuoDong() {
    	Reporter.log("点击省心瘦Banner");
        indexPage.click(indexPage.getSouShenHuoDong());
    }
    
    /*
     * 点击企业资质Banner
     * */
    public void clickZiZhi() {
    	Reporter.log("点击企业资质Banner");
        indexPage.click(indexPage.getZiZhi());
    }
    
    /**
     * @Description:
     */
    public void clickNoUsefulDiv() {
    	Reporter.log("点击无跳转等效果的标签");
        indexPage.click(indexPage.getClickDiv());
    }
    
    public void clickMine() {
    	Reporter.log("点击[我的]标签");
        indexPage.click(indexPage.getMine());
    }
    
    public void clickBuyCar() {
    	Reporter.log("点击[我的]标签");
        indexPage.click(indexPage.getBuyCar());
    }
    
    /**
     * @点击指定商品分类  
     * @param 分类索引号
     */
    public void clickCategory(int index) {
    	List<WebElement> elements = indexPage.getMenuNavElements();
    	WebElement element = elements.get(index);
    	String categoryName = element.getText();
    	Reporter.log("点击分类["+categoryName+"]");
    	log.info("点击分类[{}]",categoryName);
        indexPage.click(element);
        driver.sleep(1);
    }

    public void iteratorCategory() {
    	
    	List<WebElement> elements  = indexPage.getMenuNavElements();
    	
    	int checkRight = 0;
    	int checkWrong = 0;
    	int maxSize = elements.size();	
		
    	for (int i = 0; i < maxSize; i++) {
    		if (i < 10) {
        		elements.get(i).click();
        		categoryHandle.iteratorProducts();
			}else {
				driver.sleep(3);
				indexPage.clickAndMove(elements.get(4), -300, 0);
				elements.get(i).click();
				categoryHandle.iteratorProducts();
				driver.sleep(10);
			}
    		
    		driver.back();
    		elements  = indexPage.getMenuNavElements();
		}
    	
    	if (checkRight+checkWrong == maxSize && checkWrong == 0) {
			Reporter.log("遍历分类成功");
		}else {
			Reporter.log("遍历分类失败");
		}
    	
//    	System.out.println(maxSize);
//		System.out.println(elements.get(3).getText());
//		elements.get(0).click();
//		System.out.println(indexPage.getElement(GetData.getBy("doctor", "CA_searchResultAssertDiv")).getText());
//		System.out.println(indexPage.waitForElementPresent(3, GetData.getBy("doctor", "CA_searchResultAssertDiv"), "我是有底线的"));
//		indexPage.pageDownByKey(categoryHandle.categoryPage.getNoUsefulDiv());
//		driver.sleep(10);
//		System.out.println(indexPage.getElement(GetData.getBy("doctor", "CA_searchResultAssertDiv")).getText());
//		System.out.println(indexPage.waitForElementPresent(3, GetData.getBy("doctor", "CA_searchResultAssertDiv"), "我是有底线的"));
//    	for (int i = 0; i < 20; i++) {
//    		driver.sleep(1);
//    		indexPage.pageMoveToElement(i);			
//		}
    }
}
