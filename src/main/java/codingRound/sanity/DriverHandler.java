package codingRound.sanity;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.sun.jna.Platform;

public abstract class DriverHandler {
	
	protected WebDriver driver;
	
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public DriverHandler() {
		setDriverPath();
    	//Create a map to store  preferences 
    	Map<String, Object> prefs = new HashMap<String, Object>();

    	//add key and value to map as follow to switch off browser notification
    	//Pass the argument 1 to allow and 2 to block
    	prefs.put("profile.default_content_setting_values.notifications", 1);

    	//Create an instance of ChromeOptions 
    	ChromeOptions options = new ChromeOptions();

    	// set ExperimentalOption - prefs 
    	options.setExperimentalOption("prefs", prefs);

    	//Now Pass ChromeOptions instance to ChromeDriver Constructor to initialize chrome driver which will switch off this browser notification on the chrome browser
    	setDriver(new ChromeDriver(options));
    	
	}
	
	private void setDriverPath() {
        if (Platform.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (Platform.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (Platform.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }
	
	public boolean isloadComplete(WebDriver driver, int delay)
    {
    	//Wait 1 second before checking DOM state;
    	waitFor(delay);
    	boolean flag = false;
    	int repeat =0;
    	//Loop to wait until 120*delay
    	while (!flag && repeat <120) {
    		flag = ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("loaded")
                    || ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
    		repeat++;
    		//Wait 1 second
    		waitFor(delay);
    	}
    	return flag;
    }
	
	private static void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
