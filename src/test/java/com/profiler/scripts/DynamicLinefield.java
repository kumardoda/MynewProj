package com.profiler.scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.profiler.utils.AppUtil;
import com.profiler.utils.Excel;
import com.profiler.utils.Functions;

public class DynamicLinefield extends Functions {

	// Locators :-
	By search_field = By.xpath("//*[@id='txtSearchTop_keyw']");
	By search_results = By.xpath("//*[@id='thumbTableDiv']/table/tbody/tr[1]/td[2]/div[1]/div[1]/div[1]/h3/a[1]"); //By.xpath("//tr/td[2]/div[1]/div[1]/div[1]/h3/a[1]");
	By dynamic_tab = By.xpath("//*[@id='boxProfileOverview']/div[2]/div[3]/div/h4/a");
	By actions = By.xpath("//*[@id='actions']");
	By actionedit = By.xpath("//*[@id='linksPopupActions']/div[1]/div[2]/ul/li[1]/a");
	By newbutton = By.xpath("//*[@id='header_javascript: addLineFieldSectionData('][1]");
	By name1 = By.xpath("//textarea[contains(@name,'DynamicLineField')]");//By.xpath("//*[@id='highlightkeyword']/div/input");//xpath changed
	By savebutton = By.xpath("//*[@id='save_line_field_section_data']");
	By savebutton1 = By.xpath("//*[@id='save']");
	By verifyname = By.xpath("//descendant::div[@class='rowheight_small_dlfs']");
	By editbutton = By.xpath("//img[@class='ico ico-edit1']");
	By editname = By.xpath("//textarea[contains(@name,'DynamicLineField')]");//By.xpath("//*[@id='highlightkeyword']/div/input");//xpath changed
	By deletebutton = By.xpath("//a[contains(@onclick,'dynamicLineFieldHandler_delete')]/img[@class='ico ico-delete']"); //By.xpath("//img[@class='ico ico-delete']");//xpath changed
	By norecords = By.xpath("//div[@name='highlightkeyword']/following::td[1]");
	
	// Data :-
	String excel_file = "files//TestData.xlsx";
	String sheet_name = "DynamicLineField";
	String actual = Excel.readFromExcel(excel_file, sheet_name, 1, 1);
	String editactualname = Excel.readFromExcel(excel_file, sheet_name, 2, 1);
	String message = Excel.readFromExcel(excel_file, sheet_name, 3, 1);
	String name = Excel.readFromExcel(excel_file, sheet_name, 4, 1);
	AppUtil appUtil = new AppUtil();
	
	String application_sheet = "Application";//Added
	
	@Test(priority = 4)
	public void addDynamicField() throws InterruptedException {

		extentTest = extent.startTest("addDynamicField ");
		
		String username = Excel.readFromExcel(excel_file, application_sheet, 2, 1); 
		String password = Excel.readFromExcel(excel_file, application_sheet, 3, 1); 
		

		
		if ( ! appUtil.login(username, password) )	{
			Assert.fail("User login failed.");
		}
		
		// Testcase 3- Search Profile code
		enter(search_field,name);
		wait(5);
		driver.findElement(search_field).sendKeys(Keys.ENTER);
		wait(10);

		// **************End of testcase 3***********************

		// Testcase 11-Dynamic Linefield Column section
		// Add a record for Dynamic Linefield column section
		click(search_results);
		wait(2);
		click(dynamic_tab);
		wait(2);
		click(actions);
		wait(2);
		click(actionedit);
		wait(2);
		click(newbutton);
		wait(2);
		enter(name1,actual);
		wait(2);
		click(savebutton);
		wait(5);
		click(savebutton1);
		wait(5);
		String expected = driver.findElement(verifyname).getText();
		System.out.println(expected);

		// To verify if the Actual & expected Name are matching or not
		try {
			if (actual.equalsIgnoreCase(expected)) {
				System.out.println("Actual & expected Name matched!");
			} else {
				Assert.fail("Actual & expected Name NOT matched!");
			}
		} catch (Exception e) {
			Assert.fail("Exception occured", e);
		}
	}

	// Edit a record for Dynamic Linefield column section
	@Test(priority = 5)
	public void editDynamicField() throws InterruptedException {
		
		extentTest = extent.startTest("editDynamicField ");
		
		click(actions);
		wait(2);
		click(actionedit);
		wait(2);
		click(editbutton);
		wait(2);
		driver.findElement(editname).clear();
		enter(editname,editactualname);
		wait(2);
		click(savebutton);
		wait(2);
		click(savebutton1);
		wait(2);
		String expected = driver.findElement(verifyname).getText();
		System.out.println(expected);

		// To verify if the edited actual & expected Name are matching or not
		try {
			if (editactualname.equalsIgnoreCase(expected)) {
				System.out.println("Name edited successfully!");
			} else {
				Assert.fail("Name not edited!");
			}
		} catch (Exception e) {
			Assert.fail("Exception occured", e);
		}
	}

	// Delete a record for Dynamic Linefield column section
	@Test(priority = 6)
	public void deleteDynamicField() throws InterruptedException {
		
		extentTest = extent.startTest("deleteDynamicField ");
		click(actions);
		wait(2);
		click(actionedit);
		wait(2);
		click(deletebutton);
		wait(2);
		driver.switchTo().alert().accept();
		wait(2);
		click(savebutton1);
		wait(2);
		String norecordsexp = driver.findElement(norecords).getText();
		// To verify if the record got deleted successfully
		try {
			if (message.equalsIgnoreCase(norecordsexp)) {
				System.out.println("Record deleted successfully!");
			} else {
				Assert.fail("Record not deleted!");
			}
		} catch (Exception e) {
			Assert.fail("Exception occured", e);
		}
		appUtil.logout();
	}
}
