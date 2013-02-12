package com.rentalhouse.validation;

import org.springframework.validation.Errors;

import com.rentalhouse.form.InquilinoForm;
import com.rentalhouse.utils.StringValidation;

public class InquilinoValidator extends PersonaValidator {
	
	public void validate(InquilinoForm inquilinoForm, Errors errors) {
		super.validate(inquilinoForm, errors);		
		if (!StringValidation.isValidString(inquilinoForm.getIngreso(), 4, 10, "^[1-9][0-9]+$")) {
			errors.rejectValue("ingreso", "ingreso.invalid", "ingreso.invalid");
		} 
		if (!StringValidation.isValidString(inquilinoForm.getOcupacion(), 3, 30)) {
			errors.rejectValue("ocupacion", "ocupacion.invalid", "ocupacion.invalid");
		} 
		if (!StringValidation.isValidString(inquilinoForm.getLugarTrabajo(), 2, 25)) {
			errors.rejectValue("lugarTrabajo", "lugarTrabajo.invalid", "lugarTrabajo.invalid");
		}
	}
}
