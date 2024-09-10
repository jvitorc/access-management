package io.github.jvitorc.access.controller;

import io.github.jvitorc.access.model.Profile;
import io.github.jvitorc.access.model.Role;
import io.github.jvitorc.access.service.ProfileService;
import io.github.jvitorc.access.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    private ProfileService profileService;

    @GetMapping
    public ResponseEntity<List<Profile>> findAll() {
        return ResponseEntity.ok(profileService.findAll());
    }


}
