/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akhadam.kitabi.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 * @author AKH
 */

@Table(name = "wishlists")
@Entity
public class WishlistEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String wishlistId;

    @OneToMany(mappedBy = "wishlist", cascade = CascadeType.ALL)
    private List<WishlistItemsEntity> wishlistItems;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(String wishlistId) {
        this.wishlistId = wishlistId;
    }

    public List<WishlistItemsEntity> getWishlistItems() {
        return wishlistItems;
    }

    public void setWishlistItems(List<WishlistItemsEntity> wishlistItems) {
        this.wishlistItems = wishlistItems;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WishlistEntity)) {
            return false;
        }
        WishlistEntity other = (WishlistEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.akhadam.kitabi.entity.WishlistEntity[ id=" + id + " ]";
    }

}
