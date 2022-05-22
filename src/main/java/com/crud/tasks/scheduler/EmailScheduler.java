package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TypeOfMail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {
    private static final String SUBJECT = "Tasks: Once a day email";

    private final SimpleEmailService simpleEmailService;
    private final TaskRepository taskRepository;
    private final AdminConfig adminConfig;

    @Scheduled(fixedDelay = 30000)
    @Scheduled(cron = "0 0 8 ? * *")
    private void sendInformationEmail() {
        long size = taskRepository.count();
        String pluralWord;
        pluralWord = size == 1 ? " task." : " tasks";
        simpleEmailService.send(
                new Mail(adminConfig.getAdminMail(),
                        null,
                        SUBJECT,
                        "Currently in database you got: " + size + pluralWord, TypeOfMail.DAILY)
        );
    }
}
