package com.pavelg.tests.vendorportal;

import com.pavelg.pages.vendorportal.DashboardPage;
import com.pavelg.pages.vendorportal.LoginPage;
import com.pavelg.tests.BaseTest;
import com.pavelg.tests.utils.Config;
import com.pavelg.tests.utils.Constants;
import com.pavelg.tests.utils.JsonUtil;
import com.pavelg.tests.vendorportal.model.VendorPortalTestData;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VendorPortalTest extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setPageObjects(String testDataPath){
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
    }

    @Test
    public void loginTest(){
        loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testData.username(), testData.password());
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest(){
       Assert.assertTrue(dashboardPage.isAt());
       Assert.assertEquals(dashboardPage.getMonthlyEarnings(), testData.monthlyEarning());
       Assert.assertEquals(dashboardPage.getAnnualEarnings(), testData.annualEarning());
       Assert.assertEquals(dashboardPage.getProfitMargin(), testData.profitMargin());
       Assert.assertEquals(dashboardPage.getAvailableInventory(), testData.availableInventory());

       //oder history search
        dashboardPage.searchOrderHistoryBy(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultsCount(), testData.searchResultsCount());
    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest(){
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }

}
