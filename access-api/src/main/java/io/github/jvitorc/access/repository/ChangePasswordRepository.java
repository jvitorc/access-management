package io.github.jvitorc.access.repository;

import io.github.jvitorc.access.model.ChangePassword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChangePasswordRepository  extends JpaRepository<ChangePassword, Integer> {


    Optional<ChangePassword> findByUrlSecret(String urlSecret);
}
