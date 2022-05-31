package br.santosfyuri.algaworks.algafood.domain.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Set;

public interface EmailSendService {

    void send(Message message);

    @Getter
    @Builder(builderClassName = "Builder", builderMethodName = "create", toBuilder = true)
    class Message {

        @Singular
        private Set<String> recipients;

        @NotNull
        private String subject;

        @NotNull
        private String body;

        @Singular
        private Map<String, Object> variables;
    }
}
