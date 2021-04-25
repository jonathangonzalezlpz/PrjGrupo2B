package SubsistemaGestionOrdenesTrabajo;

import java.util.ArrayList;
import java.util.Date;

import Model.OrdenTrabajo;
import Model.Presupuesto;
import Model.Proceso;

public interface InterfaceSubsistemaGestionOrdenTrabajo {
	//Devuelve un objeto OrdenTrabajo con los campos inicializados que se indiquen
    public OrdenTrabajo inicializar(Integer identificador, String descripcion, 
    		ArrayList<String> material, ArrayList<Presupuesto> presupuestos,
    		 Double coste, String responsable, Integer personal, Date fechaInicio, 
    		 Integer duracion, String estado, Proceso proceso);

          //Devuelve un objeto Presupuesto con los campos inicializados que se indiquen
    public Presupuesto inicializar(Integer identificador, String empresa, Double presupuesto,
    		Date fechaInicio, Integer duracion, ArrayList<String> material, Integer personal);
    
    //Crea una nueva orden de trabajo y la almacena en el sistema
    public OrdenTrabajo crear(OrdenTrabajo ordenTrabajo);

    //Modifica los campos de la orden de trabajo indicada, tiene que existir en el sistema
    public OrdenTrabajo gestionarRecursos(OrdenTrabajo ordenTrabajo);

    //Asignar a la orden de trabajo una empresa responsable, debe estar entre las que presentaron un presupuesto
    public OrdenTrabajo asignarEmpresa(OrdenTrabajo ordenTrabajo, Presupuesto presupuesto);

    //Devuleve el conjunto de órdenes de trabajo que coincidan con los campos de la orden de trabajo filtro, se puede especificar nulo
    public OrdenTrabajo buscar(OrdenTrabajo filtro);
}
