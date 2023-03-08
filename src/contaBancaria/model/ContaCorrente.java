package contaBancaria.model;

public class ContaCorrente extends Conta {
	
	float limite;

	public ContaCorrente(String titular, String senha, int tipo, int numero, int agencia, float saldo, float limite) {
		super(titular, senha, tipo, numero, agencia, saldo);
		this.limite = limite;
	}

	public float getLimite() {
		return limite;
	}

	public void setLimite(float limite) {
		this.limite = limite;
	}
	
	@Override
	public boolean sacar(float valor) {
		if (getSaldo() + getLimite() < valor) {
			System.out.println(cor.TEXT_RED_BOLD_BRIGHT + "Seu saldo é insulficiente." + cor.TEXT_RESET);
			return false;
		}
		
		setSaldo(getSaldo() - valor);
		if (getSaldo() < 0) {
			setLimite(getLimite() + getSaldo());
			setSaldo(0);
		}
		System.out.printf("Seu saldo atual é R$%f e seu limite é R$%f", getSaldo(), getLimite());
		return true;
	}
	
	
	@Override
	public void visualizar() {
		super.visualizar();
		System.out.println("Limite de Crédito: " + getLimite() + "."
				+ cor.TEXT_YELLOW + "\n________________________________________________________\n" + cor.TEXT_RESET);
	}

}
