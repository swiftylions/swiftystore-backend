package com.swiftylions.SLStore.repository;

import com.swiftylions.SLStore.entity.Product;
import com.swiftylions.SLStore.entity.Role;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Cacheable("roles")
    // ROLE_USER -> CACHE MISS -> DB CALL -> CACHE STORE (ROLE_USER -> Role record) -> Customer 1
    // ROLE_USER -> CACHE HIT -> Customer 2
    // ROLE_ADMIN -> CACHE MISS -> DB CALL -> CACHE STORE (ROLE_ADMIN -> Role record) -> Customer x
    Optional<Role> findByName(String name);

}