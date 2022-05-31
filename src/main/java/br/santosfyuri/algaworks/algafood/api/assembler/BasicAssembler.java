package br.santosfyuri.algaworks.algafood.api.assembler;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public final class BasicAssembler {

    private static final Map<Class<?>, GenericAssembler<?, ?>> CACHE = new ConcurrentHashMap<>();

    @Autowired
    private ModelMapper modelMapper;

    @SuppressWarnings("unchecked")
    public <E, R> GenericAssembler<E, R> get(Class<R> entityType) {
        if (CACHE.containsKey(entityType)) {
            return (GenericAssembler<E, R>) CACHE.get(entityType);
        }

        GenericAssembler<E, R> assembler = new GenericAssembler<>(modelMapper, entityType);

        CACHE.put(entityType, assembler);

        return assembler;
    }

    @AllArgsConstructor
    public static class GenericAssembler<E, R> implements IAssembler<E, R> {

        private final ModelMapper modelMapper;
        private final Class<R> entityType;

        @Override
        public R entityToRepresentation(E entity) {
            return modelMapper.map(entity, this.entityType);
        }

        @Override
        public List<R> entityToRepresentation(Collection<E> entities) {
            return entities.stream()
                    .map(this::entityToRepresentation)
                    .collect(Collectors.toList());
        }
    }
}
