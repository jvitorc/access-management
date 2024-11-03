package io.github.jvitorc.access.repository;

import io.github.jvitorc.access.model.Rule;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface RuleRepository extends PagingAndSortingRepository<Rule, Integer> {

    Optional<Rule> findById(Integer id);

}
