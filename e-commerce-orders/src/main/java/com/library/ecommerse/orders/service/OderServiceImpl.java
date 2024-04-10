package com.library.ecommerse.orders.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.library.ecommerse.orders.config.SecurityConfiguration;
import com.library.ecommerse.orders.exception.CardExpiredException;
import com.library.ecommerse.orders.exception.EmptyCartException;
import com.library.ecommerse.orders.exception.UnauthorizedUserException;
import com.library.ecommerse.orders.exception.UpdateProfileException;
import com.library.ecommerse.orders.exception.ValidPaymentOptionException;
import com.library.ecommerse.orders.model.Customer;
import com.library.ecommerse.orders.model.Orders;
import com.library.ecommerse.orders.model.PaymentDetails;
import com.library.ecommerse.orders.model.ShoppingCart;
import com.library.ecommerse.orders.payment.CardPayment;
import com.library.ecommerse.orders.payment.PaymentMethod;
import com.library.ecommerse.orders.payment.UPIPayment;
import com.library.ecommerse.orders.repository.PaymentDetailsRepository;
import com.library.ecommerse.orders.repository.PlacedOrderRepository;

@Service
public class OderServiceImpl implements OderService {

	@Autowired
	PlacedOrderRepository repo;

	@Autowired
	PaymentDetailsRepository paymentRepo;

	@Autowired
	public RestTemplate rt;

	@Autowired
	SecurityConfiguration config;
	
	
	private Orders calculateOrder(int customer_id) throws EmptyCartException, UpdateProfileException {
		if (config.user.getBody().getUserId() == customer_id) {
			Double sum = 0.0;
			ShoppingCart shopping_cart = getShoppingCart(customer_id);

			if (shopping_cart == null || shopping_cart.getCustomerId() == 0 || shopping_cart.getCart() == null) {
				throw new EmptyCartException("Your Cart is Empty, You Cannot place Order");
			}
			Customer customer = getCustomerDetails(customer_id);
			if (customer.getCustomerName() == null || customer.getAddress() == null
					|| customer.getCustomerPhone() == 0) {
				throw new UpdateProfileException("Please update your profile to place order");
			}
			for (var product : shopping_cart.getCart()) {
				sum += product.getProductPrice() * product.getQuantity();
			}
			

			return new Orders(RandomStringUtils.randomAlphanumeric(6), customer, shopping_cart.getCart(), null, sum);
		}

		throw new UnauthorizedUserException("Please login into your account");

	}

	@Override
	public Orders placeOrder(int customer_id, String paymentType) throws EmptyCartException, UpdateProfileException,
			CardExpiredException, UnauthorizedUserException {
		
		Orders order = calculateOrder(customer_id);
		
		PaymentDetails paymentDetails = paymentRepo.findByCustomerId(customer_id);

		if (paymentDetails != null) {
			if (paymentType.equalsIgnoreCase("upi")) {
				PaymentMethod upi = new UPIPayment(paymentDetails);
				if (paymentDetails.getUpiDetails() == null)
					throw new UpdateProfileException("Please update your payment details : upi Id is null");
				order.setPaymentInfo(upi.processPayment());
				rt.delete("http://localhost:7300/ecommerse/cart/clearCart/" + customer_id);
				return repo.save(order);
			} else if (paymentType.equalsIgnoreCase("card")) {
				PaymentMethod card = new CardPayment(paymentDetails);
				if (paymentDetails.getCardDetails() == null)
					throw new UpdateProfileException("Please update your payment details : Your card details are null");
				
				order.setPaymentInfo(card.processPayment());
				rt.delete("http://localhost:7300/ecommerse/cart/clearCart/" + customer_id);
				return repo.save(order);
			} else
				throw new ValidPaymentOptionException(
						"Please enter valid payment option [upi/card], there is no option called :" + paymentType);

		}
		throw new UpdateProfileException("Please update your payment details");

	}

//	
//	//attribute for method paymentType()
//	@Override
//	public Orders placeOrderByUpi(int customer_id, UpiPaymentMethod upi) throws EmptyCartException, UpdateProfileException{
//		
//		//make db call to get all paymentDetails on customerId=>PaymentDetails
//		
//		//PaymentMethod interf impl to 2 concrete class for upi and card() write corr. logic for make payment (SYSO say print upi/card)
//		//
//		if(paymentType.eq("UPI")) {
//			PaymentMethod paymentMethod = new UPIPaymtnt(paymentDetailsFromDB);
//			paymentMethod.processPayment();
//		}
//		
//		
//		
//		Orders order=placeOrder(customer_id);
//		order.setPaymentInfo(upi.processPayment());
//		return repo.save(order);
//	}
//
//	@Override
//	public Orders placeOrderByCard(int customer_id,CreditCardPaymentMethod card) throws EmptyCartException ,UpdateProfileException, CardExpiredException{
//		Orders order=placeOrder(customer_id);
//		order.setPaymentInfo(card.processPayment());
//		return repo.save(order);
//	}

	@Override
	public List<Orders> getAllOrder() {

		return repo.findAll();
	}

	@Override
	public List<Orders> getOrderByCustomerID(int customer_Id) {
		List<Orders> list = getAllOrder();
		return list.stream().filter(i -> i.getCutomer().getCustomerId() == customer_Id).toList();

	}

	@Override
	public Orders getOrderByID(String order_id) {

		return repo.findByOrderId(order_id);
	}

	public Customer getCustomerDetails(int customer_id) {
		Customer customerDetails = rt.getForObject("http://localhost:7200/ecommerse/customer/" + customer_id,
				Customer.class);
		return customerDetails;
	}

	public ShoppingCart getShoppingCart(int customer_id) {
		ShoppingCart customerDetails = rt.getForObject("http://localhost:7300/ecommerse/cart/" + customer_id,
				ShoppingCart.class);
		return customerDetails;
	}

}
