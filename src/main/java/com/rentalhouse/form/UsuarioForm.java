package com.rentalhouse.form;

import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

import com.rentalhouse.domain.TipoDni;
import com.rentalhouse.domain.TipoTelefono;
import com.rentalhouse.domain.Usuario;
import com.rentalhouse.utils.AppConstant;
import com.rentalhouse.utils.DateUtils;

public class UsuarioForm extends PersonaForm {
	private String username;
	private String password;
	private String password2;
	private String preguntaSeguridad;
	private String respuestaSeguridad;
	public static final List<String> PREGUNTAS = new ArrayList<String>(4);
	
	static{
		PREGUNTAS.add("Nombre de su mascota favorita?");
		PREGUNTAS.add("Apodo de su padre/madre?");
		PREGUNTAS.add("Nombre de su dibujo animado favorito?");
		PREGUNTAS.add("Nombre de su equipo favorito?");
	}
	
	public UsuarioForm(String action){
		setAction(action);
	}
	public UsuarioForm(){
		super();
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getPreguntaSeguridad() {
		return preguntaSeguridad;
	}
	public void setPreguntaSeguridad(String preguntaSeguridad) {
		this.preguntaSeguridad = preguntaSeguridad;
	}
	public String getRespuestaSeguridad() {
		return respuestaSeguridad;
	}
	public void setRespuestaSeguridad(String respuestaSeguridad) {
		this.respuestaSeguridad = respuestaSeguridad;
	}
	public List<String> getPreguntas() {
		return PREGUNTAS;
	}	
	public Usuario toUsuario(Usuario usuario){
		usuario.setIdPersona(getIdPersona());
		usuario.setApellido(getApellido());
		usuario.setNombre(getNombre());
		usuario.setDni(Integer.valueOf(getDni()));
		usuario.setTipoDni(TipoDni.valueOf(getTipoDni().toUpperCase()));		
		usuario.setEmail(getEmail());		
		usuario.setUsername(getUsername());
		usuario.setPassword(getPassword());
		usuario.setPreguntaSeguridad(getPreguntaSeguridad());
		usuario.setRespuestaSeguridad(getRespuestaSeguridad());
		usuario.setFechaUltimoAcceso(Calendar.getInstance().getTime());
		if (getAction().equals(AppConstant.UPDATE)){
			usuario.setFechaNacimiento(DateUtils
					.fromIntegertoDate(getDiaNacimiento(), getMesNacimiento(),
							getAnioNacimiento()));
			usuario.setCuit(getCuit());
			usuario.setTelefono(getTelefono());
			usuario.setTipoTelefono(TipoTelefono.valueOf(getTipoTelefono()));
		}
		return usuario;		
	}
}
