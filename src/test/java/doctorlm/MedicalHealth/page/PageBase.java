package doctorlm.MedicalHealth.page;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.util.GetData;

import lombok.extern.slf4j.Slf4j;
//页面基类，其他页面继承该类
@Slf4j
public class PageBase {
	
    public DriverBase driver;
    //构造方法,初始化浏览驱动
    public PageBase(DriverBase driver) {
        //把初始化传入的DriverBase赋值给当前BasePage类的DriverBase的变量
        this.driver = driver;
    }
    
    public PageBase() {
	}
    
    /**
     * @function: 定位Element
     * @param By
     * @return WebElement
     */
	public WebElement getElement(By by) {
		WebElement element = driver.driver.findElement(by);
		if (element != null) {
			log.info("P:定位元素，通过[{}]",by); 
			return element;
		}else {
			log.error("P:定位元素失败。[{}]",by);
			Reporter.log("P:定位元素失败。[{}]");
			return null;
		}	
	}  

	/**
     * 定位一组Elements
     * @param By
     * @return List<WebElement>
     * */
	public List<WebElement> getElements(By by){
		List<WebElement> elements = driver.driver.findElements(by);
		if (elements != null) {
			log.info("P:定位组元素,通过[{}]",by); 
			return elements;
		}else {
			log.error("P:定位组元素失败。[{}]",by);
			Reporter.log("P:定位组元素失败。");
			return null;
		}
	}
	
	/**
	 * 通过父元素定位一组子元素elements
	 * @param  父元素WebElement 子By
	 * */
	public List<WebElement> getChildrenElements(WebElement element,By childrenBy){
		log.info("P:定位组元素，通过父元素定位一组子元素[{}]",childrenBy);
	    return element.findElements(childrenBy);
	}
	
    /**
     * 层级定位,通过父节点定位到子元素
     * @param 父节点By 子节点By
     * */
	public WebElement getChildrenElement(By parentBy,By childrenBy) {
		log.info("P:定位子元素，通过父节点[{}]定位子元素[{}]",parentBy,childrenBy);
	    WebElement ele=this.getElement(parentBy);
	    return ele.findElement(childrenBy);
	}

	/**
	 * 层级定位，通过父元素定位子元素
	 * @param 父元素WebElement 子By
	 * */
	public WebElement getChildrenElement(WebElement element,By childrenBy){
		log.info("P:定位子元素，通过父元素定位子元素[{}]",childrenBy);
	    return element.findElement(childrenBy);
	}
	
	/**
	 * @function: 层级定位，通过（单个）父元素定位组子元素，获取其中指定index子元素
	 * @param 父元素WebElement 子By
	 * */
	public WebElement getChildrenElement(WebElement element,By childrenBy,int index){
		log.info("P:定位子元素，通过父元素定位第[{}]个元素[{}]",index+1,childrenBy);
		List<WebElement> elements = element.findElements(childrenBy);
		int size = elements.size();
		if (index < size && index >= 0) {
			return elements.get(index);
		}else {
			log.error("出错了，索引编号超出边界值");
			return null;
		}
	    
	}
	
	/**
	 * @Decription:  通过父级组元素，定位需要的子元素
	 * @parm: (List<WebElement> elements,By by_son,int index) 父元素、子定位、索引
	 * @return: 需要的子元素 WebElement
	 */
	public WebElement getChildrenElement(List<WebElement> elements,By childrenBy,int index) {
		int size = elements.size();
		WebElement element;
		if (index < size && index >= 0) {
			element = elements.get(index);		
			log.info("P:定位子元素，通过父元素定位第[{}]个元素[{}]",index+1,element);
			return getChildrenElement(element, childrenBy);
		}else {
			log.error("出错了，索引编号超出边界值");
			return null;		
		}
		
	}
	
	
    /**
     * getText 获取元素文本
     * @param WebElement
     * */
    public String getText(WebElement element) {
    	log.info("P:获取元素文本,元素{}",element);
        return element.getText();
    }
	
    

	/**
	 * click（点击）元素
	 * @param WebElement
	 * */
    public void click(WebElement element) {
        if(element != null) {
        	log.info("点击元素成功,元素标签[{}]",element.getTagName());
        	element.click();    
        }else {
            log.error("点击元素失败,未定位到元素{}",element);
            Reporter.log("P:点击元素失败。");
        }
    } 
       
    /**
     * actionClick 点击元素
     * @param WebElement
     * */
    public void actionClick(WebElement element) {
       Actions action = new Actions(driver.driver);
       action.moveToElement(element).click().build().perform();
        
//     JavascriptExecutor js = (JavascriptExecutor)driver;
//     js.executeScript("arguments[0].click();", element);
//     element.click();
    }
 
    /**
     * 清除元素已输入的文本 
     * @param (WebElement element) 要清除文本的元素
     * */
    public boolean clearText(WebElement element) {
    	if (element.isEnabled()) {
			element.clear();
			log.info("清除元素文本成功");
			return true;
		}else {
			log.error("清除元素文本失败，元素不可编辑。[{}]",element);
			Reporter.log("P:清除元素文本失败。");
			return false;
		}
    }
      
    /**
     * sendKeys 向元素输入文本
     * @param (WebElement element,String text)
     * */
    public void sendKeys(WebElement element,String text) {
    	if(element.isEnabled()) {
            element.sendKeys(text);
            log.info("向元素输入文本[{}]成功，元素{}",text,element);
        }else {
            log.error("向元素输入文本失败，元素不可编辑。[{}]",element);
            Reporter.log("P:向元素输入文本失败。");
        }
    }

    /**
     * 鼠标悬停在元素上
     * @param WebElement
     * */
	public void moveOnWebElement(WebElement element) {
		Actions action = new Actions(driver.driver);
		action.moveToElement(element).perform();
		log.info("P:鼠标悬停至元素[{}]",element);
	}

    /**
     * 根据title切换新窗口
     * @param (String windowTitle)
     * */
    public boolean switchToWindow_Title(String windowTitle) {
        boolean flag = false;
        try {
            String currentHandle = driver.getWindowHandle();
            List<String> handles = driver.getWindowHandles();
            for (String s : handles) {
                if (s.equals(currentHandle))
                    continue;
                else {
                    driver.switchToWindow(s);
                    if (driver.getTitle().equalsIgnoreCase(windowTitle)) {
                        flag = true;
                        log.info("P:成功切换页面至[{}]",windowTitle);
                        break;
                    } else
                        continue;
                }
            }
        } catch (NoSuchWindowException e) {
        	log.error("P:切换页面失败");
        	Reporter.log("P:切换页面失败。");
        	System.out.println(e.fillInStackTrace());
            flag = false;
        }
        return flag;
    }
    
    /**
	 * 切换至Alert
	 * */
	public void switchToAlert() {
		driver.switchToAlert();
		log.info("P:切换至Alert弹窗");
	}
	
	/**
	 * 切换至iframe
	 * @param WebElement frameElement
	 * */
	public void switchToIframe(WebElement frameElement) {
		log.info("P:切换至IFrame窗{}",frameElement);
		driver.switchToIframe(frameElement);
	}
	
	/**
	 * 切换回默认iframe
	 * */
	public void switchToDefaultIframe() {
		log.info("P:切换回默认IFrame窗");
		driver.driver.switchTo().defaultContent();
	}
	
    /**
     * 点击元素hold并水平拖动x,垂直拖动y，再释放
     * @param (WebElement element,int x,int y)
     * */
    public void clickAndMove(WebElement element,int x,int y) {
    	Actions action = new Actions(driver.driver);
//    	action.moveToElement(element).clickAndHold(element).perform();
    	action.dragAndDropBy(element, x, y).perform();
    	action.release(element);
    }
	
	/**
	 * @discription: 等待toast提示信息出现某文字，等待时间3秒，检查频率100毫秒
	 * @param (By by 元素定位方式,String text 元素上出现的文本)
	 */
	public boolean waitForToast(By by,String text) {
		boolean isPresent = true;
		try {
			new WebDriverWait(driver.driver, 3, 100)
			.ignoring(StaleElementReferenceException.class, NoSuchElementException.class)
			.until(ExpectedConditions.invisibilityOfElementWithText(by,text));
			isPresent = false;
		} catch (Exception e) {
			
		}	
		return isPresent;
	}
    
    /**
     * @discription: 只要某元素上文本未出现就一致下滚到页面最底部
     * @param (int timeout,By by,String text)
     */
    public void pageDownByJs_BASE(int timeout,By by,String text) {
    	JavascriptExecutor js = (JavascriptExecutor)driver;    	
//    	while(!waitForElementPresent(1, By.className("loadMore"),"我是有底线的")) 
    	do {
    		js.executeScript("$('#main').scrollTop(100000);");
		} while (!waitForElementPresent(timeout, by,text));
    }
  
    /**
     * @discription: 只要某元素上文本未出现就一致下滚到页面最底部
     * @param (int timeout,By by,String text)
     */
    public void pageDownByJs(int timeout,By by,String text) {   	
//    	while(!waitForElementPresent(1, By.className("loadMore"),"我是有底线的")) 
    	System.out.println(waitForElementPresent(timeout, by,text));
    	JavascriptExecutor js = (JavascriptExecutor)driver.driver;
    	do {
//    		System.out.println(waitForElementPresent(timeout, by,text));
    		js.executeScript("$(window).scrollTop(1000000);");
		} while (!waitForElementPresent(timeout, by,text));
    	System.out.println(waitForElementPresent(timeout, by,text));
    }
    
    /**
	 * @discription:　下滚到页面最底部
	 * @param 循环-下滚次数
	 */
	public void pageOneDownByJs(int num) {
	JavascriptExecutor js = (JavascriptExecutor)driver.driver;
		do {
			js.executeScript("$('#main').scrollTop(100000);");
			num --;
		} while (num == 0);
	}

	/**
	 * @Description: 通过输入按键Keys.PAGE_DOWN 下滑至页面底部
	 * @param (WebElement element) 需要点击一个元素（点击后无跳转，目的，使光标在页面上）
	 */
    public void pageDownByKey(WebElement element) {  	
    	element.click();
    	Actions action = new Actions(driver.driver);
    	do {
    		action.sendKeys(Keys.PAGE_DOWN).perform();
		} while (!waitForElementPresent(1, GetData.getBy("doctor", "S_searchResultAssertDiv"),"我是有底线的~") 
				|| !waitForElementPresent(1, GetData.getBy("doctor", "S_searchNoResultAssertDiv"),"无搜索结果，换个词试试吧~")); 	
    }
    
    /**
	 * @Description: 通过输入按键Keys.PAGE_DOWN 下滑至页面底部
	 * @param 
	 */
    public void pageDownByKey(WebElement element,By byYes,String textYes) {  	
    	element.click();
    	Actions action = new Actions(driver.driver);
    	do {
    		action.sendKeys(Keys.PAGE_DOWN).perform();
		} while (!waitForElementPresent(1, byYes,textYes)); 
//    	System.out.println(waitForElementPresent(1, byYes,textYes));
    }
    
    /**
	 * @Description: 通过输入按键Keys.PAGE_DOWN 下滑至页面底部
	 */
    public void pageDownByKey() {  	
    	Actions action = new Actions(driver.driver);
    	do {
    		action.sendKeys(Keys.PAGE_DOWN).perform();
		} while (!waitForElementPresent(1, GetData.getBy("doctor", "S_searchResultAssertDiv"),"我是有底线的~")); 	
    }

    /**
	 * @Description: 通过输入按键Keys.PAGE_DOWN 下滑至页面底部,直至by定位的元素上出现text
	 */
    public void pageDownByKey(By by,String text) {  	
    	Actions action = new Actions(driver.driver);
    	do {
    		action.sendKeys(Keys.PAGE_DOWN).perform();
		} while (!waitForElementPresent(1, by, text)); 	
    }
    
	/**
     * @function: 视角返回需要点击的商品
     */
    public void pageMoveToElement_BASE(int index) {   	
    	JavascriptExecutor js = (JavascriptExecutor)driver;
    	if (index == 0) {
    		js.executeScript("function moveToElement(index) {var elements = $('.list');var elementsId = $('.list').eq(index).attr('id'); window.location.href = '#' + elementsId;}"+"moveToElement("+index+");");
    		js.executeScript("$('#main').scrollTop(-100);");
//    		System.out.println("=========视角返回第["+(category_ID+1)+"]个分类["+category_name+"]第["+(index+1)+"]个商品元素，该分类共["+productMax+"]个商品========");
		}else {
			index = index - 1;
			js.executeScript("function moveToElement(index) {var elements = $('.list');var elementsId = $('.list').eq(index).attr('id'); window.location.href = '#' + elementsId;}"+"moveToElement("+index+");");
//			System.out.println("=========视角返回第["+(category_ID+1)+"]个分类["+category_name+"]第["+(index+2)+"]个商品元素，该分类共["+productMax+"]个商品========");
		}
    }
    
    /**
     * @function: 视角返回需要点击的商品
     */
    public void pageMoveToElement(int index) {
    	JavascriptExecutor js = (JavascriptExecutor)driver.driver;
    	js.executeScript("function moveToElement(index) {var elements = $(\'.list\');var elementsId = $(\'.list\').eq(index).attr(\'id\');window.location.href = \'#\' + elementsId;var scrollTop = $(window).scrollTop();$(window).scrollTop(scrollTop - 150);}moveToElement("+index+")");
    }
    
    /**
     * @Decription:  用来执行JS语句
     * @parm:  js语句
     */
    public void executeJs(String javaScript) {
    	JavascriptExecutor js = (JavascriptExecutor)driver.driver;
    	js.executeScript(javaScript);
    }
    
    /**
     * 截图
     */
    public void takeScreenShot() {
//         long date = System.currentTimeMillis();   //获取当前时间（毫秒数）来给文件命名，缺点名字查看不友好无规律
//         String path_temp = String.valueOf(date);   
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hhmmss");    //SimpleDateFormat类自定义输出日期格式 此处例：2019-10-30-063731
       Date now = new Date();
       String path_temp = sdf.format(now);
       path_temp = path_temp+".png";                                    
       String projectPath = System.getProperty("user.dir");    
       String screenshotPath = projectPath+"\\screenshot\\"+path_temp;   
       File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);   
       try {
             FileUtils.copyFile(screen, new File(screenshotPath));                  
       } catch (IOException e) {
             // TODO 自动生成的 catch 块
       e.printStackTrace();
       }
    }

    /**
     * @Decription: 过滤页面获得的文本元素特殊符号
     */
    public String filterString(String before) {
    	String after;
    	after = before.replaceAll("[￥¥x月售笔省元]","");
    	return after;
    }
    
    /**
     * @description: 普通元素-显示等待，默认频率500ms 适用元素实际绝对会出现
     * @param 等待时间outTime秒，页面名字page，元素Key
     * @return WebElement
     */
    public WebElement explicitlyWait(int outTime,String page,String key) {
    	log.info("P:显式等待[{}]页面元素[{}],等待时间[{}]秒",page,key,outTime);
    	WebDriverWait wait = new WebDriverWait(driver.driver,outTime);
    	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(GetData.getBy(page,key)));
    	if (element != null) {
    		log.info("P:显示等待定位元素成功");
    		return element;
		}else {
			log.error("P:显示等待定位元素失败");
			return null;
		}
    }
    
    /**
     * @description: 弱警告元素-显示等待-快速版，指定频率300ms 适用元素实际绝对会出现
     * @param 等待时间outTime秒，页面名字page，元素Key
     * @return WebElement
     */
    public WebElement explicitlyFastWait(int outTime,String page,String key) {
    	log.info("P:显式等待[{}]页面元素[{}],等待时间[{}]秒",page,key,outTime);
    	WebDriverWait wait = new WebDriverWait(driver.driver,outTime,300);
    	WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(GetData.getBy(page,key)));
    	if (element != null) {
    		log.info("P:Fast显示等待定位元素成功");
    		return element;
		}else {
			log.error("P:Fast显示等待定位元素失败");
			return null;
		}
    }
    
    /**
     * @description: 组元素-显示等待，默认检查频率  适用元素实际绝对会出现，获取null或其他视为错误
     * @param 等待时间outTime秒，页面名字page，元素key
     * @return List<WebElement>
     */
    public List<WebElement> explicitlyWaitElements(int outTime,String page,String key) {
    	log.info("P:显式等待[{}]页面元素[{}],等待时间[{}]秒",page,key,outTime);
    	WebDriverWait wait = new WebDriverWait(driver.driver,outTime);
    	List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(GetData.getBy(page,key)));
    	if (elements != null) {
    		log.info("P:显示等待定位组元素成功");
    		return elements;
		}else {
			log.error("P:显示等待定位组元素失败");
			return null;
		}
    }
    
	/**
	 * @Description: 流畅等待-自定义检查频率sleepInMillisms,且忽略无法定位异常，适用元素实际不一定会出现，可能null
	 * @parm: 等待时间outTime秒，页面名字page，元素key,检查频率 sleepInMillis
	 * @return: WebElement
	 */
	public WebElement fluentWait(int outTime,String page,String key,long sleepInMillis){    
		WebElement element = (new WebDriverWait(driver.driver,outTime,sleepInMillis))
				.ignoring(NoSuchElementException.class,TimeoutException.class)
				.until(
    			new ExpectedCondition<WebElement>() {
    				@Override
    				public WebElement apply(WebDriver driver) {
    					return driver.findElement(GetData.getBy(page, key));
    				}
				});
    	return element;
	}
    
	/**
	 * @Description: 组元素流畅等待-自定义检查频率sleepInMillisms,且忽略无法定位异常，适用组元素实际不一定会出现，可能null
	 * @parm: 等待时间outTime秒，页面名字page，元素key,检查频率 sleepInMillis
	 * @return: List<WebElement>
	 */
	public List<WebElement> fluentWaitElements(int outTime,String page,String key,long sleepInMillis) {
		List<WebElement> elements = new WebDriverWait(driver.driver,outTime,sleepInMillis)
				.ignoring(NoSuchElementException.class,TimeoutException.class)
				.until(
    			new ExpectedCondition<List<WebElement>>() {
    				public List<WebElement> apply(WebDriver driver) {
    					return driver.findElements(GetData.getBy(page, key));
    				}
				});
    	return elements;
	}
	
    /**
	 * @Description：等待直到某元素上的文本出现
	 * @param (int timeout 等待时间,By by 元素定位方式,String text 元素上出现的文本)
	 */
	public boolean waitForElementPresent(int timeout,By by,String text) {
		boolean isPresent = false;
		try {
			isPresent = new WebDriverWait(driver.driver, timeout)
			.ignoring(StaleElementReferenceException.class, NoSuchElementException.class)
			.until(ExpectedConditions.textToBePresentInElementLocated(by,text));
//			System.out.println(isPresent+"++++++++");
//			isPresent = true;
		} catch (Exception e) {
			isPresent = false;
			System.out.println(isPresent+"catch++++++++++");
			return isPresent;
		}	
		return isPresent;
	}
	
	/**
	 * @function: 等待直到某元素上的文本出现,目的用于判断元素是否为XXX属性
	 * @param (int timeout 等待时间,By by 元素定位方式,String text 元素上出现的文本)
	 */
	public boolean IsElementTextPresent(int timeout,By by,String text) {
		try {
			boolean result = (new WebDriverWait(driver.driver,5))
					.until(ExpectedConditions.textToBePresentInElementLocated(by, text));
			return result;
		} catch (Exception e) {
			Reporter.log("显示判断元素文本出错");
		}
		return false;
	}
    
    /**
     * @Description:判断元素是否显示方法
     * @param (WebElement element)
     * */
    public boolean assertElementIsDisplay(WebElement element) {
    	log.info("P:判断元素是否显示[{}]",element);
        return element.isDisplayed();
    }
    
    /**
     * @Decription:  判断组元素中是否有元素文本信息为text
     * @return: 存在为true
     */
    public boolean assertElementsHasText(List<WebElement> elements,String text) {
    	boolean result = false;
    	for (WebElement element : elements) {
    		if (element.getText().equals(text)) {
    			result = true;
			}
		}
    	return result;
    }
    
    
//    public static void main(String[] args) {
//		PageBase p = new PageBase();
//		String after = p.filterString("￥¥x351元");
//		System.out.println(after);
//		int a = Integer.valueOf(after);
//		System.out.println(a);
//		System.out.println(a+9);
//	}
    
    
}
