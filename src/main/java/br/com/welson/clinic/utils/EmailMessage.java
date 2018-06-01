package br.com.welson.clinic.utils;

import java.text.MessageFormat;

public class EmailMessage {

    public static String getEmailMessageForRecoveryPassword(String username, String url) {
        return "<h1>" +
                MessageFormat.format(FacesUtil.getBundleValue("sayHello"), username) +
                "</h1><p>" +
                MessageFormat.format(FacesUtil.getBundleValue("messageRecoveryPasswordMail"), "<a href=\"" + url + "\">" + url + "</a>") +
                "</p>";
    }

    public static String getEmailMessageForActivateAccount(String username, String url) {
        return "<h1>" +
                MessageFormat.format(FacesUtil.getBundleValue("sayHello"), username) +
                "</h1><p>" +
                MessageFormat.format(FacesUtil.getBundleValue("messageActivateAccountMail"), "<a href=\"" + url + "\">" + url + "</a>") +
                "</p>";
    }
}
