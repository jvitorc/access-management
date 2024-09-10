package io.github.jvitorc.access.controller;

import io.github.jvitorc.access.model.Permission;
import io.github.jvitorc.access.model.Role;
import io.github.jvitorc.access.service.PermissionService;
import io.github.jvitorc.access.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/permission")
public class PermissionController {

    private PermissionService permissionService;

    @GetMapping
    public ResponseEntity<List<Permission>> findAll() {
        return ResponseEntity.ok(permissionService.findAll());
    }


}
