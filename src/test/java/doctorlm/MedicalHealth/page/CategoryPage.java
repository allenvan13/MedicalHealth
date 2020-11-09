package doctorlm.MedicalHealth.page;

import java.util.List;

import org.openqa.selenium.WebElement;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.util.GetData;

public class CategoryPage extends PageBase{
	
	public CategoryPage(DriverBase driver) {
		super(driver);
	}
	
	/*
		 #分类-单独某分类页面Category
		CA_searchInputBox=className>search-input>分类页面搜索框
		CA_searchButton=className>search-text>搜索提交按钮
		CA_productName=className>drug-name>组元素-商品名字，可以用来获取商品数量
		CA_productPrice=className>drug3>组元素-商品价格
		CA_productSaleNum=className>num>组元素-月售XXX笔
		CA_productFreePrice=className>reward>组元素，省¥XX
		CA_searchResultAssertDiv=className>loadMore>加载底部元素，用于显示提示的的div
		CA_searchResultWaitDiv=className>loadMore>加载中... div
		CA_searchNoResultAssertDiv=xpath>//*[@id="app"]/div[6]/div>无结果显示提示的div
		CA_searchResultWaitText=加载中
		CA_searchResultAssertText=我是有底线的
		CA_searchNoResultAssertText=无搜索结果
		CA_screenButton=xpath>//*[@id=\\"app\\"]/div[3]/div[4]/button>筛选按钮
		CA_screenChufang=xpath>//*[@id=\\"app\\"]/div[4]/div[2]>筛选下处方药
		CA_screenNoChufang=xpath>//*[@id="app"]/div[4]/div[3]>筛选下非处方药
		CA_productDiv=className>fadeInUp>组元素-商品DIV
	 */
	
	/**
	 * @获取元素：  分类页面搜索框
	 */
	public WebElement getSearchInputBox() {
		return getElement(GetData.getBy("doctor", "CA_searchInputBox"));
	}
	
	/**
	 * @获取元素： 无效Div，用于下翻页面
	 */
	public WebElement getNoUsefulDiv() {
		return getElement(GetData.getBy("doctor", "CA_noUsefulDiv"));
	}
	
	/**
	 * @获取元素：  搜索提交按钮
	 */
	public WebElement getSearchButton() {
		return getElement(GetData.getBy("doctor", "CA_searchButton"));
	}
	
	/**
	 * @获取元素：  加载底部元素，用于显示提示的的div
	 */
	public WebElement getSearchResultAssertDiv() {
		return getElement(GetData.getBy("doctor", "CA_searchResultAssertDiv"));
	}
	
	/**
	 * @获取元素：  加载中... div
	 */
	public WebElement getSearchResultWaitDiv() {
		return getElement(GetData.getBy("doctor", "CA_searchResultWaitDiv"));
	}
	
	/**
	 * @获取元素：  无结果显示提示的div
	 */
	public WebElement getSearchNoResultAssertDiv() {
		return getElement(GetData.getBy("doctor", "CA_searchNoResultAssertDiv"));
	}
	
	/**
	 * @获取元素：  筛选按钮
	 */
	public WebElement getScreenButton() {
		return getElement(GetData.getBy("doctor", "CA_screenButton"));
	}
	
	/**
	 * @获取元素：  筛选下拉处方药按钮
	 */
	public WebElement getScreenChufang() {
		return getElement(GetData.getBy("doctor", "CA_screenChufang"));
	}
	
	/**
	 * @获取元素：  筛选下拉非处方药按钮
	 */
	public WebElement getScreenNoChufang() {
		return getElement(GetData.getBy("doctor", "CA_screenNoChufang"));
	}
	
	/**
	 * @获取组元素：  商品名字，可以用来获取商品数量
	 */
	public List<WebElement> getProductNames() {
		return getElements(GetData.getBy("doctor", "CA_productName"));
	}
	
	/**
	 * @获取组元素：  商品价格
	 */
	public List<WebElement> getProductPrices() {
		return getElements(GetData.getBy("doctor", "CA_productPrice"));
	}
	
	/**
	 * @获取组元素：  月售XXX笔
	 */
	public List<WebElement> getProductSaleNums() {
		return getElements(GetData.getBy("doctor", "CA_productSaleNum"));
	}

	/**
	 * @获取组元素：  省¥XX
	 */
	public List<WebElement> getProductFreePrices() {
		return getElements(GetData.getBy("doctor", "CA_productFreePrice"));
	}
	
	/**
	 * @获取组元素：  组元素-商品DIV
	 */
	public List<WebElement> getProductDiv() {
		return getElements(GetData.getBy("doctor", "CA_productDiv"));
	}
}
