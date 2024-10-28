package io.github.jvitorc.access.service;

import io.github.jvitorc.access.dto.ProfileDTO;
import io.github.jvitorc.access.exception.NotFoundException;
import io.github.jvitorc.access.model.Profile;
import io.github.jvitorc.access.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProfileService {

    private ProfileRepository profileRepository;

    public Page<Profile> findAll(PageRequest pageable) {
        return profileRepository.findAll(pageable);
    }

    public ProfileDTO findById(Integer id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        List<Integer> roles = profileRepository.findProfileIdsByAccountId(id);

        return ProfileDTO.builder()
                .id(profile.getId())
                .name(profile.getName())
                .description(profile.getDescription())
                .roles(roles)
                .build();
    }
}
