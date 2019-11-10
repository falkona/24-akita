package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.utils.Card;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private SelenideElement amountInput = $("[data-test-id=amount] input");
    private SelenideElement cardFromInput = $("[data-test-id=from] input");
    private Card card1;
    private Card card2;

    public DashboardPage() {}

    public DashboardPage depositCardFromCard(Card cardFrom, Card cardTo, double amount) {
        String buttonSelector = cardTo.getId();
        SelenideElement depositCardButton = $(String.format("[data-test-id='%s'] [data-test-id=action-deposit]", buttonSelector));
        depositCardButton.click();
        amountInput.shouldBe(Condition.visible);
        amountInput.sendKeys(Double.toString(amount));
        cardFromInput.sendKeys(cardFrom.getNumber());
        transferButton.click();
        return new DashboardPage();
    }

    public void checkBalance(Card card) {
        String balanceInfo = "**** **** **** %s, баланс: %s р.";
        $(String.format("[data-test-id='%s']", card.getId())).shouldHave(Condition.exactText(String.format(balanceInfo, card.getLastSymbols(), card.getBalance())));
    }
}
