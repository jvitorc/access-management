package io.github.jvitorc.access.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;

import static java.util.Objects.isNull;

public class RequestUtil {

    public static String extractBearerToken(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isNull(authHeader) || !authHeader.startsWith("Bearer ")) {
            return "";
        }
        return authHeader.substring(7);
    }
}
