package contaBancaria.controler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import contaBancaria.model.*;
import contaBancaria.repository.Repository;

public class Controller implements Repository {

	private ArrayList<Conta> contas = new ArrayList<Conta>();

	Scanner leia = new Scanner(System.in);

	public ArrayList<Conta> getContas() {
		return contas;
	}

	public void setContas(ArrayList<Conta> contas) {
		this.contas = contas;
	}

	@SuppressWarnings("unused")
	public int gerarNumero() {
		int numero = 0;
		for (int i = 0; i < contas.size(); i++) {
			if (contas.get(i) == null) {
				numero = i + 1;
				return numero;
			} else {
				numero = contas.size() + 1;
				return numero;
			}
		}

		return numero;
	}

	@Override
	public Conta procurarPorNumero(int numero) {
		try {
			var conta = contas.get(numero - 1);
			return conta;
		} catch (IndexOutOfBoundsException erro) {
			System.out.println("\nConta não encontrada!");
			return null;
		}
	}

	@Override
	public void listarTodas() {
		for (var conta : contas) {
			conta.visualizar();
		}

	}

	public void visualizar(int numero) {
		Conta conta = contas.get(numero - 1);
		conta.visualizar();
	}

	@Override
	public void cadastrar(Conta conta) {
		int numero = conta.getNumero() - 1;
		try {
			if (contas.get(numero) == null) {
				contas.set(numero, conta);
			}
		} catch (IndexOutOfBoundsException erro) {
			contas.add(conta);
		}
	}

	@Override
	public void atualizar(int numero, int opcao) {
		var conta = procurarPorNumero(numero);

		switch (opcao) {
		case 1 -> {
			System.out.println("Digite o nome do novo titular: ");
			leia.skip("\\R?");
			String nome = leia.nextLine();
			conta.setTitular(nome);
		}
		case 2 -> {
			System.out.println("Digite o novo número de agência da conta:");
			int agencia = leia.nextInt();
			conta.setAgencia(agencia);
		}
		}
	}

	@Override
	public void atualizarLimite(int numero, float limite) {
		var conta = procurarPorNumero(numero);

		String titular = conta.getTitular();
		int tipo = conta.getTipo(), agencia = conta.getAgencia();
		float saldo = conta.getSaldo();
		ContaCorrente nova = new ContaCorrente(titular, tipo, numero, agencia, saldo, limite);
		contas.set((numero - 1), nova);
		System.out.println("\nSeu limite de crédito foi atuaizado para: R$" + limite + ".");
	}

	@Override
	public void atualizarAniversario(int numero, int aniversario) {
		var conta = procurarPorNumero(numero);

		String titular = conta.getTitular();
		int tipo = conta.getTipo(), agencia = conta.getAgencia();
		float saldo = conta.getSaldo();
		ContaCorrente nova = new ContaCorrente(titular, tipo, numero, agencia, saldo, aniversario);
		contas.set((numero - 1), nova);
		System.out.println("\nSeu aniversário foi atuaizado para: " + aniversario + ".");
	}

	@Override
	public void atualizarParaCorrente(int numero, float limite) {
		var conta = procurarPorNumero(numero);

		conta.setTipo(1);
		String titular = conta.getTitular();
		int tipo = conta.getTipo(), agencia = conta.getAgencia();
		float saldo = conta.getSaldo();
		ContaCorrente nova = new ContaCorrente(titular, tipo, numero, agencia, saldo, limite);
		contas.set((numero - 1), nova);
		System.out.println("\nSua Conta Poupança foi alterada para Conta Corrente, " + conta.getTitular() + ".");

	}

	@Override
	public void atualizarParapoupanca(int numero, int aniversario) {
		var conta = procurarPorNumero(numero);

		conta.setTipo(2);
		String titular = conta.getTitular();
		int tipo = conta.getTipo(), agencia = conta.getAgencia();
		float saldo = conta.getSaldo();
		ContaCorrente nova = new ContaCorrente(titular, tipo, numero, agencia, saldo, aniversario);
		contas.set((numero - 1), nova);
		System.out.println("\nSua Conta Corrente foi alterada para Conta Poupança, " + conta.getTitular() + ".");

	}

	@Override
	public void deletar(int numero) {
		contas.set(numero - 1, null);
	}

	@Override
	public void sacar(int numero, float valor) {
		Conta conta = contas.get(numero - 1);
		conta.sacar(valor);
	}

	@Override
	public void depositar(int numero, float valor) {
		Conta conta = contas.get(numero - 1);
		float saldo = conta.getSaldo();
		conta.setSaldo(saldo + valor);
	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		Conta contaOrigem = contas.get(numeroOrigem - 1), contaDestino = contas.get(numeroDestino - 1);
		float saldoOrigem = contaOrigem.getSaldo(), saldoDestino = contaDestino.getSaldo();
		contaDestino.setSaldo(saldoDestino + valor);
		contaOrigem.setSaldo(saldoOrigem - valor);
	}

}
