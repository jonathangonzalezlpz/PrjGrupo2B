package SubsistemaGestionProcesos;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import Exception.CustomException;
import Model.Incidencia;
import Model.OrdenTrabajo;
import Model.Proceso;

public class SubsistemaGestionProcesos implements InterfaceSubsistemaGestionProcesos{
	//Almacenamiento
	private HashMap<Integer,Proceso> procesos;
	
	public SubsistemaGestionProcesos() {
		this.procesos=new HashMap<>();
	}
	
	//Métodos
	@Override
	public Proceso inicializar(Integer identificador, String nombreProceso, String descripcion, Double coste,
			Double estimado, String estado, String responsable, String servicio, ArrayList<Incidencia> incidencias,
			ArrayList<OrdenTrabajo> ordenesTrabajo, Date fechaInicio) throws CustomException{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Proceso crear(Proceso proceso) throws CustomException{
		//Conflictos
		if(proceso==null) {
			throw new CustomException("Proceso nulo",1);
		}else {
			if(_camposNulos(proceso)) {
				throw new CustomException("Proceso con todos los campos nulos",1);
			}else if(this.procesos.containsKey(proceso.getIdentificador())) {
				throw new CustomException("Id ya registrado", 2);
			}else if(_camposObligatorios(proceso)) {
				throw new CustomException("Faltan campos Obligatorios {id, nombre, descripcion , estimado, estado, responsable, servicio, fechaInicio}",1);
			}
		}
		
		//Todo correcto
		this.procesos.put(proceso.getIdentificador(), proceso);
		return proceso;
	}

	@Override
	public Proceso actualizar(Proceso proceso) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Proceso> buscar(Proceso filtro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Proceso asignarIncidencia(Proceso proceso, ArrayList<Incidencia> incidencias) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Proceso asignarOrdenTrabajo(Proceso proceso, ArrayList<OrdenTrabajo> ordenesTrabajo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//Métodos privados
	private Boolean _camposNulos(Proceso proceso) {
		if(proceso.getIdentificador()==null && proceso.getNombre()==null && proceso.getDescripcion()==null && 
				proceso.getCoste()==null && proceso.getEstado()==null && proceso.getEstimado()==null &&
				proceso.getFechaInicio()==null && proceso.getIncidencias()==null && proceso.getOrdenesTrabajo()==null)
			return true;
		return false;
	}
	
	private Boolean _camposObligatorios(Proceso proceso) {
		if(proceso.getIdentificador()==null || proceso.getNombre()==null || proceso.getDescripcion()==null ||
				proceso.getEstimado()==null || proceso.getEstado()==null || proceso.getFechaInicio()==null ||
				proceso.getResponsable()==null || proceso.getServicio()==null)
			return true;
		return false;
	}

}
