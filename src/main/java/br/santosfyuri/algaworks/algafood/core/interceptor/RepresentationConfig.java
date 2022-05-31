/*
package br.santosfyuri.algaworks.algafood.core.interceptor;

import br.santosfyuri.algaworks.algafood.api.assembler.BasicDisassembler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManager;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@ComponentScan(basePackages = "br.santosfyuri.*")
@EnableWebMvc
public class RepresentationConfig implements WebMvcConfigurer {
    private final BasicDisassembler basicDisassembler;
    private final EntityManager entityManager;
    private List<HttpMessageConverter<?>> converters;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        this.converters = converters;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new RepresentationMethodProcessor(converters, basicDisassembler, entityManager));
    }
}
*/
