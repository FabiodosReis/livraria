package application.util;

public class Validation {
	
	public static boolean onlyNumbers(String numero){
		
		return numero.matches("\\d+\\.\\d+") || numero.matches("\\d+");
		
	}
	
	public static boolean email(String email){
		
		return email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	}
	
	public String formatDateToEnglish(String data){
		
		String [] dataAtual = data.split("/");
		
		return dataAtual[0] + "-" + dataAtual[1] + "-" + dataAtual[2];
	}
	
	
}
