package contaBancaria;

import java.util.InputMismatchException;
import java.util.Scanner;

public class menu {

	public static void main(String[] args) {

		Scanner leia = new Scanner(System.in);

		boolean loop = true;

		int codigoOperacao;
		double saldo = 1000, valor, saque, deposito;

		System.out.println("\t\nBEM-VINDE AO BANCO GEN!");

		while (true) {
			do {
				try {
					System.out.println(
							"\nESCOLHA UMA OPÇÃO:\n1 - CRIAR CONTA\n2 - SALDO\n3 - SAQUE\n4 - DEPÓSITO\n5 - TRANSFERÊNCIA"
									+ "\n6 - ATUALIZAR DADOS\n7 - BUSCAR CONTA\n8 - LISTAR CONTAS\n9 - APAGAR CONTA\n0 - SAIR");
					codigoOperacao = leia.nextInt();

					while (codigoOperacao < 0 || codigoOperacao > 9) {
						System.out.println("\nOPÇÃO INVÁLDA"
								+ "\nESCOLHA UMA OPÇÃO:\n1 - CRIAR CONTA\n2 - SALDO\n3 - SAQUE\n4 - DEPÓSITO\n5 - TRANSFERÊNCIA"
								+ "\n6 - ATUALIZAR DADOS\n7 - BUSCAR CONTA\n8 - LISTAR CONTAS\n9 - APAGAR CONTA");
						codigoOperacao = leia.nextInt();
					}
				} catch (InputMismatchException erro) {
					System.out.println("\nOPÇÃO INVÁLDA, DIGITE O NÚMERO DA OPÇÃO!"
							+ "\nESCOLHA UMA OPÇÃO:\n1 - CRIAR CONTA\n2 - SALDO\n3 - SAQUE\n4 - DEPÓSITO\n5 - TRANSFERÊNCIA"
							+ "\n6 - ATUALIZAR DADOS\n7 - BUSCAR CONTA\n8 - LISTAR CONTAS\n9 - APAGAR CONTA");
					codigoOperacao = leia.nextInt();
				}
			} while (loop);

			if (codigoOperacao == 0) {
				System.out.println("\nObrigado! Volte sempre!");
				leia.close();
				System.exit(0);
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

				break;
			case 9:

				break;
			}

		}

	}

}
