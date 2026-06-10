package SeleniumFrameworkDesign.pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import BogdanPopLearning.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement passwordElement;

	@FindBy(id = "login")
	WebElement submit;

	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

	public ProductCatalogue loginApplication(String email, String password) {
	    userEmail.sendKeys(email);
	    passwordElement.sendKeys(password);

	    waitForOverlayToDisappear();
	    clickElement(submit);

	    return new ProductCatalogue(driver);
	}



	public String getErrorMessage() {

		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();

	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
}
