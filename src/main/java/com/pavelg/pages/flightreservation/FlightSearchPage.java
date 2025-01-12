package com.pavelg.pages.flightreservation;

import com.pavelg.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightSearchPage extends BasePage {

    @FindBy(id = "passengers")
    private WebElement passengersField;

    @FindBy(id = "search-flights")
    private WebElement searchFlightsButton;

    public FlightSearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.passengersField));
        return this.passengersField.isDisplayed();
    }

    public void selectPassengers(String noOfPassengers){
        Select passengers = new Select(this.passengersField);
        passengers.selectByValue(noOfPassengers);
    }

    public void searchFlights(){
        this.searchFlightsButton.click();
    }
}
