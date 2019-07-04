package com.profiler.scripts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.profiler.utils.AppUtil;
import com.profiler.utils.Excel;
import com.profiler.utils.Functions;
import com.relevantcodes.extentreports.LogStatus;

public class AddEducationToProfile extends Functions {

	// Data :-
	String excel_file = "files//TestData.xlsx";
	String testData_sheet = "ProfileEducation";
	String appData_sheet = "Application";

	String url = Excel.readFromExcel(excel_file, appData_sheet, 1, 1);
	String username = Excel.readFromExcel(excel_file, appData_sheet, 2, 1);
	String password = Excel.readFromExcel(excel_file, appData_sheet, 3, 1);
	String search_profile_value = Excel.readFromExcel(excel_file, testData_sheet, 4, 1);

	String instNameValue = null;
	String addedInstname_value = null;
	String viewPageInstname_value = null;
	
	String association_name_dd_value = "67";
	String institution_type_value_dd = "110";
	
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
	
	By personal_info_btn = By.xpath("//a[@title='Personal Info']");//Now using biography btn
	By actions_btn = By.xpath("//span[@id='actions']");
	By edit_link = By.xpath("//a[@title='Edit']");

	By new_btn = By.xpath("//span[contains(@id,'header_javascript:newEducationItem')]");
	By search_image = By.xpath("//img[@src='/profiler/_assets/newimages/search.gif']");

	By type_search = By.xpath("//div[@id='companyType_dd_div']//input[@class='dropdown dd-all']");
	By association_name = By.xpath("//div[@id='profilerPopupDiv1']/following-sibling::div/a[@name='"+association_name_dd_value+"']");//updated xpath, not using association entered value
	By search_edu_btn = By.xpath("//span[@id='search_education']");
	By institution_name = By.xpath("//table[@class='grid resizeTable tblProfiles']/tbody/tr[1]/td[1]/span[1]/input[1]");
	By institution_name_value = By.xpath("//div[@id='profilerPopupDiv1']//tbody//tr[1]//td[2]//div[1]");
	By select_btn = By.xpath("//span[@id='select_education']");
	By institution_type_dd = By.xpath("//div[@id='instituteType_dd_div']//input[@class='dropdown dd-all']");
	By institution_type_value = By.xpath("//div[@id='profilerPopupDiv']/following-sibling::div/a[@name='"+institution_type_value_dd+"']");//updated xpath, not using institution entered value
	By save_btn = By.xpath("//*[@id='profilerPopupTarget']/descendant::div[@class='boxFooter']//a[1]");//By.xpath("//span[@id='save_education']");
	By addedInstname_loc = By.xpath("//table[@class='grid resizeTable tblPublications']/tbody/tr[1]/td[2]/div");

	By finalsave_edit = By.xpath("//span[@id='save']");
	By viewPageInstname_loc = By.xpath("//table[@class='grid resizeTable tblProfiles']/tbody/tr[1]/td[1]/div");


	By biography_btn = By.xpath("//a[@title='Biography']");//Added
	By association_select = By.xpath("//div[@id='companyType_dd_div']//select[@id='companyType']");//Added, future use
	String association_value_or_text = "Association";//Added
	By institution_select = By.xpath("//div[@id='instituteType_dd_div']//select[@id='institutionType']");//Added, future use 
	
	String institution_value_or_text = "A.B.";//Added
	By edudetails_viewpage = By.xpath("//div[@id='educationTableDiv']/table[@class='grid resizeTable tblProfiles']/tbody/tr");
	By edudetails_forchecking = By.xpath("//div[@id='educationTableDiv']/table[@class='grid resizeTable tblPublications']/tbody/tr");
	List<WebElement> educationDetails = null;//Added
	WebElement educationName = null;//Added
	int countedEducations = 1;  
	
	
	@Test
	public void addProfileEducation() {
		
		extentTest = extent.startTest("Add Profile Education ");
		AppUtil appUtil = new AppUtil();
		try{
	/*		getUrl(url);
			wait(5);
			enter(username_field, username);
			wait(1);
			enter(password_field, password);
			wait(1);
			click(signin_button);
			wait(5);*/
			
			if ( appUtil.login(username, password) )	
				System.out.println("User Login SUCCESSFUL");
			 else
				Assert.fail("addProfileEducation() - User Login UNSUCCESSFUL");
			
			// System.out.println(driver.getTitle());
			//wait(5);
			click(profile_tab);
			wait(3);
			enter(search_profile_field, search_profile_value);
			click(search_button);
			wait(4);
			/*
			 * click(personal_info_field); wait(5);
			 */
			click(profile_result_name_link);
			wait(3);
			click(biography_btn); //previous it was personal_info_btn
			wait(3);
			click(actions_btn);
			click(edit_link);
			wait(3);
			click(new_btn);
			wait(2);
			click(search_image);
			wait(4);
			click(type_search);
			//click(type_search);//added
			//enter(type_search, association_value_or_text);//Added
			wait(3);
			click(association_name);//changed to entering value
			click(search_edu_btn);
			wait(2);
			click(institution_name);
			instNameValue = getText(institution_name_value);
			click(select_btn);
			wait(1);//Added
			click(institution_type_dd);
			//enter(institution_type_dd,institution_value_or_text);//Added
			wait(1);//Added
			click(institution_type_value);//changed to entering value
			wait(1);//Added
			click(save_btn);
			wait(3);

			//addedInstname_value = getText(addedInstname_loc);//checking with tablelist
			
			//Checking added education details from tablelist//Added
			educationDetails = driver.findElements(edudetails_forchecking);//Added
			for(WebElement education : educationDetails){//Added
				educationName = education.findElement(By.xpath("./td/div"));//Added
				addedInstname_value = educationName.getText();//Added
				System.out.println(addedInstname_value);
				try {
					if (instNameValue.trim().contentEquals(addedInstname_value.trim())) {//Updated
						System.out.println("Selected Education details added successfully");
						break;//Added
					} else {
						if((educationDetails.size()) == countedEducations) {//Added
							Assert.fail("Selected Education details are not added");
						}//Added
					}
				} catch (Exception e) {
					Assert.fail("Exception occurred", e);
				}
				++countedEducations;//Added
			}//Added
			
			click(finalsave_edit);
			wait(2);
			//viewPageInstname_value = getText(viewPageInstname_loc);//Checking with tablelist
			
			countedEducations = 1;//Added
			educationDetails = driver.findElements(edudetails_viewpage);//Added
			for(WebElement education : educationDetails){//Added
				educationName = education.findElement(By.xpath("./td/div"));//Added
				viewPageInstname_value = educationName.getText();//Added
				try {
					if (instNameValue.trim().contentEquals(viewPageInstname_value.trim())) {//updated
						System.out.println("Details are displayed at Education Section");
						break;//Added
					} else {
						if(educationDetails.size() == countedEducations){//Added
							Assert.fail("Details are not displayed at Education Section");//Updated
						}//Added
					}
				} catch (Exception e) {
					Assert.fail("Exception occurred", e);
				}
				++countedEducations;//Added
			}//Added
		}catch(Exception problem){
			problem.printStackTrace();
		}
		finally{
			appUtil.logout();
		}
	}
}
