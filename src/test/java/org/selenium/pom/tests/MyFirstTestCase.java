package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyFirstTestCase extends BaseTest {

    // @Test
    public void guestCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {
        String searchProduct = "Blue";

        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);
//        BillingAddress billingAddress = new BillingAddress().
//                setFirstName("demo").
//                setLastName("user").
//                setAddress("San Francisco").
//                setCity("San Francisco").
//                setPosteCode("94188").
//                setEmail("askomdch@gmail.com");
        //BillingAddress billingAddress = new BillingAddress("demo","user","San Francisco","San Francisco","94188","askomdch@gmail.com");

        StorePage storePage = new HomePage(getDriver()).
                load().
                getMyHeader().navigateToStoreUsingMenu().
                search(searchProduct);
        Assert.assertEquals(storePage.getTitle(),"Search results: “Blue”");

        storePage.getProductThumbnail().clickAddToCartBtn(product.getName());

        CartPage cartPage = storePage.getProductThumbnail().clickViewCart();
        cartPage.isLoaded();
        Assert.assertEquals(cartPage.getProductName(),product.getName());

        CheckoutPage checkoutPage= cartPage.
                checkout().
                setBillingAddress(billingAddress).
//                enterFirstName("demo").
//                enterLastName("user").
//                enterAddress("San Francisco").
//                enterCity("San Francisco").
//                enterPosteCode("94188").
//                enterEmail("askomdch@gmail.com").
                selectDirectBankTransfer().
                clickPlaceOrder();
        Assert.assertEquals(checkoutPage.getNotice(),"Thank you. Your order has been received.");

    }
    //@Test
    public void loginCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {
        String searchProduct = "Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);
        User user = new User(ConfigLoader.getInstance().getUsername(), ConfigLoader.getInstance().getPassword());

        StorePage storePage = new HomePage(getDriver()).
                load().getMyHeader().
                navigateToStoreUsingMenu().
                search(searchProduct);
        Assert.assertEquals(storePage.getTitle(),"Search results: “Blue”");;

        storePage.getProductThumbnail().clickAddToCartBtn(product.getName());

        CartPage cartPage = storePage.getProductThumbnail().clickViewCart();
        Assert.assertEquals(cartPage.getProductName(),product.getName());

        CheckoutPage checkoutPage= cartPage.checkout();
        checkoutPage.clickHereTologinlink();


        checkoutPage.login(user).
                setBillingAddress(billingAddress).
                selectDirectBankTransfer().
                clickPlaceOrder();

        Assert.assertEquals(checkoutPage.getNotice(),"Thank you. Your order has been received.");
    }
}
