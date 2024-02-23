package app;

import java.util.Scanner;
import authentication.LoginCliente;
import authentication.LoginColaborador;

public class TelaInicial {
	public static Scanner sc = new Scanner(System.in);

	public static void inicializar() {
		int digito = 0;
		do {
			System.out.println("Bem-vindo ao Vai Nu Bank");
	        System.out.println("Por favor, selecione sua op��o:");
	        System.out.println("1. Cliente\n2. Colaborador\n3. Sair");
	        digito = sc.nextInt();
	        switch(digito) {
				case 1:
					LoginCliente.realizarLogin(sc);
					break;
				case 2:
					LoginColaborador.realizarLogin(sc);
					break;
				case 3:
					System.out.println("Saindo...");
					System.exit(0);
				default:
					System.out.println("Digito inv�lido.");
					break;
			}
		}while(digito!=3);
	}
}
