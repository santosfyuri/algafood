package br.santosfyuri.algaworks.algafood.core.email;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@Getter
@Setter
@Component
@ConfigurationProperties("algafood.email")
public class EmailProperties {

    @NotNull
    private String sender;

    private Implementation impl = Implementation.FAKE;

    private Sandbox sandbox = new Sandbox();

    public enum Implementation {
        SMTP, FAKE, SANDBOX
    }

    @Getter
    @Setter
    public static class Sandbox {

        private String recipient;
    }
}
