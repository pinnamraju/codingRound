package codingRound.sanity;
import org.openqa.selenium.By;
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
	} 

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

        driver.get("https://www.cleartrip.com/");
        isloadComplete(driver, 2000);

        driver.findElement(By.linkText("Your trips")).click();
        isloadComplete(driver, 1000);
        driver.findElement(By.id("SignIn")).click();
        isloadComplete(driver, 2000);
        driver.switchTo().frame("modal_window");
        driver.findElement(By.cssSelector("#signInButton")).click();
        isloadComplete(driver, 2000);

        String errors1 = driver.findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        driver.quit();
    }

}
