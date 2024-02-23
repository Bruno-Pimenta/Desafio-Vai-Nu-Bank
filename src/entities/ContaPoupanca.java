package entities;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

import authentication.LoginCliente;
import enums.TipoOperacao;

public class ContaPoupanca extends Conta{
	private int diaDoAniversarioDaConta;
	
	public ContaPoupanca(short agencia, Set<Cliente> titulares, int diaDoAniversarioDaConta, double saldo) {
		super(agencia, titulares, saldo);
		this.diaDoAniversarioDaConta = diaDoAniversarioDaConta;
	}
	
	public int getDiaDoAniversarioDaConta() {
		return diaDoAniversarioDaConta;
	}

	public double sacar(double valor) {
		setSaldo(getSaldo()-1.02*valor);
		return valor;
	}
	
	public void transferir(Integer numeroContaDestino, double valor) {
		try {
			Conta.contas.get(LoginCliente.getNumeroContaLogin()).sacar(valor);
			contas.get(numeroContaDestino).depositar(valor);
		} catch (NullPointerException e) {
			System.out.println("O n�mero da conta de destino � inv�lido");
		}
	}
	
	public static void rendimentosAniversario(ContaPoupanca conta) {
		int diaAniversario = conta.getDiaDoAniversarioDaConta();
		Instant dataAtual = Instant.now();
		ZonedDateTime zonedDateTime =dataAtual.atZone(ZoneId.systemDefault());
		int diaDoMes = zonedDateTime.getDayOfMonth();
		int mes = zonedDateTime.getMonthValue();
		if(mes==2) {
			if(diaDoMes == 28 && diaAniversario == 29 ||diaAniversario == 30 || diaAniversario == 31) {
				double valor = conta.getSaldo()*0.01;
				conta.depositar(valor);
				Operacao operacao = new Operacao(TipoOperacao.RENDIMENTOS,valor, "Vai NuBank" );
				conta.adicionarOperacao(operacao);
			} else if(diaDoMes>=1 && diaDoMes<=28 && conta.getDiaDoAniversarioDaConta()==diaDoMes) {
				double valor = conta.getSaldo()*0.01;
				conta.depositar(valor);
				Operacao operacao = new Operacao(TipoOperacao.RENDIMENTOS,valor, "Vai NuBank" );
				conta.adicionarOperacao(operacao);
			}
		}
		else if (mes==4||mes==6||mes==9||mes==11) {
			if(diaDoMes == 30 && diaAniversario == 31) {
				double valor = conta.getSaldo()*0.01;
				conta.depositar(valor);
				Operacao operacao = new Operacao(TipoOperacao.RENDIMENTOS,valor, "Vai NuBank" );
				conta.adicionarOperacao(operacao);
			} else if(diaDoMes>=1 && diaDoMes<=30 && conta.getDiaDoAniversarioDaConta()==diaDoMes) {
				double valor = conta.getSaldo()*0.01;
				conta.depositar(valor);
				Operacao operacao = new Operacao(TipoOperacao.RENDIMENTOS,valor, "Vai NuBank" );
				conta.adicionarOperacao(operacao);
			}
		}
		else if(conta.getDiaDoAniversarioDaConta()==diaDoMes) {
			double valor = conta.getSaldo()*0.01;
			conta.depositar(valor);
			Operacao operacao = new Operacao(TipoOperacao.RENDIMENTOS,valor, "Vai NuBank" );
			conta.adicionarOperacao(operacao);
		}
	}
	
	@Override
	public String exibirConta() {
		return "Conta Poupan�a: "+super.exibirConta() + "Dia do anivers�rio dos rendimentos: " + this.diaDoAniversarioDaConta + "\n";
	}
}
