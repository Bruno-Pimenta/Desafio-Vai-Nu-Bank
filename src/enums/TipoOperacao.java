package enums;

public enum TipoOperacao {
	DEPOSITO("depósito + "),
	SAQUE("saque - "),
	TRANFERENCIARECEBIDA("transferência + "),
	TRANFERENCIAFEITA("transferência - "),
	RENDIMENTOS("rendimentos + "),
	UTILIZACAOLIMITES("utilização do limite - ");
		
	private String mensagem;

	private TipoOperacao(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}
}
