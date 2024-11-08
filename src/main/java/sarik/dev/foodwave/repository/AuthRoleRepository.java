package sarik.dev.foodwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwave.entity.auth.AuthRole;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthRoleRepository extends JpaRepository<AuthRole, UUID> {

    Optional<AuthRole> findByName(String name);
}
