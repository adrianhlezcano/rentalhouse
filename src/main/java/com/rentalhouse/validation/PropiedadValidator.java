package com.rentalhouse.validation;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import com.rentalhouse.form.PropiedadForm;
import com.rentalhouse.utils.StringValidation;

public class PropiedadValidator {
	public void validate(PropiedadForm propiedadForm, Errors errors) {
		
		if (!StringUtils.hasLength(propiedadForm.getPrecioAlquiler()) && 
				!StringUtils.hasLength(propiedadForm.getPrecioVenta())){
			errors.rejectValue("precioAlquiler", "precioAlquiler.invalid", "precioAlquiler.invalid");
			errors.rejectValue("precioVenta", "precioVenta.invalid", "precioVenta.invalid");
		}
		if (StringUtils.hasLength(propiedadForm.getPrecioAlquiler()) &&
				!StringValidation.isValidString(propiedadForm.getPrecioAlquiler(), 3, 10, "^[\\d\\.]+$")) {
			errors.rejectValue("precioAlquiler", "precioAlquiler.invalid", "precioAlquiler.invalid");
		} 
		if (StringUtils.hasLength(propiedadForm.getPrecioVenta()) &&
				!StringValidation.isValidString(propiedadForm.getPrecioVenta()
						, 3, 10, "^[\\d\\.]+$")) {
			errors.rejectValue("precioVenta", "precioVenta.invalid", "precioVenta.invalid");
		} 
		if (StringUtils.hasLength(propiedadForm.getSuperficie()) &&
				!StringValidation.isValidString(propiedadForm.getSuperficie(), 2, 8, "^[\\d\\.]+$")) {
			errors.rejectValue("superficie", "superficie.invalid", "superficie.invalid");
		} 
		if (StringUtils.hasLength(propiedadForm.getDetalle()) &&
				!StringValidation.isValidString(propiedadForm.getDetalle(), 0, 150)) {
			errors.rejectValue("detalle", "detalle.invalid", "detalle.invalid");
		}
		if (!StringValidation.isValidString(propiedadForm.getCalle(), 4, 30)) {
			errors.rejectValue("calle", "calle.invalid", "calle.invalid");
		} 
		if (!StringValidation.isValidString(propiedadForm.getNumero(), 1, 5, "^[\\d]+$")) {
			errors.rejectValue("numero", "numero.invalid", "numero.invalid");
		} 
		if (!StringValidation.isValidString(propiedadForm.getCodigoPostal(), 2, 8, "^[\\w]+$")) {
			errors.rejectValue("codigoPostal", "codigoPostal.invalid", "codigoPostal.invalid");
		}
		if (propiedadForm.getIdPropietario() == null || 0 == propiedadForm.getIdPropietario()) {
			errors.rejectValue("idPropietario", "idPropietario.invalid", "idPropietario.invalid");
		}
	}
}
