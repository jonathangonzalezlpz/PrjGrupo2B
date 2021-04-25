package SubsistemaGestionProcesos;

import java.util.ArrayList;
import Model.Incidencia;
import Model.OrdenTrabajo;
import Model.Proceso;
import java.util.Date;

public interface InterfaceSubsistemaGestionProcesos {
	//Devuelve un objeto Proceso con los campos inicializados que se indiquen
	public Proceso inicializar(Integer identificador, String nombreProceso, String descripcion, 
			Double coste, Double estimado, String estado, String responsable, String servicio, 
			ArrayList<Incidencia> incidencias, ArrayList<OrdenTrabajo> ordenesTrabajo, Date fechaInicio);
	
    //Crea un nuevo proceso y lo almacena en el sistema
    public Proceso crear(Proceso proceso);

    //Actualiza un proceso existente en el sistema
    public Proceso actualizar(Proceso proceso);

    //Devuleve el conjunto de procesos que coincidan con los campos del proceso filtro, se puede especificar nulo
    public ArrayList<Proceso> buscar(Proceso filtro);

    //Asigna un conjunto de incidencias al proceso indicado
    public Proceso asignarIncidencia(Proceso proceso, ArrayList<Incidencia> incidencias);

    //Asigna un conjunto de órdenes de trabajo al proceso indicado
    public Proceso asignarOrdenTrabajo (Proceso proceso, ArrayList<OrdenTrabajo> ordenesTrabajo);
}
