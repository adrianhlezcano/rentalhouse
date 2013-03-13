package com.rentalhouse.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.*;

@Controller
public class WelcomeController {
	
	@RequestMapping({"/", "/home"})
	public String welcomeHandler(Map<String, Object> model, HttpServletRequest request) {
//		synchronized (request.getSession()) {
//			if (request.getSession(false) == null || request.getSession(false).getAttribute("user") == null){
//				return "redirect:/usuario/login";
//			}
//		}				
//		model.put("greeting", "Hello Everybody!");
//		model.put("owner", "Adrian Lezcano");
//		model.put("now", new java.util.Date());
//		return "welcome";
		return "redirect:/propiedad";
	}
	
}
