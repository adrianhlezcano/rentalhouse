package com.rentalhouse.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.*;

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
import org.springframework.web.multipart.MultipartFile;

import com.rentalhouse.domain.Localidad;
import com.rentalhouse.domain.Propiedad;
import com.rentalhouse.domain.Propietario;
import com.rentalhouse.form.PropiedadForm;
import com.rentalhouse.service.LocalidadService;
import com.rentalhouse.service.PersonaService;
import com.rentalhouse.service.PropiedadService;
import com.rentalhouse.utils.AppConstant;
import com.rentalhouse.utils.StringValidation;
import com.rentalhouse.validation.PropiedadValidator;

@Controller()
@RequestMapping("/admin/propiedad")
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
	@Autowired
	private ServletContext context;
	private Log _log = LogFactoryImpl.getLog(getClass());
	
	@RequestMapping(method=RequestMethod.GET)
	public String show(Model model){
		model.addAttribute(propiedadService.getPropidades(0, 10, true));
		model.addAttribute(new PropiedadForm());
		return "admin/propiedad/list";
	}	
	@RequestMapping(method=RequestMethod.GET, params="new")
	public String setupForm(Model model){
		PropiedadForm form = new PropiedadForm(); // load object with default values
		form.setProvinciaList(localidadService.getProvincias());
		form.setLocalidadList(localidadService.getLocalidadByIdProvincia(form.getIdProvincia()));
		form.setIdLocalidad(4156);
		model.addAttribute(form);
		return "admin/propiedad/form";
	}	
	@RequestMapping(method=RequestMethod.POST)
	public String processForm(@ModelAttribute PropiedadForm propiedadForm, BindingResult result, ServletRequest request, 
			@RequestParam(value="image", required=false) MultipartFile[] images){
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
			return "admin/propiedad/form";	
		}		
		Localidad localidad = localidadService.getLocalidadById(propiedadForm.getIdLocalidad());
		if (propiedadForm.getAction().equals(AppConstant.INSERT)) {  
			Propiedad propiedad = propiedadForm.toPropiedad(new Propiedad(), localidad, propietario);
			propiedadService.save(propiedad);
			propiedadForm.setIdPropiedad(propiedad.getIdPropiedad());
			
			if(propiedadForm.getFiles() != null && propiedadForm.getFiles().size() > 0){
				int counter = 0;
				String imageDir = context.getRealPath("/resources/images");	
				for (MultipartFile file : propiedadForm.getFiles()) {
					if (file != null && !file.isEmpty()){
						String propiedadId = String.valueOf(propiedad.getIdPropiedad());
						String imageLocation = imageDir.concat("/").concat(propiedadId);							
						validateAndSaveImage(file, imageLocation);	
						counter++;
					}				
				}
			}			
		}else if (propiedadForm.getAction().equals(AppConstant.UPDATE)){
			Propiedad propiedad = propiedadService.get(Integer.valueOf(propiedadForm.getIdPropiedad()));
			propiedad = propiedadForm.toPropiedad(propiedad, localidad, propietario);
			propiedadService.update(propiedad);
		}		
		return "redirect:/admin/propiedad/"+propiedadForm.getIdPropiedad() ;		
	}
	// This is a common way to resolve a url with a param
	@RequestMapping(method=RequestMethod.GET, value="/{idPropiedad}")
	public String displayPropiedad(@PathVariable Integer idPropiedad, Model model){
		Propiedad propiedad = propiedadService.getPropiedadById(idPropiedad);
		Localidad localidad = localidadService.getLocalidadById(propiedad.getDomicilio().getLocalidad().getIdLocalidad());
		propiedad.getDomicilio().setLocalidad(localidad);
		Set<String> images = context.getResourcePaths("/resources/images/"+propiedad.getIdPropiedad());
		if (images != null && images.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (String image:images) {
				sb.append(image).append(";");
			}
			String imgList = sb.toString();
			imgList = imgList.substring(0, imgList.length() - 1);
			model.addAttribute("imgList", imgList);
			_log.info(imgList);
		} else {
			model.addAttribute("imgList", "");
		}
		model.addAttribute(propiedad);
		return "admin/propiedad/display";
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
		return "admin/propiedad/form";
	}
	@RequestMapping(method=RequestMethod.GET, value="/remove")
	public String remove(@RequestParam("idPropiedad") Integer idPropiedad){
		Propiedad propiedad = propiedadService.getPropiedadById(idPropiedad);
		Localidad localidad = localidadService.getLocalidadById(propiedad.getDomicilio().getLocalidad().getIdLocalidad());
		propiedad.getDomicilio().setLocalidad(localidad);
		propiedadService.remove(propiedad);
		return "redirect:/admin/propiedad";
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
	private void validateAndSaveImage(MultipartFile image, String imageDir){
		FileOutputStream fileOutputStream = null;
		try {
			if (!StringValidation.isValidString(image.getContentType(), 0, 
					image.getContentType().length(), "^([^\\s]+).(jpg|jpeg|giff|png)$")){
				throw new Exception ("Only JPG, GIF, PNG images accepted.");
			}
			File fileDir = new File(imageDir);
			if (!fileDir.exists()){
				boolean createdDir = fileDir.mkdir();
				String message = createdDir ? "New dir ": "Cannot create dir: ";
				_log.info(message.concat(imageDir));
			}
			
			fileOutputStream = new FileOutputStream(
				new File(imageDir.concat("/").concat(image.getOriginalFilename())));
			fileOutputStream.write(image.getBytes());
			fileOutputStream.close();
			_log.info("Storing: "+imageDir);
		} catch (Exception e) {
			_log.info("Not Storing: "+imageDir);
			_log.info(e.getMessage());
		}
	}	
}
