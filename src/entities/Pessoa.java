package entities;

public abstract class Pessoa {
	private String nome;
	private String cpf;
	private String hashSenha;
	
	public Pessoa(String nome, String cpf, String hashSenha) {
		this.nome = nome;
		this.cpf = cpf;
		this.hashSenha = hashSenha;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
			
	public String getHashSenha() {
		return hashSenha;
	}

	public void setHashSenha(String hashSenha) {
		this.hashSenha = hashSenha;
	}
}
