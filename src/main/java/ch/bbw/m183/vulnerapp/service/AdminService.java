package ch.bbw.m183.vulnerapp.service;

import ch.bbw.m183.vulnerapp.datamodel.Role;
import ch.bbw.m183.vulnerapp.datamodel.UserEntity;
import ch.bbw.m183.vulnerapp.datamodel.UserRole;
import ch.bbw.m183.vulnerapp.repository.RoleRepository;
import ch.bbw.m183.vulnerapp.repository.UserRepository;
import ch.bbw.m183.vulnerapp.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public UserEntity createUser(UserEntity newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        log.info("new Password {}", newUser.getPassword());
        return userRepository.save(newUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Page<UserEntity> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    @PreAuthorize("hasRole('ADMIN')")
    private void createUserWithRole(UserEntity userEntity) {
        UserEntity user = createUser(userEntity);
        if (user.getUsername().equals("admin")) {
            Role adminRole = roleRepository.findByName("ADMIN");
            if (adminRole != null) {
                UserRole userRole = new UserRole();
                userRole.setUser(user);
                userRole.setRole(adminRole);
                userRoleRepository.save(userRole);
            }
        } else {
            Role role = roleRepository.findByName("USER");
            if (role != null) {
                UserRole userRole = new UserRole();
                userRole.setUser(user);
                userRole.setRole(role);
                userRoleRepository.save(userRole);
            }
        }
    }

    public void createRole(Role role) {
        roleRepository.save(role);
    }

    @EventListener(ContextRefreshedEvent.class)
    public void loadTestData() {
        Stream.of(
                new Role()
                        .setName("ADMIN"),
                new Role()
                        .setName("USER")
        ).forEach(this::createRole);
        Stream.of(
                new UserEntity()
                        .setUsername("admin")
                        .setFullname("Super Admin")
                        .setPassword("super5ecret"),
                new UserEntity()
                        .setUsername("fuu")
                        .setFullname("Johanna Doe")
                        .setPassword("bar")
        ).forEach(this::createUserWithRole);
    }
}
