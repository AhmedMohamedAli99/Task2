package com.souq.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utilities.Helper;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	static String chromepath = System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe"; // to get path for chrome
	static String firefoxpath = System.getProperty("user.dir") + "\\Drivers\\geckodriver.exe"; // to get path for
																								// firefox
	static String InternetExplorerpath = System.getProperty("user.dir") + "\\Drivers\\IEDriverServer.exe"; // to get
																											// path for
																											// internet
																											// explorer
	static String configpath = System.getProperty("user.dir")
			+ "\\src\\test\\java\\com\\souq\\configuration\\config.properties"; // to get path for config properties
																				// file

	public TestBase() throws IOException // this is constructor for initilzation property file
	{
		prop = new Properties();
		FileInputStream file = new FileInputStream(configpath);
		prop.load(file);
	}

	@Parameters({ "browser" })
	@BeforeSuite
	public void startdriver(@Optional("chrome") String browsername) // this initilization method before any test
	{
		if (browsername.equalsIgnoreCase("chrome")) // work with chrome driver
		{

			System.setProperty("webdriver.chrome.driver", chromepath);
			driver = new ChromeDriver();
		} else if (browsername.equalsIgnoreCase("firefox")) // work with firefox driver
		{
			System.setProperty("webdriver.gecko.driver", firefoxpath);
			driver = new FirefoxDriver();
		} else if (browsername.equalsIgnoreCase("internetexplorer")) // work with internet explorer
		{
			System.setProperty("webdriver.ie.driver", InternetExplorerpath);
			driver = new InternetExplorerDriver();
		}

		driver.get(prop.getProperty("URL")); // to get url from config.properties and open it in browser
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // implicit wait to make page load and element
		driver.manage().window().maximize(); // to maximize window
	}

	@AfterSuite
	public void teardown() {
		driver.quit();

	}

	@AfterMethod // this method take screenshot in failure but override picture if have more than
					// fail
	public void ScreenShotonFailureandclosedriver(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			System.out.println("fail will take screenshot");
			Helper.takescreenshot(driver, result.getName());
		}
	}

}
