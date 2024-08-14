package io.github.jvitorc.access.runner;

import io.github.jvitorc.access.jwt.JwtUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import java.util.logging.Logger;

import java.util.HashMap;

@Component
public class InitConfig {

    private final Logger logger = Logger.getLogger(InitConfig.class.getName());

    @Bean
    CommandLineRunner initTokenJwt(UserDetailsService userDetailsService) {
        return args -> {
            String admin = JwtUtil.generateToken(new HashMap<>(), userDetailsService.loadUserByUsername("admin"));
            String user = JwtUtil.generateToken(new HashMap<>(), userDetailsService.loadUserByUsername("user"));

            logger.info("Admin token: " + admin);
            logger.info("User token: " + user);
        };
    }


}
