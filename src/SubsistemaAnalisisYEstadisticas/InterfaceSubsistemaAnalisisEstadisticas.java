package SubsistemaAnalisisYEstadisticas;

import Model.Estadistica;
import Model.Incidencia;
import Model.OrdenTrabajo;
import Model.Proceso;

public interface InterfaceSubsistemaAnalisisEstadisticas {
    
    /*Permite el filtrado por rango de fechas, por campos de incidencia y distribuir los datos en días (por defecto) o meses
    Devuelve un objeto estadísticas que contiene numero, coste y distribución de los datos obtenidos*/
    public Estadistica obtenerEstadisticasIncidencias(String rango, Incidencia filtro, String distribucion);
    
    /*Permite el filtrado por rango de fechas, por campos de proceso y distribuir los datos en días (por defecto) o meses
    Devuelve un objeto estadísticas que contiene numero, coste y distribución de los datos obtenidos*/
    public Estadistica obtenerEstadisticasProcesos(String rango, Proceso filtro, String distribucion);
    
    /*Permite el filtrado por rango de fechas, por campos de orden de trabajo y distribuir los datos en días (por defecto) o meses
    Devuelve un objeto estadísticas que contiene numero, coste y distribución de los datos obtenidos*/
    public Estadistica obtenerEstadisticasOrdenesTrabajo(String rango, OrdenTrabajo filtro, String distribucion);
    
}