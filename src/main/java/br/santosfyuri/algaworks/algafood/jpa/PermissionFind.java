package br.santosfyuri.algaworks.algafood.jpa;

import br.santosfyuri.algaworks.algafood.AlgaFoodApplication;
import br.santosfyuri.algaworks.algafood.domain.model.Permission;
import br.santosfyuri.algaworks.algafood.domain.repository.PermissionRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class PermissionFind {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgaFoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        PermissionRepository permissionRepository = applicationContext.getBean(PermissionRepository.class);
        List<Permission> permissions = permissionRepository.list();
        permissions.forEach(r -> {
            System.out.printf("%d - %s\n",
                    r.getId(), r.getName());
        });
    }
}
