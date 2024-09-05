package io.github.jvitorc.access.repository;

import io.github.jvitorc.access.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
}
