package Model;

import java.util.ArrayList;
import java.util.Date;

public class OrdenTrabajo {
	
	private Integer identificador;
	private String descripcion;
	private ArrayList<String> material;
	private ArrayList<Presupuesto> presupuesto;
	private Double coste;
	private String responsable;
	private Integer personal;
	private Date fechaInicio;
	private Integer duracion;
	private String estado;
	private Proceso proceso;
	
	//Constructor
	//Vacio para inicializar libremente con setters 
	public OrdenTrabajo() {
		
	}
	
	public OrdenTrabajo(Integer identificador, String descripcion, 
			ArrayList<String> material, ArrayList<Presupuesto> presupuesto,
			Double coste, String responsable, Integer personal, Date fechaInicio, Integer duracion, 
			String estado, Proceso proceso) {
		this.identificador = identificador;
		this.descripcion = descripcion;
		this.material = material;
		this.presupuesto = presupuesto;
		this.coste = coste;
		this.responsable = responsable;
		this.personal = personal;
		this.fechaInicio = fechaInicio;
		this.duracion = duracion;
		this.estado = estado;
		this.proceso = proceso;
	}
	
	
	//GETTERS Y SETTERS
	public Integer getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Integer identificador) {
		this.identificador = identificador;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public ArrayList<String> getMaterial() {
		return material;
	}

	public void setMaterial(ArrayList<String> material) {
		this.material = material;
	}

	public ArrayList<Presupuesto> getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(ArrayList<Presupuesto> presupuesto) {
		this.presupuesto = presupuesto;
	}

	public Double getCoste() {
		return coste;
	}

	public void setCoste(Double coste) {
		this.coste = coste;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public Integer getPersonal() {
		return personal;
	}

	public void setPersonal(Integer personal) {
		this.personal = personal;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}
	
	
	

}
