package com.lg.electronic_store.entity.shoppingCart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lg.electronic_store.entity.shoppingCartItem.ShoppingCartItem;
import com.lg.electronic_store.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "shopping_carts")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderDescription;

    private BigDecimal totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<ShoppingCartItem> cartItems = new ArrayList<>();

    public void addCartItem(ShoppingCartItem item) {
        cartItems.add(item);
        item.setCart(this);
    }
}

/*
In a bidirectional relationship between two entities, one side is considered the owning side, and the other is the
inverse side. The mappedBy attribute is always specified on the inverse (non-owning) side of the relationship.

Here's a general rule of thumb:
In a @OneToMany/@ManyToOne relationship:
The @ManyToOne side is typically the owning side.
The @OneToMany side is the inverse side and should have the mappedBy attribute.

FetchType can be specified on both sides, depending on your needs.
 */
/*
orphanRemoval = true:
This feature ensures that when a ShoppingCartItem is removed from the cartItems list, it will be deleted from the
database. This is useful for maintaining referential integrity.
 */

