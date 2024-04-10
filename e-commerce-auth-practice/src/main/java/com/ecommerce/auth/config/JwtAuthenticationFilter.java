package com.ecommerce.auth.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ecommerce.auth.service.CustomUserDetailsService;
import com.ecommerce.auth.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	public static String userMial;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String requestTokenHeader=request.getHeader("Authorization");
		String jwtToken=null;
	     String userEmail = null;
		
		 if (requestTokenHeader == null ||!requestTokenHeader.startsWith("Bearer ")) {
		      filterChain.doFilter(request, response);
		      return;
		    }
		
		
		 
	    if (requestTokenHeader != null ||requestTokenHeader.startsWith("Bearer ")) {
	    	 jwtToken = requestTokenHeader.substring(7);
	    	 
	    	 try {
	    		 userEmail = this.jwtUtil.extractUsername(jwtToken); 
	    		 this.userMial=userEmail;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	   
	    
	   
	  // UserDetails userDetails=this.customUserDetailsService.loadUserByUsername(userEmail);
	    if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	      UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(userEmail);
	      
	     UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	     usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	     SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	    }
	    filterChain.doFilter(request, response);
	}

}
