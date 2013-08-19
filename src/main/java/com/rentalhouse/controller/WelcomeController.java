package com.rentalhouse.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rentalhouse.domain.Localidad;
import com.rentalhouse.domain.Propiedad;
import com.rentalhouse.form.PropiedadForm;
import com.rentalhouse.service.LocalidadService;
import com.rentalhouse.service.PropiedadService;

import javax.servlet.http.*;
import javax.servlet.*;

@Controller
@RequestMapping({"/", "/home"})
public class WelcomeController {
	@Autowired
	@Qualifier("propiedadService")
	private PropiedadService propiedadService;
	@Autowired
	@Qualifier("localidadService")
	private LocalidadService localidadService;
	@Autowired
	private ServletContext context;
	private Log _log = LogFactoryImpl.getLog(getClass());
	
	@RequestMapping(method=RequestMethod.GET)
	public String welcomeHandler(Model model) {
		List<Propiedad> props = propiedadService.getPropidades(0, 10, true);	
		model.addAttribute(props);
		model.addAttribute("imageMap", getImages(props));
		model.addAttribute(new PropiedadForm());
		return "home";
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
		List<Propiedad> propiedadList = propiedadService.searchPropidadesByParams
			(operacionPropiedad, tipoPropiedad, precioMinimo, precioMaximo, dormitorios);
		Map<String, Object> propiedadJson = Collections.emptyMap();
		List<Map<String, Object>> list = 
			new ArrayList<Map<String, Object>>(propiedadList.size());
		for (Propiedad p : propiedadList) {
			propiedadJson = new HashMap<String, Object>();
			propiedadJson.put("idPropiedad", p.getIdPropiedad().toString());
			propiedadJson.put("tipoPropiedad", p.getTipoPropiedad().getValue());
			propiedadJson.put("operacionPropiedad", p.getOperacionPropiedad().getValue());
			propiedadJson.put("precioAlquiler", p.getPrecioAlquiler());
			propiedadJson.put("precioVenta", p.getPrecioVenta());
			propiedadJson.put("dormitorios", p.getDormitorios());
			propiedadJson.put("domicilio", p.getDomicilio().getCalle().concat(" ").concat(p.getDomicilio().getNumero()));
			list.add(propiedadJson);			
		}
		model.put("imageMap", getImages(propiedadList));		
		model.put("propiedadList", list);
		return model;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/propiedad/{idPropiedad}")
	public String displayPropiedad(@PathVariable Integer idPropiedad, Model model){
		Propiedad propiedad = propiedadService.getPropiedadById(idPropiedad);
		Localidad localidad = localidadService.getLocalidadById(propiedad.getDomicilio().getLocalidad().getIdLocalidad());
		propiedad.getDomicilio().setLocalidad(localidad);
		Set<String> images = context.getResourcePaths("/images/"+propiedad.getIdPropiedad());
		StringBuilder sb = new StringBuilder();
		for (String image:images) {
			sb.append(image).append(";");
		}
		String imgList = sb.toString();
		imgList = imgList.substring(0, imgList.lastIndexOf(";"));
		_log.info(imgList);
		model.addAttribute("imgList", imgList);
		model.addAttribute(propiedad);
		model.addAttribute(new PropiedadForm());
		return "displayProperty";
	}
	
	private String getImages(List<Propiedad> propiedades){
		StringBuilder sb = new StringBuilder();
		for(Propiedad prop : propiedades){
			Set<String> images = context.getResourcePaths("/images/"+prop.getIdPropiedad());
			
			if (images != null && images.size() > 0){
				sb.append(String.valueOf(prop.getIdPropiedad()).concat(":").
						concat(images.iterator().next()).concat(";"));				
			} else {
				sb.append(String.valueOf(prop.getIdPropiedad()).concat(":").
						concat("/images/house.jpeg").concat(";"));
			}				
		}	
		String returnValue = sb.toString();
		returnValue = returnValue.substring(0, returnValue.lastIndexOf(";"));
		return returnValue;
	}
	
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public String gotToLogin() {		
		return "redirect:/admin/usuario/login";
	}
	
}
