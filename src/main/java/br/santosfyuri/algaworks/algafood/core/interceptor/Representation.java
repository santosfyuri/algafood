package br.santosfyuri.algaworks.algafood.core.interceptor;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Representation {

    Class<?> from();

    Class<?> to();
}
