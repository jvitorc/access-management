package io.github.jvitorc.access.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProfileDTO {

    private Integer id;
    private String name;
    private String description;
    private List<Integer> rules;

}
