package br.santosfyuri.algaworks.algafood.core.email;

import br.santosfyuri.algaworks.algafood.domain.service.EmailSendService;
import br.santosfyuri.algaworks.algafood.infrastructure.email.FakeEmailSendService;
import br.santosfyuri.algaworks.algafood.infrastructure.email.SandboxEmailSendService;
import br.santosfyuri.algaworks.algafood.infrastructure.email.SmtpEmailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Autowired
    private EmailProperties emailProperties;

    @Bean
    public EmailSendService emailSendService() {
        switch (emailProperties.getImpl()) {
            case FAKE:
                return new FakeEmailSendService();
            case SMTP:
                return new SmtpEmailSendService();
            case SANDBOX:
                return new SandboxEmailSendService();
            default:
                return null;
        }
    }
}
