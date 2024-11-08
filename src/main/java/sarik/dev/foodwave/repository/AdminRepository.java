package sarik.dev.foodwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwave.entity.admin.Admin;
import sarik.dev.foodwave.enums.admin.AdminRole;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID> {

    Optional<Admin> findByEmail(String email);

    List<Admin> findByRole(AdminRole role);

    List<Admin> findByIsActive(boolean isActive);
}
