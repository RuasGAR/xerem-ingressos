package org.agilekip.tutorials.travel.delegate;

import org.agilekip.tutorials.travel.service.MailService;
import org.agilekip.tutorials.travel.service.dto.IngressoDTO;
import org.agilekip.tutorials.travel.service.dto.ProcessoIngressoDTO;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.Locale;

@Component
public class EmailIngressoDelegate implements JavaDelegate {

    @Autowired
    MailService mailService;

    @Autowired
    SpringTemplateEngine templateEngine;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        ProcessoIngressoDTO processoIngresso = (ProcessoIngressoDTO) delegateExecution.getVariable("processInstance");
        IngressoDTO ingresso = processoIngresso.getIngresso();
        String to = ingresso.getEmailComprador();
        String subject = "[Xerem Ingressos] Confirmação do ingresso";
        Context context = new Context(Locale.getDefault());
        context.setVariable("ingresso", ingresso);
        String content = templateEngine.process("processoIngresso/emailIngresso", context);
        mailService.sendEmail(to, subject, content, false, true);
    }
}