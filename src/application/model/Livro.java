package application.model;

public class Livro {
	
	private Long codigo;
    private String nome;
    private String descricao;
    private Double valor;
    private String isbn;
    private Autor autor;

    public String getIsbn() {return isbn;}
    public void setIsbn(String isbn) {this.isbn = isbn;}

    public Autor getAutor() {return autor;}
    public void setAutor(Autor autor) {this.autor = autor;} 
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Long getCodigo() {return codigo;}
    public void setCodigo(Long codigo) {this.codigo = codigo;}    
    

}
