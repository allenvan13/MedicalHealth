package doctorlm.MedicalHealth.handle;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.page.SearchPage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SearchPageHandle{
	
	public DriverBase driver;
	public SearchPage searchPage;
	
	public SearchPageHandle(DriverBase driver) {
		this.driver = driver;
		searchPage = new SearchPage(driver);
	}
	
	/**
	 * @Description: 点击搜索输入框
	 */
	public void clickSearchInputBox() {	
		searchPage.click(searchPage.getSearchInputBox());
		Reporter.log("点击搜索框");
	}
	
	/**
	 * @Description: 点击搜索按钮-提交（也可以输入回车提交）
	 */
	public void clickSearchButton() {	
		searchPage.click(searchPage.getSearchButton());
		Reporter.log("点击搜索按钮");
	}
	
	/**
	 * @Decription: 点击删除按钮-删除历史搜索记录
	 */
	public void clickDeleteButton() {
		searchPage.click(searchPage.getDeleteButton());
		Reporter.log("点击删除");
	}
	
	/**
	 * @Decription: 点击确认删除-历史搜索记录
	 */
	public void clickDeleteYes() {
		searchPage.click(searchPage.getDeleteYes());
		Reporter.log("点击确认删除");
	}
	
	/**
	 * @Decription: 点击取消删除-历史搜索记录
	 */
	public void clickDeleteNo() {
		searchPage.click(searchPage.getDeleteNo());
		Reporter.log("点击取消删除");
	}
	
	/** 
	 * @Description: 清除搜索框中文本
	 */
	public void clearSearchInputBox() {
		searchPage.clearText(searchPage.getSearchInputBox());
		Reporter.log("清除搜索框中文本");
	}
	
	/**
	 * @Description: 输入搜索关键字
	 * @param String keys 搜索的关键词
	 */
	public void sendSearchKeys(String keys) {
		Reporter.log("输入搜索关键字");
		searchPage.sendKeys(searchPage.getSearchInputBox(), keys);
	}

	/**
	 * @Description: 删除一个搜索关键字（输入BACK_SPACE）
	 */
	public void deleteSearchKeys() {
		Reporter.log("从右往左 删除一个搜索关键字");
		driver.backOneSpace(searchPage.getSearchInputBox());
	}

	/**
	 * @Description:  按下回车，提交搜索
	 */
	public void pressEnter() {
		Reporter.log("按下回车，提交搜索");
		driver.pagePressEnter();
	}
	
	/**
	 * @function: 获取 搜索没有结果 的提示文本元素-用于断言
	 * */
	public boolean assertHasNoSearchResult() {
//		return getElement(GetData.getBy("doctor", "S_searchNoResultAssertDiv"));
//		WebDriverWait wait = new WebDriverWait(driver.driver, 3);
//		boolean result_yes = wait
//				.ignoring(TimeoutException.class,NoSuchElementException.class)
//				.until(ExpectedConditions.textToBePresentInElementLocated(GetData.getBy("doctor", 
//				"S_searchResultAssertDiv"), GetData.getData("doctor","S_searchResultAssertText")));
//		boolean result_no = wait.until(ExpectedConditions.textToBePresentInElementLocated(GetData.getBy("doctor", 
//				"S_searchNoResultAssertDiv"), GetData.getData("doctor","S_searchNoResultAssertText")));
		WebElement element = searchPage.getSearchNoResultAssertDiv();
		if (element.getText().contains("无搜索结果")) {
			Reporter.log("搜索结果：未匹配到商品");
			return false;
		}else {
			log.error("出错了，应该无搜索结果");
			Reporter.log("出错了，应该无搜索结果");
			return true;
		}
		
//		if (result_yes && !result_no) {
//			Reporter.log("有搜索结果");
//			return true;
//		}else if (!result_yes && result_no) {
//			Reporter.log("无搜索结果");
//			return false;
//		}else {
//			log.error("BUG：搜索结果有误");
//			return false;
//		}
	}
	
	/**
	 * @function: 获取 搜索有结果 的提示文本元素-用于断言
	 */
	public boolean assertHasSearchResult() {
//		WebElement element_NoUseful = searchPage.getClickDiv();
		searchPage.pageDownByKey();
		WebElement element = searchPage.getSearchResultAssertDiv();
		int num = searchPage.getSearchResultDiv().size();
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
	 * @function: 获取 删除成 的提示文本元素-用于断言
	 */
	public boolean assertDeleteSucess() {
	
		WebElement element = searchPage.getToastMessage();
//		log.info("{}|{}",element,element.getText().toString());
		String message = element.getText().toString();
		Reporter.log("弹出Toast提示信息："+message);
//		if (message.contains(GetData.getData("doctor", "S_deleteSucessText"))) {
		if (message.contains("重试")) {
			Reporter.log("无搜索历史记录供删除，视为删除成功");
			return true;
//		}else if (message.contains(GetData.getData("doctor", "S_deleteRetryText"))) {
		}else if (message.contains("成功")) {
			Reporter.log("删除搜索历史记录成功");
			return true;
		}else{
			log.error("assertDeleteSucess() 出错啦。");
			Reporter.log("assertDeleteSucess() 出错啦。");
			return false;
		}
	}
	
//	public boolean assertHasSaveSearchKeys() {
//		
//	}
}
