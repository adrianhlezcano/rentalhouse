package com.rentalhouse.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rentalhouse.domain.Contrato;
import com.rentalhouse.domain.Garante;
import com.rentalhouse.domain.Localidad;
import com.rentalhouse.domain.Persona;
import com.rentalhouse.domain.Propiedad;
import com.rentalhouse.domain.Propietario;
import com.rentalhouse.form.ContratoForm;
import com.rentalhouse.form.GaranteForm;
import com.rentalhouse.form.PersonaForm;
import com.rentalhouse.form.PropiedadForm;
import com.rentalhouse.service.LocalidadService;
import com.rentalhouse.service.PersonaService;
import com.rentalhouse.utils.AppConstant;
import com.rentalhouse.utils.StringValidation;
import com.rentalhouse.validation.GaranteValidator;

@Controller
@RequestMapping("/garante")
public class GaranteController {
	@Autowired
	@Qualifier("personaService")
	private PersonaService personaService;
	@Autowired
	@Qualifier("localidadService")
	private LocalidadService localidadService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String show(Model model){
		model.addAttribute(personaService.getAll(0, 10, false, Garante.class));
		return "garante/list";
	}	
	@RequestMapping(method=RequestMethod.GET, params="new")
	public String setupForm(Model model){
		GaranteForm form = new GaranteForm();		
		form.setIdProvincia(4);  // PROVINCIA 4 = 'Corrientes'
		form.setProvinciaList(localidadService.getProvincias());
		form.setLocalidadList(localidadService.getLocalidadByIdProvincia(4));
		model.addAttribute(form);
		return "garante/form";
	}
	@RequestMapping(method=RequestMethod.POST)
	public String processForm(@ModelAttribute GaranteForm garanteForm, BindingResult result){
		new GaranteValidator().validate(garanteForm, result);
		if(result.hasErrors()){	
			garanteForm.setProvinciaList(localidadService.getProvincias());
			garanteForm.setLocalidadList(localidadService.getLocalidadByIdProvincia(garanteForm.getIdProvincia()));
			return "garante/form";
		}
		Localidad localidad = localidadService.getLocalidadById(garanteForm.getIdLocalidad());
		if (garanteForm.getAction().equals(AppConstant.INSERT)) {  
			if (personaService.getByDni(Integer.valueOf(garanteForm.getDni()), Persona.class) != null){
				// agregar error de que ya existe una persona con mismo dni
				return "garante/form";
			}else{					
				personaService.save(garanteForm.toGarante(new Garante(), localidad));
			}
		}else if (garanteForm.getAction().equals(AppConstant.UPDATE)){
			Garante garante = (Garante) personaService.get(garanteForm.getIdPersona());
			personaService.update(garanteForm.toGarante(garante, localidad));
		}		
		return "redirect:/garante/"+garanteForm.getDni();		
	}
	// This is a common way to resolve a url with a param
	@RequestMapping(method=RequestMethod.GET, value="/{dni}")
	public String displayPersona(@PathVariable Integer dni, Model model){
		Garante garante = (Garante) personaService.getByDni(dni, Garante.class);
		Localidad localidad = localidadService.getLocalidadById(garante.getDomicilio().getLocalidad().getIdLocalidad());
		garante.getDomicilio().setLocalidad(localidad);
		model.addAttribute(garante);
		return "garante/display";		
	}
	@RequestMapping(method=RequestMethod.GET, value="/edit/{dni}")
	public String edit(@PathVariable Integer dni, Model model){
		Garante garante = (Garante) personaService.getByDni(dni, Garante.class);
		Localidad localidad = localidadService.getLocalidadById(garante.getDomicilio().getLocalidad().getIdLocalidad());
		garante.getDomicilio().setLocalidad(localidad);		
		GaranteForm form = new GaranteForm(garante);
		form.setProvinciaList(localidadService.getProvincias());
		form.setLocalidadList(localidadService.getLocalidadByIdProvincia(localidad.getProvincia().getIdProvincia()));
		model.addAttribute(form);
		return "garante/form";
	}
	@RequestMapping(method=RequestMethod.GET, value="/remove")
	public String remove(@RequestParam("idPersona") Integer idPersona){
		Garante garante = (Garante) personaService.get(idPersona);
		personaService.delete(garante);
		return "redirect:/garante";
	}
	@RequestMapping(method=RequestMethod.GET, value="/get")
	public @ResponseBody Map<String, String> getGarante(@RequestParam("dni") Integer dni){
		Persona persona =  personaService.getByDni(dni, Garante.class);
		Map<String, String> result = new HashMap<String, String>();
		if(persona != null){			
			result.put("nombre", persona.getNombre());
			result.put("apellido", persona.getApellido());
			result.put("idPersona", String.valueOf(persona.getIdPersona()));
		}
		return result;
	}
	@RequestMapping(method=RequestMethod.GET, value="/search")
	public @ResponseBody Map<String, Object> search( @RequestParam("fieldName")String fieldName, 
		@RequestParam("fieldValue") String fieldValue, @RequestParam("page")String page){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("personList", personaService.findPersonaByFieldValue(fieldName, fieldValue, Garante.class));
		return model;
	}
}
