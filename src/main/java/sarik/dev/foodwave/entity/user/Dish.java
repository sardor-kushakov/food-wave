package sarik.dev.foodwave.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import sarik.dev.foodwave.dto.dish.DishDTO;
import sarik.dev.foodwave.entity.admin.Category;
import sarik.dev.foodwave.entity.admin.Ingredient;
import sarik.dev.foodwave.enums.user.DishType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "dishes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE dishes SET is_deleted = true WHERE id = ?")
@Filter(name = "deletedFilter", condition = "is_deleted = :isDeleted")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String name;

    @Lob
    private String description;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "image_url")
    private String imageUrl;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DishType dishType;

    @Column(name = "is_available", nullable = false)
    private boolean isAvailable = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Relationships

    /**
     * -- SETTER --
     * Sets the category for the dish.
     */
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "dish_ingredients",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private Set<Ingredient> ingredients = new HashSet<>();

    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();

    // Methods

    /**
     * Converts a DishDTO to a Dish entity.
     *
     * @param dishDTO the DishDTO to convert
     * @return the converted Dish entity
     */
    public static Dish fromDTO(DishDTO dishDTO) {
        if (dishDTO == null) {
            return null; // or throw an exception if necessary
        }

        return Dish.builder()
                .id(dishDTO.getId())
                .name(dishDTO.getName())
                .description(dishDTO.getDescription())
                .price(dishDTO.getPrice())
                .imageUrl(dishDTO.getImageUrl())
                .dishType(dishDTO.getDishType())
                .isAvailable(dishDTO.isAvailable())
                .createdAt(dishDTO.getCreatedAt())
                .updatedAt(dishDTO.getUpdatedAt())
                .build();
    }

    /**
     * Adds an ingredient to the dish.
     */
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        ingredient.getDishes().add(this);
    }

    /**
     * Removes an ingredient from the dish.
     */
    public void removeIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
        ingredient.getDishes().remove(this);
    }

    /**
     * Checks if the dish is available.
     *
     * @return true if available, false otherwise
     */
    public boolean isAvailable() {
        return isAvailable;
    }

}
