package com.spring.basics.services.impletentations;

import com.spring.basics.services.interfaces.SenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;

@Service
@RequiredArgsConstructor
public class SenderServiceImpl implements SenderService {
    private final JavaMailSender mailSender;
    private final ExecutorService executorService;

    @Value("${sender.name}")
    private String senderName;
    @Override
    public void sendMessage(String subject, String mail, String html) {
        Runnable runnable = () -> {
            MimeMessagePreparator message = mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                        StandardCharsets.UTF_8.name());
                messageHelper.setFrom(senderName);
                messageHelper.setTo(mail);
                messageHelper.setSubject(subject);
                messageHelper.setText(html, true);
            };
            mailSender.send(message);
        };
        executorService.submit(runnable);
    }
}
