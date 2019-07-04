package com.profiler.scripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.profiler.utils.AppUtil;
import com.profiler.utils.Excel;
import com.profiler.utils.Functions;
import com.profiler.utils.TemplateUtil;
import com.relevantcodes.extentreports.LogStatus;

public class FlowTest extends Functions		{

	@Test
	public void flowTest()	{
		
		extentTest = extent.startTest("Flow Test ");
		
		AppUtil apputil = new AppUtil();
		TemplateUtil tmplUtil = new TemplateUtil(); 
		String excel_file = "files//TestData.xlsx";
		String sheet_name = "Application";
		String templateName = null;
		String profileName = null;
		
		String username = Excel.readFromExcel(excel_file, sheet_name, 2, 1); 
		String password = Excel.readFromExcel(excel_file, sheet_name, 3, 1); 

		
		if ( apputil.login(username, password) )	{

			templateName = tmplUtil.createTemplate("");
			if( templateName==null )
				Assert.fail("flowTest() - Template Creation UNSUCCESSFUL");
			extentTest.log(LogStatus.INFO, "Template Created - "+templateName);
			
			profileName = apputil.createProfile(templateName);
			if( profileName==null )
				Assert.fail("flowTest() - Profile Creation UNSUCCESSFUL");
			extentTest.log(LogStatus.INFO, "Profile Created - "+profileName);
			
			
		} else
			Assert.fail("flowTest() - User Login UNSUCCESSFUL");
		apputil.logout();
	}
}
