package io.github.jvitorc.access.service;

import io.github.jvitorc.access.exception.NotFoundException;
import io.github.jvitorc.access.model.Permission;
import io.github.jvitorc.access.repository.PermissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PermissionService {

    private PermissionRepository permissionRepository;

    public Page<Permission> findAll(Pageable pageable) {
        return permissionRepository.findAll(pageable);
    }

    public Permission findById(Integer id) {
        return permissionRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }
}
