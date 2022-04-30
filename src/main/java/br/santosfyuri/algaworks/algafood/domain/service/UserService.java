package br.santosfyuri.algaworks.algafood.domain.service;

import br.santosfyuri.algaworks.algafood.domain.exception.BusinessException;
import br.santosfyuri.algaworks.algafood.domain.exception.EntityInUseException;
import br.santosfyuri.algaworks.algafood.domain.exception.StateNotFoundException;
import br.santosfyuri.algaworks.algafood.domain.exception.UserNotFoundException;
import br.santosfyuri.algaworks.algafood.domain.model.Group;
import br.santosfyuri.algaworks.algafood.domain.model.User;
import br.santosfyuri.algaworks.algafood.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private static final String MESSAGE_USER_IN_USE = "Usuário de código %d não pode ser removido, pois está em uso.";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupService groupService;

    @Transactional
    public User save(User user) {
        if (userRepository.existsByEmailAndIdNot(user.getEmail(), user.getId())) {
            throw new BusinessException(String.format("Já existe um usuário cadastrado com o e-mail %s", user.getEmail()));
        }
        return userRepository.save(user);
    }

    @Transactional
    public void updatePassword(Long userId, String currentPassword, String newPassword) {
        User user = findOrNull(userId);
        if (user.differentPassword(currentPassword)) {
            throw new BusinessException("Senha atual não coincide com a senha do usuário.");
        }
        user.toBuilder()
                .password(newPassword)
                .build();
    }

    public User findOrNull(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
            userRepository.flush();
        } catch (EmptyResultDataAccessException exception) {
            throw new StateNotFoundException(id);
        } catch (DataIntegrityViolationException exception) {
            throw new EntityInUseException(String.format(MESSAGE_USER_IN_USE, id));
        }
    }

    @Transactional
    public void disassociateGroup(Long userId, Long groupId) {
        User user = findOrNull(userId);
        Group group = groupService.findOrNull(groupId);

        user.removeGroup(group);
    }

    @Transactional
    public void asassociateGroup(Long userId, Long groupId) {
        User user = findOrNull(userId);
        Group group = groupService.findOrNull(groupId);

        user.addGroup(group);
    }
}
