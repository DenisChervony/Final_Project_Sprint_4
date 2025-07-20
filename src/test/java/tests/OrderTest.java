package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.MainPage;
import pages.OrderPage;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {
    private final String buttonLocation;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String period;
    private final String color;
    private final String comment;

    public OrderTest(String buttonLocation, String name, String surname, String address,
                     String metro, String phone, String date, String period,
                     String color, String comment) {
        this.buttonLocation = buttonLocation;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.period = period;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"top", "Иван", "Иванов", "Москва, ул. Ленина 1", "Сокольники",
                        "88005553535", "01.08.2025", "сутки", "чёрный жемчуг", "Позвонить за неделю"},
                {"bottom", "Михаил", "Заводчиков", "ул Шпица, дом 007", "Парк Победы",
                        "+72153456124", "27.07.2025", "сутки", "серая безысходность", "В доме злая собака. Прошу не пугать"}
        });
    }

    @Test
    public void testOrderCreation() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCookieButton();

        if ("top".equals(buttonLocation)) {
            mainPage.clickOrderButtonTop();
        } else {
            mainPage.clickOrderButtonBottom();
        }

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillFirstPage(name, surname, address, metro, phone);
        orderPage.fillSecondPage(date, period, color, comment);

        assertTrue("Order success message not displayed", orderPage.isSuccessMessageDisplayed());
    }
}