package io.github.jvitorc.access.controller;

import io.github.jvitorc.access.model.Role;
import io.github.jvitorc.access.service.RoleService;
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
@RequestMapping("/api/v1/role")
public class RoleController {

    private RoleService roleService;

    @GetMapping
    public ResponseEntity<Page<Role>> findAll(@PathParam("pageNumber") Integer pageNumber) {
        return ResponseEntity.ok(roleService.findAll(pageRequestBuild(pageNumber)));
    }

    @GetMapping("{id}")
    public ResponseEntity<Role> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(roleService.findById(id));
    }

}
