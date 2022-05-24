package com.crud.tasks.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Mail {

    private final String mailTo;
    private final String toCc;
    private final String subject;
    private final String message;
    private String shortUrl = null;


    @Builder
    public Mail(String mailTo, String toCc, String subject, String message) {
        this.mailTo = mailTo;
        this.toCc = toCc;
        this.subject = subject;
        this.message = message;
    }

    public Mail(String mailTo, String toCc, String subject, String message, String shortUrl) {
        this.mailTo = mailTo;
        this.toCc = toCc;
        this.subject = subject;
        this.message = message;
        this.shortUrl = shortUrl;
    }
}
