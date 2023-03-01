package contaBancaria;

import contaBancaria.model.Conta;
import contaBancaria.model.ContaCorrente;
import contaBancaria.model.ContaPopanca;
import contaBancaria.util.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class menu {

	public static void main(String[] args) {

		Cores cor = new Cores();

		Conta conta = new Conta("João Silva", 1, 123, 456, 1500);

		Scanner leia = new Scanner(System.in);

		boolean loop = true;

		int codigoOperacao = 1;
		double saldo = 1000, valor, saque, deposito;

		System.out.println(cor.TEXT_YELLOW_BOLD + "\t\nBEM-VINDE AO BANCO GEN!" + cor.TEXT_RESET);

		while (codigoOperacao >= 0 && codigoOperacao <= 9) {
			do {
				try {
					System.out.println(
							"\nEscolha uma opção:\n1 - Criar conta\n2 - Saldo\n3 - Saque\n4 - Depósito\n5 - Tranferência"
									+ "\n6 - Atualizar dados\n7 - Buscar contas\n8 - Listar contas\n9 - Apagar Conta\n0 - Sair");
					codigoOperacao = leia.nextInt();

					while (codigoOperacao < 0 || codigoOperacao > 9) {
						System.out.println("\nOPÇÃO INVÁLDA!"
								+ "\n\nEscolha uma opção:\n1 - Criar conta\n2 - Saldo\n3 - Saque\n4 - Depósito\n5 - Tranferência"
								+ "\n6 - Atualizar dados\n7 - Buscar contas\n8 - Listar contas\n9 - Apagar Conta\n0 - Sair");
						codigoOperacao = leia.nextInt();
					}
					loop = false;

				} catch (InputMismatchException erro) {
					System.out.println("\nOPÇÃO INVÁLDA! Por favor, digite o número da opção.");
					leia.next();
				}
			} while (loop);

			if (codigoOperacao == 0) {
				do {
					try {
						System.out.println("Deseja sair?\n1 - Sim\n2 - Não");
						int opcaoSair = leia.nextInt();
						
						if (opcaoSair == 1) {
						System.out.println("\nObrigado! Volte sempre!");
						leia.close();
						System.exit(0);
						
						while (opcaoSair < 1 || opcaoSair > 2) {
							System.out.println("Deseja sair?\n1 - Sim\n2 - Não");
							opcaoSair = leia.nextInt();
						}
						
						loop = false;
						}
					} catch (InputMismatchException erro) {
						System.out.println("\nOPÇÃO INVÁLDA! Por favor, digite o número da opção.");
						leia.next();
						
						loop = true;
					}
				} while (loop);
			}

			switch (codigoOperacao) {
			case 1:

				break;
			case 2:

				break;
			case 3:

				break;
			case 4:

				break;
			case 5:

				break;
			case 6:

				break;
			case 7:

				break;
			case 8:
				conta.visualizar();
				break;
			case 9:

				break;
			}

		}

	}

}
