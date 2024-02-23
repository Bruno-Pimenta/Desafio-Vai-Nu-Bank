package entities;

import java.util.Set;
import authentication.LoginCliente;

public class ContaCorrente extends Conta{
	private double limiteDeCredito;
	
	public ContaCorrente(short agencia, Set<Cliente> titulares, double saldo, double limiteDeCredito) {
		super(agencia, titulares, saldo);
		this.limiteDeCredito = limiteDeCredito;
	}
	
	public double getLimiteDeCredito() {
		return limiteDeCredito;
	}
	
	private void setLimiteDeCredito(double limiteDeCredito) {
		this.limiteDeCredito = limiteDeCredito;
	}

	
	public void usarLimiteDisponivelSacar(double valor) {
		double diferenca = getSaldo()-valor;
		setLimiteDeCredito(limiteDeCredito + diferenca);
		this.setSaldo(0);
	}
	
	public double sacar(double valor) {
		setSaldo(getSaldo()-valor);
		return valor;
	}
	
	public void transferir(Integer numeroContaDestino, double valor) {
		try {
			Conta.contas.get(LoginCliente.getNumeroContaLogin()).sacar(valor);
			contas.get(numeroContaDestino).depositar(valor);
		} catch (NullPointerException e) {
			System.out.println("O número da conta de destino é inválido");
		}
	}
	
	public void usarLimiteDisponivelTransferir(int numeroContaDestino, double valor) {
		try {
			double diferenca = getSaldo()-valor;
			setLimiteDeCredito(limiteDeCredito + diferenca);
			this.setSaldo(0);
			contas.get(numeroContaDestino).depositar(valor);
		} catch (NullPointerException e) {
			System.out.println("O número da conta de destino é inválido");
		}
	}
	
	@Override
	public String exibirConta() {
		return super.exibirConta() + "Limite disponível R$: " + String.format("%.2f", this.limiteDeCredito) + "\n";
	}
}
