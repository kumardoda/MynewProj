//This test will add, edit, delete the Type payment threshold.

package com.profiler.contractmgt;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.profiler.utils.AppUtil;
import com.profiler.utils.Excel;
import com.profiler.utils.Functions;

public class TypePaymentThreshold extends AppUtil {
	// Data :-
		String excel_file = "files//TestData.xlsx";
		String ContractManagementFile = "TypePayment";
		String appData_sheet = "Application";
		
		// Login Data
		String Login_sheet = "TypePayment";
		String url = Excel.readFromExcel(excel_file, appData_sheet, 1, 1); 
		String username = Excel.readFromExcel(excel_file, appData_sheet, 2, 1); 
		String password = Excel.readFromExcel(excel_file, appData_sheet, 3, 1);
		String first_alert_data = Excel.readFromExcel(excel_file, Login_sheet, 4, 1); 
		String second_alertdata = Excel.readFromExcel(excel_file, Login_sheet, 5, 1); 
		String thresholdtextdata = Excel.readFromExcel(excel_file, Login_sheet, 6, 1); 
		String first_alert_emaildata = Excel.readFromExcel(excel_file, Login_sheet, 7, 1); 
		String second_alert_emaildata = Excel.readFromExcel(excel_file, Login_sheet, 8, 1); 
		String thresholdtextemaildata = Excel.readFromExcel(excel_file, Login_sheet, 9, 1); 
		String editedthresholddata = Excel.readFromExcel(excel_file, Login_sheet, 10, 1);
		//Objects
		By MoreMenuLinkn=By.xpath("//ul[@id='sectionsNav']/li[10]/div");
		By tools_arrow = By.xpath("//div[@id='navWrapper']/ul/li[@class='moreMenuParent']");
		By tools_field =By.xpath("//a[contains(text(),'Tools')]");
		By threshold =By.xpath("//span[@id='thresholdnav']");
		By type_payment=By.id("thresholdnav_2_anchor");
		By new_payment_type_icon=By.xpath("//ul[@id='menu']/li/a/span/img[@src='/newimages/icons/new.gif']");
		By newExpenseId=By.id("NewExpenseId_dd_label");
		By expenseitem=By.xpath("//div[@id='NewExpenseId_dd_options']/div[2]/span");
		By currency=By.id("NewCurrencyId_dd_label");
		By currencyitem=By.xpath("//div[@id='NewCurrencyId_dd_options']/div[4]");
		By viewgroup=By.xpath("//a[@id='view_assigned_owners']/span/i");
		By add_group=By.xpath("//table[@class='grid resizeTable tblPublications']/tbody/tr[1]/td[@class='opt']/span/input[1]");
		By grp_save_btn=By.xpath("//span[@id='populate_objectives']");
		By first_alert=By.xpath("//textarea[@id='threshold0']");
		By second_alert=By.id("threshold1");
		By thresholdtext=By.id("threshold2");
		By first_alert_email=By.id("emailList0");
		By second_alert_email=By.id("emailList1");
		By thresholdtextemail=By.id("emailList2");
	    By Create_row=By.xpath("//tr[contains(@id,'autoidcu')]/td/div[text()="+first_alert_data+"]/../../td/div[text()="+second_alertdata+"]/../../td/div[text()="+thresholdtextdata+"]/../../td/a[1]/img");
	    By Edit_row=By.xpath("//tr[contains(@id,'autoidcu')]/td/div[text()='"+first_alert_data+"']/../../td/div[text()="+second_alertdata+"]/../../td/div[text()="+thresholdtextdata+"]/../../td/a[2]/img");
	    By Delete_row=By.xpath("//tr[contains(@id,'autoidcu')]/td/div[text()='"+first_alert_data+"']/../../td/div[text()="+second_alertdata+"]/../../td/div[text()="+editedthresholddata+"]/../../td/a[1]/img");
	    By save_btn=By.xpath("//img[@id='save2']");
		
		
	
    @Test
	public void AddCountryPaymentThreshold(){		
		extentTest = extent.startTest("CountryPaymentThreshold");
		AppUtil appUtil = new AppUtil();
		if ( appUtil.login(username, password) )	
			System.out.println("User Login SUCCESSFUL");
		 else
			Assert.fail("CountryPaymentThreshold() - User Login UNSUCCESSFUL");
		
/*		wait(10);
		click(tools_arrow);
		wait(6);
		click(tools_field);
		wait(10);
		driver.switchTo().frame("toolsContent");*/
		
		//gotoToolsPage();
		
		AppUtil apputil = new AppUtil();
		boolean tools_page_shown = apputil.gotoToolsPage();//Added
		if(tools_page_shown == false){//Added
			Assert.fail("Tools page failed to load");
			getScreenshot(driver, "ToolsPageLoading_Failed");
		}
		
		
		click(threshold);
		click(type_payment);
		wait(3);
		click(new_payment_type_icon);
		wait(2);
		click(newExpenseId);
		click(expenseitem);
		click(currency);
		click(currencyitem);
		click(viewgroup);
		wait(5);
		click(add_group);
		click(grp_save_btn);
		wait(5);
		enter(first_alert,first_alert_data);
		enter(second_alert,second_alertdata);
		enter(thresholdtext,thresholdtextdata);
		enter(first_alert_email,first_alert_emaildata);
		enter(second_alert_email,second_alert_emaildata);
		enter(thresholdtextemail,thresholdtextemaildata);
		wait(6);
		click(save_btn);
		wait(6);
		if(isElementPresent(driver, Edit_row))	{
			System.out.println("ContryPayment creaeted SUCCESSFUL");
		} else {
			Assert.fail("ContryPayment not creaeted SUCCESSFUL");
			getScreenshot(driver, "countrypayment");
			}
		click(Edit_row);
		wait(6);
		driver.findElement(thresholdtext).clear();
		enter(thresholdtext,editedthresholddata);
		click(save_btn);
		wait(6);
		if(isElementPresent(driver, Delete_row))	{
			System.out.println("ContryPayment edited SUCCESSFUL");
		} else {
			Assert.fail("ContryPayment not edited SUCCESSFUL");
			getScreenshot(driver, "countrypayment");
			}
		click(Delete_row);
		wait(3);
		driver.switchTo().alert().accept();	
		wait(6);		
		if(isElementPresent(driver, Delete_row))	{
    		Assert.fail("ContryPayment not deleted properly");
			getScreenshot(driver, "Deleted ContryPayment");
			System.out.println("ContryPayment Not Deleteed  SUCCESSFUL");
		} else {
			System.out.println("ContryPayment Deleted SUCCESSFUL");
		}
	}
	 
	
	
	}

