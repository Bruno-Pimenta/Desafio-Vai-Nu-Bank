package enums;

public enum TipoOperacao {
	DEPOSITO("dep�sito + "),
	SAQUE("saque - "),
	TRANFERENCIARECEBIDA("transfer�ncia + "),
	TRANFERENCIAFEITA("transfer�ncia - "),
	RENDIMENTOS("rendimentos + "),
	UTILIZACAOLIMITES("utiliza��o do limite - ");
		
	private String mensagem;

	private TipoOperacao(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}
}
