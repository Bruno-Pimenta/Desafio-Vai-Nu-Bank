package menu;

import java.util.Scanner;
import authentication.LoginColaborador;
import services.ColaboradorService;

public class MenuColaborador {
	
	public static void menu(Scanner sc) {
		int digito = 0;
		ColaboradorService colaboradorService = new ColaboradorService();
		
		do {
			System.out.println("Insira o digito referente a operação desejada:");
			System.out.println("1 - Criar conta"
					+ "\n2 - Cadastro de pessoa em conta"
					+ "\n3 - Exclusão de pessoa em conta"
					+ "\n4 - Edição de pessoa em conta"
					+ "\n5 - Visualizar todas as contas"
					+ "\n6 - Buscar conta pelo numero"
					+ "\n7 - Sair");
			digito = sc.nextInt();
			switch(digito) {
				case 1:
					colaboradorService.cadastrarConta(sc);
					break;
					
				case 2:
					colaboradorService.adicionarTitularConta(sc);
					break;
					
				case 3:
					colaboradorService.excluirTitularConta(sc);
					break;
					
				case 4:
					colaboradorService.editarTitularConta(sc);
					break;	
					
				case 5:
					colaboradorService.visualizarTodasContas();
					sc.nextLine();
					break;
					
				case 6:
					colaboradorService.buscarContaNumero(sc);
					break;
					
				case 7:
					System.out.println("Saindo...");
					LoginColaborador.logout();
					break;
					
				default:
					System.out.println("Digito inválido.");
			}
		} while(digito!=7);
	}
}
