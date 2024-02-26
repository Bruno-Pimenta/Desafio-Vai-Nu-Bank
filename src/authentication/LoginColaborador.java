package authentication;

import java.util.Scanner;
import entities.Colaborador;
import menu.MenuColaborador;

public abstract class LoginColaborador extends Login{
	private static Integer idColaborador = null;
		
	public static Integer getIdColaborador() {
		return idColaborador;
	}
	
	public static void realizarLogin(Scanner sc) {
		System.out.println("Digite o seu identificador:");
		int id = sc.nextInt(); 
		System.out.println("Insira a senha:");
		sc.nextLine();
		String senha = sc.nextLine();
		autenticar(id, senha, sc);
	}

	private static void autenticar(int id, String senha, Scanner sc) {
		Colaborador colaborador = Colaborador.colaboradores.get(id);
		try {
			if(senha.equals(colaborador.getHashSenha())) {
				idColaborador = colaborador.getIdColaborador();
				nome = colaborador.getNome();
				System.out.println("Seja vem vindo " + nome);
				MenuColaborador.menu(sc);
			}
			else {
				System.out.println("Senha incorreta.\n");
			}
		} catch (NullPointerException e) {
			System.out.println("Identificador não encontrado\n");
		}
	}
	
	public static void logout() {
		idColaborador = null;
		nome = null;
	}
}
