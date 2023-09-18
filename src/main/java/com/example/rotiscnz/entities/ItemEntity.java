package com.example.rotiscnz.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item", schema = "dinner_dash", catalog = "")
public class ItemEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "item_photo")
    private String itemPhoto;
    @Basic
    @Column(name = "price")
    private double price;
    @OneToMany(mappedBy = "itemByItemId")
    private Set<CartItemEntity> cartItemsById;
    @Basic
    @Column(name = "category_id")
    private String categoryID;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getItemPhoto() {
//        return itemPhoto;
//    }
//
//    public void setItemPhoto(String itemPhoto) {
//        this.itemPhoto = itemPhoto;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//
//    public Set<CartItemEntity> getCartItemsById() {
//        return cartItemsById;
//    }
//
//    public void setCartItemsById(Set<CartItemEntity> cartItemsById) {
//        this.cartItemsById = cartItemsById;
//    }
//

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemEntity that = (ItemEntity) o;
        return id == that.id && Double.compare(price, that.price) == 0 && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(itemPhoto, that.itemPhoto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, itemPhoto, price);
    }
}
