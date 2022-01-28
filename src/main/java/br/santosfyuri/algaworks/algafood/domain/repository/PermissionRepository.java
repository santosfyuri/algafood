package br.santosfyuri.algaworks.algafood.domain.repository;

import br.santosfyuri.algaworks.algafood.domain.model.Permission;

import java.util.List;

public interface PermissionRepository {

    List<Permission> list();

    Permission find(Long id);

    Permission save(Permission permission);

    void delete(Permission permission);
}
