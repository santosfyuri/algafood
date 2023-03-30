package br.santosfyuri.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public final class BasicAssembler {

    private static final Map<Class<?>, GenericAssembler<?, ?>> CACHE = new ConcurrentHashMap<>();

    @Autowired
    private ModelMapper modelMapper;

    @SuppressWarnings("unchecked")
    public <E, R extends RepresentationModel<R>> GenericAssembler<E, R> get(AssemblerParameters<R> assemblerParameters) {
        if (CACHE.containsKey(assemblerParameters.getEntityType())) {
            return (GenericAssembler<E, R>) CACHE.get(assemblerParameters.getEntityType());
        }

        GenericAssembler<E, R> assembler = new GenericAssembler<>(modelMapper, assemblerParameters);

        CACHE.put(assemblerParameters.getEntityType(), assembler);

        return assembler;
    }

    public static class GenericAssembler<E, R extends RepresentationModel<R>>
            extends RepresentationModelAssemblerSupport<E, R> implements IAssembler<E, R> {

        private final ModelMapper modelMapper;
        private final AssemblerParameters<R> assemblerParameters;

        public GenericAssembler(ModelMapper modelMapper, AssemblerParameters<R> assemblerParameters) {
            super(assemblerParameters.getControllerType(), assemblerParameters.getEntityType());
            this.modelMapper = modelMapper;
            this.assemblerParameters = assemblerParameters;
        }


        @Override
        @Nonnull
        public R toModel(@Nonnull E entity) {
            R entityResponse = modelMapper.map(entity, this.assemblerParameters.getEntityType());
            if (Objects.nonNull(this.assemblerParameters.getConsumer())) {
                this.assemblerParameters.getConsumer().accept(entityResponse);
            }
            return entityResponse;
        }

        @Override
        @Nonnull
        public CollectionModel<R> toCollectionModel(@Nonnull Iterable<? extends E> entities) {
            return super.toCollectionModel(entities)
                    .add(WebMvcLinkBuilder.linkTo(this.assemblerParameters.getControllerType()).withSelfRel());
        }
    }
}
