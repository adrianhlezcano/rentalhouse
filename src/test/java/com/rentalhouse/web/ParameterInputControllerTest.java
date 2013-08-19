package com.rentalhouse.web;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import com.rentalhouse.controller.ParameterInputController;
import com.rentalhouse.domain.TipoDni;

public class ParameterInputControllerTest {
	
	@Test
	public void displayParameterUsingInput(){
		ParameterInputController pic = new ParameterInputController();
		ModelMap model = new ModelMap(); 
		String view = pic.getListOfValues("adrian", model);
		
		Assert.assertEquals("persona/user", view);		
		Assert.assertNotNull(model.get("persona"));		
		Assert.assertNotNull(model.get("tiposDni"));
		
	}

}
