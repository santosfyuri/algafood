package br.santosfyuri.algaworks.algafood.infrastructure.repository;

import br.santosfyuri.algaworks.algafood.domain.model.State;
import br.santosfyuri.algaworks.algafood.domain.repository.StateRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class StateRepositoryImpl implements StateRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<State> list() {
        return manager.createQuery("from State", State.class).getResultList();
    }

    @Override
    public State find(Long id) {
        return manager.find(State.class, id);
    }

    @Transactional
    @Override
    public State save(State state) {
        return manager.merge(state);
    }

    @Transactional
    @Override
    public void delete(State state) {
        state = find(state.getId());
        manager.remove(state);
    }
}
