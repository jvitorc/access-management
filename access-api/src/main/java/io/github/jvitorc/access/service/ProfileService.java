package io.github.jvitorc.access.service;

import io.github.jvitorc.access.model.Profile;
import io.github.jvitorc.access.model.Role;
import io.github.jvitorc.access.repository.ProfileRepository;
import io.github.jvitorc.access.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProfileService {

    private ProfileRepository profileRepository;

    public List<Profile> findAll() {
        return profileRepository.findAll();
    }
}
