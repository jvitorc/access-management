package io.github.jvitorc.access.service;

import io.github.jvitorc.access.exception.NotFoundException;
import io.github.jvitorc.access.model.Role;
import io.github.jvitorc.access.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoleService {

    private RoleRepository roleRepository;

    public Page<Role> findAll(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    public Role findById(Integer id) {
        return roleRepository.findById(id).
                orElseThrow(NotFoundException::new);
    }
}
