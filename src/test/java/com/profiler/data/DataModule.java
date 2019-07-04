package com.profiler.data;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.profiler.utils.AppUtil;
import com.profiler.utils.Excel;
import com.profiler.utils.Functions;
import com.relevantcodes.extentreports.LogStatus;

public class DataModule extends Functions {

	
	AppUtil appUtil = new AppUtil();
	
	// Data :-
	String excel_file = "Files//TestData.xlsx";
	String sheet_name = "Application";
	String sheet_name2 = "ActivityTypes";

	String url = Excel.readFromExcel(excel_file, sheet_name, 1, 1);
	String username = Excel.readFromExcel(excel_file, sheet_name, 2, 1);
	String password = Excel.readFromExcel(excel_file, sheet_name, 3, 1);

//	String ActTypes = Excel.readFromExcel(excel_file, sheet_name2, 1, 1);
//	String ActType_Modify = Excel.readFromExcel(excel_file, sheet_name2, 2, 1);
	String ActTypes = "A_test_qa";
	String ActType_Modify ="A_test_qa_Modify";

	// Locators :-

	// Activity Type
	By ActivityLink = By.xpath("//a[starts-with(text(),'Activity Types')]");
	By New_Button = By.xpath("//span/img[@src='/newimages/icons/new.gif']");
	By Name_TextBox = By.xpath("//input[@name='description']");
	By Role = By.xpath("//div[@id='roleId_dd_label']");
	By RoleSelect = By.xpath("//input[@id='Abbott Diagnostics Admin']");
	By SaveButton = By.xpath("//img[@name='save2']");
	By NewBtn = By.xpath("//span[@id='header_javascript:loadGoalProjectActivity()']");
	By ActTypecheck = By.xpath("//input[@class='dropdown dd-all']");
	By MoreLink = By.xpath("//a[@title='More']");
	By ToolsLink = By.xpath("//a[@id='section_tools']");
	By UserActivityLink = By.xpath("//a[@id='section_user_activity']");
	By ActivityTypeName = By.xpath("//a[contains(text(), '" + ActTypes + "')]");
	By Modified_ActivityTypeName = By.xpath("//a[contains(text(), '" + ActType_Modify + "')]");
	By CloseAddGoalPopUp = By.xpath("//img[@class='ico ico-close boxPopup-close']");
	By ModifyImageLink = By.xpath("(//td/div[contains(text(),'" + ActTypes + "')]/following::a)[2]");
	By DeleteImageLink = By.xpath("(//td/div[contains(text(),'" + ActType_Modify + "')]/following::a)[1]");
	By DropDownOptions = By.xpath("//a[@class='dropdownOpt']");
	// Approved Material
	By ApprovedMaterial_link = By.xpath("//a[starts-with(text(),'Approved Materials')]");
	By ModifyImageLink_APM = By.xpath("(//td/div[contains(text(),'" + ActTypes + "')]/following::a)[3]");
	By DeleteImageLink_APM = By.xpath("(//td/div[contains(text(),'" + ActType_Modify + "')]/following::a)[2]");
	// Event Types
	By EventTypes_link = By.xpath("//a[starts-with(text(),'Event Types')]");
	By Event_Link = By.xpath("//a[@title='Event']");
	By Go_Link = By.xpath("//span[@id='event_go']");
	By EventType_Drpdwn = By.xpath("//div[@id='eventType_dd_div']/input");
	By EventType_DrdwnValue = By.xpath("//a[text()='" + ActTypes + "']");
	By EventType_ModifiedValue = By.xpath("//a[text()='" + ActType_Modify + "']");
	By EBrands_Drpdwn = By.xpath("//div[@id='brandEvents_dd_label']");
	By EventName_txtbx = By.xpath("//input[@id='eventName']");
	By StartDate = By.xpath("//input[@id='startDate']");
	By EndDate = By.xpath("//input[@id='endDate']");
	By AddParticipentAndSave = By.xpath("//span[@id='AddParticipantAndSave']");
	By Search_Image = By.xpath("//img[@alt='Search for Organization']");
	By SelectedBrand_drpdwnVal = By
			.xpath("//div[@id='selectedBrands_dd_options']//following::li/input[@title='Abbott NA']");
	By SelectedBrand_drpdwnVal1 = By
			.xpath("//div[@id='selectedBrands_dd_options']//following::li/input[@title='Abbott']");
	By EventRole_List = By.xpath("(//select[@id='eventRoleList'])[1]");
	By AddRole_buttn = By.xpath("//span[@id='add_role']");
	By Save = By.xpath("//span[text()='Save']");
	By Radio_buttn = By.xpath("//input[@id='tbl-containerrbutton']");
	By AddProfile_buttn = By.xpath("//span[text()='Add Profiles']");
	By Event_Tab = By.xpath("//a[@id='section_event']");
	By Search_buttn = By.xpath("//span[text()='SEARCH']");
	// Forms/Agreement
	By FA_link = By.xpath("//a[starts-with(text(),'Forms/Agreements')]");
	By ProfileTab_link = By.xpath("//*[@id='section_profile']");
	
	By prof_searchBox1 = By.id("searchall");/////
	By search_btn =By.id("search_list_btn");
	By FirstProfile_link = By.xpath("//a[contains(text(),'Ted Thaell, John Fredrick Dr.')]");
	
	By FA_EditLink = By.xpath("//div[text()='ddkdkkd']/following::a[@title='edit']/span/img");
	By DocumentsLink = By.xpath("//a[@title='Documents']");
	By FA_NewLink = By.xpath("//a[@id='Forms/Agreementss']/following::span[1]");
	By AgreementType_Drpdwn = By.xpath("//input[@id='agreementTitle']/preceding::input[1]");
	By AgreementType_drpdwnValue = By.xpath("//a[text()='" + ActTypes + "']");
	By AgreementType_ModifiedValue = By.xpath("//a[text()='" + ActType_Modify + "']");
	By AgreementTitle_Txtbx = By.xpath("//input[@id='agreementTitle']");
	By SignedDate = By.xpath("//input[@id='signedDate1']");
	By SignedDate_tbody = By.xpath("//table[@class='ui-datepicker-calendar']/tbody");
	By Requested_chxbx = By.xpath("//input[@id='requested']");
	By FA_EditImage = By.xpath("//div[text()='" + ActTypes + "']/following::img[@class='ico ico-edit']");
	By FA_EditImage_Modify = By.xpath("//div[text()='" + ActType_Modify + "']/following::img[@class='ico ico-edit']");
	// InteractionTypes
	By InteractionTypes_link = By.xpath("//a[starts-with(text(),'Interaction Types')]");
	By forwordlink = By.xpath("//a/img[@name='forward']");
	By GlobalInteractionNew_drpdwn = By.xpath("//span[contains(text(),'Global Create New')]");
	By Interaction_dropdownlink = By.xpath("//a[text()='Interaction']");
	By Template_drpdwn = By.xpath("//input[@class='dropdown dd-all']");
	By DefaultTemplate_link = By.xpath("//a[starts-with(text(),'Default Template')]");
	By InteractionType_drpdwn = By.xpath("//div[@id='interactionType_dd_div']/input[@class='dropdown dd-all']");
	By InteractionType_drpdwnValue = By.xpath("//a[text()='" + ActTypes + "']");
	By ModifiedInteractionType = By.xpath("//a[text()='" + ActType_Modify + "']");
	By FBrands_drpdwn = By.xpath("//div[@id='brandInteractions_dd_label']");
	By Brand_chkbx = By.xpath("//input[@title='Abbott NA']");
	By InteractionContext_drpdwn = By.xpath("//div[@id='interactionContext_dd_div']/input[@class='dropdown dd-all']");
	By InteractionContext_drpdwnvalue = By.xpath("//a[contains(text(),'Proactive')]");
	By AddParticipants_btn = By.xpath("//span[@id='intr_save']");
	By SelectdBrand_drpdwn = By.xpath("//div[@id='selectedBrands_dd_label']");
	By Search_link = By.xpath("//a/span[text()='Search']");
	By ProfileSearch_chkbx = By.xpath("//input[@id='tbl-containercbox']");
	By SearchAndFinish_link = By.xpath("//a/span[text()='Save & Finish']");
	By Actions_drpdwn = By.xpath("//span[text()='Actions']");
	By Edit_link = By.xpath("//a[@title='Edit']");
	By PD_NewLink = By.xpath("//a[@title='PD']//following::span[text()='New'][1]");
	By Topic_drpdwn = By.xpath("//*[@id='interactionTopics_dd_label']");//("//div[@id='topicDiv']//input[@class='dropdown dd-all']");
	By Topic_drpdwnvalue = By.xpath("//label[text()='Abdomen']");
	By SaveInterBtn = By.xpath("//*[@id='intr_save']");//------------added
	By MaterialUsed_drpdwn = By.xpath("//div[@id='materialsUsedId_dd_label']");
	By MaterialUsed_drpdwnValue = By.xpath("//input[@title='" + ActTypes + "']");
	By ModifiedMaterialUsed = By.xpath("//input[@title='" + ActType_Modify + "']");
	By Save_button = By.xpath("//span[@id='saveInteractionUnsolicitedRequest']");
	By Close_button = By.xpath("//a[@title='close']/img");
	// Languages
	By Languages_link = By.xpath("//a[starts-with(text(),'Languages')]");
	By Biography_link = By.xpath("//a[@title='Biography']");
	By Languages_drpdwn = By.xpath("//*[@id='personLanguages_dd_label']");
	By Languages_drpdwnValue = By.xpath("//input[@title='" + ActTypes + "']");
	By Languages_ModifiedValue = By.xpath("//input[@title='" + ActType_Modify + "']");
	// Payment Types
	By PaymentTypes_link = By.xpath("//a[starts-with(text(),'Payment Types')]");
	By Payment_Save_button = By.xpath("//input[@name='save2']");
	By EventName_link = By.xpath("(//a[text()='" + ActTypes + "'])[1]");
	By EventName_Modified_link = By.xpath("(//a[text()='" + ActType_Modify + "'])[1]");
	By SearchEvent_txtbox = By.xpath("//input[@id='searchall']");
	By Payment_Add_Button = By.xpath("//a[@id='Paymentss']/following::span[1]");
	By PaymentType_Drpdwn = By.xpath("//div[@id='personPaymentType_dd_div']/input");
	By PaymentType_DrpdwnValue = By.xpath("(//a[starts-with(text(),'" + ActTypes + "')])[2]");
	By PaymentType_ModifiedValue = By.xpath("(//a[starts-with(text(),'" + ActTypes + "')])[2]");
	By StartDate_Column_link = By.xpath("//span[text()='Start Date']");

	// Specialties
	By Specialties_link = By.xpath("//a[starts-with(text(),'Specialties')]");
	By Specialities_drpdwn = By.xpath("//div[@id='personSpecialties_dd_label']");
	By Specialities_DrpdwnValue = By.xpath("//input[@title='" + ActTypes + "']");
	By Specialities_ModifiedValue = By.xpath("//input[@title='" + ActType_Modify + "']");
	// Topics
	By Topics_link = By.xpath("//a[starts-with(text(),'Topics')]");
	By TopicType_Dropdown = By.xpath("//img[@id='topicTypeId_dd_image']");
	By TopicType_CheckBox = By.xpath("//input[@id='Event']");
	By DateCreated_link = By.xpath("//a[text()='Date Created']");
	By LastUpdated_link = By.xpath("//a[text()='Last Updated']");
	By Topics_Add_Button = By.xpath("//a[@id='Topicss']/following::span[1]");
	By FilterMainTopic_Drpdwn = By.xpath("//div[@id='parentTopicsArray_dd_label']");
	By Topics_DrpdwnValue = By.xpath("//input[@title='" + ActTypes + "']");
	By Topics_ModifiedValue = By.xpath("//input[@title='" + ActType_Modify + "']");

		@Test

	public void activityTypes() {

		// Login to the application

		extentTest = extent.startTest("Activity Types Verification");
		

		if (appUtil.login(username, password))
			System.out.println("User Login SUCCESSFUL");
		else
			Assert.fail("addProfileEducation() - User Login UNSUCCESSFUL");

		// Create activity type and Verify

		//mouseHoverAndClcik(MoreLink, ToolsLink);
		appUtil.gotoToolsPage();
		wait(3);
	//	driver.switchTo().frame("toolsContent");
		click(ActivityLink);
		wait(3);
		click(New_Button);
		wait(2);
		enter(Name_TextBox, ActTypes);
		click(Role);
		click(RoleSelect);
		click(SaveButton);
		wait(5);
		driver.switchTo().defaultContent();
		Validate_ActivityType(ActivityTypeName);

		// Modifying Activity Type
		modifyData(ActivityLink, ModifyImageLink);
		Validate_ActivityType(Modified_ActivityTypeName);
		// click(CloseAddGoalPopUp);

		// Delete Modified Activity Type
		deleteData(ActivityLink, DeleteImageLink);

		// Verify
		mouseHoverAndClcik(MoreLink, UserActivityLink);
		wait(3);
		click(NewBtn);
		wait(2);
		click(ActTypecheck);

		try {

			click(Modified_ActivityTypeName);
			Assert.fail(
					"ERROR: User is able to select the dropdown which is supposed to be deleted from the list,Delete Operation was not successfull: ");

		} catch (Exception e) {

			extentTest.log(LogStatus.PASS,
					"Deleted Element is not present in the list, Delete Opetatioin was succussfull.");
		}

		click(CloseAddGoalPopUp);
		appUtil.logout();
	}
	

//Disabled for time being
	@Test(enabled=false)

	public void Verify_Approved_Materials() {

		// Login to the application

		extentTest = extent.startTest("Approved Material Verification");
		//AppUtil appUtil = new AppUtil();

		if (appUtil.login(username, password))
			System.out.println("User Login SUCCESSFUL");
		else
			Assert.fail("addProfileEducation() - User Login UNSUCCESSFUL");

		// Creating New Approved Material and Verify
		createData(InteractionTypes_link);
		//mouseHoverAndClcik(MoreLink, ToolsLink);
		appUtil.gotoToolsPage();
		wait(3);
		//driver.switchTo().frame("toolsContent");
		click(ApprovedMaterial_link);
		wait(3);
		click(New_Button);
		wait(2);
		enter(Name_TextBox, ActTypes);
		click(Role);
		click(RoleSelect);
		click(SaveButton);
		wait(5);
		driver.switchTo().defaultContent();
		// verification
		extentTest.log(LogStatus.INFO, "Verification Started After creating the Approved Material..");
		Validate_InteractionTypeAndApprovedMaterial(InteractionType_drpdwnValue, MaterialUsed_drpdwnValue);

		// Modifying Approved Material and verify
		modifyData(InteractionTypes_link, ModifyImageLink);
		modifyData(ApprovedMaterial_link, ModifyImageLink_APM);

		// verification
		extentTest.log(LogStatus.INFO, "Verification Started After Modifying the Approved Material..");
		Validate_InteractionTypeAndApprovedMaterial(ModifiedInteractionType, ModifiedMaterialUsed);

		// Deleting Approved Material and Verify.

		deleteData(ApprovedMaterial_link, DeleteImageLink_APM);
		// verify
		extentTest.log(LogStatus.INFO, "Verification Started After Deleted the Approved Material..");
		click(GlobalInteractionNew_drpdwn);
		click(Interaction_dropdownlink);
		click(Template_drpdwn);
		click(DefaultTemplate_link);
		click(InteractionType_drpdwn);
		click(ModifiedInteractionType);
		click(FBrands_drpdwn);
		click(Brand_chkbx);
		click(FBrands_drpdwn);
		click(InteractionContext_drpdwn);
		click(InteractionContext_drpdwnvalue);
		click(AddParticipants_btn);
		click(SelectdBrand_drpdwn);
		click(Brand_chkbx);
		click(Search_link);
		click(ProfileSearch_chkbx);
		click(SearchAndFinish_link);
		click(Actions_drpdwn);
		click(Edit_link);
		click(PD_NewLink);
		waitForElementtoBeClickable(Topic_drpdwn);
		click(Topic_drpdwn);
		click(Topic_drpdwnvalue);
		click(MaterialUsed_drpdwn);
		try {
			click(ModifiedMaterialUsed);
			extentTest.log(LogStatus.FAIL,
					" Deleted MaterialUsed Item is present in the list.. Verification UnSuccessful");
		} catch (Exception e) {
			extentTest.log(LogStatus.PASS,
					" Deleted MaterialUsed Item is  not present in the list.. Verification Successful");
		}
		click(Close_button);
		deleteData(InteractionTypes_link, DeleteImageLink);

		extentTest.log(LogStatus.INFO, "Test Casse execution completed for Approved Material...");
appUtil.logout();
	}

	@Test

	public void Verify_Event_Types() {

		// Login to the application

		extentTest = extent.startTest("Event Types Verification");
	//	AppUtil appUtil = new AppUtil();

		if (appUtil.login(username, password))
			System.out.println("User Login SUCCESSFUL");
		else
			Assert.fail("addProfileEducation() - User Login UNSUCCESSFUL");

		// Creating New Envent Types and Verify
		extentTest.log(LogStatus.INFO, "Creating New Event Type...");
		createData(EventTypes_link);
		Validate_EventType(EventType_DrdwnValue);

		// Modifying Envent Types and verify
		extentTest.log(LogStatus.INFO, "Updating Event Type...");
		// waitForElementtoBeClickable(MoreLink);
		modifyData(EventTypes_link, ModifyImageLink);
		Validate_EventType(EventType_ModifiedValue);

		// Deleting Envent Types and Verify.
		extentTest.log(LogStatus.INFO, "Deleting Event Type...");
		deleteData(EventTypes_link, DeleteImageLink);
		// verify
		click(GlobalInteractionNew_drpdwn);
		click(Event_Link);
		click(Template_drpdwn);
		click(DefaultTemplate_link);
		click(Go_Link);
		enter(EventName_txtbx, ActTypes);
		DatePicker_CurrentDate(StartDate, SignedDate_tbody);
		DatePicker_CurrentDate(EndDate, SignedDate_tbody);
		click(EventType_Drpdwn);

		try {
			click(EventType_ModifiedValue);
			extentTest.log(LogStatus.FAIL, "Deleted Event Type is present in the list.. Verificatin Failed");
		} catch (Exception e) {
			extentTest.log(LogStatus.PASS, "Deleted Event Type is not present in the list. Verification Successfull");
		}
	appUtil.logout();
	}

		@Test

	public void Verify_Forms_Agreements() {

		// Login to the application

		extentTest = extent.startTest("Forms/Agreements Verification");
	//	AppUtil appUtil = new AppUtil();

		if (appUtil.login(username, password))
			System.out.println("User Login SUCCESSFUL");
		else
			Assert.fail("addProfileEducation() - User Login UNSUCCESSFUL");

		// Creating Forms/Agreement and Verify
		extentTest.log(LogStatus.INFO, "Creating New Forms/Agreement...");
		
		createData(FA_link);
		
		//createData(EventTypes_link);
		// verify
		waitForElementtoBeClickable(ProfileTab_link);
		
		Validate_FA(AgreementType_drpdwnValue);

		// Modifying Forms/Agreement and verify
		extentTest.log(LogStatus.INFO, "Updating Forms/Agreement...");
		modifyData(FA_link, ModifyImageLink);
		Validate_FA(AgreementType_ModifiedValue);

		// Deleting Forms/Agreement and Verify.
		extentTest.log(LogStatus.INFO, "Deleting Forms/Agreement...");
		deleteData(FA_link, DeleteImageLink);
		click(ProfileTab_link);
		enter(prof_searchBox1, "Ted Thaell, John");
		click(search_btn);
		wait(3);
		waitForElementtoBeClickable(FirstProfile_link);
		click(FirstProfile_link);
		wait(3);
		click(DocumentsLink);
		waitForElementtoBeClickable(Actions_drpdwn);
		click(Actions_drpdwn);
		click(Edit_link);
		click(FA_NewLink);
		click(AgreementType_Drpdwn);

		try {
			click(AgreementType_ModifiedValue);
			extentTest.log(LogStatus.FAIL, "Deleted AgreementType is present in the list.. Verificatin UnSuccessfull");
		} catch (Exception e) {
			extentTest.log(LogStatus.PASS,
					"Deleted Agreement Type Item not present in the List : " + "Verification Successfull");
		}
		enter(AgreementTitle_Txtbx, ActTypes);
		DatePicker_CurrentDate(SignedDate, SignedDate_tbody);
		click(Close_button);
appUtil.logout();
	}

		@Test

	public void Verify_Forms_Agreements_WithRequested() {

		// Login to the application

		extentTest = extent.startTest("Forms/Agreements With Requested Verification");
	//	AppUtil appUtil = new AppUtil();

		if (appUtil.login(username, password))
			System.out.println("User Login SUCCESSFUL");
		else
			Assert.fail("addProfileEducation() - User Login UNSUCCESSFUL");

		// Creating Forms/Agreement with Requested and Verify
		extentTest.log(LogStatus.INFO, "Creating New Forms/Agreement with requested ...");
		// createData(FA_link);
	//	mouseHoverAndClcik(MoreLink, ToolsLink);
		wait(3);
	//	driver.switchTo().frame("toolsContent");
		appUtil.gotoToolsPage();
		wait(3);
		click(FA_link);
		wait(3);
		click(New_Button);
		wait(2);
		enter(Name_TextBox, ActTypes);
		click(Requested_chxbx);
		click(SaveButton);
		wait(5);
		driver.switchTo().defaultContent();
		// verify
		waitForElementtoBeClickable(ProfileTab_link);
		Validate_FA_WithRequest(FA_EditImage);

		// Modifying Forms/Agreement and verify
		extentTest.log(LogStatus.INFO, "Updating Forms/Agreement...");
		modifyData(FA_link, ModifyImageLink);
		Validate_FA_WithRequest(FA_EditImage_Modify);

		// Deleting Forms/Agreement and Verify.
		extentTest.log(LogStatus.INFO, "Deleting Forms/Agreement...");
		deleteData(FA_link, DeleteImageLink);
		click(ProfileTab_link);
		enter(prof_searchBox1, "Ted Thaell, John");
		click(search_btn);
		wait(3);
		waitForElementtoBeClickable(FirstProfile_link);
		click(FirstProfile_link);
		click(DocumentsLink);
		waitForElementtoBeClickable(Actions_drpdwn);
		click(Actions_drpdwn);
		click(Edit_link);
		try {
			click(FA_EditImage_Modify);
			extentTest.log(LogStatus.FAIL, "Deleted AgreementType is present in the list.. Verificatin UnSuccessfull");
		} catch (Exception e) {
			extentTest.log(LogStatus.PASS,
					"Deleted Agreement Type Item not present in the List : " + "Verification Successfull");
		}
		appUtil.logout();

	}

//Disabled for time being
		@Test( enabled=false)

	public void Verify_InteractionTypes() {

		// Login to the application

		extentTest = extent.startTest("InteractionTypes Verification");
	//	AppUtil appUtil = new AppUtil();

		if (appUtil.login(username, password))
			System.out.println("User Login SUCCESSFUL");
		else
			Assert.fail("addProfileEducation() - User Login UNSUCCESSFUL");

		// Creating New InteractionTypes and Verify
		extentTest.log(LogStatus.INFO, "Creating New InteractionType...");
		createData(InteractionTypes_link);
		createData(ApprovedMaterial_link);
		Validate_InteractionTypeAndApprovedMaterial(InteractionType_drpdwnValue, MaterialUsed_drpdwnValue);

		// Modifying InteractionTypes and verify
		extentTest.log(LogStatus.INFO, "Updating InteractionType...");
		modifyData(InteractionTypes_link, ModifyImageLink);
		modifyData(ApprovedMaterial_link, ModifyImageLink_APM);
		Validate_InteractionTypeAndApprovedMaterial(ModifiedInteractionType, ModifiedMaterialUsed);

		// Deleting InteractionTypes and Verify.
		extentTest.log(LogStatus.INFO, "Deleting InteractionType...");
		deleteData(InteractionTypes_link, DeleteImageLink);
		// verify
		click(GlobalInteractionNew_drpdwn);
		click(Interaction_dropdownlink);
		click(Template_drpdwn);
		click(DefaultTemplate_link);
		click(InteractionType_drpdwn);
		try {
			click(ModifiedInteractionType);
			extentTest.log(LogStatus.FAIL,
					" Deleted Interaction Type is present in the list.. Verificatin UnSuccessfull");
		} catch (Exception e) {
			extentTest.log(LogStatus.PASS,
					"Deleted Interaction Type Item not present in the List : " + "Verification Successfull");
		}
appUtil.logout();
	}

	@Test

	public void Verify_Languages() {

		// Login to the application

		extentTest = extent.startTest("Languages Verification");
	//	AppUtil appUtil = new AppUtil();

		if (appUtil.login(username, password))
			System.out.println("User Login SUCCESSFUL");
		else
			Assert.fail("addProfileEducation() - User Login UNSUCCESSFUL");

		// Creating New Language
		extentTest.log(LogStatus.INFO, "Creating New Language...");
		createData(Languages_link);
		Validate_Languages(Languages_drpdwnValue);

		// Modifying Language
		extentTest.log(LogStatus.INFO, "Updating Language...");
		modifyData(Languages_link, ModifyImageLink);
		Validate_Languages(Languages_ModifiedValue);

		// Deleting Language.
		extentTest.log(LogStatus.INFO, "Deleting Language...");
		deleteData(Languages_link, DeleteImageLink);
		// verify
		click(ProfileTab_link);
		enter(prof_searchBox1, "Ted Thaell, John");
		click(search_btn);
		wait(3);
		waitForElementtoBeClickable(FirstProfile_link);
		click(FirstProfile_link);
		click(Biography_link);
		waitForElementtoBeClickable(Actions_drpdwn);
		click(Actions_drpdwn);
		click(Edit_link);
		waitForElementtoBeVisible(Languages_drpdwn);
		click(Languages_drpdwn);
		try {
			click(Languages_ModifiedValue);
			extentTest.log(LogStatus.FAIL, " Deleted Language Type is present in the list.. Verificatin UnSuccessfull");
		} catch (Exception e) {
			extentTest.log(LogStatus.PASS,
					"Deleted Language Type Item not present in the List : " + "Verification Successfull");
		}
appUtil.logout();
	}

	@Test

	public void Verify_PaymentTypes() {

		// Login to the application

		extentTest = extent.startTest("Payment Types Verification");
	//	AppUtil appUtil = new AppUtil();

		if (appUtil.login(username, password))
			System.out.println("User Login SUCCESSFUL");
		else
			Assert.fail("addProfileEducation() - User Login UNSUCCESSFUL");

		// Creating New Payment Type
		extentTest.log(LogStatus.INFO, "Creating Payment Type...");

	
		wait(3);
		appUtil.gotoToolsPage();
		/*
		mouseHoverAndClcik(MoreLink, ToolsLink);
		wait(3);
		driver.switchTo().frame("toolsContent");
		*/
		click(PaymentTypes_link);
		wait(3);
		click(New_Button);
		wait(2);
		enter(Name_TextBox, ActTypes);
		wait(3);
		click(Payment_Save_button);
		wait(5);
		driver.switchTo().defaultContent();
		// verify
		Validate_PaymentTypes(ActTypes, PaymentType_DrpdwnValue);

		// Modifying Payment Type
		extentTest.log(LogStatus.INFO, "Updating Payment Type...");
		// modifyData(PaymentTypes_link, ModifyImageLink);

		appUtil.gotoToolsPage();
	//	mouseHoverAndClcik(MoreLink, ToolsLink);
		wait(3);
		//driver.switchTo().frame("toolsContent");
		click(PaymentTypes_link);
		wait(3);
		click(ModifyImageLink);
		driver.findElement(Name_TextBox).clear();
		enter(Name_TextBox, ActType_Modify);
		click(Payment_Save_button);
		wait(5);
		driver.switchTo().defaultContent();
		// verify
		Validate_PaymentTypes(ActType_Modify, PaymentType_ModifiedValue);

		// Deleting Payment Type.
		extentTest.log(LogStatus.INFO, "Deleting Payment Type...");
		deleteData(PaymentTypes_link, DeleteImageLink);
		// verify
		
		click(Event_Tab);
		enter(SearchEvent_txtbox, ActType_Modify);
		click(Search_buttn);
		click(EventName_link);
		waitForElementtoBeClickable(Actions_drpdwn);
		click(Actions_drpdwn);
		click(Edit_link);
		click(Payment_Add_Button);
		click(PaymentType_Drpdwn);
		try {
			click(PaymentType_ModifiedValue);
			extentTest.log(LogStatus.FAIL, " Deleted PaymentType is present in the list.. Verificatin UnSuccessfull");
		} catch (Exception e) {
			extentTest.log(LogStatus.PASS,
					"Deleted PaymentType Item not present in the List : " + "Verification Successfull");
		}

		click(Close_button);
appUtil.logout();
	}

		@Test

	public void Verify_Specialties() {

		// Login to the application

		extentTest = extent.startTest("Specialties Verification");
		//AppUtil appUtil = new AppUtil();

		if (appUtil.login(username, password))
			System.out.println("User Login SUCCESSFUL");
		else
			Assert.fail("addProfileEducation() - User Login UNSUCCESSFUL");

		// Creating New Specialties
		extentTest.log(LogStatus.INFO, "Creating Specialties...");
		createData(Specialties_link);
		Validate_Specialities(Specialities_DrpdwnValue);

		// Modifying specialties
		extentTest.log(LogStatus.INFO, "Updating Specialties...");
		modifyData(Specialties_link, ModifyImageLink);
		Validate_Specialities(Specialities_ModifiedValue);

		// Deleting Specialties.
		extentTest.log(LogStatus.INFO, "Deleting Specialties...");
		deleteData(Specialties_link, DeleteImageLink);
		// Verify
		click(ProfileTab_link);
		enter(prof_searchBox1, "Ted Thaell, John");
		click(search_btn);
		wait(3);
		waitForElementtoBeClickable(FirstProfile_link);
		click(FirstProfile_link);
		click(Biography_link);
		waitForElementtoBeClickable(Actions_drpdwn);
		click(Actions_drpdwn);
		click(Edit_link);
		click(Specialities_drpdwn);
		try {
			click(Specialities_ModifiedValue);
			extentTest.log(LogStatus.FAIL, " Deleted Language Type is present in the list.. Verificatin UnSuccessfull");
		} catch (Exception e) {
			extentTest.log(LogStatus.PASS,
					"Deleted Language Type Item not present in the List : " + "Verification Successfull");
		}
		appUtil.logout();
	}

			@Test

	public void Verify_Topics() {

		// Login to the application

		extentTest = extent.startTest("Topics Verification");
	//	AppUtil appUtil = new AppUtil();

		if (appUtil.login(username, password))
			System.out.println("User Login SUCCESSFUL");
		else
			Assert.fail("addProfileEducation() - User Login UNSUCCESSFUL");

		// Creating New Topic
		extentTest.log(LogStatus.INFO, "Creating Topic...");
		//mouseHoverAndClcik(MoreLink, ToolsLink);
		//wait(3);
		//driver.switchTo().frame("toolsContent");
		appUtil.gotoToolsPage();
		wait(3);
		click(Topics_link);
		wait(3);
		click(New_Button);
		wait(2);
		click(TopicType_Dropdown);
		click(TopicType_CheckBox);
		click(TopicType_Dropdown);
		enter(Name_TextBox, ActTypes);
		click(Role);
		click(RoleSelect);
		click(SaveButton);
		wait(5);
		driver.switchTo().defaultContent();
		// verify
		Validate_Topics(ActTypes, Topics_DrpdwnValue);

		// Modifying Topic
		extentTest.log(LogStatus.INFO, "Updating Topic...");
		mouseHoverAndClcik(MoreLink, ToolsLink);
		wait(3);
		driver.switchTo().frame("toolsContent");
		click(Topics_link);
		wait(3);
		click(DateCreated_link);
		click(ModifyImageLink);
		driver.findElement(Name_TextBox).clear();
		enter(Name_TextBox, ActType_Modify);
		click(SaveButton);
		wait(5);
		driver.switchTo().defaultContent();
		// verify
		Validate_Topics(ActTypes, Topics_ModifiedValue);

		// Deleting Topic.
		extentTest.log(LogStatus.INFO, "Deleting Topic...");
		mouseHoverAndClcik(MoreLink, ToolsLink);
		wait(3);
		driver.switchTo().frame("toolsContent");
		click(Topics_link);
		wait(3);
		click(LastUpdated_link);
		click(DeleteImageLink);
		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();
		// verify
		wait(3);
		click(Event_Tab);
		enter(SearchEvent_txtbox, ActTypes);
		click(Search_buttn);
		click(EventName_link);
		waitForElementtoBeClickable(Actions_drpdwn);
		click(Actions_drpdwn);
		click(Edit_link);
		click(Topics_Add_Button);
		click(FilterMainTopic_Drpdwn);
		try {
			click(Topics_ModifiedValue);
			extentTest.log(LogStatus.FAIL, "Deleted Topics is present in the list.. Verificatin UnSuccessfull");
		} catch (Exception e) {
			extentTest.log(LogStatus.PASS,
					"Deleted Topic is not present in the List : " + "Verification Successfull" + "Verification Failed");
		}

		click(Close_button);
		appUtil.logout();

	}

	public void createData(By locator) {
	//	mouseHoverAndClcik(MoreLink, ToolsLink);
		wait(3);
	//	driver.switchTo().frame("toolsContent");
		appUtil.gotoToolsPage();
		wait(3);
		click(locator);
		wait(3);
		click(New_Button);
		wait(2);
		enter(Name_TextBox, ActTypes);
		wait(3);
		click(SaveButton);
		wait(5);
		driver.switchTo().defaultContent();
	}

	public void modifyData(By DataType, By ModifyImage) {
		waitForElementtoBeVisible(MoreLink);
		waitForElementtoBeClickable(MoreLink);
		//mouseHoverAndClcik(MoreLink, ToolsLink);
		wait(3);
		//driver.switchTo().frame("toolsContent");
		
		appUtil.gotoToolsPage();
		wait(3);
		click(DataType);
		wait(3);
		click(ModifyImage);
		driver.findElement(Name_TextBox).clear();
		enter(Name_TextBox, ActType_Modify);
		click(SaveButton);
		wait(5);
		driver.switchTo().defaultContent();
	}

	public void deleteData(By DataType, By DeleteImage) {
		waitForElementtoBeClickable(MoreLink);
	//	mouseHoverAndClcik(MoreLink, ToolsLink);
		wait(3);
		//driver.switchTo().frame("toolsContent");
		appUtil.gotoToolsPage();
		wait(3);
		click(DataType);
		wait(3);
		click(DeleteImage);
		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();

	}

	public void Validate_InteractionTypeAndApprovedMaterial(By InteractionTypeLocator, By ApprovedMaterialLocator) {
		click(GlobalInteractionNew_drpdwn);
		click(Interaction_dropdownlink);
		click(Template_drpdwn);
		click(DefaultTemplate_link);
		click(InteractionType_drpdwn);
		try {
			click(InteractionTypeLocator);
			extentTest.log(LogStatus.PASS,
					"Desired Interaction type is  present in the list.. Verification Successfull");
		} catch (Exception e) {
			extentTest.log(LogStatus.FAIL, "ERROR: Exception occured " + "while clicking InteractionType list item: "
					+ "Verification Unsuccessfull" + e);
		}
		click(FBrands_drpdwn);
		click(Brand_chkbx);
		click(FBrands_drpdwn);
		
		/* **/
		click(InteractionContext_drpdwn);
		click(InteractionContext_drpdwnvalue);
		click(AddParticipants_btn);
		click(SelectdBrand_drpdwn);
		click(Brand_chkbx);
		click(Search_link);
		click(ProfileSearch_chkbx);
		click(SearchAndFinish_link);
		click(Actions_drpdwn);
		click(Edit_link);
		click(PD_NewLink);
		/* **/
		waitForElementtoBeClickable(Topic_drpdwn);
		click(Topic_drpdwn);
		click(Topic_drpdwnvalue);
		//click(SaveInterBtn);
		click(MaterialUsed_drpdwn);
		try {
			click(ApprovedMaterialLocator);
			extentTest.log(LogStatus.PASS,
					"Desired MaterialUsed Item is  present in the list.. Verificatin Successfull");
		} catch (Exception e) {
			extentTest.log(LogStatus.FAIL, "ERROR: Exception occured " + "while clicking MaterialUsed list item: "
					+ "Verification Failed" + e);
		}
		click(Save_button);

	}

	public void Validate_FA(By Drpdwonbvalue) {
		click(ProfileTab_link);
	//	waitForElementtoBeClickable(FirstProfile_link);
		enter(prof_searchBox1, "Ted Thaell, John");
		click(search_btn);
		wait(3);
		waitForElementtoBeClickable(FirstProfile_link);
		click(FirstProfile_link);
		click(DocumentsLink);
		waitForElementtoBeClickable(Actions_drpdwn);
		click(Actions_drpdwn);
		click(Edit_link);
		click(FA_NewLink);
		click(AgreementType_Drpdwn);

		try {
			click(Drpdwonbvalue);
			extentTest.log(LogStatus.PASS, "Desired AgreementType is  present in the list.. Verificatin Successfull");
		} catch (Exception e) {
			extentTest.log(LogStatus.FAIL, "ERROR: Exception occured " + "while Selecting AgreementType list item: "
					+ "Verification Failed" + e);
		}
		enter(AgreementTitle_Txtbx, ActTypes);
		DatePicker_CurrentDate(SignedDate, SignedDate_tbody);
		click(Close_button);
	}

	public void Validate_FA_WithRequest(By ModifyImage) {
		click(ProfileTab_link);
		enter(prof_searchBox1, "Ted Thaell, John");
		click(search_btn);
		wait(3);
		waitForElementtoBeClickable(FirstProfile_link);
		click(FirstProfile_link);
		click(DocumentsLink);
		waitForElementtoBeClickable(Actions_drpdwn);
		click(Actions_drpdwn);
		click(Edit_link);
		try {
			click(ModifyImage);
			extentTest.log(LogStatus.PASS, "Desired AgreementType is  present in the list.. Verificatin Successfull");
		} catch (Exception e) {
			extentTest.log(LogStatus.FAIL, "ERROR: Exception occured " + "while Selecting AgreementType list item: "
					+ "Verification Failed" + e);
		}
		enter(AgreementTitle_Txtbx, ActTypes);
		DatePicker_CurrentDate(SignedDate, SignedDate_tbody);
		click(Close_button);
	}

	public void Validate_EventType(By EventDrpdwnValue) {
		click(GlobalInteractionNew_drpdwn);
		click(Event_Link);
		click(Template_drpdwn);
		click(DefaultTemplate_link);
		click(Go_Link);
		enter(EventName_txtbx, ActTypes);
		DatePicker_CurrentDate(StartDate, SignedDate_tbody);
		DatePicker_CurrentDate(EndDate, SignedDate_tbody);
		click(EventType_Drpdwn);

		try {
			click(EventDrpdwnValue);
			extentTest.log(LogStatus.PASS, "Desired Event Type is  present in the list.. Verificatin Successfull");
		} catch (Exception e) {
			extentTest.log(LogStatus.FAIL,
					"ERROR: Exception occured " + "while Selecting Event Type list item: " + "Verification Failed" + e);
		}

		click(EBrands_Drpdwn);
		click(Brand_chkbx);
		click(AddParticipentAndSave);
		
		waitForElementtoBeVisible(Search_Image);
		click(Search_Image);
		click(SelectdBrand_drpdwn);
		click(SelectedBrand_drpdwnVal1);
		
		click(Search_link);
		click(Radio_buttn);//
		click(AddProfile_buttn);
		selectByIndex(EventRole_List, 1);
		click(AddRole_buttn);
		click(Save);
		click(ProfileTab_link);
		waitForElementtoBeVisible(MoreLink);

	}

	public void Validate_Languages(By Language_drpdwnValue) {
		click(ProfileTab_link);
		enter(prof_searchBox1, "Ted Thaell, John");
		click(search_btn);
		wait(3);
		waitForElementtoBeClickable(FirstProfile_link);
		click(FirstProfile_link);
		click(Biography_link);
		waitForElementtoBeClickable(Actions_drpdwn);
		click(Actions_drpdwn);
		click(Edit_link);
		waitForElementtoBeVisible(Languages_drpdwn);
		click(Languages_drpdwn);
		try {
			click(Language_drpdwnValue);
			extentTest.log(LogStatus.PASS, "Desired Language Type is  present in the list.. Verificatin Successfull");
		} catch (Exception e) {
			extentTest.log(LogStatus.FAIL, "ERROR: Exception occured " + "while Selecting Laguage from the list item: "
					+ "Verification Failed" + e);
		}
		click(Save);
		click(ProfileTab_link);
	}

	public void Validate_Specialities(By Specialities_drpdwnValue) {
		click(ProfileTab_link);
		
		enter(prof_searchBox1, "Ted Thaell, John");
		click(search_btn);
		wait(3);
		
		waitForElementtoBeClickable(FirstProfile_link);
		click(FirstProfile_link);
		click(Biography_link);
		waitForElementtoBeClickable(Actions_drpdwn);
		click(Actions_drpdwn);
		click(Edit_link);
		click(Specialities_drpdwn);
		try {
			click(Specialities_drpdwnValue);
			extentTest.log(LogStatus.PASS,
					"Desired Specialities Type is  present in the list.. Verificatin Successfull");
		} catch (Exception e) {
			extentTest.log(LogStatus.FAIL, "ERROR: Exception occured "
					+ "while Selecting Specialities from the list item: " + "Verification Failed" + e);
		}
		click(Save);
		click(ProfileTab_link);
	}

	public void Validate_PaymentTypes(String SearchText, By PaymentDrpdwnvalue) {
		click(Event_Tab);
		enter(SearchEvent_txtbox, SearchText);
		click(Search_buttn);
		click(EventName_link);
		waitForElementtoBeClickable(Actions_drpdwn);
		click(Actions_drpdwn);
		click(Edit_link);
		click(Payment_Add_Button);
		click(PaymentType_Drpdwn);
		try {
			click(PaymentDrpdwnvalue);
			extentTest.log(LogStatus.PASS, "Desired PaymentType is  present in the list.. Verificatin Successfull");
		} catch (Exception e) {
			extentTest.log(LogStatus.FAIL, "ERROR: Exception occured "
					+ "while Selecting PaymentTypes from the list item: " + "Verification Failed" + e);
		}

		click(Close_button);

	}

	public void Validate_Topics(String SearchText, By TopicsDrpdwnvalue) {
		click(Event_Tab);
		enter(SearchEvent_txtbox, SearchText);
		click(Search_buttn);
		click(EventName_link);
		waitForElementtoBeClickable(Actions_drpdwn);
		click(Actions_drpdwn);
		click(Edit_link);
		click(Topics_Add_Button);
		click(FilterMainTopic_Drpdwn);
		try {
			click(TopicsDrpdwnvalue);
			extentTest.log(LogStatus.PASS, "Desired PaymentType is  present in the list.. Verificatin Successfull");
		} catch (Exception e) {
			extentTest.log(LogStatus.FAIL, "ERROR: Exception occured "
					+ "while Selecting PaymentTypes from the list item: " + "Verification Failed" + e);
		}

		click(Close_button);

	}

	public void Validate_ActivityType(By ActivityDrpdwnValue)

	{
		mouseHoverAndClcik(MoreLink, UserActivityLink);
		click(NewBtn);
		click(ActTypecheck);
		try {

			click(ActivityDrpdwnValue);

			extentTest.log(LogStatus.PASS, "Desired Activity Type Present in the list. Veification successfull..");
		} catch (Exception e) {

			extentTest.log(LogStatus.FAIL, "ERROR: Exception occured " + " while clicking Activi list item: " + e);
		}
		click(CloseAddGoalPopUp);
	}
}
