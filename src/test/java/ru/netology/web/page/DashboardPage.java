package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    public boolean isPageVisible() {

        boolean isVisible = $("data-test-id='dashboard']").shouldBe(Condition.visible).exists();
        return  isVisible;

    }

    public boolean isPageNotVisible() {

        boolean isVisible = $("data-test-id='error-notification'").has(Condition.visible);
        return isVisible;

    }


}
