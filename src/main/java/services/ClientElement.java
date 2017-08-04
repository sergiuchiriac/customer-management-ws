package services;



public class ClientElement {
	private Integer id;
	private String nome; 
	private String nif;
	private String morada;
	private String telefone;
	
	public ClientElement() {
		super();
	}
	public ClientElement(Integer id, String nome, String nif, String morada, String telefone) {
		this.id = id;
		this.nome = nome;
		this.nif = nif;
		this.morada = morada;
		this.telefone = telefone;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getMorada() {
		return morada;
	}
	public void setMorada(String morada) {
		this.morada = morada;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}

