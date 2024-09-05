package io.github.jvitorc.access.scripts;

import io.github.jvitorc.access.model.Account;
import io.github.jvitorc.access.model.Permission;
import io.github.jvitorc.access.model.Profile;
import io.github.jvitorc.access.model.Role;
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
    EntityManagerFactory entityManagerFactory;

    @Bean
    @Transactional
    CommandLineRunner roleMigration(UserDetailsService userDetailsService) {
        return args -> {

            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin(); // Begin transaction

            List<Permission> permissions = Arrays.asList(
                    Permission.builder().id(1).name("create").build(),
                    Permission.builder().id(2).name("read").build(),
                    Permission.builder().id(3).name("update").build(),
                    Permission.builder().id(4).name("delete").build()
            );
            permissions.forEach(entityManager::persist);

            List<Role> roles = Arrays.asList(
                    Role.builder().id(1).name("feat-one").description("feature one").permissions(permissions).build(),
                    Role.builder().id(2).name("feat-two").description("feature two").permissions(permissions).build()
            );
            roles.forEach(entityManager::persist);

            List<Profile> profiles = Arrays.asList(
                    Profile.builder().id(1).name("default").description("default profile").roles(Collections.singletonList(roles.getFirst())).build(),
                    Profile.builder().id(2).name("admin").description("admin profile").roles(roles).build()
            );
            profiles.forEach(entityManager::persist);

            Account accountDefault = Account.builder().name("default").email("default@email.com")
                    .password(passwordEncoder.encode("password")).profile(profiles.getFirst())
                    .build();

            Account admin = Account.builder().name("admin").email("admin@email.com")
                    .password(passwordEncoder.encode("password")).profile(profiles.getLast())
                    .build();

            entityManager.persist(accountDefault);
            entityManager.persist(admin);

            entityManager.getTransaction().commit(); // Commit transaction
        };
    }


}
