package entities;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import enums.TipoOperacao;

public class Operacao {
	private Instant momentoDeCriacao = Instant.now();
	private TipoOperacao tipoOperacao;
	private double valor;
	private String titular;
	
	public Operacao(TipoOperacao tipoOperacao, double valor, String titular) {
		this.tipoOperacao = tipoOperacao;
		this.valor = valor;
		this.titular = titular;
	}
	
	public void exibirOperacao() {
		ZonedDateTime zonedDateTime = momentoDeCriacao.atZone(ZoneId.systemDefault());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String dataFormatada = formatter.format(zonedDateTime);
		System.out.println("Operação: " + tipoOperacao.getMensagem()+"R$ " + String.format("%.2f", this.valor));
		System.out.println("Realizado por: " + titular);
		System.out.println("Data: " + dataFormatada + "\n");
	}
}
