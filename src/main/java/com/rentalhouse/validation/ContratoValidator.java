package com.rentalhouse.validation;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import com.rentalhouse.form.ContratoForm;
import com.rentalhouse.utils.StringValidation;

public class ContratoValidator {
	public void validate(ContratoForm contratoForm, Errors errors) {
		if (!StringValidation.isValidString(contratoForm.getComision(), 0, 10, "^[\\d\\.]+$")) {
			errors.rejectValue("comision", "comision.invalid", "comision.invalid");
		} 
		if (!StringValidation.isValidString(contratoForm.getDepositoGarantia(), 0, 10, "^[\\d\\.]+$")) {
			errors.rejectValue("depositoGarantia", "depositoGarantia.invalid", "depositoGarantia.invalid");
		} 
		if (!StringValidation.isValidString(contratoForm.getHonorarios(), 0, 10, "^[\\d\\.\\,]+$")) {
			errors.rejectValue("honorarios", "honorarios.invalid", "honorarios.invalid");
		} 
		if (!StringValidation.isValidString(contratoForm.getCuotas(), 1, 2, "^[0-9]{1,2}$")) {
			errors.rejectValue("cuotas", "cuotas.invalid", "cuotas.invalid");
		} 
		if (!StringUtils.hasLength(String.valueOf(contratoForm.getIdInquilino()))){
			errors.rejectValue("idInquilino", "idInquilino.invalid", "idInquilino.invalid");
		}
		if (!StringUtils.hasLength(String.valueOf(contratoForm.getIdPropiedad()))){
			errors.rejectValue("idPropiedad", "idPropiedad.invalid", "idPropiedad.invalid");
		}
		if (StringUtils.hasLength(contratoForm.getObservacion()) && 
				!StringValidation.isValidString(contratoForm.getObservacion(), 0, 100)){
			errors.rejectValue("observacion", "observacion.invalid", "observacion.invalid");
		}
	}
}
