package codingRound.sanity;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FlightBookingTest extends DriverHandler {
	FlightBookingTest() {
		super();
		LogHandle.logger = Logger.getLogger(FlightBookingTest.class.getName());
		//For 120 seconds if element is not visible, timeout exception will appear
    	AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 120);
    	PageFactory.initElements(factory, this);
    	
    }  
    
    @Test
    public void testThatResultsAppearForAOneWayJourney() {  
    	LogHandle.logger.info("FlighBookingTest initiated");
        driver.get("https://www.cleartrip.com/");
        isloadComplete(driver, 1500);
        oneWay.click();

        fromTag.clear();
        fromTag.sendKeys("Bangalore");

        //wait for the auto complete options to appear for the origin
        isloadComplete(driver, 2500);
        List<WebElement> originOptions = origin.findElements(By.tagName("li"));
        originOptions.get(0).click();
        isloadComplete(driver, 1500);
        toTag.clear();
        toTag.sendKeys("Delhi");

        //wait for the auto complete options to appear for the destination
        isloadComplete(driver, 2500);
        //select the first item from the destination auto complete list
        List<WebElement> destinationOptions = dest.findElements(By.tagName("li"));
        destinationOptions.get(0).click();
        isloadComplete(driver, 1500);
        currentDatePick.click();

        //all fields filled in. Now click on search
        searchButton.click();

        isloadComplete(driver, 1000);
        //verify that result appears for the provided journey search
        Assert.assertTrue(searchSummary.isDisplayed());
        LogHandle.logger.info("Flight results appear.....Result: Pass");
        //close the browser
        driver.quit();
    }    
    
    //Locators
	@FindBy(id = "OneWay")
    private WebElement oneWay;
	
	@FindBy(id = "FromTag")
    private WebElement fromTag;
	
	@FindBy(id = "ToTag")
    private WebElement toTag;
	
	@FindBy(id = "ui-id-1")
    private WebElement origin;
	
	@FindBy(id = "ui-id-2")
    private WebElement dest;
	
	@FindBy(xpath = "//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")
    private WebElement currentDatePick;
	
	@FindBy(id = "SearchBtn")
    private WebElement searchButton;
	
	@FindBy(className = "searchSummary")
    private WebElement searchSummary;
}
