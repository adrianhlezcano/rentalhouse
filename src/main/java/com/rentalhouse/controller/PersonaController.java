package com.rentalhouse.controller;



import java.util.List;

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

import com.rentalhouse.domain.Persona;
import com.rentalhouse.domain.Usuario;
import com.rentalhouse.form.PersonaForm;
import com.rentalhouse.service.PersonaService;
import com.rentalhouse.utils.AppConstant;
import com.rentalhouse.validation.PersonaValidator;

@Controller
@RequestMapping("/persona")
public class PersonaController {
	@Autowired
	@Qualifier("personaService")
	private PersonaService personaService;	

	@RequestMapping(method=RequestMethod.GET)
	public String show(Model model){
		List<Persona> personas = personaService.getAll(0, 10, false, Usuario.class);
		model.addAttribute(personas);
		return "persona/list";
	}	
	@RequestMapping(method=RequestMethod.GET, params="new")
	public String setupForm(Model model){
		model.addAttribute(new PersonaForm());
		return "persona/form";
	}
	@RequestMapping(method=RequestMethod.POST)
	public String processForm(@ModelAttribute PersonaForm personaForm, BindingResult result){
		new PersonaValidator().validate(personaForm, result);
		if(result.hasErrors()){			
			return "persona/form";
		}		
		if (personaForm.getAction().equals(AppConstant.INSERT)) {  
			if (personaService.getByDni(Integer.valueOf(personaForm.getDni()), Usuario.class) != null){
				// agregar error de que ya existe una persona con mismo dni
				return "persona/form";
			}else{
				personaService.save(personaForm.toPersona(new Persona()));
			}
		}else if (personaForm.getAction().equals(AppConstant.UPDATE)){
			Persona persona = personaService.get(personaForm.getIdPersona());
			personaService.update(personaForm.toPersona(persona));
		}		
		return "redirect:/persona/"+personaForm.getDni();		
	}
	// This is a common way to resolve a url with a param
	@RequestMapping(method=RequestMethod.GET, value="/{dni}")
	public String displayPersona(@PathVariable Integer dni, Model model){
		model.addAttribute(personaService.getByDni(dni, Usuario.class));
		return "persona/display";
	}
	@RequestMapping(method=RequestMethod.GET, value="/edit/{dni}")
	public String edit(@PathVariable Integer dni, Model model){
		Persona persona = personaService.getByDni(dni, Usuario.class);
		model.addAttribute(new PersonaForm(persona));
		return "persona/form";
	}
	@RequestMapping(method=RequestMethod.GET, value="/remove")
	public String remove(@RequestParam("idPersona") Integer idPersona){
		Persona persona = personaService.get(idPersona);
		personaService.delete(persona);
		return "redirect:/persona";
	}
	
	
}
