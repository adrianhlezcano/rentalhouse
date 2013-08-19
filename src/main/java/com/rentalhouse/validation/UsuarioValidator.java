package com.rentalhouse.validation;

import org.springframework.validation.Errors;

import com.rentalhouse.form.PersonaForm;
import com.rentalhouse.form.UsuarioForm;
import com.rentalhouse.utils.StringValidation;

public class UsuarioValidator {
	public void validate(UsuarioForm usuarioForm, Errors errors) {
		if (!StringValidation.isValidString(usuarioForm.getApellido(), 5, 30)) {
			errors.rejectValue("apellido", "apellido.invalid", "apellido.invalid");
		} 
		if (!StringValidation.isValidString(usuarioForm.getNombre(), 5, 50)) {
			errors.rejectValue("nombre", "nombre.invalid", "nombre.invalid");
		}
		if (!StringValidation.isValidString(String.valueOf(usuarioForm.getDni()), 7, 9, "^[0-9]+$")) {
			errors.rejectValue("dni", "dni.invalid", "dni.invalid");
		}
		if(!StringValidation.isValidString(usuarioForm.getEmail(), 10, 60, "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")){
			errors.rejectValue("email", "email.invalid", "email.invalid");
		}	
		if (!StringValidation.isValidString(usuarioForm.getUsername(), 5, 25)) {
			errors.rejectValue("username", "username.invalid", "username.invalid");
		}
		if (!StringValidation.isValidString(usuarioForm.getPassword(), 5, 25)) {
			errors.rejectValue("password", "password.invalid", "password.invalid");
		}
		if (!usuarioForm.getPassword().equals(usuarioForm.getPassword2())){
			errors.rejectValue("password", "password.notEquals", "password.notEquals");
			errors.rejectValue("password2", "password.notEquals", "password.notEquals");
		}
		if (!UsuarioForm.PREGUNTAS.contains(usuarioForm.getPreguntaSeguridad())){
			errors.rejectValue("preguntaSeguridad", "preguntaSeguridad.invalid", "preguntaSeguridad.invalid");
		}
		if (!StringValidation.isValidString(usuarioForm.getRespuestaSeguridad(), 3, 25)){
			errors.rejectValue("respuestaSeguridad", "respuestaSeguridad.invalid", "respuestaSeguridad.invalid");
		}		
	}
	public void validateCredentials(UsuarioForm usuarioForm, Errors errors) {		
		if (!StringValidation.isValidString(usuarioForm.getUsername(), 5, 25)) {
			errors.rejectValue("username", "username.invalid", "username.invalid");
		}	
		if(!StringValidation.isValidString(usuarioForm.getEmail(), 10, 60, "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")){
			errors.rejectValue("email", "email.invalid", "email.invalid");
		}			
		if (!UsuarioForm.PREGUNTAS.contains(usuarioForm.getPreguntaSeguridad())){
			errors.rejectValue("preguntaSeguridad", "preguntaSeguridad.invalid", "preguntaSeguridad.invalid");
		}
		if (!StringValidation.isValidString(usuarioForm.getRespuestaSeguridad(), 3, 25)){
			errors.rejectValue("respuestaSeguridad", "respuestaSeguridad.invalid", "respuestaSeguridad.invalid");
		}		
	}
}
