package authentication;

import java.util.Scanner;
import java.util.Set;

import entities.Cliente;
import entities.Conta;
import entities.ContaCorrente;
import entities.ContaPoupanca;
import menu.MenuCliente;

public class LoginCliente extends Login{
	private static Integer numeroContaLogin = null;
		
	public static Integer getNumeroContaLogin() {
		return numeroContaLogin;
	}

	public static void realizarLogin(Scanner sc) {
		System.out.println("Insira o número da conta:");
		int numeroConta = sc.nextInt(); 
		System.out.println("Insira a senha:");
		sc.nextLine();
		String senha = sc.nextLine();
		autenticar(numeroConta, senha, sc);
	}

	private static void autenticar(int numeroConta, String senha, Scanner sc) {
		Conta conta = Conta.contas.get(numeroConta);
		Set<Cliente> titulares = conta.getTitulares();
		boolean contaCorreta = false;
		try {
			for(Cliente titular: titulares) {
				if(senha.equals(titular.getHashSenha())) {
					if(conta instanceof ContaPoupanca) {
						numeroContaLogin = numeroConta;
						nome = titular.getNome();
						ContaPoupanca.rendimentosAniversario((ContaPoupanca)conta);
						contaCorreta = true;
						System.out.println("Seja vem vindo " + nome);
						MenuCliente.menu(sc);
						break;
					}
					else if(conta instanceof ContaCorrente) {
						numeroContaLogin = numeroConta;
						nome = titular.getNome();
						contaCorreta = true;
						System.out.println("Seja vem vindo " + nome);
						MenuCliente.menu(sc);
						break;
					}
				}
			}
			if(contaCorreta == false) {
				System.out.println("Senha inválida");
			}
		}catch (NullPointerException e) {
			System.out.println("Conta não encontrada.");
		}
	}
	
	public static void logout() {
		numeroContaLogin = null;
		nome = null;
	}
}
