package com.rentalhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rentalhouse.domain.Localidad;
import com.rentalhouse.service.LocalidadService;


@Controller
@RequestMapping("/localidad")
public class LocalidadController {
	@Autowired
	@Qualifier("localidadService")
	private LocalidadService localidadService;
		
	@RequestMapping(method=RequestMethod.GET, value="{idProvincia}")
	public @ResponseBody List<Localidad> getLocalidadesByProvincia(@PathVariable("idProvincia") Integer idProvincia){
		return localidadService.getLocalidadByIdProvincia(idProvincia);
	}

}
