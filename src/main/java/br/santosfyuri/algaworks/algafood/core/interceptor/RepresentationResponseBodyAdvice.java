package br.santosfyuri.algaworks.algafood.core.interceptor;

import br.santosfyuri.algaworks.algafood.api.assembler.BasicAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestControllerAdvice
@RequiredArgsConstructor
public class RepresentationResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private final BasicAssembler basicAssembler;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return Objects.nonNull(this.getAnnotation(returnType, Representation.class));
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        Representation representation = (Representation) this.getAnnotation(returnType, Representation.class);
        if (body instanceof List) {
            return basicAssembler.get(representation.to()).entityToRepresentation(new ArrayList<>(((List<?>) body)));
        }
        return basicAssembler.get(representation.to()).entityToRepresentation(body);
    }

    private <A extends Annotation> Annotation getAnnotation(MethodParameter parameter, Class<A> annotationType) {
        return Optional.ofNullable(parameter.getMethodAnnotation(annotationType))
                .orElseGet(() -> parameter.getContainingClass().getAnnotation(annotationType));
    }
}
