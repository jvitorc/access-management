package io.github.jvitorc.access.controller;

import io.github.jvitorc.access.model.Rule;
import io.github.jvitorc.access.service.RuleService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static io.github.jvitorc.access.util.RequestUtil.pageRequestBuild;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/rule")
public class RuleController {

    private RuleService ruleService;

    @GetMapping
    public ResponseEntity<Page<Rule>> findAll(@PathParam("pageNumber") Integer pageNumber) {
        return ResponseEntity.ok(ruleService.findAll(pageRequestBuild(pageNumber)));
    }

    @GetMapping("{id}")
    public ResponseEntity<Rule> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(ruleService.findById(id));
    }

}
