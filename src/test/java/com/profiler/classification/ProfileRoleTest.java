package com.profiler.classification;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import com.profiler.utils.AppUtil;
import com.profiler.utils.Excel;
import com.profiler.utils.Functions;

public class ProfileRoleTest extends Functions {
	
	int ZERO = 0;
	int ONE = 1;
	int TWO = 2;
	int THREE = 3;
	int FOUR = 4;
	int FIVE = 5;
	int SIX = 6;
	int SEVEN = 7;
	int EIGHT = 8;
	int NINE = 9;
	int TEN = 10;

	String username = null;
	String password = null;
	String excel_file = "files//TestData.xlsx";
	String sheet_name = "CreateProfileRole";
	String application_sheet = "Application";
	
	By more = By.xpath("(//div[@id='navWrapper']/ul[@id='sectionsNav']/descendant::a[@title='More'])");
	By more_tool = By.xpath("(//div[@id='navWrapper']/ul[@id='sectionsNav']/descendant::a[@title='More']/parent::li/div//descendant::a[@id='section_tools'])");
	By tool_frame = By.xpath("(//*[@id='toolsContent'])");
	By profile_role_link = By.xpath("(//*[@id='container']/table/descendant::div[@id='content']/table/descendant::div[@id='leftDiv']/descendant::a[contains(text(),'Profile Role')])");
	
	String profileroledescription="";
	
	By profile_roles_text = By.xpath("(//*[@id='highlightkeyword'][contains(text(),'"+profileroledescription+"')])");
	
	By profile_role_exists_close = By.xpath("(//*[@id='resultDiv']/div[@id='messageDiv']/descendant::a[@title='close']["+ONE+"])");
	
	By profile_role_new_box_input = By.xpath("(//*[@id='profilerPopupDiv']/descendant::input[@id='description'])");
	
	By profile_role_new_box_save = By.xpath("(//*[@id='profilerPopupDiv']/div[@id='profilerPopupMenu']/table/descendant::a/img[@alt='Save'])");
	
	By tools_new_profile_role_link = By.xpath("(//*[@id='menuTable']/descendant::a[starts-with(@href,'javascript:openProfilerPopup')])");
	
	By profile_role_exists_description = By.xpath("(//*[@id='messageDiv']/descendant::div[contains(text(),'Entity with this Description already exists')])");
	
	By tools_profile_roles_select = By.xpath("(//select[@id='jumpTo'])");
	
	By segmentation_results = By.xpath("(//*[@id='homePageRisingStars']/descendant::span[contains(text(),'TOP SEGMENTATION RESULTS')])");
	
	By tools_profile_roles_forward_link = By.xpath("(//img[@name='forward'])");
	
	By tools_profile_roles_text_delete = By.xpath("(//*[@id='highlightkeyword'][contains(text(),'"+ profileroledescription +"')]/parent::td/preceding-sibling::td/parent::tr//*[self::td]["+FIVE+"]/a["+ONE+"])");
	
	String deleteprofileroletext = "Delete 1 Profile Role?";
	
	String processingmessagetext = "Your request is in queue. For more details check";// \"Profile Data Management->Realtime Mass Update tracker\" screen";
	
	By tools_profile_roles_text_edit = By.xpath("(//*[@id='highlightkeyword'][contains(text(),'"+ profileroledescription +"')]/parent::td/preceding-sibling::td/parent::tr//*[self::td]["+FIVE+"]/a["+TWO+"])");
	
	By tools_profile_roles_text_checkbox = By.xpath("(//*[@id='highlightkeyword'][contains(text(),'"+profileroledescription+"')]/parent::td/preceding-sibling::td/descendant::input[@type='checkbox'])");
	
	By tools_delete_profile_role_link = By.xpath("(//*[@id='menuTable']/descendant::a[starts-with(@href,'javascript:deleteAction')])");
	
	String deletemultipleprofileroletext = "Delete 3 Profile Roles?";

	By tools_profile_roles_page_select = By.xpath("(//select[@id='bbox'])");
	
	String navigatetotools = "action=newFrameset&sourceTab=tools&siteArea=admin&isToolsNavigation=true";
	
	AppUtil appUtil = new AppUtil();//Added
	String editprofileroledescription = "";
	
	@Test(priority = 1)
	public void testCreateProfileRole(){
		
		try {
			extentTest = extent.startTest("testCreateProfileRole ");
			//sheet_name = "CreateProfileRole";

			if(username == null){
				username = getUserName();
				password = getUserPassword();
			}
			
			TargetLocator window = driver.switchTo();
			String browserWindow = driver.getWindowHandle();
			driver = window.window(browserWindow);
			
			
			appUtil.login(username, password);//Added 
			if(isElementPresent(driver, more_tool)){// changed
				
				logInfoMessageInExtentTest("testCreateProfileRole() - User login SUCCESSFUL ");
				driver = driver.switchTo().window(driver.getWindowHandle());
				
				wait(ONE);
				
				driver.switchTo().defaultContent();
				navigateToProfileRoles();
				
				//read profile role description from test data
				profileroledescription = Excel.readFromExcel(excel_file, sheet_name, ONE, ONE);
				
				boolean noerror = profileRoleCreating(profileroledescription);
				if(noerror == true){
					if(isElementPresent(driver, profile_role_exists_description)){
						
						String details = "profile role already exists, give another profile role description ";
						//displayOnConsole("testCreateProfileRole() -  "+details);
						//String screenshot = getScreenshot(driver, "Profile_Role_Exists");
						Actions actions = new Actions(driver);
						Action action = actions.moveToElement(driver.findElement(profile_role_exists_close),TWO,ZERO).click().build();
						action.perform();
						
						//Checking before creating
						boolean lookup = lookupProfileRoleDescription();//Added
						logInfoMessageInExtentTest("Checking Profile Role before re-creating ");//Added
						if(lookup){//Added
							boolean deleting = profileRoleDeleting();
							if(deleting == true){
								logInfoMessageInExtentTest("Profile Role before re-creating deleted ");
							}
						}//Added
						noerror = profileRoleCreating(profileroledescription);
						logInfoMessageInExtentTest(details);
						lookupProfileRoleDescription();
						
					}else{
						
						boolean profilerole = lookupProfileRoleDescription();
						if(profilerole){
							
							//displayOnConsole("Added new profile role ");
							logInfoMessageInExtentTest("Added new profile role description ");
						}
						
						logPassMessageInExtentTest("testCreateProfileRole() - Creating of new profile role description SUCCESSFUL ");
					}
				}else{
					
					logFailMessageInExtentTest("testCreateProfileRole() - Creating of new profile role description UNSUCCESSFUL ");
				}
				
				driver = driver.switchTo().defaultContent();
				logInfoMessageInExtentTest("testCreateProfileRole() - user logout started ");
				appUtil.logout();
				
			} else {
				
				//displayOnConsole("testCreateProfileRole() - User login UNSUCCESSFUL ");
				logFailMessageInExtentTest("testCreateProfileRole() - User login UNSUCCESSFUL, creating of new profile role description UNSUCCESSFUL ");//Changed, method call changed
			}
		} catch (Exception problem) {
			logFailMessageInExtentTest("testCreateProfileRole() - "+problem.fillInStackTrace());
		}
	}

	boolean lookupProfileRoleDescription() {
		
		boolean profilerole = false;
		
		String filter = String.valueOf(profileroledescription.charAt(ZERO)).toUpperCase();
		
		boolean selectvalue = selectByValue(driver, tools_profile_roles_select, filter);
		wait(TWO);
		
		profileroledescription = profileroledescription.trim();
		profile_roles_text = By.xpath("//*[@id='highlightkeyword'][contains(text(),'"+profileroledescription+"')]");
		
	
		filter = String.valueOf(ZERO);
		
		List<WebElement> navigationoptions = null;
		try
		{
			navigationoptions = driver.findElements(By.xpath("//select[@id='bbox']//*[self::option]"));
		}catch(Exception problem){
			logFailMessageInExtentTest("problem  " + problem.getMessage());
		}
		
		for(int pages = TWO; pages <= navigationoptions.size(); pages++){
			if((profilerole = isElementPresent(driver, profile_roles_text)) == true){
				break;
			}else{
				click(By.xpath("//table[@id='cu']/thead/tr["+ONE+"]/td["+TWO+"]/a"));
				profilerole = isElementPresent(driver, profile_roles_text);
				if(profilerole == true)
					break;
			}
			filter = String.valueOf(pages);
			selectvalue = selectByValue(driver, tools_profile_roles_page_select, filter);
			wait(ONE);
		}
		//displayOnConsole("elements "+navigationoptions.size());
		if(navigationoptions.size() == ONE)
		{
			profilerole = isElementPresent(driver, profile_roles_text);
		}
		
		return profilerole;
	}

	void loginUnsuccesful() {
		extentTest.log(LogStatus.FAIL,"User login UNSUCCESSFUL ");
		Assert.fail("User login UNSUCCESSFUL ");//Added
	}

	void logFailMessageInExtentTest(String details) {
		try {
			if(isElementPresent(driver, By.xpath("//div[@id='loginBox']/preceding-sibling::div"))){//Added to check session 
				By locator = By.xpath("//div[@id='loginBox']/preceding-sibling::div");
				String storetext = getText(locator);
				details = details + " " +storetext + " ";
			}
			logoutChecking();
		} catch (Exception problem) {
			problem.printStackTrace();
		}
		extentTest.log(LogStatus.FAIL, details);
		Assert.fail(details);//Added
	}

	void logPassMessageInExtentTest(String details) {
		extentTest.log(LogStatus.PASS, details);
	}

	void logInfoMessageInExtentTest(String details) {
		extentTest.log(LogStatus.INFO, details+ " , continuing with processing. ");
	}

	void displayOnConsole(String consoleMessage) {
		System.out.println(consoleMessage);
	}

	String getUserPassword() {
		
		String password = Excel.readFromExcel(excel_file, application_sheet, THREE, ONE);
		return password;
	}

	String getUserName() {
		String user = Excel.readFromExcel(excel_file, application_sheet, TWO, ONE);
		return user;
	}

	boolean profileRoleCreating(String profileroledescription) {
		boolean noerror = true;

		try {
			
			//click new profile role
			click(tools_new_profile_role_link);
			wait(ONE);
			
			click(profile_role_new_box_save);
			wait(TWO);
			try {
				profileRoleCreatingAlertsProcessing();
			} catch (Exception problem) {
				logInfoMessageInExtentTest("Problem: " +problem.toString() + " " + problem.getMessage());
			}
			wait(ONE);
			
			//enter profile role description
			click(profile_role_new_box_input);
			driver.findElement(profile_role_new_box_input).clear();
			enter(profile_role_new_box_input, profileroledescription);
			
			//click save
			click(profile_role_new_box_save);
			wait(TWO);
			
		} catch (Exception problem) {
			noerror=false;
			logInfoMessageInExtentTest("Problem "+ problem.fillInStackTrace());//Changed to info
		}
		
		return noerror;
	}

	private void profileRoleCreatingAlertsProcessing() throws Exception{
		
		try {
			Alert alertbox = driver.switchTo().alert();
			String alerttext = alertbox.getText();
			boolean textalert = alerttext.contains("Profile Role is required field. Please enter a value for Profile Role.");
			alertbox.accept();
		} catch (Exception problem) {
			throw new Exception(problem);
		}
		
	}

	void navigateToProfileRoles() {
		//click for more tools
		//waitForElementtoBeVisible(segmentation_results);
		//clickMoreTool();
		
		
		
		//displayOnConsole("Adding new profile role ");
		
		try {
			//Above actions were temporarily not working correctly , so using below workaround to navigate
			//driver.navigate().to(driver.getCurrentUrl() + navigatetotools);//changed
			
			//go to internal html			
			AppUtil apputil = new AppUtil();//Added
			boolean tools_page_shown = apputil.gotoToolsPage();//Added
			if(tools_page_shown == false){//Added
		
				logFailMessageInExtentTest("Problem ToolsPageLoading_Failed");//Changed
			}
			
			wait(ONE);

			//click profile role link
			click(profile_role_link);
			//wait
			wait(ONE);
		} catch (Exception problem) {
			logFailMessageInExtentTest("Problem "+ problem.fillInStackTrace());
		}
	}
	
	@Test(priority = 3)
	public void testDeleteProfileRole(){
		
		try {
			//starting of test

			extentTest = extent.startTest("testDeleteProfileRole ");
			//delete profile role test data
			//sheet_name = "CreateProfileRole";
			
			if(username == null){
				username = getUserName();
				password = getUserPassword();
			}
			
			driver = driver.switchTo().window(driver.getWindowHandle());

			appUtil.login(username, password);//Added 
			if(isElementPresent(driver, more_tool)){// changed

				//displayOnConsole("testDeleteProfileRole() - User login SUCCESSFUL ");
				
				wait(THREE);//Added
				driver = driver.switchTo().defaultContent();
				driver.switchTo().activeElement();//Added
				navigateToProfileRoles();
				
				//read profile role description from test data
				profileroledescription = Excel.readFromExcel(excel_file, sheet_name, TWO, ONE);
				
				boolean noerror = profileRoleCreating(profileroledescription);

				if(noerror == true){

					if(isElementPresent(driver, profile_role_exists_description)){

						String details = "profile role already exists, deleting existing profile role description ";
						//displayOnConsole("testDeleteProfileRole() -  "+details);
						//String screenshot = getScreenshot(driver, "Profile_Role_Exists");
						Actions actions = new Actions(driver);
						Action action = actions.moveToElement(driver.findElement(profile_role_exists_close),TWO,ZERO).click().build();
						action.perform();
						
						//Checking before creating
						boolean lookup = lookupProfileRoleDescription();//Added
						logInfoMessageInExtentTest("Checking Profile Role before re-creating ");//Added
						if(lookup){//Added
							boolean deleting = profileRoleDeleting();
							if(deleting == true){
								logInfoMessageInExtentTest("Profile Role before re-creating deleted ");
							}
						}//Added
						noerror = profileRoleCreating(profileroledescription);
						logInfoMessageInExtentTest(details);

					}else{
						
						logPassMessageInExtentTest("testDeleteProfileRole() - Creating of new profile role description for deleting SUCCESSFUL ");

					}
					
					boolean lookupProfile = lookupProfileRoleDescription();
					
					if(lookupProfile){

						boolean deletingprofilerole = profileRoleDeleting();
						if(deletingprofilerole){
							boolean exception = false;
							try {
								driver.switchTo().defaultContent();
							} catch (Exception problem) {
								problem.printStackTrace();
								exception = true;
							}
								if(exception == true){
									driver.navigate().refresh();
									wait(ONE);
									driver.switchTo().frame(driver.findElement(tool_frame));
									//click profile role link
									click(profile_role_link);
									//wait
									wait(ONE);
								}else{
									wait(ONE);
									driver.switchTo().frame(driver.findElement(tool_frame));
								}
								lookupProfile =  lookupProfileRoleDescription();
								

							if(lookupProfile == false){
								logPassMessageInExtentTest("testDeleteProfileRole() - delete of profile role description SUCCESSFUL ");
							}
							else{
								logFailMessageInExtentTest("testDeleteProfileRole() - delete of profile role description UNSUCCESSFUL ");
							}
							
						}else{
							logFailMessageInExtentTest("testDeleteProfileRole - delete of profile role description UNSUCCESSFUL ");
						}
					}
					
				}else{
					
					logFailMessageInExtentTest("testDeleteProfileRole() - Creating of new profile role description for deleting UNSUCCESSFUL , delete of profile role description UNSUCCESSFUL ");
					
				}
				driver = driver.switchTo().defaultContent();
				logInfoMessageInExtentTest("testDeleteProfileRole() - user logout started "); 
				appUtil.logout();
			}else{
				
				//displayOnConsole("testDeleteProfileRole() - User login UNSUCCESSFUL ");
				extentTest.log(LogStatus.FAIL,"testDeleteProfileRole() - User login UNSUCCESSFUL, delete of profile role description UNSUCCESSFUL ");
			
			}
			
		} catch (Exception problem) {
			logFailMessageInExtentTest("testDeleteProfileRole() - "+problem.fillInStackTrace());
		}
		

	}

	boolean profileRoleDeleting() {
		
		boolean deleteprofilerole = true;
		
		try {
			
			//delete of profile role description
			profileroledescription = profileroledescription.trim();
			tools_profile_roles_text_delete = By.xpath("(//table[@id='cu']/descendant::div[@id='highlightkeyword'][contains(text(),'"+ profileroledescription +"')]/parent::td/preceding-sibling::td/parent::tr/td["+FIVE+"]/a["+ONE+"]/img)");
			tools_profile_roles_text_checkbox = By.xpath("(//table[@id='cu']/descendant::div[@id='highlightkeyword'][contains(text(),'"+ profileroledescription +"')]/parent::td/preceding-sibling::td/descendant::input[@type='checkbox'])");
			click(tools_profile_roles_text_checkbox);
			wait(ONE);
			click(tools_profile_roles_text_delete);
			wait(ONE);
			
			//accept delete profile role description alerts
			Alert alertbox = driver.switchTo().alert();
			String alertmessagetext = alertbox.getText();
			boolean textcontent = alertmessagetext.contains(deleteprofileroletext);
			if(textcontent){
				logInfoMessageInExtentTest(deleteprofileroletext + " message shown ");
			}
			
			//cancel delete
			alertbox.dismiss();
			wait(ONE);
			
			//again select profile role row for delete
			click(tools_profile_roles_text_checkbox);
			wait(ONE);
			click(tools_profile_roles_text_delete);
			wait(ONE);
			
			try {
				profileRoleDeletingAlertProcessing(alertbox);
			} catch (Exception problem) {
				logInfoMessageInExtentTest("Problem: " +problem.toString() + " "+problem.getMessage());
			}
			
		} catch (Exception problem) {
			deleteprofilerole = false;
			logInfoMessageInExtentTest("Problem: "+ problem.toString() + " " + problem.getMessage() );//Changed to info
		}
		
		return deleteprofilerole;
		
	}

	private void profileRoleDeletingAlertProcessing(Alert alertbox) throws Exception{
		String alertmessagetext;
		try {
			alertbox.accept();
			wait(TWO);
			alertbox.sendKeys(Keys.chord(Keys.ENTER));
			//Alert alertboxtwo = driver.switchTo().alert();
			//alertmessagetext = alertboxtwo.getText();
			//alertbox.sendKeys(Keys.chord(Keys.ENTER));
			wait(TWO);
		} catch (Exception errorOccured) {
			throw new Exception(errorOccured);
		} 
	}
	
	@Test(priority = 2)
	public void testEditProfileRole(){
		
		try {
			//start of test
			extentTest = extent.startTest("testEditProfileRole ");
			
			//edit profile role test data, already define above
			//sheet_name = "CreateProfileRole";
			
			//application username, password
			if(username == null){
				
				username = getUserName();
				password = getUserPassword();
				
			}
			driver = driver.switchTo().window(driver.getWindowHandle());
			
			appUtil.login(username, password);//Added 
			if(isElementPresent(driver, more_tool)){// changed
				
				//displayOnConsole("testEditProfileRole() - user login SUCCESSFUL ");
				
				navigateToProfileRoles();
				
				//read profile role description from test data
				profileroledescription = Excel.readFromExcel(excel_file, sheet_name, THREE, ONE);
				
				boolean noerror = profileRoleCreating(profileroledescription);
				
				if(noerror == true){
					
					if(isElementPresent(driver, profile_role_exists_description)){
						
						String details = "profile role already exists, editing existing profile roledescription ";
						//displayOnConsole("testProfileRoleEditing - "+details);
						//String screenshot = getScreenshot(driver, "Profile_Role_Exists");
						Actions actions = new Actions(driver);
						Action action = actions.moveToElement(driver.findElement(profile_role_exists_close),TWO,ZERO).click().build();
						action.perform();
						
						//Checking before creating
						boolean lookup = lookupProfileRoleDescription();//Added
						logInfoMessageInExtentTest("Checking Profile Role before re-creating ");//Added
						if(lookup){//Added
							boolean deleting = profileRoleDeleting();
							if(deleting == true){
								logInfoMessageInExtentTest("Profile Role before re-creating deleted ");
							}
						}//Added
						noerror = profileRoleCreating(profileroledescription);
						
						logInfoMessageInExtentTest("testEditProfileRole() - " + details);
						
					} else {
						
						 logInfoMessageInExtentTest("testEditProfileRole() - Creating of new profile role description for editing SUCCESSFUL ");
						 
					}
					
					boolean lookupProfile = lookupProfileRoleDescription();
					
					if(lookupProfile){

						boolean profileroleedit = profileRoleEditing();
						
						if(profileroleedit){
							
							lookupProfile = lookupProfileRoleDescription();
							if(lookupProfile == true){
								logPassMessageInExtentTest("testEditProfileRole() - editing of profile role description SUCCESSFUL ");
							}
							else{
								logFailMessageInExtentTest("testEditProfileRole() - editing of profile role description UNSUCCESSFUL ");
							}
							
						}else{
							String details = "profile role already exists, give another profile role description ";
							//displayOnConsole("testCreateProfileRole() -  "+details);
							//String screenshot = getScreenshot(driver, "Profile_Role_Exists");
							Actions actions = new Actions(driver);
							Action action = actions.moveToElement(driver.findElement(profile_role_exists_close),TWO,ZERO).click().build();
							action.perform();
							logInfoMessageInExtentTest(details);
							//Checking before editing
							profileroledescription = editprofileroledescription;
							boolean lookup = lookupProfileRoleDescription();//Added
							logInfoMessageInExtentTest("Checking Profile Role before re-editing ");//Added
							if(lookup){//Added
								boolean deleting = profileRoleDeleting();
								if(deleting == true){
									logInfoMessageInExtentTest("Profile Role before re-editing deleted ");
								}
							}//Added
							noerror = profileRoleEditing();
						}
					}
					
				} else {
					logFailMessageInExtentTest("testEditProfileRole() - Creating of new profile role description for editing UNSUCCESSFUL ,editing of profile role description UNSUCCESSFUL ");//Changed
				}
				driver = driver.switchTo().defaultContent();
				logInfoMessageInExtentTest("testEditProfileRole() - user logout started ");
				appUtil.logout();
			}else{
				
				displayOnConsole("testEditProfileRole() - user login UNSUCCESSFUL ");
				extentTest.log(LogStatus.FAIL,"testEditProfileRole() - User login UNSUCCESSFUL, editing of profile role description UNSUCCESSFUL ");
			}
		} catch (Exception problem) {
			logFailMessageInExtentTest("testEditProfileRole() - "+problem.fillInStackTrace());
		}
	}
	
	boolean profileRoleEditing(){
		
		boolean profileroleediting = true;
		
		try {
			
			//read profile role description from test data
			editprofileroledescription = Excel.readFromExcel(excel_file, sheet_name, FOUR, ONE);

			//edit profile role description
			//existing profile role description
			profileroledescription = profileroledescription.trim();
			tools_profile_roles_text_edit = By.xpath("(//table[@id='cu']/descendant::div[@id='highlightkeyword'][contains(text(),'"+ profileroledescription +"')]/parent::td/preceding-sibling::td/parent::tr//*[self::td]["+FIVE+"]/a["+TWO+"]/img)");
			click(tools_profile_roles_text_edit);
			wait(ONE);
			
			//enter profile role description
			click(profile_role_new_box_input);
			driver.findElement(profile_role_new_box_input).clear();
			enter(profile_role_new_box_input, editprofileroledescription);
			
			//click save
			click(profile_role_new_box_save);
			wait(ONE);
			
			try {
				profileRoleEditingAlertProcessing();
			} catch (Exception problem) {
				logInfoMessageInExtentTest("Problem: "+problem.toString() + " " + problem.getMessage());
			}
			
			profileroledescription = editprofileroledescription.trim();
			
		} catch (Exception problem) {

			profileroleediting = false;
			logInfoMessageInExtentTest("Problem " + problem.fillInStackTrace());//Changed
			
		}
		
		return profileroleediting;
	}

	private void profileRoleEditingAlertProcessing() throws Exception{
		try {
			//accept edit profile role description alerts
			Alert alertbox = driver.switchTo().alert();
			String alertmessagetext = alertbox.getText();
			alertbox.accept();
			wait(TWO);
		} catch (Exception problem) {
			throw new Exception(problem);
		}
	}
	
	@Test(priority = 4)
	public void testMultipleProfileRolesDelete()
	{
		try {
			//start of test
			extentTest = extent.startTest("testMultipleProfileRolesDelete ");
			
			//multiple profile role test data
			//sheet_name = "ProfileRoleTest";
			
			//application username, password
			if(username == null)
			{
				username = getUserName();
				password = getUserPassword();
			}
			driver = driver.switchTo().window(driver.getWindowHandle());
			appUtil.login(username, password);//Added 
			if(isElementPresent(driver, more_tool)){// changed
				
				//click for more tools
				//waitForElementtoBeVisible(segmentation_results);
				//clickMoreTool();
				
				//Above actions were temporarily not working correctly, so using below workaround to navigate
				//driver.navigate().to(driver.getCurrentUrl()+navigatetotools);
				
				wait(TWO);
				
				//go to internal html
				//driver = driver.switchTo().frame(driver.findElement(tool_frame));
				
				//Added
				driver.switchTo().defaultContent();
				navigateToProfileRoles();
				
				//click(profile_role_link);
				
				String profileroleone = checkingProfileCreate(FIVE,ONE);
				String profileroletwo = checkingProfileCreate(SIX,ONE);
				String profilerolethree = checkingProfileCreate(SEVEN,ONE);
				
				String filter = String.valueOf(profileroleone.charAt(ZERO)).toUpperCase();
				
				boolean selectvalue = selectByValue(driver, tools_profile_roles_select, filter);
				wait(TWO);
				
				profileroleone = profileroleone.trim();
				profileroletwo = profileroletwo.trim();
				profilerolethree = profilerolethree.trim();
				
				//added
				click(tools_delete_profile_role_link);
				Alert alertbox = driver.switchTo().alert();
				String alertmessagetext = alertbox.getText();
				boolean textcontent = alertmessagetext.contains("No Profile Roles are selected. Please select Profile Roles to delete.");
				alertbox.accept();
				wait(ONE);

				checkProfileRoleRow(profileroleone);
				checkProfileRoleRow(profileroletwo);
				checkProfileRoleRow(profilerolethree);
				
				//delete selected multiple profile roles
				click(tools_delete_profile_role_link);
				
				//accept delete profile role description alerts
				alertbox = driver.switchTo().alert();
				alertmessagetext = alertbox.getText();
				textcontent = alertmessagetext.contains(deletemultipleprofileroletext);
				if(textcontent){
					logInfoMessageInExtentTest(deletemultipleprofileroletext + " message shown ");
				}
				alertbox.accept();
				wait(TWO);
				
				try {
					alertbox = driver.switchTo().alert();
					alertmessagetext = alertbox.getText();
					textcontent = alertmessagetext.contains(processingmessagetext);
					if(textcontent){
						logInfoMessageInExtentTest(processingmessagetext + " message shown ");
					}
					alertbox.accept();
				} catch (Exception problem) {
					problem.printStackTrace();
				}
				boolean exception = false;
				try {
					driver.switchTo().defaultContent();
				} catch (Exception problem) {
					problem.printStackTrace();
					exception = true;
				}
					if(exception == true){
						driver.navigate().refresh();
						wait(ONE);
						driver.switchTo().frame(driver.findElement(tool_frame));
						//click profile role link
						click(profile_role_link);
						//wait
						wait(ONE);
					}else{
						wait(ONE);
						driver.switchTo().frame(driver.findElement(tool_frame));
					}
				wait(TWO);
				
				logInfoMessageInExtentTest("testMultipleProfileRolesDelete() - deleted multiple profile roles ");
				
				driver = driver.switchTo().defaultContent();
				logInfoMessageInExtentTest("testMultipleProfileRolesDelete() - user logout started ");
				appUtil.logout();
			}else{
				
				//displayOnConsole("testMultipleProfileRolesDelete() - User login UNSUCCESSFUL ");
				extentTest.log(LogStatus.FAIL,"testMultipleProfileRolesDelete() - User login UNSUCCESSFUL, delete of profile role description UNSUCCESSFUL ");
			}
		} catch (Exception problem) {
			logFailMessageInExtentTest("testMultipleProfileRolesDelete() - "+problem.fillInStackTrace());			
		}
	}

	void checkProfileRoleRow(String profileroledescription) {
		boolean profilerole = false;
		try {
			profile_roles_text = By.xpath("//*[@id='highlightkeyword'][contains(text(),'"+profileroledescription+"')]");
				
			String filter = String.valueOf(ZERO);
			List<WebElement> navigationoptions = driver.findElements(By.xpath("//select[@id='bbox']//*[self::option]"));
			
			for(int pages = TWO; pages <= navigationoptions.size(); pages++){
				if((profilerole = isElementPresent(driver, profile_roles_text)) == true){
					
					break;
					
				}else{
					
					click(By.xpath("//table[@id='cu']/thead/tr["+ONE+"]/td["+TWO+"]/a"));
					profilerole = isElementPresent(driver, profile_roles_text);
					if(profilerole == true)
						break;
					
				}
				
				filter = String.valueOf(pages);
				boolean selectvalue = selectByValue(driver, tools_profile_roles_page_select, filter);
				wait(ONE);
			}
			
			//displayOnConsole("elements "+navigationoptions.size());
			if(navigationoptions.size() == ONE)
			{
				profilerole = isElementPresent(driver, profile_roles_text);
				
			}
			
			if(profilerole == true){
				tools_profile_roles_text_checkbox = By.xpath("//*[@id='highlightkeyword'][contains(text(),'"+profileroledescription+"')]/parent::td/preceding-sibling::td/descendant::input[@type='checkbox']");
				click(tools_profile_roles_text_checkbox);
				wait(ONE);
			}
		} catch (Exception problem) {
			logFailMessageInExtentTest(problem.fillInStackTrace()+" ");
		}
	}

	String checkingProfileCreate(int datarow, int datacolumn) {
		
		String []noerror = profileRoleCreating(datarow,datacolumn);
		
		try {
			if(noerror[ZERO].contains("true")){
				
				if(isElementPresent(driver, profile_role_exists_description)){

					String details = "profile role already exists, deleting existing profile role description ";
					//displayOnConsole("testDeleteMultipleProfileRoles() -  "+details);
					//String screenshot = getScreenshot(driver, "Profile_Role_Exists");
					Actions actions = new Actions(driver);
					Action action = actions.moveToElement(driver.findElement(profile_role_exists_close),TWO,ZERO).click().build();
					action.perform();
					logInfoMessageInExtentTest("testMultipleProfileRolesDelete() - "+details);
									
				}else{
					
					logPassMessageInExtentTest("testMultipleProfileRolesDelete() - Creating of new profile role description for deleting SUCCESSFUL ");

				}

			}else{
				logFailMessageInExtentTest("testMultipleProfileRolesDelete() - Creating of new profile role description for deleting UNSUCCESSFUL ");
			}
		} catch (Exception problem) {
			logFailMessageInExtentTest("testMultipleProfileRolesDelete() - " + problem.fillInStackTrace());
		}
		
		return noerror[ONE];
	}

	String[] profileRoleCreating(int datarow, int datacolumn) {
		String noerror[] = {"true",""};
	
		try {

			//displayOnConsole("Adding new profile role ");
			
			//click profile role link

			//wait
			wait(ONE);
			
			//read profile role description from test data
			profileroledescription = Excel.readFromExcel(excel_file, sheet_name, datarow, datacolumn);
			noerror[ONE] = profileroledescription;
			
			displayOnConsole(sheet_name + " " + profileroledescription + " description ");
			
			//click new profile role
			click(tools_new_profile_role_link);
			wait(ONE);
			
			//enter profile role description
			click(profile_role_new_box_input);
			driver.findElement(profile_role_new_box_input).clear();
			enter(profile_role_new_box_input, profileroledescription);
			
			//click save
			click(profile_role_new_box_save);
			wait(TWO);
			
		} catch (Exception problem) {
			noerror[ZERO]="false";
			logFailMessageInExtentTest("Problem "+ problem.fillInStackTrace());
		}
		
		return noerror;
	}

	void clickMoreTool() {
		
		//Actions actions = new Actions(driver);
		//Action action = actions.click(driver.findElement(more)).pause(ONE).moveToElement(driver.findElement(more_tool)).click().build();
		//action.perform();
		//Actions actionsmore = new Actions(driver);
		//Action actionmore = actionsmore.moveToElement(driver.findElement(more_tool)).clickAndHold().pause(TWO).release().build();
		//actionmore.perform();
		try {
			click(more);
			Thread.sleep((8l));
			if(isElementPresent(driver, more_tool))
				click(more_tool);
		} catch (Exception problem) {
			problem.printStackTrace();
		}
	}
	
	void logoutChecking(){
		driver = driver.switchTo().defaultContent();//Added
		if(isElementPresent(driver, more_tool)){
			appUtil.logout();//Added
		}
	}

}
