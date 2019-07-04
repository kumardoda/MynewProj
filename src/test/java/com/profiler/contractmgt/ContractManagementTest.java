//Contract Management -  Profile Credit Management 	Create , Edit and Delete Contract.

package com.profiler.contractmgt;

import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.profiler.utils.AppUtil;
import com.profiler.utils.Excel;


public class ContractManagementTest extends AppUtil {
 
	// Data :-
		String excel_file = "files//TestData.xlsx";
		String ContractManagementFile = "ContractManagement";
		String appData_sheet = "Application";
		// Login Data
		String Login_sheet = "ContractManagement";
		String url = Excel.readFromExcel(excel_file, appData_sheet, 1, 1); 
		String username = Excel.readFromExcel(excel_file, appData_sheet, 2, 1); 
		String password = Excel.readFromExcel(excel_file, appData_sheet, 3, 1); 
		String quickbase_projectdata = Excel.readFromExcel(excel_file, Login_sheet, 4, 1); 
		String initial_profiledata = Excel.readFromExcel(excel_file, Login_sheet, 5, 1); 
		String contract_Owner_Namedata = Excel.readFromExcel(excel_file, Login_sheet, 6, 1); 
		String internal_Refdata = Excel.readFromExcel(excel_file, Login_sheet, 7, 1); 

		
		//Locator		
		By MoreMenuLinkn=By.xpath("//ul[@id='sectionsNav']/li[10]/div");
		By tools_arrow = By.xpath("//div[@id='navWrapper']/ul/li[@class='moreMenuParent']");	
		By contract_management_link = By.xpath("//span[@id='contract']");
		By tools_field =By.xpath("//a[contains(text(),'Tools')]");
		By profile_credit_management =By.xpath("//*[@id='contract_1_anchor']");
		By new_contact= By.xpath("//ul[@id='menu']/li/a/span/img[@src='/newimages/icons/new.gif']/..");
		By contract_name= By.xpath("//input[@id='name']");
		By quickbase_project= By.xpath("//input[@id='quickbaseProject']");
		By initial_profile= By.xpath("//input[@id='initialProfiles']");
		By contract_Owner_Name= By.xpath("//input[@id='contractOwnerName']");
		By truven_Sales_RepName= By.xpath("//input[@id='truvenSalesRepName']");
		By crawl_Scope= By.xpath("//textarea[@id='crawlScope']");
		By update_Scope= By.xpath("//textarea[@id='updateScope']");
		By salesforce_Opportunity= By.xpath("//input[@id='salesforceOpportunity']");
		By remaining_Profiles= By.xpath("//input[@id='remainingProfiles']");
		By contract_OwnerEmail= By.xpath("//input[@id='contractOwnerEmail']");
		By truvenSales_RepEmail= By.xpath("//input[@id='truvenSalesRepEmail']");
		By crawl_Remarks= By.xpath("//textarea[@id='crawlRemarks']");
		By update_Remarks= By.xpath("//textarea[@id='updateRemarks']");
		By contract_StartDate= By.xpath("//a[@id='contractStartDateAnc']/img");
		By select_startdate = By.xpath("//div[@id='dateDiv']/table/tbody/tr/td/center/table[2]/tbody/tr[2]/td[1]/a");
		By select_enddate=By.xpath("//div[@id='dateDiv']/table/tbody/tr/td/center/table[2]/tbody/tr[2]/td[4]/a");
		By contract_EndDate= By.xpath("//a[@id='contractEndDateAnc']/img");
		By assign_owner= By.xpath("//a[@id='view_assigned_owners']/span/i");
		By internal_Ref= By.xpath("//input[@id='internalRef']");
		By adduser_group= By.xpath("//div[@id='result-container']/table/tbody/tr[1]/td[1]/span/input");
		By save_btn= By.xpath("//span[@id='populate_objectives']");
		By final_savebtn = By.name("save2");
		By added_row=By.xpath("//tr[contains(@id,'autoidcu')]/td[2]/div/a[contains(text(),strDate)]");
		By Search_field = By.id("searchStringKeyword");
		By go_butn=By.name("searchProfiles");
		By remove_row=By.xpath("//tr[contains(@id,'autoidcu')]/td[2]/div/a[contains(text(),strDate)]/../../../td[8]/a[1]");
		By edit_row=By.xpath("//tr[contains(@id,'autoidcu')]/td[2]/div/a[contains(text(),strDate)]/../../../td[8]/a[2]");
		//By edited_internal_refrence=By.xpath("//tr[contains(@id,'autoidcu')]/td[3]/div[contains(text(),strDate1)]");
		
		@Test
		public void AddContractManagement() {
         extentTest = extent.startTest("ContractManagement");
			AppUtil appUtil = new AppUtil();
			if ( appUtil.login(username, password) )	
				System.out.println("User Login SUCCESSFUL");
			 else
				Assert.fail("AddContractManagement() - User Login UNSUCCESSFUL");
			
			//gotoToolsPage();
			AppUtil apputil = new AppUtil();
			boolean tools_page_shown = apputil.gotoToolsPage();//Added
			if(tools_page_shown == false){//Added
				Assert.fail("Tools page failed to load");
				getScreenshot(driver, "ToolsPageLoading_Failed");
			}

			click(contract_management_link);
			click(profile_credit_management);
			wait(4);
			click(new_contact);
			wait(4);
			Date date = Calendar.getInstance().getTime();  
			//String username 	= "autusr_" +new SimpleDateFormat("YYMMddhhmmSS").format(new Date()).toString();
           // DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
			 DateFormat dateFormat = new SimpleDateFormat("YYMMddhhmmSS");
            String strDate = dateFormat.format(date);
    		By edited_internal_refrence=By.xpath("//a[text()='" +strDate   +"']");
    		
			enter(contract_name,strDate);
			wait(6);
			enter(quickbase_project,quickbase_projectdata);
			enter(initial_profile,initial_profiledata);
			enter(contract_Owner_Name,contract_Owner_Namedata);
			click(contract_StartDate);
			click(select_startdate);
			click(contract_EndDate);
			click(select_enddate);
			enter(internal_Ref,internal_Refdata);
			click(assign_owner);
			wait(6);
			click(adduser_group);
			click(save_btn);
			click(final_savebtn);
			wait(10);
			enter(Search_field,strDate);
			click(go_butn);
			wait(6);
			//edit raw
			Date date1 = Calendar.getInstance().getTime();    
            String strDate1 = dateFormat.format(date1);
            click(edit_row);
            enter(internal_Ref,strDate1); 
            /*if(isElementPresent(driver, edited_internal_refrence))	{
    			System.out.println("Contract edited SUCCESSFUL");
    		} else {
    			Assert.fail("Contract not edited properly");
    			getScreenshot(driver, "Edited Profile");
    			}*/
            click(final_savebtn);

            wait(20);
            enter(Search_field,strDate);
            wait(6);
		    click(go_butn);
			wait(6);
			click(remove_row);
			wait(6);
			driver.switchTo().alert().accept();	
			wait(6);
			//enter(internal_Ref,strDate1); 
        	if(isElementPresent(driver, edited_internal_refrence))	{
        		Assert.fail("Contract not deleted properly");
				getScreenshot(driver, "Deleted Contract");
				System.out.println("Contract Not Deleteed  SUCCESSFUL");
			} else {
				System.out.println("Contract Deleted SUCCESSFUL");
			}
			
			
		}
}
