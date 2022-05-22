package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
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

    public String buildTrelloCardEmail(String message) {

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8080/v1/tasks");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("show_button", true);
        context.setVariable("is_friend", true);
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

        Context context2 = new Context();
        context2.setVariable("message", message);
        context2.setVariable("tasks_url", "http://localhost:8080/v1/tasks");
        context2.setVariable("button", "Visit website");
        context2.setVariable("show_button", false);
        context2.setVariable("admin_config", adminConfig);
        context2.setVariable("application_functionality", functionality);
        context2.setVariable("company_name", companyConfig.getCompanyName());
        context2.setVariable("company_goal", companyConfig.getCompanyGoal());
        context2.setVariable("company_email", companyConfig.getCompanyEmail());
        context2.setVariable("company_phone", companyConfig.getCompanyPhone());


        return templateEngine.process("mail/created-trello-card-mail", context2);

    }

}