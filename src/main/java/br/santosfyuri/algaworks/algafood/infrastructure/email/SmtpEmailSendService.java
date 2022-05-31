package br.santosfyuri.algaworks.algafood.infrastructure.email;

import br.santosfyuri.algaworks.algafood.core.email.EmailProperties;
import br.santosfyuri.algaworks.algafood.domain.service.EmailSendService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SmtpEmailSendService implements EmailSendService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailProperties emailProperties;

    @Autowired
    private Configuration freemarkerConfig;

    @Override
    public void send(Message message) {
        try {
            MimeMessage mimeMessage = buildMimeMessage(message);
        } catch (Exception exception) {
            throw new EmailException("Não foi possível enviar o e-mail", exception);
        }
    }

    protected MimeMessage buildMimeMessage(Message message) throws MessagingException {
        final String body = processTemplate(message);

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
        mimeMessageHelper.setFrom(emailProperties.getSender());
        mimeMessageHelper.setTo(message.getRecipients().toArray(new String[0]));
        mimeMessageHelper.setSubject(message.getSubject());
        mimeMessageHelper.setText(body, true);

        return mimeMessage;
    }

    protected String processTemplate(Message message) {
        try {
            Template template = freemarkerConfig.getTemplate(message.getBody());
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, message.getVariables());
        } catch (Exception e) {
            throw new EmailException("Não foi possível montar o template do e-mail", e);
        }
    }
}
