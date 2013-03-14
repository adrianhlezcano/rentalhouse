package com.rentalhouse.validation;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import com.rentalhouse.form.GaranteForm;
import com.rentalhouse.utils.StringValidation;

public class GaranteValidator extends PersonaValidator {
	public void validate(GaranteForm garanteForm, Errors errors) {
		super.validate(garanteForm, errors);	
		if (!StringValidation.isValidString(garanteForm.getCalle(), 5, 30)) {
			errors.rejectValue("calle", "calle.invalid", "calle.invalid");
		} 
		if (!StringValidation.isValidString(garanteForm.getNumero(), 1, 5, "^[0-9]+$")) {
			errors.rejectValue("numero", "numero.invalid", "numero.invalid");
		} 
		if (!StringValidation.isValidString(garanteForm.getCodigoPostal(), 2, 8, "^[a-zA-Z0-9]+$")) {
			errors.rejectValue("codigoPostal", "codigoPostal.invalid", "codigoPostal.invalid");
		}		
		if (!StringValidation.isValidString(garanteForm.getValorGarantia(), 1, 10, "^[\\d\\.]+$")) {
			errors.rejectValue("valorGarantia", "valorGarantia.invalid", "valorGarantia.invalid");
		}
		if (StringUtils.hasLength(garanteForm.getDetalleGarantia()) && 
				!StringValidation.isValidString(garanteForm.getValorGarantia(), 2, 50)) {
			errors.rejectValue("detalleGarantia", "detalleGarantia.invalid", "detalleGarantia.invalid");
		}		 
	}	
}
