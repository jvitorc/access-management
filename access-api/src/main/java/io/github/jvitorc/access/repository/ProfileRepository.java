package io.github.jvitorc.access.repository;

import io.github.jvitorc.access.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends PagingAndSortingRepository<Profile, Integer> {

    @Query("select p.id from EN_PROFILE a join a.rules p where a.id = :profileId")
    List<Integer> findProfileIdsByAccountId(@Param("profileId") Integer profileId);

    Optional<Profile> findById(Integer id);
}
