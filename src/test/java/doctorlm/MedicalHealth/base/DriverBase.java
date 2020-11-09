package doctorlm.MedicalHealth.base;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

//浏览器基类
@Slf4j
public class DriverBase { 

	public WebDriver driver;
	
	public DriverBase(String browser) {
		SelectDriver sd = new SelectDriver();
		this.driver = sd.driverName(browser);
		log.info("打开[{}]浏览器",browser);
	}
	
	/**
	 * 获取浏览器驱动driver
	 * */
	public WebDriver getDriver() {
		log.info("获取driver");
		return driver;
	}
	
	/**
	 * 关闭浏览器
	 * */
	public void stopDriver() {
		log.info("关闭浏览器");
		driver.quit();		
	}
	
	/**
	 * 打开网址 get(url)
	 * */
	public void get(String url) {
		log.info("打开网址[{}]",url);
		driver.get(url);
	}
	
	/**
	  * 窗口最大化
	 * */
	public void setWindowMax() {
		log.info("最大化窗口");
		driver.manage().window().maximize();	
	}
	
	/**
	  * 窗口自定义
	 * */
	public void setWindowSize(int x,int y) {
		log.info("设置窗口分辨率[{},{}]",x,y);
		driver.manage().window().setSize(new Dimension(x, y));
	}
	
	/**
	  * 窗口返回
	 * */
	public void back() {
		log.info("页面返回");
		driver.navigate().back();
	}
	
	/**
	  * 窗口刷新
	 * */
	public void refresh() {
		log.info("页面刷新");
		driver.navigate().refresh();		
	}
	
	/**
	 * 获取当前URL
	 * */
	public String getUrl() {
//		log.info("获取页面url");
		return driver.getCurrentUrl();
	}
	
	/**
	 * 获取当前Title
	 * */
	public String getTitle() {
//		log.info("获取页面title");
		return driver.getTitle();	
	}
	
	/**
	 * 获取当前窗口handle
	 * */
	public String getWindowHandle() {
		log.info("获取当前窗口handle");
		return driver.getWindowHandle();	
	}
	
	/**
	 * 获取多个窗口handles
	 * */
	public List<String> getWindowHandles() {
		log.info("获取多个窗口handles");
		Set<String> winHandels = driver.getWindowHandles();
        List<String> handles = new ArrayList<String>(winHandels);
        return handles;
	}
	
	/**
	  * 切换至Alert
	 * */
	public void switchToAlert() {
		log.info("切换至弹窗");
		driver.switchTo().alert();
	}
    
	/**
	  * 切换至iframe
	 * */
	public void switchToIframe(WebElement frameElement) {
		log.info("切换至IFrame窗{}",frameElement);
		driver.switchTo().frame(frameElement);
	}
	
	/**
	  * 切换至窗口 
	 * */
	public void switchToWindow(String handle) {
		log.info("切换至窗口[{}]",handle);
		driver.switchTo().window(handle);
	}
	
    /*
     * 休眠，单位为秒
     * */
    public void sleep(int second) {
        try {
        	log.info("进程休眠[{}]秒",second);
        	Thread.sleep(second*1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
	/**
	 * setCookies
	 * */
	public void setCookies(Cookie cookie) {
		driver.manage().addCookie(cookie);
		log.info("添加cookies");
	}

	/**
	 * deleteCookies
	 * */
	public void deleteCookies() {
		driver.manage().deleteAllCookies();
		log.info("删除cookies");
	}

	/**
	 * getCookies
	 * */
	public Set<Cookie> getCookies() {
		Set<Cookie> cookies = driver.manage().getCookies();
		log.info("获取cookies");
		return cookies;
	}

    /*
     * 输入F12
     * */
    public void pagePressF12(WebElement element) {
        element.click();
    	Actions action = new Actions(driver);
        action.sendKeys(Keys.F12).perform();
        log.info("输入F12键");
    }
    
    /**
     * PAGE DOWN
     * @param (WebElement element,int num) 输入次数
     * */
    public void pageDown(WebElement element,int num) {
    	element.click();
    	Actions action = new Actions(driver);
        while (num>0) {
        	action.sendKeys(Keys.PAGE_DOWN).perform();
        	num--;
		}    
    }
    	
    /**
     * PAGE DOWN 1次 
     * @param (WebElement element) 需要先点击不跳转或无效果的某元素
     * */
    public void pageOneDown(WebElement element) {
    	element.click();
    	Actions action = new Actions(driver);
        action.sendKeys(Keys.PAGE_DOWN).perform();
    }
    
    /**
     * PAGE UP 1次 
     * @param (WebElement element) 需要先点击不跳转或无效果的某元素
     * */
    public void pageOneUp(WebElement element) {
    	element.click();
    	Actions action = new Actions(driver);
        action.sendKeys(Keys.PAGE_UP).perform();
    }
    
    /**
     * BACK SPACE 1次 
     * @param (WebElement element) 
     * */
    public boolean backOneSpace(WebElement element) {
    	boolean isSucess = false;
    	Actions action = new Actions(driver);
    	if (element != null) {
    		element.click();
            action.sendKeys(Keys.BACK_SPACE).perform();
            isSucess = true;
            return isSucess;
		}else {
			log.error("BackSpace ERROR！");
			Reporter.log("出错啦！ BackSpace ERROR！");
		}
    	return isSucess;
    	
    }
       
    /**
	 * 输入回车键
	 * */
	public void pagePressEnter() {
		Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).perform();
        log.info("输入回车键->提交");
	}
    
	/**
     * 传入参数截图
     * @param 
     */
    public void takeScreenShot(TakesScreenshot drivername, String path) {
        String currentPath = System.getProperty("user.dir")+"\\test-output\\screenshots\\"; 
        File screenFile = drivername.getScreenshotAs(OutputType.FILE);
        
        try {
        	FileUtils.copyFile(screenFile, new File(currentPath+"\\"+path));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            log.info("已成功截图至路径[{}]",path);
        }
    }  
    
    /**
     * 自动截图
     */
    public void takeScreenShot() {
    	String currentPath = System.getProperty("user.dir")+"\\test-output\\screenshots\\";
        SimpleDateFormat simpleDF = new SimpleDateFormat("yyyyMMdd_HH-mm-ss");
        
        String path_temp = simpleDF.format(new Date());
        String path = currentPath+this.getClass().getSimpleName() + "_" + path_temp + ".png";
        takeScreenShot((TakesScreenshot) driver, path);
        //takeScreenShot((TakesScreenshot) driver, path);
        File screenFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
			FileUtils.copyFile(screenFile, new File(currentPath+"\\"+path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
            log.info("已成功截图至路径[{}]",currentPath);
        }
    }
    
    /**
     * @discription: 隐式等待 全局
     * @param 等待时间second秒
     */
    public void implicitlyWait(int second) {
    	log.info("设置全局元素等待[{}]秒",second);
    	driver.manage().timeouts().implicitlyWait(second, TimeUnit.SECONDS);	
    }
    
    /**
     * @Decription: 判断元素是否存在，忽略异常
     * @return 存在为true ，不存在为false
     */
    public boolean isElementPresent(By locatorKey) {
        try {
            driver.findElement(locatorKey);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
    
    /**
     * @Decription: 判断元素是否可见（有些元素存在DOM中，但不可见）
     */
    public boolean isElementVisible(String cssLocator){
        return driver.findElement(By.cssSelector(cssLocator)).isDisplayed();
    }
    
    
}
