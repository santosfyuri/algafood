package br.santosfyuri.algaworks.algafood.domain.service;

import br.santosfyuri.algaworks.algafood.domain.exception.EntityInUseException;
import br.santosfyuri.algaworks.algafood.domain.exception.GroupNotFoundException;
import br.santosfyuri.algaworks.algafood.domain.exception.StateNotFoundException;
import br.santosfyuri.algaworks.algafood.domain.model.Group;
import br.santosfyuri.algaworks.algafood.domain.model.Permission;
import br.santosfyuri.algaworks.algafood.domain.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GroupService {

    private static final String MESSAGE_GROUP_IN_USE = "Grupo de código %d não pode ser removido, pois está em uso.";

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PermissionService permissionService;

    @Transactional
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    public Group findOrNull(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException(id));
    }

    @Transactional
    public void delete(Long id) {
        try {
            groupRepository.deleteById(id);
            groupRepository.flush();
        } catch (EmptyResultDataAccessException exception) {
            throw new StateNotFoundException(id);
        } catch (DataIntegrityViolationException exception) {
            throw new EntityInUseException(String.format(MESSAGE_GROUP_IN_USE, id));
        }
    }

    @Transactional
    public void disassociatePermission(Long groupId, Long permissionId) {
        Group group = findOrNull(groupId);
        Permission permission = permissionService.findOrNull(permissionId);

        group.removePermission(permission);
    }

    @Transactional
    public void asassociatePermission(Long groupId, Long permissionId) {
        Group group = findOrNull(groupId);
        Permission permission = permissionService.findOrNull(permissionId);

        group.addPermission(permission);
    }
}
