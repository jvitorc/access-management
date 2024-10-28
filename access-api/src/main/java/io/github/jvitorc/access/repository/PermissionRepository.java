package io.github.jvitorc.access.repository;

import io.github.jvitorc.access.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PermissionRepository extends PagingAndSortingRepository<Permission, Integer> {
    Optional<Permission> findById(Integer id);
}
