package SeleniumFrameworkDesign.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BogdanPopLearning.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

	@FindBy(css = "[placeholder='Select Country']")
	private WebElement country;

	@FindBy(css = ".action__submit")
	private WebElement submit;

	@FindBy(css = ".ta-item:last-of-type")
	private WebElement selectCountry;

	WebDriver driver;

	private By results = By.cssSelector(".ta-results");

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();
	}

	public ConfirmationPage submitOrder() {
		submit.click();
		return new ConfirmationPage(driver);
	}

}
