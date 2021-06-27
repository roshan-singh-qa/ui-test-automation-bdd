package com.rk.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ButtonHelper {

	private WebDriver driver;
	public static Logger log =LogManager.getLogger(ButtonHelper.class);
	
	
	public ButtonHelper(WebDriver driver) {
		this.driver = driver;
		log.debug("ButtonHelper : " + this.driver.hashCode());
	}
	
	public void click(By locator) {
		click(driver.findElement(locator));
		log.info(locator);
		
	}
	
	public void click(WebElement element){
		element.click();
		log.info(element);
		
	}
}
