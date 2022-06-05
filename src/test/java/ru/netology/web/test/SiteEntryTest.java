package ru.netology.web.test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataInfo;
import ru.netology.web.page.LoginPage;

import java.sql.*;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SiteEntryTest {

    @BeforeEach
    void openSite() {
        open("http://localhost:9999");
    }

    @AfterAll
    static void clearBase() throws SQLException {
        DataInfo.closeConnect();
    }

    @Test
    void loginOldTest() throws SQLException {
        var authInfo = DataInfo.getAuthInfo();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var codeInfo = DataInfo.getVerificationCodeFromBase(authInfo.getLogin());
        verificationPage.validCode(codeInfo);
        assertTrue($("[data-test-id='dashboard']").isEnabled());
    }

    @Test
    void loginOldTest2() throws SQLException {
        var authInfo = DataInfo.getOtherAuthInfo();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var codeInfo = DataInfo.getVerificationCodeFromBase(authInfo.getLogin());
        verificationPage.validCode(codeInfo);
        assertTrue($("data-test-id='dashboard']").isEnabled());
    }

    @Test
    void loginNewTest() throws SQLException {
        var authInfo = DataInfo.addUser();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var codeInfo = DataInfo.getVerificationCodeFromBase(authInfo.getLogin());
        verificationPage.validCode(codeInfo);
        assertTrue($("data-test-id='dashboard']").exists());
    }

}
