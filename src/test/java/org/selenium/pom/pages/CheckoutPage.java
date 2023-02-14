package org.selenium.pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.User;

public class CheckoutPage extends BasePage {
    private final By firstnameTxt = By.cssSelector("#billing_first_name");
    private final By lastnameTxt = By.cssSelector("#billing_last_name");
    private final By addressTxt = By.cssSelector("#billing_address_1");
    private final By cityTxt = By.cssSelector("#billing_city");
    private final By posteCodeTxt = By.id("billing_postcode");
    private final By emailTxt = By.id("billing_email");
    private final By placeOrderBtn = By.id("place_order");
    private final By succesNotice = By.cssSelector(".woocommerce-notice");
    private final By overlay = By.cssSelector(".blockUI.blockOverlay");

    private final By clickHereToLoginLink=By.className("showlogin");
    private final By usernameFld=By.id("username");
    private final By passwordfld=By.id("password");
    private final By loginBtn=By.name("login");

    private final By countryDropDown = By.id("billing_country");
    private final By stateDropDown = By.id("billing_state");
    private final By directBankTransferRadiobtn=By.id("payment_method_bacs");

    private final By alternateStateDropDown = By.id("select2-billing_state-container");
    private final By alternateCountryDropDown = By.id("select2-billing_country-container");

    private final By productName = By.cssSelector("td[class='product-name']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage load(){
        load("/checkout/");
        return this;
    }
    @Step
    public CheckoutPage enterFirstName(String firstName){
        //WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(firstnameTxt));
        WebElement e=waitForElementToBeVisible(firstnameTxt);
        e.clear();
        e.sendKeys(firstName);
        return this;
    }
    @Step
    public CheckoutPage enterLastName(String lastName){
        WebElement e=waitForElementToBeVisible(lastnameTxt);
        e.clear();
        e.sendKeys(lastName);
        return this;
    }
    @Step
    public CheckoutPage selectCountry(String countryName){
//        Select select = new Select(driver.findElement(countryDropDown));
//        select.selectByVisibleText(countryName);

        wait.until(ExpectedConditions.elementToBeClickable(alternateCountryDropDown)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[text()='" + countryName + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
        e.click();
        return this;
    }
    @Step
    public CheckoutPage selectState(String stateName){
       // Select select = new Select(driver.findElement(stateDropDown));
        //select.selectByVisibleText(stateName);

        wait.until(ExpectedConditions.elementToBeClickable(alternateStateDropDown)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[text()='" + stateName + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
        e.click();
        return this;
    }
    @Step
    public CheckoutPage enterAddress(String address){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(addressTxt));
        e.clear();
        e.sendKeys(address);
        return this;
    }
    @Step
    public CheckoutPage enterCity(String city){
        driver.findElement(cityTxt).clear();
        driver.findElement(cityTxt).sendKeys(city);
        return this;
    }
    @Step
    public CheckoutPage enterPosteCode(String posteCode){
        driver.findElement(posteCodeTxt).clear();
        driver.findElement(posteCodeTxt).sendKeys(posteCode);
        return this;
    }
    @Step
    public CheckoutPage enterEmail(String email){
        driver.findElement(emailTxt).clear();
        driver.findElement(emailTxt).sendKeys(email);
        return this;
    }
    @Step
    public CheckoutPage setBillingAddress(BillingAddress billingAddress){
        return  enterFirstName(billingAddress.getFirstName()).
                enterLastName(billingAddress.getLastName()).
                selectCountry(billingAddress.getCountry()).
                selectState(billingAddress.getState()).
                enterAddress(billingAddress.getAddress()).
                enterCity(billingAddress.getCity()).
                enterPosteCode(billingAddress.getPosteCode()).
                enterEmail(billingAddress.getEmail());

    }
    @Step
    public CheckoutPage clickPlaceOrder() throws InterruptedException {
        waitforOverlaysToDisappear(overlay);
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn)).click();
        //driver.findElement(placeOrderBtn).click();
        return this;
    }
    @Step
    public String getNotice(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(succesNotice)).getText();
    }
    @Step
    public CheckoutPage clickHereTologinlink(){
        driver.findElement(clickHereToLoginLink).click();
        return this;
    }
    @Step
    public CheckoutPage enterUserName(String username){
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameFld)).sendKeys(username);
        return this;
    }
    @Step
    public CheckoutPage enterPassword(String password){
        driver.findElement(passwordfld).sendKeys(password);
        return this;
    }
    @Step
    public CheckoutPage clickLoginBtn(){
        driver.findElement(loginBtn).click();
        return this;
    }
    @Step
    private CheckoutPage waitForLoginBtnToDisappear(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loginBtn));
        return this;
    }
    @Step
    public CheckoutPage login(User user){
        return enterUserName(user.getUsername()).
                enterPassword(user.getPassword()).
                clickLoginBtn().waitForLoginBtnToDisappear();
    }
    @Step
    public CheckoutPage selectDirectBankTransfer(){
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(directBankTransferRadiobtn));
        if (!e.isSelected()){
            e.click();
        }
        return this;
    }
    @Step
    public String getProductName() throws Exception {
        int i = 5;
        while(i > 0){
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
            }catch (StaleElementReferenceException e){
                System.out.println("NOT FOUND. TRYING AGAIN" + e);
            }
            Thread.sleep(5000);
            i--;
        }
        throw new Exception("Element not found");
    }
}
