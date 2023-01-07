import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys






WebUI.verifyElementVisible(findTestObject('Object Repository/BookingPage/h2_Make Appointment'))

String facility, program , visitDate

for (def row=1 ;row <= findTestData('Booking').getRowNumbers(); row++) {
	facility= findTestData('Booking').getValue("Facility", row)
	program= findTestData('Booking').getValue("Program", row)
		
	CustomKeywords.'utilis.WebKeywords.selectDropdown'(findTestObject('Object Repository/BookingPage/combo_facility') ,facility)
	WebUI.click(CustomKeywords.'utilis.WebKeywords.getCheckboxByLabel'("Apply for"))
	WebUI.click(CustomKeywords.'utilis.WebKeywords.getRadiobuttonByLabel'(program))
	CustomKeywords.'utilis.WebKeywords.selectDay'()
	WebUI.click(findTestObject('Object Repository/BookingPage/textarea_Comment_comment'))
	visitDate = WebUI.getAttribute(findTestObject('Object Repository/BookingPage/input_Visit Date') , 'value')
	println("Facility ${facility}, Program ${program}")
	WebUI.clearText(findTestObject('Object Repository/BookingPage/textarea_Comment_comment'))
	String Textarea = CustomKeywords.'utilis.WebKeywords.getrandomString'(10)
	WebUI.sendKeys(findTestObject('Object Repository/BookingPage/textarea_Comment_comment'), Textarea)	
	
	
	
	WebUI.click(findTestObject('Object Repository/BookingPage/button_Book Appointment'))
	WebUI.callTestCase( findTestCase('Test Cases/Confirm'),[('facility'):facility,
		('program'):program,
		('visitDate'):visitDate,
		('Textarea'):Textarea])
}
























