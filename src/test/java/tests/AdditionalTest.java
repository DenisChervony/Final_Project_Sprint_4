package tests;

import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import pages.OrderPage;
import pages.OrderStatusPage;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AdditionalTest extends BaseTest {
    // Константы
    private static final String SCOOTER_HOMEPAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static final String YANDEX_DZEN_URL_PART = "dzen.ru";
    private static final String INVALID_ORDER_NUMBER = "00000";

    // Доп. тест 1: Логотип Самоката
    @Test
    public void dop1_clickScooterLogoRedirectsToHomepage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCookieButton();
        mainPage.clickOrderButtonTop();
        mainPage.clickScooterLogo();

        assertEquals("Главная страница не открылась",
                SCOOTER_HOMEPAGE_URL,
                driver.getCurrentUrl());
    }

    // Доп. тест 2: Логотип Яндекса
    @Test
    public void dop2_clickYandexLogoOpensDzen() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCookieButton();
        mainPage.clickYandexLogo();

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlContains(YANDEX_DZEN_URL_PART));

        assertTrue("Страница Яндекса не открылась",
                driver.getCurrentUrl().contains(YANDEX_DZEN_URL_PART));
    }

    // Доп. тест 3: Ошибки в форме заказа
    @Test
    public void dop3_checkOrderFormValidation() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCookieButton();
        mainPage.clickOrderButtonTop();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillFirstPage("", "", "", "", "");

        // Используем метод из PageObject для проверки ошибки
        assertTrue("Ошибка имени не отображается",
                orderPage.isNameErrorDisplayed());
    }

    // Доп. тест 4: Неверный номер заказа
    @Test
    public void dop4_checkInvalidOrderNumber() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCookieButton();
        mainPage.clickStatusOrderButton();

        OrderStatusPage statusPage = mainPage.checkOrderStatus(INVALID_ORDER_NUMBER);

        assertTrue("Изображение 'Not found' не отображается (сообщение об отсутствии заказа)",
                statusPage.isNotFoundMessageDisplayed());
    }
}