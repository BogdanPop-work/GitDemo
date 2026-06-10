package BogdanPopLearning.StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import BogdanPopLearning.TestComponents.BaseTest;
import SeleniumFrameworkDesign.pageObjects.CartPage;
import SeleniumFrameworkDesign.pageObjects.CheckOutPage;
import SeleniumFrameworkDesign.pageObjects.ConfirmationPage;
import SeleniumFrameworkDesign.pageObjects.LandingPage;
import SeleniumFrameworkDesign.pageObjects.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);
	}

	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}

	@And("^Checkout (.+) and submit the Order$")
	public void checkout_submit_order(String productName) {
		CartPage cartPage = productCatalogue.goToCartPage();

		// Checking if the product is the correct one
		Boolean presenceOfProduct = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(presenceOfProduct);

		// Check out by selecting the correct country and submitting the order
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.selectCountry("india");
		confirmationPage = checkOutPage.submitOrder();
	}
	


	@Then("I verify if {string} is displayed")
	public void message_displayed_confirmationPage(String string)
	{
		String confirmationMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase(string));
		driver.quit();
	}
	
	@Then("{string} message is displayed")
	public void some_message_is_displayed(String string) {
		Assert.assertEquals(string, landingPage.getErrorMessage());
		driver.quit();
	}

}
