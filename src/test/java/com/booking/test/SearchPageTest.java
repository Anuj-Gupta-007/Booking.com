package com.booking.test;

import java.io.IOException;
import com.aventstack.chaintest.plugins.ChainTestListener;
import com.booking.base.TestBase;
import com.booking.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class SearchPageTest extends TestBase {

    private SearchPage searchPage;

    @BeforeClass
    public void initialize() throws IOException
    {
        preCondition();
        searchPage = new SearchPage(driver);
    }

    @Test (enabled = true)
    void searchFight() throws InterruptedException {
        String departureLocation = properties.getProperty("DepartureLocation");
        String destinationLocation = properties.getProperty("DestinationLocation");
        ChainTestListener.log("<b>Click on OneWay radiobutton </b>");
        searchPage.oneWayRadioBtn().click();
        String departureDropdownText = searchPage.departureDropdownText().getText();
        if(departureDropdownText.equalsIgnoreCase("Where from?")){
            searchPage.departureField().click();
            searchPage.locationDropdown().sendKeys(departureLocation);
            if(searchPage.isLocationPresent(departureLocation)){
                searchPage.selectDepartureLocationCheckbox(departureLocation).click();
            }
        }
        else {
        if(!departureDropdownText.contains(departureLocation)){
            searchPage.departureField().click();
            searchPage.removeLocation().click();
            searchPage.locationDropdown().sendKeys(departureLocation);
            if(searchPage.isLocationPresent(departureLocation)){
                searchPage.selectDepartureLocationCheckbox(departureLocation).click();
            }
        }
        }
        ChainTestListener.log("<b>Departure Location selected: </b>"+ departureLocation);
        //Select Destination Location
        String destinationDropdownText = searchPage.destinationDropdownText().getText();
        if(destinationDropdownText.equalsIgnoreCase("Where to?")){
            searchPage.destinationField().click();
            searchPage.locationDropdown().sendKeys(destinationLocation);
            //searchPage.flightSuggestions();
            if(searchPage.isLocationPresent(destinationLocation)){
                searchPage.selectDepartureLocationCheckbox(destinationLocation).click();
            }
        }

        ChainTestListener.log("<b> Destination Location selected: </b>"+ destinationLocation);


        //Search for desired Month
        searchPage.departureDateField().click();
        while (true) {
            boolean monthFound = false;
            for (int i = 0; i < searchPage.calendarMonths().size(); i++) {
                String month = searchPage.calendarMonths().get(i).getText().replaceAll("\\d", "").trim();
                String expectedMonth = properties.getProperty("DepartureDate").replaceAll("\\d", "").trim();

                if (expectedMonth.equalsIgnoreCase(month)) {
                    monthFound = true;
                    break;
                }
            }
            if (monthFound) {
                break;
            } else {
                searchPage.nextIconInCalendar().click();
            }
        }


        // Select Desired Date
        for(int i = 0 ; i< searchPage.fetchCalendarDates().size() ; i++){
            String calendarDate = searchPage.fetchCalendarDates().get(i).getDomAttribute("aria-label");
            String month = calendarDate.replaceAll("\\d", "").trim();
            if(calendarDate.equalsIgnoreCase(properties.getProperty("DepartureDate"))){
                searchPage.selectDepartureDate(properties.getProperty("DepartureDate")).click();
                break;
            }
        }
        ChainTestListener.log("<b> Departure Location selected: </b>"+ properties.getProperty("DepartureDate"));

        //Add AdultPassengers
        searchPage.occupancyField().click();
        String adultPassengers = searchPage.addAdultPassenger(properties.getProperty("AdultPassengers"));
        Assert.assertEquals(adultPassengers,properties.getProperty("AdultPassengers"),"Adult passengers count doesnot match");
        String childrenPassengers = searchPage.addChildrenPassenger(properties.getProperty("ChildrenPassengers"));
        Assert.assertEquals(childrenPassengers,properties.getProperty("ChildrenPassengers"),"Children passengers count doesnot match");
        searchPage.doneButton().click();

        ChainTestListener.log("<b>Adult Passengers: </b>" + adultPassengers);
        ChainTestListener.log("<b>Children Passengers: </b>" + childrenPassengers);

        //Click on Search Button
        searchPage.searchButton().click();
        ChainTestListener.log("<b>Click on Search button</b>");
        searchPage.searchResultPage();
        ChainTestListener.log("<b>Checking for the best flight.....</b>");
        String cheapestFlightDetails = searchPage.searchFlightbasedOnLowestPrice();
        ChainTestListener.log(cheapestFlightDetails);
    }

    @AfterClass
    public void wrapUp() {
        driver.quit();
    }
    }






