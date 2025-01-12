package com.pavelg.tests.flightreservation;

import com.pavelg.pages.flightreservation.*;
import com.pavelg.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTests extends BaseTest {

    private String numberOfPassengers;
    private String expectedPrice;

    @BeforeTest
    @Parameters({"numberOfPassengers","expectedPrice"})
    public void setUp(String numberOfPassengers, String expectedPrice){
        this.numberOfPassengers = numberOfPassengers;
        this.expectedPrice = expectedPrice;
    }

    @Test
    public void userRegistrationTest(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html#");
        Assert.assertTrue(registrationPage.isAt());

        registrationPage.enterUserDetails("Selenuim", "Selen'evich");
        registrationPage.enterUserCredentials("selenium@docker.com", "qwedsaq");
        registrationPage.enterUserAddress("Main 15", "The Best", "1234567");
        registrationPage.register();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest(){
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        registrationConfirmationPage.goToFlightsSearch();
    }
    
    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightSearchTest(){
        FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAt());
        flightSearchPage.selectPassengers(numberOfPassengers);
        flightSearchPage.searchFlights();
    }

    @Test(dependsOnMethods = "flightSearchTest")
    public void flightSelectionTest(){
        FlightsSelectionPage flighstSelectionPage = new FlightsSelectionPage(driver);
        Assert.assertTrue(flighstSelectionPage.isAt());
        flighstSelectionPage.selectFlights();
        flighstSelectionPage.confirmFlights();
    }

    @Test(dependsOnMethods = "flightSelectionTest")
    public void flightReservationConfirmationTest(){
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        Assert.assertEquals(flightConfirmationPage.getPrice(), expectedPrice);
    }

}
