package doctorlm.MedicalHealth.handle;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.page.CategoryPage;
import doctorlm.MedicalHealth.page.ProductDetailPage;
import doctorlm.MedicalHealth.util.GetData;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class CategoryHandle {

	public DriverBase driver;
	public CategoryPage categoryPage;
	public ProductDetailPage proDetailPage;
	
	public CategoryHandle(DriverBase driver) {
		this.driver = driver;
		categoryPage = new CategoryPage(driver);
		proDetailPage = new ProductDetailPage(driver);
	}
	
	/**
	 * @Description: 点击筛选按钮
	 */
	public void clickScreenButton() {	
		categoryPage.click(categoryPage.getScreenButton());
		Reporter.log("点击筛选按钮");
		driver.sleep(1);
	}
	
	/**
	 * @Description: 点击筛选下处方按钮
	 */
	public void clickScreenChufang() {	
		categoryPage.click(categoryPage.getScreenChufang());
		Reporter.log("点击处方筛选");
		driver.sleep(1);
	}
	
	/**
	 * @Description: 点击筛选下非处方按钮
	 */
	public void clickScreenNoChufang() {	
		categoryPage.click(categoryPage.getScreenNoChufang());
		Reporter.log("点击非处方筛选");
		driver.sleep(1);
	}
	
	/**
	 * @Description: 点击搜索输入框
	 */
	public void clickSearchInputBox() {	
		categoryPage.click(categoryPage.getSearchInputBox());
		Reporter.log("点击搜索框");
	}
	
	/**
	 * @Description: 点击搜索按钮-提交（也可以输入回车提交）
	 */
	public void clickSearchButton() {	
		categoryPage.click(categoryPage.getSearchButton());
		Reporter.log("点击搜索按钮");
	}
	
	/** 
	 * @Description: 清除搜索框中文本
	 */
	public void clearSearchInputBox() {
		categoryPage.clearText(categoryPage.getSearchInputBox());
		Reporter.log("清除搜索框中文本");
	}
	
	/**
	 * @Description: 输入搜索关键字
	 * @param String keys 搜索的关键词
	 */
	public void sendSearchKeys(String keys) {
		Reporter.log("输入搜索关键字");
		categoryPage.sendKeys(categoryPage.getSearchInputBox(), keys);
	}

	/**
	 * @Description: 删除一个搜索关键字（输入BACK_SPACE）
	 */
	public void deleteSearchKeys() {
		Reporter.log("从右往左 删除一个搜索关键字");
		driver.backOneSpace(categoryPage.getSearchInputBox());
	}

	/**
	 * @Description:  按下回车，提交搜索
	 */
	public void pressEnter() {
		Reporter.log("按下回车，提交搜索");
		driver.pagePressEnter();
	}
	
	/**
	 * @Description:  点击随机商品
	 */
	public void clickProductRandom() {
		List<WebElement> elements = categoryPage.getProductDiv();
		int size = elements.size();
		Random rand = new Random();
		int index = rand.nextInt(size);
		WebElement element = elements.get(index);
		Reporter.log("随机点选商品:["+element.getText()+"]");
		categoryPage.click(elements.get(index));
		driver.sleep(1);
	}
	
	/**
	 * @Description:  点击指定商品，并返回商品总个数
	 * @return 商品总数
	 */
	public int clickProduct(int index) {
		List<WebElement> elements = categoryPage.getProductDiv();
		int size = elements.size();
		categoryPage.click(elements.get(index));
		return size;
	}
	
	public void addToCar(int max) {
		categoryPage.pageDownByKey(categoryPage.getNoUsefulDiv(),GetData.getBy("doctor", "CA_searchResultAssertDiv"),"我是有底线");	
		List<WebElement> elements_drugName = categoryPage.getProductNames();
		
		int maxSize = elements_drugName.size();
		driver.sleep(1);
		
		for (int i = 0; i < max; i++) {
			categoryPage.pageMoveToElement(i);
			WebElement element = elements_drugName.get(i);
		}
	}
	
	public void iteratorProducts() {
//		System.out.println(GetData.getData("doctor", "CA_searchResultAssertText"));
//		System.out.println(GetData.getBy("doctor", "CA_searchResultAssertDiv"));
		//先下滚到最底部
		categoryPage.pageDownByKey(categoryPage.getNoUsefulDiv(),GetData.getBy("doctor", "CA_searchResultAssertDiv"),"我是有底线");		
		int checkRight = 0;
		int checkWrong = 0;
		
		List<WebElement> elements_drugName = categoryPage.getProductNames();
		List<WebElement> elements_drugPrice = categoryPage.getProductPrices();
		List<WebElement> elements_drugFreePrice = categoryPage.getProductFreePrices();
		List<WebElement> elements_drugSaleNum = categoryPage.getProductSaleNums();
		
		int maxSize = elements_drugName.size();
		
		driver.sleep(1);
		for (int i = 0; i < maxSize; i++) {
			
			categoryPage.pageMoveToElement(i);

			WebElement element = elements_drugName.get(i);
			String drugName = element.getText();
			String drugPrice = elements_drugPrice.get(i).getText();
			String drugFreePrice = elements_drugFreePrice.get(i).getText();
			String drugSaleNum = elements_drugSaleNum.get(i).getText();
			
			driver.sleep(1);
			element.click();
			
			String drugName_detail = proDetailPage.getDrugName().getText();
			String drugPrice_detail = proDetailPage.getDrugPrice().getText();
			String drugFreePrice_detail = proDetailPage.getDrugFreePrice().getText();
			String drugSaleNum_detail = proDetailPage.getDrugSaleNum().getText();
			
			if (drugName.equals(drugName_detail) && drugPrice.equals(drugPrice_detail) && drugFreePrice.equals(drugFreePrice_detail) && drugSaleNum.equals(drugSaleNum_detail)) {
				checkRight++;
				System.out.println("商品名字、价格、月销数量核对一致。");
			}else {
				checkWrong++;
			}
			
			back();
			
			categoryPage.pageDownByKey(categoryPage.getNoUsefulDiv());
			
			elements_drugName = categoryPage.getProductNames();
			elements_drugPrice = categoryPage.getProductPrices();
			elements_drugFreePrice = categoryPage.getProductFreePrices();
			elements_drugSaleNum = categoryPage.getProductSaleNums();	
		}
		
		if (checkRight+checkWrong == maxSize && checkWrong == 0) {
			System.out.println("=================商品遍历Sucess");
		}else {
			System.out.println("=================商品遍历Fail");
		}
	}
	
	public void pageDownByKey() {
		categoryPage.pageDownByKey(categoryPage.getNoUsefulDiv(),GetData.getBy("doctor", "CA_searchResultAssertDiv"),"我是有底线的~");	
	}
	
	/**
	 * @function: 获取 搜索没有结果 的提示文本元素-用于断言
	 * */
	public boolean assertHasNoSearchResult() {

		WebElement element = categoryPage.getSearchNoResultAssertDiv();
		if (element.getText().contains("无搜索结果")) {
			Reporter.log("搜索结果：未匹配到商品");
			return false;
		}else {
			log.error("出错了，应该无搜索结果");
			Reporter.log("出错了，应该无搜索结果");
			return true;
		}
	}
	
	/**
	 * @function: 获取 搜索有结果 的提示文本元素-用于断言
	 */
	public boolean assertHasSearchResult() {
		categoryPage.pageDownByKey(GetData.getBy("doctor", "CA_searchResultAssertDiv"),GetData.getData("doctor", "CA_searchResultAssertText"));
		WebElement element = categoryPage.getSearchResultAssertDiv();
		int num = categoryPage.getProductNames().size();
		if (element.getText().contains("我是有底线的") ) {
			Reporter.log("搜索结果，共计匹配到["+num+"]个商品");
//			("搜索结果，共计匹配到个商品",num);
			return true;
		}else {
			log.error("出错了，应该存在搜索结果");
			Reporter.log("出错了，应该存在搜索结果");
			return false;
		}
	}
	
	/**
	 * @function: 返回至上一页
	 */
	public void back() {
		driver.back();
	}

	/**
	 * @function: 点击无效Div
	 */
	public void clickNoUsefulDiv() {
		categoryPage.click(categoryPage.getNoUsefulDiv());
	}
}
