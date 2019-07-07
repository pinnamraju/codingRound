package codingRound.sanity;
import java.util.logging.Logger;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest extends DriverHandler {
	SignInTest() {
		super();
		//For 120 seconds if element is not visible, timeout exception will appear
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 120);
		PageFactory.initElements(factory, this);
		LogHandle.logger = Logger.getLogger(SignInTest.class.getName());
	} 

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
		LogHandle.logger.info("SignInTest initiated");
        driver.get("https://www.cleartrip.com/");
        isloadComplete(driver, 2000);

        yourTripLink.click();
        isloadComplete(driver, 1000);
        signInOption.click();
        isloadComplete(driver, 2000);
        driver.switchTo().frame("modal_window");
        signInButton.click();
        isloadComplete(driver, 2000);

        String errorText = signInErrors.getText();
        Assert.assertTrue(errorText.contains("There were errors in your submission"));
        LogHandle.logger.info("Sign In Checks validated.....Result: Pass");
        driver.quit();
    }
    
    //Locators
    @FindBy(linkText = "Your trips")
    private WebElement yourTripLink;
	
	@FindBy(linkText = "SignIn")
    private WebElement signInOption;
	
	@FindBy(css = "#SignInButton")
    private WebElement signInButton;
	
	@FindBy(id = "errors1")
    private WebElement signInErrors;
}
