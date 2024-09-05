package io.github.jvitorc.access.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feat-two")
public class FeatTwoController {


    @GetMapping
    @PreAuthorize("hasAuthority('feat-two::read')")
    public ResponseEntity<String> read() {
        return ResponseEntity.ok("feat-two read");
    }

    @PutMapping
    @PreAuthorize("hasAuthority('feat-two::update')")
    public ResponseEntity<String> update() {
        return ResponseEntity.ok("feat-two update");
    }

    @PostMapping
    @PreAuthorize("hasAuthority('feat-two::create')")
    public ResponseEntity<String> create() {
        return ResponseEntity.ok("feat-two create");
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('feat-two::delete')")
    public ResponseEntity<String> delete() {
        return ResponseEntity.ok("feat-two delete");
    }

}

