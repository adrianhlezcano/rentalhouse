package com.rentalhouse.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rentalhouse.domain.Persona;
import com.rentalhouse.domain.TipoDni;

@Controller
@RequestMapping("/parameter")
public class ParameterInputController {
	// set service by injection and assign to an local final variable
	@RequestMapping(value="/list",method=RequestMethod.GET )
	public String getListOfValues(@RequestParam("username") String username, ModelMap model){
		Persona p = new Persona();
		p.setApellido(username);
		p.setNombre(username);
		
		model.put("persona", p);
		List<String> tiposDni = Arrays.asList(TipoDni.DNI.getValue(), 
				TipoDni.LE.getValue(), TipoDni.LC.getValue(), TipoDni.PASS.getValue());
		model.put("tiposDni", tiposDni);
		return "persona/user";
	}

}
