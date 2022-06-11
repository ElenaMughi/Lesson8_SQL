package ru.netology.web.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataInfo;
import ru.netology.web.page.LoginPage;

import java.time.Duration;
import static com.codeborne.selenide.Selenide.*;

public class SiteEntryTest {

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
        verificationPage.validCode(codeInfo);
        $("data-test-id='dashboard']").shouldBe();
    }

    @Test
    void loginOldTest2() {
        var authInfo = DataInfo.getOtherAuthInfo();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var codeInfo = DataInfo.getVerificationCodeFromBase(authInfo.getLogin());
        verificationPage.validCode(codeInfo);
        $("data-test-id='dashboard']").shouldBe();
    }

    @Test
    void loginErrorTest() {
        var authInfo = DataInfo.getOtherAuthInfo();
        var loginPage = new LoginPage();
        loginPage.notValidLogin(authInfo);
        $("data-test-id='error-notification'").shouldBe();
    }

    @Test
    void loginErrorTripleClickTest() {
        var authInfo = DataInfo.getOtherAuthInfo();
        var loginPage = new LoginPage();
        loginPage.notValidTripleClickLogin(authInfo);
        $("data-test-id='error-notification'").shouldBe();
    }

}
