package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.TestBase;

public class HomePage extends TestBase {
	
	public HomePage() {
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(xpath="//img[@id='gh-logo']")
	public WebElement Logo;
	@FindBy(xpath="//a[normalize-space()='Sign in']")
	public WebElement SignInBtn;
	@FindBy(xpath="//a[normalize-space()='register']")
	public WebElement RegisterBtn;
	@FindBy(xpath="//a[normalize-space()='Daily Deals']")
	public WebElement DailyDeals;
	@FindBy(xpath="//a[@class='gh-p'][normalize-space()='Help & Contact']")
	public WebElement Contact;
	@FindBy(xpath="//a[normalize-space()='Sell']")
	public WebElement Sell;
	@FindBy(xpath="//a[normalize-space()='My eBay']")
	public WebElement MyeBay;
	@FindBy(xpath="//a[normalize-space()='Summary']")
	public WebElement Summary;
	
	@FindBy(xpath="//a[normalize-space()='Messages']")
	public WebElement Message;
	
	@FindBy(xpath="//input[@id='gh-ac']")
	public WebElement txtSearchProducts;
	
	
	
	public String validateTitle() {
		return driver.getTitle();
	}
	
	public boolean validateLogo() {
		
		return Logo.isDisplayed();
	}
	
	public void validateSignIn() {
		SignInBtn.click();
		
	}
	
	public void validateRegister() {
		RegisterBtn.click();
	}
	
	public void validateDailyDeals() {
		DailyDeals.click();
	}
	
	public void validateContact() {
		Contact.click();
	}
	
	public void validateSell() {
		Sell.click();
	}
	
	public void validateMyeBay() throws InterruptedException {
		Actions act= new Actions(driver);
		act.moveToElement(MyeBay).build().perform();
		WebDriverWait sumwait= new WebDriverWait(driver, 10);
		sumwait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='Summary']")));
		Summary.click();
		Thread.sleep(2000);
		driver.navigate().back();
		act.moveToElement(MyeBay).build().perform();
		WebDriverWait msgwait= new WebDriverWait(driver, 10);
		msgwait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='Messages']")));
		Message.click();
		Thread.sleep(2000);
	}
	
}

