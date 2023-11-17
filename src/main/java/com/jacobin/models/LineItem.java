package com.jacobin.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name =  "lineItem")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LineItem implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "line_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int lineItemId;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product item;
	
	@Column(name = "quantity")
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;		
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
}
