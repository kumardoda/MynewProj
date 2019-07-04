package com.profiler.scripts;

import com.profiler.utils.AppUtil;
import com.profiler.utils.Excel;
import com.profiler.utils.Functions;
import com.profiler.utils.TemplateUtil;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TemplateTest extends Functions		 {
	
	TemplateUtil tmplUtil = new TemplateUtil();
	
	@Test
	public void testTemplate()	{
	
		extentTest = extent.startTest("Template Creation ");
		
		
		String excel_file = "files//TestData.xlsx";
		String sheet_name = "Application";
		String templateName = null;
		
		//getting values from excel
		String username = Excel.readFromExcel(excel_file, sheet_name, 2, 1); 
		String password = Excel.readFromExcel(excel_file, sheet_name, 3, 1); 
		AppUtil appUtil = new AppUtil();
		//TemplateUtil tmplUtil = new TemplateUtil();
		
		if ( appUtil.login(username, password) )	{
			System.out.println("testTemplate() - User Login SUCCESSFUL");
			
			templateName = tmplUtil.createTemplate("");
			
			if( templateName!=null )	{
				System.out.println("testTemplate() > templateName -" + templateName);
			} else
				System.out.println("testTemplate() - Template Creation UNSUCCESSFUL");
		} else
			System.out.println("testTemplate() - User Login UNSUCCESSFUL");	
		
		appUtil.logout();

	}
	
	
	
	@Test
	public void testCustomizeTemplate()	{
	
		extentTest = extent.startTest("Customize Template");
		
		String excel_file = "files//TestData.xlsx";
		String sheet_name = "Application";
		String templateName = null;
		
		String username = Excel.readFromExcel(excel_file, sheet_name, 2, 1); 
		String password = Excel.readFromExcel(excel_file, sheet_name, 3, 1); 
		//TemplateUtil tmplUtil = new TemplateUtil();
		
		if ( tmplUtil.login(username, password) )	{
			
			if(! tmplUtil.customizeTemplate())	{
				Assert.fail("Template customization failed");
				getScreenshot(driver, "testCustomizeTemplateFailure");
			}
		}	

		tmplUtil.logout();

	}
}