package com.qa.Testcases;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.base.TestBase;
import com.qa.pages.HomePage;

public class HomePageTest extends TestBase {
	static HomePage homepage;
	static ExtentReports extent;
	static ExtentSparkReporter spark;
	static ExtentTest test;
	
	public HomePageTest() {
		super();// TODO Auto-generated constructor stub
	}
	
	@BeforeSuite
	public void setupExtentReport() {
		extent= new ExtentReports();
		spark= new ExtentSparkReporter("D:\\IRFAN---\\java program\\Ebay_AutomationPOM\\ExtentReports\\HomePage.html");
		spark.config().setReportName("Mohammed Irfan");
		spark.config().setDocumentTitle("Ebay_HomePage Automation");
		spark.config().setTheme(Theme.DARK);
		extent.attachReporter(spark);
		
		extent.setSystemInfo("Author", "Mohammed Irfan");
		extent.setSystemInfo("Tool", "Eclipse");
		extent.setSystemInfo("Language", "Selenium with Java");
		extent.setSystemInfo("OS", "Windows10");
	}
	
	@AfterSuite
	public void tearExtentReoprt() {
		extent.flush();
	}
	
	@BeforeMethod
	public void setup() {
		initialization();
		homepage= new HomePage();
		
	}
	
	@Test(priority=1)
	public void ValidateHomePageTitleTest() {
		test=extent.createTest("TC_01 : HOMEPAGE Validate Title Test");
		String ExpextedTitle="Electronics, Cars, Fashion, Collectibles & More | eBay";
		String ActualTitle= homepage.validateTitle();
		Assert.assertEquals(ActualTitle, ExpextedTitle, "Title Not matched");
	}
	
	@Test(priority=2)
	public void ValidateHomePageLogoTest() {
		test=extent.createTest("TC_02 : HOMEPAGE Validate Logo Test");
		boolean flag= homepage.validateLogo();
		Assert.assertTrue(flag, "Logo not Dispalyed");
	}
	
	@Test(priority=3)
	public void ValidateHomePageSignInTest() {
		test=extent.createTest("TC_03 : HOMEPAGE Validate SignIn Test");
		homepage.validateSignIn();
		
	}
	
	@Test(priority=4)
	public void ValidateHomePageRegisterTest() {
		test=extent.createTest("TC_04 : HOMEPAGE Validate Register Test");
		homepage.validateRegister();
		
	}
	//
	@Test(priority=5)
	public void ValidateHomePageDailyDealsTest() {
		test=extent.createTest("TC_05 : HOMEPAGE Validate DailyDeals Test");
		homepage.validateDailyDeals();
		
	}
	
	@Test(priority=6)
	public void ValidateHomePageContactTest() {
		test=extent.createTest("TC_06 : HOMEPAGE Validate Contact Test");
		homepage.validateContact();
		
	}
	
	@Test(priority=7)
	public void ValidateHomePageSellTest() {
		test=extent.createTest("TC_07 : HOMEPAGE Validate Sell Test");
		homepage.validateSell();
		
	}
	
	@Test(priority=8)
	public void ValidateHomePageMayeBayTest() throws InterruptedException {
		test=extent.createTest("TC_08 : HOMEPAGE Validate MyeBay Test");
		homepage.validateMyeBay();
		
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test cases MethodName Failed ==>" + result.getName());
			test.log(Status.FAIL, "Test cases MethodName Error is==>" + result.getThrowable());
			test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
		}
		else if(result.getStatus()==ITestResult.SKIP) {
			test.log(Status.SKIP, "Test cases MethodName Skiped ==>" + result.getName());
			test.log(Status.SKIP, "Test cases MethodName Skiped ==>" + result.getThrowable());	
			test.log(Status.SKIP, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
			
		}
		else if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test cases MethodName==>" + result.getName());
			test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShots()).build());
			
		}
		driver.close();
		
	}
	
	public static String getBase64ScreenShots() {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
	}
}
