package sarik.dev.foodwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwave.entity.auth.AuthPermission;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthPermissionRepository extends JpaRepository<AuthPermission, UUID> {

    Optional<AuthPermission> findByName(String name);
}
