package entities;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class Conta {
	public static Map<Integer, Conta> contas = new HashMap<>();
	private static int contadorConta=0;
	
	private short agencia;
	private Integer numeroConta = 0;
	private Set<Cliente> titulares = new HashSet<Cliente>();
	private Instant dataDaCriacao = Instant.now();
	private double saldo;
	protected List<Operacao> historicoOperacoes = new LinkedList<>();
	
	public Conta(short agencia, Set<Cliente> titulares, double saldo) {
		this.agencia = agencia;
		this.numeroConta = contadorConta+1;
		contadorConta++;
		this.titulares = titulares;
		this.saldo = saldo;
	}

	public short getAgencia() {
		return agencia;
	}

	public int getNumeroConta() {
		return numeroConta;
	}

	public Set<Cliente> getTitulares() {
		return titulares;
	}

	public double getSaldo() {
		return saldo;
	}

	protected void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public abstract double sacar(double valor);
	
	public abstract void transferir(Integer numeroContaDestino, double valor);
	
	public void depositar(double valor) {
		setSaldo(getSaldo()+valor);
	}
	
	public void adicionarOperacao(Operacao operacao) {
		historicoOperacoes.add(0, operacao);
	}
	
	public String exibirConta() {
		String mensagem = "Agência: " + getAgencia()+
				"\nNúmero da conta: " + getNumeroConta();
		for(Cliente clientes: getTitulares()) {
			mensagem += clientes.exibirCliente();
		}
		mensagem +="\nSaldo: R$ " + String.format("%.2f", this.getSaldo());
		
		ZonedDateTime zonedDateTime = dataDaCriacao.atZone(ZoneId.systemDefault());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String dataFormatada = formatter.format(zonedDateTime);
		mensagem += "\nData de criação: "+dataFormatada+"\n";
		return mensagem;		
	}
	
	public void exibirMovimentacaoDaConta() {
		System.out.println("Histórico de operações ");
		if(historicoOperacoes.size()>0) {
			for(Operacao operacao : historicoOperacoes) {
				operacao.exibirOperacao();
			}
		}
		else {
			System.out.println("Não há registros de operações bancárias nessa conta");
		}
	}
}
