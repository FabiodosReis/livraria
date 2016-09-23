/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.util;

/**
 *
 * @author fabio
 */
public class NegocioException extends Exception{    
    
	private static final long serialVersionUID = 1L;
	
	private String mensagem;

    public NegocioException(String mensagem) {
    	super(mensagem);
        this.mensagem = mensagem;
        
    } 

    public String getMensagem() {
        return mensagem;
    }
    
    
    
}
