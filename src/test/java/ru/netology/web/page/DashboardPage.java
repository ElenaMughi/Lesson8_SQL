package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    public boolean isPageVisible() {

        boolean isVisible = $("[data-test-id='dashboard']").shouldBe(Condition.visible).exists();
 //       return $("[data-test-id='dashboard']").shouldBe(Condition.visible);
        return  isVisible;

    }

    public boolean isPageNotVisible() {

        boolean isVisible = $("[data-test-id='error-notification']").shouldBe(Condition.visible).exists();
        return isVisible;

    }


}
