package SubsistemaGestionOrdenesTrabajo;

public interface InterfaceSubsistemaGestionOrdenTrabajo {
    //Crea una nueva orden de trabajo y la almacena en el sistema
    public OrdenTrabajo crear(OrdenTrabajo ordenTrabajo);

    //Modifica los campos de la orden de trabajo indicada, tiene que existir en el sistema
    public OrdenTrabajo gestionarRecursos(OrdenTrabajo ordenTrabajo);

    //Asignar a la orden de trabajo una empresa responsable, debe estar entre las que presentaron un presupuesto
    public OrdenTrabajo asignarEmpresa(OrdenTrabajo ordenTrabajo, Presupuesto presupuesto);

    //Devuleve el conjunto de órdenes de trabajo que coincidan con los campos de la orden de trabajo filtro, se puede especificar nulo
    public OrdenTrabajo buscar(OrdenTrabajo filtro);
}
