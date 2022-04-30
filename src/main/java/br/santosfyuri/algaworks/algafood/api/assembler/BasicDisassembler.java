package br.santosfyuri.algaworks.algafood.api.assembler;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public final class BasicDisassembler {

    private static final Map<Class<?>, GenericDisassembler<?, ?>> CACHE = new ConcurrentHashMap<>();

    @Autowired
    private ModelMapper modelMapper;

    @SuppressWarnings("unchecked")
    public <E, R> GenericDisassembler<E, R> get(Class<E> entityType) {
        if (Objects.nonNull(entityType) && CACHE.containsKey(entityType)) {
            return (GenericDisassembler<E, R>) CACHE.get(entityType);
        }

        GenericDisassembler<E, R> disassembler = new GenericDisassembler<>(modelMapper, entityType);

        CACHE.put(entityType, disassembler);

        return disassembler;
    }

    @AllArgsConstructor
    public static class GenericDisassembler<E, R> implements IDisassembler<E, R> {

        private final ModelMapper modelMapper;
        private final Class<E> entityType;

        @Override
        public E representationToEntity(R representation) {
            Objects.requireNonNull(this.entityType);
            return modelMapper.map(representation, this.entityType);
        }

        @Override
        public List<E> representationToEntity(Collection<R> representations) {
            return representations.stream()
                    .map(this::representationToEntity)
                    .collect(Collectors.toList());
        }

        @Override
        public void copyToEntity(R representation, E entity) {
            modelMapper.map(representation, entity);
        }
    }
}
