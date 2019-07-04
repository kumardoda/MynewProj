package com.profiler.scripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.profiler.utils.AppUtil;
import com.profiler.utils.Excel;
import com.profiler.utils.Functions;

public class Dynamic2ColumnSection extends Functions	{
	
	// Locators :-
	
	//By username_field = By.xpath("//*[@name='username']"); 
	//By password_field = By.xpath("//a[contains(text(),'Password')]");
	//By login_button = By.xpath("//*[@id='login_submit']");
	By Profiles_tab=By.xpath("//a[contains(@title,'Profiles')]");
	By Search_profiles=By.xpath("//input[@id='searchall']");
	By Search_button=By.xpath("//span[text()='SEARCH']");
	//By click_on_profile=By.xpath("//a[text()='Profile1, Naresh']");//Changed
	//By Userdefined_tab= By.xpath("//a[text()='AutomationTestTab ']");//Changed
	By actions= By.xpath("//span[@id='actions']");
	By actions_edit=By.xpath("//a[@title='Edit']");
	By Dynamic2_Section_Namefield=By.xpath("//div[contains(@id,\"SectionTwoColumn\")]/input[contains(@name,\"SectionTwoColumn\")]");
	By Dynamic2_Section_Genderfield=By.xpath("//input[@class='dropdown dd-all']");
	By actions_save=By.xpath("//span[text()='Save']");
	By verify_name=By.xpath("//div[@class='block first']//div[contains(@name,'highlightkeyword')]");
	By verify_gender=By.xpath("//div[@class='block']//div[contains(@name,'highlightkeyword')]");
	By dropdown_defaultvalue=By.xpath("//div[@class='dropdownOpts']/a[text()='-- Select Gender --']");
	
	// Data :-
	// Login Data
	String excel_file = "files//TestData.xlsx";
	String Login_sheet = "Application";//changed
	String url = Excel.readFromExcel(excel_file, Login_sheet, 1, 1); 
	String username = Excel.readFromExcel(excel_file, Login_sheet, 2, 1); 
	String password = Excel.readFromExcel(excel_file, Login_sheet, 3, 1); 
	
	//Profile and Dynamic2Section Data
	String Profile_sheet= "Dynamic2Section";
	String profilename= Excel.readFromExcel(excel_file, Profile_sheet, 1, 1); 
	String actualname_value = Excel.readFromExcel(excel_file, Profile_sheet, 2, 1);
	String actualgender_value = Excel.readFromExcel(excel_file, Profile_sheet, 3, 1);
	String edit_actualname_value = Excel.readFromExcel(excel_file, Profile_sheet, 4, 1);
	String edit_actualgender_value = Excel.readFromExcel(excel_file, Profile_sheet, 5, 1);
	AppUtil appUtil = new AppUtil();
	
	//Added
	By click_on_profile=By.xpath("//div[@id='thumbTableDiv']/table[@class='grid tblProfiles profileThumbviewTable']/descendant::a[contains(text(),'"+profilename+"')]");
	String AutomationTestTab = "AutomationTestTab ";
	By Userdefined_tab= By.xpath("//a[text()='"+AutomationTestTab +"']");
	
	@Test
	public void Dynamic2Section() {
		
		// Login 
		extentTest = extent.startTest("Dynamic2Section");
		AppUtil appUtil = new AppUtil();
		getUrl(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		/*driver.findElement(username_field).sendKeys(username);
		driver.findElement(password_field).sendKeys(password);
		driver.findElement(signin_button);
		driver.findElement(signin_button).click();*/
		
		if ( appUtil.login(username, password) )	
			System.out.println("User Login SUCCESSFUL");
		 else
			Assert.fail("Dynamic2Section() - User Login UNSUCCESSFUL");
		
		
		// Search Profile
		driver.findElement(Profiles_tab).click();
		driver.findElement(Search_profiles).sendKeys(profilename);
		wait(5);
		driver.findElement(Search_button).click();
		wait(5);
		driver.findElement(click_on_profile).click();
		wait(2);
		
		// Test case 10-Dynamic2 column section
		
		// Adding records for Dynamic2 column section
		//String message_for_tab = AutomationTestTab+" not available";//Added
		
		//Assert.assertTrue(driver.getPageSource().contains(AutomationTestTab),message_for_tab);//changed
		//Assert.assertTrue(driver.getPageSource().contains("AutomationTestTab"));
		//driver.findElement(Userdefined_tab).click();
		driver.findElement(actions).click();
		driver.findElement(actions_edit).click();
		driver.findElement(Dynamic2_Section_Namefield).clear();
		driver.findElement(Dynamic2_Section_Namefield).sendKeys(actualname_value);
		wait(5);
		driver.findElement(Dynamic2_Section_Genderfield).sendKeys(actualgender_value);
		wait(5);
		driver.findElement(actions_save).click();
		wait(5);
		
		// To verify if the Actual & expected Name are matching or not after adding values
		
		String expectedname_value = driver.findElement(verify_name).getText();
		System.out.println(expectedname_value);
		try {
			if (actualname_value.equalsIgnoreCase(expectedname_value)) {
				System.out.println("Actual & expected Name matched!");
			} else {
				Assert.fail("Actual & expected Name NOT matched!");
			}
		 } catch (Exception e) {
			Assert.fail("Exception occured", e);
		}
	
		// To verify if the Actual & expected Gender are matching or not after adding values
		
		String expectedgender_value = driver.findElement(verify_gender).getText();
		System.out.println(expectedgender_value);
		try {
			if (actualgender_value.equalsIgnoreCase(expectedgender_value)) {
				System.out.println("Actual & expected Gender matched!");
			} else {
				Assert.fail("Actual & expected Gender NOT matched!");
			}
		 } catch (Exception e) {
			Assert.fail("Exception occured", e);
		}
		
		
	// Editing records for the Dynamic2 Section
	
	driver.findElement(actions).click();
	driver.findElement(actions_edit).click();
	driver.findElement(Dynamic2_Section_Namefield).clear();
	driver.findElement(Dynamic2_Section_Namefield).sendKeys(edit_actualname_value);
	wait(5);
	driver.findElement(Dynamic2_Section_Genderfield).sendKeys(edit_actualgender_value);
	wait(5);
	driver.findElement(actions_save).click();
	wait(5);
	
	// To verify if the edited actual & expected Name are matching or not
	
	String expectededitedname_value = driver.findElement(verify_name).getText();
	System.out.println(expectededitedname_value);
	try {
		if (edit_actualname_value.equalsIgnoreCase(expectededitedname_value)) {
			System.out.println("Name edited successfully");
		} else {
			Assert.fail("Name not edited");
		}
	 } catch (Exception e) {
		Assert.fail("Exception occured", e);
	}
	
	// To verify if the edited actual & expected Gender are matching or not
	
	String expectededitedgender_value = driver.findElement(verify_gender).getText();
	System.out.println(expectededitedgender_value);
	try {
		if (edit_actualgender_value.equalsIgnoreCase(expectededitedgender_value)) {
			System.out.println("Gender edited successfully");
		} else {
			Assert.fail("Gender not edited");
		}
	 } catch (Exception e) {
		Assert.fail("Exception occured", e);
	}
	
	// Deleting records under the Dynamic2 Section
	
	driver.findElement(actions).click();
	driver.findElement(actions_edit).click();
	driver.findElement(Dynamic2_Section_Namefield).clear();
	wait(2);
	WebElement dropdown=driver.findElement(Dynamic2_Section_Genderfield);
	WebElement selectdropdown_defaultvalue=driver.findElement(dropdown_defaultvalue);
	Actions action=new Actions(driver);
	action.moveToElement(dropdown).click().moveToElement(selectdropdown_defaultvalue).click().build().perform();
	wait(10);
	driver.findElement(actions_save).click();
	wait(5);
	
	
	// To verify if the Name & Gender records got deleted successfully
	
	String Namefieldvalue_afterdelete= driver.findElement(verify_name).getText();
	
	if(Namefieldvalue_afterdelete.trim().isEmpty())
	{		
	  System.out.println("Name field is empty & value deleted successfully");
	}	
	else
	{
		System.out.println("Name field is not empty & value is not deleted");
	}
	
	String Genderfieldvalue_afterdelete= driver.findElement(verify_gender).getText();
	
	if(Genderfieldvalue_afterdelete.trim().isEmpty())
	{		
	  System.out.println("Gender field is empty & value deleted successfully");
	}	
	else
	{
		System.out.println("Gender field is not empty & value is not deleted");
	}
			appUtil.logout();
	}

	
}
