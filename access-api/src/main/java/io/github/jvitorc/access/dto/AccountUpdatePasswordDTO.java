package io.github.jvitorc.access.dto;


import io.github.jvitorc.access.validator.HasDigit;
import io.github.jvitorc.access.validator.HasLowerCase;
import io.github.jvitorc.access.validator.HasSpecialCharacter;
import io.github.jvitorc.access.validator.HasUpperCase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountUpdatePasswordDTO {

    @NotBlank
    @HasLowerCase(min=1)
    @HasSpecialCharacter(min=1)
    @HasUpperCase(min=1)
    @HasDigit(min=1)
    @Size(min=8, max=16)
    private String password;

    @NotBlank
    private String confirmPassword;
}
