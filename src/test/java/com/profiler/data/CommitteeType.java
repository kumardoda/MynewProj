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

public class CommitteeType extends Functions {
	
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
	By toOpen_Link_InputData = By.xpath("(//*[@id='container']/table/descendant::div[@id='content']/table/descendant::div[@id='leftDiv']/descendant::a[contains(text(),'Committee Type')])");
	By toOpen_Create_NewPage = By.xpath("(//*[@id='menuTable']/descendant::a[starts-with(@href,'javascript:newCommitteeType()')])");
	
	By popUp_Exists_Description = By.xpath("(//*[@id='messageDiv']/descendant::div[contains(text(),'Entity with this Description already exists')])");
	By popUp_Exists_Close = By.xpath("(//*[@id='resultDiv']/div[@id='messageDiv']/descendant::a[@title='close']["+ONE+"])");
	
	//Create
	By toInput_Data_txtBox = By.xpath("(//*[@id='profilerPopupDiv']/descendant::input[@id='description'])");
	By toSave_Create_NewPage = By.xpath("(//*[@id='profilerPopupDiv']/div[@id='profilerPopupMenu']/table/descendant::a/img[@alt='Save'])");
	
	//Delete
	By tools_condn_text_delete = By.xpath("//*[@id='highlightkeyword'][contains(text(),'"+ readDescriptionFrmExcel +"')]/parent::td/preceding-sibling::td/parent::tr//*[self::td]["+FIVE+"]/a["+ONE+"]");
	By tools_condn_text_checkbox = By.xpath("//*[@id='highlightkeyword'][contains(text(),'"+readDescriptionFrmExcel+"')]/parent::td/preceding-sibling::td/descendant::input[@type='checkbox']");
	
	String deleteCondnTextMsg = "Delete 1 Committee Type?";
	String delCondnProcessingMsgText = "Your request is in queue. For more details check \"Profile Data Management->Realtime Mass Update tracker\" screen";
	
	//Edit
	By tools_condn_text_edit = By.xpath("//*[@id='highlightkeyword'][contains(text(),'"+ readDescriptionFrmExcel +"')]/parent::td/preceding-sibling::td/parent::tr//*[self::td]["+FIVE+"]/a["+TWO+"]");
	By condn_new_box_input = By.xpath("(//*[@id='profilerPopupDiv']/descendant::input[@id='description'])");
	By condn_new_box_save = By.xpath("(//*[@id='profilerPopupDiv']/div[@id='profilerPopupMenu']/table/descendant::a/img[@alt='Save'])");
	
	String txtMsg = "Adding new Committee Type ";
	
	@Test
	public void testCreateCommType(){
		
		extentTest = extent.startTest("testCreateCommType");
		logInfoMessageInExtentTest(" ********** Starting testing : Committee Type - Create flow *************");
		
		sheet_name = "CommitteeType";
		
		if(username == null){
			username = getUserName(sheet_name);
			password = getUserPassword(sheet_name);
		}
		
		//read profile role description from test data
		readDescriptionFrmExcel = Excel.readFromExcel(excel_file, sheet_name, FOUR, ONE);
		
		AppUtil appUtil = new AppUtil();
		if(appUtil.login(username, password)){
			
			displayOnConsole("testCreateCommType() - User login SUCCESSFUL ");
			driver = driver.switchTo().window(driver.getWindowHandle());
			
			wait(ONE);
			
			boolean noerror = commTypeCreating (txtMsg, readDescriptionFrmExcel, toOpen_Link_InputData, toOpen_Create_NewPage);
			
			if(noerror == true){
				if(isElementPresent(driver, popUp_Exists_Description)){
					displayOnConsole("testCreateCommType() - Creating new Committee Type UNSUCCESSFUL ");
					logFailMessageInExtentTest("testCreateCommType() - Creating new Committee Type UNSUCCESSFUL ");
					
					String details = "Committee Type : Entity with this Description already exists. Please, give another description ";
					displayOnConsole("testCreateCommType() -  "+details);
					
					String screenshot = getScreenshot(driver, "Profile_Condn_Exists");
					Actions actions = new Actions(driver);
					Action action = actions.moveToElement(driver.findElement(popUp_Exists_Close),TWO,ZERO).click().build();
					action.perform();
					logInfoMessageInExtentTest(details);
				} else {
					displayOnConsole("testCreateCommType() - Creating new Committee Type SUCCESSFUL ");
					logInfoMessageInExtentTest("testCreateCommType() - Creating new Committee Type SUCCESSFUL ");
					
					//Verifying for the added Condition
					displayOnConsole("testCreateCommType() - Verifying the new added Committee Type.");
					logInfoMessageInExtentTest("testCreateCommType() - Verifying the new added Committee Type.");
					
					boolean condnDescFlag = lookupCondnDescription(readDescriptionFrmExcel);
					if(condnDescFlag){
						displayOnConsole("testCreateCommType() - Verifying new Committee Type SUCCESSFUL ");
						logPassMessageInExtentTest("testCreateCommType() - Verifying new Committee Type SUCCESSFUL ");
					}
				}
			} else {
				
				logFailMessageInExtentTest("testCreateCommType() - Creating new Committee Type UNSUCCESSFUL ");
			}
			
			driver = driver.switchTo().defaultContent();
			appUtil.logout();
		} else {
					displayOnConsole("testCreateCommType() -  User login UNSUCCESSFUL, Creating new Committee Type UNSUCCESSFUL ");
					logFailMessageInExtentTest("testCreateCommType() - User login UNSUCCESSFUL, Creating new Committee Type UNSUCCESSFUL ");
		}
		
		logInfoMessageInExtentTest(" ********** Ending testing : Committee Type - Create flow *************");
	}
	
	@Test
	public void testDeleteCommType(){
		
		//starting of test 
		extentTest = extent.startTest("testDeleteCommType ");
		
		logInfoMessageInExtentTest(" ********** Starting testing : Committee Type - Delete flow *************");
		
		sheet_name = "CommitteeType";
		
		if(username == null){
			username = getUserName(sheet_name);
			password = getUserPassword(sheet_name);
		}
		
		//read description from test data
		readDescriptionFrmExcel = Excel.readFromExcel(excel_file, sheet_name, SIX, ONE);
				
		AppUtil apputil = new AppUtil();
		if(apputil.login(username, password)){

			displayOnConsole("testDeleteCommType() - User login SUCCESSFUL ");
			logInfoMessageInExtentTest("testDeleteCommType() - User login SUCCESSFUL ");
			
			driver = driver.switchTo().defaultContent();

			boolean noerror = commTypeCreating (txtMsg, readDescriptionFrmExcel, toOpen_Link_InputData, toOpen_Create_NewPage);

			if(noerror == true){

				if (isElementPresent(driver, popUp_Exists_Description)){
						displayOnConsole("testDeleteCommType() - Creating new Committee Type UNSUCCESSFUL ");
						logInfoMessageInExtentTest("testDeleteCommType() - Creating new Committee Type UNSUCCESSFUL ");
						
						String details = "Committee Type : Entity with this Description already exists. Deleting existing one.";
						displayOnConsole("testDeleteCommType() -  "+details);
						
						String screenshot = getScreenshot(driver, "Profile_Condn_Exists");
						Actions actions = new Actions(driver);
						Action action = actions.moveToElement(driver.findElement(popUp_Exists_Close),TWO,ZERO).click().build();
						action.perform();
						logInfoMessageInExtentTest(details);
				} else {
							displayOnConsole("testDeleteCommType() - Creating new Committee Type SUCCESSFUL ");
							logInfoMessageInExtentTest("testDeleteCommType() - Creating new Committee Type SUCCESSFUL ");
				}
				
				//Finding for deleting
				displayOnConsole("testDeleteCommType() - Finding the Committee Type to delete.");
				logInfoMessageInExtentTest("testDeleteCommType() - Finding the Committee Type to delete.");
				
				boolean lookupProfile = lookupCondnDescription(readDescriptionFrmExcel);
				
				if(lookupProfile){

					boolean deletingprofilerole = commTypeDeleting(readDescriptionFrmExcel);
					
					if(deletingprofilerole){
						displayOnConsole("testDeleteCommType() - Deleting Committee Type SUCCESSFUL ");
						logInfoMessageInExtentTest("testDeleteCommType() - Deleting Committee Type SUCCESSFUL ");
						
						displayOnConsole("testDeleteCommType() - Verifying the deleted Committee Type.");
						logInfoMessageInExtentTest("testDeleteCommType() - Verifying the deleted Committee Type.");
						
						//Verifying whether deleted or not.
						lookupProfile =  lookupCondnDescription(readDescriptionFrmExcel);
						if(lookupProfile == false) {
								displayOnConsole("testDeleteCommType() - Verifying for deleting Committee Type SUCCESSFUL ");
								logPassMessageInExtentTest("testDeleteCommType() - Verifying for deleting Committee Type SUCCESSFUL ");
						}
						else {
								displayOnConsole("testDeleteCommType() - Verifying for deleting Committee Type UNSUCCESSFUL ");
								logFailMessageInExtentTest("testDeleteCommType() - Verifying for deleting Committee Type UNSUCCESSFUL ");
						}
					} else {
								displayOnConsole("testDeleteCommType() - deleting Committee Type UNSUCCESSFUL ");
								logFailMessageInExtentTest("testDeleteCommType() - deleting Committee Type UNSUCCESSFUL ");
					}
				} else {
					displayOnConsole("testDeleteCommType() - Search for Committee Type itself UNSUCCESSFUL, so, can't procced to delete. ");
					logFailMessageInExtentTest("testDeleteCommType() - Search for Committee Type itself UNSUCCESSFUL, so, can't procced to delete. ");
				}
				
			} else{
					displayOnConsole("testDeleteCommType() - Creating new Committee Type itself UNSUCCESSFUL, so, can't procced to delete. ");
					logFailMessageInExtentTest("testDeleteCommType() - Creating new Committee Type itself UNSUCCESSFUL, so, can't procced to delete. ");
			}
			
			driver = driver.switchTo().defaultContent();
			apputil.logout();

		}else{
			
			displayOnConsole("testDeleteCommType() - User login UNSUCCESSFUL, deleting Committee Type UNSUCCESSFUL ");
			logFailMessageInExtentTest("testDeleteCommType() - User login UNSUCCESSFUL, deleting Committee Type UNSUCCESSFUL ");
		}
		
		logInfoMessageInExtentTest(" ********** Ending testing : Committee Type - Delete flow *************");
	}

	@Test
	public void testEditCommType(){
		
		//start of test
		extentTest = extent.startTest("testEditCommType ");
		
		logInfoMessageInExtentTest(" ********** Starting testing : Committee Type - Edit flow *************");
		
		//edit Committee Type
		sheet_name = "CommitteeType";
		
		//application username, password
		if(username == null){
			
			username = getUserName(sheet_name);
			password = getUserPassword(sheet_name);
			
		}
		
		//read description from test data
		readDescriptionFrmExcel = Excel.readFromExcel(excel_file, sheet_name, FOUR, ONE);
				
		//read description from test data
		String strReplacement = Excel.readFromExcel(excel_file, sheet_name, FIVE, ONE);
		
		AppUtil apputil = new AppUtil();
		if(apputil.login(username, password)){
			
			displayOnConsole("testEditCommType() - User login SUCCESSFUL ");
			logInfoMessageInExtentTest("testEditCommType() - User login SUCCESSFUL ");
			
			boolean noerror = commTypeCreating (txtMsg, readDescriptionFrmExcel, toOpen_Link_InputData, toOpen_Create_NewPage);
			if(noerror == true){
				
				if(isElementPresent(driver, popUp_Exists_Description)){
					displayOnConsole("testEditCommType() - Creating new Committee Type UNSUCCESSFUL ");
					logInfoMessageInExtentTest("testEditCommType() - Creating new Committee Type UNSUCCESSFUL ");
					
					String details = "Committee Type already exists, editing existing one ";
					displayOnConsole("testEditCommType - "+details);
					
					String screenshot = getScreenshot(driver, "Profile_Role_Exists");
					Actions actions = new Actions(driver);
					Action action = actions.moveToElement(driver.findElement(popUp_Exists_Close),TWO,ZERO).click().build();
					action.perform();
					logInfoMessageInExtentTest("testEditCommType() - " + details);
					
				} else {
						displayOnConsole("testEditCommType() - Creating new Committee Type SUCCESSFUL ");
						logInfoMessageInExtentTest("testEditCommType() - Creating new Committee Type SUCCESSFUL ");
				}
				
				//Finding for editing
				displayOnConsole("testEditCommType() - Finding the Committee Type to Edit.");
				logInfoMessageInExtentTest("testEditCommType() - Finding the Committee Type to Edit.");
				
				boolean lookupProfile = lookupCondnDescription(readDescriptionFrmExcel);
				
				if(lookupProfile){

					boolean profileroleedit = commTypeEditing(readDescriptionFrmExcel, strReplacement);
					
					if(profileroleedit){
						
						if(isElementPresent(driver, popUp_Exists_Description)){
							
							String details = "Committee Type with same name already exists, Editing Committee Type UNSUCCESSFUL ";
							displayOnConsole("testEditCommType() : "+details);
							
							String screenshot = getScreenshot(driver, "Profile_Role_Exists");
							Actions actions = new Actions(driver);
							Action action = actions.moveToElement(driver.findElement(popUp_Exists_Close),TWO,ZERO).click().build();
							action.perform();
							logInfoMessageInExtentTest("testEditCommType() - " + details);
							logFailMessageInExtentTest("testEditCondition() - Editing Condition UNSUCCESSFUL ");
							
						} else {
							displayOnConsole("testEditCommType() - Editing Committee Type SUCCESSFUL ");
							logInfoMessageInExtentTest("testEditCommType() - Editing Committee Type SUCCESSFUL ");
							
							displayOnConsole("testEditCommType() - Verifying the Edited Committee Type.");
							logInfoMessageInExtentTest("testEditCommType() - Verifying the Edited Committee Type.");
							
							//Verifying whether edited or not.
							lookupProfile = lookupCondnDescription(strReplacement);
							if(lookupProfile == true) {
								displayOnConsole("testEditCommType() - Verifying Edited Committee Type SUCCESSFUL ");
								logPassMessageInExtentTest("testEditCommType() - Verifing Edited Committee Type SUCCESSFUL ");
							}
							else {
								displayOnConsole("testEditCommType() - Verifying Edited Committee Type UNSUCCESSFUL ");
								logFailMessageInExtentTest("testEditCommType() - Edited Committee Type UNSUCCESSFUL ");
							}	
						}
					} else{
								displayOnConsole("testEditCommType() - Editing Committee Type UNSUCCESSFUL ");
								logPassMessageInExtentTest("testEditCommType() - Editing Committee Type UNSUCCESSFUL ");
					}
				} else {
							displayOnConsole("testEditCommType() - Search for Committee Type itself UNSUCCESSFUL, so, can't procced to edit. ");
							logFailMessageInExtentTest("testEditCommType() - Search for Committee Type itself UNSUCCESSFUL, so, can't procced to edit. ");
				}
				
			} else {
						displayOnConsole("testEditCommType() - Creating new Committee Type itself UNSUCCESSFUL, so, can't procced to edit. ");
						logFailMessageInExtentTest("testEditCommType() - Creating new Committee Type itself UNSUCCESSFUL, so, can't procced to edit. ");
			}
			
			driver = driver.switchTo().defaultContent();
			apputil.logout();

		} else{
			
			displayOnConsole("testEditCommType() - User login UNSUCCESSFUL, Editing Committee Type UNSUCCESSFUL ");
			logFailMessageInExtentTest("testEditCommType() - User login UNSUCCESSFUL, Editing Committee Type UNSUCCESSFUL ");
		}
		
		logInfoMessageInExtentTest(" ********** Ending testing : Committee Type - Edit flow *************");
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
		Assert.fail(details);
	}
	
	void logPassMessageInExtentTest(String details) {
		extentTest.log(LogStatus.PASS, details);
	}

	void logInfoMessageInExtentTest(String details) {
		extentTest.log(LogStatus.INFO, details);
	}
	
	boolean commTypeCreating (String txtMsg, String readDescriptionFrmExcel, By toOpen_Link_InputData, By toOpen_Create_NewPage) {
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
			
			displayOnConsole("commTypeCreating() -"+txtMsg);
			
			//go to internal html
			//driver = driver.switchTo().frame(driver.findElement(tool_frame));
			
			wait(ONE);
			//click Committee Type link
			click(toOpen_Link_InputData);
			//wait
			wait(ONE);
			
			displayOnConsole("commTypeCreating() - Description read from excel-sheet '" + sheet_name +"' is '" +readDescriptionFrmExcel + "'");
			
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
			logFailMessageInExtentTest("commTypeCreating() :: Problem : "+ problem.fillInStackTrace());
		}
		
		return noerror;
	}
	
	boolean commTypeDeleting(String readDescriptionFrmExcel) {
		boolean deleteCondition = true;
		
		try {
			
			//delete of Condition description
			readDescriptionFrmExcel = readDescriptionFrmExcel.trim();
			
			displayOnConsole("Deleting Condition : "+ readDescriptionFrmExcel);
			
			tools_condn_text_delete = By.xpath("(//table[@id='cu']/descendant::div[@id='highlightkeyword'][contains(text(),'"+ readDescriptionFrmExcel +"')]/parent::td/preceding-sibling::td/parent::tr/td["+FIVE+"]/a["+ONE+"]/img)");
			tools_condn_text_checkbox = By.xpath("(//table[@id='cu']/descendant::div[@id='highlightkeyword'][contains(text(),'"+ readDescriptionFrmExcel +"')]/parent::td/preceding-sibling::td/descendant::input[@type='checkbox'])");
			
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
			logInfoMessageInExtentTest("commTypeDeleting() :: Problem : "+ problem.getMessage());
		}
		
		return deleteCondition;
	}
	
	boolean commTypeEditing(String readDescriptionFrmExcel, String strReplacement ) {
		boolean EditCondition = true;
		
		try {
			
			//delete description
			readDescriptionFrmExcel = readDescriptionFrmExcel.trim();
			tools_condn_text_edit = By.xpath("(//table[@id='cu']/descendant::div[@id='highlightkeyword'][contains(text(),'"+ readDescriptionFrmExcel +"')]/parent::td/preceding-sibling::td/parent::tr//*[self::td]["+FIVE+"]/a["+TWO+"]/img)");
			click(tools_condn_text_edit);
			wait(ONE);
			
			//enter new description
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

			EditCondition = false;
			logInfoMessageInExtentTest("Problem " + problem.fillInStackTrace());
			
		}

		return EditCondition;
	}
	
	boolean lookupCondnDescription(String readDescriptionFrmExcel) {
		
		boolean condnFlag = false;
		
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
			logInfoMessageInExtentTest("lookupCondnDescription() :: problem : " + problem.getMessage());
		}
		
		displayOnConsole("lookupCondnDescription() - Total pages : "+navigationoptions.size());
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
			displayOnConsole("lookupCondnDescription() - Searched profile found at page no. : "+filter);
		
		if(navigationoptions.size() == ONE)
		{
			condnFlag = isElementPresent(driver, condnDesc_text);
		}
		
		return condnFlag;
	}

}
