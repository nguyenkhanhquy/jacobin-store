package com.jacobin.models;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

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
@Table(name = "lineitem")
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
	
	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Column(name = "quantity")
	private int quantity = 1;
	
	public double getTotal() {
        double total = product.getPrice() * quantity;
        return total;
    }

    public String getTotalCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return currency.format(this.getTotal());
    }
}
