import java.text.DateFormat;
import java.util.Date;

import Exception.CustomException;
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
			gu.obtenerEstadisticasIncidencias("15/3/2002-15/4/2002", null, "pep");
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
