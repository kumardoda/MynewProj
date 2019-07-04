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

public class ClinicalActivityType extends Functions {
	
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
	
	String readDescriptionFrmExcel="";
	By condnDesc_text = By.xpath("(//*[@id='highlightkeyword'][contains(text(),'"+readDescriptionFrmExcel+"')])");
	
	By tools_filterWithIntials_select = By.xpath("//select[@id='jumpTo']");
	By tools_condn_page_select = By.xpath("//select[@id='bbox']");
	
	By tool_frame = By.xpath("(//*[@id='toolsContent'])");
	By toOpen_Link_InputData = By.xpath("(//*[@id='container']/table/descendant::div[@id='content']/table/descendant::div[@id='leftDiv']/descendant::a[contains(text(),'Clinical Activity Type')])");
	By toOpen_Create_NewPage = By.xpath("(//*[@id='menuTable']/descendant::a[starts-with(@href,'javascript:newClinicalActivityType')])");
	
	By popUp_Exists_Description = By.xpath("(//*[@id='messageDiv']/descendant::div[contains(text(),'Entity with this Description already exists')])");
	By popUp_Exists_Close = By.xpath("(//*[@id='resultDiv']/div[@id='messageDiv']/descendant::a[@title='close']["+ONE+"])");
	
	//Create
	By toInput_Data_txtBox = By.xpath("(//*[@id='profilerPopupDiv']/descendant::input[@id='description'])");
	By toSave_Create_NewPage = By.xpath("(//*[@id='profilerPopupDiv']/div[@id='profilerPopupMenu']/table/descendant::a/img[@alt='Save'])");
	
	//Delete
	By tools_condn_text_delete = By.xpath("//*[@id='highlightkeyword'][contains(text(),'"+ readDescriptionFrmExcel +"')]/parent::td/preceding-sibling::td/parent::tr//*[self::td]["+FIVE+"]/a["+ONE+"]");
	By tools_condn_text_checkbox = By.xpath("//*[@id='highlightkeyword'][contains(text(),'"+readDescriptionFrmExcel+"')]/parent::td/preceding-sibling::td/descendant::input[@type='checkbox']");
	
	String deleteCondnTextMsg = "Delete 1 Clinical Activity Type?";
	String delCondnProcessingMsgText = "Your request is in queue. For more details check \"Profile Data Management->Realtime Mass Update tracker\" screen";
	
	//Edit
	By tools_condn_text_edit = By.xpath("//*[@id='highlightkeyword'][contains(text(),'"+ readDescriptionFrmExcel +"')]/parent::td/preceding-sibling::td/parent::tr//*[self::td]["+FIVE+"]/a["+TWO+"]");
	By condn_new_box_input = By.xpath("(//*[@id='profilerPopupDiv']/descendant::input[@id='description'])");
	By condn_new_box_save = By.xpath("(//*[@id='profilerPopupDiv']/div[@id='profilerPopupMenu']/table/descendant::a/img[@alt='Save'])");
	
	String txtMsg = "Adding new Clinical Activity Type ";
	
	@Test
	public void testCreateClinicalActType(){
		
		extentTest = extent.startTest("testCreateClinicalActType");
		sheet_name = "ClinicalActType";
		
		if(username == null){
			username = getUserName(sheet_name);
			password = getUserPassword(sheet_name);
		}
		
		//read profile role description from test data
		readDescriptionFrmExcel = Excel.readFromExcel(excel_file, sheet_name, FOUR, ONE);
		
		AppUtil appUtil = new AppUtil();
		if(appUtil.login(username, password)){
			
			displayOnConsole("testCreateClinicalActType() - User login SUCCESSFUL ");
			driver = driver.switchTo().window(driver.getWindowHandle());
			
			wait(ONE);
			
			boolean noerror = clinicalActTypeCreating (txtMsg, readDescriptionFrmExcel, toOpen_Link_InputData, toOpen_Create_NewPage);
			
			if(noerror == true){
				if(isElementPresent(driver, popUp_Exists_Description)){
					displayOnConsole("testCreateClinicalActType() - Creating new Clinical Activity Type UNSUCCESSFUL ");
					logFailMessageInExtentTest("testCreateClinicalActType() - Creating new Clinical Activity Type UNSUCCESSFUL ");
					
					String details = "Clinical Activity Type : Entity with this Description already exists. Please, give another description ";
					displayOnConsole("testCreateClinicalActType() -  "+details);
					
					String screenshot = getScreenshot(driver, "Profile_Condn_Exists");
					Actions actions = new Actions(driver);
					Action action = actions.moveToElement(driver.findElement(popUp_Exists_Close),TWO,ZERO).click().build();
					action.perform();
					logInfoMessageInExtentTest(details);
				} else {
					displayOnConsole("testCreateClinicalActType() - Creating new Clinical Activity Type SUCCESSFUL ");
					logInfoMessageInExtentTest("testCreateClinicalActType() - new Creating Clinical Activity Type SUCCESSFUL ");
					
					//Verifying for the added Condition
					displayOnConsole("testCreateClinicalActType() - Verifying the new added Clinical Activity Type.");
					boolean condnDescFlag = lookupDataOptionDescription(readDescriptionFrmExcel);
					if(condnDescFlag){
						displayOnConsole("testCreateClinicalActType() - Verifying new Clinical Activity Type SUCCESSFUL ");
						logPassMessageInExtentTest("testCreateClinicalActType() - Verifying new Clinical Activity Type SUCCESSFUL ");
					}
				}
			} else {
				
				logFailMessageInExtentTest("testCreateClinicalActType() - Creating new Clinical Activity Type UNSUCCESSFUL ");
			}
			
			driver = driver.switchTo().defaultContent();
			appUtil.logout();
		} else {
					displayOnConsole("testCreateClinicalActType() -  User login UNSUCCESSFUL, Creating new Clinical Activity Type UNSUCCESSFUL ");
					logFailMessageInExtentTest("testCreateClinicalActType() - User login UNSUCCESSFUL, Creating new Clinical Activity Type UNSUCCESSFUL ");
		}
	}
	
	@Test
	public void testDeleteClinicalActType(){
		
		//starting of test 
		extentTest = extent.startTest("testDeleteClinicalActType ");
		
		//delete profile role test data
		sheet_name = "ClinicalActType";
		
		if(username == null){
			username = getUserName(sheet_name);
			password = getUserPassword(sheet_name);
		}
		
		//read description from test data
		readDescriptionFrmExcel = Excel.readFromExcel(excel_file, sheet_name, SIX, ONE);
				
		AppUtil apputil = new AppUtil();
		if(apputil.login(username, password)){

			displayOnConsole("testDeleteClinicalActType() - User login SUCCESSFUL ");
			
			driver = driver.switchTo().defaultContent();

			boolean noerror = clinicalActTypeCreating (txtMsg, readDescriptionFrmExcel, toOpen_Link_InputData, toOpen_Create_NewPage);

			if(noerror == true){

				if (isElementPresent(driver, popUp_Exists_Description)){
						displayOnConsole("testDeleteClinicalActType() - Creating new Clinical Activity Type UNSUCCESSFUL ");
						logInfoMessageInExtentTest("testDeleteClinicalActType() - Creating new Clinical Activity Type UNSUCCESSFUL ");
						
						String details = "Clinical Activity Type : Entity with this Description already exists. Deleting existing one.";
						displayOnConsole("testDeleteClinicalActType() -  "+details);
						
						String screenshot = getScreenshot(driver, "Profile_Condn_Exists");
						Actions actions = new Actions(driver);
						Action action = actions.moveToElement(driver.findElement(popUp_Exists_Close),TWO,ZERO).click().build();
						action.perform();
						logInfoMessageInExtentTest(details);
				} else {
							displayOnConsole("testDeleteClinicalActType() - Creating new Clinical Activity Type SUCCESSFUL ");
							logInfoMessageInExtentTest("testDeleteClinicalActType() - Creating new Clinical Activity Type SUCCESSFUL ");
				}
				
				//Finding for deleting
				displayOnConsole("testDeleteClinicalActType() - Finding the Clinical Activity Type to delete.");
				boolean lookupProfile = lookupDataOptionDescription(readDescriptionFrmExcel);
				
				if(lookupProfile){

					boolean deletingprofilerole = clinicalActTypeDeleting(readDescriptionFrmExcel);
					
					if(deletingprofilerole){
						displayOnConsole("testDeleteClinicalActType() - Deleting Clinical Activity Type SUCCESSFUL ");
						logInfoMessageInExtentTest("testDeleteClinicalActType() - Deleting Clinical Activity Type SUCCESSFUL ");
						
						displayOnConsole("testDeleteClinicalActType() - Verifying the deleted Clinical Activity Type.");
						
						//Verifying whether deleted or not.
						lookupProfile =  lookupDataOptionDescription(readDescriptionFrmExcel);
						if(lookupProfile == false) {
								displayOnConsole("testDeleteClinicalActType() - Verifying for deleting Clinical Activity Type SUCCESSFUL ");
								logPassMessageInExtentTest("testDeleteClinicalActType() - Verifying for deleting Clinical Activity Type SUCCESSFUL ");
						}
						else {
								displayOnConsole("testDeleteClinicalActType() - Verifying for deleting Clinical Activity Type UNSUCCESSFUL ");
								logFailMessageInExtentTest("testDeleteClinicalActType() - Verifying for deleting Clinical Activity Type UNSUCCESSFUL ");
						}
					} else {
								displayOnConsole("testDeleteClinicalActType() - Deleting Clinical Activity Type UNSUCCESSFUL ");
								logFailMessageInExtentTest("testDeleteClinicalActType() - Deleting Clinical Activity Type UNSUCCESSFUL ");
					}
				} else {
					displayOnConsole("testDeleteClinicalActType() - Search for Clinical Activity Type itself UNSUCCESSFUL, so, can't procced to delete. ");
					logFailMessageInExtentTest("testDeleteClinicalActType() - Search for Clinical Activity Type itself UNSUCCESSFUL, so, can't procced to delete. ");
				}
				
			} else{
					displayOnConsole("testDeleteClinicalActType() - Creating new Clinical Activity Type itself UNSUCCESSFUL, so, can't procced to delete. ");
					logFailMessageInExtentTest("testDeleteClinicalActType() - Creating new Clinical Activity Type itself UNSUCCESSFUL, so, can't procced to delete. ");
			}
			
			driver = driver.switchTo().defaultContent();
			apputil.logout();

		}else{
			
			displayOnConsole("testDeleteClinicalActType() - User login UNSUCCESSFUL, deleting Clinical Activity Type UNSUCCESSFUL ");
			logFailMessageInExtentTest("testDeleteClinicalActType() - User login UNSUCCESSFUL, deleting Clinical Activity Type UNSUCCESSFUL ");
		
		}
	}

	@Test
	public void testEditClinicalActType(){
		
		//start of test
		extentTest = extent.startTest("testEditClinicalActType ");
		
		//edit profile role test data
		sheet_name = "ClinicalActType";
		
		//application username, password
		if(username == null){
			
			username = getUserName(sheet_name);
			password = getUserPassword(sheet_name);
			
		}
		
		//read description from test data
		readDescriptionFrmExcel = Excel.readFromExcel(excel_file, sheet_name, FOUR, ONE);
				
		//read profile role description from test data
		String strReplacement = Excel.readFromExcel(excel_file, sheet_name, FIVE, ONE);
		
		AppUtil apputil = new AppUtil();
		if(apputil.login(username, password)){
			
			displayOnConsole("testEditClinicalActType() - User login SUCCESSFUL ");
			
			boolean noerror = clinicalActTypeCreating (txtMsg, readDescriptionFrmExcel, toOpen_Link_InputData, toOpen_Create_NewPage);
			if(noerror == true){
				
				if(isElementPresent(driver, popUp_Exists_Description)){
					displayOnConsole("testEditClinicalActType() - Creating new Clinical Activity Type UNSUCCESSFUL ");
					logInfoMessageInExtentTest("testEditClinicalActType() - Creating new Clinical Activity Type UNSUCCESSFUL ");
					
					String details = "Clinical Activity Type already exists, editing existing one ";
					displayOnConsole("testProfileRoleEditing - "+details);
					
					String screenshot = getScreenshot(driver, "Profile_Role_Exists");
					Actions actions = new Actions(driver);
					Action action = actions.moveToElement(driver.findElement(popUp_Exists_Close),TWO,ZERO).click().build();
					action.perform();
					logInfoMessageInExtentTest("testEditClinicalActType() - " + details);
					
				} else {
						displayOnConsole("testEditClinicalActType() - Creating new Clinical Activity Type SUCCESSFUL ");
						logInfoMessageInExtentTest("testEditClinicalActType() - Creating new Clinical Activity Type SUCCESSFUL ");
				}
				
				//Finding for editing
				displayOnConsole("testEditClinicalActType() - Finding the Clinical Activity Type to Edit.");
				boolean lookupProfile = lookupDataOptionDescription(readDescriptionFrmExcel);
				
				if(lookupProfile){

					boolean profileroleedit = clinicalActTypeEditing(readDescriptionFrmExcel, strReplacement);
					
					if(profileroleedit){
						
						if(isElementPresent(driver, popUp_Exists_Description)){
							
							String details = "Clinical Activity Type with same name already exists, Editing Clinical Activity Type UNSUCCESSFUL ";
							displayOnConsole("testEditClinicalActType() : "+details);
							
							String screenshot = getScreenshot(driver, "Profile_Role_Exists");
							Actions actions = new Actions(driver);
							Action action = actions.moveToElement(driver.findElement(popUp_Exists_Close),TWO,ZERO).click().build();
							action.perform();
							logInfoMessageInExtentTest("testEditClinicalActType() - " + details);
							logFailMessageInExtentTest("testEditCondition() - Editing Condition UNSUCCESSFUL ");
							
						} else {
							displayOnConsole("testEditClinicalActType() - Editing Clinical Activity Type SUCCESSFUL ");
							logInfoMessageInExtentTest("testEditClinicalActType() - Editing Clinical Activity Type SUCCESSFUL ");
							
							displayOnConsole("testEditClinicalActType() - Verifying the Edited Clinical Activity Type.");
							
							//Verifying whether edited or not.
							lookupProfile = lookupDataOptionDescription(strReplacement);
							if(lookupProfile == true) {
								displayOnConsole("testEditClinicalActType() - Verifying Edited Clinical Activity Type SUCCESSFUL ");
								logPassMessageInExtentTest("testEditClinicalActType() - Verifing Edited Clinical Activity Type SUCCESSFUL ");
							}
							else {
								displayOnConsole("testEditClinicalActType() - Verifying Edited Clinical Activity Type UNSUCCESSFUL ");
								logFailMessageInExtentTest("testEditClinicalActType() - Edited Clinical Activity Type UNSUCCESSFUL ");
							}	
						}
					} else{
								displayOnConsole("testEditClinicalActType() - Editing Clinical Activity Type UNSUCCESSFUL ");
								logPassMessageInExtentTest("testEditClinicalActType() - Editing Clinical Activity Type UNSUCCESSFUL ");
					}
				} else {
							displayOnConsole("testEditClinicalActType() - Search for Clinical Activity Type itself UNSUCCESSFUL, so, can't procced to edit. ");
							logFailMessageInExtentTest("testEditClinicalActType() - Search for Clinical Activity Type itself UNSUCCESSFUL, so, can't procced to edit. ");
				}
				
			} else {
						displayOnConsole("testEditClinicalActType() - Creating new Clinical Activity Type itself UNSUCCESSFUL, so, can't procced to edit. ");
						logFailMessageInExtentTest("testEditClinicalActType() - Creating new Clinical Activity Type itself UNSUCCESSFUL, so, can't procced to edit. ");
			}
			
			driver = driver.switchTo().defaultContent();
			apputil.logout();

		} else{
			
			displayOnConsole("testEditClinicalActType() - User login UNSUCCESSFUL, Editing Clinical Activity Type UNSUCCESSFUL ");
			logFailMessageInExtentTest("testEditClinicalActType() - User login UNSUCCESSFUL, Editing Clinical Activity Type UNSUCCESSFUL ");
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
	
	boolean clinicalActTypeCreating (String txtMsg, String readDescriptionFrmExcel, By toOpen_Link_InputData, By toOpen_Create_NewPage) {
		boolean noerror = true;

		try {
			
			//click for more tools
			//By more = By.xpath("(//div[@id='navWrapper']/ul[@id='sectionsNav']/descendant::a[@title='More'])");
			//By more_tool = By.xpath("(//*[@id='section_tools'])");
			//click(more);
			//wait(TWO);
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
			
			displayOnConsole("clinicalActTypeCreating() - "+txtMsg);
			
			//go to internal html
			//driver = driver.switchTo().frame(driver.findElement(tool_frame));
			
			wait(ONE);
			//click Clinical Activity Type link
			click(toOpen_Link_InputData);
			//wait
			wait(ONE);
			
			displayOnConsole("clinicalActTypeCreating() - Description read from excel-sheet '" + sheet_name +"' is '" +readDescriptionFrmExcel + "'");
			
			//click new profile role
			click(toOpen_Create_NewPage);
			wait(ONE);
			
			//Enter Profile Condition description
			click(toInput_Data_txtBox);
			driver.findElement(toInput_Data_txtBox).clear();
			enter(toInput_Data_txtBox, readDescriptionFrmExcel);
			
			//click save
			click(toSave_Create_NewPage);
			wait(TWO);
			
		} catch (Exception problem) {
			noerror=false;
			logFailMessageInExtentTest("clinicalActTypeCreating() :: Problem : "+ problem.fillInStackTrace());
		}
		
		return noerror;
	}
	
	boolean clinicalActTypeDeleting(String readDescriptionFrmExcel) {
		boolean deleteDataOptionFlag = true;
		
		try {
			
			//delete of Condition description
			readDescriptionFrmExcel = readDescriptionFrmExcel.trim();
			
			displayOnConsole("clinicalActTypeDeleting() - Deleting Entity : "+ readDescriptionFrmExcel);
			
			tools_condn_text_delete = By.xpath("(//table[@id='cu']/descendant::div[@id='highlightkeyword'][contains(text(),'"+ readDescriptionFrmExcel +"')]/parent::td/preceding-sibling::td/parent::tr/td["+FIVE+"]/a["+ONE+"]/img)");
			tools_condn_text_checkbox = By.xpath("(//table[@id='cu']/descendant::div[@id='highlightkeyword'][contains(text(),'"+ readDescriptionFrmExcel +"')]/parent::td/preceding-sibling::td/descendant::input[@type='checkbox'])");
			
			click(tools_condn_text_checkbox);
			wait(ONE);
			
			click(tools_condn_text_delete);
			wait(ONE);
			
			//accept delete profile role description alerts
			Alert alertbox = driver.switchTo().alert();
			String alertmessagetext = alertbox.getText();
			boolean textcontent = alertmessagetext.contains(deleteCondnTextMsg);
			if(textcontent){
				//logInfoMessageInExtentTest(deleteCondnTextMsg + " message shown ");
			}
			
			alertbox.accept();
			wait(TWO);
						
		} catch (Exception problem) {
			
			deleteDataOptionFlag = false;
			logInfoMessageInExtentTest("clinicalActTypeDeleting() :: Problem : "+ problem.getMessage());
		}
		
		return deleteDataOptionFlag;
	}
	
	boolean clinicalActTypeEditing(String readDescriptionFrmExcel, String strReplacement) {
		boolean editDataOptionFlag = true;
		
		try {
			
			//delete entity description
			readDescriptionFrmExcel = readDescriptionFrmExcel.trim();
			tools_condn_text_edit = By.xpath("(//table[@id='cu']/descendant::div[@id='highlightkeyword'][contains(text(),'"+ readDescriptionFrmExcel +"')]/parent::td/preceding-sibling::td/parent::tr//*[self::td]["+FIVE+"]/a["+TWO+"]/img)");
			click(tools_condn_text_edit);
			wait(ONE);
			
			//enter profile role description
			click(condn_new_box_input);
			driver.findElement(condn_new_box_input).clear();
			enter(condn_new_box_input, strReplacement);
			
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
			
			readDescriptionFrmExcel = editprofileroledescription.trim();*/
			
		} catch (Exception problem) {

			editDataOptionFlag = false;
			logInfoMessageInExtentTest("clinicalActTypeEditing :: Problem " + problem.fillInStackTrace());
			
		}

		return editDataOptionFlag;
	}
	
	boolean lookupDataOptionDescription(String readDescriptionFrmExcel) {
		
		boolean lookUpDataFlag = false;
		
		String filter = String.valueOf(readDescriptionFrmExcel.charAt(ZERO)).toUpperCase();
		
		boolean selectvalue = selectByValue(driver, tools_filterWithIntials_select, filter);
		wait(TWO);
		
		readDescriptionFrmExcel = readDescriptionFrmExcel.trim();
		condnDesc_text = By.xpath("//*[@id='highlightkeyword'][contains(text(),'"+readDescriptionFrmExcel+"')]");
		
	
		filter = String.valueOf(ONE);
		
		List<WebElement> navigationoptions = null;
		try
		{
			navigationoptions = driver.findElements(By.xpath("//select[@id='bbox']//*[self::option]"));
		}catch(Exception problem){
			logInfoMessageInExtentTest("lookupDataOptionDescription() :: problem : " + problem.getMessage());
		}
		
		displayOnConsole("lookupDataOptionDescription() - Total pages : "+navigationoptions.size());
		for(byte pages = TWO; pages <= navigationoptions.size(); pages++){
			if((lookUpDataFlag = isElementPresent(driver, condnDesc_text)) == true){
				break;
			}else{
				click(By.xpath("//table[@id='cu']/thead/tr["+ONE+"]/td["+TWO+"]/a"));
				lookUpDataFlag = isElementPresent(driver, condnDesc_text);
				if(lookUpDataFlag == true)
					break;
			}
			
			filter = String.valueOf(pages);
			selectvalue = selectByValue(driver, tools_condn_page_select, filter);
			wait(ONE);
		}
		
		if(lookUpDataFlag == true)
			displayOnConsole("lookupDataOptionDescription() - Searched profile found at page no. : "+filter);
		
		if(navigationoptions.size() == ONE)
		{
			lookUpDataFlag = isElementPresent(driver, condnDesc_text);
		}
		
		return lookUpDataFlag;
	}

}
