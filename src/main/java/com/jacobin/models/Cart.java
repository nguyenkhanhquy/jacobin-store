package com.jacobin.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cart")
@Getter
@Setter
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;
	
	@OneToMany
	private List<LineItem> items;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Cart() {
        items = new ArrayList<LineItem>();
    }
	
    public int getCount() {
        return items.size();
    }

    public void addItem(LineItem item) {
        int productId = item.getProduct().getProductId();
        int quantity = item.getQuantity();
    	for (LineItem cartItem : items) {
            if (cartItem.getProduct().getProductId() == productId) {
            	int quantityCartItem = cartItem.getQuantity();
        		quantity += quantityCartItem;
                cartItem.setQuantity(quantity);
                return;
            }
        }
        items.add(item);
    }
    
    public void updateItem(LineItem item) {
        int productId = item.getProduct().getProductId();
        int quantity = item.getQuantity();
    	for (LineItem cartItem : items) {
            if (cartItem.getProduct().getProductId() == productId) {
            	int quantityCartItem = cartItem.getQuantity();
            	if (quantity == -1) {
            		quantity = quantityCartItem;
            	}
            	cartItem.setQuantity(quantity);
                return;
            }
        }
        items.add(item);
    }

    public void removeItem(LineItem item) {
        int productId = item.getProduct().getProductId();
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (lineItem.getProduct().getProductId() == productId) {
                items.remove(i);
                return;
            }
        }
    }
}
