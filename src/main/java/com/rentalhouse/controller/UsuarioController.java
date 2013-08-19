package com.rentalhouse.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rentalhouse.domain.Usuario;
import com.rentalhouse.form.UsuarioForm;
import com.rentalhouse.service.PersonaService;
import com.rentalhouse.utils.AppConstant;
import com.rentalhouse.validation.UsuarioValidator;
import javax.servlet.http.*;

@Controller()
@RequestMapping("/admin/usuario")
public class UsuarioController {
	@Autowired
	@Qualifier("personaService")
	private PersonaService personaService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(ModelMap model){
		model.addAttribute(new UsuarioForm());
		return "admin/usuario/login";
	}
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String handlerLogin(@ModelAttribute UsuarioForm usuarioForm, BindingResult result,
			HttpServletRequest request){
		Usuario user = personaService.getUsuarioForLogin(usuarioForm.getUsername(), usuarioForm.getPassword());
		if (user != null){			
			synchronized (request.getSession()) {
				request.getSession().setAttribute("user", user);					
			}		
			return "redirect:/admin/propiedad";
		}
		result.addError(new ObjectError("usuario", "El usuario no existe o la password es incorrecta."));
		return "admin/usuario/login";
	}
	@RequestMapping(value="/registrar", method=RequestMethod.GET)
	public String register(ModelMap model){
		model.addAttribute(new UsuarioForm(AppConstant.INSERT));
		return "admin/usuario/register";		
	}
	@RequestMapping(value="/registrar", method=RequestMethod.POST)
	public String handleRegister(@ModelAttribute UsuarioForm usuarioForm, BindingResult result,
			HttpServletRequest request){
		new UsuarioValidator().validate(usuarioForm, result);
		if(result.hasErrors()){
			return "admin/usuario/register";
		}
		
		Usuario user = usuarioForm.toUsuario(new Usuario());
		if (!personaService.isNewUsuario(user)){
			result.addError(new ObjectError("usuario", "El usuario ya existe."));
			return "admin/usuario/register";
		}else{
			personaService.save(user);
			synchronized (request.getSession()) {
				request.getSession().setAttribute("user", user);					
			}			
			return "redirect:/admin/propiedad";
		}				
	}
	@RequestMapping(value="/obtenerCredencial", method=RequestMethod.GET)
	public String getCredential(ModelMap model){
		model.addAttribute(new UsuarioForm());
		return "admin/usuario/getCredential";		
	}
	@RequestMapping(value="/obtenerCredencial", method=RequestMethod.POST)
	public String getCredential(@ModelAttribute UsuarioForm usuarioForm, BindingResult result,
			HttpServletRequest request){
		new UsuarioValidator().validateCredentials(usuarioForm, result);
		if(result.hasErrors()){
			return "admin/usuario/getCredential";
		}
		Usuario user = personaService.getUsuarioWithCredentials
		(usuarioForm.getUsername(), usuarioForm.getRespuestaSeguridad(), usuarioForm.getEmail());
		
		if (user == null){
			result.addError(new ObjectError("usuario", "El usuario no ya existe."));
			return "admin/usuario/getCredential";
		}else{			
			synchronized (request.getSession()) {
				request.getSession().setAttribute("user", user);					
			}			
			return "redirect:/admin/propiedad";
		}				
	}
}