package com.profiler.scripts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.profiler.utils.AppUtil;
import com.profiler.utils.Excel;
import com.profiler.utils.Functions;

public class ProfileDashborad extends Functions {

	
	// Data :-
		String excel_file = "files//TestData.xlsx";
		String sheet_name = "Application";
		String sheet_name2 = "SearchProfile";
		
		String url = Excel.readFromExcel(excel_file, sheet_name, 1, 1); 
		String username = Excel.readFromExcel(excel_file, sheet_name, 2, 1); 
		String password = Excel.readFromExcel(excel_file, sheet_name, 3, 1); 

		String ProfilesSearch = Excel.readFromExcel(excel_file,sheet_name2, 2,1);

		// Locators :-	
		By username_homepage = By.xpath("//*[@id='linksPopupUsermenu']/div[1]/div[1]");
		By profile_tab = By.xpath("//a[@id='section_profile']");
		By SearchProfiles = By.xpath("//input[@id='searchall']");
		By SearchButton = By.xpath("//span[@id='search_list_btn']");

		By profileLink = By.xpath("//*[contains(text(),'"+ProfilesSearch+"')]");
		By DashboardTab = By.xpath("//a[@title='Dashboard']");
		
		By Dashboard_Summary= By.xpath("//*[@id='profileSummarySection']//div[contains(text(),'"+ ProfilesSearch+"')]");
		
		AppUtil appUtil = new AppUtil();
		
		
		// Locators :-	
		String BrandElement = Excel.readFromExcel(excel_file,sheet_name2, 1,1);
		By RefineBy = By.xpath("//div[@class='marketAccess-filter-current'][contains(text(),'Brand')]");
		By RefineByElement = By.xpath("//input[@value='ACTIQ']");
		
		By profilelist=By.xpath("//div[contains(text(),'" +BrandElement+"')]");	
		
		By clearAll_button = By.id("refine_clear_all");
	
	
	@Test

	public void profileDashboard() {

		extentTest = extent.startTest("profileDashboard");

		
		if ( appUtil.login(username, password) )	{
			System.out.println("testCreateProfile() - User Login SUCCESSFUL");
		
		
		click(profile_tab);
		wait(5);
		
		click(clearAll_button);
		wait(2);
		click(SearchProfiles);
		wait(5);
		enter(SearchProfiles, ProfilesSearch);
		wait(5);
		click(SearchButton);
		wait(5);
		click(profileLink);
		wait(5);
		click(DashboardTab);
		
		if (isElementPresent(driver, Dashboard_Summary)){
			
			System.out.println("Dashboard Summary is Visible ");
		} else {
			Assert.fail("Dashboard Summary is NOT Visible");
			getScreenshot(driver, "Dashboard_Summary_Area");
		}

		}else
		System.out.println("profileDashboard() - User Login UNSUCCESSFUL");
		
		appUtil.logout();

	} 
		
	@Test

	public void profilesTab() {

		extentTest = extent.startTest("profilesTab");
		
		
		
		if ( appUtil.login(username, password) )	{
			System.out.println("testCreateProfile() - User Login SUCCESSFUL");
		
		click(profile_tab);
		wait(5);
		
		click(clearAll_button);
		wait(2);
		
		click(RefineBy);
		wait(5);

		WebElement checkbox = driver.findElement(RefineByElement);
		if (!checkbox.isSelected()) {
			checkbox.click();
		}

		wait(5);
		click(RefineBy);
		wait(5);

	//	int profilecount = driver.findElements(profilelist).size();
		By profilecount =By.xpath("//*[@id='headers']//span[contains(text(),'Results')]");
		
		System.out.println("profilecount----" + getText(profilecount));


			List<WebElement> actual = driver.findElements(profilelist);

			for(WebElement val :actual )
			{
			if (val.getText().contains(BrandElement))
				Assert.assertEquals(true, true);
			else{
				Assert.fail("Refine By Brand: "+BrandElement+"UNSUCCESSFUL ");
				getScreenshot(driver, "Profile_RefineBy_Results");
			}
			}
	
		
		

	}else
	System.out.println("profilesTab() - User Login UNSUCCESSFUL");
		appUtil.logout();
	}
				
		
}
