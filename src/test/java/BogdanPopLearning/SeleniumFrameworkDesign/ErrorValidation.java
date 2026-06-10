package BogdanPopLearning.SeleniumFrameworkDesign;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import BogdanPopLearning.TestComponents.BaseTest;
import BogdanPopLearning.TestComponents.Retry;
import SeleniumFrameworkDesign.pageObjects.CartPage;
import SeleniumFrameworkDesign.pageObjects.ProductCatalogue;

public class ErrorValidation extends BaseTest {

	@Test(groups = { "ErrorHandling" },retryAnalyzer=Retry.class)
	public void incorrectLoginValidation() throws IOException, InterruptedException {

		landingPage.loginApplication("bogdan2.pop@gmail.com", "Parola1232");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

	@Test
	public void productErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("bogdan.pop@gmail.com", "Parola123");
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean presenceOfProduct = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(presenceOfProduct);

	}

}
