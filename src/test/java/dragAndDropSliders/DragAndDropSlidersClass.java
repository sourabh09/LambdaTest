package dragAndDropSliders;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DragAndDropSlidersClass {

	private RemoteWebDriver driver;
	private String Status = "passed";

	@BeforeMethod
	public void setup(Method m, ITestContext ctx) throws MalformedURLException {
		String username = System.getenv("LT_USERNAME") == null ? "sourabhm" : System.getenv("LT_USERNAME");
		String authkey = System.getenv("LT_ACCESS_KEY") == null ? "LT_jeR2e7ZJo1Vyl2oxJUTr5H006oZWzpQjW9TAUxPQ4xA67Or" : System.getenv("LT_ACCESS_KEY");
		String hub = "@hub.lambdatest.com/wd/hub";

        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("dev");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("visual", true);
        ltOptions.put("video", true);
        ltOptions.put("network", true);
        ltOptions.put("build", "TestNG With Java");
        ltOptions.put("project", "Untitled");
        String[] customTags = {"Feature", "Magicleap", "Severe"};
        ltOptions.put("tags", customTags);
        ltOptions.put("console", "info");
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-testNG");
        browserOptions.setCapability("LT:Options", ltOptions);

		driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), browserOptions);
	}

	@Test
	public void TestScenario2() throws InterruptedException {

		driver.get("https://www.lambdatest.com/selenium-playground/");
		driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));

        WebElement DrageAndDroplink = driver.findElement(
				By.xpath("//a[contains(@href, 'drag-drop-range-sliders-demo')]"));
		DrageAndDroplink.click();

		WebElement slider3 = driver.findElement(By.xpath(".//*[@id='slider3']/div/input"));
		// js.executeScript("arguments[0].scrollIntoView(true);", slider3);

		Actions move = new Actions(driver);
		Actions action = (Actions) move.dragAndDropBy(slider3, 99, 0);
		action.perform();

		WebElement Expected_Range = driver.findElement(By.xpath(".//*[@id='slider3']/div/output"));
		String Expe_range = Expected_Range.getText();
		String Actual_Range = "95";

		if (Expe_range.contains(Actual_Range)) {
			System.out.println("Range is matched");
		} else {
			System.out.println("Range is not matched!");
		}
	}

	@AfterMethod
	public void tearDown() {
		driver.executeScript("lambda-status=" + Status);
		driver.quit();
	}
}

