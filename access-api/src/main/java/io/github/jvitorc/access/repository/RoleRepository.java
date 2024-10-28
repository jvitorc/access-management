package io.github.jvitorc.access.repository;

import io.github.jvitorc.access.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface RoleRepository extends PagingAndSortingRepository<Role, Integer> {

    Optional<Role> findById(Integer id);

}
