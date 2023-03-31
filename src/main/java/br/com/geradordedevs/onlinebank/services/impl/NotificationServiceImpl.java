package br.com.geradordedevs.onlinebank.services.impl;

import br.com.geradordedevs.onlinebank.entities.TransactionEntity;
import br.com.geradordedevs.onlinebank.exceptions.NotificationException;
import br.com.geradordedevs.onlinebank.exceptions.enums.NotificationExceptionEnum;
import br.com.geradordedevs.onlinebank.services.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    @Value("${email.smpt-port}")
    private int smptPort;

    @Value("${email.host-name}")
    private String hostName;

    @Value("${email.user}")
    private String user;

    @Value("${email.password}")
    private String password;

    @Value("${email.from}")
    private String from;

    @Value("${email.subject}")
    private String subject;

    private final String START_TEXT = "We register a new transfer made by you to : ";

    private final String END_TEXT = " \nin the amount of : ";
    private final String HELLP_TEXT = " \nif you don't remember what you did, please contact us by phone (0800-9999999) ";

    @Override
    public void sendEmail(String payerEmail, String payeeEmail, BigDecimal paymentValue) {
        SimpleEmail simpleEmail = new SimpleEmail();
        simpleEmail.setHostName(hostName);
        simpleEmail.setSmtpPort(smptPort);
        simpleEmail.setAuthenticator(new DefaultAuthenticator(user, password));
        simpleEmail.setSSLOnConnect(true);

        try {
            simpleEmail.addTo(payerEmail);
            simpleEmail.setFrom(from);
            simpleEmail.setSubject(subject);
            simpleEmail.setMsg(START_TEXT + payeeEmail + END_TEXT + paymentValue + HELLP_TEXT );

            simpleEmail.send();
            log.info("email successfully sent");

        } catch (EmailException e) {
            throw new NotificationException(NotificationExceptionEnum.ERROR_TO_SEND_EMAIL);
        }
    }
}
