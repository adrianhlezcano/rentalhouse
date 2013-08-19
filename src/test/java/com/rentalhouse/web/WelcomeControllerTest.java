package com.rentalhouse.web;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.Assert;
import org.springframework.mock.web.MockHttpServletRequest;

import com.rentalhouse.controller.WelcomeController;

import javax.servlet.http.*;

public class WelcomeControllerTest {
	private static final String GREETING = "Hello Everybody!";
	private static final String OWNER = "Adrian Lezcano";
	
	
	@Test
	public void shouldDisplayWelcomeWords(){
//		WelcomeController welcomeController = new WelcomeController();
//		Map<String, Object> model = new HashMap<String, Object>(); 
//		HttpServletRequest request = new MockHttpServletRequest();
//		
//		String viewName = welcomeController.welcomeHandler(model);		
//		Assert.assertEquals("welcome", viewName);
//		
//		Assert.assertNotNull(model.get("greeting"));
//		Assert.assertEquals(GREETING, model.get("greeting"));
//		
//		Assert.assertNotNull(model.get("owner"));
//		Assert.assertEquals(OWNER, model.get("owner"));
//		
//		Assert.assertNotNull(model.get("now"));			
	}

}
