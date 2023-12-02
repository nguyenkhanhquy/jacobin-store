package com.jacobin.models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.jacobin.dao.DetailOrderDB;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer_order")
@Getter
@Setter
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "order_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	
	@ManyToOne
	@JoinColumn(name = "order_track_id")
	private OrderTrack orderTrack;
	
	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	public String getOrderDateDefaultFormat() {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, new Locale("vi", "VN"));
        String orderDateFormatted = dateFormat.format(date);
        return orderDateFormatted;
    }
	
	@Column(name = "phone")
	private String phone;

	@Column(name = "address")
    private String address;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany
	private List<DetailOrder> details;
	
	public Order() {
		details = new ArrayList<DetailOrder>();
    }
	
	
	@Column(name = "shipping_method")
	private String shippingMethod;
	
	@Column(name = "payment_method")
	private String paymentMethod;
	
	@Column(name = "total_price")
	private String totalPrice;
	
    public double getTotal() {
        double total = 0.0;
        for (DetailOrder item : details) {
        	total += item.getTotal();
        }
        return total;
    }
    
    public String getTotalCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return currency.format(this.getTotal());
    }
    
    public void addItem(DetailOrder item) {
    	details.add(item);
    	DetailOrderDB.insert(item);
    }
}
