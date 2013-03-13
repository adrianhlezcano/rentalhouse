package com.rentalhouse.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
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
import com.rentalhouse.domain.Propiedad;
import com.rentalhouse.domain.Propietario;
import com.rentalhouse.form.PropiedadForm;
import com.rentalhouse.service.LocalidadService;
import com.rentalhouse.service.PersonaService;
import com.rentalhouse.service.PropiedadService;
import com.rentalhouse.utils.AppConstant;
import com.rentalhouse.validation.PropiedadValidator;

@Controller()
@RequestMapping("/propiedad")
public class PropiedadController {
	@Autowired
	@Qualifier("propiedadService")
	private PropiedadService propiedadService;
	@Autowired
	@Qualifier("localidadService")
	private LocalidadService localidadService;
	@Autowired
	@Qualifier("personaService")
	private PersonaService propietarioService;
	private Log _log = LogFactoryImpl.getLog(getClass());
	
	@RequestMapping(method=RequestMethod.GET)
	public String show(Model model){
		model.addAttribute(propiedadService.getPropidades(0, 10, true));
		model.addAttribute(new PropiedadForm());
		return "propiedad/list";
	}	
	@RequestMapping(method=RequestMethod.GET, params="new")
	public String setupForm(Model model){
		PropiedadForm form = new PropiedadForm(); // load object with default values
		form.setProvinciaList(localidadService.getProvincias());
		form.setLocalidadList(localidadService.getLocalidadByIdProvincia(form.getIdProvincia()));
		model.addAttribute(form);
		return "propiedad/form";
	}	
	@RequestMapping(method=RequestMethod.POST)
	public String processForm(@ModelAttribute PropiedadForm propiedadForm, BindingResult result){
		new PropiedadValidator().validate(propiedadForm, result);
		Propietario propietario = null;
		if(propiedadForm.getIdPropietario() != null && 0 != propiedadForm.getIdPropietario()){
			propietario = (Propietario) propietarioService.get(propiedadForm.getIdPropietario());
		}
		if(result.hasErrors()){		
			propiedadForm.setProvinciaList(localidadService.getProvincias());
			propiedadForm.setLocalidadList(localidadService.getLocalidadByIdProvincia(propiedadForm.getIdProvincia()));
			propiedadForm.setIdPropietario(propietario != null? propietario.getIdPersona() : 0);
			propiedadForm.setNombrePropietario(propietario != null? propietario.getNombreCompleto() : "");
			return "propiedad/form";	
		}
		Localidad localidad = localidadService.getLocalidadById(propiedadForm.getIdLocalidad());
		if (propiedadForm.getAction().equals(AppConstant.INSERT)) {  
			Propiedad propiedad = propiedadForm.toPropiedad(new Propiedad(), localidad, propietario);
			propiedadService.save(propiedad);
			propiedadForm.setIdPropiedad(propiedad.getIdPropiedad());
		}else if (propiedadForm.getAction().equals(AppConstant.UPDATE)){
			Propiedad propiedad = propiedadService.get(Integer.valueOf(propiedadForm.getIdPropiedad()));
			propiedad = propiedadForm.toPropiedad(propiedad, localidad, propietario);
			propiedadService.update(propiedad);
		}		
		return "redirect:/propiedad/"+propiedadForm.getIdPropiedad() ;		
	}
	// This is a common way to resolve a url with a param
	@RequestMapping(method=RequestMethod.GET, value="/{idPropiedad}")
	public String displayPropiedad(@PathVariable Integer idPropiedad, Model model){
		Propiedad propiedad = propiedadService.getPropiedadById(idPropiedad);
		Localidad localidad = localidadService.getLocalidadById(propiedad.getDomicilio().getLocalidad().getIdLocalidad());
		propiedad.getDomicilio().setLocalidad(localidad);
		model.addAttribute(propiedad);
		return "propiedad/display";
	}
	@RequestMapping(method=RequestMethod.GET, value="/edit/{idPropiedad}")
	public String edit(@PathVariable Integer idPropiedad, Model model){
		Propiedad propiedad = propiedadService.getPropiedadById(idPropiedad);
		Localidad localidad = localidadService.getLocalidadById(propiedad.getDomicilio().getLocalidad().getIdLocalidad());
		propiedad.getDomicilio().setLocalidad(localidad);
		PropiedadForm form = new PropiedadForm(propiedad);		
		int idProvincia = propiedad.getDomicilio().getLocalidad().getProvincia().getIdProvincia();
		form.setProvinciaList(localidadService.getProvincias());
		form.setLocalidadList(localidadService.getLocalidadByIdProvincia(idProvincia));
		model.addAttribute(form);
		return "propiedad/form";
	}
	@RequestMapping(method=RequestMethod.GET, value="/remove")
	public String remove(@RequestParam("idPropiedad") Integer idPropiedad){
		Propiedad propiedad = propiedadService.getPropiedadById(idPropiedad);
		Localidad localidad = localidadService.getLocalidadById(propiedad.getDomicilio().getLocalidad().getIdLocalidad());
		propiedad.getDomicilio().setLocalidad(localidad);
		propiedadService.remove(propiedad);
		return "redirect:/propiedad";
	}
	@RequestMapping(method=RequestMethod.GET, value="/search")
	public @ResponseBody Map<String, Object> search(
			@RequestParam("tipoPropiedad") String tipoPropiedad,
			@RequestParam("operacionPropiedad") String operacionPropiedad,
			@RequestParam("precioMinimo") Integer precioMinimo,
			@RequestParam("precioMaximo") Integer precioMaximo,
			@RequestParam("dormitorios") Integer dormitorios) {
		Map<String, Object> model = new HashMap<String, Object>();
		precioMinimo = precioMinimo != null? precioMinimo : 0;
		precioMaximo = precioMaximo != null? precioMaximo : 0;
		dormitorios = dormitorios != null? dormitorios : 0;
		List list = propiedadService.searchPropidades
			(operacionPropiedad, tipoPropiedad, precioMinimo, precioMaximo, dormitorios);		
		model.put("propiedadList", list);
		return model;
	}
}
