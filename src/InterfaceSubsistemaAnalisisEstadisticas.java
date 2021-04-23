package project;

public interface InterfaceSubsistemaAnalisisEstadisticas {
    
    /*Permite el filtrado por rango de fechas, por campos de incidencia y distribuir los datos en días (por defecto) o meses
    Devuelve un objeto estadísticas que contiene numero, coste y distribución de los datos obtenidos*/
    public Estadisticas obtenerEstadisticasIncidencias(String rango, Incidencia filtro, String distribucion);
    
    /*Permite el filtrado por rango de fechas, por campos de proceso y distribuir los datos en días (por defecto) o meses
    Devuelve un objeto estadísticas que contiene numero, coste y distribución de los datos obtenidos*/
    public Estadisticas obtenerEstadisticasProcesos(String rango, Proceso filtro, String distribucion);
    
    /*Permite el filtrado por rango de fechas, por campos de orden de trabajo y distribuir los datos en días (por defecto) o meses
    Devuelve un objeto estadísticas que contiene numero, coste y distribución de los datos obtenidos*/
    public Estadisticas obtenerEstadisticasOrdenesTrabajo(String rango, OrdenTrabajo filtro, String distribucion);
    
}
