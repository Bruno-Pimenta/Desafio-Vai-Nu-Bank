package services;

import java.util.Scanner;

import authentication.LoginCliente;
import entities.Cliente;
import entities.Conta;
import entities.ContaCorrente;
import entities.Operacao;
import enums.TipoOperacao;

public class ClienteService {
	
	public void verSaldo() {
		System.out.println("\nSaldo: " + String.format("%.2f", Conta.contas.get(LoginCliente.getNumeroContaLogin()).getSaldo()));
	}
	
	public void depositar(Scanner sc) {
		System.out.println("Insira o número da conta de destino para realizar o depósito:");
		int numeroConta = sc.nextInt(); 
		System.out.println("Insira o valor do depósito:");
		double valor = sc.nextDouble();
		if(valor>0 &&Conta.contas.get(numeroConta)!=null) {
			Conta.contas.get(numeroConta).depositar(valor);
			Operacao operacao = new Operacao(TipoOperacao.DEPOSITO, valor, LoginCliente.getNome());
			Conta.contas.get(LoginCliente.getNumeroContaLogin()).adicionarOperacao(operacao);
			System.out.println("Depósito realizado com sucesso!!");
		}
		else {
			System.out.println("Número da conta ou valor do depósito inválido!!");
		}
	}
	
	public double sacar(Scanner sc) {
		System.out.println("Insira o valor do saque:");
		Conta conta = Conta.contas.get(LoginCliente.getNumeroContaLogin());
		double valor = sc.nextDouble();
		if(conta.getSaldo()>=valor) {
			Conta.contas.get(LoginCliente.getNumeroContaLogin()).sacar(valor);
			Operacao operacao = new Operacao(TipoOperacao.SAQUE, valor, LoginCliente.getNome());
			Conta.contas.get(LoginCliente.getNumeroContaLogin()).adicionarOperacao(operacao);
			System.out.println("Saque realizado com sucesso!");
			return valor;
		}
		
		else if(conta instanceof ContaCorrente) {
			ContaCorrente contaCorrente = (ContaCorrente)conta;
			if(contaCorrente.getSaldo()+contaCorrente.getLimiteDeCredito()>=valor) {
				double valorLimiteUtilizado = -1*(contaCorrente.getSaldo()-valor);
				contaCorrente.usarLimiteDisponivelSacar(valor);
				Operacao operacaoSaque = new Operacao(TipoOperacao.SAQUE, valor, LoginCliente.getNome());
				Operacao operacaoUtilizarLimite = new Operacao(TipoOperacao.UTILIZACAOLIMITES, valorLimiteUtilizado, LoginCliente.getNome());
				Conta.contas.get(LoginCliente.getNumeroContaLogin()).adicionarOperacao(operacaoSaque);
				Conta.contas.get(LoginCliente.getNumeroContaLogin()).adicionarOperacao(operacaoUtilizarLimite);
				System.out.println("Saque realizado com sucesso!");
				return valor;
			}
			else {
				System.out.println("Saldo e limite insuficientes para realizar o saque.");
				return 0;
			}
		}
		
		else {
			System.out.println("Saldo insuficiente para realizar o saque.");
			return 0;
		}
	}
	
	public void transferir(Scanner sc){
		boolean contaEncontrada = false;
		int numeroConta;
		Conta conta;
		double valor=0;
		do{
			try {
				System.out.println("Insira o número da conta de destino para realizar a trasferência:");
				numeroConta = sc.nextInt(); 
				conta = Conta.contas.get(numeroConta);
				System.out.println("O(s) titular(es) dessa conta é(são): ");
				for (Cliente cliente : conta.getTitulares()) {
					System.out.println(cliente.getNome()+"\n");
				}
				contaEncontrada = true;
				do {
					System.out.println("Insira o valor:");
					valor = sc.nextDouble();
					if(valor<=0) {
						System.out.println("Valor inválido");
					}
					else if(Conta.contas.get(LoginCliente.getNumeroContaLogin()).getSaldo()>=valor) {
						Conta.contas.get(LoginCliente.getNumeroContaLogin()).transferir(conta.getNumeroConta(), valor);
						Operacao operacaoTransferenciaOrigem = new Operacao(TipoOperacao.TRANFERENCIAFEITA, valor, LoginCliente.getNome());
						Operacao operacaoTransferenciaDestino = new Operacao(TipoOperacao.TRANFERENCIARECEBIDA, valor, LoginCliente.getNome() + " - Número da conta: "+LoginCliente.getNumeroContaLogin());
						Conta.contas.get(LoginCliente.getNumeroContaLogin()).adicionarOperacao(operacaoTransferenciaOrigem);
						Conta.contas.get(numeroConta).adicionarOperacao(operacaoTransferenciaDestino);
						System.out.println("Tranferência realizada com sucesso!!");
						break;
					}
					else if(Conta.contas.get(LoginCliente.getNumeroContaLogin()) instanceof ContaCorrente) {
						ContaCorrente contaCorrente = (ContaCorrente)Conta.contas.get(LoginCliente.getNumeroContaLogin());
						if(contaCorrente.getSaldo()+contaCorrente.getLimiteDeCredito()>=valor) {
							double valorLimiteUtilizado = -1*(contaCorrente.getSaldo()-valor);
							contaCorrente.usarLimiteDisponivelTransferir(conta.getNumeroConta(), valor);
							Operacao operacaoFazerTransferencia = new Operacao(TipoOperacao.TRANFERENCIAFEITA, valor, LoginCliente.getNome());
							Operacao operacaoReceberTransferencia = new Operacao(TipoOperacao.TRANFERENCIARECEBIDA, valor, LoginCliente.getNome() + " - Número da conta: "+LoginCliente.getNumeroContaLogin());
							Operacao operacaoUtilizarLimite = new Operacao(TipoOperacao.UTILIZACAOLIMITES, valorLimiteUtilizado, LoginCliente.getNome());
							Conta.contas.get(LoginCliente.getNumeroContaLogin()).adicionarOperacao(operacaoFazerTransferencia);
							Conta.contas.get(LoginCliente.getNumeroContaLogin()).adicionarOperacao(operacaoUtilizarLimite);
							Conta.contas.get(numeroConta).adicionarOperacao(operacaoReceberTransferencia);
							System.out.println("Tranferência realizada com sucesso!!");
							break;
						}
						else {
							System.out.println("Saldo e limite insuficientes para realizar o saque.");
						}
					}
					else {
						System.out.println("Saldo insuficiente para realizar o saque.");
					}
				}while(valor<=0);
			}catch(NullPointerException e) {
				System.out.println("Não existe conta com o número informado");
			}
		}while(contaEncontrada == false);
	}
	
	public void exibirMovimentacaoDaConta() {
		Conta conta = Conta.contas.get(LoginCliente.getNumeroContaLogin());
		conta.exibirMovimentacaoDaConta();
	}
	
	public void exibirInformacoesConta() {
		System.out.println(Conta.contas.get(LoginCliente.getNumeroContaLogin()).exibirConta());
	}
}