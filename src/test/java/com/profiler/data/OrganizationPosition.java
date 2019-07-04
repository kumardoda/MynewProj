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

public class OrganizationPosition extends Functions {
	
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
	By toOpen_Link_InputData = By.xpath("(//*[@id='container']/table/descendant::div[@id='content']/table/descendant::div[@id='leftDiv']/descendant::a[contains(text(),'Organization Position')])");
	By toOpen_Create_NewPage = By.xpath("(//*[@id='menuTable']/descendant::a[starts-with(@href,'javascript:newOrganizationPosition')])");
	
	By popUp_Exists_Description = By.xpath("(//*[@id='messageDiv']/descendant::div[contains(text(),'Entity with this Description already exists')])");
	By popUp_Exists_Close = By.xpath("(//*[@id='resultDiv']/div[@id='messageDiv']/descendant::a[@title='close']["+ONE+"])");
	
	//Create
	By toInput_Data_txtBox = By.xpath("(//*[@id='profilerPopupDiv']/descendant::input[@id='description'])");
	By toSave_Create_NewPage = By.xpath("(//*[@id='profilerPopupDiv']/div[@id='profilerPopupMenu']/table/descendant::a/img[@alt='Save'])");
	
	//Delete
	By tools_condn_text_delete = By.xpath("//*[@id='highlightkeyword'][contains(text(),'"+ readDescriptionFrmExcel +"')]/parent::td/preceding-sibling::td/parent::tr//*[self::td]["+FIVE+"]/a["+ONE+"]");
	By tools_condn_text_checkbox = By.xpath("//*[@id='highlightkeyword'][contains(text(),'"+readDescriptionFrmExcel+"')]/parent::td/preceding-sibling::td/descendant::input[@type='checkbox']");
	
	String deleteCondnTextMsg = "Delete 1 Organization Position?";
	String delCondnProcessingMsgText = "Your request is in queue. For more details check \"Profile Data Management->Realtime Mass Update tracker\" screen";
	
	//Edit
	By tools_condn_text_edit = By.xpath("//*[@id='highlightkeyword'][contains(text(),'"+ readDescriptionFrmExcel +"')]/parent::td/preceding-sibling::td/parent::tr//*[self::td]["+FIVE+"]/a["+TWO+"]");
	By condn_new_box_input = By.xpath("(//*[@id='profilerPopupDiv']/descendant::input[@id='description'])");
	By condn_new_box_save = By.xpath("(//*[@id='profilerPopupDiv']/div[@id='profilerPopupMenu']/table/descendant::a/img[@alt='Save'])");
	
	String txtMsg = "Adding new Organization Position ";
	
	String application_sheet = "Application";//Added
	
	@Test
	public void testCreateOrgPosition(){
		
		extentTest = extent.startTest("testCreateOrgPosition");
		sheet_name = "OrgPosition";
		
		if(username == null){
			username = getUserName();//Changed
			password = getUserPassword();//Changed
		}
		
		//read OrganizationPosition description from test data
		this.readDescriptionFrmExcel = Excel.readFromExcel(excel_file, sheet_name, FOUR, ONE);
		
		AppUtil appUtil = new AppUtil();
		if(appUtil.login(username, password)){
			
			displayOnConsole("testCreateOrgPosition() - User login SUCCESSFUL ");
			driver = driver.switchTo().window(driver.getWindowHandle());
			
			wait(ONE);
			
			boolean noerror = orgPositionCreating (txtMsg, this.readDescriptionFrmExcel, toOpen_Link_InputData, toOpen_Create_NewPage);
			
			if(noerror == true){
				if(isElementPresent(driver, popUp_Exists_Description)){
					displayOnConsole("testCreateOrgPosition() - Creating new Organization Position UNSUCCESSFUL ");
					logFailMessageInExtentTest("testCreateOrgPosition() - Creating new Organization Position UNSUCCESSFUL ");
					
					String details = "Organization Position : Entity with this Description already exists. Please, give another description ";
					displayOnConsole("testCreateOrgPosition() -  "+details);
					
					String screenshot = getScreenshot(driver, "Organization_Position_Exists");
					Actions actions = new Actions(driver);
					Action action = actions.moveToElement(driver.findElement(popUp_Exists_Close),TWO,ZERO).click().build();
					action.perform();
					logInfoMessageInExtentTest(details);
				} else {
					displayOnConsole("testCreateOrgPosition() - Creating new Organization Position SUCCESSFUL ");
					logInfoMessageInExtentTest("testCreateOrgPosition() - new Creating Organization Position SUCCESSFUL ");
					
					//Verifying for the added Condition
					displayOnConsole("testCreateOrgPosition() - Verifying the new added Organization Position.");
					boolean condnDescFlag = lookupDataOptionDescription(this.readDescriptionFrmExcel);
					if(condnDescFlag){
						displayOnConsole("testCreateOrgPosition() - Verifying new Organization Position SUCCESSFUL ");
						logPassMessageInExtentTest("testCreateOrgPosition() - Verifying new Organization Position SUCCESSFUL ");
					}
				}
			} else {
				
				logFailMessageInExtentTest("testCreateOrgPosition() - Creating new Organization Position UNSUCCESSFUL ");
			}
			
			driver = driver.switchTo().defaultContent();
			appUtil.logout();
		} else {
					displayOnConsole("testCreateOrgPosition() -  User login UNSUCCESSFUL, Creating new Organization Position UNSUCCESSFUL ");
					logFailMessageInExtentTest("testCreateOrgPosition() - User login UNSUCCESSFUL, Creating new Organization Position UNSUCCESSFUL ");
		}
	}
	
	@Test
	public void testDeleteOrgPosition(){
		
		//starting of test 
		extentTest = extent.startTest("testDeleteOrgPosition ");
		
		//delete OrganizationPosition test data
		sheet_name = "OrgPosition";
				
		if(username == null){
			username = getUserName();//Changed
			password = getUserPassword();//Changed
		}
		
		//read description from test data
		this.readDescriptionFrmExcel = Excel.readFromExcel(excel_file, sheet_name, SIX, ONE);
				
		AppUtil apputil = new AppUtil();
		if(apputil.login(username, password)){

			displayOnConsole("testDeleteOrgPosition() - User login SUCCESSFUL ");
			
			driver = driver.switchTo().defaultContent();

			boolean noerror = orgPositionCreating (txtMsg, this.readDescriptionFrmExcel, toOpen_Link_InputData, toOpen_Create_NewPage);

			if(noerror == true){

				if (isElementPresent(driver, popUp_Exists_Description)){
						displayOnConsole("testDeleteOrgPosition() - Creating new Organization Position UNSUCCESSFUL ");
						logInfoMessageInExtentTest("testDeleteOrgPosition() - Creating new Organization Position UNSUCCESSFUL ");
						
						String details = "Organization Position : Entity with this Description already exists. Deleting existing one.";
						displayOnConsole("testDeleteOrgPosition() -  "+details);
						
						String screenshot = getScreenshot(driver, "Organization_Position_Exists");
						Actions actions = new Actions(driver);
						Action action = actions.moveToElement(driver.findElement(popUp_Exists_Close),TWO,ZERO).click().build();
						action.perform();
						logInfoMessageInExtentTest(details);
				} else {
							displayOnConsole("testDeleteOrgPosition() - Creating new Organization Position SUCCESSFUL ");
							logInfoMessageInExtentTest("testDeleteOrgPosition() - Creating new Organization Position SUCCESSFUL ");
				}
				
				//Finding for deleting
				displayOnConsole("testDeleteOrgPosition() - Finding the Organization Position to delete.");
				boolean lookupOrganizationPosition = lookupDataOptionDescription(this.readDescriptionFrmExcel);
				
				if(lookupOrganizationPosition){

					boolean deletingOrganizationPosition = orgPositionDeleting();
					
					if(deletingOrganizationPosition){
						displayOnConsole("testDeleteOrgPosition() - Deleting Organization Position SUCCESSFUL ");
						logInfoMessageInExtentTest("testDeleteOrgPosition() - Deleting Organization Position SUCCESSFUL ");
						
						displayOnConsole("testDeleteOrgPosition() - Verifying the deleted Organization Position.");
						
						//Verifying whether deleted or not.
						lookupOrganizationPosition =  lookupDataOptionDescription(this.readDescriptionFrmExcel);
						if(lookupOrganizationPosition == false) {
								displayOnConsole("testDeleteOrgPosition() - Verifying for deleting Organization Position SUCCESSFUL ");
								logPassMessageInExtentTest("testDeleteOrgPosition() - Verifying for deleting Organization Position SUCCESSFUL ");
						}
						else {
								displayOnConsole("testDeleteOrgPosition() - Verifying for deleting Organization Position UNSUCCESSFUL ");
								logFailMessageInExtentTest("testDeleteOrgPosition() - Verifying for deleting Organization Position UNSUCCESSFUL ");
						}
					} else {
								displayOnConsole("testDeleteOrgPosition() - Deleting Organization Position UNSUCCESSFUL ");
								logFailMessageInExtentTest("testDeleteOrgPosition() - Deleting Organization Position UNSUCCESSFUL ");
					}
				} else {
					displayOnConsole("testDeleteOrgPosition() - Search for Organization Position itself UNSUCCESSFUL, so, can't procced to delete. ");
					logFailMessageInExtentTest("testDeleteOrgPosition() - Search for Organization Position itself UNSUCCESSFUL, so, can't procced to delete. ");
				}
				
			} else{
					displayOnConsole("testDeleteOrgPosition() - Creating new Organization Position itself UNSUCCESSFUL, so, can't procced to delete. ");
					logFailMessageInExtentTest("testDeleteOrgPosition() - Creating new Organization Position itself UNSUCCESSFUL, so, can't procced to delete. ");
			}
			
			driver = driver.switchTo().defaultContent();
			apputil.logout();

		}else{
			
			displayOnConsole("testDeleteOrgPosition() - User login UNSUCCESSFUL, deleting Organization Position UNSUCCESSFUL ");
			logFailMessageInExtentTest("testDeleteOrgPosition() - User login UNSUCCESSFUL, deleting Organization Position UNSUCCESSFUL ");
		
		}
	}

	@Test
	public void testEditOrgPosition(){
		
		//start of test
		extentTest = extent.startTest("testEditOrgPosition ");
		
		//edit OrganizationPosition test data
		sheet_name = "OrgPosition";
		
		//application username, password
		if(username == null){
			
			username = getUserName();//Changed
			password = getUserPassword();//Changed
			
		}
		
		//read description from test data
		this.readDescriptionFrmExcel = Excel.readFromExcel(excel_file, sheet_name, FOUR, ONE);
		
		//read OrganizationPosition description from test data
		String editOrganizationPositiondescription = Excel.readFromExcel(excel_file, sheet_name, FIVE, ONE);
		
		AppUtil apputil = new AppUtil();
		if(apputil.login(username, password)){
			
			displayOnConsole("testEditOrgPosition() - User login SUCCESSFUL ");
			
			boolean noerror = orgPositionCreating (txtMsg, this.readDescriptionFrmExcel, toOpen_Link_InputData, toOpen_Create_NewPage);
			if(noerror == true){
				
				if(isElementPresent(driver, popUp_Exists_Description)){
					displayOnConsole("testEditOrgPosition() - Creating new Organization Position UNSUCCESSFUL ");
					logInfoMessageInExtentTest("testEditOrgPosition() - Creating new Organization Position UNSUCCESSFUL ");
					
					String details = "Organization Position already exists, editing existing one ";
					displayOnConsole("testOrganizationPositionEditing - "+details);
					
					String screenshot = getScreenshot(driver, "Organization_Position_Exists");
					Actions actions = new Actions(driver);
					Action action = actions.moveToElement(driver.findElement(popUp_Exists_Close),TWO,ZERO).click().build();
					action.perform();
					logInfoMessageInExtentTest("testEditOrgPosition() - " + details);
					
				} else {
						displayOnConsole("testEditOrgPosition() - Creating new Organization Position SUCCESSFUL ");
						logInfoMessageInExtentTest("testEditOrgPosition() - Creating new Organization Position SUCCESSFUL ");
				}
				
				//Finding for editing
				displayOnConsole("testEditOrgPosition() - Finding the Organization Position to Edit.");
				boolean lookupOrganizationPosition = lookupDataOptionDescription(this.readDescriptionFrmExcel);
				
				if(lookupOrganizationPosition){

					boolean OrganizationPositionedit = orgPositionEditing(this.readDescriptionFrmExcel, editOrganizationPositiondescription);					
					if(OrganizationPositionedit){
						
						if(isElementPresent(driver, popUp_Exists_Description)){
							
							String details = "Organization Position with same name already exists, Editing Organization Position UNSUCCESSFUL ";
							displayOnConsole("testEditOrgPosition() : "+details);
							
							String screenshot = getScreenshot(driver, "Organization_Position_Exists");
							Actions actions = new Actions(driver);
							Action action = actions.moveToElement(driver.findElement(popUp_Exists_Close),TWO,ZERO).click().build();
							action.perform();
							logInfoMessageInExtentTest("testEditOrgPosition() - " + details);							
						} else {
							displayOnConsole("testEditOrgPosition() - Editing Organization Position SUCCESSFUL ");
							logInfoMessageInExtentTest("testEditOrgPosition() - Editing Organization Position SUCCESSFUL ");
							
							displayOnConsole("testEditOrgPosition() - Verifying the Edited Organization Position.");
							
							//Verifying whether edited or not.
							lookupOrganizationPosition = lookupDataOptionDescription(editOrganizationPositiondescription);
							if(lookupOrganizationPosition == true) {
								displayOnConsole("testEditOrgPosition() - Verifying Edited Organization Position SUCCESSFUL ");
								logPassMessageInExtentTest("testEditOrgPosition() - Verifing Edited Organization Position SUCCESSFUL ");
							}
							else {
								displayOnConsole("testEditOrgPosition() - Verifying Edited Organization Position UNSUCCESSFUL ");
								logFailMessageInExtentTest("testEditOrgPosition() - Edited Organization Position UNSUCCESSFUL ");
							}	
						}
					} else{
								displayOnConsole("testEditOrgPosition() - Editing Organization Position UNSUCCESSFUL ");
								logPassMessageInExtentTest("testEditOrgPosition() - Editing Organization Position UNSUCCESSFUL ");
					}
				} else {
							displayOnConsole("testEditOrgPosition() - Search for Organization Position itself UNSUCCESSFUL, so, can't procced to edit. ");
							logFailMessageInExtentTest("testEditOrgPosition() - Search for Organization Position itself UNSUCCESSFUL, so, can't procced to edit. ");
				}
				
			} else {
						displayOnConsole("testEditOrgPosition() - Creating new Organization Position itself UNSUCCESSFUL, so, can't procced to edit. ");
						logFailMessageInExtentTest("testEditOrgPosition() - Creating new Organization Position itself UNSUCCESSFUL, so, can't procced to edit. ");
			}
			
			driver = driver.switchTo().defaultContent();
			apputil.logout();

		} else{
			
			displayOnConsole("testEditOrgPosition() - User login UNSUCCESSFUL, Editing Organization Position UNSUCCESSFUL ");
			logFailMessageInExtentTest("testEditOrgPosition() - User login UNSUCCESSFUL, Editing Organization Position UNSUCCESSFUL ");
		}
	}

	String getUserPassword() {
		
		String password = Excel.readFromExcel(excel_file, application_sheet, THREE, ONE);//Changed
		return password;
	}

	String getUserName() {
		
		String user = Excel.readFromExcel(excel_file, application_sheet, TWO, ONE);//Changed
		return user;	
	}
	
	void displayOnConsole(String consoleMessage) {
		System.out.println(consoleMessage);
	}
	
	void logFailMessageInExtentTest(String details) {
		extentTest.log(LogStatus.FAIL, details);
		Assert.fail(details);//Added
	}
	
	void logPassMessageInExtentTest(String details) {
		extentTest.log(LogStatus.PASS, details);
	}

	void logInfoMessageInExtentTest(String details) {
		extentTest.log(LogStatus.INFO, details);
	}
	
	boolean orgPositionCreating (String txtMsg, String readDescriptionFrmExcel, By toOpen_Link_InputData, By toOpen_Create_NewPage) {
		boolean noerror = true;

		try {
			
			//click for more tools
			//By more = By.xpath("(//div[@id='navWrapper']/ul[@id='sectionsNav']/descendant::a[@title='More'])");
			//By more_tool = By.xpath("(//*[@id='section_tools'])");
			//click(more);
			//wait(TWO);
			//click(more_tool);
			
			//Above was temporarily not working , so using below workaround to navigate
			//driver.navigate().to(driver.getCurrentUrl()+"action=newFrameset&sourceTab=tools&siteArea=admin&isToolsNavigation=true");//This was not working with new url
			AppUtil apputil = new AppUtil();//Added
			boolean tools_page_shown = apputil.gotoToolsPage();//Added
			if(tools_page_shown == false){//Added
				Assert.fail("Tools page failed to load");
				getScreenshot(driver, "ToolsPageLoading_Failed");
			}
			wait(ONE);
			
			
			displayOnConsole("orgPositionCreating() - "+txtMsg);
			
			//go to internal html
			//driver = driver.switchTo().frame(driver.findElement(tool_frame));//Changed
			
			wait(ONE);
			//click Organization Position link
			click(toOpen_Link_InputData);
			//wait
			wait(ONE);
			
			//Checking before editing
			boolean lookup = lookupDataOptionDescription(readDescriptionFrmExcel);//Added
			logInfoMessageInExtentTest("Checking Organisation Position before creating ");//Added
			if(lookup){//Added
				logInfoMessageInExtentTest("Organisation Position before creating was exist, deleting ");
				this.readDescriptionFrmExcel = readDescriptionFrmExcel;
				boolean deleting = orgPositionDeleting();
				if(deleting == true){
					logInfoMessageInExtentTest("Organisation Position before creating deleted ");
				}
			}//Added
			
			displayOnConsole("orgPositionCreating() - Description read from excel-sheet '" + sheet_name +"' is '" +readDescriptionFrmExcel + "'");
			
			//click new role
			click(toOpen_Create_NewPage);
			wait(ONE);
			
			//Enter OrganizationPosition description
			click(toInput_Data_txtBox);
			driver.findElement(toInput_Data_txtBox).clear();
			enter(toInput_Data_txtBox, readDescriptionFrmExcel);
			
			//click save
			click(toSave_Create_NewPage);
			wait(TWO);
			
		} catch (Exception problem) {
			noerror=false;
			logFailMessageInExtentTest("orgPositionCreating() :: Problem : "+ problem.fillInStackTrace());
		}
		
		return noerror;
	}
	
	boolean orgPositionDeleting() {
		boolean deleteDataOptionFlag = true;
		
		try {
			
			//delete of Condition description
			readDescriptionFrmExcel = readDescriptionFrmExcel.trim();
			
			displayOnConsole("orgPositionDeleting() - Deleting Entity : "+ readDescriptionFrmExcel);
			
			tools_condn_text_delete = By.xpath("(//table[@id='cu']/descendant::div[@id='highlightkeyword'][contains(text(),'"+ readDescriptionFrmExcel +"')]/parent::td/preceding-sibling::td/parent::tr/td["+FIVE+"]/a["+ONE+"]/img)");
			tools_condn_text_checkbox = By.xpath("(//table[@id='cu']/descendant::div[@id='highlightkeyword'][contains(text(),'"+ readDescriptionFrmExcel +"')]/parent::td/preceding-sibling::td/descendant::input[@type='checkbox'])");
			
			click(tools_condn_text_checkbox);
			wait(ONE);
			
			click(tools_condn_text_delete);
			wait(ONE);
			
			//accept delete OrganizationPosition description alerts
			Alert alertbox = driver.switchTo().alert();
			String alertmessagetext = alertbox.getText();
			boolean textcontent = alertmessagetext.contains(deleteCondnTextMsg);
			if(textcontent){
				logInfoMessageInExtentTest(deleteCondnTextMsg + " message shown ");
			}
			
			alertbox.accept();
			wait(TWO);
			
		} catch (Exception problem) {
			
			deleteDataOptionFlag = false;
			logInfoMessageInExtentTest("orgPositionDeleting() :: Problem : "+ problem.getMessage());
		}
		
		return deleteDataOptionFlag;
	}
	
	boolean orgPositionEditing(String readDescriptionFrmExcel, String editOrganizationPositiondescription) {
		boolean editDataOptionFlag = true;
		
		try {
			
			//Checking before editing
			String storeDescription = readDescriptionFrmExcel;
			boolean lookup = lookupDataOptionDescription(editOrganizationPositiondescription);//Added
			logInfoMessageInExtentTest("Checking Influence Strength before editing ");//Added
			if(lookup){//Added
				logInfoMessageInExtentTest("Organisation Position before editing was exist, deleting ");
				this.readDescriptionFrmExcel = editOrganizationPositiondescription;
				boolean deleting = orgPositionDeleting();
				if(deleting == true){
						logInfoMessageInExtentTest("Organisation Position before editing deleted ");
				}//Added
			}
			
			//edit entity description
			this.readDescriptionFrmExcel = storeDescription.trim();//changed
			lookup = lookupDataOptionDescription(this.readDescriptionFrmExcel);//Added
			tools_condn_text_edit = By.xpath("(//table[@id='cu']/descendant::div[@id='highlightkeyword'][contains(text(),'"+ this.readDescriptionFrmExcel +"')]/parent::td/preceding-sibling::td/parent::tr//*[self::td]["+FIVE+"]/a["+TWO+"]/img)");
			click(tools_condn_text_edit);
			wait(ONE);
			
			//enter  OrganizationPosition description
			click(condn_new_box_input);
			driver.findElement(condn_new_box_input).clear();
			enter(condn_new_box_input, editOrganizationPositiondescription);
			
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
			logInfoMessageInExtentTest("orgPositionEditing :: Problem " + problem.fillInStackTrace());
			
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
			displayOnConsole("lookupDataOptionDescription() - Searched OrganizationPosition found at page no. : "+filter);
		
		if(navigationoptions.size() == ONE)
		{
			lookUpDataFlag = isElementPresent(driver, condnDesc_text);
		}
		
		return lookUpDataFlag;
	}

}
