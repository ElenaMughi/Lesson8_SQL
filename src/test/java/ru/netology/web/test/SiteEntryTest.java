package ru.netology.web.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataInfo;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SiteEntryTest {

    private DashboardPage dashboardPage;

    @BeforeEach
    void openSite() {
        open("http://localhost:9999");
    }

    @AfterAll
    static void clearBase() {
        DataInfo.clearBase();
    }

    @Test
    void loginOldTest() {
        var authInfo = DataInfo.getAuthInfo();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var codeInfo = DataInfo.getVerificationCodeFromBase(authInfo.getLogin());
        dashboardPage = verificationPage.validCode(codeInfo);
        boolean actual = dashboardPage.isPageVisible();
        assertTrue(actual);
    }

    @Test
    void loginOldTest2() {
        var authInfo = DataInfo.getOtherAuthInfo();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var codeInfo = DataInfo.getVerificationCodeFromBase(authInfo.getLogin());
        dashboardPage = verificationPage.validCode(codeInfo);
        boolean actual = dashboardPage.isPageVisible();
        assertTrue(actual);
    }

    @Test
    void loginErrorTest() {
        var authInfo = DataInfo.getOtherAuthInfo();
        var loginPage = new LoginPage();
        dashboardPage = loginPage.notValidLogin(authInfo);
        boolean actual = dashboardPage.isPageNotVisible();
        assertTrue(actual);
    }

    @Test
    void loginErrorTripleClickTest() {
        var authInfo = DataInfo.getOtherAuthInfo();
        var loginPage = new LoginPage();
        dashboardPage = loginPage.notValidTripleClickLogin(authInfo);
        boolean actual = dashboardPage.isPageNotVisible();
        assertTrue(actual);
    }

}
