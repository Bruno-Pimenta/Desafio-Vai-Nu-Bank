package teste;

import java.util.HashSet;
import java.util.Set;
import app.TelaInicial;
import entities.Cliente;
import entities.Colaborador;
import entities.Conta;
import entities.ContaCorrente;
import entities.ContaPoupanca;

public class TesteEntidades {
	public static void testeCadastro() {
		Cliente cliente = new Cliente("Bruno", "102", "senha");
		Set<Cliente> titulares = new HashSet<Cliente>();
		titulares.add(cliente);
		ContaCorrente conta = new ContaCorrente((short)104, titulares, 500, 500);
		Conta.contas.put(conta.getNumeroConta(), conta);
		
		Cliente cliente2 = new Cliente("Lucas", "108", "senha2");
		Set<Cliente> titulares2 = new HashSet<Cliente>();
		titulares2.add(cliente2);
		ContaPoupanca conta2 = new ContaPoupanca((short)104,titulares2, 17, 800);
		Conta.contas.put(conta2.getNumeroConta(), conta2);
		
		Colaborador colaborador = new Colaborador ("Brenda", "154", 321, "123");
		Colaborador.colaboradores.put(321, colaborador);
		
		TelaInicial.inicializar();
	}
}
