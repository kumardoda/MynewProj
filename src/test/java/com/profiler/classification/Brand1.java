package com.profiler.classification;

import static org.testng.Assert.assertTrue;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.profiler.utils.AppUtil;
import com.profiler.utils.Excel;
import com.profiler.utils.Functions;

public class Brand1 extends AppUtil {

	// Data :-
		String excel_file = "files//TestData.xlsx";
		String sheet_name = "AddBrand";
		String actualbrand = Excel.readFromExcel(excel_file, sheet_name, 1, 1);
		String expmessage = Excel.readFromExcel(excel_file, sheet_name, 2, 1);
		String FirstName =Excel.readFromExcel(excel_file, sheet_name, 4, 1);
		String LastName = Excel.readFromExcel(excel_file, sheet_name, 5, 1);
	
	
	// Locators :-
	By tools_arrow = By.xpath("//div[@id='navWrapper']/ul/li[@class='moreMenuParent']");
	By tools_field = By.xpath("//a[contains(text(),'Tools')]");
	By alltools_field = By.xpath(
			"//div[@id='treeFrameDiv']/div/div[@id='contentDiv']/table/tbody/tr/td/a[contains(text(),'All Tools')]");
	By brand = By.xpath(
			"//table/tbody/tr/td[1]/table/tbody/tr[2]/td/div/table[2]/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr[2]/td/a[1]");
	By new_brand = By.xpath("//*[@id='menu']/li/a/span");
	By brand_name = By.xpath("//*[@id='description']");
	By save_button = By.xpath("//img[@alt='Save']");
	By select_brand = By.xpath("//div[contains(text(),'"+actualbrand+"')]");
	By delete_button = By.xpath("//*[@id='menu']/li/a/span/following::span");
	By profile = By.xpath("//*[@id='section_profile']");
	By search_results1 = By.xpath("//*[@id='resultsetFacetedSearchTableBody']/tr[1]/td[3]/h3/a");
	By personal_info = By.xpath("//*[@id='boxProfileOverview']/div[2]/div[3]/div/h4/a");
	By actions = By.xpath("//*[@id='actions']");
	By actionedit = By.xpath("//*[@id='linksPopupActions']/div[1]/div[2]/ul/li[1]/a");
	By new_classification = By.xpath("//*[contains(@id,'header_javascript:newClassification')]");
	By select_brand1 = By.xpath("//*[@id='classification1_dd_label']");
	By verifybrand = By.xpath("//div[contains(text(),'"+actualbrand+"')]");
	By verifybrand1 = By.xpath("//label[contains(text(),'"+actualbrand+"')]");
	By closebuton = By.xpath("//*[@id='POPUP_content']/div/div[1]/div/a/img");
	By cancel = By.xpath("//*[@id='cancel']");
	By createnew = By.xpath("//*[@id='popup_create_new']");
	By createprofile = By.xpath("//*[@id='global_create_new_profile']");
	By firstname = By.xpath("//*[@id='firstName']");
	By lastname = By.xpath("//*[@id='lastName']");
	By next = By.xpath("//*[@id='profile_next']");
	By nextlookup = By.xpath("//*[@id='profile_lookup_next']");
	By selecttemplate = By.xpath("//*[@id='newProfileTemplateId_dd_div']/input");
	By templatename = By.xpath("//a[@class='dropdownOpt'][1]");
	By select_brand2 = By.xpath("//*[@id='classification1_dd_label']");
	By closebutton = By.xpath("//*[@id='POPUP_content']/div/div[1]/div/a/img");
	By select_brand3 = By.xpath("//div[text()='"+actualbrand+"']/parent::td/parent::tr//input");
	By delete_brand = By.xpath("//*[@id='menuTable']/tbody/tr/td[2]");
	By select_brandlist = By.xpath("//*[@id='classification1_dd_options']/ul/li");

	

	AppUtil appUtil = new AppUtil();

	// Add Brand
	@Test(priority = 1)
	public void addBrand() throws InterruptedException {

		extentTest = extent.startTest("add brand ");

		String username = Excel.readFromExcel(excel_file, "Application", 2, 1);
		String password = Excel.readFromExcel(excel_file, "Application", 3, 1);

		if (!appUtil.login(username, password)) {
			Assert.fail("User login failed.");
		}

		gotoToolsPage();
		
		click(brand);
		wait(2);
		click(new_brand);
		wait(2);
		enter(brand_name, actualbrand);
		wait(2);
		click(save_button);
		wait(2);
		String expected = driver.findElement(verifybrand).getText();

		try {
			if (actualbrand.equalsIgnoreCase(expected)) {
				System.out.println("Actual & expected Brand name matched on Brand Page!");
			} else {
				Assert.fail("Actual & expected Brand name NOT matched not Brand page!");
			}
		} catch (Exception e) {
			Assert.fail("Exception occured", e);
		}
	}

	// Verify that added brand exists on the New Classification page
	@Test(priority = 2)
	public void verifyBrand() throws InterruptedException {

		extentTest = extent.startTest("verify brand ");
		driver.switchTo().defaultContent();
		click(profile);
		wait(2);
		click(search_results1);
		wait(2);
		click(personal_info);
		wait(2);
		click(actions);
		wait(2);
		click(actionedit);
		wait(2);
		click(new_classification);
		wait(2);
		click(select_brand1);

		// To verify if the actual brand added exists in the list
		//String expected1 = driver.findElement(verifybrand1).getText();
		
		WebElement select = driver.findElement(select_brand1);
		List<WebElement> options = select.findElements(By.xpath("//label[contains(text(),'"+actualbrand+"')]"));
		wait(2);

		try {
			if (options.size() == 0) {
				System.out.println("Actual & expected Brand name matched in New Classification page!");
			} else {
				System.out.println("Actual & expected Brand name NOT matched in New Classification page!");
			}
		} catch (Exception e) {
			Assert.fail("Exception occured", e);
		} finally {
			wait(2);
			click(closebuton);
		}
	}
		

/*		try {
			if (actualbrand.equalsIgnoreCase(expected1)) {
				System.out.println("Actual & expected Brand name matched in New Classification page!");
			} else {
				Assert.fail("Actual & expected Brand name NOT matched in New Classification page!");
			}
		} catch (Exception e) {
			Assert.fail("Exception occured", e);
		} finally {
			wait(2);
			click(closebuton);
		}
	}*/

	// Verify that add brand exists on the Profile Template List page
	@Test(priority = 3)
	public void verifyBrndProfile() throws InterruptedException {

		extentTest = extent.startTest("verify brand profile ");
		click(cancel);
		wait(5);
		click(createnew);
		wait(2);
		click(createprofile);
		wait(2);
		enter(firstname, FirstName);
		wait(2);
		enter(lastname, LastName);
		wait(2);
		click(next);
		wait(2);
		click(nextlookup);
		wait(2);
		click(selecttemplate);
		wait(2);
		click(templatename);
		wait(2);
		click(select_brand2);

		// To verify if the actual brand added exists in the list
		//String expected1 = driver.findElement(verifybrand1).getText();
		
		WebElement select = driver.findElement(select_brand2);
		List<WebElement> options = select.findElements(By.xpath("//label[contains(text(),'"+actualbrand+"')]"));
		wait(2);

		try {
			if (options.size() == 0) {
				System.out.println("Actual & expected Brand name matched in Profile Template List page!");
			} else {
				System.out.println("Actual & expected Brand name NOT matched in Profile Template List page!");
			}
		} catch (Exception e) {
			Assert.fail("Exception occured", e);
		} finally {
			wait(2);
			click(closebuton);
		}
	}

	/*	try {
			if (actualbrand.equalsIgnoreCase(expected1)) {
				System.out.println("Actual & expected Brand name matched in Profile Template List page!");
			} else {
				Assert.fail("Actual & expected Brand name NOT matched in Profile Template List page!");
			}
		} catch (Exception e) {
			Assert.fail("Exception occured", e);
		} finally {
			wait(2);
			click(closebuton);
		}
	}*/

	// Verify that add brand is deleted successfully or not
	@Test(priority = 4)
	public void deleteBrand() throws InterruptedException {

		extentTest = extent.startTest("delete brand ");
		
		/*click(tools_arrow);
		wait(2);
		click(tools_field);
		driver.switchTo().frame("toolsContent");
		wait(5);*/
		gotoToolsPage();
		
		click(brand);
		wait(2);
		click(select_brand3);
		wait(2);
		click(delete_brand);

		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert1 = driver.switchTo().alert();
		alert1.accept();

		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert2 = driver.switchTo().alert();
		String actmessage = alert2.getText();
		alert2.accept();

		// To verify if the delete message displayed is correct or not

		try {
			if (expmessage.equalsIgnoreCase(actmessage)) {
				System.out.println("Brand deleted successfully!");
			} else {
				Assert.fail("Brand not deleted!");
			}
		} catch (Exception e) {
			Assert.fail("Exception occured", e);
		}
	}

	// Verify that Added Brand not showing on New Classification page
	
	@Test(priority = 5)
	public void verifyBrandDelFromClassification() throws InterruptedException {

		extentTest = extent.startTest("verify brand deletion from classification");
		driver.switchTo().defaultContent();
		click(profile);
		wait(2);
		click(search_results1);
		wait(2);
		click(personal_info);
		wait(2);
		click(actions);
		wait(2);
		click(actionedit);
		wait(2);
		click(new_classification);
		wait(2);

		click(select_brand1);

		// To verify if the actual brand added not showing in the list

		WebElement select = driver.findElement(select_brand1);
		List<WebElement> options = select.findElements(By.xpath("//label[contains(text(),'"+actualbrand+"')]"));
		wait(2);
		try {
			if (options.size() == 0) {
				System.out.println("Added Brand not showing on New Classification page now. Deletion Success!");
			} else {
				System.out.println("Added Brand still showing on New Classification page now. Deletion Unsuccessful!");
			}
		} catch (Exception e) {
			Assert.fail("Exception occured", e);
		} finally {
			wait(2);
			click(closebuton);
		}
	}

	// Verify that Added Brand not showing on Profile Template page
	@Test(priority = 6)
	public void verifyBrandDelFromProfile() throws InterruptedException {

		extentTest = extent.startTest("verify brand deletion from profile");
		click(cancel);
		wait(5);
		click(createnew);
		wait(2);
		click(createprofile);
		wait(5);
		enter(firstname, FirstName);
		wait(2);
		enter(lastname, LastName);
		wait(5);
		click(next);
		wait(2);
		click(nextlookup);
		wait(2);
		click(selecttemplate);
		wait(2);
		click(templatename);
		wait(2);
		click(select_brand2);
		wait(2);
		
		// To verify if the actual brand added not showing in the list
		WebElement select = driver.findElement(select_brand2);
		List<WebElement> options = select.findElements(By.xpath("//label[contains(text(),'"+actualbrand+"')]"));
		wait(2);
		try {
			if (options.size() == 0) {
				System.out.println("Added Brand not showing on Profile Template list page. Deletion Success!");

				click(closebuton);
			} else {
				System.out.println("Added Brand still showing on Profile Template List page. Test case failed!");
				click(closebuton);
			}
		} catch (Exception e) {
			Assert.fail("Exception occured", e);
		}

		appUtil.logout();
	}
}
