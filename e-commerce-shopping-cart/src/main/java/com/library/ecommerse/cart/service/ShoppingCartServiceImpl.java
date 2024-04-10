package com.library.ecommerse.cart.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.library.ecommerse.cart.model.Customer;
import com.library.ecommerse.cart.model.Products;
import com.library.ecommerse.cart.model.ShoppingCart;
import com.library.ecommerse.cart.repository.ShppingCartRepository;
import com.library.ecommerse.exception.ShoppingCartException;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	public RestTemplate rt;

	List<Products> list;

	@Autowired
	ShppingCartRepository repo;

	@Autowired
	ShoppingCart cart;

	private ExecutorService executor = Executors.newFixedThreadPool(2);

	@Override
	public ShoppingCart addItem(int customer_id, int product_id) throws ShoppingCartException {
		Customer customer = getCustomerDetails(customer_id);
		Products product = getProductDetails(product_id);

		if (customer != null && product != null) {
			if (repo.existsByCustomerId(customer.getCustomerId())) {
				cart = getItemByCustomerId(customer_id);
				boolean flag = cart.getCart().stream().anyMatch(i -> i.getProductId() == product.getProductId());
				if (!flag) {
					product.setQuantity(1);
					cart.getCart().add(product);
					return repo.save(cart);
				} else {

					Optional<Products> item = cart.getCart().stream()
							.filter(i -> i.getProductId() == product.getProductId()).findFirst();
					item.get().setQuantity(item.get().getQuantity() + 1);
					cart.getCart().removeIf(i -> i.getProductId() == product.getProductId());
					cart.getCart().add(item.get());

					return repo.save(cart);

				}

			}
			product.setQuantity(1);
			return repo.save(new ShoppingCart(customer.getCustomerId(), Arrays.asList(product)));
		} else {
			throw new ShoppingCartException("Either Customer Id or Product Id is invalid");
		}

	}

	@Override
	public ShoppingCart getItemByCustomerId(int customer_id) {

		return repo.findByCustomerId(customer_id);
	}

	@Override
	public ShoppingCart deleteItem(int customer_id, int product_id) throws ShoppingCartException {
		cart = getItemByCustomerId(customer_id);
		if (cart == null || cart.getCart() == null) {
			throw new ShoppingCartException("Your Cart is Empty");
		}

		boolean flag = cart.getCart().stream().anyMatch(i -> i.getProductId() == product_id);
		if (!flag) {
			throw new ShoppingCartException("Product is not present in your cart");
		}

		Optional<Products> item = cart.getCart().stream().filter(i -> i.getProductId() == product_id).findFirst();
		if (item.get().getQuantity() - 1 == 0) {
			cart.getCart().removeIf(i -> i.getProductId() == product_id);
		} else {
			for (var v : cart.getCart()) {
				if (v.getProductId() == product_id)
					v.setQuantity(v.getQuantity() - 1);
			}
		}

		if (cart.getCart().size() == 0) {
			repo.deleteById(customer_id);
			return null;
		}
		return repo.save(cart);

	}

	@Override
	public void deleteCartByCustomerID(int customer_Id) {
		repo.deleteById(customer_Id);

	}



	private Customer getCustomerDetails(int customer_id) {
		Customer customerDetails = rt.getForObject("http://localhost:7200/ecommerse/customer/" + customer_id,
				Customer.class);
		return customerDetails;
	}

	private Products getProductDetails(int product_id) {
		Products productDetails = rt.getForObject("http://localhost:7100/ecommerse/product/" + product_id,
				Products.class);
		return productDetails;
	}

}
