package com.profiler.data;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.profiler.utils.AppUtil;
import com.profiler.utils.Excel;
import com.profiler.utils.Functions;
import com.relevantcodes.extentreports.LogStatus;

public class Condition extends Functions {
	
	byte ZERO = 0;
	byte ONE = 1;
	byte TWO = 2;
	byte THREE = 3;
	byte FOUR = 4;
	byte FIVE = 5;
	byte SIX = 6;
	
	String username = null;
	String password = null;
	String excel_file = "files//TestData.xlsx";
	String sheet_name = null;
	
	String condnDescription="";
	By condnDesc_text = By.xpath("(//*[@id='highlightkeyword'][contains(text(),'"+condnDescription+"')])");
	
	By tools_filterWithIntials_select = By.xpath("//select[@id='jumpTo']");
	By tools_condn_page_select = By.xpath("//select[@id='bbox']");
	
	By tool_frame = By.xpath("(//*[@id='toolsContent'])");
	
	By toOpen_Link_InputData = By.xpath("(//*[@id='container']/table/descendant::div[@id='content']/table/descendant::div[@id='leftDiv']/descendant::a[contains(text(),'Condition')])");
	By toOpen_Create_NewPage = By.xpath("(//*[@id='menuTable']/descendant::a[starts-with(@href,'javascript:newCondition')])");
	
	By popUp_Exists_Description = By.xpath("(//*[@id='messageDiv']/descendant::div[contains(text(),'Entity with this Description already exists')])");
	By popUp_Exists_Close = By.xpath("(//*[@id='resultDiv']/div[@id='messageDiv']/descendant::a[@title='close']["+ONE+"])");
	
	//Create
	By toInput_Data_txtBox = By.xpath("(//*[@id='profilerPopupDiv']/descendant::input[@id='description'])");
	By toSave_Create_NewPage = By.xpath("(//*[@id='profilerPopupDiv']/div[@id='profilerPopupMenu']/table/descendant::a/img[@alt='Save'])");
	
	//Delete
	By tools_condn_text_delete = By.xpath("//*[@id='highlightkeyword'][contains(text(),'"+ condnDescription +"')]/parent::td/preceding-sibling::td/parent::tr//*[self::td]["+FIVE+"]/a["+ONE+"]");
	By tools_condn_text_checkbox = By.xpath("//*[@id='highlightkeyword'][contains(text(),'"+condnDescription+"')]/parent::td/preceding-sibling::td/descendant::input[@type='checkbox']");
	
	String deleteCondnTextMsg = "Delete 1 Condition?";
	String delCondnProcessingMsgText = "Your request is in queue. For more details check \"Profile Data Management->Realtime Mass Update tracker\" screen";
	
	//Edit
	By tools_condn_text_edit = By.xpath("//*[@id='highlightkeyword'][contains(text(),'"+ condnDescription +"')]/parent::td/preceding-sibling::td/parent::tr//*[self::td]["+FIVE+"]/a["+TWO+"]");
	By condn_new_box_input = By.xpath("(//*[@id='profilerPopupDiv']/descendant::input[@id='description'])");
	By condn_new_box_save = By.xpath("(//*[@id='profilerPopupDiv']/div[@id='profilerPopupMenu']/table/descendant::a/img[@alt='Save'])");
	
	@Test
	public void testCreateCondition(){
		
		extentTest = extent.startTest("testCreateCondition");
		sheet_name = "Condition";
		
		if(username == null){
			username = getUserName(sheet_name);
			password = getUserPassword(sheet_name);
		}
		
		//read description from test data
		condnDescription = Excel.readFromExcel(excel_file, sheet_name, FOUR, ONE);
		
		AppUtil appUtil = new AppUtil();
		if(appUtil.login(username, password)){
			
			displayOnConsole("testCreateCondition() - User login SUCCESSFUL ");
			driver = driver.switchTo().window(driver.getWindowHandle());
			
			wait(ONE);
			
			boolean noerror = conditionCreating(condnDescription);
			if(noerror == true){
				if(isElementPresent(driver, popUp_Exists_Description)){
					displayOnConsole("testCreateCondition() - Creating new Condition UNSUCCESSFUL ");
					logFailMessageInExtentTest("testCreateCondition() - Creating new Condition UNSUCCESSFUL ");
					
					String details = "Condition : Entity with this Description already exists. Please, give another description ";
					displayOnConsole("testCreateCondition() -  "+details);
					
					String screenshot = getScreenshot(driver, "condnExists_scrnShot");
					Actions actions = new Actions(driver);
					Action action = actions.moveToElement(driver.findElement(popUp_Exists_Close),TWO,ZERO).click().build();
					action.perform();
					logInfoMessageInExtentTest(details);
					logInfoMessageInExtentTest("testCreateCondition() - Creating new Condition UNSUCCESSFUL ");
				}else{
					displayOnConsole("testCreateCondition() - Creating new Condition SUCCESSFUL ");
					logInfoMessageInExtentTest("testCreateCondition() - Creating new Condition SUCCESSFUL ");
					
					//Verifying for the added Condition
					displayOnConsole("testCreateCondition() - Verifying the new added Condition.");
					boolean condnDescFlag = lookupCondnDescription(condnDescription);
					if(condnDescFlag){
						displayOnConsole("testCreateCondition() - Verifying new Condition SUCCESSFUL ");
						logPassMessageInExtentTest("testCreateCondition() - Verifying new Condition SUCCESSFUL ");
					}
				}
			}else{
					logFailMessageInExtentTest("testCreateCondition() - Creating new Condition UNSUCCESSFUL ");
			}
			
			driver = driver.switchTo().defaultContent();
			appUtil.logout();
		}else {
			
			displayOnConsole("testCreateCondition() - User login UNSUCCESSFUL, Creating new Condition UNSUCCESSFUL ");
			logFailMessageInExtentTest("testCreateCondition() - User login UNSUCCESSFUL, Creating new Condition UNSUCCESSFUL ");
		}
	}
	
	@Test
	public void testDeleteCondition(){
		
		//starting of test 
		extentTest = extent.startTest("testDeleteCondition ");
		
		//delete Condition sheet
		sheet_name = "Condition";
		
		if(username == null){
			username = getUserName(sheet_name);
			password = getUserPassword(sheet_name);
		}
		
		//read description from test data
		condnDescription = Excel.readFromExcel(excel_file, sheet_name, SIX, ONE);
		
		AppUtil apputil = new AppUtil();
		if(apputil.login(username, password)){

			displayOnConsole("testDeleteCondition() - User login SUCCESSFUL ");
			
			driver = driver.switchTo().defaultContent();

			boolean noerror = conditionCreating(condnDescription);

			if(noerror == true){

				if (isElementPresent(driver, popUp_Exists_Description)){
						displayOnConsole("testDeleteCondition() - Creating new Condition UNSUCCESSFUL ");
						logInfoMessageInExtentTest("testDeleteCondition() - Creating new Condition UNSUCCESSFUL ");
						
						String details = "Condition : Entity with this Description already exists. Deleting existing one.";
						displayOnConsole("testDeleteCondition() -  "+details);
						
						String screenshot = getScreenshot(driver, "condnExists_scrnShot");
						Actions actions = new Actions(driver);
						Action action = actions.moveToElement(driver.findElement(popUp_Exists_Close),TWO,ZERO).click().build();
						action.perform();
						logInfoMessageInExtentTest(details);
				} else {
							displayOnConsole("testDeleteCondition() - Creating new Condition SUCCESSFUL ");
							logInfoMessageInExtentTest("testDeleteCondition() - Creating new Condition SUCCESSFUL ");
				}
				
				boolean lookupProfile = lookupCondnDescription(condnDescription);
				
				if(lookupProfile){

					boolean deletingprofilerole = conditionDeleting(condnDescription);
					
					if(deletingprofilerole){
						displayOnConsole("testDeleteCondition() - deleting Condition SUCCESSFUL ");
						logInfoMessageInExtentTest("testDeleteCondition() - deleting Condition SUCCESSFUL ");
						
						displayOnConsole("testDeleteCondition() - Verifying the deleted Condition.");
						
						//Verifying whether deleted or not.
						lookupProfile =  lookupCondnDescription(condnDescription);
						if(lookupProfile == false) {
								displayOnConsole("testDeleteCondition() - Verifying the deleted Condition SUCCESSFUL ");
								logPassMessageInExtentTest("testDeleteCondition() - Verifying the deleted Condition SUCCESSFUL ");
						}
						else {
								displayOnConsole("testDeleteCondition() - Verifying the deleted Condition UNSUCCESSFUL ");
								logFailMessageInExtentTest("testDeleteCondition() - Verifying the deleted Condition UNSUCCESSFUL ");
						}
					} else {
								displayOnConsole("testDeleteCondition() - deleting Condition UNSUCCESSFUL ");
								logFailMessageInExtentTest("testDeleteCondition() - deleting Condition UNSUCCESSFUL ");
					}
				} else {
							displayOnConsole("testDeleteCondition() - Search for Condition itself UNSUCCESSFUL, so, can't procced to delete. ");
							logFailMessageInExtentTest("testDeleteCondition() - Search for Condition itself UNSUCCESSFUL, so, can't procced to delete. ");
				}
				
			} else{
					displayOnConsole("testDeleteCondition() - Creating new Condition itself UNSUCCESSFUL, so, can't procced to delete. ");
					logFailMessageInExtentTest("testDeleteCondition() - Creating new Condition itself UNSUCCESSFUL, so, can't procced to delete. ");
			}
			
			driver = driver.switchTo().defaultContent();
			apputil.logout();

		}else{
			
			displayOnConsole("testDeleteCondition() - User login UNSUCCESSFUL, deleting Condition UNSUCCESSFUL  ");
			logFailMessageInExtentTest("testDeleteCondition() - User login UNSUCCESSFUL, deleting Condition UNSUCCESSFUL ");
		
		}
	}

	@Test
	public void testEditCondition(){
		
		//start of test
		extentTest = extent.startTest("testEditCondition ");
		
		//Edit Condition test data
		sheet_name = "Condition";
		
		//application username, password
		if(username == null){
			
			username = getUserName(sheet_name);
			password = getUserPassword(sheet_name);
			
		}
		
		//read description from test data
		condnDescription = Excel.readFromExcel(excel_file, sheet_name, FOUR, ONE);
		
		//Committee - New name : For Editing
		String strReplacement = Excel.readFromExcel(excel_file, sheet_name, FIVE, ONE);
				
		AppUtil apputil = new AppUtil();
		if(apputil.login(username, password)){
			
			displayOnConsole("testEditCondition() - User login SUCCESSFUL ");
			
			boolean noerror = conditionCreating(condnDescription);
			if(noerror == true){
				
				if(isElementPresent(driver, popUp_Exists_Description)){
					displayOnConsole("testCreateCondition() - Creating new Condition UNSUCCESSFUL ");
					logInfoMessageInExtentTest("testCreateCondition() - Creating new Condition UNSUCCESSFUL ");
					
					String details = "Condition already exists, editing existing one. ";
					displayOnConsole("testProfileRoleEditing - "+details);
					
					String screenshot = getScreenshot(driver, "condnExists_scrnShot");
					Actions actions = new Actions(driver);
					Action action = actions.moveToElement(driver.findElement(popUp_Exists_Close),TWO,ZERO).click().build();
					action.perform();
					logInfoMessageInExtentTest("testEditCondition() - " + details);
					
				} else {
						displayOnConsole("testEditCondition() - Creating new Condition SUCCESSFUL ");
						logInfoMessageInExtentTest("testEditCondition() - Creating new Condition SUCCESSFUL ");
				}
				
				//Finding for editing
				displayOnConsole("testEditCondition() - Finding the Condition to Edit.");
				boolean lookupProfile = lookupCondnDescription(condnDescription);
				
				if(lookupProfile){

					boolean profileroleedit = conditionEditing(condnDescription, strReplacement );
					
					if(profileroleedit){
						if(isElementPresent(driver, popUp_Exists_Description)){
							
							String details = "Condition with same name already exists, Editing Condition UNSUCCESSFUL ";
							displayOnConsole("testEditCondition - "+details);
							
							String screenshot = getScreenshot(driver, "condnExists_scrnShot");
							Actions actions = new Actions(driver);
							Action action = actions.moveToElement(driver.findElement(popUp_Exists_Close),TWO,ZERO).click().build();
							action.perform();
							logInfoMessageInExtentTest("testEditCondition() - " + details);
							logFailMessageInExtentTest("testEditCondition() - Editing Condition UNSUCCESSFUL ");
						} else {
								displayOnConsole("testEditCondition() - Editing Condition SUCCESSFUL ");
								logInfoMessageInExtentTest("testEditCondition() - Editing Condition SUCCESSFUL ");
								
								displayOnConsole("testEditCondition() - Verifying the Edited Condition.");
								
								//Verifying whether edited or not.
								lookupProfile = lookupCondnDescription(strReplacement);
								if(lookupProfile == true) {
									displayOnConsole("testEditCondition() - Verifying Edited Condition SUCCESSFUL ");
									logPassMessageInExtentTest("testEditCondition() - Verifying Edited condition SUCCESSFUL ");
								}
								else {
									displayOnConsole("testEditCondition() - Verifying Edited Condition UNSUCCESSFUL ");
									logFailMessageInExtentTest("testEditCondition() - Verifying Edited Condition UNSUCCESSFUL ");
								}
						}
					} else {
							displayOnConsole("testEditCondition() - Editing Condition UNSUCCESSFUL ");
							logFailMessageInExtentTest("testEditCondition() - Editing Condition UNSUCCESSFUL ");
					}
				} else {
							displayOnConsole("testEditCondition() - Search for Condition itself UNSUCCESSFUL, so, can't procced to edit. ");
							logFailMessageInExtentTest("testEditCondition() - Search for Condition itself UNSUCCESSFUL, so, can't procced to edit. ");
				}
				
			} else {
				displayOnConsole("testEditCondition() - Creating new Condition itself UNSUCCESSFUL, so, can't procced to edit. ");
				logFailMessageInExtentTest("testEditCondition() - Creating new Condition itself UNSUCCESSFUL, so, can't procced to edit. ");
			}
			
			driver = driver.switchTo().defaultContent();
			apputil.logout();

		}else{
			displayOnConsole("testEditCondition() - User login UNSUCCESSFUL, Editing Condition UNSUCCESSFUL ");
			logFailMessageInExtentTest("testEditCondition() - User login UNSUCCESSFUL, Editing Condition UNSUCCESSFUL ");
		}
	}

	String getUserPassword(String sheet_name) {
		
		String password = Excel.readFromExcel(excel_file, sheet_name, THREE, ONE);
		if(password == null || password.isEmpty() || password.equalsIgnoreCase("")){
			password = Excel.readFromExcel(excel_file, "Application", THREE, ONE);
		}
		
		return password;
	}

	String getUserName(String sheet_name) {
		String user = Excel.readFromExcel(excel_file, sheet_name, TWO, ONE);
		if(user == null || user.isEmpty() || user.equalsIgnoreCase("")){
			user = Excel.readFromExcel(excel_file, "Application", TWO, ONE);
		}
		return user;	
	}
	
	void displayOnConsole(String consoleMessage) {
		System.out.println(consoleMessage);
	}
	
	void logFailMessageInExtentTest(String details) {
		extentTest.log(LogStatus.FAIL, details);
	}
	
	void logPassMessageInExtentTest(String details) {
		extentTest.log(LogStatus.PASS, details);
	}

	void logInfoMessageInExtentTest(String details) {
		extentTest.log(LogStatus.INFO, details);
	}
	
	boolean conditionCreating(String condnDescription) {
		boolean noerror = true;

		try {
			
			//click for more tools
			//By more = By.xpath("(//div[@id='navWrapper']/ul[@id='sectionsNav']/descendant::a[@title='More'])");
			//By more_tool = By.xpath("(//*[@id='section_tools'])");
			//click(more);
			wait(TWO);
			//click(more_tool);
			
			//Above was temporarily not working , so using below workaround to navigate
			//driver.navigate().to(driver.getCurrentUrl()+"action=newFrameset&sourceTab=tools&siteArea=admin&isToolsNavigation=true");
			AppUtil apputil = new AppUtil();//Added
			boolean tools_page_shown = apputil.gotoToolsPage();//Added
			if(tools_page_shown == false){//Added
				Assert.fail("Tools page failed to load");
				getScreenshot(driver, "ToolsPageLoading_Failed");
			}
			wait(ONE);
			
			displayOnConsole("Adding new Condition ");
			
			//go to internal html
			//driver = driver.switchTo().frame(driver.findElement(tool_frame));
			
			wait(ONE);
			//click Condition role link
			click(toOpen_Link_InputData);
			//wait
			wait(ONE);
			
			displayOnConsole("Description read from excel-sheet '" + sheet_name +"' is '" +condnDescription + "'");
			
			//click new condition
			click(toOpen_Create_NewPage);
			wait(ONE);
			
			//Enter  Condition description
			click(toInput_Data_txtBox);
			driver.findElement(toInput_Data_txtBox).clear();
			enter(toInput_Data_txtBox, condnDescription);
			
			//click save
			click(toSave_Create_NewPage);
			wait(TWO);
			
		} catch (Exception problem) {
			noerror=false;
			logFailMessageInExtentTest("conditionCreating() :: Problem : "+ problem.fillInStackTrace());
		}
		
		return noerror;
	}
	
	boolean conditionDeleting(String condnDescription) {
		boolean deleteCondition = true;
		
		try {
			
			condnDescription = condnDescription.trim();
			
			displayOnConsole("Deleting Condition : "+ condnDescription);
			
			tools_condn_text_delete = By.xpath("(//table[@id='cu']/descendant::div[@id='highlightkeyword'][contains(text(),'"+ condnDescription +"')]/parent::td/preceding-sibling::td/parent::tr/td["+FIVE+"]/a["+ONE+"]/img)");
			tools_condn_text_checkbox = By.xpath("(//table[@id='cu']/descendant::div[@id='highlightkeyword'][contains(text(),'"+ condnDescription +"')]/parent::td/preceding-sibling::td/descendant::input[@type='checkbox'])");
			
			click(tools_condn_text_checkbox);
			wait(ONE);
			
			click(tools_condn_text_delete);
			wait(ONE);
			
			//accept delete alert
			Alert alertbox = driver.switchTo().alert();
			String alertmessagetext = alertbox.getText();
			boolean textcontent = alertmessagetext.contains(deleteCondnTextMsg);
			if(textcontent){
				//logInfoMessageInExtentTest(deleteCondnTextMsg + " message shown ");
			}
			
			alertbox.accept();
			wait(TWO);
			
		} catch (Exception problem) {
			
			deleteCondition = false;
			logInfoMessageInExtentTest("conditionDeleting() :: Problem : "+ problem.getMessage());
		}
		
		return deleteCondition;
	}
	
	boolean conditionEditing (String condnDescription, String editCondnDescription) {
		boolean EditCondition = true;
		
		try {
			
			//edit Condition 
			condnDescription = condnDescription.trim();
			tools_condn_text_edit = By.xpath("(//table[@id='cu']/descendant::div[@id='highlightkeyword'][contains(text(),'"+ condnDescription +"')]/parent::td/preceding-sibling::td/parent::tr//*[self::td]["+FIVE+"]/a["+TWO+"]/img)");
			click(tools_condn_text_edit);
			wait(ONE);
			
			//enter description
			click(condn_new_box_input);
			driver.findElement(condn_new_box_input).clear();
			enter(condn_new_box_input, editCondnDescription);
			
			//click save
			click(condn_new_box_save);
			wait(ONE);
			
			/*//accept edit profile role description alerts
			Alert alertbox = driver.switchTo().alert();
			String alertmessagetext = alertbox.getText();
			
			boolean textcontent = alertmessagetext.contains(delCondnProcessingMsgText);
			if(textcontent){
				logInfoMessageInExtentTest(delCondnProcessingMsgText + " message shown ");
			}
			
			alertbox.accept();
			wait(TWO);
			
			condnDescription = editCondnDescription.trim();*/
			
		} catch (Exception problem) {

			EditCondition = false;
			logInfoMessageInExtentTest("Problem " + problem.fillInStackTrace());
			
		}

		return EditCondition;
	}
	
	boolean lookupCondnDescription(String lookForString) {
		
		boolean condnFlag = false;
		
		String filter = String.valueOf(lookForString.charAt(ZERO)).toUpperCase();
		
		boolean selectvalue = selectByValue(driver, tools_filterWithIntials_select, filter);
		wait(TWO);
		
		lookForString = lookForString.trim();
		condnDesc_text = By.xpath("//*[@id='highlightkeyword'][contains(text(),'"+lookForString+"')]");
		
	
		filter = String.valueOf(ONE);
		
		List<WebElement> navigationoptions = null;
		try
		{
			navigationoptions = driver.findElements(By.xpath("//select[@id='bbox']//*[self::option]"));
		}catch(Exception problem){
			logInfoMessageInExtentTest("lookupCondnDescription() :: problem : " + problem.getMessage());
		}
		
		displayOnConsole("Total pages : "+navigationoptions.size());
		for(byte pages = TWO; pages <= navigationoptions.size(); pages++){
			if((condnFlag = isElementPresent(driver, condnDesc_text)) == true){
				break;
			}else{
				click(By.xpath("//table[@id='cu']/thead/tr["+ONE+"]/td["+TWO+"]/a"));
				condnFlag = isElementPresent(driver, condnDesc_text);
				if(condnFlag == true)
					break;
			}
			
			filter = String.valueOf(pages);
			selectvalue = selectByValue(driver, tools_condn_page_select, filter);
			wait(ONE);
		}
		
		if(condnFlag == true)
			displayOnConsole("Searched Condition found at page no. : "+filter);
		
		if(navigationoptions.size() == ONE)
		{
			condnFlag = isElementPresent(driver, condnDesc_text);
		}
		
		return condnFlag;
	}

}
