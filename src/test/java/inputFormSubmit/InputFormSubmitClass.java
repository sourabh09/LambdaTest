package inputFormSubmit;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InputFormSubmitClass {

	private RemoteWebDriver driver;
	private String Status = "passed";

	@BeforeMethod
	public void setup(Method m, ITestContext ctx) throws MalformedURLException {
		String username = System.getenv("LT_USERNAME") == null ? "sourabhm" : System.getenv("LT_USERNAME");
		String authkey = System.getenv("LT_ACCESS_KEY") == null ? "LT_jeR2e7ZJo1Vyl2oxJUTr5H006oZWzpQjW9TAUxPQ4xA67Or"
				: System.getenv("LT_ACCESS_KEY");
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
	public void TestScenario3() throws InterruptedException {

		driver.get("https://www.lambdatest.com/selenium-playground/");
		driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));

		WebElement InputFormLink = driver
				.findElement(By.xpath("//a[contains(@href, 'input-form-demo')]"));
		InputFormLink.click();

		WebElement submit = driver.findElement(By.xpath("//div[@class='text-right mt-20']/button"));
		submit.click();

		WebElement name = driver.findElement(By.id("name"));
		String Expected_validation = name.getAttribute("validationMessage");
		String Actual_validation = "Please fill out this field.";
		Assert.assertEquals(Actual_validation, Expected_validation);

		if (Expected_validation.equals(Actual_validation)) {
			System.out.println("Validation is properly appear.");
		} else {
			System.out.println("Validation is not properly appear.");
		}

		name.sendKeys("TestName");

		WebElement email = driver.findElement(By.id("inputEmail4"));
		email.sendKeys("Test123@gmail.com");

		WebElement password = driver.findElement(By.id("inputPassword4"));
		password.sendKeys("Test@1234");

		WebElement company = driver.findElement(By.id("company"));
		company.sendKeys("TestCompany");

		WebElement website = driver.findElement(By.id("websitename"));
		website.sendKeys("Testdomain.com");

		WebElement country = driver.findElement(By.name("country"));
		Select select = new Select(country);
		select.selectByVisibleText("United States");

		WebElement city = driver.findElement(By.id("inputCity"));
		city.sendKeys("TestCity");

		WebElement address1 = driver.findElement(By.id("inputAddress1"));
		address1.sendKeys("TestAddress1");

		WebElement address2 = driver.findElement(By.id("inputAddress2"));
		address2.sendKeys("TestAddress2");

		WebElement state = driver.findElement(By.id("inputState"));
		state.sendKeys("TestState");

		WebElement zipcode = driver.findElement(By.id("inputZip"));
		zipcode.sendKeys("360002");

		submit.click();

		WebElement successmessage = driver.findElement(
				By.xpath("//p[@class='success-msg hidden']"));
		String Actualmessage = successmessage.getText();
		String Expectedmessage = "Thanks for contacting us, we will get back to you shortly.";

		if (Actualmessage.equals(Expectedmessage)) {
			System.out.println("Success message is properly appear.");
		} else {
			System.out.println("Success message is not properly appear.");
		}
	}

	@AfterMethod
	public void tearDown() {
		driver.executeScript("lambda-status=" + Status);
		driver.quit();
	}
}

