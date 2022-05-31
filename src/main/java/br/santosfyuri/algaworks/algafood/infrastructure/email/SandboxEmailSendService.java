package br.santosfyuri.algaworks.algafood.infrastructure.email;

import br.santosfyuri.algaworks.algafood.core.email.EmailProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class SandboxEmailSendService extends SmtpEmailSendService{

    @Autowired
    private EmailProperties emailProperties;

    @Override
    protected MimeMessage buildMimeMessage(Message message) throws MessagingException {
        MimeMessage mimeMessage = super.buildMimeMessage(message);

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
        mimeMessageHelper.setTo(emailProperties.getSandbox().getRecipient());

        return mimeMessage;
    }
}
