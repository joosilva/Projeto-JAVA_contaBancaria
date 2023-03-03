package contaBancaria.model;

import contaBancaria.util.*;

public abstract class Conta {

	Cores cor = new Cores();

	private int numero;
	private int agencia;
	private int tipo;
	private String titular;
	private float saldo = 0;

	public Conta(String titular, int tipo, int numero, int agencia, float saldo) {
		super();
		this.numero = numero;
		this.agencia = agencia;
		this.tipo = tipo;
		this.titular = titular;
		this.saldo = saldo;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public boolean sacar(float valor) {
		if (getSaldo() < valor) {
			System.out.println(cor.TEXT_RED_BOLD_BRIGHT + "Seu saldo é insulficiente." + cor.TEXT_RESET);
			return false;
		}

		setSaldo(getSaldo() - valor);
		System.out.println("Seu saldo atual é R$" + getSaldo() + ".");
		return true;
	}

	public void depositar(float valor) {
		setSaldo(getSaldo() + valor);
		System.out.printf("Seu saldo atual é R$%f.", getSaldo());
	}

	public void visualizar() {
		String tipo = "";

		switch (this.tipo) {
		case 1:
			tipo = "Conta Corrente";
			break;
		case 2:
			tipo = "Conta Poupança";
			break;
		}

		System.out.println(cor.TEXT_YELLOW + "\n____________________DADOS BANCÁRIOS:____________________"
				+ cor.TEXT_RESET + "\n\nTitular da Conta: " + getTitular() + ".\n" 
				+ tipo + " - " + getNumero() + "." + "\nAgência: " + getAgencia() + "."
				+ "\nSaldo: R$" + getSaldo() + ".");	
		}

}
