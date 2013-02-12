package com.rentalhouse.validation;

import org.springframework.validation.Errors;

import com.rentalhouse.form.PropietarioForm;
import com.rentalhouse.utils.StringValidation;

public class PropietarioValidator extends PersonaValidator{

	public void validate(PropietarioForm propietarioForm, Errors errors) {
		super.validate(propietarioForm, errors);	
		if (!StringValidation.isValidString(propietarioForm.getCalle(), 5, 30)) {
			errors.rejectValue("calle", "calle.invalid", "calle.invalid");
		} 
		if (!StringValidation.isValidString(propietarioForm.getNumero(), 1, 5, "^[0-9]+$")) {
			errors.rejectValue("numero", "numero.invalid", "numero.invalid");
		} 
		if (!StringValidation.isValidString(propietarioForm.getCodigoPostal(), 2, 8, "^[a-zA-Z0-9]+$")) {
			errors.rejectValue("codigoPostal", "codigoPostal.invalid", "codigoPostal.invalid");
		}		
	}	
}
