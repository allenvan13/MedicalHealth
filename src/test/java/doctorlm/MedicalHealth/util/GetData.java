package doctorlm.MedicalHealth.util;

import org.openqa.selenium.By;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetData {

	public static String getData(String key) {
		
		ProUtil proper = new ProUtil("resource\\data.properties");
		if(proper.getPro(key)!=null) {
			return proper.getPro(key);
		}else {
			log.error("获取[{}]失败",key);
//			System.out.println("获取["+key+"]失败");
			return null;
		}	
	}
	
	public static String getData(String page,String key) {
		ProUtil proper = null;
		if(page.equalsIgnoreCase("doctor")) {
			proper = new ProUtil("resource\\doctorlmElements.properties");
			if (proper.getPro(key) != null) {
				return proper.getPro(key);
			}else {
				log.error("获取[{}]失败",key);
				return null;
			}
		}else {
			log.error("获取[{}]失败",key);
			return null;
		}
	}

	public static String getConfigData(String page,String key) {
		ProUtil proper = null;
		if(page.equalsIgnoreCase("config")) {
			proper = new ProUtil("resource\\configuration.properties");
			if (proper.getPro(key) != null) {
				return proper.getPro(key);
			}else {
				log.error("获取[{}]失败",key);
				return null;
			}
		}else {
			log.error("获取[{}]失败",key);
			return null;
		}
	}
	
	public static By getBy(String page,String key) {
		ProUtil proper = null;
		if(page.equalsIgnoreCase("login")) {
			proper = new ProUtil("resource\\loginElements.properties");
		}
		if(page.equalsIgnoreCase("home")) {
			proper = new ProUtil("resource\\homeElements.properties");
		}
		if(page.equalsIgnoreCase("doctor")) {
			proper = new ProUtil("resource\\doctorlmElements.properties");
		}
		
		String locator = proper.getPro(key);
		String locatorType = locator.split(">")[0]; //按>拆分，第1个值
		String locatorTypeValue = locator.split(">")[1]; //按>拆分，第2个值
		
		switch (locatorType) {
			case "id":
				return By.id(locatorTypeValue);
			case "name":
				return By.name(locatorTypeValue);
			case "className":
				return By.className(locatorTypeValue);
			case "cssSelector":
				return By.cssSelector(locatorTypeValue);
			case "tagName":
				return By.tagName(locatorTypeValue);
			case "linkText":
				return By.linkText(locatorTypeValue);
			case "partialLinkText":
				return By.partialLinkText(locatorTypeValue);	
			case "xpath":
				return By.xpath(locatorTypeValue);
			default:
				return null;
		}
		
	}
}
