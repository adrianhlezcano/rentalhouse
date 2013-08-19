package com.rentalhouse.controller;

import java.util.HashMap;
import java.util.List;
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

import com.rentalhouse.domain.Contrato;
import com.rentalhouse.domain.Cuota;
import com.rentalhouse.domain.EstadoContrato;
import com.rentalhouse.domain.Garante;
import com.rentalhouse.domain.Inquilino;
import com.rentalhouse.domain.OperacionPropiedad;
import com.rentalhouse.domain.Propiedad;
import com.rentalhouse.form.ContratoForm;
import com.rentalhouse.form.InquilinoForm;
import com.rentalhouse.service.ContratoService;
import com.rentalhouse.service.PersonaService;
import com.rentalhouse.service.PropiedadService;
import com.rentalhouse.utils.AppConstant;
import com.rentalhouse.validation.ContratoValidator;
import com.sun.corba.se.impl.copyobject.JavaStreamObjectCopierImpl;

@Controller()
@RequestMapping("/admin/contrato")
public class ContratoController {
	@Autowired
	@Qualifier("contratoService")
	private ContratoService contratoService;
	@Autowired
	@Qualifier("propiedadService")
	private PropiedadService propiedadService;
	@Autowired
	@Qualifier("personaService")
	private PersonaService personaService;
	private Log _log = LogFactoryImpl.getLog(getClass());
	
	@RequestMapping(method=RequestMethod.GET)
	public String show(Model model){
		model.addAttribute(contratoService.getContratos(0, 10));
		return "admin/contrato/list";
	}	
	@RequestMapping(method=RequestMethod.GET, params="new")
	public String setupForm(Model model){
		ContratoForm form = new ContratoForm();
		form.setPropiedadList(propiedadService.getPropidadByField("operacionPropiedad", OperacionPropiedad.ALQUILA));
		model.addAttribute(form);
		return "admin/contrato/form";
	}	
	@RequestMapping(method=RequestMethod.POST)
	public String processForm(@ModelAttribute("contratoForm") ContratoForm form, BindingResult result){
		new ContratoValidator().validate(form, result);
		if (result.hasErrors()){
			form.setPropiedadList(propiedadService.getPropidadByField("operacionPropiedad", OperacionPropiedad.ALQUILA));
			return "admin/contrato/form";
		}
		Inquilino inquilino = (Inquilino) personaService.get(form.getIdInquilino());
		Propiedad propiedad = propiedadService.get(form.getIdPropiedad());
		if(form.getAction().equals(AppConstant.INSERT)){
			Contrato contrato = form.toContrato(new Contrato(), propiedad, inquilino);
			propiedad.setPublicar(Boolean.FALSE);
			contratoService.saveContrato(contrato);
			propiedadService.update(propiedad);
			form.setIdContrato(contrato.getIdContrato());
		}
		return "redirect:/admin/contrato/"+form.getIdContrato();
	}
	@RequestMapping(method=RequestMethod.GET, value="/{idContrato}")
	public String displayPropiedad(@PathVariable Integer idContrato, Model model){
		_log.info("/admin/contrato/"+idContrato);
		model.addAttribute(contratoService.getContratoById(idContrato));
		return "admin/contrato/display";
	}
	@RequestMapping(method=RequestMethod.GET, value="/cancel/{idContrato}")
	public String cancelar(@PathVariable Integer idContrato, Model model){
		Contrato contrato = contratoService.getContratoById(idContrato);
		contrato.setEstadoContrato(EstadoContrato.CANCELADO);
		contratoService.updateContrato(contrato);
		return "redirect:/admin/contrato";
	}
	@RequestMapping(method=RequestMethod.GET, value="/cuotas/{idContrato}")
	public String displayCuotas(@PathVariable Integer idContrato, @RequestParam("page")Integer page, Model model){
		model.addAttribute(contratoService.getCuotaByIdContrato(idContrato, page * 10, 10));
		Integer totalRows = contratoService.numberOfCuotasByContrato(idContrato);
		model.addAttribute("contrato", contratoService.getContratoById(idContrato));
		model.addAttribute("previous", page == 0 ? -1 : page - 1);
		model.addAttribute("next",page * 10 > totalRows.intValue() - 10? -1 : page + 1);
		return "admin/contrato/cuotas";
	}
	@RequestMapping(method=RequestMethod.POST, value="/cuotas/{idContrato}")
	public String payCuota(@PathVariable Integer idContrato, @RequestParam("idCuota")Integer idCuota,
			@RequestParam("importePagado")Integer importePagado, @RequestParam("page")Integer page,
			Model model){
		Cuota cuota = (Cuota) contratoService.getCuota(idCuota);
		cuota.pay(importePagado);		
		contratoService.updateCuota(cuota);
		return "redirect:/admin/contrato/cuotas/"+idContrato+"?page="+page;		
	}
	@RequestMapping(method=RequestMethod.GET, value="/search")
	public @ResponseBody Map<String, Object> search(
		@RequestParam("fieldName")String fieldName, @RequestParam("fieldValue") String fieldValue,
		@RequestParam("page")String page){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("page", page);
		int firstRow = (page == null || page.isEmpty())? 0: Integer.parseInt(page);
		List<Contrato> contratoList = contratoService.getContratosByFieldValue(fieldName, fieldValue, firstRow, 10);
		model.put("contratoList", contratoList);
		return model;
	}
}
