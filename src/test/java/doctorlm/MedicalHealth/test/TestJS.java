package doctorlm.MedicalHealth.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TestJS{
	
	public static WebDriver driver;
	
	@Test
	public void beFore() throws Exception  {
		//声明ChromeOptions，主要是给chrome设置参数
	    ChromeOptions options = new ChromeOptions();

	    Map<String, String> mobileEmulation = new HashMap<>();
	    
	    mobileEmulation.put("deviceName", "iPhone X");

	    options.setExperimentalOption("mobileEmulation", mobileEmulation);

	    //设置驱动位置
	    System.setProperty("webdriver.chrome.driver", "D:\\Program Files\\Java\\webdriver\\chromedriver.exe");
		driver = new ChromeDriver(options);
		
//		String url = "http://test.runtvip.com/?userinfo=8nrxdqggr4sW0ZTKr7fqEB6i8zZQ3RIwfhKN7M8ajgrQLvBtRgKLLC6bvgL/gi+/B41on+3jpBI/TzQXEq7rMVRyUAAmo/7tyfGt72EUZlPyFn5+z/XxQ8Y75LQx3XcTREPs5mrhAHm0uNJuzsQeQS11/hWdNelbggwRzgOusdjS4pKF0anA4A==";
		String url = "http://fx.runtvip.com/?userinfo=8nrxdqggr4sW0ZTKr7fqEB6i8zZQ3RIwfhKN7M8ajgrQLvBtRgKLLC6bvgL/gi+/B41on+3jpBI/TzQXEq7rMVRyUAAmo/7tyfGt72EUZlPyFn5+z/XxQ8Y75LQx3XcTREPs5mrhAHm0uNJuzsQeQS11/hWdNelbggwRzgOusdjS4pKF0anA4A==";
        driver.get(url);
        driver.manage().window().setSize(new Dimension(1000, 1000));
        Thread.sleep(3000);
        pageDownByJs();
        iterateProduct();
        
        sleep(10);
        System.out.println("==============关闭浏览器==============");
        driver.quit();

	}
	
	
	public static boolean iterateProduct() {
		int productAll = 0;
        int checkRight = 0;
        int checkWrong = 0;
        boolean sucess = false;
        
        List<WebElement> elements_menu = getMenuElements();
        int category_max = elements_menu.size();
//        System.out.println(category_max);
        for (int j = 0; j < category_max; j++) {
        	System.out.println(elements_menu.size());
        	WebElement element_menu = elements_menu.get(j);
        	String category_name = getCategoryName(elements_menu, j);
        	element_menu.click();
			sleep(1);
			
			pageDownByJs();
			
			List<WebElement> elements_drug = getProductElements();
	        List<WebElement> elements_drug_name = getDrugNameElements();
	        List<WebElement> elements_drug_price = getDrugPriceElements();
	        int productMax = elements_drug.size();
	        productAll += productMax;
//	        System.out.println(elements_drug.size());
	        
	        for (int i = 0; i < productMax; i++) {
	        	System.out.println(elements_drug.size());
	        	 pageMoveToElement(j,category_name,i,productMax);
	             
	             String drug_name_index = getDrugNameIndex(elements_drug_name, i);
	             String drug_price_index = getDrugPriceIndex(elements_drug_price, i);
//	             System.out.println(drug_name_index+"|"+drug_price_index);
	             
	             elements_drug.get(i).click();
	             sleep(1);//强制休眠需要改写为隐式等待
	             String drug_name_detail = getDrugName();
	             String drug_price_detail = getDrugPrice();
	             
//	             System.out.println(drug_name_detail+"|"+drug_price_detail);
	             
	             if (drug_name_index.equals(drug_name_detail) && drug_price_index.equals(drug_price_detail)) {
	     			System.out.println("商品名称["+drug_name_detail+"],价格["+drug_price_detail+"]元,主页与详情页一致");
	     			checkRight ++;
	     		}else {
	     			System.out.println("首页商品名称["+drug_name_index+"],详情页商品名称["+drug_name_detail+"]");
	     			System.out.println("首页商品价格["+drug_price_index+"]元,详情页商品价格["+drug_price_detail+"]元,主页与详情页信息不一致");
	     			checkWrong ++;
	     		}
				
	             back();
	             
	             pageDownByJs();
	             elements_menu = getMenuElements();
	             elements_menu.get(j).click();
//	             sleep(1);
	             
	             pageDownByJs();
	             elements_drug = getProductElements();
	             elements_drug_name = getDrugNameElements();
	             elements_drug_price = getDrugPriceElements();
//	             System.out.println(elements_drug.size());
	                     
			}
		}
        if (checkWrong == 0) {
			sucess = true;
		}
        System.out.println("商品遍历结束,共检查["+category_max+"]个分类，共计["+productAll+"]个商品"
        		+ ",其中信息正确["+checkRight+"]个,错误["+checkWrong+"]个。");
        return sucess;
        
	}
	
	/*
	 * 
	 * */
	public static List<WebElement> getMenuElements() {
		return driver.findElements(By.className("navMenuItem"));
	}
	
	/*
	 * 
	 * */
	public static List<WebElement> getProductElements() {
		return driver.findElements(By.className("drug-info"));
	}
	
	/*
	 * 
	 * */
	public static List<WebElement> getDrugNameElements() {
		return driver.findElements(By.className("drug-name"));
	}
	
	/*
	 * 
	 * */
	public static List<WebElement> getDrugPriceElements() {
		return driver.findElements(By.className("drug3"));
	}
	
	/*
	 * 
	 * */
	public static String getCategoryName(List<WebElement> elements,int index) {
		return elements.get(index).getText();
	}
	
	/*
	 * 首页列表单个药品价格
	 * */
	public static String getDrugNameIndex(List<WebElement> elements,int index) {
		return elements.get(index).getText();
	}
	
	/*
	 * 首页列表单个药品价格
	 * */
	public static String getDrugPriceIndex(List<WebElement> elements,int index) {
		return elements.get(index).getText();
	}
	
	/*
	 * 详情页的药品名称
	 * */
	public static String getDrugName() {
		return driver.findElement(By.className("drug-name")).getText();
	}
	
	/*
	 * 详情页的药品价格
	 * */
	public static String getDrugPrice() {
		return driver.findElement(By.className("price")).getText();
	}
	
	/**
	  * 窗口返回
	 * */
	public static void back() {
		driver.navigate().back();
	}
	
	/*
     * PAGE DOWN 1次
     * */
    public static void pageOneDown() {
    	driver.findElement(By.className("swiperContainerTitle-right")).click();
    	Actions action = new Actions(driver);
        action.sendKeys(Keys.PAGE_DOWN).perform();
    }
	
	/*
     * 首页通过输入按键Keys.PAGE_DOWN 下滑至页面底部
     * */
    public static void pageDownByKey() {  	
    	driver.findElement(By.className("swiperContainerTitle-right")).click();
    	Actions action = new Actions(driver);
    	while(!waitForElementPresent(1, By.className("loadMore"),"我是有底线的")) {
    		action.sendKeys(Keys.PAGE_DOWN).perform();
//            System.out.println("下滑到底部");
    	}  	
    }
    
    /*
     * 视角返回需要点击的商品
     * */
    public static void pageMoveToElement(int category_ID,String category_name,int product_ID,int productMax) {
 	
    	JavascriptExecutor js = (JavascriptExecutor)driver;
    	if (product_ID == 0) {
    		js.executeScript("function moveToElement(index) {var elements = $('.list');var elementsId = $('.list').eq(index).attr('id'); window.location.href = '#' + elementsId;}"+"moveToElement("+product_ID+");");
    		js.executeScript("$('#main').scrollTop(-100);");
    		System.out.println("=========视角返回第["+(category_ID+1)+"]个分类["+category_name+"]第["+(product_ID+1)+"]个商品元素，该分类共["+productMax+"]个商品========");
		}else {
			product_ID = product_ID - 1;
			js.executeScript("function moveToElement(index) {var elements = $('.list');var elementsId = $('.list').eq(index).attr('id'); window.location.href = '#' + elementsId;}"+"moveToElement("+product_ID+");");
			System.out.println("=========视角返回第["+(category_ID+1)+"]个分类["+category_name+"]第["+(product_ID+2)+"]个商品元素，该分类共["+productMax+"]个商品========");
		}

    }
	
	/*
     * 通过执行Js语句下滑至页面底部
     * */
    public static void pageDownByJs() {
    	JavascriptExecutor js = (JavascriptExecutor)driver;  
//    	System.out.println(!waitForElementPresent(3, By.className("loadMore"),"我是有底线的"));
    	do {
    		js.executeScript("$('#main').scrollTop(100000);");
    		System.out.println(!waitForElementPresent(3, By.className("loadMore"),"我是有底线的"));
		} while (!waitForElementPresent(3, By.className("loadMore"),"我是有底线的"));
//    	System.out.println(!waitForElementPresent(3, By.className("loadMore"),"我是有底线的"));
//    	while(!waitForElementPresent(1, By.className("loadMore"),"我是有底线的")) {
//            	js.executeScript("$('#main').scrollTop(100000);");
//    	}  	
    }
    
    /*
	 * 等待直到某元素的文字的出现
	 * */
	public static boolean waitForElementPresent(int timeout,By by,String text) {
		boolean isPresent = true;
		try {
			new WebDriverWait(driver, timeout)
			.ignoring(StaleElementReferenceException.class, NoSuchElementException.class)
			.until(ExpectedConditions.invisibilityOfElementWithText(by,text));
			isPresent = false;
			
		} catch (Exception e) {
//			System.out.println(e);
//			isPresent = true;
		}
//		System.out.println(isPresent);
		return isPresent;
	}
	
	/*
     * 休眠，单位为秒
     * */
    public static void sleep(int second) {
        try {
        	System.out.println("进程休眠[{"+second+"}]秒");
        	Thread.sleep(second*1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
    
}
