package com.jacobin.models;

import static javax.persistence.FetchType.EAGER;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.CascadeType;
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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
	
	@OneToMany(fetch=EAGER, cascade=CascadeType.PERSIST)
	private List<DetailOrder> details;
	
	@Column(name = "payment_method")
	private String paymentMethod;
	
	@Column(name = "shipping_method")
	private String shippingMethod;
	
	@Column(name = "total_price")
	private String totalPrice;
}
