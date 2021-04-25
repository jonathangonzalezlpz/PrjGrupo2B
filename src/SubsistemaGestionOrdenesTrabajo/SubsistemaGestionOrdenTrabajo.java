package SubsistemaGestionOrdenesTrabajo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import Exception.CustomException;
import Model.OrdenTrabajo;
import Model.Presupuesto;
import Model.Proceso;

public class SubsistemaGestionOrdenTrabajo implements InterfaceSubsistemaGestionOrdenTrabajo {

	//ARRAYS ALMACENAMIENTO
	private HashMap<Integer, OrdenTrabajo> OTs;
	private HashMap<Integer, Presupuesto> presupuestos;
	
	public SubsistemaGestionOrdenTrabajo() {
		this.OTs = new HashMap<>();
		this.presupuestos = new HashMap<>();
	}
	
	//MÉTODOS
	@Override
	public OrdenTrabajo inicializar(Integer identificador, String descripcion, ArrayList<String> material,
			ArrayList<Presupuesto> presupuestos, Double coste, String responsable, Integer personal, 
			Date fechaInicio,Integer duracion, String estado, Proceso proceso) throws CustomException{
		//Casos erroneos
		//id negativo
		//descripcion mayor de 500 chars 
		//responsable no alfabetico
		//personal negativo
		//fecha formato incorrecto salta al crear Objeto Date
		//duracion negativa
		//estado no valido
		if(identificador < 0) {
			throw new CustomException(1, "Identificador negativo");
		}else if(descripcion.length() > 500) {
			return new OrdenTrabajo(null, null, null, null, null, null, null, null, null, null, null);
		}else if(!(_alfabetico(responsable))) {
			return new OrdenTrabajo(null, null, null, null, null, null, null, null, null, null, null);
		}else if(personal < 0) {
			return new OrdenTrabajo(null, null, null, null, null, null, null, null, null, null, null);
		}else if(duracion < 0) {
			return new OrdenTrabajo(null, null, null, null, null, null, null, null, null, null, null);
		}else if(!_estadoValido(estado)) {
			return new OrdenTrabajo(null, null, null, null, null, null, null, null, null, null, null);
		}
			
		//Todo bien, null...
		return new OrdenTrabajo(identificador, descripcion, material, presupuestos, coste, responsable, personal, fechaInicio, duracion, estado, proceso);
	}

	@Override
	public Presupuesto inicializar(Integer identificador, String empresa, Double presupuesto, Date fechaInicio,
			Integer duracion, ArrayList<String> material, Integer personal) {
		//id existente
		//id negativo
		//empresa > 100 chars
		//presupuesto < 0
		//fecha formato incorrecto falla al construir la fecha Exception
		//fecha anterior a actual
		//duracion negativa
		//personal negativo
		if(this.presupuestos.containsKey(identificador) || identificador < 0 || empresa.length() > 100 || 
				presupuesto < 0 || _fechaValida(fechaInicio) || duracion < 0 || personal < 0)
			return new Presupuesto(null, null, null, null, null, null, null);
		//todo bien, null...
		//se añade al sistema
		Presupuesto p = new Presupuesto(identificador, empresa, presupuesto, fechaInicio, duracion, material, personal);
		this.presupuestos.put(identificador, p);
		return p;
	}
	
	@Override
	public OrdenTrabajo crear(OrdenTrabajo ordenTrabajo) {
		if(ordenTrabajo == null)
			return new OrdenTrabajo(null, null, null, null, null, null, null, null, null, null, null);
		//éxito
		this.OTs.put(ordenTrabajo.getIdentificador(), ordenTrabajo);
		return ordenTrabajo;
	}

	@Override
	public OrdenTrabajo gestionarRecursos(OrdenTrabajo ordenTrabajo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrdenTrabajo asignarEmpresa(OrdenTrabajo ordenTrabajo, Presupuesto presupuesto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrdenTrabajo buscar(OrdenTrabajo filtro) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//Métodos privados
	private Boolean _alfabetico(String palabra) {
		for (int x = 0; x < palabra.length(); x++) {
	        char c = palabra.charAt(x);
	        // Si no está entre a y z, ni entre A y Z, ni es un espacio
	        if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
	            return false;
	        }
	    }
	    return true;
	}
	
	private Boolean _estadoValido(String estado) {
		if( estado.equals("Pendiente de asignación") || estado.equals("Asignado") || estado.equals("En curso") || estado.equals("Finalizado"))
			return true;
		return false;
	}

	private Boolean _fechaValida(Date fecha) {
		Date date = new Date(System.currentTimeMillis());
		if(fecha.before(date))
			return false;
		return true;
	}
	 

}
