package ru.netology.web.page;

import ru.netology.web.data.DataInfo;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    public void validCode(DataInfo.VerificationCode verificationCode) {
        $("[data-test-id=code] input").setValue(verificationCode.getCode());
        $("[data-test-id=action-verify]").click();
    }

}
