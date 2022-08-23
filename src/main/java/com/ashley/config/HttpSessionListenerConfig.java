package com.ashley.config;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ashley.model.Cart;

@WebListener
public class HttpSessionListenerConfig implements HttpSessionListener {
	private static final Logger LOG=LoggerFactory
			.getLogger(HttpSessionListenerConfig.class);
	private final AtomicInteger activeSessions;

	
	
	public HttpSessionListenerConfig() {
		super();
		activeSessions = new AtomicInteger();
		
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent sessionEvent) {
		LOG.info("Incrementing Session Counter");
		sessionEvent.getSession().setAttribute("activeSessions", activeSessions.get());
		
		LOG.info("Total Active Session: {} ", activeSessions.get());
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		LOG.info("Decrementing Session Counter");
		activeSessions.decrementAndGet();
		sessionEvent.getSession().setAttribute("activeSessions", activeSessions.get());
		LOG.info("Session Destroyed");
	}

}
