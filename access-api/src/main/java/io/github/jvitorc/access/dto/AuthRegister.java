package io.github.jvitorc.access.dto;

import io.github.jvitorc.access.validator.HasDigit;
import io.github.jvitorc.access.validator.HasLowerCase;
import io.github.jvitorc.access.validator.HasSpecialCharacter;
import io.github.jvitorc.access.validator.HasUpperCase;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRegister {

    @NotBlank
    @Size(min=3)
    private String name;
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @HasLowerCase(min=1)
    @HasSpecialCharacter(min=1)
    @HasUpperCase(min=1)
    @HasDigit(min=1)
    @Size(min=8, max=16)
    private String password;
}
