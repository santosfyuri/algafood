package br.santosfyuri.algaworks.algafood.core.interceptor;

import br.santosfyuri.algaworks.algafood.api.assembler.BasicDisassembler;
import org.springframework.core.Conventions;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class RepresentationMethodProcessor extends RequestResponseBodyMethodProcessor {

    private final EntityManager entityManager;
    private final BasicDisassembler basicDisassembler;

    public RepresentationMethodProcessor(List<HttpMessageConverter<?>> converters,
                                         BasicDisassembler basicDisassembler, EntityManager entityManager) {
        super(converters);
        this.entityManager = entityManager;
        this.basicDisassembler = basicDisassembler;
    }

    @Override
    public boolean supportsParameter(@NonNull MethodParameter parameter) {
        return this.checkRequired(parameter);
    }

    @Override
    protected void validateIfApplicable(WebDataBinder binder, @NonNull MethodParameter parameter) {
        binder.validate();
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter, ModelAndViewContainer mavContainer, @NonNull NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Object obj = this.resolveMethod(parameter, mavContainer, webRequest, binderFactory);
        Long id = (Long) getEntityId(obj);
        Class<?> parameterType = parameter.getParameterType();
        if (Objects.isNull(id)) {
            return basicDisassembler.get(parameterType).representationToEntity(obj);
        } else {
            Object persistedObject = entityManager.find(parameterType, id);
            basicDisassembler.get(null).copyToEntity(obj, persistedObject);
            return persistedObject;
        }
    }

    @Override
    protected Object readWithMessageConverters(@NonNull HttpInputMessage inputMessage, @NonNull MethodParameter parameter, @NonNull Type targetType) throws IOException, HttpMediaTypeNotSupportedException, HttpMessageNotReadableException {
        if (this.checkRequired(parameter)) {
            return super.readWithMessageConverters(inputMessage, parameter, targetType);
        }
        throw new RuntimeException();
    }

    @Override
    protected boolean checkRequired(@NonNull MethodParameter parameter) {
        return Objects.nonNull(this.getAnnotation(parameter, Representation.class));
    }

    private Object resolveMethod(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Representation representation = (Representation) this.getAnnotation(parameter, Representation.class);
        parameter = parameter.nestedIfOptional();
        Object arg = readWithMessageConverters(webRequest, parameter, representation.from());
        String name = Conventions.getVariableNameForParameter(parameter);

        if (binderFactory != null) {
            WebDataBinder binder = binderFactory.createBinder(webRequest, arg, name);
            if (arg != null) {
                validateIfApplicable(binder, parameter);
                if (binder.getBindingResult().hasErrors() && isBindExceptionRequired(binder, parameter)) {
                    throw new MethodArgumentNotValidException(parameter, binder.getBindingResult());
                }
            }
            if (mavContainer != null) {
                mavContainer.addAttribute(BindingResult.MODEL_KEY_PREFIX + name, binder.getBindingResult());
            }
        }

        return adaptArgumentIfNecessary(arg, parameter);
    }

    private <A extends Annotation> Annotation getAnnotation(MethodParameter parameter, Class<A> annotationType) {
        return Optional.ofNullable(parameter.getMethodAnnotation(annotationType))
                .orElseGet(() -> parameter.getContainingClass().getAnnotation(annotationType));

    }

    private Object getEntityId(@NonNull Object dto) {
        for (Field field : dto.getClass().getDeclaredFields()) {
            if (field.getAnnotation(Id.class) != null) {
                try {
                    field.setAccessible(true);
                    return field.get(dto);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }
}
