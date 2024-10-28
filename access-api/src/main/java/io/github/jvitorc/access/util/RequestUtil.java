package io.github.jvitorc.access.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;

import static java.util.Objects.isNull;

public class RequestUtil {

    private static final int PAGE_SIZE = 10;
    private static final Sort SORT_ID = Sort.by("id");


    public static String extractBearerToken(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isNull(authHeader) || !authHeader.startsWith("Bearer ")) {
            return "";
        }
        return authHeader.substring(7);
    }

    public static PageRequest pageRequestBuild(Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = 0;
        }
        return PageRequest.of(pageNumber, PAGE_SIZE, SORT_ID);
    }
}
