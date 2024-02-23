package services;

import java.util.HashSet;
import java.util.Iterator;
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
			System.out.println("Digite o nome do titular " + (i+1));
			String nome = sc.next();
			System.out.println("Digite o cpf do titular " + (i+1));
			String cpf = sc.next();
			System.out.println("Digite a senha do titular " + (i+1));
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
		Conta conta = Conta.contas.get(numeroConta);
		if(conta!= null) {
			if(conta.getTitulares().size()>2) {
				System.out.println("Não é possível ter mais de 3 titulares nessa conta");
			}
			else {
				System.out.println("Digite o nome do titular ");
				sc.nextLine();
				String nome = sc.nextLine();
				System.out.println("Digite o cpf do titular ");
				String cpf = sc.next();
				System.out.println("Digite a senha do titular ");
				String senha = sc.next();
				Cliente cliente = new Cliente(nome, cpf, senha);
				Conta.contas.get(numeroConta).getTitulares().add(cliente);
				System.out.println("Cadastro efetuado com sucesso!");
			}
		}
		else {
			System.out.println("Não existe em nosso sistema uma conta com o número informado");
		}
	}
	
	public void excluirTitularConta(Scanner sc){
		System.out.println("Informe o número da conta ");
		int numeroConta = sc.nextInt();
		System.out.println("Digite o cpf do titular que desvincular dessa conta");
		String cpf = sc.next();
		
		Conta conta = Conta.contas.get(numeroConta);
		boolean encontrado = false;
		if (conta != null) { 
	        if(conta.getTitulares().size()>1) {
	        	Iterator<Cliente> iterator = conta.getTitulares().iterator();
	        	while (iterator.hasNext()) {
		            Cliente titular = iterator.next();
		            if (titular.getCpf().equals(cpf)) {
		                iterator.remove();
		                encontrado = true;
		                System.out.println("Exclusão efetuada com sucesso!");
		                break; 
		            }
		        }
		        if (!encontrado) {
		            System.out.println("O cpf informado não está vinculado à conta");
		        } 
	        } else {
	        	System.out.println("Não é possível remover o único titular da conta.");
	        }
	    } else {
	        System.out.println("Conta não encontrada");
	    }
	}
	
	public void editarTitularConta(Scanner sc){
		System.out.println("Informe o número da conta ");
		int numeroConta = sc.nextInt();
				
		Conta conta = Conta.contas.get(numeroConta);
		boolean encontrado = false;
		
		if (conta != null) { 
			Iterator<Cliente> iterator = conta.getTitulares().iterator();
	        System.out.println("Digite o cpf do titular que deseja alterar o nome e/ou senha");
			String cpf = sc.next();
        	while (iterator.hasNext()) {
	            Cliente titular = iterator.next();
	            if (titular.getCpf().equals(cpf)) {
	            	System.out.println("Digite o nome/novo nome do titular ");
	            	sc.nextLine();
	            	String nome = sc.nextLine();
	    			System.out.println("Digite a senha/nova senha do titular ");
	    			String senha = sc.next();
	    			Cliente cliente = new Cliente(nome, cpf, senha);
	                iterator.remove();
	                conta.getTitulares().add(cliente);
	                encontrado = true;
	                System.out.println("Edição efetuada com sucesso!");
	                break; 
	            }
	        }
	        if (encontrado == false) {
	            System.out.println("O cpf informado não está vinculado à conta");
	        } 
	    } else {
	        System.out.println("Conta não encontrada");
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
