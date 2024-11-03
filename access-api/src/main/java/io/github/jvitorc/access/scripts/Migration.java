package io.github.jvitorc.access.scripts;

import io.github.jvitorc.access.repository.ProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Component
public class Migration {

    private final Logger logger = Logger.getLogger(Migration.class.getName());

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ProfileRepository repository;

    @Bean
    @Transactional
    CommandLineRunner ruleMigration(UserDetailsService userDetailsService) {
        return args -> {
            logger.info("Migrating rules...");
            Arrays.asList(repository.findProfileIdsByAccountId(1)).forEach(
                    p -> logger.info("Profile: " + p)
            );
            logger.info("Migrating end...");

        };
    }
}
