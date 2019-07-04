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

public class InfluenceStrength extends Functions {
	
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
	
	private By toOpen_Link_InputData = By.xpath("(//*[@id='container']/table/descendant::div[@id='content']/table/descendant::div[@id='leftDiv']/descendant::a[contains(text(),'Influence Strengths')])");
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
	
	private String deletePopUpTextMsg = "Delete 1 Influence Strengths?";
	private String delCondnProcessingMsgText = "Your request is in queue. For more details check \"Profile Data Management->Realtime Mass Update tracker\" screen";
	
	//Edit
	private By tools_condn_text_edit = By.xpath("//*[@id='highlightkeyword'][contains(text(),'"+ strTarget +"')]/parent::td/preceding-sibling::td/parent::tr//*[self::td]["+FIVE+"]/a["+TWO+"]");
	private By condn_new_box_input = By.xpath("(//*[@id='profilerPopupDiv']/descendant::input[@id='description'])");
	private By condn_new_box_save = By.xpath("(//*[@id='profilerPopupDiv']/div[@id='profilerPopupMenu']/table/descendant::a/img[@alt='Save'])");
	
	private String txtMsg = "Adding new Influence Strengths ";
	private List<String> roleList = new LinkedList<String>();
	
	private String application_sheet = "Application";//Added
	
	private AppUtil apputil = new AppUtil();//Added
		
	@Test(priority = 1)
	public void testCreateInflncStrngth(){
		
		extentTest = extent.startTest("testCreateInflncStrngth");
		logInfoMessageInExtentTest(" ********** Starting testing : Influence Strengths - Create flow *************");
		
		sheet_name = "InfluenceStrength";
		
		if(username == null){
			username = getUserName();
			password = getUserPassword();
		}
		
		//Influence Strengths - Name : For Creation
		strTarget = Excel.readFromExcel(excel_file, sheet_name, FOUR, ONE);
				
		//Influence Strengths - Role : For Creation
		String addRoles = Excel.readFromExcel(excel_file, sheet_name, FIVE, ONE);
		
		List<String> roleList = new LinkedList<String>();
		
		if(addRoles != null) {
			if(addRoles.contains(SEPERATOR))
				roleList = Arrays.asList(addRoles.split(","));
			else
				roleList.add(addRoles);
		}
		
		apputil.login(username, password);//Added 
		if(isElementPresent(driver, By.xpath("(//div[@id='navWrapper']/ul[@id='sectionsNav']/descendant::a[@title='More'])"))){// changed
			
			wait(TWO);//Added
			
			displayOnConsole("testCreateInflncStrngth() - User login SUCCESSFUL ");
			logInfoMessageInExtentTest(" ********** User Login Successful *************");
			
			driver = driver.switchTo().window(driver.getWindowHandle());
			
			wait(ONE);
			
			boolean noerror = InflncStrngthCreating (txtMsg, strTarget, roleList, toOpen_Link_InputData, toOpen_Create_NewPage);
			
			if(noerror == true){
				if(isElementPresent(driver, popUp_Exists_Description)){
					
					displayOnConsole("testCreateInflncStrngth() - Creating new Influence Strengths UNSUCCESSFUL ");
					
					String details = "Influence Strengths : Entity with this Description already exists. Please, give another description, else re-creating ";//Changed
					displayOnConsole("testCreateInflncStrngth() -  "+details);
					
					String screenshot = getScreenshot(driver, "Influence_Strength_Exists");//changed
					Actions actions = new Actions(driver);
					Action action = actions.moveToElement(driver.findElement(popUp_Exists_Close),TWO,ZERO).click().build();
					action.perform();
					
					//Added
					boolean lookupInfluenceStrength = lookupDataOptionDescription(strTarget);
					boolean deleting = InflncStrngthDeleting(strTarget);
					if(deleting == true){
						logPassMessageInExtentTest("Influence Strength before re-creating deleted ");
					}
					logInfoMessageInExtentTest(details);
					noerror = InflncStrngthCreating (txtMsg, strTarget, roleList, toOpen_Link_InputData, toOpen_Create_NewPage);
					
				}
				
				if(noerror) {
				
					displayOnConsole("testCreateInflncStrngth() - Creating new Influence Strengths SUCCESSFUL ");
					logPassMessageInExtentTest("testCreateInflncStrngth() - Creating new Influence Strengths SUCCESSFUL ");
					
					//Verifying for the added Condition
					displayOnConsole("testCreateInflncStrngth() - Verifying the new added Influence Strengths.");
					boolean condnDescFlag = lookupDataOptionDescription(strTarget);
					if(condnDescFlag){
						displayOnConsole("testCreateInflncStrngth() - Verifying new Influence Strengths SUCCESSFUL ");
						logPassMessageInExtentTest("testCreateInflncStrngth() - Verifying new Influence Strengths SUCCESSFUL ");
					}
				
				}else {
					logFailMessageInExtentTest("testCreateInflncStrngth() - Creating new Influence Strengths UNSUCCESSFUL ");
				}
				
			} else {
					logFailMessageInExtentTest("testCreateInflncStrngth() - Creating new Influence Strengths UNSUCCESSFUL ");
			}
			
			driver = driver.switchTo().defaultContent();
			apputil.logout();
		} else {
					displayOnConsole("testCreateInflncStrngth() -  User login UNSUCCESSFUL, Creating new Influence Strengths UNSUCCESSFUL ");
					logFailMessageInExtentTest("testCreateInflncStrngth() - User login UNSUCCESSFUL, Creating new Influence Strengths UNSUCCESSFUL ");
		}
		logInfoMessageInExtentTest(" ********** Ending testing : Influence Strengths : Create Flow *************");
	}
	
	@Test(priority = 3)
	public void testDeleteInflncStrngth(){
		
		//starting of test 
		extentTest = extent.startTest("testDeleteInflncStrngth ");
		logInfoMessageInExtentTest(" ********** Starting testing : Influence Strengths - Delete flow *************");
		
		sheet_name = "InfluenceStrength";
		
		if(username == null){
			username = getUserName();
			password = getUserPassword();
		}
		
		//Influence Strengths - Name : For Creation
		String strToDelete = Excel.readFromExcel(excel_file, sheet_name, EIGHT, ONE);
				
		//Influence Strengths - Role : For Creation
		String addRoles = Excel.readFromExcel(excel_file, sheet_name, NINE, ONE);
		
		List<String> roleList = new LinkedList<String>();
		
		if(addRoles != null) {
			if(addRoles.contains(SEPERATOR))
				roleList = Arrays.asList(addRoles.split(","));
			else
				roleList.add(addRoles);
		}
		
		apputil.login(username, password);//Added 
		if(isElementPresent(driver, By.xpath("(//div[@id='navWrapper']/ul[@id='sectionsNav']/descendant::a[@title='More'])"))){// changed

			wait(TWO);//Added
			
			displayOnConsole("testDeleteInflncStrngth() - User login SUCCESSFUL ");
			logInfoMessageInExtentTest(" ********** User Login Successful *************");
			
			driver = driver.switchTo().defaultContent();

			boolean noerror = InflncStrngthCreating (txtMsg, strToDelete, roleList, toOpen_Link_InputData, toOpen_Create_NewPage);

			if(noerror == true){

				if (isElementPresent(driver, popUp_Exists_Description)){
						displayOnConsole("testDeleteInflncStrngth() - Creating new Influence Strengths UNSUCCESSFUL ");
						
						String details = "Influence Strengths : Entity with this Description already exists. Deleting existing one.";
						displayOnConsole("testDeleteInflncStrngth() -  "+details);
						
						String screenshot = getScreenshot(driver, "Influence_Strength_Condn_Exists");//changed
						Actions actions = new Actions(driver);
						Action action = actions.moveToElement(driver.findElement(popUp_Exists_Close),TWO,ZERO).click().build();
						action.perform();
						logInfoMessageInExtentTest(details);
						
						//Added
						boolean lookupInfluenceStrength = lookupDataOptionDescription(strTarget);
						boolean deleting = InflncStrngthDeleting(strTarget);
						if(deleting == true){
							logPassMessageInExtentTest("Influence Strength before re-creating deleted ");
						}
						logInfoMessageInExtentTest(details);
						noerror = InflncStrngthCreating (txtMsg, strTarget, roleList, toOpen_Link_InputData, toOpen_Create_NewPage);

				} else {
							displayOnConsole("testDeleteInflncStrngth() - Creating new Influence Strengths SUCCESSFUL ");
							logInfoMessageInExtentTest("testDeleteInflncStrngth() - Creating new Influence Strengths SUCCESSFUL ");
				}
				
				//Finding the Entity for deleting
				displayOnConsole("testDeleteInflncStrngth() - Finding the Influence Strengths to delete.");
				boolean lookupInfluenceStrength = lookupDataOptionDescription(strToDelete);
				
				if(lookupInfluenceStrength){

					boolean deletinginfluencestrength = InflncStrngthDeleting(strToDelete);
					
					if(deletinginfluencestrength){
						displayOnConsole("testDeleteInflncStrngth() - Deleting Influence Strengths SUCCESSFUL ");
						logPassMessageInExtentTest("testDeleteInflncStrngth() - Deleting Influence Strengths SUCCESSFUL ");
						
						displayOnConsole("testDeleteInflncStrngth() - Verifying the deleted Influence Strengths.");
						
						//Verifying whether deleted or not.
						lookupInfluenceStrength =  lookupDataOptionDescription(strToDelete);
						if(lookupInfluenceStrength == false) {
								displayOnConsole("testDeleteInflncStrngth() - Verifying for deleting Influence Strengths SUCCESSFUL ");
								logPassMessageInExtentTest("testDeleteInflncStrngth() - Verifying for deleting Influence Strengths SUCCESSFUL ");
						}
						else {
								displayOnConsole("testDeleteInflncStrngth() - Verifying for deleting Influence Strengths UNSUCCESSFUL ");
								logFailMessageInExtentTest("testDeleteInflncStrngth() - Verifying for deleting Influence Strengths UNSUCCESSFUL ");
						}
					} else {
								displayOnConsole("testDeleteInflncStrngth() - Deleting Influence Strengths UNSUCCESSFUL ");
								logFailMessageInExtentTest("testDeleteInflncStrngth() - Deleting Influence Strengths UNSUCCESSFUL ");
					}
				} else {
					displayOnConsole("testDeleteInflncStrngth() - Search for Influence Strengths itself UNSUCCESSFUL, so, can't procced to delete. ");
					logFailMessageInExtentTest("testDeleteInflncStrngth() - Search for Influence Strengths itself UNSUCCESSFUL, so, can't procced to delete. ");
				}
				
			} else{
					displayOnConsole("testDeleteInflncStrngth() - Creating new Influence Strengths itself UNSUCCESSFUL, so, can't procced to delete. ");
					logFailMessageInExtentTest("testDeleteInflncStrngth() - Creating new Influence Strengths itself UNSUCCESSFUL, so, can't procced to delete. ");
			}
			
			driver = driver.switchTo().defaultContent();
			apputil.logout();

		}else{
			  	displayOnConsole("testDeleteInflncStrngth() - User login UNSUCCESSFUL, deleting Influence Strengths UNSUCCESSFUL ");
			  	logFailMessageInExtentTest("testDeleteInflncStrngth() - User login UNSUCCESSFUL, deleting Influence Strengths UNSUCCESSFUL ");
		}
		
		logInfoMessageInExtentTest(" ********** Ending testing : Influence Strengths - Delete flow *************");
	}

	@Test(priority = 2)
	public void testEditInflncStrngth(){
		
		//start of test
		extentTest = extent.startTest("testEditInflncStrngth ");
		logInfoMessageInExtentTest(" ********** Starting testing : Influence Strengths - Edit flow *************");
		
		sheet_name = "InfluenceStrength";
		
		//application username, password
		if(username == null){
			username = getUserName();
			password = getUserPassword();
		}
		
		/*Reading data from excel-data sheet - "InfluenceStrength" */
		
		//Influence Strengths - Name : For Creation
		strTarget = Excel.readFromExcel(excel_file, sheet_name, FOUR, ONE);
		
		//Influence Strengths - Role : For Creation
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
		
		
		//Influence Strengths - New name : For Editing
		String strReplacement = Excel.readFromExcel(excel_file, sheet_name, SIX, ONE);
				
		apputil.login(username, password);//Added 
		if(isElementPresent(driver, By.xpath("(//div[@id='navWrapper']/ul[@id='sectionsNav']/descendant::a[@title='More'])"))){// changed

			
			displayOnConsole("testEditInflncStrngth() - User login SUCCESSFUL ");
			logInfoMessageInExtentTest(" ********** User Login Successful *************");
			
			boolean noerror = InflncStrngthCreating (txtMsg, strTarget, roleList, toOpen_Link_InputData, toOpen_Create_NewPage);
			if(noerror == true){
				
				if(isElementPresent(driver, popUp_Exists_Description)){
					displayOnConsole("testEditInflncStrngth() - Creating new Influence Strengths UNSUCCESSFUL ");
					
					String details = "Influence Strengths already exists, editing existing one ";
					displayOnConsole("testinfluencestrengthEditing - "+details);
					
					String screenshot = getScreenshot(driver, "Influence_Strength_Condn_Exists");//changed
					Actions actions = new Actions(driver);
					Action action = actions.moveToElement(driver.findElement(popUp_Exists_Close),TWO,ZERO).click().build();
					action.perform();
					logInfoMessageInExtentTest("testEditInflncStrngth() - " + details);
					//Added
					boolean lookupInfluenceStrength = lookupDataOptionDescription(strTarget);
					boolean deleting = InflncStrngthDeleting(strTarget);
					if(deleting == true){
						logPassMessageInExtentTest("Influence Strength before re-creating deleted ");
					}
					logInfoMessageInExtentTest(details);
					noerror = InflncStrngthCreating (txtMsg, strTarget, roleList, toOpen_Link_InputData, toOpen_Create_NewPage);
				} else {
						displayOnConsole("testEditInflncStrngth() - Creating new Influence Strengths SUCCESSFUL ");
						logInfoMessageInExtentTest("testEditInflncStrngth() - Creating new Influence Strengths SUCCESSFUL ");
				}
				
				//Finding for editing
				displayOnConsole("testEditInflncStrngth() - Finding the Influence Strengths to Edit.");
				boolean lookupInfluenceStrength = lookupDataOptionDescription(strTarget);//Changed
				
				if(lookupInfluenceStrength){//Changed
					displayOnConsole("testEditInflncStrngth() - Finding the Influence Strengths to Edit SUCCESSFUL.");
					logPassMessageInExtentTest("testEditInflncStrngth() - Finding the Influence Strengths to Edit SUCCESSFUL.");
					
					boolean influencestrengthedit = InflncStrngthEditing(strTarget, strReplacement);//Changed
					
					if(influencestrengthedit){//Changed
						
						if(isElementPresent(driver, popUp_Exists_Description)){
							
							String details = "Influence Strengths with same name already exists ";
							displayOnConsole("testEditInflncStrngth() : "+details);
							
							String screenshot = getScreenshot(driver, "Edit_Influence_Strength_Condn_Exists");//Changed
							Actions actions = new Actions(driver);
							Action action = actions.moveToElement(driver.findElement(popUp_Exists_Close),TWO,ZERO).click().build();
							action.perform();
							logInfoMessageInExtentTest("testEditInflncStrngth() - " + details);
						} else {
							displayOnConsole("testEditInflncStrngth() - Editing Influence Strengths SUCCESSFUL ");
							logPassMessageInExtentTest("testEditInflncStrngth() - Editing Influence Strengths SUCCESSFUL ");
							
							displayOnConsole("testEditInflncStrngth() - Verifying the Edited Influence Strengths.");
							logInfoMessageInExtentTest("testEditInflncStrngth() - Verifying the Edited Influence Strengths.");
							
							//Verifying whether edited or not.
							lookupInfluenceStrength = lookupDataOptionDescription(strReplacement);
							if(lookupInfluenceStrength == true) {
								displayOnConsole("testEditInflncStrngth() - Verifying Edited Influence Strengths SUCCESSFUL ");
								logPassMessageInExtentTest("testEditInflncStrngth() - Verifing Edited Influence Strengths SUCCESSFUL ");
							}
							else {
								displayOnConsole("testEditInflncStrngth() - Verifying Edited Influence Strengths UNSUCCESSFUL ");
								logFailMessageInExtentTest("testEditInflncStrngth() - Edited Influence Strengths UNSUCCESSFUL ");
							}	
						}
					} else{
								displayOnConsole("testEditInflncStrngth() - Editing Influence Strengths UNSUCCESSFUL ");
								logPassMessageInExtentTest("testEditInflncStrngth() - Editing Influence Strengths UNSUCCESSFUL ");
					}
				} else {
							displayOnConsole("testEditInflncStrngth() - Search for Influence Strengths itself UNSUCCESSFUL, so, can't procced to edit. ");
							logFailMessageInExtentTest("testEditInflncStrngth() - Search for Influence Strengths itself UNSUCCESSFUL, so, can't procced to edit. ");
				}
				
			} else {
						displayOnConsole("testEditInflncStrngth() - Creating new Influence Strengths itself UNSUCCESSFUL, so, can't procced to edit. ");
						logFailMessageInExtentTest("testEditInflncStrngth() - Creating new Influence Strengths itself UNSUCCESSFUL, so, can't procced to edit. ");
			}
			
			driver = driver.switchTo().defaultContent();
			apputil.logout();

		} else{
			
			displayOnConsole("testEditInflncStrngth() - User login UNSUCCESSFUL, Editing Influence Strengths UNSUCCESSFUL ");
			logFailMessageInExtentTest("testEditInflncStrngth() - User login UNSUCCESSFUL, Editing Influence Strengths UNSUCCESSFUL ");
		}
		
		logInfoMessageInExtentTest(" ********** Ending testing : Influence Strengths - Edit flow *************");
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
	
	boolean InflncStrngthCreating (String txtMsg, String strTarget, List<String> roleList, By toOpen_Link_InputData, By toOpen_Create_NewPage) {
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

			boolean tools_page_shown = apputil.gotoToolsPage();//Changed
			if(tools_page_shown == false){//Added
				logFailMessageInExtentTest("Tools page failed to load");//Changed
			}
			wait(TWO);//Changed
			
			displayOnConsole("InflncStrngthCreating() - "+txtMsg);
			logInfoMessageInExtentTest(" ********** " + txtMsg + "*************");
			
			//go to internal html
			//driver = driver.switchTo().frame(driver.findElement(tool_frame));//Changed
			
			wait(ONE);
			//click Influence Strengths link
			click(toOpen_Link_InputData);
			//wait
			wait(TWO);//Changed
			

			
			displayOnConsole("InflncStrngthCreating() - Description read from excel-sheet '" + sheet_name +"' is '" +strTarget + "'");
			logInfoMessageInExtentTest(" InflncStrngthCreating() - Description read from excel-sheet '" + sheet_name +"' is '" +strTarget + "'");
			//click to open new Influence Strengths popup
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
			logFailMessageInExtentTest("InflncStrngthCreating() :: Problem : "+ problem.fillInStackTrace());
		}
		
		return noerror;
	}
	
	boolean InflncStrngthDeleting(String strToDelete) {
		boolean deleteDataOptionFlag = true;
		
		try {
			
			//Entity : to Delete
			strToDelete = strToDelete.trim();
			
			displayOnConsole("InflncStrngthDeleting() - Deleting Entity : "+ strToDelete);
			logInfoMessageInExtentTest("InflncStrngthDeleting() - Deleting Entity : "+ strToDelete);
			
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
			logInfoMessageInExtentTest("InflncStrngthDeleting() :: Problem : "+ problem.getMessage());
		}
		
		return deleteDataOptionFlag;
	}
	
	boolean InflncStrngthEditing(String strTarget, String strReplacement) {
		boolean editDataOptionFlag = true;
		
		try {
			
			displayOnConsole("InflncStrngthEditing() - Editing Entity : "+ strTarget);
			logInfoMessageInExtentTest("InflncStrngthEditing() - Editing Entity : "+ strTarget);
			
			//Checking before editing
			boolean lookup = lookupDataOptionDescription(strReplacement);//Added
			logInfoMessageInExtentTest("Checking Influence Strength before editing ");//Added
			if(lookup){//Added
				logInfoMessageInExtentTest("Influence Strength before editing was exist, deleting ");
				boolean deleting = InflncStrngthDeleting(strReplacement);
				if(deleting == true){
					logInfoMessageInExtentTest("Influence Strength before editing deleted ");
				}
			}//Added
			
			//Influence Strengths - New Roles : For Editing
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
			tools_condn_text_edit = By.xpath("(//*[@id='highlightkeyword'][contains(text(),'"+ strTarget +"')]/parent::td/preceding-sibling::td/parent::tr//*[self::td]["+SIX+"]/a["+TWO+"]/img)");
			click(tools_condn_text_edit);
			wait(ONE);
			
			//enter InfluenceStrength description
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
			logInfoMessageInExtentTest("InflncStrngthEditing :: Problem " + problem.fillInStackTrace());
			
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
			displayOnConsole("lookupDataOptionDescription() - Searched InfluenceStrength found at page no. : "+filter);
		
		if(navigationoptions.size() == ZERO || navigationoptions.size() == ONE)
		{
			lookUpDataFlag = isElementPresent(driver, lookUp_entity);
		}
		
		return lookUpDataFlag;
	}

	void logoutChecking(){//added
		driver = driver.switchTo().defaultContent();//Added
		if(isElementPresent(driver, By.xpath("(//div[@id='navWrapper']/ul[@id='sectionsNav']/descendant::a[@title='More'])"))){
			apputil.logout();//Added
		}
	}
}
