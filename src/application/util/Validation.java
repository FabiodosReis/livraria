package application.util;

public class Validation {
	
	public static boolean SomenteNumero(String numero){
		
		return numero.matches("\\d+\\.\\d+") || numero.matches("\\d+");
		
	}
	
	public static boolean email(String email){
		
		return email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	}
	
	
}
