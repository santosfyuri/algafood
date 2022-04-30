package br.santosfyuri.algaworks.algafood.domain.service;

import br.santosfyuri.algaworks.algafood.domain.exception.PermissionNotFoundException;
import br.santosfyuri.algaworks.algafood.domain.model.Permission;
import br.santosfyuri.algaworks.algafood.domain.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public Permission findOrNull(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new PermissionNotFoundException(id));
    }
}
