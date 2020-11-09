/**
 * Copyright (c)  2019 BFD-SoftwareStudio. All rights reserved.
 *
 * Function: 
 * @author: Allen Van
 * @date: 2019年12月26日 下午8:49:31
 */
package doctorlm.MedicalHealth.test;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import bsh.commands.dir;
import doctorlm.MedicalHealth.base.DriverBase;
import doctorlm.MedicalHealth.page.PageBase;

/**
 * @ClassName:TestHandle.java
 * @author： Allen Van
 * @date: 2019年12月26日 下午8:49:31
 * @Description: 
 */
public class TestSLX extends CaseBase{
	
	public DriverBase driver;
	public PageBase pageBase;
	
	@BeforeTest
	public void before() {
		driver = InitDriverBase("chrome");
		driver.setWindowSize(1500, 1000);
		driver.implicitlyWait(5);
	}
	
	@Test
	public void test() {
		
		String url = "http://192.168.1.183:90";
		
		driver.get(url);
		
		WebElement ele = driver.driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div/div[1]/div[3]/div[1]"));
		ele.click();
		WebElement ele1 = driver.driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[1]/div[2]"));
		ele1.click();
		WebElement ele_username = driver.driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[2]/form/div[1]/div/div[2]/input"));
		ele_username.sendKeys("13652521111");
		WebElement ele_password = driver.driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[2]/form/div[2]/div/div[2]/input"));
		ele_password.sendKeys("123456");
		WebElement submit = driver.driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[2]/form/button/span"));
		submit.click();
		driver.sleep(20);
		driver.back();
		
	}
	
	@AfterTest
	public void after() {
		driver.sleep(10);
		driver.stopDriver();
	}
	
}
