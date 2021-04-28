import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import Exception.CustomException;
import Model.OrdenTrabajo;
import Model.Presupuesto;
import SubsistemaAnalisisYEstadisticas.InterfaceSubsistemaAnalisisEstadisticas;
import SubsistemaAnalisisYEstadisticas.SubsistemaAnalisisEstadisticas;
import SubsistemaGestionIncidencias.InterfaceSubsistemaGestionIncidencias;
import SubsistemaGestionIncidencias.SubsistemaGestionIncidencias;
import SubsistemaGestionOrdenesTrabajo.InterfaceSubsistemaGestionOrdenTrabajo;
import SubsistemaGestionOrdenesTrabajo.SubsistemaGestionOrdenTrabajo;
import SubsistemaGestionProcesos.InterfaceSubsistemaGestionProcesos;
import SubsistemaGestionProcesos.SubsistemaGestionProcesos;

public class Main {

	public static void main(String[] args) {
		Date fecha = new Date(100,11,1);
		System.out.println(fecha);
		//got.inicializar(null, null, null, null, null, null, null, null, null, null, null);
		
		InterfaceSubsistemaGestionIncidencias gi = new SubsistemaGestionIncidencias();
		InterfaceSubsistemaGestionOrdenTrabajo got = new SubsistemaGestionOrdenTrabajo();
		InterfaceSubsistemaGestionProcesos gp = new SubsistemaGestionProcesos();
		InterfaceSubsistemaAnalisisEstadisticas gu = new SubsistemaAnalisisEstadisticas(gi, got, gp);
		try {
			//OTS
			ArrayList<String> material = new ArrayList<String>();
			material.add("Bombillas");
			material.add("Cable");
			ArrayList<Presupuesto> presupuesto = new ArrayList<Presupuesto>();
			//Inicializar filtros
			presupuesto.add(got.inicializar(1, "Pepe Electricas", 350.0, new Date(2019,2,1), 2, material, 2, null));
			got.inicializar(1, 
					"Necesario arreglar un par de farolas situadas en la calle Alfonso XIII"
					,material , presupuesto, null, "Pepe", null, new Date(2019,3,12), 20, "Pendiente de asignación", null);
			//Creación Orden
			OrdenTrabajo o1 = got.crear(got.inicializar(12345,"Necesario arreglar un par de farolas situadas en la calle Alfonso XIII",null,null,null,null,null, null, null, "Pendiente de asignación", null));
			//Asignamos presupuestos
			Presupuesto c1 = got.inicializar(1, "Pepe Electricas", 350.0, new Date(2021,11,1), 2, material, 2, 12345);
			Presupuesto c2 = got.inicializar(2, "Paco Electricas", 350.0, new Date(2021,11,1), 2, material, 2, 12345);
			Presupuesto c3 = got.inicializar(3, "Juan Electricas", 350.0, new Date(2021,11,1), 2, material, 2, 12345);
			//asignar
			got.asignarEmpresa(o1, c1);
			//gestionar
			OrdenTrabajo modificada = got.inicializar(12345,"Necesario arreglar un par de farolas situadas en la calle Alfonso XIII",null,null,null,null,null, null, null, "En curso", null);
			got.gestionarRecursos(modificada);
			System.out.println(o1);
			//
			gu.obtenerEstadisticasIncidencias("15/3/2002-15/4/2002", null, "pep");
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
