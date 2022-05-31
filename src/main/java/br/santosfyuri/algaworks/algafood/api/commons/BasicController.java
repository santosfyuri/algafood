package br.santosfyuri.algaworks.algafood.api.commons;

import br.santosfyuri.algaworks.algafood.domain.exception.BusinessException;
import br.santosfyuri.algaworks.algafood.domain.exception.EntityNotFoundException;
import br.santosfyuri.algaworks.algafood.domain.persistence.BasicRepository;
import br.santosfyuri.algaworks.algafood.domain.persistence.Identity;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BasicController<T extends Identity<Long>> {

    private final BasicRepository<T> repository;

    @SuppressWarnings("unchecked")
    protected BasicController(BasicRepository<T> repository) {
        this.repository = repository;
    }

    @GetMapping
    protected List<T> list() {
        return repository.find();
    }

    @GetMapping(path = "{id}")
    protected T find(@PathVariable Long id) {
        return repository.find(id).orElseThrow(() -> new EntityNotFoundException("Entity not found."));
    }

    @PostMapping
    @Transactional
    protected T save(T entity) {
        try {
            return repository.save(entity);
        } catch (EntityNotFoundException exception) {
            throw new BusinessException(exception.getMessage(), exception);
        }
    }

    @PutMapping(path = "{id}")
    protected T update(T entity) {
        try {
            return repository.save(entity);
        } catch (EntityNotFoundException exception) {
            throw new BusinessException(exception.getMessage(), exception);
        }
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    protected void delete(@PathVariable Long id) {
        repository.remove(id);
    }
}
