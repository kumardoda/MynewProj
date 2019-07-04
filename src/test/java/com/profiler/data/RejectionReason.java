package com.profiler.data;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.profiler.utils.AppUtil;
import com.profiler.utils.Excel;
import com.profiler.utils.Functions;
import com.relevantcodes.extentreports.LogStatus;

public class RejectionReason extends Functions {
	
	byte ZERO = 0;
	byte ONE = 1;
	byte TWO = 2;
	byte THREE = 3;
	byte FOUR = 4;
	byte FIVE = 5;
	byte SIX = 6;
	byte SEVEN = 7;
	byte EIGHT = 8;
	byte NINE = 9;
	
	String SEPERATOR = ",";
			
	String username = null;
	String password = null;
	String excel_file = "files//TestData.xlsx";
	String sheet_name = null;
	
	String strTarget="";
	By lookUp_entity = By.xpath("(//*[@id='highlightkeyword'][contains(text(),'"+strTarget+"')])");
	
	By tools_filterWithIntials_select = By.xpath("//select[@id='jumpTo']");
	By tools_condn_page_select = By.xpath("//select[@id='bbox']");
	
	By tool_frame = By.xpath("(//*[@id='toolsContent'])");
	
	By toOpen_Link_InputData = By.xpath("(//*[@id='container']/table/descendant::div[@id='content']/table/descendant::div[@id='leftDiv']/descendant::a[contains(text(),'Rejection Reasons')])");
	By toOpen_Create_NewPage = By.xpath("(//*[@id=\"menu\"]/li/a/span[1]//img[@src='/newimages/icons/new.gif'])");
	
	By roleDropDown = By.xpath("//*[@id='roleId_dd_label']");
	
	By popUp_Exists_Description = By.xpath("(//*[@id='messageDiv']/descendant::div[contains(text(),'Entity with this Description already exists')])");
	By popUp_Exists_Close = By.xpath("(//*[@id='resultDiv']/div[@id='messageDiv']/descendant::a[@title='close']["+ONE+"])");
	
	//Create
	By toInput_Data_txtBox = By.xpath("(//*[@id='profilerPopupDiv']/descendant::input[@id='description'])");
	By toSave_Create_NewPage = By.xpath("(//*[@id='profilerPopupDiv']/div[@id='profilerPopupMenu']/table/descendant::a/img[@alt='Save'])");
	
	//Delete
	By tools_condn_text_delete = By.xpath("//*[@id='highlightkeyword'][contains(text(),'"+ strTarget +"')]/parent::td/preceding-sibling::td/parent::tr//*[self::td]["+FIVE+"]/a["+ONE+"]");
	By tools_condn_text_checkbox = By.xpath("//*[@id='highlightkeyword'][contains(text(),'"+strTarget+"')]/parent::td/preceding-sibling::td/descendant::input[@type='checkbox']");
	
	String deletePopUpTextMsg = "Delete 1 Rejection Reason?";
	String delCondnProcessingMsgText = "Your request is in queue. For more details check \"Profile Data Management->Realtime Mass Update tracker\" screen";
	
	//Edit
	By tools_condn_text_edit = By.xpath("//*[@id='highlightkeyword'][contains(text(),'"+ strTarget +"')]/parent::td/preceding-sibling::td/parent::tr//*[self::td]["+FIVE+"]/a["+TWO+"]");
	By condn_new_box_input = By.xpath("(//*[@id='profilerPopupDiv']/descendant::input[@id='description'])");
	By condn_new_box_save = By.xpath("(//*[@id='profilerPopupDiv']/div[@id='profilerPopupMenu']/table/descendant::a/img[@alt='Save'])");
	
	String txtMsg = "Adding new Rejection Reason ";
	List<String> roleList = new LinkedList<String>();
		
	@Test
	public void testCreateRejectionReason(){
		
		extentTest = extent.startTest("testCreateRejectionReason");
		logInfoMessageInExtentTest(" ********** Starting testing : RejectionReason - Create flow *************");
		
		sheet_name = "RejectionReasons";
		
		if(username == null){
			username = getUserName(sheet_name);
			password = getUserPassword(sheet_name);
		}
		
		//Rejection Reason - Name : For Creation
		strTarget = Excel.readFromExcel(excel_file, sheet_name, FOUR, ONE);
				
		//Rejection Reason - Role : For Creation
		String addRoles = Excel.readFromExcel(excel_file, sheet_name, FIVE, ONE);
		
		List<String> roleList = new LinkedList<String>();
		
		if(addRoles != null) {
			if(addRoles.contains(SEPERATOR))
				roleList = Arrays.asList(addRoles.split(","));
			else
				roleList.add(addRoles);
		}
		
		AppUtil appUtil = new AppUtil();
		if(appUtil.login(username, password)){
			
			displayOnConsole("testCreateRejectionReason() - User login SUCCESSFUL ");
			logInfoMessageInExtentTest(" ********** User Login Successful *************");
			
			driver = driver.switchTo().window(driver.getWindowHandle());
			
			wait(ONE);
			
			boolean noerror = rejectionReasonCreating (txtMsg, strTarget, roleList, toOpen_Link_InputData, toOpen_Create_NewPage);
			
			if(noerror == true){
				if(isElementPresent(driver, popUp_Exists_Description)){
					displayOnConsole("testCreateRejectionReason() - Creating new Rejection Reason UNSUCCESSFUL ");
					//logFailMessageInExtentTest("testCreateRejectionReason() - Creating new Rejection Reason UNSUCCESSFUL ");
					
					String details = "Rejection Reason : Entity with this Description already exists. Please, give another description ";
					displayOnConsole("testCreateRejectionReason() -  "+details);
					
					String screenshot = getScreenshot(driver, "Profile_Condn_Exists");
					Actions actions = new Actions(driver);
					Action action = actions.moveToElement(driver.findElement(popUp_Exists_Close),TWO,ZERO).click().build();
					action.perform();
					logInfoMessageInExtentTest(details);
					
					//added
					lookupDataOptionDescription(strTarget);
					boolean deleting = rejectionReasonDeleting(strTarget);
					if(deleting == true){
						logPassMessageInExtentTest("Rejection Reason before re-creating deleted ");
					}
					logInfoMessageInExtentTest(details);
					noerror = rejectionReasonCreating (txtMsg, strTarget, roleList, toOpen_Link_InputData, toOpen_Create_NewPage);
				} else {
					displayOnConsole("testCreateRejectionReason() - Creating new Rejection Reason SUCCESSFUL ");
					logPassMessageInExtentTest("testCreateRejectionReason() - Creating new Rejection Reason SUCCESSFUL ");
					
					//Verifying for the added Condition
					displayOnConsole("testCreateRejectionReason() - Verifying the new added Rejection Reason.");
					logInfoMessageInExtentTest("testCreateRejectionReason() - Verifying new added Rejection Reason. ");
					
					boolean condnDescFlag = lookupDataOptionDescription(strTarget);
					if(condnDescFlag){
						displayOnConsole("testCreateRejectionReason() - Verifying new Rejection Reason SUCCESSFUL ");
						logPassMessageInExtentTest("testCreateRejectionReason() - Verifying new Rejection Reason SUCCESSFUL ");
					}
				}
			} else {
						logFailMessageInExtentTest("testCreateRejectionReason() - Creating new Rejection Reason UNSUCCESSFUL ");
			}
			
			driver = driver.switchTo().defaultContent();
			appUtil.logout();
		} else {
					displayOnConsole("testCreateRejectionReason() -  User login UNSUCCESSFUL, Creating new Rejection Reason UNSUCCESSFUL ");
					logFailMessageInExtentTest("testCreateRejectionReason() - User login UNSUCCESSFUL, Creating new Rejection Reason UNSUCCESSFUL ");
		}
		logInfoMessageInExtentTest(" ********** Ending testing : Rejection Reason : Create Flow *************");
	}
	
	@Test
	public void testDeleteRejectionReason(){
		
		//starting of test 
		extentTest = extent.startTest("testDeleteRejectionReason ");
		logInfoMessageInExtentTest(" ********** Starting testing : Rejection Reason - Delete flow *************");
		
		sheet_name = "RejectionReasons";
		
		if(username == null){
			username = getUserName(sheet_name);
			password = getUserPassword(sheet_name);
		}
		
		//Rejection Reason - Name : For Creation
		String strToDelete = Excel.readFromExcel(excel_file, sheet_name, EIGHT, ONE);
				
		//Rejection Reason - Role : For Creation
		String addRoles = Excel.readFromExcel(excel_file, sheet_name, NINE, ONE);
		
		List<String> roleList = new LinkedList<String>();
		
		if(addRoles != null) {
			if(addRoles.contains(SEPERATOR))
				roleList = Arrays.asList(addRoles.split(","));
			else
				roleList.add(addRoles);
		}
		
		AppUtil apputil = new AppUtil();
		if(apputil.login(username, password)){

			displayOnConsole("testDeleteRejectionReason() - User login SUCCESSFUL ");
			logInfoMessageInExtentTest(" ********** User Login Successful *************");
			
			driver = driver.switchTo().defaultContent();

			boolean noerror = rejectionReasonCreating (txtMsg, strToDelete, roleList, toOpen_Link_InputData, toOpen_Create_NewPage);

			if(noerror == true){

				if (isElementPresent(driver, popUp_Exists_Description)){
						displayOnConsole("testDeleteRejectionReason() - Creating new Rejection Reason UNSUCCESSFUL ");
						//logInfoMessageInExtentTest("testDeleteRejectionReason() - Creating new Rejection Reason UNSUCCESSFUL ");
						
						String details = "Rejection Reason : Entity with this Description already exists. Deleting existing one.";
						displayOnConsole("testDeleteRejectionReason() -  "+details);
						
						String screenshot = getScreenshot(driver, "Profile_Condn_Exists");
						Actions actions = new Actions(driver);
						Action action = actions.moveToElement(driver.findElement(popUp_Exists_Close),TWO,ZERO).click().build();
						action.perform();
						logInfoMessageInExtentTest(details);
						
						//added
						lookupDataOptionDescription(strTarget);
						boolean deleting = rejectionReasonDeleting(strTarget);
						if(deleting == true){
							logPassMessageInExtentTest("Rejection Reason before re-creating deleted ");
						}
						logInfoMessageInExtentTest(details);
						noerror = rejectionReasonCreating (txtMsg, strTarget, roleList, toOpen_Link_InputData, toOpen_Create_NewPage);
				} else {
							displayOnConsole("testDeleteRejectionReason() - Creating new Rejection Reason SUCCESSFUL ");
							logInfoMessageInExtentTest("testDeleteInflncType() - Creating new Rejection Reason SUCCESSFUL ");
				}
				
				//Finding the Entity for deleting
				displayOnConsole("testDeleteRejectionReason() - Finding the Rejection Reason to delete.");
				logInfoMessageInExtentTest("testDeleteRejectionReason() - Finding the Rejection Reason to delete.");
				boolean lookupProfile = lookupDataOptionDescription(strToDelete);
				
				if(lookupProfile){

					boolean deletingprofilerole = rejectionReasonDeleting(strToDelete);
					
					if(deletingprofilerole){
						displayOnConsole("testDeleteRejectionReason() - Deleting Rejection Reason SUCCESSFUL ");
						logInfoMessageInExtentTest("testDeleteInflncType() - Deleting Rejection Reason SUCCESSFUL ");
						
						displayOnConsole("testDeleteRejectionReason() - Verifying the deleted Rejection Reason.");
						logInfoMessageInExtentTest("testDeleteRejectionReason() - Verifying the deleted Rejection Reason.");
						
						//Verifying whether deleted or not.
						lookupProfile =  lookupDataOptionDescription(strToDelete);
						if(lookupProfile == false) {
								displayOnConsole("testDeleteRejectionReason() - Verifying for deleting Rejection Reason SUCCESSFUL ");
								logPassMessageInExtentTest("testDeleteRejectionReason() - Verifying for deleting Rejection Reason SUCCESSFUL ");
						}
						else {
								displayOnConsole("testDeleteRejectionReason() - Verifying for deleting Rejection Reason UNSUCCESSFUL ");
								logFailMessageInExtentTest("testDeleteRejectionReason() - Verifying for deleting Rejection Reason UNSUCCESSFUL ");
						}
					} else {
								displayOnConsole("testDeleteRejectionReason() - Deleting Rejection Reason UNSUCCESSFUL ");
								logFailMessageInExtentTest("testDeleteRejectionReason() - Deleting Rejection Reason UNSUCCESSFUL ");
					}
				} else {
					displayOnConsole("testDeleteRejectionReason() - Search for Rejection Reason itself UNSUCCESSFUL, so, can't procced to delete. ");
					logFailMessageInExtentTest("testDeleteRejectionReason() - Search for Rejection Reason itself UNSUCCESSFUL, so, can't procced to delete. ");
				}
				
			} else{
					displayOnConsole("testDeleteRejectionReason() - Creating new Rejection Reason itself UNSUCCESSFUL, so, can't procced to delete. ");
					logFailMessageInExtentTest("testDeleteRejectionReason() - Creating new Rejection Reason itself UNSUCCESSFUL, so, can't procced to delete. ");
			}
			
			driver = driver.switchTo().defaultContent();
			apputil.logout();

		}else{
			  	displayOnConsole("testDeleteRejectionReason() - User login UNSUCCESSFUL, deleting Rejection Reason UNSUCCESSFUL ");
			  	logFailMessageInExtentTest("testDeleteRejectionReason() - User login UNSUCCESSFUL, deleting Rejection Reason UNSUCCESSFUL ");
		}
		
		logInfoMessageInExtentTest(" ********** Ending testing : Rejection Reason - Delete flow *************");
	}

	@Test
	public void testEditRejectionReason(){
		
		//start of test
		extentTest = extent.startTest("testEditRejectionReason ");
		logInfoMessageInExtentTest(" ********** Starting testing : Rejection Reason - Edit flow *************");
		
		sheet_name = "RejectionReasons";
		
		//application username, password
		if(username == null){
			username = getUserName(sheet_name);
			password = getUserPassword(sheet_name);
		}
		
		/*Reading data from excel-data sheet - "RejectionReason" */
		
		//Rejection Reason - Name : For Creation
		strTarget = Excel.readFromExcel(excel_file, sheet_name, FOUR, ONE);
		
		//Rejection Reason - Role : For Creation
		String addRoles = Excel.readFromExcel(excel_file, sheet_name, FIVE, ONE);
		
		List<String> roleList = new LinkedList<String>();
				
		if(addRoles != null) {
			if(addRoles.contains(SEPERATOR))
				roleList = Arrays.asList(addRoles.split(","));
			else
				roleList.add(addRoles);
		} else {
			roleList.add("");
		}
		
		
		//Rejection Reason - New name : For Editing
		String strReplacement = Excel.readFromExcel(excel_file, sheet_name, SIX, ONE);
				
		AppUtil apputil = new AppUtil();
		if(apputil.login(username, password)){
			
			displayOnConsole("testEditRejectionReason() - User login SUCCESSFUL ");
			logInfoMessageInExtentTest(" ********** User Login Successful *************");
			
			boolean noerror = rejectionReasonCreating (txtMsg, strTarget, roleList, toOpen_Link_InputData, toOpen_Create_NewPage);
			if(noerror == true){
				
				if(isElementPresent(driver, popUp_Exists_Description)){
					displayOnConsole("testEditRejectionReason() - Creating new Rejection Reason UNSUCCESSFUL ");
					//logInfoMessageInExtentTest("testEditRejectionReason() - Creating new Rejection Reason UNSUCCESSFUL ");
					
					String details = "Rejection Reason already exists, editing existing one ";
					displayOnConsole("testProfileRoleEditing - "+details);
					
					String screenshot = getScreenshot(driver, "Profile_Role_Exists");
					Actions actions = new Actions(driver);
					Action action = actions.moveToElement(driver.findElement(popUp_Exists_Close),TWO,ZERO).click().build();
					action.perform();
					logInfoMessageInExtentTest("testEditRejectionReason() - " + details);
					//added
					lookupDataOptionDescription(strTarget);
					boolean deleting = rejectionReasonDeleting(strTarget);
					if(deleting == true){
						logPassMessageInExtentTest("Rejection Reason before re-creating deleted ");
					}
					logInfoMessageInExtentTest(details);
					noerror = rejectionReasonCreating (txtMsg, strTarget, roleList, toOpen_Link_InputData, toOpen_Create_NewPage);
				
				} else {
						displayOnConsole("testEditRejectionReason() - Creating new Rejection Reason SUCCESSFUL ");
						logInfoMessageInExtentTest("testEditRejectionReason() - Creating new Rejection Reason SUCCESSFUL ");
				}
				
				//Finding for editing
				displayOnConsole("testEditRejectionReason() - Finding the Rejection Reason to Edit.");
				boolean lookupProfile = lookupDataOptionDescription(strTarget);
				
				if(lookupProfile){
					displayOnConsole("testEditRejectionReason() - Finding the Rejection Reason to Edit SUCCESSFUL.");
					logInfoMessageInExtentTest("testEditRejectionReason() - Finding the Rejection Reason to Edit SUCCESSFUL.");
					
					boolean profileroleedit = rejectionReasonEditing(strTarget, strReplacement);
					
					if(profileroleedit){
						
						if(isElementPresent(driver, popUp_Exists_Description)){
							
							String details = "Rejection Reason with same name already exists, Editing Rejection Reason UNSUCCESSFUL ";
							displayOnConsole("testEditRejectionReason() : "+details);
							
							String screenshot = getScreenshot(driver, "Profile_Role_Exists");
							Actions actions = new Actions(driver);
							Action action = actions.moveToElement(driver.findElement(popUp_Exists_Close),TWO,ZERO).click().build();
							action.perform();
							logInfoMessageInExtentTest("testEditRejectionReason() - " + details);
							logFailMessageInExtentTest("testEditRejectionReason() - Editing Condition UNSUCCESSFUL ");							
						} else {
							displayOnConsole("testEditRejectionReason() - Editing Rejection Reason SUCCESSFUL ");
							logInfoMessageInExtentTest("testEditRejectionReason() - Editing Rejection Reason SUCCESSFUL ");
							
							displayOnConsole("testEditRejectionReason() - Verifying the Edited Rejection Reason.");
							logInfoMessageInExtentTest("testEditRejectionReason() - Verifying the Edited Rejection Reason.");
							
							//Verifying whether edited or not.
							lookupProfile = lookupDataOptionDescription(strReplacement);
							if(lookupProfile == true) {
								displayOnConsole("testEditRejectionReason() - Verifying Edited Rejection Reason SUCCESSFUL ");
								logPassMessageInExtentTest("testEditRejectionReason() - Verifing Edited Rejection Reason SUCCESSFUL ");
							}
							else {
								displayOnConsole("testEditRejectionReason() - Verifying Edited Rejection Reason UNSUCCESSFUL ");
								logFailMessageInExtentTest("testEditRejectionReason() - Edited Rejection Reason UNSUCCESSFUL ");
							}	
						}
					} else{
								displayOnConsole("testEditRejectionReason() - Editing Rejection Reason UNSUCCESSFUL ");
								logPassMessageInExtentTest("testEditRejectionReason() - Editing Rejection Reason UNSUCCESSFUL ");
					}
				} else {
							displayOnConsole("testEditRejectionReason() - Search for Rejection Reason itself UNSUCCESSFUL, so, can't procced to edit. ");
							logFailMessageInExtentTest("testEditRejectionReason() - Search for Rejection Reason itself UNSUCCESSFUL, so, can't procced to edit. ");
				}
				
			} else {
						displayOnConsole("testEditRejectionReason() - Creating new Rejection Reason itself UNSUCCESSFUL, so, can't procced to edit. ");
						logFailMessageInExtentTest("testEditRejectionReason() - Creating new Rejection Reason itself UNSUCCESSFUL, so, can't procced to edit. ");
			}
			
			driver = driver.switchTo().defaultContent();
			apputil.logout();

		} else{
			
			displayOnConsole("testEditRejectionReason() - User login UNSUCCESSFUL, Editing Rejection Reason UNSUCCESSFUL ");
			logFailMessageInExtentTest("testEditRejectionReason() - User login UNSUCCESSFUL, Editing Rejection Reason UNSUCCESSFUL ");
		}
		
		logInfoMessageInExtentTest(" ********** Ending testing : Rejection Reason - Edit flow *************");
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
	
	boolean rejectionReasonCreating (String txtMsg, String strTarget, List<String> roleList, By toOpen_Link_InputData, By toOpen_Create_NewPage) {
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
			AppUtil apputil = new AppUtil();
			boolean tools_page_shown = apputil.gotoToolsPage();//Added
			if(tools_page_shown == false){//Added
				Assert.fail("Tools page failed to load");
				getScreenshot(driver, "ToolsPageLoading_Failed");
			}
			wait(ONE);
			
			displayOnConsole("rejectionReasonCreating() - "+txtMsg);
			logInfoMessageInExtentTest(" ********** " + txtMsg + "*************");
			
			//go to internal html
			//driver = driver.switchTo().frame(driver.findElement(tool_frame));
			
			wait(ONE);
			//click Rejection Reason link
			click(toOpen_Link_InputData);
			//wait
			wait(ONE);
			
			displayOnConsole("rejectionReasonCreating() - Description read from excel-sheet '" + sheet_name +"' is '" +strTarget + "'");
			logInfoMessageInExtentTest(" rejectionReasonCreating() - Description read from excel-sheet '" + sheet_name +"' is '" +strTarget + "'");
			//click to open new Rejection Reason popup
			click(toOpen_Create_NewPage);
			wait(ONE);
			
			//Enter Name
			click(toInput_Data_txtBox);
			driver.findElement(toInput_Data_txtBox).clear();
			enter(toInput_Data_txtBox, strTarget);
			
			WebElement dropDown = driver.findElement(By.xpath("//*[@id='roleId_dd_main']//div[contains(text(),'Select Role')]"));
			dropDown.click();
			
			//Enter Roles
			int roleAddCount = roleList.size();
			
			List<WebElement> chk = driver.findElements(By.xpath("//*[@id='roleId_dd_options']//input[@type='checkbox'] "));
			int totalChkBoxOptions = chk.size();
			
			WebDriverWait wait = new WebDriverWait(driver,300);
			for (int i = 0, j = 0 ; (i < totalChkBoxOptions && j < roleAddCount ); i++) {
				 //System.out.println("roleAddCount :: "+ j);
			    WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(
			    		driver.findElements(By.name("roleId")).get(i)));
			    String roleElement = driver.findElements(By.name("roleId")).get(i).getAttribute("id").toString();
			      if (!ele.isSelected() && roleList.contains(roleElement)) {
			    	   j++;
			           ele.click();
			       }
			 }
			
			//click save
			click(toSave_Create_NewPage);
			wait(TWO);
			
		} catch (Exception problem) {
			noerror=false;
			logFailMessageInExtentTest("rejectionReasonCreating() :: Problem : "+ problem.fillInStackTrace());
		}
		
		return noerror;
	}
	
	boolean rejectionReasonDeleting(String strToDelete) {
		boolean deleteDataOptionFlag = true;
		
		try {
			
			//Entity : to Delete
			strToDelete = strToDelete.trim();
			
			displayOnConsole("rejectionReasonDeleting() - Deleting Entity : "+ strToDelete);
			logInfoMessageInExtentTest("rejectionReasonDeleting() - Deleting Entity : "+ strToDelete);
			
			tools_condn_text_delete = By.xpath("(//*[@id='highlightkeyword'][contains(text(),'"+ strToDelete +"')]/parent::td/preceding-sibling::td/parent::tr/td["+SIX+"]/a["+ONE+"]/img)");
			tools_condn_text_checkbox = By.xpath("(//*[@id='highlightkeyword'][contains(text(),'"+ strToDelete +"')]/parent::td/preceding-sibling::td/descendant::input[@type='checkbox'])");
			
			//Selecting checkbox
			click(tools_condn_text_checkbox);
			wait(ONE);
			
			//Clicking Delete image
			click(tools_condn_text_delete);
			wait(ONE);
			
			//Capturing delete text msg from delete pop-up
			Alert alertbox = driver.switchTo().alert();
			String alertmessagetext = alertbox.getText();
			boolean textcontent = alertmessagetext.contains(deletePopUpTextMsg);
			if(textcontent){
				logInfoMessageInExtentTest("Delete pop-up message : " + deletePopUpTextMsg);
			}
			
			alertbox.accept();
			wait(TWO);
						
		} catch (Exception problem) {
			
			deleteDataOptionFlag = false;
			logInfoMessageInExtentTest("rejectionReasonDeleting() :: Problem : "+ problem.getMessage());
		}
		
		return deleteDataOptionFlag;
	}
	
	boolean rejectionReasonEditing(String strTarget, String strReplacement) {
		boolean editDataOptionFlag = true;
		
		try {
			
			displayOnConsole("rejectionReasonEditing() - Editing Entity : "+ strTarget);
			logInfoMessageInExtentTest("rejectionReasonEditing() - Editing Entity : "+ strTarget);
			
			//Checking before editing
			boolean lookup = lookupDataOptionDescription(strReplacement);//Added
			logInfoMessageInExtentTest("Checking Rejection Reason before editing ");//Added
			if(lookup){//Added
				logInfoMessageInExtentTest("Rejection Reason before editing was exist, deleting ");
				boolean deleting = rejectionReasonDeleting(strReplacement);
				if(deleting == true){
					logInfoMessageInExtentTest("Rejection Reason before editing deleted ");
				}
			}//Added
			
			
			//Rejection Reason - New Roles : For Editing
			String editRoles = Excel.readFromExcel(excel_file, sheet_name, SEVEN, ONE);
			
			List<String> editRoleList = new LinkedList<String>();
			
			if(editRoles != null) {
				if(editRoles.contains(SEPERATOR))
					editRoleList = Arrays.asList(editRoles.split(","));
				else
					editRoleList.add(editRoles);
			}	
			
			
			//Editing existing Condition description
			strTarget = strTarget.trim();
			tools_condn_text_edit = By.xpath("(//*[@id='highlightkeyword'][contains(text(),'"+ strTarget +"')]/parent::td/preceding-sibling::td/parent::tr//*[self::td]["+SIX+"]/a["+TWO+"]/img)");
			click(tools_condn_text_edit);
			wait(ONE);
			
			//enter profile role description
			click(condn_new_box_input);
			driver.findElement(condn_new_box_input).clear();
			enter(condn_new_box_input, strReplacement);
			
			//Role
			WebElement dropDown = driver.findElement(By.xpath("//*[@id='roleId_dd_main']//div[contains(@caption,'Select Role')]"));
			dropDown.click();
			
			int roleEditCount = editRoleList.size();
			
			// Nothing to be changed while editing i.e. editing for role field should be ignored.
			if(editRoleList.size() == 0) {
				dropDown.click();
			} else { 
				//Editing the old roles
				
				List<WebElement> chk = driver.findElements(By.xpath("//*[@id='roleId_dd_options']//input[@type='checkbox'] "));
				int totalChkBoxOptions = chk.size();
			
				WebDriverWait wait = new WebDriverWait(driver,300);
				//for (int i = 0, j = roleEditCount ; (i < totalChkBoxOptions && j > 0 ); i++) {
				for (int i = 0 ; i < totalChkBoxOptions ; i++) {
				    WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(
				    		driver.findElements(By.name("roleId")).get(i)));
				    
				    String roleElement = driver.findElements(By.name("roleId")).get(i).getAttribute("id").toString();
				    
				    //Adding the new roles
				    if (!ele.isSelected() && editRoleList.contains(roleElement))
			        { 
				    	//j-- ;
				    	ele.click(); 
			        } // Removing the existing one as not required after edit 
				    else if (ele.isSelected() && !editRoleList.contains(roleElement) ) {
				    	//j-- ;
			        	ele.click();
			        } else if (ele.isSelected() && editRoleList.contains(roleElement) ) {
			        	//j-- ;
			        }
				}
			}
			//click save
			click(condn_new_box_save);
			wait(ONE);
						
		} catch (Exception problem) {

			editDataOptionFlag = false;
			logInfoMessageInExtentTest("inflnTypeEditing :: Problem " + problem.fillInStackTrace());
			
		}

		return editDataOptionFlag;
	}
	
	boolean lookupDataOptionDescription(String lookForString) {
		
		boolean lookUpDataFlag = false;
		
		String filter = String.valueOf(lookForString.charAt(ZERO)).toUpperCase();
		
		boolean selectvalue = selectByValue(driver, tools_filterWithIntials_select, filter);
		wait(TWO);
		
		lookForString = lookForString.trim();
		lookUp_entity = By.xpath("//*[@id='highlightkeyword'][contains(text(),'"+lookForString+"')]");
		
	
		filter = String.valueOf(ONE);
		
		List<WebElement> navigationoptions = null;
		try
		{
			navigationoptions = driver.findElements(By.xpath("//select[@id='bbox']//*[self::option]"));
		}catch(Exception problem){
			logInfoMessageInExtentTest("lookupDataOptionDescription() :: problem : " + problem.getMessage());
		}
		
		//displayOnConsole("lookupDataOptionDescription() - Total pages : "+navigationoptions.size());
		for(byte pages = TWO; pages <= navigationoptions.size(); pages++){
			if((lookUpDataFlag = isElementPresent(driver, lookUp_entity)) == true){
				break;
			}else{
				click(By.xpath("//table[@id='cu']/thead/tr["+ONE+"]/td["+TWO+"]/a"));
				lookUpDataFlag = isElementPresent(driver, lookUp_entity);
				if(lookUpDataFlag == true)
					break;
			}
			
			filter = String.valueOf(pages);
			selectvalue = selectByValue(driver, tools_condn_page_select, filter);
			wait(ONE);
		}
		
		if(lookUpDataFlag == true)
			displayOnConsole("lookupDataOptionDescription() - Searched profile found at page no. : "+filter);
		
		if(navigationoptions.size() == ZERO || navigationoptions.size() == ONE)
		{
			lookUpDataFlag = isElementPresent(driver, lookUp_entity);
		}
		
		return lookUpDataFlag;
	}

}
