package com.profiler.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TemplateUtil extends AppUtil {

	
	public boolean customizeTemplate() 
	{
			By tools_arrow = By.xpath("//div[@id='navWrapper']/ul/li[@class='moreMenuParent']");		
			By tools_field =By.xpath("//a[contains(text(),'Tools')]");		
			By alltools_field = By.xpath("//div[@id='treeFrameDiv']/div/div[@id='contentDiv']/table/tbody/tr/td/a[contains(text(),'All Tools')]");
			By customizeSite_field = By.xpath("//span[@id='customizenav']");
			By customizePlus_filed = By.xpath("//img[@id='customizenav_toggle']");
			By systemconf_field = By.xpath("//a[@id='customizenav_2_anchor']");
			By skip = By.xpath("//*[@id='ius-verified-user-update-btn-skip']");
			By signin_button = By.xpath("//*[@alt='Sign In']");
			By new_button = By.xpath("//a/img[@src='/newimages/buttons/new.gif']");
			By save_button = By.xpath("//a/img[@src='/profiler/_assets/newimages/buttons/save.gif']");
			By template_Name = By.xpath("//*[@id='templateName']");	
			By tab_Name = By.xpath("//input[@id='tabName']");
			By save_Button = By.xpath("//span[contains(text(),'Save')]"); 		
			By section_Name = By.xpath("input[@id='sectionName']");
			By use_Tabs = By.xpath("//input[@id='useTabs']");
			By new_Tab= By.xpath("//a[@title='New Tab']");
			By add_Section = By.xpath("//a[@title='Add Section']");
			
			
		try	{	
			
			click(tools_arrow);		
			click(tools_field);	
			driver.switchTo().frame("toolsContent");		
			click(customizeSite_field);		
			click(systemconf_field);
			wait(4);
			click(new_button);
			wait(4);
			
			String tempName = "Automation" + new Date().getTime();
			enter(template_Name,tempName);		
			Select templateName = new Select(driver.findElement(By.id("templateType")));
			templateName.selectByVisibleText("Profile");
			
					
			click(use_Tabs);
			click(save_button);
			wait(8);
			driver.findElement(By.xpath("//td[contains(text(), '"+tempName+"')]/following::td/a[@title='Configure Template']")).click();
			wait(6);
			
			driver.switchTo().defaultContent();
		//	driver.switchTo().frame("toolsContent");
		
			
			Actions actions1 = new Actions(driver);
		    actions1.sendKeys(Keys.PAGE_UP).build().perform();		
		    wait(5);
			
			
			   driver.switchTo().frame("toolsContent");
			   wait(10);
		       WebElement elem=driver.findElement(new_Tab);
		       JavascriptExecutor jse = (JavascriptExecutor)driver;
		       jse.executeScript("arguments[0].click();", elem);
	 	   
			
		
			Map<String, List<String>> map = new HashMap<String, List<String>>();
		        // create list one and store values
		        List<String> valSetPublications = new ArrayList<String>();
		        valSetPublications.add("Publication Activities");
		        valSetPublications.add("Publications");
		        valSetPublications.add("Publications in Progess");
		        
		        map.put("Publications", valSetPublications);
		        
		        
		        List<String> valSetClinicalActivities = new ArrayList<String>();
		        
		        valSetClinicalActivities.add("Clinical Activities");
		        valSetClinicalActivities.add("Classification");
		        valSetClinicalActivities.add("Client-Specific Clinical Trials");
		        
		        map.put("Clinical Activities", valSetClinicalActivities);
		        
		        
		        
	           List<String> valSetEvents = new ArrayList<String>();	        
	           valSetEvents.add("Event Activities");
	           valSetEvents.add("Events");           
		        
		        map.put("Events", valSetEvents);
		        
		        
		        
		        List<String> valSetInteractions = new ArrayList<String>();	        
		        valSetInteractions.add("Interaction Activities");
		        valSetInteractions.add("Interactions");
		      
		        map.put("Interactions", valSetInteractions);
		        
		        
		        List<String> valSetIntelligence = new ArrayList<String>();	        
		        valSetIntelligence.add("Competitive Intelligence");	        
		      
		        map.put("Competitive Intelligence", valSetIntelligence);
		        
		        
		        List<String> valSetBiography = new ArrayList<String>();	        
		        valSetBiography.add("Professional Details");
		        valSetBiography.add("Practice History");
		        valSetBiography.add("Press Items");
		        valSetBiography.add("Awards");
		        valSetBiography.add("Education");        
		         
		        map.put("Biography", valSetInteractions);
		        
		        

		        List<String> valSetInfluential= new ArrayList<String>();	        
		        valSetInfluential.add("Professional Details");
		        valSetInfluential.add("Practice History");
		        valSetInfluential.add("Press Items");
		        valSetInfluential.add("Awards");
		        valSetInfluential.add("Education");        
		         
		        map.put("Influential Connections", valSetInfluential);
		       
		        
		        List<String> valSetSocialMedia= new ArrayList<String>();	        
		        valSetInfluential.add("Social Media Accounts");
		        valSetInfluential.add("Social Media Presence");
		       
		        map.put("Social Media", valSetSocialMedia);
		       
		        
		        List<String> valSetPayments = new ArrayList<String>();	        
		        valSetPayments.add("Payments");
		        valSetPayments.add("Payment Disclosure");
		       
		        map.put("Payments", valSetPayments);
		       
		        
		        
		        List<String> valSetPlannedActivity= new ArrayList<String>();	        
		        valSetPlannedActivity.add("Activities");	  
		       
		        map.put("Planned Activity", valSetPlannedActivity);
		        
		        
		        List<String> valSetDocuments= new ArrayList<String>();	        
		        valSetDocuments.add("Forms/Agreements");
		        valSetDocuments.add("Internal Documents");
		        valSetDocuments.add("Personal Documents");
		       
		        map.put("Documents", valSetDocuments);
		        
		        
		        
		        
		        List<String> valSetNominations= new ArrayList<String>();	        
		        valSetNominations.add("Nomination Overview");
		        valSetNominations.add("Peer Nominations");
		       
		        map.put("Nominations", valSetNominations);
		        
		        List<String> valSetSegmentation= new ArrayList<String>();	        
		        valSetSegmentation.add("Segmentation");
		         
		        map.put("Segmentation", valSetSegmentation);
		        
		        int temp = 0;
		        
	  
		        
		        
		        // iterate and display values	        
		        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
		            String key = entry.getKey();
		            
		        	
		            List<String> values = entry.getValue();                      
		     
		      // driver.switchTo().frame("toolsContent");
		            if(temp == 0)
		            {
		        		enter(tab_Name,key);	
		            }
		            else
		            {
		            WebElement elem1=driver.findElement(new_Tab);
				       JavascriptExecutor jse1 = (JavascriptExecutor)driver;
				       jse1.executeScript("arguments[0].click();", elem1);
		 	   
		 		wait(5);	 		
		 		
		 		enter(tab_Name,key);	
		            }
				click(save_Button);	 		
				temp++;
				wait(10);	
				
				 Iterator<String> itr = values.iterator();
		 		 int i = 0;
		 		 String[] data = new String[values.size()];
		            while(itr.hasNext()){
		            	
		            	  	
		      	 		By add_SectionName = By.xpath("//input[@id='sectionName']");
		      	 		By section_dropdown = By.xpath("//input[@class='dropdown dd-all']");      	 		
		             	click(add_Section);
		    			wait(5);
		    		
		    			String sectionName = key + new Date().getTime();
		    			enter(add_SectionName,sectionName);	
		            	
		            	
		            	String array = itr.next();
		            	data[i] = array;
		            	
		            	
		            	String test = data[i];
		          	            	
		            	i++;            	
		                //System.out.println("Key inside while = " + key);
		              driver.findElement(By.xpath("//input[@class ='dropdown dd-all']")).click();
		             
		           
		             Robot robot;
		             
		             
		         	WebElement select = driver.findElement(By.xpath("//*[@id='ptSectionType_dd_div']"));
		    		List<WebElement> optionsize = select.findElements(By.xpath("//a[contains(text(), '"+ test +"')]"));
	 	    		if(optionsize.size()>0)
		    		{
		    			
		    			//System.out.println("exists");
		    			
		    			  driver.findElement(By.xpath("//div[@class='dropdownOpts' and contains(@style,'display: block')]")).click();
		    			   Robot robot1; 	 
		             try {
						robot1 = new Robot();
						robot1.keyPress(KeyEvent.VK_DOWN);
					} catch (AWTException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
							
							wait(4);						
							WebElement selectdropdown_value =driver.findElement(By.xpath("//div[@class='dropdownOpts' and contains(@style,'display: block')]/a[contains(text(), '"+ test +"')]"));
							wait(2);
							selectdropdown_value.click();
							click(savesection_button);
				              test= "";
							
							
		     		}
		     		else
		     		{
		     			
		     			
		     			driver.findElement(By.xpath("//a/img[@src='/images/widgets/x.gif']")).click();    			
		     			
		     			wait(5);
		     			test="";
		     		}
		       
		             

		            }
		         
		    }
		        
		        return true;
		}
		
		
		catch(Exception e)	{
			return false;
		}
	}
	
	
	
	public void addSection(String fieldType)
	{		
		
		String excel_file = "files//TemplateDetails.xlsx";
		String sheet_name = "TemplateValues";		
		String sectionName2Value = Excel.readFromExcel(excel_file, sheet_name, 2, 1);
		String sectionName1Value = Excel.readFromExcel(excel_file, sheet_name, 1, 1);

        
		
		By add_Section = By.xpath("//a[@title='Add Section']");		
		By add_SectionName = By.xpath("//input[@id='sectionName']");
		By section_dropdown = By.xpath("//input[@class='dropdown dd-all']");
		wait(5);
		click(add_Section);
		wait(2);
		if(fieldType == "fecthTabSection")
		{
			
			  String tabSheet_name = "TabSection";
		      String tabSecName = Excel.readFromExcel(excel_file, tabSheet_name, 2, 1);
		      String secType = Excel.readFromExcel(excel_file, tabSheet_name, 3, 1);
		      enter(add_SectionName,sectionName1Value);
		      click(section_dropdown);
		      

	         	WebElement select = driver.findElement(By.xpath("//*[@id='ptSectionType_dd_div']"));
	    		List<WebElement> optionsize = select.findElements(By.xpath("//a[contains(text(), '"+ secType +"')]"));
	    		if(optionsize.size()>0)
	    		{
	    			
	    			//System.out.println("exists");
	    			
	    			  driver.findElement(By.xpath("//div[@class='dropdownOpts' and contains(@style,'display: block')]")).click();
	    			   Robot robot1; 	 
	             try {
					robot1 = new Robot();
					robot1.keyPress(KeyEvent.VK_DOWN);
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
						
						wait(4);						
						WebElement selectdropdown_value =driver.findElement(By.xpath("//div[@class='dropdownOpts' and contains(@style,'display: block')]/a[contains(text(), '"+ secType +"')]"));
						wait(2);
						selectdropdown_value.click();
						click(savesection_button);						
						System.exit(0);
		      
			
		}
			
		
		}
		
		if(fieldType == "1")		
			enter(add_SectionName,sectionName1Value);		
		else		
			enter(add_SectionName,sectionName2Value);		
			
			
	 //  sectionName = "Section" + new Date().getTime();
		wait(2);	
		click(section_dropdown);
		 driver.findElement(By.xpath("//div[@class='dropdownOpts' and contains(@style,'display: block')]")).click();
		wait(2);
	}
	
	
	
	public  String createTemplate(String varName)
	{
		By tools_arrow = By.xpath("//div[@id='navWrapper']/ul/li[@class='moreMenuParent']");		
		By tools_field =By.xpath("//a[contains(text(),'Tools')]");		
		By alltools_field = By.xpath("//div[@id='treeFrameDiv']/div/div[@id='contentDiv']/table/tbody/tr/td/a[contains(text(),'All Tools')]");
		By customizeSite_field = By.xpath("//span[@id='customizenav']");
		By customizePlus_filed = By.xpath("//img[@id='customizenav_toggle']");
		By systemconf_field = By.xpath("//a[@id='customizenav_2_anchor']");
		By skip = By.xpath("//*[@id='ius-verified-user-update-btn-skip']");
		By signin_button = By.xpath("//*[@alt='Sign In']");
		By new_button = By.xpath("//a/img[@src='/newimages/buttons/new.gif']");
		By save_button = By.xpath("//a/img[@src='/profiler/_assets/newimages/buttons/save.gif']");
		By template_Name = By.xpath("//*[@id='templateName']");	
		By tab_Name = By.xpath("//input[@id='tabName']");
		By save_Button = By.xpath("//span[contains(text(),'Save')]"); 		
		By section_Name = By.xpath("input[@id='sectionName']");
		By use_Tabs = By.xpath("//input[@id='useTabs']");
		By new_Tab= By.xpath("//a[@title='New Tab']");
		By add_Section = By.xpath("//a[@title='Add Section']");
		
		
		
		String excel_file = "files//TemplateDetails.xlsx";
		String sheet_name = "TemplateValues";
		String tabSheet_name = "TabSection";
		
		String tabName = Excel.readFromExcel(excel_file, sheet_name, 5, 1); 	
		String tabSecName = Excel.readFromExcel(excel_file, tabSheet_name, 1, 1);
		String lineFieldValueSection2 = Excel.readFromExcel(excel_file, sheet_name, 6, 1); 
		String dropNameValueSection2 = Excel.readFromExcel(excel_file, sheet_name, 7, 1); 
		String dropdownValue1 = Excel.readFromExcel(excel_file, sheet_name, 8, 1); 
		String dropdownValue2 = Excel.readFromExcel(excel_file, sheet_name, 9, 1); 
		
		
		
		click(tools_arrow);		
		click(tools_field);	
		driver.switchTo().frame("toolsContent");		
		click(customizeSite_field);		
		click(systemconf_field);
		wait(4);
		click(new_button);
		wait(4);
		
		String tempName = "Automation" + new Date().getTime();
		enter(template_Name,tempName);		
		Select templateName = new Select(driver.findElement(By.id("templateType")));
		templateName.selectByVisibleText("Profile");
		
				
		click(use_Tabs);
		click(save_button);
		wait(5);
		driver.findElement(By.xpath("//td[contains(text(), '"+tempName+"')]/following::td/a[@title='Configure Template']")).click();
		wait(6);
		
		driver.switchTo().defaultContent();
	
		Actions actions1 = new Actions(driver);
	    actions1.sendKeys(Keys.PAGE_UP).build().perform();		
	    wait(5);
	   // addTab();		 
		
	    driver.switchTo().frame("toolsContent");
		   wait(10);
	       WebElement elem=driver.findElement(new_Tab);
	       JavascriptExecutor jse = (JavascriptExecutor)driver;
	       jse.executeScript("arguments[0].click();", elem);
	       
		// click(new_Tab);
		//driver.findElement(By.xpath("//a[@title='New Tab']")).click();
		   wait(5);	
		//String tabName = "AutomationTab" + new Date().getTime();
		 
		
			// this line is new functionality
			if(varName!= "")
			{
		   enter(tab_Name,tabSecName);	
			click(save_Button);
			wait(10);	
		   driver.findElement(By.xpath("//a[contains(text(),'"+tabSecName+"')]")).click();
			
		   addSection(varName);
		    
			}
			else
			{
				 enter(tab_Name,tabName);	
					click(save_Button);
					wait(10);	
				 driver.findElement(By.xpath("//a[contains(text(),'"+tabName+"')]")).click();
			}
			
				
			addSection("1"); 
			//click on particular value in section dropdown
			//driver.findElement(By.xpath("//a[@class='dropdownOpt' and @name='-11']")).click();
			driver.findElement(By.xpath("//a[@class='dropdownOpt' and @name='-12']")).click();	
			wait(2);		
			//section saving 
			click(savesection_button);
		
			
			//click on new button to add fields in to the section				
			driver.findElement(By.xpath("//div[@class='toolbarButton']/a[1]")).click();
			wait(2);				
			//String fieldName = "Name" + new Date().getTime();
			String fieldName = Excel.readFromExcel(excel_file, sheet_name, 10, 1);  //Name
			wait(2);
			driver.findElement(By.xpath("//input[@id='description']")).sendKeys(fieldName);
			wait(2);
			//driver.findElement(By.xpath("//a[@title ='Save']")).click();
			click(savesection_button);		
		
							
			wait(3);			
			addSection("2");
			wait(3);			
			for(int i=0; i<=9; i++)
			{
			driver.findElement(By.xpath("//input[@class='dropdown dd-all']")).sendKeys(Keys.ARROW_DOWN);
			}			
			driver.findElement(By.xpath("//input[@class='dropdown dd-all']")).sendKeys(Keys.ENTER);				
			wait(3);
			click(savesection_button);
			wait(3);
			String sectionName1Value = Excel.readFromExcel(excel_file, sheet_name, 2, 1);
			driver.findElement(By.xpath("//a[@title='"+sectionName1Value+"']//following::a[1]")).click();
			wait(2);
			newFieldAdd();
			/*Select sectionType = new Select(driver.findElement(By.id("ptSectionType")));
			sectionType.selectByIndex(10);
			sectionType.selectByValue("-11");
		    sectionType.selectByVisibleText("Dynamic 2-Column Section");
			driver.findElement(By.xpath("//a[@class='dropdownOpt' and @name='-11']")).click();
			System.out.println("test");*/		
			wait(2);			
			driver.switchTo().defaultContent();
			wait(1);			
			return tempName;
	}
	
	public void newFieldAdd()
	{
		wait(2);		
		By add_Value = By.xpath("//a/img[@alt ='Add Value']");			
		Select fieldType = new Select(driver.findElement(By.id("fieldType")));
		fieldType.selectByIndex(5);
		fieldType.selectByValue("5");
		fieldType.selectByVisibleText("Dropdown Menu");
		wait(3);
		//String fieldName = "Gender" + new Date().getTime();
		String excel_file = "files//TemplateDetails.xlsx";
		String sheet_name = "TemplateValues";
		String fieldName = Excel.readFromExcel(excel_file, sheet_name, 7, 1);
		enter(description,fieldName);
		//String maleField = "Male"+ new Date().getTime();
		String maleField = Excel.readFromExcel(excel_file, sheet_name, 8, 1);
		enter(field_value,maleField);
		click(add_Value);
		//String femaleField = "Female" + new Date().getTime();
		String femaleField = Excel.readFromExcel(excel_file, sheet_name, 9, 1);
		enter(field_value,femaleField);
		click(savesection_button);
		wait(4);
		
		
		// adding single line text field value
		String sectionName1Value = Excel.readFromExcel(excel_file, sheet_name, 2, 1);
		driver.findElement(By.xpath("//a[@title='"+sectionName1Value+"']//following::a[1]")).click();
		wait(2);
		String fieldName1 = Excel.readFromExcel(excel_file, sheet_name, 6, 1); 
		wait(2);
		driver.findElement(By.xpath("//input[@id='description']")).sendKeys(fieldName1);
		click(savesection_button);
	}
	

}
