package com.Imdb.cases;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.apache.poi.xwpf.usermodel.examples.UpdateEmbeddedDoc;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Imdb.utility.Utility;


public class Mainclass {
	
	 WebDriver driver;
	 Utility util;


	@BeforeMethod
	public void setUp() throws Exception {
		System.setProperty(
				"webdriver.chrome.driver",
				"C:\\Libraries\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		util = new Utility();
		driver.get("http://www.imdb.com/chart/top");
			}
	
	//
	@Test(priority=1, description = "Top 250 page returns at least 1 movie in the list")
	public void checkforrecordOnLoad() throws Exception{
		util.checkForLink(".//*[@id='main']/div/span/div/div/div[3]/table/tbody/tr[1]/td[2]/a",driver);
			}
	
	@Test(priority=2, description = "Return 1 movie for each of the sorting options")
	public void checkOptionChange() throws Exception{
		util.CheckLinkOnOptionValueChange("sort",".//*[@id='main']/div/span/div/div/div[3]/div/div/div[1]/select/option",".//*[@id='main']/div/span/div/div/div[3]/table/tbody/tr[1]/td[2]/a",driver);
		
	}
	
	@Test(priority=3, description = "Return 1 movie when navigating to the Western genre")
	public void checkForRecordOnWestern() throws Exception{
		driver.findElement(By.xpath(".//*[@id='sidebar']/div[7]/span/ul/li[21]/a")).click();
		Thread.sleep(3000);
		//assertEquals(driver.getTitle(), "IMDb: Highest Rated Western Feature Films With At Least 25000 Votes - IMDb");
		Thread.sleep(3000);
		util.checkForLink("html/body/div[1]/div/div[4]/div[3]/div[1]/div/div/div[3]/div[1]/div[3]/h3/a",driver);
		
	}
	
	
	//Function that could be used to check for all entries under Movie Category
	@Test(priority = 4, enabled= false,description = "get all elements")
	public void testcheck() throws Exception{
		//".//*[@id='sidebar']/div[7]/span/ul/li[" + i "*]/a""driver.findElement(By.xpath(".//*[@id='sidebar']/div[7]"));//li[contains(@class,'managed-services')]/ul/li/a"
		//List<WebElement> allelems = driver.findElements(By.xpath(".//*[@id='sidebar']/div[7]/span/ul/li[*]/a"));
		List<WebElement> allitems = driver.findElements(By.xpath(".//*[@id='sidebar']/div[7]/span/ul/li[*]/a"));
		for (int i =1; i<allitems.size();i++){
			
			String numberAsString = String.valueOf(i);
			StringBuilder sb = new StringBuilder();
			sb.append(".//*[@id='sidebar']/div[7]/span/ul/li[");
			sb.append(numberAsString);
			sb.append("]/a");
			String strI = sb.toString();
			System.out.println(strI);
			
			
			//String updatedxpath =".//*[@id='sidebar']/div[7]/span/ul/li[" + numberAsString "]/a";
			Thread.sleep(3000);
			driver.findElement(By.xpath(strI)).click();
			Thread.sleep(3000);
			util.checkForLink(".//*[@id='main']/div/div/div[3]/div[1]/div[3]/h3/a", driver);
			Thread.sleep(3000);
			driver.navigate().back();
			
			
				 
			//System.out.println(numberAsString);
			//util.checkForLink(allelems.get(i), driver);
		}
		
	}
	
	@AfterMethod
	public void close() throws Exception{
		driver.close();
	}

}


