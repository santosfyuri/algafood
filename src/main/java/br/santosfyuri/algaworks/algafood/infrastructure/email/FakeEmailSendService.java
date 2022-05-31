package br.santosfyuri.algaworks.algafood.infrastructure.email;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FakeEmailSendService extends SmtpEmailSendService {

    @Override
    public void send(Message message) {
        final String body = processTemplate(message);
        log.info("[FAKE E-MAIL] Para: {}\n{}", message.getRecipients(), body);
    }
}
