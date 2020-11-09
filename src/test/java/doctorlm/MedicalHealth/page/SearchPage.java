package doctorlm.MedicalHealth.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.util.GetData;

public class SearchPage extends PageBase{

	public SearchPage(DriverBase driver) {
		super(driver);
	}
	
	/*
	 * #搜索页面
		S_searchInputBox=className>search-input>搜索页面的搜索框
		S_searchButton=className>search-text>搜索提交按钮
		S_searchKeysHistory=className>btn_active>组元素搜索历史记录,其中0，1，2为筛选条件全部、处方、非处方，3-n为记录
		S_searchResultDiv=className>list>组元素，搜索结果商品div
		S_searchResultAssertDiv=className>loadMore>搜索结果 显示信息“我是有底线的~”
		S_searchResultWaitDiv=className>loadMore>加载中... div
		S_searchResultWaitText=加载中
		S_searchResultAssertText=我是有底线的
		S_searchNoResultAssertDiv=xpath>//*[@id="app"]/div[7]/div>搜索无结果 显示信息“无搜索结果，换个词试试吧～”
		S_searchNoResultAssertText=无搜索结果
		S_deleteButton=className>title-right>删除搜索历史记录按钮
		S_deleteYes=xpath>//*[@id="maskBox"]/div/div[3]/div[2]>确认删除按钮  className>r-mask-btn2
		S_deleteNo=xpath>//*[@id="maskBox"]/div/div[3]/div[1]>取消删除按钮   className>r-mask-btn1
		S_deleteSucess=xpath>//*[@id="showToast"]/div/div[2]>删除搜索历史记录成功提示弹窗，toast
		S_deleteSucessText=删除成功
		S_pleaseInput=xpath=//*[@id="showToast"]/div/div[2]>提示信息，请输入关键字
		S_pleaseInputText=请输入关键字
	 * */
	
	/**
	 * @function: 获取 搜索框
	 */
	public WebElement getSearchInputBox() {
		return explicitlyWait(3,"doctor","S_searchInputBox");
//		return getElement(GetData.getBy("doctor", "S_searchInputBox"));
	}
	
	/**
	 * @function: 获取 搜索提交按钮
	 */
	public WebElement getSearchButton() {
		return explicitlyWait(3,"doctor","S_searchButton");
//		return getElement(GetData.getBy("doctor", "S_searchButton"));
	}
	
	/**
	 * @function: 获取 点击无效的div，用于翻页需要
	 */
	public WebElement getClickDiv() {
//		return explicitlyWait(3,"doctor","S_ClickDiv");
		return getElement(GetData.getBy("doctor", "S_ClickDiv"));
	}
	
	/**
	 * @function: 获取 搜索关键字历史记录 组元素
	 */
	public List<WebElement> getSearchKeysHistory() {
		return getElements(GetData.getBy("doctor", "S_searchKeysHistory"));
	}
	
	/**
	 * @return: List<String> 搜索关键字记录
	 * @Description: 取得所有搜索关键字记录
	 */
	public List<String> getSearchKeysHistoryText(){
		List<WebElement> elements = getSearchKeysHistory();
		List<String> searchKeys = new ArrayList<String>();
		for(WebElement element:elements) {
			searchKeys.add(element.getText());
		}
		return searchKeys;
	}
	
	/**
	 * @function: 获取 搜索结果-商品div 组元素
	 */
	public List<WebElement> getSearchResultDiv() {
		return getElements(GetData.getBy("doctor", "S_searchResultDiv"));
	}
	
	/**
	 * @function: 获取 有搜索结果 的提示文本元素-用于断言（正在加载中。。。）
	 */
	public WebElement getSearchResultWaitDiv() {
		return getElement(GetData.getBy("doctor", "S_searchResultWaitDiv"));
	}
	
	/**
	 * @function: 获取 有搜索结果 的提示文本元素-用于断言（我是有底线的）
	 */
	public WebElement getSearchResultAssertDiv() {
		return getElement(GetData.getBy("doctor", "S_searchResultAssertDiv"));
	}
	
	/**
	 * @function: 获取 无搜索结果 的提示文本元素-用于断言（无搜索结果）
	 */
	public WebElement getSearchNoResultAssertDiv() {
		return getElement(GetData.getBy("doctor", "S_searchNoResultAssertDiv"));
	}

	/**
	 * @function: 获取 删除-搜索记录删除按钮
	 */
	public WebElement getDeleteButton() {
//		return getElement(GetData.getBy("doctor", "S_deleteButton"));
		return explicitlyWait(5, "doctor", "S_deleteButton");
	}
	
	/**
	 * @Decription: 获取确认 删除搜索记录按钮
	 */
	public WebElement getDeleteYes() {
		return explicitlyWait(3,"doctor","S_deleteYes");
	}
	
	/**
	 * @Decription: 获取取消 删除搜索记录按钮
	 */
	public WebElement getDeleteNo() {
		return explicitlyWait(3,"doctor","S_deleteNo");
	}
	
	/**
	 * @function: 获取 删除成功 提示toast元素
	 */
	public WebElement getToastMessage() {
//		return explicitlyWaitToast(5,"doctor","S_toastMessage");
		return explicitlyFastWait(5,"doctor","S_toastMessage");
	}
	
	public WebElement getFatherToast() {
		return explicitlyWait(3,"doctor","S_fatherToast");
	}

}
