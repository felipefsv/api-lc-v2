package com.lc.apilc.repositories;

import com.lc.apilc.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByName(String name);
    boolean existsByNameAndLogin(String name, String login);
    boolean existsByLogin(String name);
    User findByLogin(String login);

    Optional<User> findById(UUID id);

    Set<User> findByDepartmentId(UUID departmentId);
}
