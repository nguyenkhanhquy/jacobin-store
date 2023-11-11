package com.jacobin.models;

import java.io.Serializable;
import java.util.List;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	private int orderId;
	private User user;
	private List<LineItem> items;
	private DateTime dateTime;
	private String paymentMethod;
	private String shippingMethod;
	private OrderTrack status;
}
