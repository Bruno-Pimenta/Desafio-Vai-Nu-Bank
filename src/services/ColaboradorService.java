package services;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import entities.Cliente;
import entities.Conta;
import entities.ContaCorrente;
import entities.ContaPoupanca;

public class ColaboradorService {

	public ColaboradorService() {
		
	};
		
	public void cadastrarConta(Scanner sc) {
		int numeroTitulares = 0;
		Set<Cliente> titulares = new HashSet<Cliente>();
		do {
			System.out.println("Informe o número de titulares da conta (o número deve ser entre 1 a 3)");
			numeroTitulares = sc.nextInt();
			if(numeroTitulares<=0||numeroTitulares>3) {
				System.out.println("Número de titualres inválido (o número deve ser entre 1 a 3)");
				numeroTitulares = 0;
			}
		}while(numeroTitulares == 0);
		
		for(int i=0; i<numeroTitulares; i++) {
			System.out.println("Digite o nome do titular " + i+1);
			String nome = sc.next();
			System.out.println("Digite o cpf do titular " + i+1);
			String cpf = sc.next();
			System.out.println("Digite a senha do titular " + i+1);
			String senha = sc.next();
			Cliente cliente = new Cliente(nome, cpf, senha);
			titulares.add(cliente);
		}
		
		System.out.println("Digite o número da agência");
		short agencia = sc.nextShort();
		System.out.println("Digite o valor do saldo inicial");
		double saldo = sc.nextDouble();
		int tipoConta = 0;
		while(tipoConta<1 || tipoConta>2) {
			System.out.println("digite:\n1 - para criar conta poupança\n2 - para criar conta corrente");
			tipoConta = sc.nextInt();
			if(tipoConta == 1) {
				System.out.println("Escolha o dia do aniversário da conta");
				int dia = sc.nextInt();
				ContaPoupanca conta = new ContaPoupanca(agencia, titulares, dia, saldo);
				Conta.contas.put(conta.getNumeroConta(), conta);
			} else if (tipoConta == 2) {
				System.out.println("Determine o valor do limite de crédito");
				double limiteDeCredito = sc.nextDouble();
				ContaCorrente conta = new ContaCorrente(agencia, titulares, saldo, limiteDeCredito);
				Conta.contas.put(conta.getNumeroConta(), conta);
			}
		}
		System.out.println("Cadastro efetuado com sucesso!");
	}
	
	public void adicionarTitularConta(Scanner sc){
		System.out.println("Informe o número da conta ");
		int numeroConta = sc.nextInt();
		System.out.println("Digite o nome do titular ");
		String nome = sc.next();
		System.out.println("Digite o cpf do titular ");
		String cpf = sc.next();
		System.out.println("Digite a senha do titular ");
		String senha = sc.next();
		Cliente cliente = new Cliente(nome, cpf, senha);
		Conta.contas.get(numeroConta).getTitulares().add(cliente);
		System.out.println("Cadastro efetuado com sucesso!");
		
	}
	
	public void excluirTitularConta(Scanner sc){
		System.out.println("Informe o número da conta ");
		int numeroConta = sc.nextInt();
		System.out.println("Digite o cpf do titular que desvincular dessa conta");
		String cpf = sc.next();
		Conta conta = Conta.contas.get(numeroConta);
		boolean encontrado = false;
		if(conta.getTitulares().size()>1) {
			for(Cliente titular : conta.getTitulares()) {
				if(titular.getCpf().equals(cpf)) {
					conta.getTitulares().remove(titular);
					encontrado = true;
					System.out.println("Exclusão efetuada com sucesso!");
				}
			}
			if(encontrado == false) {
				System.out.println("O cpf informado não está vinculado a conta");
			}
		}
		else {
			System.out.println("Não é possível excluir o único titular da conta");
		}
	}
	
	public void editarTitularConta(Scanner sc){
		System.out.println("Informe o número da conta ");
		int numeroConta = sc.nextInt();
		System.out.println("Digite o nome/novo nome do titular ");
		String nome = sc.next();
		System.out.println("Digite o cpf do titular ");
		String cpf = sc.next();
		System.out.println("Digite a senha/nova senha do titular ");
		String senha = sc.next();
		Cliente cliente = new Cliente(nome, cpf, senha);
		
		Conta conta = Conta.contas.get(numeroConta);
		boolean encontrado = false;
		for(Cliente titular : conta.getTitulares()) {
			if(titular.getCpf().equals(cpf)) {
				conta.getTitulares().remove(titular);
				conta.getTitulares().add(cliente);
				System.out.println("Edição efetuada com sucesso!");
				encontrado = true;
			}
		}
		if(encontrado == false) {
			System.out.println("O cpf informado não está vinculado a conta");
		}
	}
	
	public void visualizarTodasContas() {
		for(Conta contas : Conta.contas.values()){
			System.out.println(contas.exibirConta());
		}
	}
	
	public void buscarContaNumero(Scanner sc) {
		System.out.println("Informe o número da conta ");
		int numeroConta = sc.nextInt();
		Conta conta = Conta.contas.get(numeroConta);
		System.out.println(conta.exibirConta());
	}
}
