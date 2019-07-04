package com.profiler.classification;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.Test;

public class WordSearch {
@Test
public void searchTextCount() throws InterruptedException {
	
	System.setProperty("webdriver.chrome.driver", "D:\\HeartBeat-MTP-042\\Drivers\\chromedriver-win.exe");
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("http://www.google.com");
	driver.findElement(By.name("q")).sendKeys("Pavan");
	WebElement textBox = driver.findElement(By.name("q"));
	textBox.sendKeys(Keys.ENTER);
	List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'Pavan')]"));
    if(list.size() == 0){
    	System.out.println("Test not found on webpage");
    }else{
    	System.out.println("Expected text found on the webpage: " + list.size() + " times");
    }

    driver.close();
}
}
