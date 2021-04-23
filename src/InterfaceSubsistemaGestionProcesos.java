package project;

import java.util.ArrayList;

public interface InterfaceSubsistemaGestionProcesos {
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
