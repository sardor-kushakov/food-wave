package sarik.dev.foodwave.entity.admin;

import jakarta.persistence.*;
import lombok.*;
import sarik.dev.foodwave.entity.user.Dish;

import java.math.BigDecimal;

@Entity
@Table(name = "menu_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dish_id", nullable = false)
    private Dish dish;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    // Methods
    public BigDecimal getPrice() {
        return price != null ? price : dish.getPrice();
    }
}
