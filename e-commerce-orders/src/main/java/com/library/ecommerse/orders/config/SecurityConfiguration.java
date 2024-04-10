package com.library.ecommerse.orders.config;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.library.ecommerse.orders.exception.UnauthorizedUserException;
import com.library.ecommerse.orders.model.UserInfo;
import com.library.ecommerse.orders.payment.PaymentMethod;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration extends OncePerRequestFilter {
	@Autowired
	RestTemplate rt;

	@Autowired
	@Qualifier("handlerExceptionResolver")
	HandlerExceptionResolver resolver;

	@Autowired
	public static ResponseEntity<UserInfo> user;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String path = request.getRequestURI();

		if (path.startsWith("/ecommerce/orders") || path.startsWith("/ecommerce")) {
			filterChain.doFilter(request, response);
			return;
		}

		System.out.println("Intercepter added");

		String url = "http://localhost:7600/ecommerce/auth/getDetails";

		HttpHeaders headers = new HttpHeaders();

		String token = request.getHeader("Authorization");

		if (token == null || !token.startsWith("Bearer ")) {
			System.out.println("No header");
			throw new UnauthorizedUserException("User Must be Logged In");

		}

		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		headers.add("Authorization", token);

		HttpEntity httpRequest = new HttpEntity<>(null, headers);

		try {

			user = rt.exchange("http://localhost:7600/ecommerce/auth/getDetails", HttpMethod.GET, httpRequest,
					UserInfo.class);

			filterChain.doFilter(request, response);
		} catch (RestClientException ex) {
			System.out.println("Try method failed!!!!!!");
			resolver.resolveException(request, response, null, new UnauthorizedUserException("User Must be Logged In"));
		}

	}

	

}
