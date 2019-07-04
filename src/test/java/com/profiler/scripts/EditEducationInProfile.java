package com.profiler.scripts;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.profiler.utils.AppUtil;
import com.profiler.utils.Excel;
import com.profiler.utils.Functions;

public class EditEducationInProfile extends Functions {

	// Data :-
	String excel_file = "files//TestData.xlsx";
	String sheet_name = "ProfileEducation";
	String appData_sheet = "Application";//added
	
	String url = Excel.readFromExcel(excel_file, appData_sheet, 1, 1);
	String username = Excel.readFromExcel(excel_file,appData_sheet, 2, 1);
	String password = Excel.readFromExcel(excel_file,appData_sheet, 3, 1);
	String search_profile_value = Excel.readFromExcel(excel_file, sheet_name, 7, 1);
	
	
    String fromyear_value=Excel.readFromExcel(excel_file, sheet_name, 5, 1);
    String toyear_value=Excel.readFromExcel(excel_file, sheet_name, 6, 1);
    
	String instNameValue = null;
	String modifiedInstName_value = null;
	String modifiedYear_value = null;
	String viewPageInstname_value = null;
	String viewPageYear_value = null;


	// Locators :-
	By username_field = By.xpath("//*[@value='Username']");
	By password_field = By.xpath("//*[@class='defText-fakePassword']");
	By signin_button = By.xpath("//*[@alt='Sign In']");
	By username_homepage = By.xpath("//*[@id='linksPopupUsermenu']/div[1]/div[1]");
	By profile_tab = By.xpath("//a[@id='section_profile']");
	By search_profile_field = By.xpath("//input[@id='searchall']");
	By search_button = By.xpath("//span[@id='search_list_btn']");

	//By profile_result_name_link = By.xpath("//table[@class='grid tblProfiles profileThumbviewTable']//a[contains(text(),'"
	//				+ search_profile_value + "')]");//Change another xpath
	
	By profile_result_name_link = By.xpath("//div[@id='thumbTableDiv']/table[@class='grid tblProfiles profileThumbviewTable']/tbody/tr[1]/td[3]//a[1]");
	
	By personal_info_btn = By.xpath("//a[@title='Personal Info']");
	By actions_btn = By.xpath("//span[@id='actions']");
	By edit_link = By.xpath("//a[@title='Edit']");

	By edit_eduLink = By.xpath("//table[@class='grid resizeTable tblPublications']/tbody/tr[1]/td[6]/a[2]");//updated xpath,By.xpath("//table[@class='grid resizeTable tblPublications']/tbody/tr/td[6]/a[2]");
	By search_image = By.xpath("//img[@src='/profiler/_assets/newimages/search.gif']");
	By type_search = By.xpath("//div[@id='companyType_dd_div']//input[@class='dropdown dd-all']");
	By association_name = By.xpath("//div[@id='profilerPopupDiv1']/following-sibling::div/a[@name='67']");//updated xpath,By.xpath("//a[@name='67']
	By search_edu_btn = By.xpath("//span[@id='search_education']");
	By institution_name = By.xpath("//table[@class='grid resizeTable tblProfiles']/tbody/tr[2]/td[1]/span[1]/input[1]");
	By institution_name_value = By.xpath("//div[@id='profilerPopupDiv1']//tbody//tr[2]//td[2]//div[1]");
	By select_btn = By.xpath("//span[@id='select_education']");

	By fromyeardropdown = By.xpath("//div[@id='attendedYearFrom_dd_div']//input[@class='dropdown dd-all']");
	By fromyearvalue_loc = By.xpath("//div[@id='profilerPopupDiv']/following-sibling::div[2]/a[@name='"+fromyear_value+"']");//By.xpath("//div[@class='dropdownOpts' and contains(@style,'display: block')]/a[contains(@name,'"+fromyear_value+"')]");

	By toyeardropdown = By.xpath("//div[@id='attendedYearTo_dd_div']//input[@class='dropdown dd-all']");
	By toyearvalu_loc = By.xpath("//div[@id='profilerPopupDiv']/following-sibling::div[3]/a[@name='"+toyear_value+"']");//By.xpath("//div[@class='dropdownOpts' and contains(@style,'display: block')]/a[contains(@name,'"+toyear_value+"')]");

	By save_education = By.xpath("//span[@id='save_education']");
	By modifiedInstName = By.xpath("//div[@id='educationTableDiv']/table[@class='grid resizeTable tblPublications']/tbody/tr[1]/td[2]/div");//updated xpath, By.xpath("//table[@class='grid resizeTable tblPublications']/tbody/tr/td[2]/div");
	By modifiedYear = By.xpath("//div[@id='educationTableDiv']/table[@class='grid resizeTable tblPublications']/tbody/tr[1]/td[5]/div");//updated xpath,By.xpath("//table[@class='grid resizeTable tblPublications']/tbody/tr/td[5]/div");

	By final_save = By.xpath("//span[@id='save']");
	By viewPageInstName_loc = By.xpath("//div[@id='educationTableDiv']/table[@class='grid resizeTable tblProfiles']/tbody/tr[1]/td[1]/div");//updated xpath, By.xpath("//table[@class='grid resizeTable tblProfiles']/tbody/tr/td[1]/div");
	By viewPageYear_loc = By.xpath("//div[@id='educationTableDiv']/table[@class='grid resizeTable tblProfiles']/tbody/tr[1]/td[4]/div");//updated xpath, By.xpath("//table[@class='grid resizeTable tblProfiles']/tbody/tr/td[4]/div");
	
	By biography_btn = By.xpath("//a[@title='Biography']");//Added
	
	@Test
	public void editProfileEducation() {
		
		extentTest = extent.startTest("Edit Profile Education ");
		AppUtil appUtil = new AppUtil();
		
		/*getUrl(url);
		wait(5);
		enter(username_field, username);
		wait(1);
		enter(password_field, password);
		wait(1);
		click(signin_button);
		wait(5);*/
		
		try {//added
			if (appUtil.login(username, password))
				System.out.println("User Login SUCCESSFUL");
			else
				Assert.fail("editProfileEducation() - User Login UNSUCCESSFUL");
			// System.out.println(driver.getTitle());
			// wait(5);
			click(profile_tab);
			wait(3);
			enter(search_profile_field, search_profile_value);
			click(search_button);
			wait(3);
			click(profile_result_name_link);
			wait(2);
			click(biography_btn);//previous it was personal_info_btn//updated
			wait(2);
			click(actions_btn);
			click(edit_link);
			wait(3);
			click(edit_eduLink);
			wait(3);
			click(search_image);
			wait(3);
			click(type_search);
			click(association_name);
			click(search_edu_btn);
			wait(2);
			click(institution_name);
			instNameValue = getText(institution_name_value);
			click(select_btn);
			click(fromyeardropdown);
			wait(2);
			click(fromyearvalue_loc);
			wait(2);
			/*
			 * fromyear_value=getText(fromyearvalue_loc);
			 * fromyear_value=fromyear_value.concat("-")
			 */;
			click(toyeardropdown);
			wait(2);
			click(toyearvalu_loc);
			wait(2);
			// toyear_value=getText(toyearvalu_loc);
			click(save_education);
			wait(3);
			String finalyearValue = fromyear_value.concat("-" + toyear_value);
			modifiedInstName_value = getText(modifiedInstName);
			
			//debug
			System.out.println(modifiedInstName_value);
			modifiedYear_value = getText(modifiedYear);
			System.out.println(modifiedYear_value);
			//debug
			
			try {
				if (instNameValue.contentEquals(modifiedInstName_value)
						&& finalyearValue.contentEquals(modifiedYear_value)) {
					System.out.println("Modified Organisation and Years are added successfully");
				} else {
					Assert.fail("Modified Organisation and Years are not added");
				}
			} catch (Exception e){
				Assert.fail("Exception occurred", e);
			}
			click(final_save);
			wait(3);
			viewPageInstname_value = getText(viewPageInstName_loc);
			viewPageYear_value = getText(viewPageYear_loc);
			try {
				if (instNameValue.contentEquals(viewPageInstname_value)
						&& finalyearValue.contentEquals(viewPageYear_value)) {
					System.out.println("Modified details are displayed at Education Section");
				} else {
					Assert.fail("Modified details are not displayed at Education Section");
				}
			} catch (Exception e) {
				Assert.fail("Exception occurred", e);
			}
		}catch (Exception problem) {//added
			problem.printStackTrace();//added
		}finally{//added
			appUtil.logout();//added
		}//added	
	}
}
