package com.rentalhouse.controller;

import java.util.HashMap;
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

import com.rentalhouse.domain.Garante;
import com.rentalhouse.domain.Inquilino;
import com.rentalhouse.domain.Persona;
import com.rentalhouse.form.InquilinoForm;
import com.rentalhouse.service.PersonaService;
import com.rentalhouse.utils.AppConstant;
import com.rentalhouse.validation.InquilinoValidator;

@Controller
@RequestMapping("/admin/inquilino")
public class InquilinoController {
	@Autowired
	@Qualifier("personaService")
	private PersonaService personaService;	
	private Log _log = LogFactoryImpl.getLog(getClass());

	@RequestMapping(method=RequestMethod.GET)
	public String show(Model model){		
		model.addAttribute(personaService.getAll(0, 10, false, Inquilino.class));
		return "admin/inquilino/list";
	}	
	@RequestMapping(method=RequestMethod.GET, params="new")
	public String setupForm(Model model){
		model.addAttribute(new InquilinoForm());
		return "admin/inquilino/form";
	}
	@RequestMapping(method=RequestMethod.POST)
	public String processForm(@ModelAttribute InquilinoForm inquilinoForm, BindingResult result){
		new InquilinoValidator().validate(inquilinoForm, result);
		if(result.hasErrors()){			
			return "admin/inquilino/form";
		}	
		Garante garante = (Garante) personaService.get(inquilinoForm.getIdGarante());
		if (inquilinoForm.getAction().equals(AppConstant.INSERT)) {  
			if (personaService.getByDni(Integer.valueOf(inquilinoForm.getDni()), Persona.class) != null){
				// agregar error de que ya existe una persona con mismo dni
				return "admin/inquilino/form";
			}else{
				Inquilino inquilino = inquilinoForm.toInquilino(new Inquilino(), garante);
				personaService.update(inquilino);
			}
		}else if (inquilinoForm.getAction().equals(AppConstant.UPDATE)){
			Inquilino inquilino = (Inquilino) personaService.get(inquilinoForm.getIdPersona());
			personaService.update(inquilinoForm.toInquilino(inquilino, garante));
		}		
		return "redirect:/admin/inquilino/"+inquilinoForm.getDni();		
	}
	// This is a common way to resolve a url with a param
	@RequestMapping(method=RequestMethod.GET, value="/{dni}")
	public String displayPersona(@PathVariable Integer dni, Model model){
		Inquilino inquilino = (Inquilino) personaService.getByDni(dni, Inquilino.class);
		model.addAttribute(inquilino);
		return "admin/inquilino/display";
	}
	@RequestMapping(method=RequestMethod.GET, value="/edit/{dni}")
	public String edit(@PathVariable Integer dni, Model model){
		Inquilino inquilino = (Inquilino) personaService.getByDni(dni, Inquilino.class);
		for(Garante garante : inquilino.getGarantes()){
		    Garante g = (Garante) personaService.get(garante.getIdPersona());	
		    inquilino.getGarantes().add(g);
		}					
		model.addAttribute(new InquilinoForm(inquilino));
		return "admin/inquilino/form";
	}
	@RequestMapping(method=RequestMethod.GET, value="/remove")
	public String remove(@RequestParam("idPersona") Integer idPersona){
		Inquilino inquilino = (Inquilino) personaService.get(idPersona);
		personaService.delete(inquilino);
		return "redirect:/admin/inquilino";
	}
	@RequestMapping(method=RequestMethod.GET, value="/get")
	public @ResponseBody Map<String, String> getInquilino(@RequestParam("dni") Integer dni){
		Persona persona = personaService.getByDni(dni, Inquilino.class);
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
//		model.put("page", page);
//		int firstRow = (page == null || page.isEmpty())? 0: Integer.parseInt(page); 
		model.put("personList", personaService.findPersonaByFieldValue(fieldName, fieldValue, Inquilino.class));
		return model;
	}
	
}
