package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Mail {

    private final String mailTo;
    private final String toCc;
    private final String subject;
    private final String message;


    @Builder
    public Mail(String mailTo, String toCc, String subject, String message) {
        this.mailTo = mailTo;
        this.toCc = toCc;
        this.subject = subject;
        this.message = message;
    }
}
