package com.profiler.scripts;

import com.profiler.utils.Excel;
import com.profiler.utils.RoleManagerUtil;

import com.relevantcodes.extentreports.LogStatus;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RoleManager extends RoleManagerUtil {
	
	String excel_file = null;
	String sheet_name = null;
	String newUser = null;
	String newRole = null;
	String username = null, password = null;
		

	@Test (priority = 1)
	public void testCreateRole()	{
		
		extentTest = extent.startTest("Create Role");
		excel_file = "files//TestData.xlsx";
		sheet_name = "Application";
		
		username = Excel.readFromExcel(excel_file, sheet_name, 2, 1); 
		password = Excel.readFromExcel(excel_file, sheet_name, 3, 1); 
		
		login(username, password);
		gotoToolsPage();
		
		if(  (newRole = createRole()) !=null )	{
			extentTest.log(LogStatus.INFO, "Role Created - " +newRole);
		}	else {
			Assert.fail("Role Creation failed");
			getScreenshot(driver, "testCreateRole_Failure");
		}
		
		wait(2);
		driver.switchTo().defaultContent();
		logout();		
	}
	
	
	@Test (priority = 2)
	public void testDeleteRole()	{
		
		extentTest = extent.startTest("Delete Role");
		excel_file = "files//TestData.xlsx";
		sheet_name = "Application";
		
		username = Excel.readFromExcel(excel_file, sheet_name, 2, 1); 
		password = Excel.readFromExcel(excel_file, sheet_name, 3, 1); 
		
		String role = null;
		
		login(username, password);
		gotoToolsPage();
		
		role = createRole();
		driver.switchTo().defaultContent();
		gotoToolsPage();
		wait(2);
		
		if(role!=null) {
			if( ! deleteRole(role) )	{
				Assert.fail("Role Deletion failed");
				getScreenshot(driver, "testDeleteRole_Failure");
			}
		}	else {
			Assert.fail("Role Creation failed");
			getScreenshot(driver, "testDeleteRole_RoleCreationFailure");
		}
		
		wait(2);
		driver.switchTo().defaultContent();
		logout();
	}
	
	
	@Test (priority = 3)
	public void testCreateUserGroup()	{
		
		extentTest = extent.startTest("Create UserGroup");
		excel_file = "files//TestData.xlsx";
		sheet_name = "Application";
		
		username = Excel.readFromExcel(excel_file, sheet_name, 2, 1); 
		password = Excel.readFromExcel(excel_file, sheet_name, 3, 1);
		String userGroup = null;
		
		login(username, password);
		gotoToolsPage();
		
		if(  (userGroup = createUserGroup()) !=null )	{
			//extentTest.log(LogStatus.INFO, "UserGroup Created - " +userGroup);
		}	else {
			Assert.fail("UserGroup Creation failed");
			getScreenshot(driver, "testCreateUserGroup_Failure");
		}
		
		wait(2);
		driver.switchTo().defaultContent();
		logout();		
	}
	
}
