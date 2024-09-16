package io.github.jvitorc.access.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthResponse(
        @JsonProperty("access_token") String accessToken,
        @JsonProperty("name") String name,
        @JsonProperty("email") String email,
        @JsonProperty("refresh_token") String refreshToken
) {
}
