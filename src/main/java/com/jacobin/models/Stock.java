package com.jacobin.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stock")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stock implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "stock_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stockId;
	
	@Column(name = "quantity")
	private int quantity;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private Product product;
}
