package com.pavelg.pages.flightreservation;

import com.pavelg.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class RegistrationPage extends BasePage {

    @FindBy(id="firstName")
    private WebElement firstNameField;

    @FindBy(id="lastName")
    private WebElement lastNameField;

    @FindBy(id="email")
    private WebElement emailField;

    @FindBy(id="password")
    private WebElement passwordField;

    @FindBy(name="street")
    private WebElement streetField;

    @FindBy(name="city")
    private WebElement cityField;

    @FindBy(name="zip")
    private WebElement zipField;

    @FindBy(id="register-btn")
    private WebElement registerButton;

    public RegistrationPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.firstNameField));
        return this.firstNameField.isDisplayed();
    }

    public void goTo(String url){
        this.driver.get(url);
    }

    public void enterUserDetails(String firstName, String lastName){
        this.firstNameField.sendKeys(firstName);
        this.lastNameField.sendKeys(lastName);

    }

    public void enterUserCredentials(String email, String password){
        this.emailField.sendKeys(email);
        this.passwordField.sendKeys(password);
    }

    public void enterUserAddress(String street, String city, String zip){
        this.streetField.sendKeys(street);
        this.cityField.sendKeys(city);
        this.zipField.sendKeys(zip);
    }

    public void register(){
        this.registerButton.click();
    }


}
