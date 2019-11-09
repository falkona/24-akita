package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.utils.Card;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    private SelenideElement depositCard1Button = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] [data-test-id=action-deposit]");
    private SelenideElement depositCard2Button = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] [data-test-id=action-deposit]");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private SelenideElement amountInput = $("[data-test-id=amount]");
    private SelenideElement cardFromInput = $("[data-test-id=from]");
    private Card card1;
    private Card card2;

    public DashboardPage depositCard1FromCard2NoOverdraft() {
        depositCard1Button.click();
        amountInput.shouldBe(Condition.visible);
        amountInput.sendKeys("100");
        cardFromInput.sendKeys(card2.getNumber());
        transferButton.click();
        return new DashboardPage();
    }

    public DashboardPage depositCard2FromCard1NoOverdraft() {
        depositCard2Button.click();
        amountInput.shouldBe(Condition.visible);
        amountInput.sendKeys("100");
        cardFromInput.sendKeys(card1.getNumber());
        transferButton.click();
        return new DashboardPage();
    }





}
