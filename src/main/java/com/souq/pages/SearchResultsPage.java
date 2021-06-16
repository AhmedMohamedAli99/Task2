package com.souq.pages;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.google.gson.Gson;
import com.souq.model.Product;

public class SearchResultsPage extends PageBase {

	public SearchResultsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//div[contains(@class,'medium-up-1 tpl' )]")
	public WebElement searchTableResults;

	@FindBy(xpath="//li[contains(text(),'Items found' )]")
	public WebElement itemSearchExist;
	
	@FindBy(xpath="//li[contains(@class,'crumbs' )]/h1")
	public WebElement searchResultTitle;


	public String checkItemExist() {
		return itemSearchExist.getText();
	}

	public String checkSearchTitle() {
		return searchResultTitle.getText();
	}
	
	public List<Product> getResult() {
		List<WebElement> productList = searchTableResults.findElements(By.xpath("./child::*")); // creating a list of the search results
		List<Product> productItems = new ArrayList<Product>(); // creating a list to add items in with iteration
		for (int i = 0; i < productList.size(); i++) {
			String name = productList.get(i).getAttribute("data-name")
					.replace("\u00a0", " ").replace("\u0026", " "); // removing &nbsp; from the title of the product

			String price = productList.get(i)
					.findElement(By.xpath("//h3[@class='itemPrice']")).getText(); // getting the price of each item using parent webelement

			Product product = new Product(name, price);
			productItems.add(product); // adding title and price into list
			System.out.println(name + "   " + "||" + "   " + price);
		}
		return productItems;
	}

	public void extractList() {
		Gson gson = new Gson(); // using gson library to extract array list into JSON file
		String json = gson.toJson(getResult());
		try {
			FileWriter file = new FileWriter("D:\\/output.json"); // giving the path to extract the file
			file.write(json);
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}