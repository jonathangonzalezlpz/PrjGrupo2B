package Exception;

/*
 * Exception creada para mayor especificación de la causa de un error
 * Contiene un mensaje aclaratorio
 * y un codigo:
 * 	1- Campo no válido.
 * 	2- Conflicto.
 */
public class CustomException extends Exception{
	
	public Integer codigo;
	
	
	public CustomException(String mensaje, Integer codigo) {
		super(mensaje);
		this.codigo = codigo;
	}
}
