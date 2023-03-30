package br.santosfyuri.algaworks.algafood.api.assembler;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
@AllArgsConstructor
public class AssemblerParameters<R> {

    private Class<R> entityType;
    private Class<?> controllerType;
    private Consumer<R> consumer;

}
