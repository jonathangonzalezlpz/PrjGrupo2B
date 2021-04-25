import java.util.Date;

import SubsistemaGestionOrdenesTrabajo.InterfaceSubsistemaGestionOrdenTrabajo;
import SubsistemaGestionOrdenesTrabajo.SubsistemaGestionOrdenTrabajo;

public class Main {

	public static void main(String[] args) {
		InterfaceSubsistemaGestionOrdenTrabajo got = new SubsistemaGestionOrdenTrabajo();
		Date fecha = new Date("31//2021");
		System.out.println(fecha);
		got.inicializar(null, null, null, null, null, null, null, null, null, null, null);
		

	}

}
