import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BookingStepDefinitions {

    private WebDriver driver;
    private List<Map<String, String>> bookings;
    private List<Map<String, String>> schedule;
    private boolean bookingSuccessful;

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

    @When("^I try to book a slot$")
    public void i_try_to_book_a_slot() {
        for (Map<String, String> slot : schedule) {
            if (isSlotAvailable(slot)) {
                bookSlot(slot);
                bookingSuccessful = true;
                break;
            }
        }
    }

    @Then("^I should (not )?receive a confirmation$")
    public void i_should_receive_a_confirmation(String negation) {
        WebElement confirmationElement = null;
        try {
            confirmationElement = driver.findElement(By.id("confirmation-message"));
        } catch (Exception e) {
            // Confirmation element not found
        }

        if (negation == null) {
            assertNotNull("Confirmation message should be displayed", confirmationElement);
        } else {
            assertNull("Confirmation message should not be displayed", confirmationElement);
        }

        if (bookingSuccessful) {
            driver.quit();
        }
    }

    private boolean isSlotAvailable(Map<String, String> slot) {
        for (Map<String, String> booking : bookings) {
            if (booking.get("date").equals(slot.get("date")) &&
                    booking.get("start_time").equals(slot.get("start_time"))) {
                return false;
            }
        }
        return true;
    }

    private void bookSlot(Map<String, String> slot) {
        WebElement slotElement = driver.findElement(By.id("slot-" + slot.get("id")));
        slotElement.click();
        WebElement bookButton = driver.findElement(By.id("book-button"));
        bookButton.click();
    }
}