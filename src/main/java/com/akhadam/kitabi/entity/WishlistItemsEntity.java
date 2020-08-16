package com.akhadam.kitabi.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "wishlistItems")
@Entity
public class WishlistItemsEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "wishlist")
    private WishlistEntity wishlist;

  
}
