package com.souq.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class PageBase {

	protected static WebDriver driver;
	public Actions Action;

	public PageBase(WebDriver driver) // constructor
	{
		PageFactory.initElements(driver, this);
	}

	public static void setTextElementText(WebElement textElement, String value) // method to send text to element
	{
		textElement.sendKeys(value);
	}

}
