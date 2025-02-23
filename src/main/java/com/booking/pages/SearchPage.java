package com.booking.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage {

    WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @FindBy(xpath = "//button[@data-ui-name ='input_location_from_segment_0']")
    private WebElement departureField;

    @FindBy(xpath = "//input[contains(@class,'AutoComplete-module')]")
    private WebElement locationInputField;

    @FindBy(xpath = "//label[contains(@class,'Chip-module')]/span")
    private WebElement removeLocation;

    @FindBy(id = "#flights-searchbox_suggestions")
    private WebElement flightSuggestions;

    @FindBy(xpath = "//ul[@id='flights-searchbox_suggestions']/li/span[contains(@class,'List-module__content')]/span")
    private List<WebElement> flightSuggestionList;

    @FindBy(xpath = "//button[@data-ui-name ='input_location_to_segment_0']")
    private WebElement destinationField;

    @FindBy(xpath = "//label[contains(@for,'ONEWAY')]/span[contains(@class,'InputRadio-module__field')]")
    private WebElement oneWayRadioBtn;

    @FindBy(xpath = "//button[@placeholder='Choose departure date']")
    private WebElement departureDateField;

    @FindBy(xpath = "//button[@data-ui-name ='input_location_from_segment_0']/div[contains(@class,'ShellButton-module__inner')]//span[contains(@class,'ShellButton-module__contentInner')]/span")
    private WebElement departureDropdownText;

    @FindBy(xpath = "//button[@data-ui-name ='input_location_to_segment_0']/div[contains(@class,'ShellButton-module__inner')]//span[contains(@class,'ShellButton-module__contentInner')]/span")
    private WebElement destinationDropdownText;

    @FindBy(xpath = "//h3[@aria-live]")
    private List<WebElement> calendarMonths;

    @FindBy(xpath = "//div[@data-ui-name='calendar_body']//button[contains(@class,'next')]")
    private WebElement nextIconInCalendar;

    @FindBy(xpath = "//button[@data-ui-name='button_occupancy']")
    private WebElement occupancyField;

    @FindBy(xpath = "//button[@data-ui-name='button_occupancy_action_bar_done']")
    private WebElement doneButton;

    @FindBy(xpath = "//button[@data-ui-name='button_search_submit']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@data-testid='searchresults_list']")
    private WebElement searchResultPage;

    @FindBy(xpath = "//div[@data-testid='flight_card_price_main_price']/div/div")
    private List<WebElement> priceList;

    @FindBy(id = "TAB-CHEAPEST")
    private WebElement cheapestTab;



    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement oneWayRadioBtn() {
        return oneWayRadioBtn;
    }

    public WebElement departureDropdownText() {
        return departureDropdownText;
    }

    public WebElement doneButton() {
        return doneButton;
    }

    public WebElement searchButton() {
        return searchButton;
    }

    public WebElement destinationDropdownText() {
        return destinationDropdownText;
    }

    public WebElement removeLocation() {
        return removeLocation;
    }

    public WebElement departureField() {
        return departureField;
    }

    public WebElement nextIconInCalendar() {
        return nextIconInCalendar;
    }


//    public WebElement flightSuggestions() {
//        return flightSuggestions;
//    }

    public List<WebElement> flightSuggestionList() {
        return flightSuggestionList;
    }

    public WebElement locationDropdown() {
        return locationInputField;
    }

    public WebElement destinationField() {
        return destinationField;
    }


    public WebElement departureDateField() {
        return departureDateField;
    }

    public List<WebElement> calendarMonths() {
        return calendarMonths;
    }

    public WebElement occupancyField() {
        return occupancyField;
    }

    public WebElement cheapestTab() {
        return cheapestTab;
    }



    public void searchResultPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(searchResultPage)).click();
    }

    public WebElement selectDepartureDate(String date) {
        String selectDepartureDate = "//span[contains(@aria-label, '" + date + "')]";
        return driver.findElement(By.xpath(selectDepartureDate));
    }

    public WebElement checkDeparatureLocation(String location) {
        String checkDepatureLocation = "//button[@data-ui-name ='input_location_from_segment_0']/div[contains(@class,'ShellButton-module__inner')]//span[text()='" + location + "']";
        return driver.findElement(By.xpath(checkDepatureLocation));
    }

    public void flightSuggestions() {
        String locationSuggestions = "#flights-searchbox_suggestions";
        try {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(locationSuggestions))));
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter text: " + e.getMessage());
        }
    }

    public boolean isLocationPresent(String locationName) {
        String location = "//ul[@id='flights-searchbox_suggestions']/li/span[contains(@class,'List-module__content')]/span[text()='" + locationName + "']";
        return driver.findElement(By.xpath(location)).isDisplayed();
    }


    public WebElement selectDepartureLocationCheckbox(String locationName) {
        String location = "//ul[@id='flights-searchbox_suggestions']/li/span[contains(@class,'List-module__content')]/span[text()='" + locationName + "']/../../span[contains(@class,'List-module__checkBox')]/div";
        return driver.findElement(By.xpath(location));
    }

    public List<WebElement> fetchCalendarDates() {
        String calendarDates = "//div[contains(@class,'Calendar-module__monthWrapper')]/table/tbody/tr/td/span";
        return driver.findElements(By.xpath(calendarDates));
    }

    public String addAdultPassenger(String passengerCount) {
        String passenger = "//div[@data-ui-name='occupancy_adults']//label";

        if (driver.findElement(By.xpath(passenger)).getText().equalsIgnoreCase("Adults")) {
            String addButton = "//div[@data-ui-name='occupancy_adults']//button[contains(@class,'add')]";
            for (int i = 1; i < Integer.parseInt(passengerCount); i++) {
                driver.findElement(By.xpath(addButton)).click();
            }
        }
        String adultCount = "//div[@data-ui-name='occupancy_adults']//span[contains(@class,'InputStepper-module__value')]";
        String adultPassengerCount = driver.findElement(By.xpath(adultCount)).getText();
        return adultPassengerCount;
    }

    public String addChildrenPassenger(String passengerCount) throws InterruptedException {
        String passenger = "//div[@data-ui-name='occupancy_children']//label";

        if (driver.findElement(By.xpath(passenger)).getText().equalsIgnoreCase("Adults")) {
            String addButton = "//div[@data-ui-name='occupancy_children']//button[contains(@class,'add')]";
            for (int i = 0; i <= Integer.parseInt(passengerCount); i++) {
                driver.findElement(By.xpath(addButton)).click();
                Thread.sleep(1000);
            }
        }
        String childrenCount = "//div[@data-ui-name='occupancy_children']//span[contains(@class,'InputStepper-module__value')]";
        String childrenPassengerCount = driver.findElement(By.xpath(childrenCount)).getText();
        return childrenPassengerCount;
    }

    public String searchFlightbasedOnLowestPrice(){
        double minPrice = Double.MAX_VALUE;
        int minIndex = -1;
        for(int i = 0 ; i< priceList.size(); i++){
            double value = Double.parseDouble(priceList.get(i).getText().replaceAll("INR", "").replaceAll(",", "").trim());
            if (value < minPrice) {
                minPrice = value;
                minIndex = i;
            }
        }

        String airlineName = "((//div[@data-testid='flight_card_price_main_price']/div/div)["+minIndex+"])/../../../../../../../../div[contains(@class,'justify')]//div[contains(@data-testid,'flight_card_carrier_0')]/div/div";

        System.out.println("Cheapest price airline name is: "+ driver.findElement(By.xpath(airlineName)).getText());
        System.out.println("Price of Airline: "+ priceList.get(minIndex).getText());


        //String viewDetails = "((//div[@data-testid='flight_card_price_main_price']/div/div)["+minIndex+"])/../../../../../../div/following-sibling::button[@data-testid='flight_card_bound_select_flight']";
        //driver.findElement(By.xpath(viewDetails)).click();

        //driver.findElement(By.xpath("//div[@data-testid='flight_details_inner_modal_select_button']"));

        String airlineDetailsWithPrice = "Airline Name <b>" + driver.findElement(By.xpath(airlineName)).getText() + "</b> and it price: <b>" + priceList.get(minIndex).getText() +"</b>";
        return airlineDetailsWithPrice;
    }

    public WebElement airlineNameFilter(String airlineName) {
        String airline = "//div[@data-testid='search_filter_airline_"+airlineName+"']";
        return driver.findElement(By.xpath(airline));
    }

    public WebElement onlyThisAirlineButton(String airlineName){
        String button = "//button[@data-testid='search_filter_airline_only_this_"+airlineName+"']";
        return driver.findElement(By.xpath(button));

    }

    public String searchCheapestFlight(){
        double minPrice = Double.MAX_VALUE;
        int minIndex = 1;
        double value = Double.parseDouble(priceList.get(1).getText().replaceAll("INR", "").replaceAll(",", "").trim());
        minPrice = value;
//        for(int i = 0 ; i< priceList.size(); i++){
//            double value = Double.parseDouble(priceList.get(i).getText().replaceAll("INR", "").replaceAll(",", "").trim());
//            if (value < minPrice) {
//                minPrice = value;
//            }
//        }

        String airlineName = "((//div[@data-testid='flight_card_price_main_price']/div/div)["+minIndex+"])/../../../../../../../../div[contains(@class,'justify')]//div[contains(@data-testid,'flight_card_carrier_0')]/div/div";

        System.out.println("Cheapest price airline name is: "+ driver.findElement(By.xpath(airlineName)).getText());
        System.out.println("Price of Airline: "+ priceList.get(minIndex).getText());


        //String viewDetails = "((//div[@data-testid='flight_card_price_main_price']/div/div)["+minIndex+"])/../../../../../../div/following-sibling::button[@data-testid='flight_card_bound_select_flight']";
        //driver.findElement(By.xpath(viewDetails)).click();

        //driver.findElement(By.xpath("//div[@data-testid='flight_details_inner_modal_select_button']"));

        String airlineDetailsWithPrice = "Airline Name <b>" + driver.findElement(By.xpath(airlineName)).getText() + "</b> and it price: <b>" + priceList.get(minIndex).getText() +"</b>";
        return airlineDetailsWithPrice;
    }



}
