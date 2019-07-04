package com.profiler.scripts;

import org.openqa.selenium.By;

import com.profiler.utils.AppUtil;
import com.profiler.utils.Excel;
import com.profiler.utils.Functions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends Functions {
	
	String profileName = null;
	
	@Test (priority=1)
	public void createProfile()	{
		
		extentTest = extent.startTest("Create Profile ");		
		String excel_file = "files//TestData.xlsx";
		String sheet_name = "Application";
		profileName = null;
		
		
		
		String username = Excel.readFromExcel(excel_file, sheet_name, 2, 1); 
		String password = Excel.readFromExcel(excel_file, sheet_name, 3, 1); 
		AppUtil appUtil = new AppUtil();
		
		if ( appUtil.login(username, password) )	{
			System.out.println("testCreateProfile() - User Login SUCCESSFUL");
			
			profileName = appUtil.createProfile("");
			
			if( profileName!=null )	{
				System.out.println("testCreateProfile() > profileName -" + profileName);
			} else
				System.out.println("testCreateProfile() - Profile Creation UNSUCCESSFUL");
		} else
			System.out.println("testCreateProfile() - User Login UNSUCCESSFUL");			
	}
	
	
	
	@Test (priority=2, dependsOnMethods={"createProfile"})
	public void profileSearch()	{
		
		extentTest = extent.startTest("Profile Search ");		
		
		
		By prof_searchBox = By.id("txtSearchTop_keyw");
		
		By prof_list_item = By.xpath("//*[@id='"+profileName+"']");
		
		By Search_Result = By.xpath("//h3/a[contains(text(), '"+profileName+"')]");
		
	
		
		
		int wait_time = 5;
		wait(wait_time);
		if(isElementPresent(driver, prof_searchBox))	{
			
			enter(prof_searchBox, profileName);
			wait(2);
			click(prof_list_item);
			wait(wait_time);
			
			if(isElementPresent(driver, Search_Result))	{
				System.out.println("profileSearch() - Profile search SUCCESSFUL");
			} else {
				Assert.fail("Profile name not found in search");
				getScreenshot(driver, "Profile_Search_TextArea");
			}			
		} else {
			Assert.fail("Profile search textbox not found");
			getScreenshot(driver, "Profile_Search_TextArea");
		}
		new AppUtil().logout();
	}
	
	
	
	/* //@Test  
	public void Dynamic2ColumnSection()	{
		
		
		extentTest = extent.startTest("Dynamic2ColumnSection ");		
		String excel_file = "files//TestData.xlsx";
		String sheet_name = "LoginDetails";
		profileName = null;
		
		String username = Excel.readFromExcel(excel_file, sheet_name, 2, 1); 
		String password = Excel.readFromExcel(excel_file, sheet_name, 3, 1); 
		AppUtil appUtil = new AppUtil();
		
		if ( appUtil.login(username, password) )	{
			System.out.println("testCreateProfile() - User Login SUCCESSFUL");
			
			/*profileName = appUtil.createProfile();
			
			if( profileName!=null )	{
				System.out.println("testCreateProfile() > profileName -" + profileName);
			} else
				System.out.println("testCreateProfile() - Profile Creation UNSUCCESSFUL");*/
	/*	} else
			System.out.println("testCreateProfile() - User Login UNSUCCESSFUL");	
		
		appUtil.dynamic2ColumnSection(profileName);

	}*/
	
/*	@Test  (priority=3)	  // Not sure the purpose of this hence commented.
	public void failTest()	{
		
		extentTest = extent.startTest("failTest");	
		
		getUrl("https://www.google.co.in");
		Assert.fail("test failure from failTest()");
	}
	*/
	
	
}
