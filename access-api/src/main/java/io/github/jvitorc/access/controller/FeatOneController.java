package io.github.jvitorc.access.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feat-one")
public class FeatOneController {


    @GetMapping
    @PreAuthorize("hasAuthority('feat-one::read')")
    public ResponseEntity<String> read() {
        return ResponseEntity.ok("feat-one read");
    }

    @PutMapping
    @PreAuthorize("hasAuthority('feat-one::update')")
    public ResponseEntity<String> update() {
        return ResponseEntity.ok("feat-one update");
    }

    @PostMapping
    @PreAuthorize("hasAuthority('feat-one::create')")
    public ResponseEntity<String> create() {
        return ResponseEntity.ok("feat-one create");
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('feat-one::delete')")
    public ResponseEntity<String> delete() {
        return ResponseEntity.ok("feat-one delete");
    }



}

