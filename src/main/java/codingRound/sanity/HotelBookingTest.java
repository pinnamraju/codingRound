package codingRound.sanity;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HotelBookingTest extends DriverHandler {
	HotelBookingTest() {
		super();
		LogHandle.logger = Logger.getLogger(HotelBookingTest.class.getName());
		//For 120 seconds if element is not visible, timeout exception will appear
    	AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 120);
    	PageFactory.initElements(factory, this);
    	
    } 

    @Test
    public void shouldBeAbleToSearchForHotels() {
		LogHandle.logger.info("HotelBookingTest initiated");
        driver.get("https://www.cleartrip.com/");
        isloadComplete(driver, 2000);
        hotelLink.click();
        
        localityTextBox.sendKeys("Indiranagar, Bangalore");
        isloadComplete(driver, 3000);
        
        List<WebElement> whereOptions = localityName.findElements(By.tagName("li"));
        whereOptions.get(1).findElements(By.tagName("a")).get(0).click();
        isloadComplete(driver, 1000);
        currentDate.click();
        isloadComplete(driver, 1000);
        nextDate.click();
        isloadComplete(driver, 1000);
        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
        isloadComplete(driver, 1500);
        searchButton.click();
        isloadComplete(driver, 1500);
        //Added assertion as exit criteria of test
        Assert.assertTrue(searchCompleteNotification.getText().equalsIgnoreCase("Indiranagar"));
		LogHandle.logger.info("Holtel Search results obtained..Result: Pass");
        driver.quit();
    }
    
    //Locators
    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;
    @FindBy(xpath = "//*[@id=\"ui-id-1\"]")
    private WebElement localityName;
    
    
    @FindBy(css = "#ui-datepicker-div > div.monthBlock.first > table > tbody > tr:nth-child(1) > td.weekEnd.ui-datepicker-days-cell-over.selected > a")
    private WebElement currentDate;
    
    @FindBy(css = ".ui-state-active")
    private WebElement nextDate;
    
    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;
    
    @FindBy(css = "#area > section > div > div.listViewResults.theiaStickySidebar > div.resultsCont > section > div.row.searchWidgetRow.propertySection > div > div.colZero.col18 > strong")
    private WebElement searchCompleteNotification;
}
