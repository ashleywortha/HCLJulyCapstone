package com.ashley.controller;


import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ashley.model.Cart;
import com.ashley.model.Order;
import com.ashley.model.Product;
import com.ashley.model.User;
import com.ashley.repo.UserRepo;
import com.ashley.service.OrderService;
import com.ashley.service.ProductService;
import com.ashley.service.UserService;
import com.ashley.service.UserUserDetails;

@RestController
@RequestMapping("/cart")
@SessionAttributes("myCart")
public class SessionListenerController {
	private static final Logger LOG = LoggerFactory.getLogger(SessionListenerController.class);
	@Autowired 
	private ProductService pService;
	
	@Autowired 
	private OrderService oService;
	
	@Autowired
	private UserRepo uRepo;
	
	@ModelAttribute("myCart")
	public Cart myCart() {
		return new Cart();
	}
	
	private double total = 0;
	@GetMapping("/checkout")
	public String checkoutCart(@ModelAttribute("myCart") Cart cart) {
		
		Order myOrder = new Order();
		
		cart.forEach((k, v) -> {
			myOrder.getProducts().add(pService.getProductsById(v).get());
			total = total + pService.getProductsById(v).get().getPrice();
		});
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUsername = authentication.getName();
			User currentUser = uRepo.findByUsername(currentUsername).get();
			
			myOrder.setAmount(total);
			myOrder.setStatus("Shipped");
			myOrder.setDate(new Date());
			oService.addOrder(currentUser, myOrder);
			
			cart.clear();
		}
		
		return "Order created!";
	}
	@GetMapping("/view")
	public String viewCart(HttpServletRequest request, HttpServletResponse response
			 ,@ModelAttribute("myCart") Cart cart
			 ) {
		
		HttpSession sessionObj = request.getSession(false);
		if(cart.isEmpty()) {
			return "Cart is empty: " + cart;
		}
		
		return "result is: " + cart;
	}
	
	@GetMapping("/add/{productId}")
	public String addToCart(
			HttpServletRequest request, HttpServletResponse response, 
			@PathVariable Integer productId
			,@ModelAttribute("myCart") Cart cart
			) {

		HttpSession sessionObj = request.getSession();
//		Cart cart = (Cart) sessionObj.getAttribute("myCart");
			sessionObj.setAttribute("myCart", cart.put(cart.size(), productId));
		
		
		return "product with id " + productId + " added!";
	}
	
	@GetMapping("/remove/{cartId}")
	public String removeFromCart(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable Integer cartId
			,@ModelAttribute("myCart") Cart cart) {
		
		HttpSession sessionObj = request.getSession();
		sessionObj.setAttribute("myCart", cart.remove(cartId));
		return "product removed from cart";
	}
	
	@GetMapping("/clear")
	public String clearCart(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("myCart") Cart cart) {
		HttpSession sessionObj = request.getSession();
		cart.clear();
		
		return "Cart Cleared";
	}

	@GetMapping("/create-new-session")
	public String createNewSession(HttpServletRequest request, HttpServletResponse response) {
		HttpSession sessionObj = request.getSession(false);
		if(sessionObj == null) {
			LOG.info("Session not avalible, creating new session");
			sessionObj = request.getSession(true);
		}
		
		String activeSessions = sessionObj.getAttribute("activeSessions") != null
				?sessionObj.getAttribute("activeSessions").toString()
						:"0";
		return "Session is avalible now with total active sessions: " + activeSessions;
	}
	
	@GetMapping("/destroy-active-session")
	public String removeSession(HttpServletRequest request, HttpServletResponse response) {
		HttpSession sessionObj = request.getSession(false);
		
		if(sessionObj != null) {
			sessionObj.invalidate();
			return "Session destroyed, no more active sessions";
		}
		return "Session not avalible to destroy";
	}


}
