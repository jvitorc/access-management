package io.github.jvitorc.access.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.jvitorc.access.model.Profile;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AccountDTO {
    private Integer id;
    private String name;
    private String email;
    private Profile profile;
    private List<String> authoirities;
}
