package contaBancaria.controler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import contaBancaria.model.*;
import contaBancaria.repository.Repository;

public class Controller implements Repository {

	private List<Conta> contas = new ArrayList<Conta>();

	Scanner leia = new Scanner(System.in);

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	public int gerarNumero() {
		int numero = contas.size() + 1;
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

	@Override
	public void cadastrar(Conta conta) {
		contas.add(conta);
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
	public void atualizarLimite (int numero, float limite) {
		var conta = procurarPorNumero (numero);
		
		String titular = conta.getTitular();
		int tipo = conta.getTipo(), agencia = conta.getAgencia();
		float saldo = conta.getSaldo();
		ContaCorrente nova = new ContaCorrente (titular, tipo, numero, agencia, saldo, limite);
		contas.set((numero - 1), conta);
		System.out.println("\nSeu limite de crédito foi atuaizado para: R$" + limite + ".");
	}
	
	@Override
	public void atualizarAniversario (int numero, int aniversario) {
	var conta = procurarPorNumero (numero);
		
		String titular = conta.getTitular();
		int tipo = conta.getTipo(), agencia = conta.getAgencia();
		float saldo = conta.getSaldo();
		ContaCorrente nova = new ContaCorrente (titular, tipo, numero, agencia, saldo, aniversario);
		contas.set((numero - 1), conta);
		System.out.println("\nSeu aniversário foi atuaizado para: " + aniversario + ".");
	}
	
	@Override
	public void atualizarParaCorrente (int numero, float limite) {
		var conta = procurarPorNumero (numero);
		
			conta.setTipo(1);
			String titular = conta.getTitular();
			int tipo = conta.getTipo(), agencia = conta.getAgencia();
			float saldo = conta.getSaldo();
			ContaCorrente nova = new ContaCorrente (titular, tipo, numero, agencia, saldo, limite);
			contas.set((numero - 1), nova);
			System.out.println("\nSua Conta Poupança foi alterada para Conta Corrente, " + conta.getTitular() + ".");
		
	}
	
	@Override
	public void atualizarParapoupanca (int numero, int aniversario) {
		var conta = procurarPorNumero (numero);
		
			conta.setTipo(2);
			String titular = conta.getTitular();
			int tipo = conta.getTipo(), agencia = conta.getAgencia();
			float saldo = conta.getSaldo();
			ContaCorrente nova = new ContaCorrente (titular, tipo, numero, agencia, saldo, aniversario);
			contas.set((numero - 1), nova);
			System.out.println("\nSua Conta Corrente foi alterada para Conta Poupança, " + conta.getTitular() + ".");
		
	}

	@Override
	public void deletar(int numero) {
		contas.remove(numero - 1);
	}

	@Override
	public void sacar(int numero, float valor) {
		Conta conta = contas.get(numero - 1);
		conta.sacar(valor);
	}

	@Override
	public void depositar(int numero, float valor) {
	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
	}

}
