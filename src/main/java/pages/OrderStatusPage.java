package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderStatusPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локатор для изображения "Not found"
    private final By notFoundImage = By.xpath("//div[contains(@class, 'Track_NotFound')]//img[@alt='Not found']");

    public OrderStatusPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public boolean isNotFoundMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(notFoundImage)).isDisplayed();
        } catch (Exception e) {
            System.err.println("Изображение 'Not found' не найдено");
            return false;
        }
    }
}