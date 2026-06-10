package BogdanPopLearning.SeleniumFrameworkDesign;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import BogdanPopLearning.TestComponents.BaseTest;
import SeleniumFrameworkDesign.pageObjects.CartPage;
import SeleniumFrameworkDesign.pageObjects.CheckOutPage;
import SeleniumFrameworkDesign.pageObjects.ConfirmationPage;
import SeleniumFrameworkDesign.pageObjects.OrderPage;
import SeleniumFrameworkDesign.pageObjects.ProductCatalogue;

public class StandAloneTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		// Adding product to cart will redirect us to cart page
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();

		// Checking if the product is the correct one
		Boolean presenceOfProduct = cartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(presenceOfProduct);

		// Check out by selecting the correct country and submitting the order
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();

		// Check confirmation message
		String confirmationMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));

	}
	// VErify ZARA COAT 3 is displaying in order page

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		// "ZARA COAT 3"
		ProductCatalogue productCatalogue = landingPage.loginApplication("bogdan.pop@gmail.com", "Parola123");
		OrderPage ordersPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));

	}

	// Extent Reports --

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\BogdanPopLearning\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

//	HashMap<String,String> map = new HashMap();
//	map.put("email", "bogdan.pop@gmail.com");
//	map.put("password", "Parola123");
//	map.put("productName", "ZARA COAT 3");
//	
//	HashMap<String,String> map1 = new HashMap();
//	map1.put("email", "anca.nicoleta@gmail.com");
//	map1.put("password", "Parola1!!!");
//	map1.put("productName", "ADIDAS ORIGINAL");
}
