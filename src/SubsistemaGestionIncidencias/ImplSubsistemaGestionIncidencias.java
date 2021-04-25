package SubsistemaGestionIncidencias;

import java.util.ArrayList;
import java.util.Date;

import Model.Incidencia;
import Model.Proceso;

public class ImplSubsistemaGestionIncidencias implements InterfaceSubsistemaGestionIncidencias {

	@Override
	public Incidencia inicializar(Integer identificador, String ciudadano, String DNI, String telefono,
			String descripcion, String localización, String tipoIncidencia, Proceso proceso, Date fechaInicio) {
		
		Incidencia incidencia = new Incidencia();
		if(identificador > 0) {
			incidencia.setIdentificador(identificador);
		}
		if(ciudadano.length() <= 100 && !ciudadano.matches(".*\\d.*"))
			incidencia.setNombreCiudadano(ciudadano);
		if(DNI.matches("[0-9]{8}[aA-zZ]"))
			incidencia.setDescripcion(DNI);
		if(telefono.matches("^[0-9\\-\\+]{9,15}$"))
			incidencia.setTelefono(telefono);
		
		return null;
			
	}
	
	@Override
	public Incidencia crear(Incidencia incidencia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Incidencia actualizar(Incidencia incidencia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Incidencia> buscar(Incidencia filtro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Incidencia> obtenerIncidenciaSinAsignar() {
		// TODO Auto-generated method stub
		return null;
	}

}
