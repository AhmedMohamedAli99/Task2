package com.souq.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.souq.pages.HomePage;

public class SearchTest extends TestBase {

	public SearchTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	
	HomePage homeobject;
	public String productName="iphone";
	
	@Test
	public void SearchForItem() {

		homeobject = new HomePage(driver);
		homeobject.Search(productName);

	}

}
