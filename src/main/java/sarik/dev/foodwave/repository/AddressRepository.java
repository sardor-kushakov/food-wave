package sarik.dev.foodwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwave.entity.user.Address;
import sarik.dev.foodwave.entity.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {

    List<Address> findByUser(User user);

    Optional<Address> findByUserAndIsPrimary(User user, boolean isPrimary);

    List<Address> findByCityAndIsDeleted(String city, boolean isDeleted);
}
