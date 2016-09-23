package application.model;

import java.util.Date;

public class Autor {
	
	private Long codigo;
    private String nome;
    private String email;
    private Date dataNascimento;

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public Date getDataNascimento() {return dataNascimento;}
    public void setDataNascimento(Date dataNascimento) {this.dataNascimento = dataNascimento;
    }

    public Long getCodigo() {return codigo;}
    public void setCodigo(Long codigo) {this.codigo = codigo;}   
     

}
