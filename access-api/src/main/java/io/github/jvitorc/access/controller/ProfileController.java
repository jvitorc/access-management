package io.github.jvitorc.access.controller;

import io.github.jvitorc.access.dto.ProfileDTO;
import io.github.jvitorc.access.model.Profile;
import io.github.jvitorc.access.service.ProfileService;
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
@RequestMapping("/api/v1/profile")
public class ProfileController {

    private ProfileService profileService;

    @GetMapping
    public ResponseEntity<Page<Profile>> findAll(@PathParam("pageNumber") Integer pageNumber) {
        return ResponseEntity.ok(profileService.findAll(pageRequestBuild(pageNumber)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(profileService.findById(id));
    }

}
