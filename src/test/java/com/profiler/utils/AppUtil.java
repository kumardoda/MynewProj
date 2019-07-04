package com.profiler.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

public class AppUtil extends Functions{
	
	
	By savesection_button = By.xpath("//a[@title='Save']");
	By description = By.xpath("//input[@id='description']");
	By field_value= By.xpath("//input[@id='fieldValue']");
	String sectionName = null;
	
	String excel_file = null;
	String sheet_name = null;
	
	public boolean login(String username, String password)	{
		
		boolean loginSuccessful = false;
		
		String excel_file = "files//TestData.xlsx";
		String url = Excel.readFromExcel(excel_file, "Application", 1, 1); 
		
		
		By username_field 	= By.xpath("//*[@id='email']");
		By password_field 	= By.xpath("//*[@id='password']");
		By signin_button  	= By.xpath("//*[@id='cd_login_button']"); 
		
		By username_homepage = By.xpath("//*[@id='linksPopupUsermenu']/div/div[contains(text(),'User:')]");
		By change_password	= By.xpath("//input[@name='change_password']");
		//By  close_rating = By.xpath("//*[@id='liveForm']//button");

		
		getUrl(url);
		wait(1);
		driver.findElement(username_field).clear();
		enter(username_field,username);
		wait(2);
		enter(password_field,password);
		wait(2);
		click(signin_button);
		//driver.switchTo().frame("kampyleForm4161");
	//	waitForElementtoBeVisible(close_rating);
	//	click(close_rating);
	//	driver.switchTo().defaultContent();
		if(isElementPresent(driver, username_homepage) || isElementPresent(driver, change_password))
			loginSuccessful = true;
		
		return loginSuccessful;
	}
	
	
	
	public String createProfile(String TplName)	{
		
		String profileName = null;
		/*String newProfile_TplNameToSelect;
		By newProfile_TplToSelect = By.xpath("//a[text()='"+newProfile_TplNameToSelect    +"']");*/
		//boolean profCreated = false;
		int wait_time = 4;
		
		String excel_file = "files//TestData.xlsx";
		String sheet_name = "CreateProfile";
		
		String profile_firstName_val = Excel.readFromExcel(excel_file, sheet_name, 4, 1); 					
		String profile_lastName_val = Excel.readFromExcel(excel_file, sheet_name, 5, 1)
				+ new SimpleDateFormat("YYYYMMddhhmmSS").format(new Date()).toString();
		String profile_email_val = Excel.readFromExcel(excel_file, sheet_name, 6, 1);
		
		By homepage_GlobalCreateNew_list = By.id("popup_create_new");
		
		
		By homepage_GlobalCreateNew_createProfile = By.id("global_create_new_profile");
		By newProfile_popup 	= By.xpath("//div[@id='POPUP_content']//h3[contains(text(), 'New Profile')]");
		By newProfile_firstName = By.name("personFirstName");
		By newProfile_lastName  = By.name("personLastName");
		By newProfile_email 	= By.name("personEmail");
		By newProfile_next_btn  = By.id("profile_next");
		
		By newProfile_pg2_dtls  = By.xpath("//span[contains(text(),'"   +profile_firstName_val   +"')]");
		By newProfile_pg2_next_btn = By.id("profile_lookup_next");
		
		// Page : Profile Template List
		By newProfile_profTplList 	= By.xpath("//div[@id='POPUP_content']//h3[contains(text(), 'Profile Template List')]");

		By newProfile_TplList  		= By.xpath("//input[@class='dropdown dd-all']");
		String newProfile_TplNameToSelect 	= TplName;
		
			if(TplName == "" || TplName == null)
				newProfile_TplNameToSelect= Excel.readFromExcel(excel_file, sheet_name, 7, 1);			
		//By newProfile_TplToSelect = By.xpath("//*[@id='ext-gen1009']//a[text()='" 
		//									+newProfile_TplNameToSelect    +"']"); //changed
			
			By newProfile_TplToSelect = By.xpath("//a[text()='"+newProfile_TplNameToSelect    +"']");
		
		By profile_PersonalInfo_label = By.xpath("//div[@class='formWrapper']/h4");
		
		By newProfile_CtryList	  = By.xpath("//div[@id='countryId_dd_div']//input[@class='dropdown dd-all']");
		
		//By Country_drop = By.xpath("//*[@id='classification19_dd_label']"); 
		//By Country_select =By.xpath("//*[@id='classification19_dd_grid']/li/label");
		
		
		String newProfile_CtryNameToSelect = Excel.readFromExcel(excel_file, sheet_name, 9, 1);
		By newProfile_CtryToSelect = By.xpath("//div[@class='dropdownOpts' and"
			+ " contains(@style, 'display: block') ]/a[text()='" +newProfile_CtryNameToSelect  +"']");
		

		By newProfile_BrandList = By.xpath("//div[contains(text(),'Select Brand')]");
		String newProfile_BrandNameToSelect = Excel.readFromExcel(excel_file, sheet_name, 10, 1);
		By newProfile_BrandToSelect = By.xpath("//ul[@id='classification1_dd_grid']/li//"
					+ "input[@type='checkbox' and  @title='" +newProfile_BrandNameToSelect +"']");
										
		
		By newProfile_RoleList 	   = By.xpath("//div[@id='classification3_dd_label' and contains(@title,'Profile Role')]");
		String newProfile_RoleName = Excel.readFromExcel(excel_file, sheet_name, 11, 1);
		By newProfile_RoleNameToSelect = By.xpath("//ul[@id='classification3_dd_grid']/"
					+ "li[@class='option']/label[text()='" +newProfile_RoleName  +"']");
		
		By newProfile_ProjList = By.xpath("//div[contains(text(),'Select Project')]");
		String newProfile_ProjNameToSelect = Excel.readFromExcel(excel_file, sheet_name, 16, 1);
		By newProfile_ProjToSelect = By.xpath("//ul[@id='classification5_dd_grid']/li//"
					+ "input[@type='checkbox' and @title='" +newProfile_ProjNameToSelect +"']");				
		
		
		By newProfile_RegionList = By.xpath("//div[ @id='classification2_dd_label' and contains(text(),'Select Region')]");
		String newProfile_RegionName = Excel.readFromExcel(excel_file, sheet_name, 12, 1);
		By newProfile_RegionToSelect = By.xpath("//ul[@id='classification2_dd_grid']/li//"
				+ "input[@type='checkbox' and @title='" +newProfile_RegionName +"']");
		

		By newProfile_TherapeuticList = By.xpath("//div[ @id='classification4_dd_label' and contains(text(),'Select Therapeutic Area')]");
		String TherapeuticAreaName = Excel.readFromExcel(excel_file, sheet_name, 13, 1);
		By newProfile_TherapAreaToSelect = By.xpath("//ul[@id='classification4_dd_grid']/"
					+ "li/input[@type='checkbox' and @title='" +TherapeuticAreaName +"']");
		
		By newProf_UserGrpList = By.xpath("//div[@id='userGroup_dd_label' and contains(text(),'Select User Groups')]");
		String UserGroupName = Excel.readFromExcel(excel_file, sheet_name, 14, 1); 
		By newProf_UserGrpToSelect = By.xpath("//ul[@id='userGroup_dd_grid']/"
					+ "li[@class='option']/label[text()='" +UserGroupName +"']");
		

		By newProfile_ProfUserName	= By.xpath("//input[@id='description' and @type='text']");
		String newProfile_ProfUserNameVal 	= Excel.readFromExcel(excel_file, sheet_name, 3, 1).substring(6);
		
		By searchUserContactBtn = By.id("validate_search_user_contact");		
		
		
		String UserContactName = Excel.readFromExcel(excel_file, sheet_name, 15, 1);
		By newProf_UserContactChkBox = By.xpath("//div[@id='searchResults']/"
					+ "table//div[contains(text(),'" +UserContactName +"')]/../../td/span"); 
		By newProf_UserCtTypeList = By.xpath("//div[@id='contactType6444_dd_div']//input[@class='dropdown dd-all']");
		By newProf_UserCtType = By.xpath("//div[@class='dropdownOpts' and contains(@style,'block')]/a[text()='Approver']");
		
		By newProf_lookup_SaveBtn = By.id("profile_lookup_save");
		
		By newProf_verifyProfCreation = By.xpath("//div[contains(text(),'" +profile_firstName_val +" " +profile_lastName_val +"')]");
		
		click(homepage_GlobalCreateNew_list);  wait(1);
		click(homepage_GlobalCreateNew_createProfile); 	wait(3);
			
		if(isElementPresent(driver, newProfile_popup))	{
			enter(newProfile_firstName, profile_firstName_val);
			enter(newProfile_lastName, profile_lastName_val);
			enter(newProfile_email, profile_email_val);
			click(newProfile_next_btn);
			wait(3);
			
			
			if(isElementPresent(driver, newProfile_pg2_dtls))	{
				click(newProfile_pg2_next_btn);
				wait(wait_time);
				
				if(isElementPresent(driver, newProfile_profTplList))	{
					
					click(newProfile_TplList);
					click(newProfile_TplToSelect);
					wait(wait_time);
					
					click(newProfile_CtryList);
					click(newProfile_CtryToSelect);
					click(newProfile_profTplList);
					wait(wait_time);
					
					click(newProfile_BrandList);
					click(newProfile_BrandToSelect);
					click(newProfile_profTplList);wait(1);
					click(profile_PersonalInfo_label);
					wait(1);
					
				/*	click(Country_drop);
					click(Country_select);
					click(newProfile_profTplList);wait(1);
					click(profile_PersonalInfo_label);
					wait(1);*/
					
					click(newProfile_RoleList);  wait(wait_time+2);
					click(newProfile_RoleNameToSelect); wait(wait_time+2);
					click(newProfile_profTplList);wait(1);
					click(profile_PersonalInfo_label);
					wait(wait_time+1);
					
					click(newProfile_ProjList); wait(wait_time);
					click(newProfile_ProjToSelect); wait(wait_time);
					click(newProfile_profTplList);
					click(profile_PersonalInfo_label);
					wait(1);
					
					click(newProfile_RegionList);wait(wait_time);
					click(newProfile_RegionToSelect);wait(wait_time);
					click(newProfile_profTplList);
					click(profile_PersonalInfo_label);
					wait(1);
					
					click(newProfile_TherapeuticList);wait(wait_time);
					click(newProfile_TherapAreaToSelect);wait(wait_time);
					click(newProfile_profTplList);
					click(profile_PersonalInfo_label);
					wait(2);					
					
					if(isElementPresent(driver, newProfile_ProfUserName))  {
						enter(newProfile_ProfUserName, newProfile_ProfUserNameVal);
						click(newProf_UserGrpList);
						click(newProf_UserGrpToSelect);
						click(newProfile_profTplList);
						wait(1);
						
	
						click(searchUserContactBtn);
						wait(3);
						
						click(newProf_UserContactChkBox);
						click(newProf_UserCtTypeList);
						click(newProf_UserCtType);
						click(newProfile_profTplList);
						wait(wait_time);
					}
					
					click(newProf_lookup_SaveBtn);
					wait(wait_time+2);
					
					if(isElementPresent(driver, newProf_verifyProfCreation))	{

						//profCreated = true;	
						profileName = profile_lastName_val +", " +profile_firstName_val;
					}
				}
				else {
					Assert.fail("Profile Template  List page not found");
					getScreenshot(driver, "CreateProfile_ProfileTemplateList");
				}				
				
			}
			else {
				Assert.fail("Profile Details page not found");
				getScreenshot(driver, "CreateProfile_Details");
			}
		}
		else {
			Assert.fail("New Profile page not found");
			getScreenshot(driver, "CreateProfile_NewProfile");
		}
		
		return profileName;
	}
	
	
	public boolean gotoToolsPage()	{
		
		boolean navigationSuccessful = false;
		
		By tools_arrow = By.xpath("//div[@id='navWrapper']/ul/li[@class='moreMenuParent']");		
		By tools_field =By.xpath("//a[contains(text(),'Tools')]");
		By tools_heading = By.className("pagetitle");
		By profiles_tab			= By.id("section_profile");
		int count = 0;
		
		while (navigationSuccessful==false && count < 3) {
			
			count+=1;
			//System.out.println("try count - " +count);
			
			try	{
				driver.navigate().refresh();
				click(profiles_tab);
				wait(5);
				click(tools_arrow);
				wait(2);
	
				click(tools_field);
				driver.switchTo().frame("toolsContent");

				if (isElementPresent(driver, tools_heading))
					navigationSuccessful = true;
			}catch(Exception e)	{
				Assert.fail("Tools page failed to load");
				getScreenshot(driver, "ToolsPageLoading_Failed");
			}
		}
		
		return navigationSuccessful;
	}
	
	
	
	public boolean gotoToolsPage_Original()	{
		
		boolean navigationSuccessful = false;
		
		By tools_arrow = By.xpath("//div[@id='navWrapper']/ul/li[@class='moreMenuParent']");		
		By tools_field =By.xpath("//a[contains(text(),'Tools')]");
		By tools_heading = By.className("pagetitle");
		WebDriverWait wait = new WebDriverWait(driver,40);
		
		driver.navigate().refresh();
		wait(5);
		//wait.until(ExpectedConditions.elementToBeClickable(tools_arrow));
		click(tools_arrow);	
		wait(2);
		
		if(! isElementPresent(driver, tools_field))	{
			click(tools_arrow); wait(2);	
		}
		
		//wait.until(ExpectedConditions.elementToBeClickable(tools_field));
		click(tools_field);	
		driver.switchTo().frame("toolsContent");
		
		if(isElementPresent(driver, tools_heading))
			navigationSuccessful = true;
		
		return navigationSuccessful;
	}
	
	
	
	public String createUser()		{
		
		
		excel_file = "files//TestData.xlsx";
		sheet_name = "CreateUser";

		String username 	= "autusr_" +new SimpleDateFormat("YYMMddhhmmSS").format(new Date()).toString();
		String password 	=Excel.readFromExcel(excel_file, sheet_name, 1, 1); // "Test@123";//
		String  userClient  = Excel.readFromExcel(excel_file, sheet_name, 2, 1); ; // 64; //- UCB - Georgia
		String Country      = Excel.readFromExcel(excel_file, sheet_name, 3, 1);; //"India"
	
		
		
		By users_link 		= By.linkText("Users");
		By new_user_button 	= By.xpath("//li[@id='New']/a//img");
		By username_field 	= By.name("username");
		By password_field 	= By.name("password");
		By password_re_field 	= By.name("password2");
		By user_client_list 	= By.name("UserClient");
		By email_field 	= By.name("email");
		By fname_field 	= By.id("firstName");
		By lname_field 	= By.id("lastName");
		By country_list = By.name("mCountry");
		By save_button  = By.xpath("//input[@alt='Save']");
		By user_created_heading = By.xpath("//span[@class='pagetitle' and contains(text(),'Users: ')]");

		WebDriverWait wait = new WebDriverWait(driver,35);
		
		click(users_link);
		//wait(18);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(new_user_button));
		click(new_user_button);
		wait(5);
		enter(username_field, username);
		enter(password_field, password);
		enter(password_re_field, password);
		
		selectByValue(driver, user_client_list, userClient);
		wait(1);
		
		enter(email_field, username+"@test.com");
		enter(fname_field, "fname");
		enter(lname_field, "lname");
		
		selectByValue(driver, country_list, Country);
		wait(1);
		
		click(save_button);
		wait(10);
		
		if(isElementPresent(driver, user_created_heading))	{
			extentTest.log(LogStatus.INFO, "User created - " +username);
			return username;
		}
		else
			return null;
	}
	
	
/*	public String createRole()	{
		
		By roles_link 	 = By.linkText("Roles");
		By new_role_link = By.xpath("//li[@id='New']/a//img");
		By role_name_ele = By.name("roleName");
		By role_desc	 = By.xpath("//textarea[@id='description']");
		By submit_button = By.xpath("//img[@name='submit']");
		By actions_tab	 = By.xpath("//a[text()='Actions']");
		String role_name = "autrole_" +new SimpleDateFormat("YYMMddhhmmSS").format(new Date()).toString();
		
		click(roles_link);
		wait(9);
		click(new_role_link);
		wait(5);
		enter(role_name_ele, role_name);
		enter(role_desc, role_name);
		click(submit_button);
		wait(2);
		
		if(isElementPresent(driver, actions_tab))	{
			extentTest.log(LogStatus.INFO, "Role Created - " +role_name);
			assignActionsToRole(role_name);
			return role_name;
		}	
		else
			return null;
	}*/
	
	
/*	public boolean deleteRole(String roleName)	{
		
		By roles_link 	 = By.linkText("Roles");
		
		click(roles_link);
		wait(9);
		
		if(roleName==null)
			return false;
		else 
			extentTest.log(LogStatus.INFO, "Role to remove - " +roleName);

		By role_delete_button = By.xpath("//a[text()='" +roleName  +"']/../..//img[@alt='Delete Role']");
		click(role_delete_button);
		
		try	{
			driver.switchTo().alert().accept();
		}catch(NoAlertPresentException e)	{
			extentTest.log(LogStatus.INFO, "Delete 1 Role? alert not found.");
		}
		wait(10);
		
		if(isElementPresent(driver, role_delete_button))	
			return false;
		else	
			return true;
	}*/
	
	
	
/*	public boolean assignActionsToRole(String roleName)	{
		
		By actions_tab	 	= By.xpath("//a[text()='Actions']");
		By user_mgt_link 	= By.xpath("//a[text()='User Management']");
		By cont_mgt_link 	= By.xpath("//a[text()='Content Management']");
		By func_mgt_link 	= By.xpath("//a[text()='Functional Management']");
		By site_mgt_link 	= By.xpath("//a[text()='Site Management']");
		By prflr_mgt_link 	= By.xpath("//a[text()='Profiler Management']");
		By srvy_mgt_link 	= By.xpath("//a[text()='Survey Management']");
		
		By user_mgt_chkbx 	= By.xpath("//a[text()='User Management']/../../../tr[@id='activeheaderssubRow1_']//table//input[@name='1_inactiveAll']");
		By cont_mgt_chkbx 	= By.xpath("//a[text()='Content Management']/../../../tr[@id='activeheaderssubRow2_']//table//input[@name='2_inactiveAll']");
		By func_mgt_chkbx 	= By.xpath("//a[text()='Functional Management']/../../../tr[@id='activeheaderssubRow3_']//table//input[@name='3_inactiveAll']");
		By site_mgt_chkbx 	= By.xpath("//a[text()='Site Management']/../../../tr[@id='activeheaderssubRow4_']//table//input[@name='4_inactiveAll']");
		By prflr_mgt_chkbx 	= By.xpath("//a[text()='Profiler Management']/../../../tr[@id='activeheaderssubRow5_']//table//input[@name='5_inactiveAll']");
		By srvy_mgt_chkbx 	= By.xpath("//a[text()='Survey Management']/../../../tr[@id='activeheaderssubRow8_']//table//input[@name='8_inactiveAll']");
		
		
		click(actions_tab);
		wait(120);
		
		click(user_mgt_link); wait(1);
		click(user_mgt_chkbx);
		click(user_mgt_link);
		
		click(cont_mgt_link); wait(1);
		click(cont_mgt_chkbx);
		click(cont_mgt_link);
		
		click(func_mgt_link); wait(1);
		click(func_mgt_chkbx);
		click(func_mgt_link);
		
		click(site_mgt_link); wait(1);
		click(site_mgt_chkbx);
		click(site_mgt_link);
		
		click(prflr_mgt_link); wait(1);
		click(prflr_mgt_chkbx);
		click(prflr_mgt_link);
		
		click(srvy_mgt_link); wait(1);
		click(srvy_mgt_chkbx);
		click(srvy_mgt_link);
		
		*//****  	Profile Management section		 ****//*
		
		By prof_mgt 		 = By.xpath("//a[contains(text(),'Profile Management')]");
		By prof_mgt_defTmpl_sec = By.xpath("//td[starts-with(text(),'Default Template')]/..//img[@id='image1temp6temp_']");
		By pmgt_create_chkbx = By.xpath("//input[@id='selectedItemsCreate_2013']");
		By pmgt_view_chkbx   = By.xpath("//input[@id='selectedItemsView_60000']");
		By pmgt_edit_chkbx   = By.xpath("//input[@id='selectedItemsEdit_60001']");
		By pmgt_del_chkbx	 = By.xpath("//input[@id='selectedItemsDelete_2016']");
		By pmgt_dwnld_chkbx  = By.xpath("//input[@id='selectedItemsDownload_-1']");
		By pmgt_pcedit_chkbx = By.xpath("//input[@id='selectedItemsPictEdit_2266']");
		By pmgt_pcdel_chkbx  = By.xpath("//input[@id='selectedItemsPictDelete_2267']");
		By pmgt_tabs_expand  = By.xpath("//img[@id='image1TAB6tabs_']");

		click(prof_mgt); wait(1);
		click(prof_mgt_defTmpl_sec);
				
		if(! driver.findElement(pmgt_create_chkbx).isSelected())
			click(pmgt_create_chkbx);
		if(! driver.findElement(pmgt_view_chkbx).isSelected())
			click(pmgt_view_chkbx);
		if(! driver.findElement(pmgt_edit_chkbx).isSelected())
			click(pmgt_edit_chkbx);
		if(! driver.findElement(pmgt_del_chkbx).isSelected())
			click(pmgt_del_chkbx);
		if(! driver.findElement(pmgt_dwnld_chkbx).isSelected())
			click(pmgt_dwnld_chkbx);
		if(! driver.findElement(pmgt_pcedit_chkbx).isSelected())
			click(pmgt_pcedit_chkbx);
		if(! driver.findElement(pmgt_pcdel_chkbx).isSelected())
			click(pmgt_pcdel_chkbx);
		
		click(pmgt_tabs_expand); wait(1);
		
		
		List<WebElement> pmgt_inner_tabs = driver.findElements(By.xpath("//tr[@id='1TAB6tabs_']//img[@alt='Expand Tab']"));
		
		for(WebElement element : pmgt_inner_tabs)	{
			element.click(); wait(1);
		}
		
		List<WebElement> pmgt_chkbxs = driver.findElements(By.xpath("//tr[@id='1TAB6tabs_']//input[@type='checkbox']"));
		System.out.println("pmgt_chkbxs count - " +pmgt_chkbxs.size());
		
		for(WebElement element : pmgt_chkbxs)	{
			if(element.isEnabled() && !element.isSelected())
				element.click();
		}
		click(prof_mgt); wait(1);
		
		*//****	  Interaction Management section		 ****//*
		
		By intr_mgt 		 = By.xpath("//a[contains(text(),'Interaction Management')]");
		By intr_mgt_defTmpl_sec = By.xpath("//td[starts-with(text(),'Default Template')]/..//img[@id='image1temp10temp_']");
		By intr_create_chkbx = By.xpath("//input[@id='selectedItemsCreate_2011']");
		By intr_view_chkbx   = By.xpath("//input[@id='selectedItemsView_20001']");
		By intr_edit_chkbx   = By.xpath("//input[@id='selectedItemsEdit_2402']");
		By intr_del_chkbx	 = By.xpath("//input[@id='selectedItemsDelete_2020']");
		By intr_dwnld_chkbx  = By.xpath("//input[@id='selectedItemsDownload_2018']");
		By intr_sections_expand  = By.xpath("//img[@id='image1TAB10tabs_']");
		
		click(intr_mgt); wait(1);
		click(intr_mgt_defTmpl_sec);
		
		if(! driver.findElement(intr_create_chkbx).isSelected())
			click(intr_create_chkbx);
		if(! driver.findElement(intr_view_chkbx).isSelected())
			click(intr_view_chkbx);
		if(! driver.findElement(intr_edit_chkbx).isSelected())
			click(intr_edit_chkbx);
		if(! driver.findElement(intr_del_chkbx).isSelected())
			click(intr_del_chkbx);
		if(! driver.findElement(intr_dwnld_chkbx).isSelected())
			click(intr_dwnld_chkbx);
		
		click(intr_sections_expand); wait(1);
		
		List<WebElement> intr_mgt_chkbxs = driver.findElements(By.xpath("//tr[@id='1TAB10tabs_']//input[@type='checkbox']")); ////tr[@id='1TAB10tabs_']//input[@type='checkbox']
		System.out.println("Intr_mgt_chkbxs count - " +intr_mgt_chkbxs.size());
		
		for(WebElement element : intr_mgt_chkbxs)	{
			if(element.isEnabled() && !element.isSelected())
			   try {	
				element.click();
			   } catch (ElementNotVisibleException enve)	{
					extentTest.log(LogStatus.INFO, "ElementNotVisibleException thrown- " +enve.toString());
			   }
		}
		
		click(intr_mgt); wait(1);
		
		return false;
	}*/
	
	
	/***
	 * Prerequisite: User navigated to Tools page
	 * This method accepts userId as input, navigates to Users page and searches for given userId 
	 */
	public boolean searchUserFromToolsPage(String userId)	{
				 
		By users_link 		= By.linkText("Users");
		By search_box 		= By.xpath("//input[@type='text' and @id='keyword']");
		By go_button 		= By.name("go");
		By username_link 	= By.xpath("//a[@title='"  +userId  +"']");
		
		
		WebDriverWait wait = new WebDriverWait(driver,40);
			
		click(users_link);
		//wait(15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(search_box));
		
		extentTest.log(LogStatus.INFO, "UserId - " +userId);
		driver.findElement(search_box).clear();
		enter(search_box, userId);
		click(go_button);
		wait(3);
		wait = null;
		
		if( isElementPresent(driver, username_link))	
			return true;
		else
			return false;
	}
	
	
	public void logout()	{
		By homepage_link = By.xpath("//a[@id='utilMenu_user']");
		By logout_link = By.xpath("//a[@title='Logout']");
		
		driver.switchTo().defaultContent(); wait(1);
		click(homepage_link);
		wait(2);
		click(logout_link);
		wait(2);
	}
	
	
	///
	
	

	

	

	
	
	
	
	
	///
	
	
	public boolean dynamic2ColumnSection(String profileName)	{
		
		
		By Profiles_tab=By.xpath("//a[contains(@title,'Profiles')]");
		By Search_profiles=By.xpath("//input[@id='searchall']");
		By Search_button=By.xpath("//span[text()='SEARCH']");
		//By click_on_profile=By.xpath("//a[text()='Profile1, Naresh']");
		By click_on_profile=By.xpath("//a[text()='" +profileName +"']");
		By Userdefined_tab= By.xpath("//a[contains(text(),'AutomationTab')]");
		By actions= By.xpath("//span[@id='actions']");
		By actions_edit=By.xpath("//a[@title='Edit']");
		By Dynamic2_Section_Namefield=By.xpath("//div[contains(@id,\"SectionTwoColumn\")]/input[contains(@name,\"SectionTwoColumn\")]");
		By Dynamic2_Section_Genderfield=By.xpath("//input[@class='dropdown dd-all']");
		By actions_save=By.xpath("//span[text()='Save']");
		By verify_name=By.xpath("//div[@class='block first']//div[contains(@name,'highlightkeyword')]");
		By verify_gender=By.xpath("//div[@class='block']//div[contains(@name,'highlightkeyword')]");
		By dropdown_defaultvalue=By.xpath("//div[@class='dropdownOpts']/a[text()='-- Select Gender --']");
		By clearAll_button = By.id("refine_clear_all");
		
		//Profile and Dynamic2Section Data
		String excel_file = "files//TestData.xlsx";
		String Profile_sheet= "Dynamic2Section";
		//String profilename= Excel.readFromExcel(excel_file, Profile_sheet, 1, 1); 
		String actualname_value = Excel.readFromExcel(excel_file, Profile_sheet, 2, 1);
		String actualgender_value = Excel.readFromExcel(excel_file, Profile_sheet, 3, 1);
		String edit_actualname_value = Excel.readFromExcel(excel_file, Profile_sheet, 4, 1);
		String edit_actualgender_value = Excel.readFromExcel(excel_file, Profile_sheet, 5, 1);
		
		
		// Search Profile
		driver.findElement(Profiles_tab).click();
		
		wait(5);		
		click(clearAll_button);
		wait(2);
		
		driver.findElement(Search_profiles).sendKeys(profileName);
		wait(5);
		driver.findElement(Search_button).click();
		wait(2);
		driver.findElement(click_on_profile).click();
		wait(2);
		
		// Test case 10-Dynamic2 column section
		
		// Adding records for Dynamic2 column section
		
		//Assert.assertTrue(driver.getPageSource().contains("AutomationTestTab "));
		driver.findElement(Userdefined_tab).click();
		driver.findElement(actions).click();
		driver.findElement(actions_edit).click();
		//driver.findElement(Dynamic2_Section_Namefield).clear();
		driver.findElement(Dynamic2_Section_Namefield).sendKeys(actualname_value);
		wait(5);
		driver.findElement(Dynamic2_Section_Genderfield).sendKeys(actualgender_value);
		wait(5);
		driver.findElement(actions_save).click();
		wait(5);
		
		// To verify if the Actual & expected Name are matching or not after adding values
		
		String expectedname_value = driver.findElement(verify_name).getText();
		System.out.println(expectedname_value);
		
		
		try {
			if (actualname_value.equalsIgnoreCase(expectedname_value)) {
				System.out.println("Actual & expected Name matched!");
			} else {
				Assert.fail("Actual & expected Name NOT matched!");
			}
		 } catch (Exception e) {
			Assert.fail("Exception occured", e);
		}
	
		// To verify if the Actual & expected Gender are matching or not after adding values
		
		String expectedgender_value = driver.findElement(verify_gender).getText();
		System.out.println(expectedgender_value);
		try {
			if (actualgender_value.equalsIgnoreCase(expectedgender_value)) {
				System.out.println("Actual & expected Gender matched!");
			} else {
				Assert.fail("Actual & expected Gender NOT matched!");
			}
		 } catch (Exception e) {
			Assert.fail("Exception occured", e);
		}
		
		
	// Editing records for the Dynamic2 Section
	
	driver.findElement(actions).click();
	driver.findElement(actions_edit).click();
	driver.findElement(Dynamic2_Section_Namefield).clear();
	driver.findElement(Dynamic2_Section_Namefield).sendKeys(edit_actualname_value);
	wait(5);
	driver.findElement(Dynamic2_Section_Genderfield).sendKeys(edit_actualgender_value);
	wait(5);
	driver.findElement(actions_save).click();
	wait(5);
	
	// To verify if the edited actual & expected Name are matching or not
	
	String expectededitedname_value = driver.findElement(verify_name).getText();
	System.out.println(expectededitedname_value);
	try {
		if (edit_actualname_value.equalsIgnoreCase(expectededitedname_value)) {
			System.out.println("Name edited successfully");
		} else {
			Assert.fail("Name not edited");
		}
	 } catch (Exception e) {
		Assert.fail("Exception occured", e);
	}
	
	// To verify if the edited actual & expected Gender are matching or not
	
	String expectededitedgender_value = driver.findElement(verify_gender).getText();
	System.out.println(expectededitedgender_value);
	try {
		if (edit_actualgender_value.equalsIgnoreCase(expectededitedgender_value)) {
			System.out.println("Gender edited successfully");
		} else {
			Assert.fail("Gender not edited");
		}
	 } catch (Exception e) {
		Assert.fail("Exception occured", e);
	}
	
	// Deleting records under the Dynamic2 Section
	
	driver.findElement(actions).click();
	driver.findElement(actions_edit).click();
	driver.findElement(Dynamic2_Section_Namefield).clear();
	wait(2);
	WebElement dropdown=driver.findElement(Dynamic2_Section_Genderfield);
	WebElement selectdropdown_defaultvalue=driver.findElement(dropdown_defaultvalue);
	Actions action=new Actions(driver);
	action.moveToElement(dropdown).click().moveToElement(selectdropdown_defaultvalue).click().build().perform();
	wait(10);
	driver.findElement(actions_save).click();
	wait(5);
	
	// To verify if the Name & Gender records got deleted successfully
	
	String Namefieldvalue_afterdelete= driver.findElement(verify_name).getText();
	
	if(Namefieldvalue_afterdelete.trim().isEmpty())
	{		
	  System.out.println("Name field is empty & value deleted successfully");
	}	
	else
	{
		System.out.println("Name field is not empty & value is not deleted");
	}
	
	String Genderfieldvalue_afterdelete= driver.findElement(verify_gender).getText();
	
	if(Genderfieldvalue_afterdelete.trim().isEmpty())
	{		
	  System.out.println("Gender field is empty & value deleted successfully");
	}	
	else
	{
		System.out.println("Gender field is not empty & value is not deleted");
	}
		
		return false;
	}
	
	
}
