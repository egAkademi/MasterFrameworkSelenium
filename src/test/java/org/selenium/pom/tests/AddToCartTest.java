package org.selenium.pom.tests;

import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.dataproviders.MyDataProvider;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToCartTest extends BaseTest {

    @Link("https://confluence.turksat.com.tr/display/KODS/6-+CRM")
    @Link(name = "allure", type = "mylink")
    @TmsLink("12345")
    @Issue("asdasdas")
    @Test(description = "add To Cart From Store Page")
    public void addToCartFromStorePage() throws IOException {
        Product product = new Product(1215);
        CartPage cartPage = new StorePage(getDriver()).load().
                getProductThumbnail().clickAddToCartBtn(product.getName()).
                clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }

    @Test(description = "add To Cart Featured Products",dataProvider = "getFeaturedProducts", dataProviderClass = MyDataProvider.class)
    public void addToCartFeaturedProducts(Product product){
        CartPage cartPage = new HomePage(getDriver()).load().
                getProductThumbnail().
                clickAddToCartBtn(product.getName()).
                clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }
}
