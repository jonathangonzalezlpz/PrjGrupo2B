package SubsistemaGestionIncidencias;

import java.util.ArrayList;
import java.util.Date;

import Exception.CustomException;
import Model.Incidencia;
import Model.Proceso;

public class SubsistemaGestionIncidencias implements InterfaceSubsistemaGestionIncidencias {

	private ArrayList<Incidencia> incidencias;

	@Override
	public Incidencia inicializar(Integer identificador, String ciudadano, String DNI, String telefono,
			String descripcion, String localizacion, String tipoIncidencia, Proceso proceso, Date fechaInicio)
			throws CustomException {

		Incidencia incidencia = new Incidencia();
		if (identificador != null) {
			if (identificador > 0)
				incidencia.setIdentificador(identificador);
			else
				throw new CustomException("Identificador incorrecto", 1);
		}
		if (ciudadano != null) {
			if (ciudadano.length() <= 100 && !ciudadano.matches(".*\\d.*"))
				incidencia.setNombreCiudadano(ciudadano);
			else
				throw new CustomException("Nombre ciudadano incorrecto", 1);
		}
		if (DNI != null) {
			if (DNI.matches("[0-9]{8}[aA-zZ]"))
				incidencia.setDNI(DNI);
			else
				throw new CustomException("DNI formato incorrecto", 1);
		}
		if (telefono != null) {
			if (telefono.matches("^[0-9\\-\\+]{9,15}$"))
				incidencia.setTelefono(telefono);
			else
				throw new CustomException("Teléfono incorrecto", 1);
		}
		if (descripcion != null) {
			if (descripcion.length() <= 240)
				incidencia.setDescripcion(descripcion);
			else
				throw new CustomException("Descripcion incorrecta, supera 240 caracteres", 1);
		}
		if (localizacion != null) {
			if (localizacion.length() <= 150)
				incidencia.setLocalizacion(localizacion);
			else
				throw new CustomException("Localizacion incorrecta, supera 150 caracteres", 1);
		}
		if (tipoIncidencia != null) {
			for (String t : incidencia.tiposIncidencia) {
				if (tipoIncidencia.equals(t))
					incidencia.setTipoIncidencia(tipoIncidencia);
			}
			if (incidencia.getTipoIncidencia() == null)
				throw new CustomException("No se reconoce tipo de incidencia", 1);
		}
		if (proceso != null)
			incidencia.setProceso(proceso);
		
		if(fechaInicio != null)// Al ser tipo Date el formato siempre va a ser correcto
			incidencia.setFechaInicio(fechaInicio);

		return incidencia;

	}

	@Override
	public Incidencia crear(Incidencia incidencia) throws CustomException {
		if (incidencia != null) {
			// No existe en el sistema una incidencia con el mismo identificador
			for (Incidencia i : this.incidencias) {
				if (i.getIdentificador().equals(incidencia.getIdentificador())) {
					throw new CustomException("Incidencia ya existe", 2);
				}
			}
			// Se almacena en el sistema
			this.incidencias.add(incidencia);
			return incidencia;
		} else
			return null;

	}

	@Override
	public Incidencia actualizar(Incidencia incidencia) {
		if (incidencia != null) {
			for (int i = 0; i < this.incidencias.size(); i++) {
				if (this.incidencias.get(i).getIdentificador().equals(incidencia.getIdentificador())) {
					Incidencia oldIncidencia = this.incidencias.get(i);
					if (incidencia.getNombreCiudadano() != null)
						oldIncidencia.setNombreCiudadano(incidencia.getNombreCiudadano());
					if (incidencia.getDNI() != null)
						oldIncidencia.setDNI(incidencia.getDNI());
					if (incidencia.getDescripcion() != null)
						oldIncidencia.setDescripcion(incidencia.getDescripcion());
					if (incidencia.getLocalizacion() != null)
						oldIncidencia.setLocalizacion(incidencia.getLocalizacion());
					if (incidencia.getTipoIncidencia() != null)
						oldIncidencia.setTipoIncidencia(incidencia.getTipoIncidencia());
					if (incidencia.getProceso() != null)
						oldIncidencia.setProceso(incidencia.getProceso());
					if (incidencia.getFechaInicio() != null)
						oldIncidencia.setFechaInicio(incidencia.getFechaInicio());
				}
			}
		}
		return null;
	}

	@Override
	public ArrayList<Incidencia> buscar(Incidencia filtro) {
		if(filtro == null) {
			return this.incidencias;
		}
		return null;
	}


	@Override
	public ArrayList<Incidencia> obtenerIncidenciaSinAsignar() {
		ArrayList<Incidencia> sinAsignar = new ArrayList<>();
		for(Incidencia i:this.incidencias) {
			if(i.getProceso() == null)
				sinAsignar.add(i);
		}
		return sinAsignar;
	}

}
