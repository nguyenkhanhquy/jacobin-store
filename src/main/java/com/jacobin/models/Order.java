package com.jacobin.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "order_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "order")
	private List<LineItem> items;
	
	@Column(name = "date")
	private String date;
	
	@Column(name = "payment_method")
	private String paymentMethod;
	
	@Column(name = "shipping_method")
	private String shippingMethod;
	
	@ManyToOne
	@JoinColumn(name = "order_track_id")
	private OrderTrack orderTrack;
}
