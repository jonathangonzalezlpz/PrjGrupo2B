package Exception;

/*
 * Exception creada para mayor especificación de la causa de un error
 * Contiene un mensaje aclaratorio
 * y un codigo:
 * 	1- Campo no válido.
 * 	2- Conflicto.
 */
public class CustomException extends Exception{
	
	public int codigo;
	
	
	public CustomException(String mensaje, int codigo) {
		super(mensaje);
		this.codigo = codigo;
	}
}
