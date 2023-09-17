package com.example.rotiscnz.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order", schema = "dinner_dash", catalog = "")
public class OrderEntity {
    @Basic
    @Column(name = "order_time")
    private Timestamp orderTime;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "status")
    private String status;
    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id", nullable = false)
    private CartEntity cartByCartId;

//    public Timestamp getOrderTime() {
//        return orderTime;
//    }
//
//    public void setOrderTime(Timestamp orderTime) {
//        this.orderTime = orderTime;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//public CartEntity getCartByCartId() {
//        return cartByCartId;
//    }
//
//    public void setCartByCartId(CartEntity cartByCartId) {
//        this.cartByCartId = cartByCartId;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return id == that.id && Objects.equals(orderTime, that.orderTime) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderTime, id, status);
    }
}
