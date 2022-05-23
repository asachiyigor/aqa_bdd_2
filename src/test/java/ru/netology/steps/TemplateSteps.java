package ru.netology.steps;

import io.cucumber.java.ru. огда;
import io.cucumber.java.ru.ѕусть;
import io.cucumber.java.ru.“огда;
import lombok.val;
import ru.netology.data.Data;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemplateSteps {

    @ѕусть("пользователь залогинен с именем {string} и паролем {string}")
    public void loginWithNameAndPasswordAndVerify(String string, String string2) {
        open("http://localhost:9999", LoginPage.class);
        val loginPage = new LoginPage();
        val authInfo = new Data.AuthInfo(string, string2);
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = Data.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @ огда("пользователь переводит {string} рублей с карты с номером {string} на свою \"{int}\" карту с главной страницы")
    public void transferMoneyToOwnCard(String amount, String string2, int cardId) {
        int FirstCardBalance = DashboardPage.getFirstCardBalance();
        int SecondCardBalance = DashboardPage.getSecondCardBalance();
        var TransferPage = DashboardPage.firstCard();
        String string3 = string2.replace(" ","");
        TransferPage.transferCard(new Data.CardInfo(string3), Integer.parseInt(amount));
        Data.increaseBalance(FirstCardBalance, Integer.parseInt(amount));
        Data.decreaseBalance(SecondCardBalance, Integer.parseInt(amount));
        DashboardPage.getFirstCardBalance();
        DashboardPage.getSecondCardBalance();
    }

    @“огда("баланс его \"{int}\" карты из списка на главной странице должен стать {string} рублей")
    public void verify(int cardId, String string2) {
        int firstBalance = DashboardPage.getFirstCardBalance();
        assertEquals(firstBalance, Integer.parseInt(string2));
    }
}