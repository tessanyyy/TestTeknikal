package testteknikal.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class BookingStepDefinitions {
    private WebDriver driver;
    private List<Map<String, String>> bookings;
    private List<Map<String, String>> schedule;
    private List<String> issues;

    @Given("^I have opened the booking website$")
    public void i_have_opened_the_booking_website() {
        System.setProperty("webdriver.chrome.driver", "path_to_chrome_driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://yourbookingwebsite.com");
    }

    @Given("^the following bookings exist:$")
    public void the_following_bookings_exist(List<Map<String, String>> bookingData) {
        bookings = bookingData;
    }

    @Given("^the following schedule is available:$")
    public void the_following_schedule_is_available(List<Map<String, String>> scheduleData) {
        schedule = scheduleData;
    }

    @When("^the bookings are validated$")
    public void the_bookings_are_validated() {
        for (Map<String, String> booking : bookings) {
            WebElement bookingElement = driver.findElement(By.id("booking-" + booking.get("Booking_id")));
            bookingElement.click();

            WebElement dateElement = driver.findElement(By.id("date"));
            String date = dateElement.getText();

            WebElement startTimeElement = driver.findElement(By.id("start-time"));
            String startTime = startTimeElement.getText();

            WebElement priceElement = driver.findElement(By.id("price"));
            String price = priceElement.getText();

            for (Map<String, String> slot : schedule) {
                if (date.equals(slot.get("date")) && startTime.equals(slot.get("start_time"))) {
                    if (!price.equals(slot.get("price"))) {
                        issues.add(booking.get("Booking_id"));
                    }
                    issues.add(booking.get("Booking_id"));
                    break;
                }
            }
        }
    }

    @Then("^the following booking issues are detected:$")
    public void the_following_booking_issues_are_detected(List<String> expectedIssues) {
        assertEquals(expectedIssues, issues);
        driver.quit();
    }
}
