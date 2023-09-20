package com.example.rotiscnz.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cart", schema = "dinner_dash", catalog = "")
public class CartEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Long id;

    private Long userByUserId;

    //    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
////    @JsonBackReference
//    @OneToMany(mappedBy = "cartByCartId")
//    private Set<CartItemEntity> cartItemsById;
//    @OneToMany(mappedBy = "cartByCartId")
//    private Set<OrderEntity> ordersById;


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        CartEntity that = (CartEntity) o;
//        return id == that.id;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }


//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

//    public UserEntity getUserByUserId() {
//        return userByUserId;
//    }
//
//    public void setUserByUserId(UserEntity userByUserId) {
//        this.userByUserId = userByUserId;
//    }
//
//    public Set<CartItemEntity> getCartItemsById() {
//        return cartItemsById;
//    }
//
//    public void setCartItemsById(Set<CartItemEntity> cartItemsById) {
//        this.cartItemsById = cartItemsById;
//    }
//
//    public Set<OrderEntity> getOrdersById() {
//        return ordersById;
//    }
//
//    public void setOrdersById(Set<OrderEntity> ordersById) {
//        this.ordersById = ordersById;
//    }
}
