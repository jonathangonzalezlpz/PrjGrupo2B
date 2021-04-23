package SubsistemaGestionIncidencias;

import java.util.ArrayList;

public interface InterfaceSubsistemaGestionIncidencias {
    //Crea una nueva incidencia y la almacena en el sistema
    public Incidencia crear(Incidencia incidencia);

    //Modifica una incidencia existente en el sistema
    public Incidencia actualizar(Incidencia incidencia);

    //Devuleve el conjunto de incidencias que coincidan con los campos de incidencias filtro, se puede especificar nulo
    public ArrayList<Incidencia> buscar(Incidencia filtro);

    //Obtener el conjunto de incidencias sin asignar
    public ArrayList<Incidencia> obtenerIncidenciaSinAsignar();
}
