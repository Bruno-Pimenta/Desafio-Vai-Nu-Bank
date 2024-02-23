package entities;

import java.util.HashMap;
import java.util.Map;

public class Colaborador extends Pessoa{
	public static Map<Integer, Colaborador> colaboradores = new HashMap<>();
	
	private Integer idColaborador;
	
	public Colaborador(String nome, String cpf, Integer idColaborador, String hashSenha) {
		super(nome, cpf, hashSenha);
		this.idColaborador = idColaborador;
	}

	public Integer getIdColaborador() {
		return idColaborador;
	}

	public void exibirColaborador() {
		System.out.println("\nNome: " + getNome()+"\nCPF: " + getCpf() + "\nIdentificador: " + getIdColaborador());
	}
}
