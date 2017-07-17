package com.Imdb.utility;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Utility {
	//method to click on all items in a dropdown sort and see if it returns all elements
	public void CheckLinkOnOptionValueChange(String first, String second,
			String third, WebDriver driver) throws InterruptedException {
		WebElement ele = driver.findElement(By.name(first));
		List<WebElement> allitems = driver.findElements(By.xpath(second));
		//System.out.println(allitems.size());
		Select se = new Select(ele);
		for (int i = 0; i < allitems.size(); i++) {
			se.selectByIndex(i);
			Thread.sleep(4000);
			checkForLink(third, driver);
		}
	}

	//method to check for link and to see if there is one item
	public void checkForLink(String loc, WebDriver driver) {
		WebElement e = driver.findElement(By.xpath(loc));
		String firstentry = e.getText();
		Assert.assertTrue(firstentry != null);
	}
	
	

}
