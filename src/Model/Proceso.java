package Model;

import java.util.ArrayList;
import java.util.Date;

public class Proceso {
	public static final String [] tipoEstado = {"Pendiente","En ejecución","Finalizado"};
	public static final String [] tipoServicio = {"Iluminación","Parques","Asfalto","Alcantarillado", "Tráfico", "Suministro", "Otra"};
	private Integer identificador;
	private String nombre;
	private String descripcion;
	private Double coste;
	private Double estimado;
	private String estado;
	private String responsable;
	private String servicio;
	private Date fechaInicio;
	private ArrayList<OrdenTrabajo> ordenesTrabajo;
	private ArrayList<Incidencia> incidencias;
	
	public Proceso() {
	}
	
	public Proceso(Integer identificador, String nombreProceso, String descripcion, 
			Double coste, Double estimado, String estado, String responsable, String servicio, 
			ArrayList<Incidencia> incidencias, ArrayList<OrdenTrabajo> ordenesTrabajo, Date fechaInicio) {
		this.identificador=identificador;
		this.nombre=nombreProceso;
		this.descripcion=descripcion;
		this.coste=coste;
		this.estimado=estimado;
		this.estado=estado;
		this.responsable=responsable;
		this.servicio=servicio;
		this.incidencias=incidencias;
		this.ordenesTrabajo=ordenesTrabajo;
		this.fechaInicio=fechaInicio;
	}
	
	//Setters
	public void setIdentificador(Integer identificador) {
		this.identificador=identificador;
	}
	
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion=descripcion;
	}
	
	public void setCoste(Double coste) {
		this.coste=coste;
	}
	
	public void setEstimado(Double estimado) {
		this.estimado=estimado;
	}
	
	public void setResponsable(String responsable) {
		this.responsable=responsable;
	}
	
	public void setEstado(String estado) {
		this.estado=estado;
	}
	
	public void setServicio(String servicio) {
		this.servicio=servicio;
	}
	
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio=fechaInicio;
	}
	
	public void setIncidencias(ArrayList<Incidencia> incidencias) {
		this.incidencias=incidencias;
	}
	
	public void setOrdenesTrabajo(ArrayList<OrdenTrabajo> ordenesTrabajo) {
		this.ordenesTrabajo=ordenesTrabajo;
	}
	
	//Getters
	public Integer getIdentificador() {
		return this.identificador;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public Double getCoste() {
		return this.coste;
	}
	
	public Double getEstimado() {
		return this.estimado;
	}
	
	public String getResponsable() {
		return this.responsable;
	}
	
	public String getEstado() {
		return this.estado;
	}
	
	public String getServicio() {
		return this.servicio;
	}
	
	public Date getFechaInicio() {
		return this.fechaInicio;
	}
	
	public ArrayList<Incidencia> getIncidencias(){
		return this.incidencias;
	}
	
	public ArrayList<OrdenTrabajo> getOrdenesTrabajo(){
		return this.ordenesTrabajo;
	}
}
