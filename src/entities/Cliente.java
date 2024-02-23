package entities;

public class Cliente extends Pessoa{
		
	public Cliente(String nome, String cpf, String hashSenha) {
		super(nome, cpf, hashSenha);
	}

	public String exibirCliente() {
		return "\nTitular: "+getNome()+
				"\nCPF: " + getCpf();
	}
}
