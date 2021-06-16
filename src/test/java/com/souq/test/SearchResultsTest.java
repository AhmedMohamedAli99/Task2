package com.souq.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.souq.pages.HomePage;
import com.souq.pages.SearchResultsPage;

public class SearchResultsTest extends TestBase {

	public SearchResultsTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	HomePage homeobject;
	SearchResultsPage searchResultObject;
	public String productName = "iPhone";

	@Test
	public void printAndExportSearchListToJson() {

		homeobject = new HomePage(driver);
		homeobject.Search(productName);
		searchResultObject = new SearchResultsPage(driver);
		Assert.assertTrue(searchResultObject.checkItemExist().contains("Items found"));
		Assert.assertTrue(searchResultObject.checkSearchTitle().contains(productName));
		searchResultObject.getResult();
		searchResultObject.extractList();

	}

}
