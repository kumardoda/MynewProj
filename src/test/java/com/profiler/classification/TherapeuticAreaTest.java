package com.profiler.classification;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import com.profiler.utils.AppUtil;
import com.profiler.utils.Excel;

public class TherapeuticAreaTest extends AppUtil {
	
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
	String sheet_name = "CreateTherapeuticArea";
	String application_sheet = "Application";
	
	By more = By.xpath("(//div[@id='navWrapper']/ul[@id='sectionsNav']/descendant::a[@title='More'])");
	By more_tool = By.xpath("(//div[@id='navWrapper']/ul[@id='sectionsNav']/descendant::a[@id='section_tools'])");
	By tool_frame = By.xpath("(//*[@id='toolsContent'])");
	By therapeutic_area_link = By.xpath("(//*[@id='container']/table/descendant::div[@id='content']/table/descendant::div[@id='leftDiv']/descendant::a[contains(text(),'Therapeutic Area')])");
	
	String therapeuticareadescription="";
	
	By therapeutic_areas_text = By.xpath("(//*[@id='highlightkeyword'][contains(text(),'"+therapeuticareadescription+"')])");
	
	By therapeutic_area_exists_close = By.xpath("(//*[@id='resultDiv']/div[@id='messageDiv']/descendant::a[@title='close']["+ONE+"])");
	
	By therapeutic_area_new_box_input = By.xpath("(//*[@id='profilerPopupDiv']/descendant::input[@id='description'])");
	
	By therapeutic_area_new_box_save = By.xpath("(//*[@id='profilerPopupDiv']/div[@id='profilerPopupMenu']/table/descendant::a/img[@alt='Save'])");
	
	By tools_new_therapeutic_area_link = By.xpath("(//*[@id='menuTable']/descendant::a[starts-with(@href,'javascript:openProfilerPopup')])");
	
	By therapeutic_area_exists_description = By.xpath("(//*[@id='messageDiv']/descendant::div[contains(text(),'Entity with this Description already exists')])");
	
	By tools_therapeutic_areas_select = By.xpath("(//*[@id='jumpTo'])");
	
	By segmentation_results = By.xpath("(//*[@id='homePageRisingStars']/descendant::span[contains(text(),'TOP SEGMENTATION RESULTS')])");
	
	By tools_therapeutic_areas_forward_link = By.xpath("(//img[@name='forward'])");
	
	By tools_therapeutic_areas_text_delete = By.xpath("(//*[@id='highlightkeyword'][contains(text(),'"+ therapeuticareadescription +"')]/parent::td/preceding-sibling::td/parent::tr//*[self::td]["+FIVE+"]/a["+ONE+"])");
	
	String deletetherapeuticareatext = "Delete 1 Therapeutic Area?";
	
	String processingmessagetext = "Your request is in queue. For more details check";// \"Profile Data Management->Realtime Mass Update tracker\" screen";
	
	By tools_therapeutic_areas_text_edit = By.xpath("(//*[@id='highlightkeyword'][contains(text(),'"+ therapeuticareadescription +"')]/parent::td/preceding-sibling::td/parent::tr//*[self::td]["+FIVE+"]/a["+TWO+"])");
	
	By tools_therapeutic_areas_text_checkbox = By.xpath("(//*[@id='highlightkeyword'][contains(text(),'"+therapeuticareadescription+"')]/parent::td/preceding-sibling::td/descendant::input[@type='checkbox'])");
	
	By tools_delete_therapeutic_area_link = By.xpath("(//*[@id='menuTable']/descendant::a[starts-with(@href,'javascript:sortableListHandler_delete')])");
	
	String deletemultipletherapeuticareatext = "Delete 3 Therapeutic Areas?";
	
	By tools_therapeutic_areas_page_select = By.xpath("(//select[@id='bbox'])");
	
	String navigatetotools = "action=newFrameset&sourceTab=tools&siteArea=admin&isToolsNavigation=true";
	
	//AppUtil appUtil = new AppUtil();//Added
	
	String edittherapeuticareadescription = "";
	
	@Test(priority = 1)
	public void testCreateTherapeuticArea(){
		
		try {
			extentTest = extent.startTest("createTherapeuticArea ");
			//sheet_name = "CreateTherapeuticArea";
			
			if(username == null){
				username = getUserName();
				password = getUserPassword();
			}

			driver = driver.switchTo().window(driver.getWindowHandle());
			
			loginIntoApp();
			if(isElementPresent(driver, more_tool)){// changed
				
				//displayOnConsole("testCreateTherapeuticArea() - User login SUCCESSFUL ");
				
				navigateToToolsWebPage();//Added
				navigateToTherapeuticArea();
				//read therapeutic area description from test data
				therapeuticareadescription = Excel.readFromExcel(excel_file, sheet_name, ONE, ONE);
				
				boolean noerror = therapeuticAreaCreating(therapeuticareadescription);
				if(noerror == true){
					if(isElementPresent(driver, therapeutic_area_exists_description)){
						
						String details = "therapeutic area already exists, give another therapeutic area description, else re-creating ";
						//displayOnConsole("testCreateTherapeuticArea() -  "+details);
						getScreenshot(driver, "Therapeutic_Area_Exists");
						Actions actions = new Actions(driver);
						Action action = actions.moveToElement(driver.findElement(therapeutic_area_exists_close),TWO,ZERO).click().build();
						action.perform();
						
						//Checking before creating
						boolean lookup = lookupTherapeuticAreaDescription();//Added
						logInfoMessageInExtentTest("Checking Therapeutic Area before re-creating ");//Added
						if(lookup){//Added
							boolean deleting = therapeuticAreaDeleting();
							if(deleting == true){
								logInfoMessageInExtentTest("Therapeutic Area before re-creating deleted ");
							}
						}//Added
						noerror = therapeuticAreaCreating(therapeuticareadescription);
						logInfoMessageInExtentTest(details);
						lookupTherapeuticAreaDescription();
						
					}else{
						
						boolean therapeuticarea = lookupTherapeuticAreaDescription();
						if(therapeuticarea){
							
							//displayOnConsole("Added new therapeutic area ");
							logInfoMessageInExtentTest("testCreateTherapeuticArea() - Added new therapeutic area ");
						}
						
						logPassMessageInExtentTest("testCreateTherapeuticArea() - Creating of new therapeutic area description SUCCESSFUL ");
					}
				}else{
					
					logFailMessageInExtentTest("testCreateTherapeuticArea() - Creating of new therapeutic area description UNSUCCESSFUL ");
				}
				driver = driver.switchTo().defaultContent();
				logInfoMessageInExtentTest("testCreateTherapeuticArea() - user logout started ");
				logout();

			} else {
				
				//displayOnConsole("testCreateTherapeuticArea() - User login UNSUCCESSFUL ");
				logFailMessageInExtentTest("testCreateTherapeuticArea() - User login UNSUCCESSFUL, creating of new therapeutic area description UNSUCCESSFUL ");
			}
		} catch (Exception problem) {
			logFailMessageInExtentTest("testCreateTherapeuticArea() - "+problem.fillInStackTrace());
		}
	}

	private void loginIntoApp() {
		
		try {
			login(username, password);
		} catch (Exception problem) {
			problem.printStackTrace();
		}
		driver.switchTo().defaultContent();
		driver.navigate().refresh();
		wait(THREE);
		return;
	}

	boolean lookupTherapeuticAreaDescription() {
		
		boolean therapeuticarea = false;
		try {
			String filter = String.valueOf(therapeuticareadescription.charAt(ZERO)).toUpperCase();
			
			selectByValue(driver, tools_therapeutic_areas_select, filter);
			wait(TWO);
			
			therapeuticareadescription = therapeuticareadescription.trim();
			therapeutic_areas_text = By.xpath("(//*[@id='highlightkeyword'][contains(text(),'"+therapeuticareadescription+"')])");
			
			filter = String.valueOf(ZERO);
			
			List<WebElement> navigationoptions = null;
			try
			{
				navigationoptions = driver.findElements(By.xpath("(//select[@id='bbox']//*[self::option])"));
			}catch(Exception problem){
				logFailMessageInExtentTest("problem  " + problem.fillInStackTrace());
			}
			
			for(int pages = TWO; pages <= navigationoptions.size(); pages++){
				if((therapeuticarea = isElementPresent(driver, therapeutic_areas_text)) == true){
					break;
				}else{
					click(By.xpath("(//table[@id='ct']/thead/tr["+ONE+"]/td["+TWO+"]/a)"));
					therapeuticarea = isElementPresent(driver, therapeutic_areas_text);
					if(therapeuticarea == true)
						break;
				}
				filter = String.valueOf(pages);
				selectByValue(driver, tools_therapeutic_areas_page_select, filter);
				wait(ONE);
			}
			//displayOnConsole("elements "+navigationoptions.size());
			if(navigationoptions.size() == ONE)
			{
				therapeuticarea = isElementPresent(driver, therapeutic_areas_text);
			}
		} catch (Exception problem) {
			logFailMessageInExtentTest("problem  " + problem.fillInStackTrace());
		}
		
		return therapeuticarea;
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
		extentTest.log(LogStatus.INFO, details);
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

	boolean therapeuticAreaCreating(String therapeuticareadescription) {
		boolean noerror = true;

		try {
			

			//displayOnConsole(sheet_name + " " + therapeuticareadescription + " description ");
			
			//click new therapeutic area
			click(tools_new_therapeutic_area_link);
			wait(ONE);
			
			click(therapeutic_area_new_box_save);
			wait(TWO);
			try {
				therapeuticAreaCreatingAlertProcessing();
			} catch (Exception problem) {
				logInfoMessageInExtentTest(problem.getMessage()+ " , continuing with processing. ");
			}
			wait(ONE);
			
			//enter therapeutic area description
			click(therapeutic_area_new_box_input);
			driver.findElement(therapeutic_area_new_box_input).clear();
			enter(therapeutic_area_new_box_input, therapeuticareadescription);
			
			//click save
			click(therapeutic_area_new_box_save);
			wait(TWO);
			
		} catch (Exception problem) {
			noerror=false;
			logInfoMessageInExtentTest("Problem observed "+ problem.fillInStackTrace()+ " , continuing with processing. ");
		}
		
		return noerror;
	}

	private void therapeuticAreaCreatingAlertProcessing() throws Exception{
		try{
			Alert alertbox = driver.switchTo().alert();
			String alerttext = alertbox.getText();
			alerttext.contains("Therapeutic Area is required field. Please enter a value for Therapeutic Area.");
			alertbox.accept();
		}catch(Exception problem){
			throw new Exception(problem);
		}
	}

	void navigateToTherapeuticArea() {
		//driver = driver.switchTo().defaultContent();
		
		//click for more tools
		//waitForElementtoBeVisible(segmentation_results);
		//clickMoreTool();
		
		//Above actions were temporarily not working correctly in these automated tests , so using below workaround to navigate
		//driver.navigate().to(driver.getCurrentUrl()+navigatetotools);//Changed
		//go to internal html

		
		//displayOnConsole("Adding new therapeutic area ");
		
		//click therapeutic area link
		click(therapeutic_area_link);
		//wait
		wait(ONE);
	}
	
	@Test(priority = 3)
	public void testDeleteTherapeuticArea(){
		
		try {
			//starting of test 
			extentTest = extent.startTest("testDeleteTherapeuticArea ");
			
			//delete therapeutic area test data
			//sheet_name = "CreateTherapeuticArea";
			
			if(username == null){
				username = getUserName();
				password = getUserPassword();
			}
			
			driver = driver.switchTo().window(driver.getWindowHandle());
			loginIntoApp();
			if(isElementPresent(driver, more_tool)){// changed
			
				//displayOnConsole("testDeleteTherapeuticArea() - User login SUCCESSFUL ");
				navigateToToolsWebPage();//Added
				navigateToTherapeuticArea();
				
				//read therapeutic area description from test data
				therapeuticareadescription = Excel.readFromExcel(excel_file, sheet_name, TWO, ONE);

				boolean noerror = therapeuticAreaCreating(therapeuticareadescription);
				
				if(noerror == true){
					
					if(isElementPresent(driver, therapeutic_area_exists_description)){

						String details = "therapeutic area already exists, deleting existing therapeutic area description ";
						//displayOnConsole("testDeleteTherapeuticArea() -  "+details);
						getScreenshot(driver, "Therapeutic_Area_Exists");
						Actions actions = new Actions(driver);
						Action action = actions.moveToElement(driver.findElement(therapeutic_area_exists_close),TWO,ZERO).click().build();
						action.perform();
						logInfoMessageInExtentTest("testDeleteTherapeuticArea() - "+details);
						
						//Checking before creating
						boolean lookup = lookupTherapeuticAreaDescription();//Added
						logInfoMessageInExtentTest("Checking Therapeutic Area before re-creating ");//Added
						if(lookup){//Added
							boolean deleting = therapeuticAreaDeleting();
							if(deleting == true){
								logInfoMessageInExtentTest("Therapeutic Area before re-creating deleted ");
							}
						}//Added
						noerror = therapeuticAreaCreating(therapeuticareadescription);
					}else{
						
						logPassMessageInExtentTest("testDeleteTherapeuticArea() - Creating of new therapeutic area description for deleting SUCCESSFUL ");

					}
					
					boolean lookupProfile = lookupTherapeuticAreaDescription();
					
					if(lookupProfile){

						boolean deletingprofilerole = therapeuticAreaDeleting();
						if(deletingprofilerole){
							
							lookupProfile = lookupTherapeuticAreaDescription();
							
							if(lookupProfile == false){
								
								logPassMessageInExtentTest("testDeleteTherapeuticArea() - delete of therapeutic area description SUCCESSFUL ");
							}
							else{

								logFailMessageInExtentTest("testDeleteTherapeuticArea() - delete of therapeutic area description UNSUCCESSFUL ");
							}
							
						}else{

							logFailMessageInExtentTest("testDeleteTherapeuticArea() - delete of therapeutic area description UNSUCCESSFUL ");
							
						}
					}
					
				}else{
					
					logFailMessageInExtentTest("testDeleteTherapeuticArea() - Creating of new therapeutic area description for deleting UNSUCCESSFUL ,delete of therapeutic area description UNSUCCESSFUL ");//Changed
					
				}
				driver = driver.switchTo().defaultContent();
				logInfoMessageInExtentTest("testDeleteTherapeuticArea() - user logout started ");
				logout();
			}else{
				
				//displayOnConsole("testDeleteTherapeuticArea() - User login UNSUCCESSFUL ");
				logFailMessageInExtentTest("testDeleteTherapeuticArea() - User login UNSUCCESSFUL, delete of therapeutic area description UNSUCCESSFUL ");
			
			}
		} catch (Exception problem) {
			logFailMessageInExtentTest("testCreateTherapeuticArea() - "+problem.fillInStackTrace());
		}
		

	}

	//Navigate webpage to Tools webpage
	private void navigateToToolsWebPage() {
		boolean tools_page_shown = gotoToolsPage();//Added
		if(tools_page_shown == false){//Added
			logFailMessageInExtentTest("problem  ToolsPageLoading_Failed");//Changed
			
		}
		wait(TWO);
	}

	boolean therapeuticAreaDeleting() {
		
		boolean deletetherapeuticArea = true;
		
		try {
			
			//delete of therapeutic area description
			therapeuticareadescription = therapeuticareadescription.trim();
			tools_therapeutic_areas_text_delete = By.xpath("(//descendant::div[@id='highlightkeyword'][contains(text(),'"+ therapeuticareadescription +"')]/parent::td/preceding-sibling::td/parent::tr/td["+FIVE+"]/a["+ONE+"]/img)");
			tools_therapeutic_areas_text_checkbox = By.xpath("(//descendant::div[@id='highlightkeyword'][contains(text(),'"+ therapeuticareadescription +"')]/parent::td/preceding-sibling::td/descendant::input[@type='checkbox'])");
			click(tools_therapeutic_areas_text_checkbox);
			wait(ONE);
			click(tools_therapeutic_areas_text_delete);
			wait(ONE);
			
			try {
				therapeuticAreaDeletingAlertsProcessing();
			} catch (Exception problem) {
				logInfoMessageInExtentTest("Problem: "+ problem.toString() + " " + problem.getMessage() + " , continuing with processing. ");
			}
			wait(TWO);
			
			try {
				driver.switchTo().defaultContent();
			} catch (Exception problem) {
				problem.printStackTrace();
			}
			
			driver.navigate().refresh();
			driver.switchTo().frame(driver.findElement(tool_frame));
			navigateToTherapeuticArea();
			
		} catch (Exception problem) {
			
			deletetherapeuticArea = false;
			logInfoMessageInExtentTest("Problem: "+ problem.toString() + " " + problem.getMessage() + " , continuing with processing. ");
		}
		
		return deletetherapeuticArea;
		
	}

	private void therapeuticAreaDeletingAlertsProcessing() throws Exception{
		
		try {

			//accept delete therapeutic area description alerts
			Alert alertbox = driver.switchTo().alert();
			String alertmessagetext = alertbox.getText();
			boolean textcontent = alertmessagetext.contains(deletetherapeuticareatext);
			if(textcontent){
				logInfoMessageInExtentTest(deletetherapeuticareatext + " message shown ");
			}
			
			//cancel delete
			alertbox.dismiss();
			wait(ONE);
			
			//again select therapeutic area row for delete
			click(tools_therapeutic_areas_text_checkbox);
			wait(ONE);
			click(tools_therapeutic_areas_text_delete);
			wait(ONE);
	
			alertbox.accept();
			wait(TWO);
			Alert alertboxtwo = driver.switchTo().alert();
			alertmessagetext = alertboxtwo.getText();
			alertboxtwo.accept();
			
		} catch (Exception problem) {
			
			System.out.println(""+problem.getMessage());
			logInfoMessageInExtentTest("Problem: "+ problem.toString() + " " + problem.getMessage() + " , continuing with processing. ");
			throw new Exception(problem);
			
		}
	
	}
	
	@Test(priority = 2)
	public void testEditTherapeuticArea(){
		
		try {
			//start of test
			extentTest = extent.startTest("testEditTherapeuticArea ");
			
			//edit therapeutic area test data
			//sheet_name = "CreateTherapeuticArea";
			
			//application username, password
			if(username == null){
				
				username = getUserName();
				password = getUserPassword();
				
			}
			
			driver = driver.switchTo().window(driver.getWindowHandle());
			
			loginIntoApp();
			if(isElementPresent(driver, more_tool)){// changed
				
				//displayOnConsole("testEditTherapeuticArea() - user login SUCCESSFUL ");
				
				navigateToToolsWebPage();//Added
				navigateToTherapeuticArea();
				
				//read therapeutic area description from test data
				therapeuticareadescription = Excel.readFromExcel(excel_file, sheet_name, THREE, ONE);

				boolean noerror = therapeuticAreaCreating(therapeuticareadescription);
				if(noerror == true){
					
					if(isElementPresent(driver, therapeutic_area_exists_description)){
						
						String details = "therapeutic area already exists, editing existing therapeutic area description ";
						//displayOnConsole("testTherapeuticAreaEditing - "+details);
						getScreenshot(driver, "Therapeutic_Area_Exists");
						Actions actions = new Actions(driver);
						Action action = actions.moveToElement(driver.findElement(therapeutic_area_exists_close),TWO,ZERO).click().build();
						action.perform();
						logInfoMessageInExtentTest("testEditTherapeuticArea() - "+details);
						//Checking before creating
						boolean lookup = lookupTherapeuticAreaDescription();//Added
						logInfoMessageInExtentTest("Checking Therapeutic Area before re-creating ");//Added
						if(lookup){//Added
							boolean deleting = therapeuticAreaDeleting();
							if(deleting == true){
								logInfoMessageInExtentTest("Therapeutic Area before re-creating deleted ");
							}
						}//Added
						noerror = therapeuticAreaCreating(therapeuticareadescription);
						
					} else {
						
						 logInfoMessageInExtentTest("testEditTherapeuticArea() - Creating of new therapeutic area description for editing SUCCESSFUL ");
						 
					}
					
					boolean lookupProfile = lookupTherapeuticAreaDescription();
					
					if(lookupProfile){

						boolean profileroleedit = therapeuticAreaEditing();
						
						if(profileroleedit){
							lookupProfile = lookupTherapeuticAreaDescription();
							if(lookupProfile == true){
								logPassMessageInExtentTest("testEditTherapeuticArea() - editing of therapeutic area description SUCCESSFUL ");
							}
							else{
								logFailMessageInExtentTest("testEditTherapeuticArea() - editing of therapeutic area description UNSUCCESSFUL ");
							}
							
						}else{
							
							String details = "therapeutic area already exists, provide another therapeutic area description ";
							getScreenshot(driver, "Therapeutic_Area_Exists");
							Actions actions = new Actions(driver);
							Action action = actions.moveToElement(driver.findElement(therapeutic_area_exists_close),TWO,ZERO).click().build();
							action.perform();
							logInfoMessageInExtentTest("testEditTherapeuticArea() - "+details);
							
							//Checking before editing
							therapeuticareadescription = edittherapeuticareadescription;
							boolean lookup = lookupTherapeuticAreaDescription();//Added
							logInfoMessageInExtentTest("Checking Therapeutic Area before re-editing ");//Added
							if(lookup){//Added
								boolean deleting = therapeuticAreaDeleting();
								if(deleting == true){
									logInfoMessageInExtentTest("Therapeutic Area before re-editing deleted ");
								}
							}//Added
							noerror = therapeuticAreaEditing();
						}
					}
					
				} else {
					logFailMessageInExtentTest("testEditTherapeuticArea() - Creating of new therapeutic area description for editing UNSUCCESSFUL , editing of therapeutic area description UNSUCCESSFUL ");//Changed
				}
				driver = driver.switchTo().defaultContent();
				logInfoMessageInExtentTest("testEditTherapeuticArea() - user logout started ");
				logout();
			}else{
				
				//displayOnConsole("testEditTherapeuticArea() - user login UNSUCCESSFUL ");
				logFailMessageInExtentTest("testEditTherapeuticArea() - User login UNSUCCESSFUL, editing of therapeutic area description UNSUCCESSFUL ");
			}
		} catch (Exception problem) {
			logFailMessageInExtentTest("testEditTherapeuticArea() - "+problem.fillInStackTrace());
		}
	}
	
	boolean therapeuticAreaEditing(){
		
		boolean therapeuticareaediting = true;
		
		try {
			
			//read therapeutic area description from test data
			edittherapeuticareadescription = Excel.readFromExcel(excel_file, sheet_name, FOUR, ONE);

			//edit therapeutic area description
			therapeuticareadescription = therapeuticareadescription.trim();
			tools_therapeutic_areas_text_edit = By.xpath("(//*[@id='highlightkeyword'][contains(text(),'"+ therapeuticareadescription +"')]/parent::td/preceding-sibling::td/parent::tr//*[self::td]["+FIVE+"]/a["+TWO+"]/img)");
			click(tools_therapeutic_areas_text_edit);
			wait(ONE);
			
			//enter therapeutic area description
			click(therapeutic_area_new_box_input);
			driver.findElement(therapeutic_area_new_box_input).clear();
			enter(therapeutic_area_new_box_input, edittherapeuticareadescription);
			
			//click save
			click(therapeutic_area_new_box_save);
			wait(ONE);
			
			//accept edit therapeutic area description alerts
			Alert alertbox = driver.switchTo().alert();
			//String alertmessagetext = alertbox.getText();
			alertbox.accept();
			wait(TWO);
			
			therapeuticareadescription = edittherapeuticareadescription.trim();
			
		} catch (Exception problem) {

			therapeuticareaediting = false;
			logInfoMessageInExtentTest("Problem observed " + problem.fillInStackTrace()+ " , continuing with processing. ");
			
		}
		
		return therapeuticareaediting;
	}
	
	@Test(priority = 4)
	public void testMultipleTherapeuticAreasDelete()
	{
		try {
			//start of test
			extentTest = extent.startTest("testMultipleTherapeuticAreasDelete ");
			
			//multiple therapeutic area test data
			//sheet_name = "CreateTherapeuticArea";
			
			//application username, password
			if(username == null)
			{
				username = getUserName();
				password = getUserPassword();
			}
			
			driver = driver.switchTo().window(driver.getWindowHandle());
			
			loginIntoApp();
			if(isElementPresent(driver, more_tool)){// changed
				
				navigateToToolsWebPage();//Added
				navigateToTherapeuticArea();
				
				String therapeuticareaone = checkingTherapeuticAreaCreate(FIVE,ONE);
				String therapeuticareatwo = checkingTherapeuticAreaCreate(SIX,ONE);
				String therapeuticareathree = checkingTherapeuticAreaCreate(SEVEN,ONE);
				
				String filter = String.valueOf(therapeuticareaone.charAt(ZERO)).toUpperCase();
				
				selectByValue(driver, tools_therapeutic_areas_select, filter);
				wait(TWO);
				
				therapeuticareaone = therapeuticareaone.trim();
				therapeuticareatwo = therapeuticareatwo.trim();
				therapeuticareathree = therapeuticareathree.trim();
				
				//added 
				click(tools_delete_therapeutic_area_link);
				Alert alertbox = driver.switchTo().alert();
				String alertmessagetext = alertbox.getText();
				boolean textcontent = alertmessagetext.contains("No Therapeutic Areas are selected. Please select Therapeutic Areas to delete.");
				alertbox.accept();
				wait(ONE);
				
				checkTherapeuticAreaRow(therapeuticareaone);
				checkTherapeuticAreaRow(therapeuticareatwo);
				checkTherapeuticAreaRow(therapeuticareathree);
				
				//delete selected multiple therapeutic areas
				click(tools_delete_therapeutic_area_link);
				
				//accept delete therapeutic area description alerts
				alertbox = driver.switchTo().alert();
				alertmessagetext = alertbox.getText();
				textcontent = alertmessagetext.contains(deletemultipletherapeuticareatext);
				if(textcontent){
					logInfoMessageInExtentTest(deletemultipletherapeuticareatext + " message shown ");
				}
				alertbox.accept();
				wait(TWO);
				
				try {
					Alert alertboxtwo = driver.switchTo().alert();
					alertmessagetext = alertboxtwo.getText();
					textcontent = alertmessagetext.contains(processingmessagetext);
					if(textcontent){
						logInfoMessageInExtentTest(processingmessagetext + " message shown ");
					}
					alertboxtwo.accept();
				} catch (Exception problem) {
					problem.printStackTrace();
				}
				wait(TWO);
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
						click(therapeutic_area_link);
						//wait
						wait(ONE);
					}else{
						wait(ONE);
						driver.switchTo().frame(driver.findElement(tool_frame));
					}
				
				logInfoMessageInExtentTest("testMultipleTherapeuticAreasDelete() - deleted multiple therapeutic areas ");
				driver = driver.switchTo().defaultContent();
				logInfoMessageInExtentTest("testMultipleTherapeuticAreasDelete() - user logout started ");
				
					logout();
			}else{
				
				//displayOnConsole("testMultipleTherapeuticAreasDelete() - User login UNSUCCESSFUL ");
				logFailMessageInExtentTest("testMultipleTherapeuticAreasDelete() - User login UNSUCCESSFUL, delete of multiple therapeutic areas description UNSUCCESSFUL ");
			}
		} catch (Exception problem) {
			logFailMessageInExtentTest("testMultipleTherapeuticAreasDelete() - "+problem.fillInStackTrace());
		}

	}

	void clickMoreTool() {
		Actions actions = new Actions(driver);
		Action action = actions.click(driver.findElement(more)).pause(ONE).moveToElement(driver.findElement(more_tool)).click().build();
		action.perform();
		//Actions actionsmore = new Actions(driver);
		//Action actionmore = actionsmore.moveToElement(driver.findElement(more_tool)).clickAndHold().pause(TWO).release().build();
		//actionmore.perform();
	}

	void checkTherapeuticAreaRow(String therapeuticareadescription) {
		
		boolean therapeuticarea = false;
		try {
			therapeutic_areas_text = By.xpath("(//*[@id='highlightkeyword'][contains(text(),'"+therapeuticareadescription+"')])");
				
			String filter = String.valueOf(ZERO);
			List<WebElement> navigationoptions = driver.findElements(By.xpath("(//select[@id='bbox']//*[self::option])"));
			
			for(int pages = TWO; pages <= navigationoptions.size(); pages++){
				if((therapeuticarea = isElementPresent(driver, therapeutic_areas_text)) == true){
					break;
				}else{
					click(By.xpath("(//table[@id='ct']/thead/tr["+ONE+"]/td["+TWO+"]/a)"));
					therapeuticarea = isElementPresent(driver, therapeutic_areas_text);
					if(therapeuticarea == true)
						break;
				}
				filter = String.valueOf(pages);
				selectByValue(driver, tools_therapeutic_areas_page_select, filter);
				wait(ONE);
			}
			
			//displayOnConsole("elements "+navigationoptions.size());
			if(navigationoptions.size() == ONE)
			{
				therapeuticarea = isElementPresent(driver, therapeutic_areas_text);
				
			}
			
			if(therapeuticarea == true){
				tools_therapeutic_areas_text_checkbox = By.xpath("(//*[@id='highlightkeyword'][contains(text(),'"+therapeuticareadescription+"')]/parent::td/preceding-sibling::td/descendant::input[@type='checkbox'])");
				click(tools_therapeutic_areas_text_checkbox);
				wait(ONE);
			}
		} catch (Exception problem) {
			logFailMessageInExtentTest("problem  " + problem.fillInStackTrace());
		}
	}

	String checkingTherapeuticAreaCreate(int datarow, int datacolumn) {
		
		String []noerror = therapeuticAreaCreating(datarow,datacolumn);
		
		try {
			if(noerror[ZERO].contains("true")){
				
				if(isElementPresent(driver, therapeutic_area_exists_description)){

					String details = "therapeutic area already exists, deleting existing therapeutic area description ";
					//displayOnConsole("testDeleteMultipleTherapeuticAreas() -  "+details);
					getScreenshot(driver, "Therapeutic_Area_Exists");
					Actions actions = new Actions(driver);
					Action action = actions.moveToElement(driver.findElement(therapeutic_area_exists_close),TWO,ZERO).click().build();
					action.perform();
					logInfoMessageInExtentTest("testMultipleTherapeuticAreasDelete() - "+details);
					//boolean lookup = lookupTherapeuticAreaDescription();//Added
					//logInfoMessageInExtentTest("Checking Therapeutic Area before re-creating ");//Added
					//if(lookup){//Added
						//boolean deleting = therapeuticAreaDeleting();
						//if(deleting == true){
							//logInfoMessageInExtentTest("Therapeutic Area before re-creating deleted ");
						//}
					//}//Added
					//noerror = therapeuticAreaCreating(datarow,datacolumn);
				}else{
					
					logPassMessageInExtentTest("testMultipleTherapeuticAreasDelete() - Creating of new therapeutic area description for deleting SUCCESSFUL ");
				}

			}else{
				logFailMessageInExtentTest("testMultipleTherapeuticAreasDelete() - Creating of new therapeutic area description for deleting UNSUCCESSFUL ");
			}
		} catch (Exception problem) {
			logFailMessageInExtentTest("problem  " + problem.fillInStackTrace());
		}
		
		return noerror[ONE];
	}

	String[] therapeuticAreaCreating(int datarow, int datacolumn) {
		String noerror[] = {"true",""};
	
		try {

			//displayOnConsole("Adding new therapeutic area ");
						
			//read therapeutic area description from test data
			therapeuticareadescription = Excel.readFromExcel(excel_file, sheet_name, datarow, datacolumn);
			noerror[ONE] = therapeuticareadescription;
			
			//displayOnConsole(sheet_name + " " + therapeuticareadescription + " description ");
			
			//click new therapeutic area
			click(tools_new_therapeutic_area_link);
			wait(ONE);
			
			//enter therapeutic area description
			click(therapeutic_area_new_box_input);
			driver.findElement(therapeutic_area_new_box_input).clear();
			enter(therapeutic_area_new_box_input, therapeuticareadescription);
			
			//click save
			click(therapeutic_area_new_box_save);
			wait(TWO);
			
		} catch (Exception problem) {
			noerror[ZERO]="false";
			logInfoMessageInExtentTest("Problem observed "+ problem.fillInStackTrace()+ " , continuing with processing. ");
		}
		
		return noerror;
	}
	
	//logout from application with checks on error
	void logoutChecking(){
		driver = driver.switchTo().defaultContent();//Added
		if(isElementPresent(driver, more_tool)){
			logout();//Added
		}
	}
}