package sarik.dev.foodwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sarik.dev.foodwave.entity.admin.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @Query("SELECT c FROM Category c WHERE c.isDeleted = false")
    List<Category> findAllActiveCategories();

    @Query("SELECT c FROM Category c WHERE c.id = :id AND c.isDeleted = false")
    Optional<Category> findByIdAndNotDeleted(@Param("id") UUID id);
}
