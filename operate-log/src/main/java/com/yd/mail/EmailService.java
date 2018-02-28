package com.yd.mail;

public interface EmailService {

    void sendEmail(String toAdress, String subject, String htmlText);

}
