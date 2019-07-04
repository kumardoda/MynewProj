package com.profiler.utils;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.profiler.scripts.TestRunner;
import com.relevantcodes.extentreports.LogStatus;


public class Functions extends TestRunner {

	public void enter1(By locator, String txt) {
		waitForElementtoBeVisible(locator);
		if (!isElementEnable(locator)) {
			wait(5);
		}
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(txt);
	}
	
	public void enter(By locator, String txt) {
		driver.findElement(locator).sendKeys(txt);
	}
	public void click1(By locator) {
		waitForElementtoBeVisible(locator);
		waitForElementtoBeClickable(locator);
		driver.findElement(locator).click();
	}
	public void click(By locator) {
		WebElement element = driver.findElement(locator);
		wait(1);
		element.click();
	}
	public String getTitle() {
		return driver.getTitle();
	}
	public void closeCurrentTab() {
		driver.close();
	}
	public void waitForElementtoBeClickable(By locator) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(locator));

		} catch (TimeoutException e) {
			Assert.fail("Element is not clickable : " + locator);
		}
	}
	public void getUrl(String url) {
		if (wait == null) 
			TestRunner.setWait();
		
		driver.manage().window().maximize();		
		extentTest.log(LogStatus.INFO, "Given URL- "+url);
		driver.get(url);
	}

	
	public boolean isElementPresent(WebDriver driver, By by)	{
		boolean elementPresence = false;
		
		try 	{
			driver.findElement(by);
			elementPresence = true;
		}catch(NoSuchElementException e)	{
		}
		return elementPresence;
	}	
	
	public boolean selectByValue(WebDriver driver, By locator, String option) {
		boolean result = false;

		Select list = null;

			try {
				list = new Select(driver.findElement(locator));
				list.selectByValue(option);
				result = true;
			} catch (StaleElementReferenceException e) {
				extentTest.log(LogStatus.FAIL, "ERROR: StaleElementReferenceException occured" + " in selectByValue: " + e);
			} catch (Exception e) {
				Assert.fail("ERROR: Exception occured in selectByValue: ");
				extentTest.log(LogStatus.FAIL, "ERROR: Exception occured " + "in selectByValue: " + e);
			}
		return result;
	}
	

	public void waitForElementtoBeVisible(By locator) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

		} catch (TimeoutException e) {
			Assert.fail("Element is not visible : " + locator);
		}
	}

	public void waitForElementtoBePresent(By locator) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));

		} catch (TimeoutException e) {
			Assert.fail("Element is not present : " + locator);
		}
	}

	public void wait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			System.out.println("Failed to wait for :" + seconds + " seconds");
		}
	}

	public void waitForElementToDisappear(By locator) {
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		} catch (TimeoutException e) {
		}
	}
	public boolean isElementEnable(By locator) {
		waitForElementtoBeVisible(locator);
		return driver.findElement(locator).isEnabled();
	}

	public boolean isElementSelected(By locator) {
		waitForElementtoBeVisible(locator);
		return driver.findElement(locator).isSelected();
	}
	public static WebDriverWait setWait(){
		if(wait == null) {
 			//wait = new WebDriverWait(driver, 60);
 		}
		return wait;
	}
	public void closeAllinstances() {
		wait(4);
		driver.quit();
		driver = null;
		wait = null;
	}
	public void verifyelementText(String msg, By locator, String expectedTxt) {
		verifyValues(msg, expectedTxt, getText(locator));
	}
	public String getText(By locator) {
		return driver.findElement(locator).getText();
	}
	public void verifyValues(String msg, String expected, String actuals) {
		Assert.assertEquals(msg, expected, actuals);
	}
	
	public boolean selectByIndex(By locator, int index) {
		boolean result = false;

		Select list = null;

			try {
				list = new Select(driver.findElement(locator));
				list.selectByIndex(index);
				result = true;
			} catch (StaleElementReferenceException e) {
				extentTest.log(LogStatus.FAIL, "ERROR: StaleElementReferenceException occured" + " in selectByValue: " + e);
			} catch (Exception e) {
				Assert.fail("ERROR: Exception occured in selectByValue: ");
				extentTest.log(LogStatus.FAIL, "ERROR: Exception occured " + "in selectByValue: " + e);
			}
		return result;
	}
	
	public void mouseHoverAndClcik(By locator1, By locator2) {
		
		Actions builder = new Actions(driver);
		WebElement moveon = driver.findElement(locator1);
		WebElement moveTo = driver.findElement(locator2);
		wait(3);
		builder.moveToElement(moveon).moveToElement(moveTo).click()
				.perform();
		
	}
	
	// This method added by Uday
	public void DatePicker_CurrentDate(By DatePickerLocator, By tbodyLocator)
	{
		 String today = getCurrentDay();
		
		//Click and open the datepickers
	       click(DatePickerLocator);
	 
	        //This is from date picker table
	        WebElement dateWidgetFrom = driver.findElement(tbodyLocator);
	 
	        //This are the rows of the from date picker table
	        //List<WebElement> rows = dateWidgetFrom.findElements(By.tagName("tr"));
	 
	        //This are the columns of the from date picker table
	        List<WebElement> columns = dateWidgetFrom.findElements(By.tagName("td"));
	 
	        //DatePicker is a table. Thus we can navigate to each cell
	        //and if a cell matches with the current date then we will click it.
	        for (WebElement cell: columns) {
	            /*
	            //If you want to click 18th Date
	            if (cell.getText().equals("18")) {
	            */
	            //Select Today's Date
	            if (cell.getText().equals(today)) {
	                cell.click();
	                break;
	            }
	        }
	}
	// This method added by Uday
	private String getCurrentDay (){
        //Create a Calendar Object
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
 
        //Get Current Day as a number
        int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
       
        //Integer to String Conversion
        String todayStr = Integer.toString(todayInt);
       
        return todayStr;
    }
	
	
}
