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
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Métodos
	@Override
	public Proceso inicializar(Integer identificador, String nombreProceso, String descripcion, Double coste,
			Double estimado, String estado, String responsable, String servicio, ArrayList<Incidencia> incidencias,
			ArrayList<OrdenTrabajo> ordenesTrabajo, Date fechaInicio) throws CustomException{
		
				
		if(identificador!=null && identificador<0) {
			throw new CustomException("El identificador no puede ser negativo",1);
		}else if(nombreProceso!=null && nombreProceso.length()>100) {
			throw new CustomException("Nombre superior a 100 characters",1);
		}else if(descripcion!=null && descripcion.length()>500) {
			throw new CustomException("Descipcion superior a 500 characters",1);
		}else if(responsable!=null && responsable.length()>100) {
			throw new CustomException("Responsable superior a 100 characters",1);
		}else if(estado!=null && !_estadoValido(estado)) {
			//Estado no existente
			//Por defecto será "Pendiente"
			estado="Pendiente";
		}else if(servicio!=null && !_servicioValido(servicio)) {
			//Servicio no existente
			//Por defecto será "Otro"
			servicio="Otro";
		}
		
		return new Proceso(identificador,nombreProceso,descripcion,coste,estimado,estado,responsable,servicio,incidencias,ordenesTrabajo,fechaInicio);
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
			}else if(!proceso.getEstado().equals("Pendiente")) {
				throw new CustomException("El estado no puede ser distinto de Pendiente",1);
			}
		}
		
		//Todo correcto
		this.procesos.put(proceso.getIdentificador(), proceso);
		return proceso;
	}

	@Override
	public Proceso actualizar(Proceso proceso) throws CustomException{
		Proceso anterior=new Proceso();
		if(proceso!=null) {
			if(this.procesos.containsKey(proceso.getIdentificador())) {
				anterior=this.procesos.get(proceso.getIdentificador());
				if(proceso.getNombre()!=null) 
					anterior.setNombre(proceso.getNombre());
				if(proceso.getDescripcion()!=null)
					anterior.setDescripcion(proceso.getDescripcion());
				if(proceso.getCoste()!=null)
					anterior.setCoste(proceso.getCoste());
				if(proceso.getEstimado()!=null)
					anterior.setEstimado(proceso.getEstimado());
				if(proceso.getEstado()!=null)
					anterior.setEstado(proceso.getEstado());
				if(proceso.getResponsable()!=null)
					anterior.setResponsable(proceso.getResponsable());
				if(proceso.getServicio()!=null)
					anterior.setServicio(proceso.getServicio());
				
				//TEN SENTIDO CAMBIAR A FECHA INICIO¿?¿?¿?¿?¿?¿?
				//TEN SENTIDO CAMBIAR AQUÍ INCIDENCIAS E ORDENES TRABAJO CANDO XA HAI MÉTODOS PARA ASIGNAR ¿??¿?¿?¿?¿?
			}
		}
		
		return anterior;
	}

	@Override
	public ArrayList<Proceso> buscar(Proceso filtro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Proceso asignarIncidencia(Proceso proceso, ArrayList<Incidencia> incidencias) throws CustomException{
		//El proceso tiene que ser comprobado antes
		if(proceso==null) {
			throw new CustomException("Proceso nulo", 1);
		}else if(this._camposNulos(proceso)) {
			throw new CustomException("Proceso con todos los campos nulos", 1);
		}else if(!this.procesos.containsKey(proceso.getIdentificador())){
			throw new CustomException("Proceso no registrado", 4);
		}
		//CREO QUE ME FALTAN COMPROBACIÓNS
		
		//SUPOÑENDO QUE AS INCIDENCIAS XA VEÑEN VALIDADADAS E QUE SON INCIDENCIAS SEN ASIGNAR
		proceso.setIncidencias(incidencias);
			
		return proceso;
	}

	@Override
	public Proceso asignarOrdenTrabajo(Proceso proceso, ArrayList<OrdenTrabajo> ordenesTrabajo) throws CustomException{
		//El proceso tiene que ser comprobado antes
		if(proceso==null) {
			throw new CustomException("Proceso nulo", 1);
		}else if(this._camposNulos(proceso)) {
			throw new CustomException("Proceso con todos los campos nulos", 1);
		}else if(!this.procesos.containsKey(proceso.getIdentificador())){
			throw new CustomException("Proceso no registrado", 4);
		}
		//CREO QUE ME FALTAN COMPROBACIÓNS
			
		//SUPOÑENDO QUE AS ORDES TRABALLO XA VEÑEN VALIDADADAS E QUE SON ORDES TRABALLO SEN ASIGNAR
		proceso.setOrdenesTrabajo(ordenesTrabajo);
				
		return proceso;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
	
	private Boolean _estadoValido(String estado) {
		String [] tipoEstado=Proceso.tipoEstado;
		for(String tipo:tipoEstado)
			if(tipo.equals(estado))
				return true;
		return false;
	}
	
	private Boolean _servicioValido(String servicio) {
		String [] tipoServicio=Proceso.tipoServicio;
		for(String tipo:tipoServicio)
			if(tipo.equals(servicio))
				return true;
		return false;
	}

}
