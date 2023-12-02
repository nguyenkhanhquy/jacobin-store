package com.jacobin.models;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "detail_order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detailOrderId;

	@Column(name = "name_product")
    private String nameProduct;
	
	@Column(name = "size")
    private String size;
	
	@Column(name = "quantity")
    private int quantity = 1;
	
	@Column(name = "price")
    private double price;
	
	public String getPriceCurrencyFormat() {
		NumberFormat currency = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
		return currency.format(price);
	}
	
	public double getTotal() {
        double total = price * quantity;
        return total;
    }

    public String getTotalCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return currency.format(this.getTotal());
    }
}
