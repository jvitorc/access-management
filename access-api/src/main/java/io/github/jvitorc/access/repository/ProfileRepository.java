package io.github.jvitorc.access.repository;

import io.github.jvitorc.access.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, String> {

}
