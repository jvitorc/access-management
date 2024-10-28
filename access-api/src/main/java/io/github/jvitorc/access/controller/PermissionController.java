package io.github.jvitorc.access.controller;

import io.github.jvitorc.access.model.Permission;
import io.github.jvitorc.access.service.PermissionService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static io.github.jvitorc.access.util.RequestUtil.pageRequestBuild;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/permission")
public class PermissionController {

    private PermissionService permissionService;

    @GetMapping
    public ResponseEntity<Page<Permission>> findAll(@PathParam("pageNumber") Integer pageNumber) {
        return ResponseEntity.ok(permissionService.findAll(pageRequestBuild(pageNumber)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permission> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(permissionService.findById(id));
    }

}
