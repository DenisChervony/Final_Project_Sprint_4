package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы первой страницы заказа (Для кого самокат)
    private final By nameInput = By.xpath("//input[@placeholder='* Имя']");
    private final By surnameInput = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroInput = By.xpath("//input[@placeholder='* Станция метро']");
    private final By phoneInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[text()='Далее']");

    // Локаторы второй страницы заказа (Про аренду)
    private final By dateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriod = By.xpath("//div[text()='* Срок аренды']");
    private final By colorBlack = By.id("black");
    private final By colorGrey = By.id("grey");
    private final By commentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
    private final By confirmButton = By.xpath("//button[text()='Да']");
    private final By successMessage = By.xpath("//div[text()='Заказ оформлен']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public void fillFirstPage(String name, String surname, String address, String metro, String phone) {
        wait.until(ExpectedConditions.elementToBeClickable(nameInput)).sendKeys(name);
        wait.until(ExpectedConditions.elementToBeClickable(surnameInput)).sendKeys(surname);
        wait.until(ExpectedConditions.elementToBeClickable(addressInput)).sendKeys(address);

        wait.until(ExpectedConditions.elementToBeClickable(metroInput)).click();
        if (metro != null && !metro.isEmpty()) {
            By metroOption = By.xpath(String.format("//div[text()='%s']", metro));
            wait.until(ExpectedConditions.elementToBeClickable(metroOption)).click();
        }

        wait.until(ExpectedConditions.elementToBeClickable(phoneInput)).sendKeys(phone);
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }

    public void fillSecondPage(String date, String period, String color, String comment) {
        wait.until(ExpectedConditions.elementToBeClickable(dateInput)).sendKeys(date);
        driver.findElement(By.xpath("//body")).click(); // Клик в любое место, чтобы закрыть календарь

        wait.until(ExpectedConditions.elementToBeClickable(rentalPeriod)).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(String.format("//div[text()='%s']", period)))).click();

        if (color.equals("чёрный жемчуг")) {
            wait.until(ExpectedConditions.elementToBeClickable(colorBlack)).click();
        } else {
            wait.until(ExpectedConditions.elementToBeClickable(colorGrey)).click();
        }

        wait.until(ExpectedConditions.elementToBeClickable(commentInput)).sendKeys(comment);
        wait.until(ExpectedConditions.elementToBeClickable(orderButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
    }

    public boolean isSuccessMessageDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return driver.findElement(successMessage).isDisplayed();
    }

    public boolean isNameErrorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(), 'Введите корректное имя')]"))).isDisplayed();
    }

}