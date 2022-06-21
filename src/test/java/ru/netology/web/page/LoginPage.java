package ru.netology.web.page;

import ru.netology.web.data.DataInfo;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public VerificationPage validLogin(DataInfo.AuthInfo authInfo) {
        $("[data-test-id=login] input").setValue(authInfo.getLogin());
        $("[data-test-id=password] input").setValue(authInfo.getPassword());
        $("[data-test-id=action-login]").click();
        return new VerificationPage();
    }

    public DashboardPage notValidLogin(DataInfo.AuthInfo authInfo) {
        $("[data-test-id=login] input").setValue(authInfo.getLogin());
        $("[data-test-id=password] input").setValue("111");
        $("[data-test-id=action-login]").click();
        return new DashboardPage();
    }

    public DashboardPage notValidTripleClickLogin(DataInfo.AuthInfo authInfo) {
        $("[data-test-id=login] input").setValue(authInfo.getLogin());
        $("[data-test-id=password] input").setValue("111");
        $("[data-test-id=action-login]").click();
        $("[data-test-id=action-login]").click();
        $("[data-test-id=action-login]").click();
        return new DashboardPage();
    }
}
