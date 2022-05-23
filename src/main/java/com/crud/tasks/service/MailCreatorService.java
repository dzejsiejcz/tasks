package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import com.crud.tasks.domain.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    @Qualifier("adminConfig")
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("companyConfig")
    private CompanyConfig companyConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildEmail (Mail mail) {
        if (mail.getShortUrl() == null) {
            return buildTaskDailyMail(mail.getMessage());
        }
        return buildTrelloCardEmail(mail.getMessage(), mail.getShortUrl());
    }

    public String buildTrelloCardEmail(String message, String shortTrelloUrl) {

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", shortTrelloUrl);
        context.setVariable("button", "Check your new trello task");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("show_button", true);
        context.setVariable("is_friend", true);
        context.setVariable("goodbye", "Keep smile");
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_goal", companyConfig.getCompanyGoal());
        context.setVariable("company_email", companyConfig.getCompanyEmail());
        context.setVariable("company_phone", companyConfig.getCompanyPhone());

        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildTaskDailyMail (String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://dzejsiejcz.github.io");
        context.setVariable("button", "Visit all tasks");
        context.setVariable("show_button", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        context.setVariable("goodbye", "Keep smile");
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_goal", companyConfig.getCompanyGoal());
        context.setVariable("company_email", companyConfig.getCompanyEmail());
        context.setVariable("company_phone", companyConfig.getCompanyPhone());

        return templateEngine.process("mail/tasks-daily-mail", context);
    }
}