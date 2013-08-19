package com.rentalhouse.controller;

import java.util.HashMap;
import java.util.Map;

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

import com.rentalhouse.domain.Localidad;
import com.rentalhouse.domain.Persona;
import com.rentalhouse.domain.Propietario;
import com.rentalhouse.form.PropietarioForm;
import com.rentalhouse.service.LocalidadService;
import com.rentalhouse.service.PersonaService;
import com.rentalhouse.utils.AppConstant;
import com.rentalhouse.validation.PropietarioValidator;

@Controller
@RequestMapping("/admin/propietario")
public class PropietarioController {
	@Autowired
	@Qualifier("personaService")
	private PersonaService personaService;
	@Autowired
	@Qualifier("localidadService")
	private LocalidadService localidadService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String show(Model model){
		model.addAttribute(personaService.getAll(0, 10, false, Propietario.class));
		return "admin/propietario/list";
	}	
	@RequestMapping(method=RequestMethod.GET, params="new")
	public String setupForm(Model model){
		PropietarioForm form = new PropietarioForm();
		// PROVINCIA 4 = 'Corrientes'
		form.setIdProvincia(4);
		form.setProvinciaList(localidadService.getProvincias());
		form.setLocalidadList(localidadService.getLocalidadByIdProvincia(4));
		model.addAttribute(form);
		return "admin/propietario/form";
	}
	@RequestMapping(method=RequestMethod.POST)
	public String processForm(@ModelAttribute PropietarioForm propietarioForm, BindingResult result){
		new PropietarioValidator().validate(propietarioForm, result);
		if(result.hasErrors()){		
			propietarioForm.setProvinciaList(localidadService.getProvincias());
			propietarioForm.setLocalidadList(localidadService.getLocalidadByIdProvincia(propietarioForm.getIdProvincia()));
			return "admin/propietario/form";
		}
		Localidad localidad = localidadService.getLocalidadById(propietarioForm.getIdLocalidad());
		if (propietarioForm.getAction().equals(AppConstant.INSERT)) {  
			if (personaService.getByDni(Integer.valueOf(propietarioForm.getDni()), Persona.class) != null){
				// agregar error de que ya existe una persona con mismo dni
				return "admin/propietario/form";
			}else{				
				personaService.save(propietarioForm.toPropietario(new Propietario(), localidad));
			}
		}else if (propietarioForm.getAction().equals(AppConstant.UPDATE)){
			Propietario propietario = (Propietario) personaService.get(propietarioForm.getIdPersona());
			personaService.update(propietarioForm.toPropietario(propietario, localidad));
		}		
		return "redirect:/admin/propietario/"+propietarioForm.getDni();		
	}
	// This is a common way to resolve a url with a param
	@RequestMapping(method=RequestMethod.GET, value="/{dni}")
	public String displayPersona(@PathVariable Integer dni, Model model){
		Propietario propietario = (Propietario) personaService.getByDni(dni, Propietario.class);
		Localidad locadlidad = localidadService.getLocalidadById(propietario.getDomicilio().getLocalidad().getIdLocalidad());
		propietario.getDomicilio().setLocalidad(locadlidad);
		model.addAttribute(propietario);
		return "admin/propietario/display";
	}
	@RequestMapping(method=RequestMethod.GET, value="/edit/{dni}")
	public String edit(@PathVariable Integer dni, Model model){
		Propietario propietario = (Propietario) personaService.getByDni(dni, Propietario.class);
		Localidad localidad = localidadService.getLocalidadById(propietario.getDomicilio().getLocalidad().getIdLocalidad());
		propietario.getDomicilio().setLocalidad(localidad);
		PropietarioForm form = new PropietarioForm(propietario);		
		form.setProvinciaList(localidadService.getProvincias());
		form.setLocalidadList(localidadService.getLocalidadByIdProvincia(localidad.getProvincia().getIdProvincia()));
		model.addAttribute(form);
		return "admin/propietario/form";
	}
	@RequestMapping(method=RequestMethod.GET, value="/remove")
	public String remove(@RequestParam("idPersona") Integer idPersona){
		Propietario propietario = (Propietario) personaService.get(idPersona);
		personaService.delete(propietario);
		return "redirect:/admin/propietario";
	}
	@RequestMapping(method=RequestMethod.GET, value="/get")
	public @ResponseBody Map<String, String> getPropietario(@RequestParam("dni") Integer dni){
		Persona persona =  personaService.getByDni(dni, Propietario.class);
		Map<String, String> result = new HashMap<String, String>();
		if(persona != null){			
			result.put("nombreCompleto", persona.getNombreCompleto());
			result.put("idPersona", String.valueOf(persona.getIdPersona()));
		}
		return result;
	}
	@RequestMapping(method=RequestMethod.GET, value="/search")
	public @ResponseBody Map<String, Object> search(@RequestParam("fieldName")String fieldName, 
		@RequestParam("fieldValue") String fieldValue, @RequestParam("page")String page){
		Map<String, Object> model = new HashMap<String, Object>();		
		model.put("personList", personaService.findPersonaByFieldValue(fieldName, fieldValue, Propietario.class));
		return model;
	}
}
