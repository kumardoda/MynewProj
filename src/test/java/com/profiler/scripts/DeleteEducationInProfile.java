package com.profiler.scripts;

import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.profiler.utils.AppUtil;
import com.profiler.utils.Excel;
import com.profiler.utils.Functions;

public class DeleteEducationInProfile extends Functions {

	// Data :-
	String excel_file = "files//TestData.xlsx";
	String testData_sheet = "ProfileEducation";
	String appData_sheet = "Application";

	String url = Excel.readFromExcel(excel_file, appData_sheet, 1, 1);
	String username = Excel.readFromExcel(excel_file, appData_sheet, 2, 1);
	String password = Excel.readFromExcel(excel_file, appData_sheet, 3, 1);
	String search_profile_value = Excel.readFromExcel(excel_file, testData_sheet, 4, 1);

	int rowcount_beforeDelete = 0;
	int rowcount_afterDelete = 0;
	int rowcount_viewpage = 0;

	// Locators :-
	By username_field = By.xpath("//*[@value='Username']");
	By password_field = By.xpath("//*[@class='defText-fakePassword']");
	By signin_button = By.xpath("//*[@alt='Sign In']");
	By username_homepage = By.xpath("//*[@id='linksPopupUsermenu']/div[1]/div[1]");
	By profile_tab = By.xpath("//a[@id='section_profile']");
	By search_profile_field = By.xpath("//input[@id='searchall']");
	By search_button = By.xpath("//span[@id='search_list_btn']");

	//By profile_result_name_link = By.xpath("//table[@class='grid tblProfiles profileThumbviewTable']/tbody/tr[1]/td[1]//a[contains(text(),'"
	//+ search_profile_value + "')]");//Changed with another xpath
	
	By profile_result_name_link = By.xpath("//div[@id='thumbTableDiv']/table[@class='grid tblProfiles profileThumbviewTable']/tbody/tr[1]/td[3]//a[1]");
	
	By personal_info_btn = By.xpath("//a[@title='Personal Info']");
	By actions_btn = By.xpath("//span[@id='actions']");
	By edit_link = By.xpath("//a[@title='Edit']");

	By delete_eduLink = By.xpath("//table[@class='grid resizeTable tblPublications']/tbody/tr[1]/td[6]/a[1]");

	By edudetails_Beforedelete = By.xpath("//div[@id='educationTableDiv']/table[@class='grid resizeTable tblPublications']/tbody/tr");
	By edudetails_afterdelete = By.xpath("//div[@id='educationTableDiv']/table[@class='grid resizeTable tblPublications']/tbody/tr");
	By save_button = By.xpath("//span[@id='save']");
	By edudetails_viewpage = By.xpath("//div[@id='educationTableDiv']/table[@class='grid resizeTable tblProfiles']/tbody/tr");
	
	By biography_btn = By.xpath("//a[@title='Biography']"); //Added
	
	@Test
	public void deleteProfileEducation() {


		extentTest = extent.startTest("delete Profile Education ");
		AppUtil appUtil = new AppUtil();
/*		getUrl(url);
		wait(5);
		enter(username_field, username);
		wait(1);
		enter(password_field, password);
		wait(1);
		click(signin_button);
		wait(5);*/
		
		
		
		//int count = driver.findElements(locator).size();
		
		try {
			if (appUtil.login(username, password))
				System.out.println("User Login SUCCESSFUL");
			else
				Assert.fail("addProfileEducation() - User Login UNSUCCESSFUL");
			// System.out.println(driver.getTitle());
			// wait(5);
			click(profile_tab);
			wait(3);
			enter(search_profile_field, search_profile_value);
			click(search_button);
			wait(3);
			click(profile_result_name_link);
			wait(2);
			click(biography_btn); //previous it was personal_info_btn
			wait(2);
			click(actions_btn);
			click(edit_link);
			wait(3);
			rowcount_beforeDelete = driver.findElements(edudetails_Beforedelete).size(); // getEduTableRows(edudetails_Beforedelete);
			System.out.println(rowcount_beforeDelete);
			click(delete_eduLink);
			wait(3);
			driver.switchTo().alert().accept();
			wait(3);
			rowcount_afterDelete = driver.findElements(edudetails_afterdelete).size(); //getEduTableRows(edudetails_afterdelete);
			System.out.println(rowcount_afterDelete);
			try {
				if (rowcount_afterDelete < rowcount_beforeDelete)
					System.out.println("Education details are deleted successfully at edit page");
				else
					Assert.fail("Education details are not deleted");
			} catch (Exception e1) {

				Assert.fail("Exception Occurred", e1);
			}
			click(save_button);
			wait(3);
			//rowcount_viewpage = getEduTableRows(edudetails_viewpage);
			rowcount_viewpage = driver.findElements(edudetails_viewpage).size();
			try {
				if (rowcount_viewpage == rowcount_afterDelete) {
					System.out.println("Deleted education details are not visible at VIEW PAGE");
				} else
					Assert.fail("Deleted education details are visible at VIEW PAGE");
			} catch (Exception e) {
				Assert.fail("Exception occurred", e);
			} 
		} catch (Exception problem) {
			problem.printStackTrace();
		}
		finally{
			appUtil.logout();
		}
	}
}