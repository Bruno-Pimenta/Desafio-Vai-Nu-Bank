package menu;

import java.util.InputMismatchException;
import java.util.Scanner;
import authentication.LoginCliente;
import services.ClienteService;

public abstract class MenuCliente {
	
	public static void menu(Scanner sc) {
		ClienteService clienteService = new ClienteService();
		int digito = 0;
		do {
			System.out.println("1 - Exibir informações da conta\n2 - Consultar Saldo\n3 - Depositar\n4 - Sacar"
					+ "\n5 - Transferir"
					+ "\n6 - Exibir histórico de movimentação da conta"
					+ "\n7 - Logout");
			System.out.println("Insira o digito referente a operação desejada:");
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
						System.out.println("Digito inválido.");
				}
			}catch(InputMismatchException e) {
				System.out.println("O digito deve ser um número conforme apresentado no menu. ");
				sc.next();
			}
		}while(digito!=7);
	}
}