package com.profiler.scripts;

import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestResult;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.profiler.utils.Excel;
import com.profiler.utils.Functions;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class TestRunner {

	
	public static WebDriverWait wait;
	public static WebDriver driver;
	public static String browserType=null;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static ExtentTest extentTest;
	public static String browsername = Excel.readFromExcel("files//TestData.xlsx", "Application", 4, 1);

	@BeforeSuite
	public void setReport()	{
		extent = new ExtentReports(System.getProperty("user.dir") +"/test-output/TestReport.html", true);
		
		 extent
         .addSystemInfo("Host Name", "TEST MACHINE")
         .addSystemInfo("Environment", "WINDOWS_CHROME")
         .addSystemInfo("User Name", "TEST USER");
        
		System.out.println(System.getProperty("user.dir"));
		//extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
	}
	
	
	@BeforeClass
	public static WebDriver setup() {
			
		if(browsername.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "Drivers\\chromedriver-win.exe");
			ChromeOptions weboptions = new ChromeOptions();
			weboptions = weboptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
			driver = new ChromeDriver(weboptions);
		}
		else if(browsername.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browsername.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver", "Drivers\\IEDriverServer.exe");
			driver= new InternetExplorerDriver();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
		
		
	}
	

	public static String getScreenshot(WebDriver driver, String screenshotName)  {
		 String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		 TakesScreenshot ts = (TakesScreenshot) driver;
		 File source = ts.getScreenshotAs(OutputType.FILE);
		 String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+"_"+dateName+".png";
		 
		 File finalDestination = new File(destination);
		 try {
			 FileUtils.copyFile(source, finalDestination);
		 }catch(Exception e) {
			System.out.println("******   Exception occured trying to capture screenshot - " +screenshotName); 
		 }
		 
		 return destination;
	}
	

	
	public static String getBrowserType(){
		String excel_file = "files//Application.xlsx";
		String sheet_name = "Application";
		
		return Excel.readFromExcel(excel_file, sheet_name, 4, 1);
	}
	public static WebDriverWait setWait(){
		if(wait == null) {
 			wait = new WebDriverWait(driver, 60);
 		}
		return wait;
	}

	@AfterMethod	
	public void tearDown(ITestResult result) throws Exception	
	{
		if(ITestResult.FAILURE==result.getStatus())
		{
			String screenshotpath = TestRunner.getScreenshot(driver, result.getName());
			
			extentTest.log(LogStatus.FAIL, "Screenshot ", result.getThrowable() +extentTest.addScreenCapture(screenshotpath)); // add screenshot to extent report
		}
		else if(result.getStatus()==ITestResult.SKIP)
		{
			extentTest.log(LogStatus.SKIP, "Test case skipped is ", result.getName());
		}
		else if(result.getStatus() == ITestResult.SUCCESS){
			 extentTest.log(LogStatus.PASS, "Test case - ", result.getName());
		 }
		
		extent.endTest(extentTest);
	}
	 
	
	 @AfterTest
	 public void endReport(){
		  extent.flush();
	 }

	@AfterClass
	public static void close_browser()	{
		
		new Functions().closeAllinstances();
	}
	
	@AfterSuite
	public void close()	{
        extent.close();
  
	}
		
}
