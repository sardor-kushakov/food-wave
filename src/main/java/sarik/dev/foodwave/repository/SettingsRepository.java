package sarik.dev.foodwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwave.entity.admin.Settings;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, UUID> {

    Optional<Settings> findByKey(String key);
}
