package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.pages.*;
import ru.netology.utils.*;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @Test
    @DisplayName("Выполняется проверка перевода с карты на карту, сумма перевода < баланса карты 1")
    void depositCardFromCardNoOverdaft() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);

        Card cardFrom = DataHelper.getCardInfoJson(1);
        Card cardTo = DataHelper.getCardInfoJson(2);
        int amount = DataHelper.generateRandomInt(cardFrom.getBalance());

        dashboardPage.depositCardFromCard(cardFrom, cardTo, amount);
        Card.transferMoneyCardToCard(cardFrom, cardTo, amount);
        dashboardPage.checkBalance(cardFrom);
        dashboardPage.checkBalance(cardTo);
    }

    @Test
    @DisplayName("Выполняется проверка перевода с карты 1 на карту 2, сумма перевода > баланса карты 1")
    void depositCard1FromCard2WithOverdaft() {}

    @Test
    @DisplayName("Выполняется проверка перевода с карты 1 на карту 1 любой случайной суммы")
    void depositCard1FromCard1() {}

    @Test
    @DisplayName("Выполняется проверка нотификации об ошибке, если указан номер несуществующей карты")
    void depositCard1FromUnrealCard() {}
}

