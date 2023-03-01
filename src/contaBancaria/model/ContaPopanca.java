package contaBancaria.model;

public class ContaPopanca extends Conta{
	
	int aniversario;
	
	public ContaPopanca(String titular, int tipo, int numero, int agencia, float saldo, int aniversario) {
		super(titular, tipo, numero, agencia, saldo);
		this.aniversario = aniversario;
	}

	public int getAniversario() {
		return aniversario;
	}

	public void setAniversario(int aniversario) {
		this.aniversario = aniversario;
	}

	@Override
	public void visualizar() {
		super.visualizar();
		System.out.println("\nData de niversário: " + getAniversario() + "."
				+ cor.TEXT_YELLOW + "\n________________________________________________________\n" + cor.TEXT_RESET);
	}
}
