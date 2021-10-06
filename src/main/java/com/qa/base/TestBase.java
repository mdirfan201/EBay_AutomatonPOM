package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.qa.util.TestUtil;
import com.qa.util.WebEventListener;


import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public static WebDriver driver;
    public static Properties prop;
	static EventFiringWebDriver e_driver;
	
	public TestBase() {
		try {
			FileInputStream ip= new FileInputStream("D:\\IRFAN---\\java program\\Ebay_AutomationPOM\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop= new Properties();
			prop.load(ip);
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void initialization() {
		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("FireFox")) {
			System.setProperty("webdriver.gecko.driver", "D:\\IRFAN---\\java program\\VirVentureLiveEnvironment_AutomatioFramework\\Browser-Driver\\geckodriver.exe");
			driver= new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.edge.driver", "D:\\IRFAN---\\java program\\VirVentureLiveEnvironment_AutomatioFramework\\Browser-Driver\\msedgedriver.exe");
			driver= new FirefoxDriver();
		}else {
			
			System.out.println("browser key not available in properties file");
		}
		e_driver= new EventFiringWebDriver(driver);
		WebEventListener eventListener= new WebEventListener();
		e_driver.register(eventListener);
		driver=e_driver;
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICT_WAIT_TIMEOUT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
	}
	
	
	
	
	public void getScreenShot(String methodName) {
		TakesScreenshot ts= (TakesScreenshot) driver;
		File src= ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File("D:\\IRFAN---\\java program\\Ebay_AutomationPOM\\ScreenShots\\"+methodName + "_"+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
