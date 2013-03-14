package com.rentalhouse.validation;

import org.springframework.validation.Errors;

import com.rentalhouse.form.PersonaForm;
import com.rentalhouse.utils.StringValidation;

public class PersonaValidator {
	public void validate(PersonaForm personaForm, Errors errors) {
		if (!StringValidation.isValidString(personaForm.getApellido(), 3, 30)) {
			errors.rejectValue("apellido", "apellido.invalid", "apellido.invalid");
		} 
		if (!StringValidation.isValidString(personaForm.getNombre(), 3, 50)) {
			errors.rejectValue("nombre", "nombre.invalid", "nombre.invalid");
		}
		if (!StringValidation.isValidString(String.valueOf(personaForm.getDni()), 7, 9, "^[0-9]+$")) {
			errors.rejectValue("dni", "dni.invalid", "dni.invalid");
		}
		if (!StringValidation.isValidString(personaForm.getCuit(), 11, 14, "^[0-9\\-]+$")){
			errors.rejectValue("cuit", "cuit.invalid", "cuit.invalid");
		}
		if (!StringValidation.isValidString(personaForm.getTelefono(), 10, 14, "^[0-9\\-\\(\\)]+$")){
			errors.rejectValue("telefono", "telefono.invalid", "telefono.invalid");
		}
		if(!StringValidation.isValidString(personaForm.getEmail(), 10, 60, "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")){
			errors.rejectValue("email", "email.invalid", "email.invalid");
		}	
		
	}
}
