package com.rk.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TextBoxHelper extends GenericHelper{
	
	private WebDriver driver;
	public static Logger log =LogManager.getLogger(TextBoxHelper.class);
	
	public TextBoxHelper(WebDriver driver) {
		super(driver);
		this.driver = driver;
		log.debug("TextBoxHelper : " + this.driver.hashCode());
	}
	
	public void sendKeys(By locator,String value) {
		log.info("Locator : " + locator + " Value : " + value);
		getElement(locator).sendKeys(value);
	}
	
	public void clear(By locator) {
		log.info("Locator : " + locator);
		getElement(locator).clear();
	}
	
	public String getText(By locator) {
		log.info("Locator : " + locator);
		return getElement(locator).getText();
	}
	
	public void clearAndSendKeys(By locator,String value) {
		WebElement element =  getElement(locator);
		element.clear();
		element.sendKeys(value);
		log.info("Locator : " + locator + " Value : " + value);
	}

}
