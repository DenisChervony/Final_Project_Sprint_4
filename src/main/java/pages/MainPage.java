package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private final WebDriver driver;

    // Локаторы
    private final By cookieButton = By.id("rcc-confirm-button"); // Кнопка согласия с куки

    /* В задании сказано "Обрати внимание: в приложении есть баг, который не даёт оформить заказ.
    Он воспроизводится только в Chrome."
    Локатор нижней кнопки "Заказать" срабатывает*/
    private final By orderButtonTop = By.xpath(".//button[text()='Заказать' and @class='Button_Button__ra12g']"); // Верхняя кнопка "Заказать"
    private final By orderButtonBottom = By.xpath("//div[contains(@class, 'Home_FinishButton')]/button[text()='Заказать']"); // Нижняя кнопка "Заказать"
    private final By faqQuestion = By.xpath("//div[@data-accordion-component='AccordionItem']"); // Вопросы FAQ
    private final By faqAnswer = By.xpath("//div[@data-accordion-component='AccordionItemPanel']"); // Ответы FAQ
    private final By scooterLogo = By.xpath("//a[@class='Header_LogoScooter__3lsAR']"); // Логотип Самоката
    private final By yandexLogo = By.xpath("//a[@class='Header_LogoYandex__3TSOI']"); // Логотип Яндекса
    private final By statusOrderButton = By.xpath("//button[text()='Статус заказа']"); // Кнопка "Статус заказа"
    private final By orderNumberField = By.xpath("//input[@placeholder='Введите номер заказа']"); // Поле ввода "Введите номер заказа"
    private final By goButton = By.xpath("//button[text()='Go!']"); // Кнопка Go!

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCookieButton() {
        driver.findElement(cookieButton).click();
    }

    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }

    public void clickOrderButtonBottom() {
        WebElement element = driver.findElement(orderButtonBottom);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void clickFaqQuestion(int index) {
        driver.findElements(faqQuestion).get(index).click();
    }

    public String getFaqAnswer(int index) {
        return driver.findElements(faqAnswer).get(index).getText();
    }

    public void clickScooterLogo() {
        driver.findElement(scooterLogo).click();
    }

    public void clickYandexLogo() {
        driver.findElement(yandexLogo).click();
    }

    public void searchOrderStatus(String orderNumber) {
        driver.findElement(orderNumberField).sendKeys(orderNumber);
        driver.findElement(goButton).click();
    }

    public OrderStatusPage checkOrderStatus(String orderNumber) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOfElementLocated(orderNumberField)); // Ожидаем появления формы

        driver.findElement(orderNumberField).sendKeys(orderNumber); // Вводим номер заказа

        driver.findElement(goButton).click(); // Нажимаем кнопку Go!

        wait.until(ExpectedConditions.urlContains("/track"));  // Ждем загрузки страницы статуса

        return new OrderStatusPage(driver);
    }

    public void clickStatusOrderButton() {
        driver.findElement(statusOrderButton).click();
    }

    public void setFieldOrderNumber(String orderNumber) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(orderNumberField)).sendKeys(orderNumber);
    }

    public void clickGoButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(goButton)).click();
    }
}