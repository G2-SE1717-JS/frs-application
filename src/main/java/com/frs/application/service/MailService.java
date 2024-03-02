package com.frs.application.service;
import com.frs.application.domain.mail.Email;
import org.springframework.beans.factory.annotation.Value;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {
    @Value("${mailgun.domain}")
    private String mailgunDomain;

    @Value("${mailgun.api-key}")
    private String mailgunApiKey;

    @Value("${mailgun.name}")
    private String mailgunName;

    @Value("${mailgun.from}")
    private String mailgunFrom;


    public void sendHtml(Email email) throws UnirestException {
        String url = StringUtils.join("https://api.mailgun.net/v3/", mailgunDomain, "/messages");
        HttpResponse<String> request = Unirest.post(url)
                .basicAuth("api", mailgunApiKey)
                .queryString("from", StringUtils.join(mailgunName,
                        " <", mailgunFrom, ">"))
                .queryString("to", email.getTo().getFirst().getEmail())
                .queryString("subject", email.getSubject())
                .queryString("html", email.getHtmlPart())
                .asString();
        log.info("Mailgun response: {}", request.getBody());

        request.getStatus();
    }
}
