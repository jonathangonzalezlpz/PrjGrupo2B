package SubsistemaGestionOrdenesTrabajo;

import java.util.ArrayList;
import java.util.Date;

import Model.OrdenTrabajo;
import Model.Presupuesto;
import Model.Proceso;

public class SubsistemaGestionOrdenTrabajo implements InterfaceSubsistemaGestionOrdenTrabajo {

	@Override
	public OrdenTrabajo inicializar(Integer identificador, String descripcion, ArrayList<String> material,
			ArrayList<Presupuesto> presupuestos, Double coste, String responsable, Integer personal, 
			Date fechaInicio,Integer duracion, String estado, Proceso proceso) {
		//Casos erroneos
		//id negativo
		//descripcion mayor de 500 chars 
		//responsable no alfabetico
		//personal negativo
		if(identificador < 0 || descripcion.length() > 500 || !(_alfabetico(responsable)) || personal < 0)
			return new OrdenTrabajo(null, null, null, null, null, null, null, null, null, null, null);
		//Todo bien, todo null, arraylist material vacio, arraylist presupuesto vacio, 
		return null;
	}

	@Override
	public Presupuesto inicializar(Integer identificador, String empresa, Double presupuesto, Date fechaInicio,
			Integer duracion, ArrayList<String> material, Integer personal) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public OrdenTrabajo crear(OrdenTrabajo ordenTrabajo) {
		// TODO Auto-generated method stub
		return null;
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

	

}
