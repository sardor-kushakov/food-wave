package sarik.dev.foodwave.entitiy.admin;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sarik.dev.foodwave.entitiy.user.Dish;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ingredients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

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
