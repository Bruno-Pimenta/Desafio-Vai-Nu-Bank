package menu;

import java.util.InputMismatchException;
import java.util.Scanner;
import authentication.LoginCliente;
import services.ClienteService;

public class MenuCliente {
	
	public static void menu(Scanner sc) {
		ClienteService clienteService = new ClienteService();
		int digito = 0;
		do {
			System.out.println("1 - Exibir informa��es da conta\n2 - Saldo\n3 - Dep�sito\n4 - Saque\n5 - Transfer�ncia"
					+ "\n6 - Exibir movimenta��o da conta"
					+ "\n7 - Logout");
			System.out.println("Insira o digito referente a opera��o desejada:");
			try {
				digito = sc.nextInt();
				switch(digito) {
					case 1:
						clienteService.exibirInformacoesConta();
						break;
						
					case 2:
						clienteService.verSaldo();
						break;
						
					case 3:
						clienteService.depositar(sc);
						break;
						
					case 4:
						clienteService.sacar(sc);
						break;
						
					case 5:
						clienteService.transferir(sc);
						break;
						
					case 6:
						clienteService.exibirMovimentacaoDaConta();
						break;	
						
					case 7:
						System.out.println("Saindo...");
						LoginCliente.logout();
						break;
						
					default:
						System.out.println("Digito inv�lido.");
				}
			}catch(InputMismatchException e) {
				System.out.println("O digito deve ser um n�mero conforme apresentado no menu. ");
				sc.next();
			}
		}while(digito!=7);
	}
}