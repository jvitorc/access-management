package io.github.jvitorc.access.scripts;

import io.github.jvitorc.access.model.Account;
import io.github.jvitorc.access.model.Permission;
import io.github.jvitorc.access.model.Profile;
import io.github.jvitorc.access.model.Role;
import io.github.jvitorc.access.repository.AccountRepository;
import io.github.jvitorc.access.repository.ProfileRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
    CommandLineRunner roleMigration(UserDetailsService userDetailsService) {
        return args -> {
            logger.info("Migrating roles...");
            Arrays.asList(repository.findProfileIdsByAccountId(1)).forEach(
                    p -> logger.info("Profile: " + p)
            );
            logger.info("Migrating end...");

        };
    }
}
