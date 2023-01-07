package utilis
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException




class WebKeywords {
	Random random = new Random()
	String chars = "abcdefghijklmnopqrstuvwxyz0123456789"


	@Keyword
	def getcellinfo(String info) {

		TestObject div = new TestObject("info")
		div.addProperty("Xpath", ConditionType.EQUALS,"//div[@class='col-xs-8' and contains(.,'${info}')]")

		//$x('//div[@class="col-xs-8" and contains(.,"Medicare")]')
	}


	@Keyword
	def getCheckboxByLabel(String label) {
		TestObject checkbox = new TestObject("LabelForCheckbox")
		checkbox.addProperty("Xpath", ConditionType.EQUALS,"//label[text()[contains(.,'${label}')]]")
		checkbox
	}


	@Keyword
	def getRadiobuttonByLabel(String label) {
		TestObject radioButton = new TestObject("LabelForRadioButton")
		radioButton.addProperty("Xpath", ConditionType.EQUALS,"//label[text()[contains(.,'${label}')]]")
		radioButton
	}



	@Keyword
	def getrandomString(int length) {

		StringBuilder br = new StringBuilder();

		for (int i=0; i < length ; i++ ) {
			br.append(chars.charAt(random.nextInt(chars.length())));
		}
		br.toString();
	}


	@Keyword
	def selectDay() {
		WebElement ele = WebUiCommonHelper.findWebElement(findTestObject('Object Repository/BookingPage/button_calender'),30);
		ele.click()
		//WebUI.click(findTestObject('Object Repository/BookingPage/button_calender'))
		WebElement ele2=WebUiCommonHelper.findWebElement(findTestObject('Object Repository/BookingPage/link_nextmonth'), 30)
		ele2.click()
		//WebUI.cllck(findTestObject('Object Repository/BookingPage/link_nextmonth'))
		List<WebElement> days=WebUiCommonHelper.findWebElements(findTestObject('Object Repository/BookingPage/link_day') , 30)
		int randomday = random.nextInt(days.size())
		days.get(randomday).click()
	}





	@Keyword
	def selectDropdown(TestObject testobject, String subText) {
		WebElement ele=	WebUiCommonHelper.findWebElement(testobject, 30)

		Select dropdownlist = new Select(ele)

		for(WebElement option : dropdownlist.getOptions()){

			if(option.getText().toLowerCase().contains(subText.toLowerCase())) {
				dropdownlist.selectByVisibleText(option.getText())
				break
			}
		}
	}


	@Keyword
	def userisloggin() {

		WebElement LogoutElement =WebUiCommonHelper.findWebElement(findTestObject('Object Repository/Side_Menu/a_Logout'), 30)
		LogoutElement.isDisplayed()
		//WebUI.verifyElementVisible(findTestObject('Object Repository/Side_Menu/a_Logout'))
	}


	/**
	 * Refresh browser
	 */
	@Keyword
	def refreshBrowser() {
		KeywordUtil.logInfo("Refreshing")
		WebDriver webDriver = DriverFactory.getWebDriver()
		webDriver.navigate().refresh()
		KeywordUtil.markPassed("Refresh successfully")
	}

	/**
	 * Click element
	 * @param to Katalon test object
	 */
	@Keyword
	def clickElement(TestObject to) {
		try {
			WebElement element = WebUiBuiltInKeywords.findWebElement(to);
			KeywordUtil.logInfo("Clicking element")
			element.click()
			KeywordUtil.markPassed("Element has been clicked")
		} catch (WebElementNotFoundException e) {
			KeywordUtil.markFailed("Element not found")
		} catch (Exception e) {
			KeywordUtil.markFailed("Fail to click on element")
		}
	}

	/**
	 * Get all rows of HTML table
	 * @param table Katalon test object represent for HTML table
	 * @param outerTagName outer tag name of TR tag, usually is TBODY
	 * @return All rows inside HTML table
	 */
	@Keyword
	def List<WebElement> getHtmlTableRows(TestObject table, String outerTagName) {
		WebElement mailList = WebUiBuiltInKeywords.findWebElement(table)
		List<WebElement> selectedRows = mailList.findElements(By.xpath("./" + outerTagName + "/tr"))
		return selectedRows
	}
}