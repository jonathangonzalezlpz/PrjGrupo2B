package SubsistemaGestionOrdenesTrabajo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import Exception.CustomException;
import Model.OrdenTrabajo;
import Model.Presupuesto;
import Model.Proceso;

public class SubsistemaGestionOrdenTrabajo implements InterfaceSubsistemaGestionOrdenTrabajo {

	//ARRAYS ALMACENAMIENTO
	private HashMap<Integer, OrdenTrabajo> OTs;
	
	public SubsistemaGestionOrdenTrabajo() {
		this.OTs = new HashMap<>();
	}
	
	//MÉTODOS
	@Override
	public OrdenTrabajo inicializar(Integer identificador, String descripcion, ArrayList<String> material,
			ArrayList<Presupuesto> presupuestos, Double coste, String responsable, Integer personal, 
			Date fechaInicio,Integer duracion, String estado, Proceso proceso) throws CustomException{
		//Casos erroneos
		//id negativo
		//descripcion mayor de 500 chars 
		//responsable no alfabetico
		//personal negativo
		//fecha formato incorrecto salta al crear Objeto Date
		//duracion negativa
		//estado no valido
		if(identificador != null && identificador < 0) {
			throw new CustomException("Identificador negativo", 1);
		}else if(descripcion != null && descripcion.length() > 500) {
			throw new CustomException("Descripcion superior a 500 chars", 1);
		}else if(responsable != null && !(_alfabetico(responsable))) {
			throw new CustomException("Responsable no alfabetico", 1);
		}else if(personal != null && personal < 0) {
			throw new CustomException("Personal negativo", 1);
		}else if(duracion != null && duracion < 0) {
			throw new CustomException("Duracion negativo", 1);
		}else if(estado != null && !_estadoValido(estado)) {
			throw new CustomException("Estado no válido", 1);
		}
			
		//Todo bien, null...
		return new OrdenTrabajo(identificador, descripcion, material, presupuestos, coste, responsable, personal, fechaInicio, duracion, estado, proceso);
	}

	@Override
	public Presupuesto inicializar(Integer identificador, String empresa, Double presupuesto, Date fechaInicio,
			Integer duracion, ArrayList<String> material, Integer personal, Integer idOT) throws CustomException{
		if(!this.OTs.containsKey(idOT)) { //Conflicto
			throw new CustomException("Conflito, id presupuesto registrado", 2);
		}
		//id existente
		//id negativo
		//empresa > 100 chars
		//presupuesto < 0
		//fecha formato incorrecto falla al construir la fecha Exception
		//fecha anterior a actual
		//duracion negativa
		//personal negativo
		if(identificador==null || empresa == null || presupuesto == null || fechaInicio == null || duracion == null || material == null || personal == null) {
			throw new CustomException("Campos nulos", 1);
		}
		else if(_contener(idOT,identificador)) {
			throw new CustomException("Id ya registrado", 2);
		}else if(identificador < 0) {
			throw new CustomException("Identificador negativo", 1);
		}else if(empresa.length() > 100) {
			throw new CustomException("Identificador negativo", 1);
		}else if(presupuesto < 0) {
			throw new CustomException("Identificador negativo", 1);
		}else if(_fechaValida(fechaInicio)) {
			throw new CustomException("Identificador negativo", 1);
		}else if(duracion < 0){
			throw new CustomException("Identificador negativo", 1);
		}else if(personal < 0) {
			throw new CustomException("Identificador negativo", 1);
		}
				
		//todo bien, null...
		//se añade al sistema
		Presupuesto p = new Presupuesto(identificador, empresa, presupuesto, fechaInicio, duracion, material, personal);
		this.OTs.get(idOT).getPresupuesto().add(p);
		return p;
	}
	
	@Override
	public OrdenTrabajo crear(OrdenTrabajo ordenTrabajo) throws CustomException{
		if(ordenTrabajo == null) {
			throw new CustomException("OT nula", 1);
		}else { //OT no nula
			if(_isNula(ordenTrabajo)) { //todos los campos nulos
				throw new CustomException("OT con todos los campos nulos", 1);
			}else if(ordenTrabajo.getIdentificador()==null || ordenTrabajo.getDescripcion()==null || ordenTrabajo.getEstado()==null) { //Obligatios
				throw new CustomException("Faltan campos Obligatorios {id, descripcion ,estado}",1);
			}else if(!ordenTrabajo.getPresupuesto().isEmpty()) { 
				throw new CustomException("No se pueden incorporar presupeustos en la creación",1);
			}else if(ordenTrabajo.getEstado().equals("Pendiente Asignación")) {
				throw new CustomException("El estado no puede ser distinto de Pendiente asignación", 1);
			}else if(this.OTs.containsKey(ordenTrabajo.getIdentificador())) //Conflicto
				throw new CustomException("Id ya registrado", 2);
		}
		
		//éxito
		this.OTs.put(ordenTrabajo.getIdentificador(), ordenTrabajo);
		return ordenTrabajo;
	}

	@Override
	public OrdenTrabajo gestionarRecursos(OrdenTrabajo ordenTrabajo) throws CustomException{
		Boolean cambioCoste = false, cambioPersonal = false, cambioMateriales = false, cambioDuracion = false, cambioEstado = false;
		
		if(ordenTrabajo == null ) {
			throw new CustomException("OT nula", 1);
		}else if(_isNula(ordenTrabajo)) { //todos los campos nulos
			throw new CustomException("OT con todos los campos nulos", 1);
		}else if(this.OTs.containsKey(ordenTrabajo.getIdentificador())) {
			throw new CustomException("OT no registrada", 4);
		}else if(!_modValida(ordenTrabajo)) {
			throw new CustomException("Modificacion no permitida", 3);
		}
		
		//Efectuamos las modificaciones
		if(ordenTrabajo.getCoste()!= null) {
			if(ordenTrabajo.getCoste()<0) {
				throw new CustomException("Coste negativo", 1);
			}
			cambioCoste = true;
		}
		if(ordenTrabajo.getMaterial()!=null) {
			if(ordenTrabajo.getMaterial().isEmpty()){
				throw new CustomException("Materiales no pueden ser vacios", 1);
			}
			cambioMateriales = true;
		}
		if(ordenTrabajo.getPersonal()!=null) {
			if(ordenTrabajo.getPersonal()<=0) {
				throw new CustomException("Personal no valido", 1);
			}
			cambioPersonal = true;
		}
		if(ordenTrabajo.getDuracion()!=null) {
			if(ordenTrabajo.getDuracion()<=0) {
				throw new CustomException("Duracion no valida", 1);
			}
			cambioDuracion = true;
		}
		if(ordenTrabajo.getEstado()!=null) {
			if(ordenTrabajo.getEstado().equals("En curso") || ordenTrabajo.getEstado().equals("Finalizado"))
				cambioEstado = true;
			else
				throw new CustomException("Estado no valido", 1);
		}
		
		OrdenTrabajo modificada = this.OTs.get(ordenTrabajo.getIdentificador());
		//Ejecutamos las modificaciones
		if(cambioCoste) {
			modificada.setCoste(ordenTrabajo.getCoste());
		}
		if(cambioMateriales) {
			modificada.setMaterial(ordenTrabajo.getMaterial());
		}
		if(cambioPersonal) {
			modificada.setPersonal(ordenTrabajo.getPersonal());
		}
		if(cambioDuracion) {
			modificada.setDuracion(ordenTrabajo.getDuracion());
		}
		if(cambioEstado) {
			modificada.setEstado(ordenTrabajo.getEstado());
		}
		return modificada;
	}

	@Override
	public OrdenTrabajo asignarEmpresa(OrdenTrabajo ordenTrabajo, Presupuesto presupuesto) throws CustomException{
		//Comprobamos la orden de trabajo
		if(ordenTrabajo==null) {
			throw new CustomException("OrdenTrabajo nula", 1);
		}else if(this._isNula(ordenTrabajo)) {
			throw new CustomException("OT con todos los campos nulos", 1);
		}else if(!this.OTs.containsKey(ordenTrabajo.getIdentificador())){
			throw new CustomException("OT no registrada", 4);
		}else if(!this.OTs.get(ordenTrabajo.getIdentificador()).getDescripcion().equals(ordenTrabajo.getDescripcion())) {
			throw new CustomException("La información no coincide con la almacenada", 2);
		}else if(this.OTs.get(ordenTrabajo).getPresupuesto().size()<3) {
			throw new CustomException("La OT no presenta el mínimo de 3 presupuestos para la selección", 3);
		}
		
		//Comprobamos presupuesto
		if(presupuesto==null) {
			throw new CustomException("Presupuesto nulo", 1);
		}else if(presupuesto.getIdentificador()!=null) {
			throw new CustomException("Presupuesto con id nulo", 1);
		}else if(!this._contener(ordenTrabajo.getIdentificador(),presupuesto.getIdentificador())){
			throw new CustomException("Presupuesto no registrado en la OT", 4);
		}
		
		
		//todo va bien, pasa comprobaciones
		//Actualizamos información
		OrdenTrabajo modificada = this.OTs.get(ordenTrabajo.getIdentificador());
		Presupuesto seleccionado = null;
		for(Presupuesto p: modificada.getPresupuesto()) {
			if(p.getIdentificador().equals(presupuesto.getIdentificador())) {
				seleccionado = p; 
				break;
			}
		}
		modificada.setResponsable(seleccionado.getEmpresa());
		modificada.setCoste(seleccionado.getPresupuesto());
		modificada.setMaterial(seleccionado.getMaterial());
		modificada.setFechaInicio(seleccionado.getFechaInicio());
		modificada.setDuracion(seleccionado.getDuracion());
		modificada.setPersonal(seleccionado.getPersonal());
		modificada.setEstado("Asignada");
		return modificada;
	}

	@Override
	public ArrayList<OrdenTrabajo> buscar(OrdenTrabajo filtro){
		ArrayList<OrdenTrabajo> result = new ArrayList<>();
		if(filtro==null || this._isNula(filtro)) {
			result = new ArrayList<>(this.OTs.values());
		}else { //filtramos
			Boolean fId = false, fDescripcion = false, fMaterial = false, fPresupuesto = false, 
					fCoste = false, fResponsable = false, fPersonal = false, fFechaInicio = false, 
					fDuracion = false, fEstado = false, fProceso = false;
			if(filtro.getIdentificador()!=null) {
				fId = true;
				if(!this.OTs.containsKey(filtro.getIdentificador())) {
					return result; //vacio
				}
			}
			if(filtro.getDescripcion()!=null)
				fDescripcion = true;
			if(filtro.getMaterial()!=null)
				fMaterial = true;
			if(filtro.getPresupuesto()!=null)
				fPresupuesto = false;
			if(filtro.getCoste()!=null)
				fCoste = true;
			if(filtro.getResponsable()!=null)
				fResponsable = true;
			if(filtro.getPersonal()!=null)
				fPersonal = true;
			if(filtro.getFechaInicio()!=null)
				fFechaInicio = true;
			if(filtro.getDuracion()!=null)
				fDuracion = true;
			if(filtro.getEstado()!=null)
				fEstado = true;
			if(filtro.getProceso()!=null)
				fProceso = true;
			//Aplicamos filtro
			if(fId) { //Solo puede haber una
				OrdenTrabajo candidata = this.OTs.get(filtro.getIdentificador());
				if(this._cumpleFiltro(fDescripcion, fMaterial, fPresupuesto, fCoste, 
						fResponsable, fPersonal, fFechaInicio, fDuracion, fEstado, 
						fProceso, candidata, filtro))
					result.add(candidata);
				else
					return result;
			}else {
				Collection<OrdenTrabajo> total = this.OTs.values();
				for(OrdenTrabajo o:total) {
					if(this._cumpleFiltro(fDescripcion, fMaterial, fPresupuesto, fCoste, 
							fResponsable, fPersonal, fFechaInicio, fDuracion, fEstado, 
							fProceso, o, filtro))
						result.add(o);
					else
						return result;
				}
			}
		}
		return result;
	}
	
	//Métodos privados
	private Boolean _alfabetico(String palabra) {
		for (int x = 0; x < palabra.length(); x++) {
	        char c = palabra.charAt(x);
	        // Si no está entre a y z, ni entre A y Z, ni es un espacio
	        if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
	            return false;
	        }
	    }
	    return true;
	}
	
	private Boolean _estadoValido(String estado) {
		if( estado.equals("Pendiente de asignación") || estado.equals("Asignado") || estado.equals("En curso") || estado.equals("Finalizado"))
			return true;
		return false;
	}

	private Boolean _fechaValida(Date fecha) {
		Date date = new Date(System.currentTimeMillis());
		if(fecha.before(date))
			return false;
		return true;
	}
	
	private Boolean _isNula(OrdenTrabajo ot) {
		if(ot.getIdentificador()==null && ot.getDescripcion()==null && ot.getMaterial()==null && ot.getPresupuesto()==null && 
				ot.getCoste()==null && ot.getResponsable()==null && ot.getPersonal()==null && ot.getFechaInicio()==null &&
				ot.getDuracion()==null && ot.getEstado()==null && ot.getProceso()==null)
			return true;
		return false;
	}
	
	private Boolean _contener(Integer idOT, Integer identificador) {
		for(Presupuesto p: this.OTs.get(idOT).getPresupuesto()) {
			if(p.getIdentificador().equals(identificador)) {
				return true;
			}
		}
		return false;
	}
	
	private Boolean _modValida(OrdenTrabajo ot) {
		OrdenTrabajo original = this.OTs.get(ot.getIdentificador());
		//Modifica la descripcion o el responsable
		if(original.getDescripcion().equals(ot.getDescripcion()) &&
				original.getResponsable().equals(ot.getResponsable())) {
			if(!ot.getPresupuesto().isEmpty()) { //Comprobamos presupuestos
				for(Presupuesto p: ot.getPresupuesto()) {
					if(!original.getPresupuesto().contains(p)) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}
	
	private Boolean _cumpleFiltro(Boolean fDescripcion, Boolean fMaterial, Boolean fPresupuesto, 
			Boolean fCoste, Boolean fResponsable, Boolean fPersonal, Boolean fFechaInicio, 
			Boolean fDuracion, Boolean fEstado, Boolean fProceso, OrdenTrabajo candidata, OrdenTrabajo filtro) {
		
		if(fDescripcion) {
			if(candidata.getDescripcion()!=null && !candidata.getDescripcion().equals(filtro.getDescripcion()))
				return false;
		}
		if(fMaterial) {
			if(candidata.getMaterial()!=null && !candidata.getMaterial().isEmpty()) {
				for (String m:filtro.getMaterial()) {
					if(!candidata.getMaterial().contains(m))
						return false;
				}
			}else
				return false;
			
		}
		if(fPresupuesto) {
			if(candidata.getPresupuesto()!=null && !candidata.getPresupuesto().isEmpty()) {
				for(Presupuesto p: filtro.getPresupuesto()) {
					if(!candidata.getPresupuesto().contains(p))
						return false;
				}
			}
		}
		if(fCoste) {
			if(candidata.getCoste()!=null && candidata.getCoste()!=filtro.getCoste())
				return false;
		}
		if(fResponsable) {
			if(candidata.getResponsable()!=null && !candidata.getResponsable().equals(filtro.getResponsable())) 
				return false;
		}
		if(fPersonal) {
			if(candidata.getPersonal()!=null && candidata.getPersonal()!=filtro.getPersonal())
				return false;
		}
		if(fFechaInicio) {
			if(candidata.getFechaInicio()!=null && !candidata.getFechaInicio().equals(filtro.getFechaInicio()))
				return false;
		}
		if(fDuracion) {
			if(candidata.getDuracion()!=null && candidata.getDuracion()!=filtro.getDuracion())
				return false;
		}
		if(fEstado) {
			if(candidata.getEstado()!=null && !candidata.getEstado().equals(filtro.getEstado()))
				return false;
		}
		if(fProceso) {
			if(candidata.getProceso()!=null && !candidata.getPresupuesto().equals(filtro.getProceso()))
				return false;
		}
		return true;
	}
	 

}
