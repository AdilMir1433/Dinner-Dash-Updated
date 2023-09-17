package com.example.rotiscnz.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cart_item", schema = "dinner_dash", catalog = "")
public class CartItemEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private CartEntity cartByCartId;
    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private ItemEntity itemByItemId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItemEntity that = (CartItemEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

//    public CartEntity getCartByCartId() {
//        return cartByCartId;
//    }
//
//    public void setCartByCartId(CartEntity cartByCartId) {
//        this.cartByCartId = cartByCartId;
//    }
//
//    public ItemEntity getItemByItemId() {
//        return itemByItemId;
//    }
//
//    public void setItemByItemId(ItemEntity itemByItemId) {
//        this.itemByItemId = itemByItemId;
//    }
}
