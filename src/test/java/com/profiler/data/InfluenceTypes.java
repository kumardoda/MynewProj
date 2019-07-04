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

public class InfluenceTypes extends Functions {
	
	private byte ZERO = 0;
	private byte ONE = 1;
	private byte TWO = 2;
	private byte THREE = 3;
	private byte FOUR = 4;
	private byte FIVE = 5;
	private byte SIX = 6;
	private byte SEVEN = 7;
	private byte EIGHT = 8;
	private byte NINE = 9;
	
	private String SEPERATOR = ",";
			
	private String username = null;
	private String password = null;
	private String excel_file = "files//TestData.xlsx";
	private String sheet_name = null;
	
	private String strTarget="";
	private By lookUp_entity = By.xpath("(//*[@id='highlightkeyword'][contains(text(),'"+strTarget+"')])");
	
	private By tools_filterWithIntials_select = By.xpath("//select[@id='jumpTo']");
	private By tools_condn_page_select = By.xpath("//select[@id='bbox']");
	
	private By tool_frame = By.xpath("(//*[@id='toolsContent'])");
	
	private By toOpen_Link_InputData = By.xpath("(//*[@id='container']/table/descendant::div[@id='content']/table/descendant::div[@id='leftDiv']/descendant::a[contains(text(),'Influence Types')])");
	private By toOpen_Create_NewPage = By.xpath("(//*[@id=\"menu\"]/li/a/span[1]//img[@src='/newimages/icons/new.gif'])");
	
	private By roleDropDown = By.xpath("//*[@id='roleId_dd_label']");
	
	private By popUp_Exists_Description = By.xpath("(//*[@id='messageDiv']/descendant::div[contains(text(),'Entity with this Description already exists')])");
	private By popUp_Exists_Close = By.xpath("(//*[@id='resultDiv']/div[@id='messageDiv']/descendant::a[@title='close']["+ONE+"])");
	
	//Create
	private By toInput_Data_txtBox = By.xpath("(//*[@id='profilerPopupDiv']/descendant::input[@id='description'])");
	private By toSave_Create_NewPage = By.xpath("(//*[@id='profilerPopupDiv']/div[@id='profilerPopupMenu']/table/descendant::a/img[@alt='Save'])");
	
	//Delete
	private By tools_condn_text_delete = By.xpath("//*[@id='highlightkeyword'][contains(text(),'"+ strTarget +"')]/parent::td/preceding-sibling::td/parent::tr//*[self::td]["+FIVE+"]/a["+ONE+"]");
	private By tools_condn_text_checkbox = By.xpath("//*[@id='highlightkeyword'][contains(text(),'"+strTarget+"')]/parent::td/preceding-sibling::td/descendant::input[@type='checkbox']");
	
	private String deletePopUpTextMsg = "Delete 1 Influence Type?";
	private String delCondnProcessingMsgText = "Your request is in queue. For more details check \"Profile Data Management->Realtime Mass Update tracker\" screen";
	
	//Edit
	private By tools_condn_text_edit = By.xpath("//*[@id='highlightkeyword'][contains(text(),'"+ strTarget +"')]/parent::td/preceding-sibling::td/parent::tr//*[self::td]["+FIVE+"]/a["+TWO+"]");
	private By condn_new_box_input = By.xpath("(//*[@id='profilerPopupDiv']/descendant::input[@id='description'])");
	private By condn_new_box_save = By.xpath("(//*[@id='profilerPopupDiv']/div[@id='profilerPopupMenu']/table/descendant::a/img[@alt='Save'])");
	
	private String txtMsg = "Adding new Influence Type ";
	private List<String> roleList = new LinkedList<String>();
	
	private String application_sheet = "Application";//Added
	private AppUtil appUtil = new AppUtil();//Added
	
	@Test(priority = 1)
	public void testCreateInflncType(){
		
		extentTest = extent.startTest("testCreateInflncType");
		logInfoMessageInExtentTest(" ********** Starting testing : Influence Type - Create flow *************");
		
		sheet_name = "InfluenceType";
		
		if(username == null){
			username = getUserName();//Changed
			password = getUserPassword();//Changed
		}
		
		//Influence Type - Name : For Creation
		strTarget = Excel.readFromExcel(excel_file, sheet_name, FOUR, ONE);
				
		//Influence Type - Role : For Creation
		String addRoles = Excel.readFromExcel(excel_file, sheet_name, FIVE, ONE);
		
		List<String> roleList = new LinkedList<String>();
		
		if(addRoles != null) {
			if(addRoles.contains(SEPERATOR))
				roleList = Arrays.asList(addRoles.split(","));
			else
				roleList.add(addRoles);
		}
		

		appUtil.login(username, password);//Added 
		if(isElementPresent(driver, By.xpath("(//div[@id='navWrapper']/ul[@id='sectionsNav']/descendant::a[@title='More'])"))){// changed

			
			displayOnConsole("testCreateInflncType() - User login SUCCESSFUL ");
			logInfoMessageInExtentTest(" ********** User Login Successful *************");
			
			driver = driver.switchTo().window(driver.getWindowHandle());
			
			wait(ONE);
			
			boolean noerror = inflnTypeCreating (txtMsg, strTarget, roleList, toOpen_Link_InputData, toOpen_Create_NewPage);
			
			if(noerror == true){
				if(isElementPresent(driver, popUp_Exists_Description)){
					displayOnConsole("testCreateInflncType() - Creating new Influence Type UNSUCCESSFUL ");
					
					String details = "Influence Type : Entity with this Description already exists. Please, give another description, else re-creating ";//changed
					displayOnConsole("testCreateInflncType() -  "+details);
					
					String screenshot = getScreenshot(driver, "Create_Influence_Type_Exists");
					Actions actions = new Actions(driver);
					Action action = actions.moveToElement(driver.findElement(popUp_Exists_Close),TWO,ZERO).click().build();
					action.perform();
					//Checking before creating
					boolean lookupInfluenceType = lookupDataOptionDescription(strTarget);//Added
					logInfoMessageInExtentTest("Checking Influence Type before re-creating ");//Added
					if(lookupInfluenceType){//Added
						boolean deletingInfluenceType = inflnTypeDeleting(strTarget);
						if(deletingInfluenceType == true){
							logInfoMessageInExtentTest("Influence Type before re-creating deleted ");
						}
					}//Added
					noerror = inflnTypeCreating (txtMsg, strTarget, roleList, toOpen_Link_InputData, toOpen_Create_NewPage);
					logInfoMessageInExtentTest(details);
				} 
				if(noerror) {
					
					displayOnConsole("testCreateInflncType() - Creating new Influence Type SUCCESSFUL ");
					logPassMessageInExtentTest("testCreateInflncType() - Creating new Influence Type SUCCESSFUL ");
					
					//Verifying for the added Condition
					displayOnConsole("testCreateInflncType() - Verifying the new added Influence Type.");
					logInfoMessageInExtentTest("testCreateInflncType() - Verifying new added Influence Type. ");
					
					boolean condnDescFlag = lookupDataOptionDescription(strTarget);
					if(condnDescFlag){
						displayOnConsole("testCreateInflncType() - Verifying new Influence Type SUCCESSFUL ");
						logPassMessageInExtentTest("testCreateInflncType() - Verifying new Influence Type SUCCESSFUL ");
					}
					
				}else {
					logFailMessageInExtentTest("testCreateInflncType() - Creating new Influence Type UNSUCCESSFUL ");
				}
			} else {
						logFailMessageInExtentTest("testCreateInflncType() - Creating new Influence Type UNSUCCESSFUL ");
			}
			
			driver = driver.switchTo().defaultContent();
			appUtil.logout();
		} else {
					displayOnConsole("testCreateInflncType() -  User login UNSUCCESSFUL, Creating new Influence Type UNSUCCESSFUL ");
					logFailMessageInExtentTest("testCreateInflncType() - User login UNSUCCESSFUL, Creating new Influence Type UNSUCCESSFUL ");
		}
		logInfoMessageInExtentTest(" ********** Ending testing : Influence Type : Create Flow *************");
	}
	
	@Test(priority = 3)
	public void testDeleteInflncType(){
		
		//starting of test 
		extentTest = extent.startTest("testDeleteInflncType ");
		logInfoMessageInExtentTest(" ********** Starting testing : Influence Type - Delete flow *************");
		
		sheet_name = "InfluenceType";
		
		if(username == null){
			username = getUserName();//Changed
			password = getUserPassword();//Changed
		}
		
		//Influence Type - Name : For Creation
		String strToDelete = Excel.readFromExcel(excel_file, sheet_name, EIGHT, ONE);
				
		//Influence Type - Role : For Creation
		String addRoles = Excel.readFromExcel(excel_file, sheet_name, NINE, ONE);
		
		List<String> roleList = new LinkedList<String>();
		
		if(addRoles != null) {
			if(addRoles.contains(SEPERATOR))
				roleList = Arrays.asList(addRoles.split(","));
			else
				roleList.add(addRoles);
		}
		
		appUtil.login(username, password);//Added 
		if(isElementPresent(driver, By.xpath("(//div[@id='navWrapper']/ul[@id='sectionsNav']/descendant::a[@title='More'])"))){// changed


			displayOnConsole("testDeleteInflncType() - User login SUCCESSFUL ");
			logInfoMessageInExtentTest(" ********** User Login Successful *************");
			
			driver = driver.switchTo().defaultContent();

			boolean noerror = inflnTypeCreating (txtMsg, strToDelete, roleList, toOpen_Link_InputData, toOpen_Create_NewPage);

			if(noerror == true){

				if (isElementPresent(driver, popUp_Exists_Description)){
						displayOnConsole("testDeleteInflncType() - Creating new Influence Type UNSUCCESSFUL ");
						
						String details = "Influence Type : Entity with this Description already exists. Deleting existing one.";
						displayOnConsole("testDeleteInflncType() -  "+details);
						
						String screenshot = getScreenshot(driver, "Create_Influence_Type_Exists");
						Actions actions = new Actions(driver);
						Action action = actions.moveToElement(driver.findElement(popUp_Exists_Close),TWO,ZERO).click().build();
						action.perform();
						logInfoMessageInExtentTest(details);
						
						//Checking before creating
						boolean lookupInfluenceType = lookupDataOptionDescription(strTarget);//Added
						logInfoMessageInExtentTest("Checking Influence Type before re-creating ");//Added
						if(lookupInfluenceType){//Added
							boolean deletingInfluenceType = inflnTypeDeleting(strTarget);
							if(deletingInfluenceType == true){
								logInfoMessageInExtentTest("Influence Type before re-creating deleted ");
							}
						}//Added
						noerror = inflnTypeCreating (txtMsg, strTarget, roleList, toOpen_Link_InputData, toOpen_Create_NewPage);
						logInfoMessageInExtentTest(details);
				} else {
							displayOnConsole("testDeleteInflncType() - Creating new Influence Type SUCCESSFUL ");
							logInfoMessageInExtentTest("testDeleteInflncType() - Creating new Influence Type SUCCESSFUL ");
				}
				
				//Finding the Entity for deleting
				displayOnConsole("testDeleteInflncType() - Finding the Influence Type to delete.");
				logInfoMessageInExtentTest("testDeleteInflncType() - Finding the Influence Type to delete.");
				boolean lookupInfluenceType = lookupDataOptionDescription(strToDelete);
				
				if(lookupInfluenceType){

					boolean deletingInfluenceType = inflnTypeDeleting(strToDelete);
					
					if(deletingInfluenceType){
						displayOnConsole("testDeleteInflncType() - Deleting Influence Type SUCCESSFUL ");
						logInfoMessageInExtentTest("testDeleteInflncType() - Deleting Influence Type SUCCESSFUL ");
						
						displayOnConsole("testDeleteInflncType() - Verifying the deleted Influence Type.");
						logInfoMessageInExtentTest("testDeleteInflncType() - Verifying the deleted Influence Type.");
						
						//Verifying whether deleted or not.
						lookupInfluenceType =  lookupDataOptionDescription(strToDelete);
						if(lookupInfluenceType == false) {
								displayOnConsole("testDeleteInflncType() - Verifying for deleting Influence Type SUCCESSFUL ");
								logPassMessageInExtentTest("testDeleteInflncType() - Verifying for deleting Influence Type SUCCESSFUL ");
						}
						else {
								displayOnConsole("testDeleteInflncType() - Verifying for deleting Influence Type UNSUCCESSFUL ");
								logFailMessageInExtentTest("testDeleteInflncType() - Verifying for deleting Influence Type UNSUCCESSFUL ");
						}
					} else {
								displayOnConsole("testDeleteInflncType() - Deleting Influence Type UNSUCCESSFUL ");
								logFailMessageInExtentTest("testDeleteInflncType() - Deleting Influence Type UNSUCCESSFUL ");
					}
				} else {
					displayOnConsole("testDeleteInflncType() - Search for Influence Type itself UNSUCCESSFUL, so, can't procced to delete. ");
					logFailMessageInExtentTest("testDeleteInflncType() - Search for Influence Type itself UNSUCCESSFUL, so, can't procced to delete. ");
				}
				
			} else{
					displayOnConsole("testDeleteInflncType() - Creating new Influence Type itself UNSUCCESSFUL, so, can't procced to delete. ");
					logFailMessageInExtentTest("testDeleteInflncType() - Creating new Influence Type itself UNSUCCESSFUL, so, can't procced to delete. ");
			}
			
			driver = driver.switchTo().defaultContent();
			appUtil.logout();

		}else{
			  	displayOnConsole("testDeleteInflncType() - User login UNSUCCESSFUL, deleting Influence Type UNSUCCESSFUL ");
			  	logFailMessageInExtentTest("testDeleteInflncType() - User login UNSUCCESSFUL, deleting Influence Type UNSUCCESSFUL ");
		}
		
		logInfoMessageInExtentTest(" ********** Ending testing : Influence Type - Delete flow *************");
	}

	@Test(priority = 2)
	public void testEditInflncType(){
		
		//start of test
		extentTest = extent.startTest("testEditInflncType ");
		logInfoMessageInExtentTest(" ********** Starting testing : Influence Type - Edit flow *************");
		
		sheet_name = "InfluenceType";
		
		//application username, password
		if(username == null){
			username = getUserName();//Changed
			password = getUserPassword();//Changed
		}
		
		/*Reading data from excel-data sheet - "InfluenceType" */
		
		//Influence Type - Name : For Creation
		strTarget = Excel.readFromExcel(excel_file, sheet_name, FOUR, ONE);
		
		//Influence Type - Role : For Creation
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
		
		
		//Influence Type - New name : For Editing
		String strReplacement = Excel.readFromExcel(excel_file, sheet_name, SIX, ONE);
				
		appUtil.login(username, password);//Added 
		if(isElementPresent(driver, By.xpath("(//div[@id='navWrapper']/ul[@id='sectionsNav']/descendant::a[@title='More'])"))){// changed

			
			displayOnConsole("testEditInflncType() - User login SUCCESSFUL ");
			logInfoMessageInExtentTest(" ********** User Login Successful *************");
			
			boolean noerror = inflnTypeCreating (txtMsg, strTarget, roleList, toOpen_Link_InputData, toOpen_Create_NewPage);
			if(noerror == true){
				
				if(isElementPresent(driver, popUp_Exists_Description)){
					displayOnConsole("testEditInflncType() - Creating new Influence Type UNSUCCESSFUL ");
					
					String details = "Influence Type already exists, editing existing one ";
					displayOnConsole("testEditInflncType() - "+details);
					
					String screenshot = getScreenshot(driver, "Create_Influence_Type_Exists");
					Actions actions = new Actions(driver);
					Action action = actions.moveToElement(driver.findElement(popUp_Exists_Close),TWO,ZERO).click().build();
					action.perform();
					logInfoMessageInExtentTest("testEditInflncType() - " + details);
					
					//Checking before creating
					boolean lookupInfluenceType = lookupDataOptionDescription(strTarget);//Added
					logInfoMessageInExtentTest("Checking Influence Type before re-creating ");//Added
					if(lookupInfluenceType){//Added
						boolean deletingInfluenceType = inflnTypeDeleting(strTarget);
						if(deletingInfluenceType == true){
							logInfoMessageInExtentTest("Influence Type before re-creating deleted ");
						}
					}//Added
					noerror = inflnTypeCreating (txtMsg, strTarget, roleList, toOpen_Link_InputData, toOpen_Create_NewPage);
					logInfoMessageInExtentTest(details);
					
				} else {
						displayOnConsole("testEditInflncType() - Creating new Influence Type SUCCESSFUL ");
						logInfoMessageInExtentTest("testEditInflncType() - Creating new Influence Type SUCCESSFUL ");
				}
				
				//Finding for editing
				displayOnConsole("testEditInflncType() - Finding the Influence Type to Edit.");
				boolean lookupInfluenceType = lookupDataOptionDescription(strTarget);
				
				if(lookupInfluenceType){
					displayOnConsole("testEditInflncType() - Finding the Influence Type to Edit SUCCESSFUL.");
					logInfoMessageInExtentTest("testEditInflncType() - Finding the Influence Type to Edit SUCCESSFUL.");
					
					boolean influencetypeedit = inflnTypeEditing(strTarget, strReplacement);
					
					if(influencetypeedit){
						
						if(isElementPresent(driver, popUp_Exists_Description)){
							
							String details = "Influence Type with same name already exists, Editing Influence Type UNSUCCESSFUL ";
							displayOnConsole("testEditInflncType() : "+details);
							
							String screenshot = getScreenshot(driver, "Edit_Influence_Type_Exists");//Changed
							Actions actions = new Actions(driver);
							Action action = actions.moveToElement(driver.findElement(popUp_Exists_Close),TWO,ZERO).click().build();
							action.perform();
							logInfoMessageInExtentTest("testEditInflncType() - " + details);
							logFailMessageInExtentTest("testEditInflncType() - Finding the Influence Type to Edit UNSUCCESSFUL.");		
						} else {
							displayOnConsole("testEditInflncType() - Editing Influence Type SUCCESSFUL ");
							logInfoMessageInExtentTest("testEditInflncType() - Editing Influence Type SUCCESSFUL ");
							
							displayOnConsole("testEditInflncType() - Verifying the Edited Influence Type.");
							logInfoMessageInExtentTest("testEditInflncType() - Verifying the Edited Influence Type.");
							
							//Verifying whether edited or not.
							lookupInfluenceType = lookupDataOptionDescription(strReplacement);
							if(lookupInfluenceType == true) {
								displayOnConsole("testEditInflncType() - Verifying Edited Influence Type SUCCESSFUL ");
								logPassMessageInExtentTest("testEditInflncType() - Verifing Edited Influence Type SUCCESSFUL ");
							}
							else {
								displayOnConsole("testEditInflncType() - Verifying Edited Influence Type UNSUCCESSFUL ");
								logFailMessageInExtentTest("testEditInflncType() - Edited Influence Type UNSUCCESSFUL ");
							}	
						}
					} else{
								displayOnConsole("testEditInflncType() - Editing Influence Type UNSUCCESSFUL ");
								logPassMessageInExtentTest("testEditInflncType() - Editing Influence Type UNSUCCESSFUL ");
					}
				} else {
							displayOnConsole("testEditInflncType() - Search for Influence Type itself UNSUCCESSFUL, so, can't procced to edit. ");
							logFailMessageInExtentTest("testEditInflncType() - Search for Influence Type itself UNSUCCESSFUL, so, can't procced to edit. ");
				}
				
			} else {
						displayOnConsole("testEditInflncType() - Creating new Influence Type itself UNSUCCESSFUL, so, can't procced to edit. ");
						logFailMessageInExtentTest("testEditInflncType() - Creating new Influence Type itself UNSUCCESSFUL, so, can't procced to edit. ");
			}
			
			driver = driver.switchTo().defaultContent();
			appUtil.logout();

		} else{
			
			displayOnConsole("testEditInflncType() - User login UNSUCCESSFUL, Editing Influence Type UNSUCCESSFUL ");
			logFailMessageInExtentTest("testEditInflncType() - User login UNSUCCESSFUL, Editing Influence Type UNSUCCESSFUL ");
		}
		
		logInfoMessageInExtentTest(" ********** Ending testing : Influence Type - Edit flow *************");
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
		
		try {//Added to check on session , logout
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
		extentTest.log(LogStatus.INFO, details);
	}
	
	boolean inflnTypeCreating (String txtMsg, String strTarget, List<String> roleList, By toOpen_Link_InputData, By toOpen_Create_NewPage) {
		boolean noerror = true;

		try {
			
			//click for more tools
			//By more = By.xpath("(//div[@id='navWrapper']/ul[@id='sectionsNav']/descendant::a[@title='More'])");
			//By more_tool = By.xpath("(//*[@id='section_tools'])");
			//click(more);
			wait(TWO);
			//click(more_tool);
			
			//Above was temporarily not working , so using below workaround to navigate
			//driver.navigate().to(driver.getCurrentUrl()+"action=newFrameset&sourceTab=tools&siteArea=admin&isToolsNavigation=true");//This was not working with new url
			AppUtil apputil = new AppUtil();//Added
			boolean tools_page_shown = apputil.gotoToolsPage();//Changed
			if(tools_page_shown == false){//Added
				logFailMessageInExtentTest("Tools page failed to load");//Changed
			}
			wait(TWO);
			
			displayOnConsole("inflnTypeCreating() - "+txtMsg);
			logInfoMessageInExtentTest(" ********** " + txtMsg + "*************");
			
			//go to internal html
			//driver = driver.switchTo().frame(driver.findElement(tool_frame));//Changed
			
			wait(ONE);
			//click Influence Type link
			click(toOpen_Link_InputData);
			//wait
			wait(ONE);
			

			
			displayOnConsole("inflnTypeCreating() - Description read from excel-sheet '" + sheet_name +"' is '" +strTarget + "'");
			logInfoMessageInExtentTest(" inflnTypeCreating() - Description read from excel-sheet '" + sheet_name +"' is '" +strTarget + "'");
			//click to open new Influence Type popup
			click(toOpen_Create_NewPage);
			wait(ONE);
			
			//added
			//click save
			click(toSave_Create_NewPage);
			wait(ONE);	
			Alert alertbox = driver.switchTo().alert();
			wait(ONE);
			alertbox.accept();
			
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
			logFailMessageInExtentTest("inflnTypeCreating() :: Problem : "+ problem.fillInStackTrace());
		}
		
		return noerror;
	}
	
	boolean inflnTypeDeleting(String strToDelete) {
		boolean deleteDataOptionFlag = true;
		
		try {
			
			//Entity : to Delete
			strToDelete = strToDelete.trim();
			
			displayOnConsole("inflnTypeDeleting() - Deleting Entity : "+ strToDelete);
			logInfoMessageInExtentTest("inflnTypeDeleting() - Deleting Entity : "+ strToDelete);
			
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
			logInfoMessageInExtentTest("inflnTypeDeleting() :: Problem : "+ problem.getMessage());
		}
		
		return deleteDataOptionFlag;
	}
	
	boolean inflnTypeEditing(String strTarget, String strReplacement) {
		boolean editDataOptionFlag = true;
		
		try {
			
			displayOnConsole("inflnTypeEditing() - Editing Entity : "+ strTarget);
			logInfoMessageInExtentTest("inflnTypeEditing() - Editing Entity : "+ strTarget);
			
			//Checking before editing
			boolean lookup = lookupDataOptionDescription(strReplacement);//Added
			logInfoMessageInExtentTest("Checking Influence Type before editing ");//Added
			if(lookup){//Added
				logInfoMessageInExtentTest("Influence Type before editing was exist, deleting ");
				boolean deletingInfluenceType = inflnTypeDeleting(strReplacement);
				if(deletingInfluenceType == true){
					logInfoMessageInExtentTest("Influence Type before editing deleted ");
				}
			}//Added
			
			//Influence Type - New Roles : For Editing
			String editRoles = Excel.readFromExcel(excel_file, sheet_name, SEVEN, ONE);
			
			List<String> editRoleList = new LinkedList<String>();
			
			if(editRoles != null) {
				if(editRoles.contains(SEPERATOR))
					editRoleList = Arrays.asList(editRoles.split(","));
				else
					editRoleList.add(editRoles);
			}	
			
			//Editing existing Condition description
			this.strTarget = strTarget.trim();
			lookup = lookupDataOptionDescription(this.strTarget);//Added
			tools_condn_text_edit = By.xpath("(//*[@id='highlightkeyword'][contains(text(),'"+ this.strTarget +"')]/parent::td/preceding-sibling::td/parent::tr//*[self::td]["+SIX+"]/a["+TWO+"]/img)");
			click(tools_condn_text_edit);
			wait(ONE);
			
			//enter InfluenceType description
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
			displayOnConsole("lookupDataOptionDescription() - Searched InfluenceType found at page no. : "+filter);
		
		if(navigationoptions.size() == ZERO || navigationoptions.size() == ONE)
		{
			lookUpDataFlag = isElementPresent(driver, lookUp_entity);
		}
		
		return lookUpDataFlag;
	}
	
	void logoutChecking(){//added
		driver = driver.switchTo().defaultContent();//Added
		if(isElementPresent(driver, By.xpath("(//div[@id='navWrapper']/ul[@id='sectionsNav']/descendant::a[@title='More'])"))){
			appUtil.logout();//Added
		}
	}

}
