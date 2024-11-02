package sarik.dev.foodwave.entity.admin;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import sarik.dev.foodwave.entity.user.Dish;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "ingredients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "ingredient_name", nullable = false, unique = true)
    private String ingredientName;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false)
    private String unit; // Example: "kg", "grams", "liters", "pieces"

    // Relationships

    @ManyToMany(mappedBy = "ingredients", fetch = FetchType.LAZY)
    private Set<Dish> dishes = new HashSet<>();

    // Methods

    /**
     * Adds this ingredient to a dish.
     */
    public void addDish(Dish dish) {
        dishes.add(dish);
        dish.getIngredients().add(this);
    }

    /**
     * Removes this ingredient from a dish.
     */
    public void removeDish(Dish dish) {
        dishes.remove(dish);
        dish.getIngredients().remove(this);
    }
}
