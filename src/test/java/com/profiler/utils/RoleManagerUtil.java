package com.profiler.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

public class RoleManagerUtil extends AppUtil 	{


	
	public String createRole()	{
		
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
			if( assignActionsToRole(role_name) )
				return role_name;
			else 
				return null;
		}	
		else
			return null;
	}
	
	
	public boolean deleteRole(String roleName)	{
		
		WebDriverWait waitForRole = new WebDriverWait(driver, 110);
		
		By roles_link 	 = By.linkText("Roles");
		By new_role_link = By.xpath("//li[@id='New']/a//img");
		
		waitForRole.until(ExpectedConditions.elementToBeClickable(roles_link));
		click(roles_link);
		//wait(15);
		
		if(roleName==null)
			return false;
		else 
			extentTest.log(LogStatus.INFO, "Role to remove - " +roleName);
		
		By role_delete_button = By.xpath("//a[text()='" +roleName  +"']/../..//img[@alt='Delete Role']");
		
		waitForRole.until(ExpectedConditions.elementToBeClickable(role_delete_button));
		click(role_delete_button);
		wait(4);
		
		try	{
			driver.switchTo().alert().accept();
		}catch(NoAlertPresentException e)	{
			extentTest.log(LogStatus.INFO, "Delete 1 Role? alert not found.");
		}
		//wait(20);
		waitForRole.until(ExpectedConditions.elementToBeClickable(new_role_link));
		
		if( isElementPresent(driver, new_role_link)  && ! isElementPresent(driver, role_delete_button) )	
			return false;
		else	
			return true;
	}
	

	public boolean assignActionsToRole(String roleName)	{
		
		WebDriverWait waitForActions = new WebDriverWait(driver, 200);
		
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
		
		if( ! isElementPresent(driver, actions_tab))	{
			extentTest.log(LogStatus.INFO, "waiting for actions tab");
			waitForActions.until(ExpectedConditions.visibilityOfElementLocated(actions_tab));
			extentTest.log(LogStatus.INFO, "waiting for actions tab completed");
		}
			
		click(actions_tab);
		waitForActions.until(ExpectedConditions.elementToBeClickable(user_mgt_link));
		
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
		
		/****  	Profile Management section		 ****/
		
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
		
		for(WebElement element : pmgt_chkbxs)	{
			if(element.isEnabled() && !element.isSelected())
			   try	{	
				element.click();
			   } catch (ElementNotVisibleException enve)	{
			   }
		}
		click(prof_mgt); wait(1);
		
		
		/****	  Interaction Management section		 ****/
		
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
		
		for(WebElement element : intr_mgt_chkbxs)	{
			if(element.isEnabled() && !element.isSelected())
			   try {	
				element.click();
			   } catch (ElementNotVisibleException enve)	{
			   }
		}
		
		click(intr_mgt); wait(1);
		
		
		/****	  Organization Management section		 ****/
		
		By org_mgt 		 = By.xpath("//a[contains(text(),'Organization Management')]");
		By org_mgt_defTmpl_sec = By.xpath("//td[starts-with(text(),'Default Template')]/..//img[@id='image1temp11temp_']");
		By org_create_chkbx = By.xpath("//input[@id='selectedItemsCreate_2029']");
		By org_view_chkbx   = By.xpath("//input[@id='selectedItemsView_2032']");
		By org_edit_chkbx   = By.xpath("//input[@id='selectedItemsEdit_2030']");
		By org_del_chkbx	 = By.xpath("//input[@id='selectedItemsDelete_2031']");

		By org_pcedit_chkbx = By.xpath("//input[@id='selectedItemsPictEdit_40014']");
		By org_pcdel_chkbx  = By.xpath("//input[@id='selectedItemsPictDelete_40015']");
		By org_tabs_expand  = By.xpath("//img[@id='image1TAB11tabs_']");

		click(org_mgt); wait(1);
		click(org_mgt_defTmpl_sec);
		
		if(! driver.findElement(org_create_chkbx).isSelected())
			click(org_create_chkbx);
		if(! driver.findElement(org_view_chkbx).isSelected())
			click(org_view_chkbx);
		if(! driver.findElement(org_edit_chkbx).isSelected())
			click(org_edit_chkbx);
		if(! driver.findElement(org_del_chkbx).isSelected())
			click(org_del_chkbx);
		if(! driver.findElement(org_pcedit_chkbx).isSelected())
			click(org_pcedit_chkbx);
		if(! driver.findElement(org_pcdel_chkbx).isSelected())
			click(org_pcdel_chkbx);
				
		click(org_tabs_expand); wait(1);
		
		List<WebElement> org_mgt_inner_tabs = driver.findElements(By.xpath("//tr[@id='1TAB11tabs_']//img[@alt='Expand Tab']"));
		
		for(WebElement element : org_mgt_inner_tabs)	{
			element.click(); wait(1);
		}
		
		
		List<WebElement> org_mgt_chkbxs = driver.findElements(By.xpath("//tr[@id='1TAB11tabs_']//input[@type='checkbox']"));
		
		for(WebElement element : org_mgt_chkbxs)	{
			if(element.isEnabled() && !element.isSelected())
			   try {	
				element.click();
			   } catch (ElementNotVisibleException enve)	{
			   }
		}
	
		click(org_mgt); wait(1);
		
		/****	  Event Management section		 ****/
		
		By evt_mgt 		 = By.xpath("//a[contains(text(),'Event Management')]");
		By evt_mgt_defTmpl_sec = By.xpath("//td[starts-with(text(),'Default Template')]/..//img[@id='image1temp12temp_']");
		By evt_create_chkbx = By.xpath("//input[@id='selectedItemsCreate_2012']");
		By evt_view_chkbx   = By.xpath("//input[@id='selectedItemsView_80001']");
		By evt_edit_chkbx   = By.xpath("//input[@id='selectedItemsEdit_2401']");
		By evt_del_chkbx	 = By.xpath("//input[@id='selectedItemsDelete_2019']");
		By evt_dwnld_chkbx  = By.xpath("//input[@id='selectedItemsDownload_2017']");
		By evt_view_invite  = By.xpath("//input[@id='selectedItemsInvtView_2455']");
		By evt_edit_invite  = By.xpath("//input[@id='selectedItemsInvtEdit_2456']");
		By evt_send_invite  = By.xpath("//input[@id='selectedItemsInvtSend_2457']");
		By evt_section_expand  = By.xpath("//img[@id='image1TAB12tabs_']");

		click(evt_mgt); wait(1);
		click(evt_mgt_defTmpl_sec);
		
		if(! driver.findElement(evt_create_chkbx).isSelected())
			click(evt_create_chkbx);
		if(! driver.findElement(evt_view_chkbx).isSelected())
			click(evt_view_chkbx);
		if(! driver.findElement(evt_edit_chkbx).isSelected())
			click(evt_edit_chkbx);
		if(! driver.findElement(evt_del_chkbx).isSelected())
			click(evt_del_chkbx);
		if(! driver.findElement(evt_dwnld_chkbx).isSelected())
			click(evt_dwnld_chkbx);
		if(! driver.findElement(evt_view_invite).isSelected())
			click(evt_view_invite);
		if(! driver.findElement(evt_edit_invite).isSelected())
			click(evt_edit_invite);
		if(! driver.findElement(evt_send_invite).isSelected())
			click(evt_send_invite);
		
		click(evt_section_expand); wait(1);
		
		List<WebElement> evt_mgt_chkbxs = driver.findElements(By.xpath("//tr[@id='1TAB12tabs_']//input[@type='checkbox']"));
		
		for(WebElement element : evt_mgt_chkbxs)	{
			if(element.isEnabled() && !element.isSelected())
			   try {	
				element.click();
			   } catch (ElementNotVisibleException enve)	{
			   }
		}
	
		click(evt_mgt); wait(1);
		
		
		/****	  Event Participant Management section		 ****/
		
		By evt_parti_mgt 		  = By.xpath("//a[contains(text(),'Event Participant Management')]");
		By evt_parti_mgt_none_sec = By.xpath("//td[starts-with(text(),'None')]/..//img[@id='image1temp13temp_']");
		By evt_parti_mgt_create_chkbx = By.xpath("//input[@id='selectedItemsCreate_90003']");
		By evt_parti_mgt_view_chkbx   = By.xpath("//input[@id='selectedItemsView_90000']");
		By evt_parti_mgt_edit_chkbx   = By.xpath("//input[@id='selectedItemsEdit_90006']");
		By evt_parti_mgt_del_chkbx	  = By.xpath("//input[@id='selectedItemsDelete_90005']");
		By evt_parti_mgt_dwnld_chkbx  = By.xpath("//input[@id='selectedItemsDownload_90004']");
		By evt_parti_mgt_sec_expand   = By.xpath("//img[@id='image1TAB13tabs_']");
		By save_button = By.xpath("//img[@alt='Save']");
		click(evt_parti_mgt); wait(1);
		click(evt_parti_mgt_none_sec);
		
		if(! driver.findElement(evt_parti_mgt_create_chkbx).isSelected())
			click(evt_parti_mgt_create_chkbx);
		if(! driver.findElement(evt_parti_mgt_view_chkbx).isSelected())
			click(evt_parti_mgt_view_chkbx);
		if(! driver.findElement(evt_parti_mgt_edit_chkbx).isSelected())
			click(evt_parti_mgt_edit_chkbx);
		if(! driver.findElement(evt_parti_mgt_del_chkbx).isSelected())
			click(evt_parti_mgt_del_chkbx);
		if(! driver.findElement(evt_parti_mgt_dwnld_chkbx).isSelected())
			click(evt_parti_mgt_dwnld_chkbx);
		
		click(evt_parti_mgt_sec_expand); wait(1);
		
		List<WebElement> evt_parti_mgt_chkbxs = driver.findElements(By.xpath("//tr[@id='1TAB13tabs_']//input[@type='checkbox']"));
		
		for(WebElement element : evt_parti_mgt_chkbxs)	{
			if(element.isEnabled() && !element.isSelected())
			   try {	
				element.click();
			   } catch (ElementNotVisibleException enve)	{
			   }
		}
		
		click(evt_parti_mgt); wait(1);
		
		
		waitForElementtoBeClickable(save_button);
		click(save_button);
		wait(5);
		
		WebDriverWait waitForSave = new WebDriverWait(driver, 140);
		
		if ( waitForSave.until(
			ExpectedConditions.visibilityOfElementLocated(save_button))!=null 
			&&
			waitForSave.until(
			ExpectedConditions.elementToBeClickable(save_button))!=null
			)	{ 
			
			extentTest.log(LogStatus.INFO, "Actions Assigned to Role - " +roleName);
			return true;
		}else 		
			return false;
	}
	
	
	public String createUserGroup()	{
		
		By usrgrps_link 	 	= By.linkText("User Groups");
		By new_usrgrp_link 	= By.xpath("//li[@id='New']/a//img");
		By usrgrp_name_ele 	= By.name("userGroupName");
		By usrgrp_desc	 	= By.xpath("//textarea[@name='description']");
		By submit_button 	= By.xpath("//input[@name='submit']");
		By roles_tab	 	= By.xpath("//a[text()='Roles']");
		String user			= null;
		String usrgrp_name 	= "autUG_" +new SimpleDateFormat("YYMMddhhmmSS").format(new Date()).toString();
		
		WebDriverWait wait = new WebDriverWait(driver,25);
		
		user = createUser();
		driver.switchTo().defaultContent();
		gotoToolsPage();
		wait(3);
		click(usrgrps_link);
		wait(3);
		//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(new_usrgrp_link));
		click(new_usrgrp_link);
		wait(5);
		enter(usrgrp_name_ele, usrgrp_name);
		enter(usrgrp_desc, usrgrp_name);
		click(submit_button);
		wait(4);
		
		if(isElementPresent(driver, roles_tab))	{
			extentTest.log(LogStatus.INFO, "UserGroup Created - " +usrgrp_name);
			
			if(assignRoleToUserGroup(usrgrp_name) && addMembersToUserGroup(user, usrgrp_name) )		{	
				
				extentTest.log(LogStatus.INFO, "Member " +user +" added to group - " +usrgrp_name);
	
				
				/*logout();
				 wait(2);
				String excel_file = "files//TestData.xlsx";
				String old_password     = Excel.readFromExcel(excel_file, "AssignRoleToUG", 10, 1); 
				String new_password 	= Excel.readFromExcel(excel_file, "AssignRoleToUG", 11, 1);
				String prof_record_count	= Excel.readFromExcel(excel_file, "AssignRoleToUG", 7, 1);
				
				login(user, old_password);
				changePasswordForNewUser(user, old_password, new_password);
				verifyUserProfileRecordCount(prof_record_count);
				*/
				return usrgrp_name;
			}
			else return null;
		}	
		else
			return null;
	}
	
	public boolean verifyUserProfileRecordCount(String recordCount)	{
		
		WebDriverWait wait = new WebDriverWait(driver,25);

		By profiles_tab			= By.id("section_profile");
		By result_count			= By.xpath("//span[text()='" +recordCount  +" Results']");
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(profiles_tab));
		click(profiles_tab);
		
		if ( wait.until(ExpectedConditions.
				visibilityOfAllElementsLocatedBy(result_count)) != null )	{
			extentTest.log(LogStatus.INFO, "Results listed : " +recordCount  );
			return true;
		}	
		else	
			return false;
	}
	
	
/*	public boolean changePasswordForNewUser(String user, String oldPassword, String newPassword)	{
		
		By old_password 		= By.linkText("Old Password");
		By new_password 		= By.linkText("New Password");
		By confirm_password 	= By.linkText("Confirm Password");
		
		By change_password_button = By.name("change_password");
		By username_homepage = By.xpath("//*[@id='linksPopupUsermenu']/div/div[contains(text(),'User:')]");
		
		waitForElementtoBeVisible(old_password);
		enter(old_password, oldPassword);
		enter(new_password, newPassword);
		enter(confirm_password, newPassword);
		click(change_password_button);
		wait(5);
		
		if(isElementPresent(driver, username_homepage) )
			return true;
		else		
			return false;
	}
	
	*/
	public boolean assignRoleToUserGroup(String userGroup)	{
		
		
		String excel_file = "files//TestData.xlsx";
		
		String prof_brand_value_str = Excel.readFromExcel(excel_file, "AssignRoleToUG", 1, 1);
		String prof_therp_value_str = Excel.readFromExcel(excel_file, "AssignRoleToUG", 2, 1); 
		String prof_role_01_str = Excel.readFromExcel(excel_file, "AssignRoleToUG", 3, 1); 
		String prof_role_02_str = Excel.readFromExcel(excel_file, "AssignRoleToUG", 4, 1); 
		String prof_role_03_str = Excel.readFromExcel(excel_file, "AssignRoleToUG", 5, 1); 
		String prof_regions_str = Excel.readFromExcel(excel_file, "AssignRoleToUG", 6, 1); 
		String prof_records_str = Excel.readFromExcel(excel_file, "AssignRoleToUG", 7, 1); 
		
		By roles_tab	 	= By.xpath("//a[text()='Roles']");
		By usrgrps_link 	= By.linkText("User Groups");
		By usr_grp			= By.xpath("//a[text()='" +userGroup  +"']");
		By role				= By.xpath("//input[@type='checkbox' and @value='6468']"); //Role - AT_ROLE_FOR_UG_linking_1902220407433
		By save_button		= By.name("submit");
		
		By prof_tmpl_names	= By.xpath("//td//b[contains(text(),'Profile Template Names')]");
		
		By prof_brand_list 	= By.xpath("//div[contains(@id, 'metadata1_0_cTypeMetadataId') and contains(@id,'Profile_dd_label')]");
		By prof_brand_value	= By.xpath("//input[@id='" +prof_brand_value_str  +"' and @type='checkbox' and contains(@name,'Profile')]");
		By prof_therp_list	= By.xpath("//div[contains(@id,'Profile_dd_label') and contains(@caption,'Select Therapeutic Area')]");
		By prof_therp_value = By.xpath("//input[@id='" +prof_therp_value_str  +"' and @type='checkbox' and contains(@name,'Profile')]");
		By prof_role_list 	= By.xpath("//div[contains(@id,'Profile_dd_label') and contains(@caption,'Select Profile Role')]");
		By prof_role_01		= By.xpath("//input[@id='" +prof_role_01_str  +"' and @type='checkbox' and contains(@name,'Profile')]");
		By prof_role_02		= By.xpath("//input[@id='" +prof_role_02_str  +"' and @type='checkbox' and contains(@name,'Profile')]");
		By prof_role_03		= By.xpath("//input[@id='" +prof_role_03_str  +"' and @type='checkbox' and contains(@name,'Profile')]");
		By prof_region_list = By.xpath("//div[contains(@id,'Profile_dd_label') and contains(@caption,'Select Region')]");
		String prof_regions = "//input[@id='" +prof_regions_str  +"' and @type='checkbox' and contains(@name,'Profile')]";
		By prof_update_button = By.xpath("//a/img[@alt='Update' and @id='update1' and contains(@onclick,'Profile')]");
		By prof_records		= By.xpath("//td[text()='Total Record Count:" +prof_records_str +"']");
		
		
		WebDriverWait wait = new WebDriverWait(driver,35);
		Actions actions = new Actions(driver);
		
		driver.switchTo().defaultContent();
		gotoToolsPage();
		click(usrgrps_link);
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(usr_grp));
		click(usr_grp);
		wait(3);
		
		click(roles_tab);
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(role));
		actions.moveToElement(driver.findElement(role));
		wait(1);
		click(role);
		wait(1);
		click(save_button);
		
		wait.until(ExpectedConditions.elementToBeClickable(prof_brand_list));
		
		click(prof_brand_list); wait(1);
		click(prof_brand_value); 
		click(prof_tmpl_names); 
		
		click(prof_therp_list); wait(1);
		click(prof_therp_value); 
		click(prof_tmpl_names); 
		
		click(prof_role_list); wait(1);
		click(prof_role_01); 
		click(prof_role_02); 
		click(prof_role_03); 
		click(prof_tmpl_names); 	
		
		click(prof_region_list); wait(1);
		List<WebElement> regions = driver.findElements(By.xpath(prof_regions));
		for(WebElement ele : regions)
			ele.click();
		regions = null;
		wait(1);
		click(prof_tmpl_names); wait(1);	
		
		click(prof_update_button); 

		wait.until(ExpectedConditions.alertIsPresent());
		try	{
			driver.switchTo().alert().accept();
		}catch(NoAlertPresentException e)	{
			extentTest.log(LogStatus.INFO, "Profiles Data Management.. alert not found.");
		}
		
		if(isElementPresent(driver, prof_records))	{
			click(save_button);
			extentTest.log(LogStatus.INFO, "Roles assigned to UserGroup - " +userGroup);
			return true;
		}
		else
		return false;
	}
	
	public boolean addMembersToUserGroup(String user, String user_group)	{
		
		WebDriverWait wait = new WebDriverWait(driver,25);

		By usrgrps_link 	= By.linkText("User Groups");
		By usr_grp			= By.xpath("//a[text()='" +user_group  +"']");
		
		By members_tab 		= By.linkText("Members");
		By assign_button	= By.name("assigntousergroup");
		By members_frame	= By.name("popup_frame");
		By search_box 		= By.name("searchtemp");
		By go_button		= By.name("go");
		
		By member_chkbox	= By.xpath("//a[text()='" +user  +"@test.com']/parent::*/preceding-sibling::td/input");
		By save_button 		= By.xpath("//img[@alt='Save']");
		By member_activated	= By.xpath("//td[text()='Activated']");
		

		driver.switchTo().defaultContent();
		gotoToolsPage();
		click(usrgrps_link);
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(usr_grp));
		click(usr_grp);
		wait(3);
		
		wait.until(ExpectedConditions.elementToBeClickable(members_tab));
		click(members_tab);
		wait(3);
		click(assign_button);
		
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(members_frame));

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(search_box));
		enter(search_box, user);
		click(go_button);
		
		wait.until(ExpectedConditions.elementToBeClickable(member_chkbox));	
		
		wait(4);
		click(member_chkbox); wait(1);
		
		click(save_button); wait(3); 
		
		driver.switchTo().defaultContent(); wait(1);
		driver.switchTo().frame("toolsContent");
		
		if(isElementPresent(driver, member_activated))	
			return true;
		else		
			return false;
	}
	
	
}
