package doctorlm.MedicalHealth.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AutoTakeScreenShot {
	
	public static void takeScreenShot(WebDriver driver) {
		
		File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-hh_mm_ss");
		String path_temp = String.valueOf(sdf.format(new Date()));
		File desFile = new File(System.getProperty("user.dir")+"/test-output/screenshot/"+path_temp+".png");
		try {
			FileUtils.copyFile(screenShot, desFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void takeScreenShot(WebDriver driver,String fileName) {
		
		File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-hh_mm_ss");
		String path_temp = String.valueOf(sdf.format(new Date()));
		File desFile = new File(System.getProperty("user.dir")+"//test-output///screenshot//"+fileName+path_temp+".png");
		try {
			FileUtils.copyFile(screenShot, desFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
