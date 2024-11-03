package io.github.jvitorc.access.service;

import io.github.jvitorc.access.exception.NotFoundException;
import io.github.jvitorc.access.model.Rule;
import io.github.jvitorc.access.repository.RuleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RuleService {

    private RuleRepository ruleRepository;

    public Page<Rule> findAll(Pageable pageable) {
        return ruleRepository.findAll(pageable);
    }

    public Rule findById(Integer id) {
        return ruleRepository.findById(id).
                orElseThrow(NotFoundException::new);
    }
}
