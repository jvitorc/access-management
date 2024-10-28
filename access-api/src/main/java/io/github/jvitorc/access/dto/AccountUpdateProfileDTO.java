package io.github.jvitorc.access.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountUpdateProfileDTO {
    @NotNull
    private Integer id;

    @NotNull
    private Integer profileId;
}
