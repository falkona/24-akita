package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.pages.*;
import ru.netology.utils.*;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @Test
    void depositCard1FromCard2NoOverdaft() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);

        Card card1 = DataHelper.getCardInfoJson(1);
        Card card2 = DataHelper.getCardInfoJson(2);

        dashboardPage.depositCardFromCard(card2, card1, 100);
        Card.transferMoneyCardToCard(card2, card1, 100);
        dashboardPage.checkBalance(card1);
        dashboardPage.checkBalance(card2);
    }
}

