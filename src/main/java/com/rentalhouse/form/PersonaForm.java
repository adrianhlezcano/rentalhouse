package com.rentalhouse.form;

import java.util.Arrays;
import java.util.List;

import com.rentalhouse.domain.Persona;
import com.rentalhouse.domain.TipoDni;
import com.rentalhouse.domain.TipoTelefono;
import com.rentalhouse.utils.AppConstant;
import com.rentalhouse.utils.DateUtils;

public class PersonaForm {	
	private Integer idPersona;
	private String action;
	private String nombre;
	private String apellido;
	private String tipoDni;
	private String dni;
	private String cuit;
	private Integer diaNacimiento;
	private Integer mesNacimiento;
	private Integer anioNacimiento;
	private String telefono;
	private String tipoTelefono;
	private String email;
	private List<TipoDni> tiposDni = Arrays.asList(TipoDni.values());
	private List<TipoTelefono> tiposTelefono = Arrays.asList(TipoTelefono.values());
	
	public PersonaForm(){		
		setDiaNacimiento(25);
		setMesNacimiento(10);
		setAnioNacimiento(1985);
		setAction(AppConstant.INSERT);
	}	
	public PersonaForm(Persona persona){
		setIdPersona(persona.getIdPersona());
		setNombre(persona.getNombre());
		setApellido(persona.getApellido());
		setTipoDni(persona.getTipoDni().getValue());
		setDni(String.valueOf(persona.getDni()));
		setCuit(persona.getCuit());
		setDiaNacimiento(DateUtils.getDate(persona.getFechaNacimiento()));
		setMesNacimiento(DateUtils.getMonth(persona.getFechaNacimiento()));
		setAnioNacimiento(DateUtils.getYear(persona.getFechaNacimiento()));
		setEmail(persona.getEmail());
		setTipoTelefono(persona.getTipoTelefono().getValue());
		setTelefono(persona.getTelefono());	
		setAction(AppConstant.UPDATE);
	}	
	public Integer getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTipoDni() {
		return tipoDni;
	}
	public void setTipoDni(String tipoDni) {
		this.tipoDni = tipoDni;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public Integer getDiaNacimiento() {
		return diaNacimiento;
	}
	public void setDiaNacimiento(Integer diaNacimiento) {
		this.diaNacimiento = diaNacimiento;
	}
	public Integer getMesNacimiento() {
		return mesNacimiento;
	}
	public void setMesNacimiento(Integer mesNacimiento) {
		this.mesNacimiento = mesNacimiento;
	}
	public Integer getAnioNacimiento() {
		return anioNacimiento;
	}
	public void setAnioNacimiento(Integer anioNacimiento) {
		this.anioNacimiento = anioNacimiento;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getTipoTelefono() {
		return tipoTelefono;
	}
	public void setTipoTelefono(String tipoTelefono) {
		this.tipoTelefono = tipoTelefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Persona toPersona(Persona p){
		p.setIdPersona(idPersona);
		p.setApellido(formatName(apellido));
		p.setNombre(formatName(nombre));
		p.setDni(Integer.valueOf(dni));
		p.setTipoDni(TipoDni.valueOf(tipoDni.toUpperCase()));
		p.setCuit(cuit);
		p.setEmail(email);
		p.setTelefono(telefono);
		p.setTipoTelefono(TipoTelefono.valueOf(tipoTelefono.toUpperCase()));
		p.setFechaNacimiento(
			DateUtils.fromIntegertoDate(diaNacimiento, mesNacimiento, anioNacimiento));		
		return p;
	}
	public List<TipoTelefono> getTiposTelefono() {
		return tiposTelefono;
	}
	public List<TipoDni> getTiposDni() {
		return tiposDni;
	}
	public List<Integer> getAnios() {
		return DateUtils.getYears();
	}
	public List<Integer> getMeses() {
		return DateUtils.getMonths();
	}
	public List<Integer> getDias() {
		return DateUtils.getDays();
	}
	private final String formatName(String name){
		String[] names = name.split(" ");
		StringBuilder sb = new StringBuilder(names.length);
		for (int i=0; i<names.length; i++) {
			names[i] = names[i].substring(0, 1).toUpperCase().concat(names[i].substring(1));
			sb.append(names[i]).append(" ");
		}
		return sb.toString().trim();
	}
	
}
