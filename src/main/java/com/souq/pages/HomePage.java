package com.souq.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageBase {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "search_value")
	public WebElement SearchBox;

	@FindBy(id = "searchButton")
	public WebElement SearchButton;

	public void Search(String name) {

		setTextElementText(SearchBox, name);
		SearchButton.click();
	}

}
