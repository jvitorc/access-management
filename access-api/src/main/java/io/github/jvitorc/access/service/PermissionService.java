package io.github.jvitorc.access.service;

import io.github.jvitorc.access.model.Permission;
import io.github.jvitorc.access.model.Profile;
import io.github.jvitorc.access.repository.PermissionRepository;
import io.github.jvitorc.access.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PermissionService {

    private PermissionRepository permissionRepository;

    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }
}
