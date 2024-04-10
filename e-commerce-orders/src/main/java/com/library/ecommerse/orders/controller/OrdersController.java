package com.library.ecommerse.orders.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.ecommerse.orders.model.Orders;
import com.library.ecommerse.orders.model.ShoppingCart;
import com.library.ecommerse.orders.repository.PlacedOrderRepository;
import com.library.ecommerse.orders.service.OderService;
import com.library.ecommerse.orders.service.OrdersPDFExporter;
import com.lowagie.text.DocumentException;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/ecommerce/orders")
public class OrdersController {
	@Autowired
	OderService service;
	
	@Autowired
	OrdersPDFExporter exporter;
	
	@Autowired
	PlacedOrderRepository repo;

	

	@GetMapping
	public List<Orders> getAllOrder() {
		return repo.findAll();
//		return service.getAllOrder();
	}

	@GetMapping("/{customer_Id}")
	public List<Orders> getOrderByCustomerID(@PathVariable("customer_Id") int customer_Id) {
		System.out.println("==========");
		return null;
//		return service.getOrderByCustomerID(customer_Id);
	}
	
	

	@GetMapping("/export/pdf/{order_id}")
	public void exportToPDF(@PathVariable("order_id") String order_id,HttpServletResponse response)
			throws DocumentException, IOException {

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Orders_"+order_id+"__"+ currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        Orders order = service.getOrderByID(order_id);
         
        exporter.export(order,response);
		
		

	}

}
