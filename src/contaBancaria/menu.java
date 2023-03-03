package contaBancaria;

import java.util.InputMismatchException;
import java.util.Scanner;

import contaBancaria.controler.Controller;
import contaBancaria.model.Conta;
import contaBancaria.model.ContaCorrente;
import contaBancaria.model.ContaPoupanca;
import contaBancaria.util.Cores;

public class menu {

	public static void main(String[] args) {

		Cores cor = new Cores();

		Controller contas = new Controller();

		var contaTeste = new ContaCorrente("João Victor", 1, 1, 123, 0, 0);
		contas.cadastrar(contaTeste);

		Scanner leia = new Scanner(System.in);

		boolean loop = true;

		int codigoOperacao = 1, numero = 0, agencia = 0, tipo = 0, aniversario = 0;
		float saldo = 0, limite = 0;
		String titular;

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
								+ "\n6 - Atualizar dados\n7 - Buscar conta\n8 - Listar contas\n9 - Apagar Conta\n0 - Sair");
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
				System.out.println(cor.TEXT_WHITE + "\nCriar conta:" + "\n\nDigite o nome completo do titular:");
				leia.skip("\\R?");
				titular = leia.nextLine();

				do {
					try {
						System.out.println("\nDigite o número da agência:");
						agencia = leia.nextInt();

						loop = false;
					} catch (InputMismatchException erro) {
						System.out.println("\nPor favor, digite apenas números.");
						leia.next();

						loop = true;
					}
				} while (loop);
				do {
					try {
						do {
							System.out.println("\nSelecione o tipo da conta: \n1 - Conta Corrente\n2- Conta Poupança");
							tipo = leia.nextInt();
						} while (tipo < 1 && tipo > 2);

						loop = false;
					} catch (InputMismatchException erro) {
						System.out.println("\nOPÇÃO INVÁLDA! Por favor, digite o número da opção.");
						leia.next();

						loop = true;
					}
				} while (loop);
				switch (tipo) {
				case 1 -> {
					do {
						try {
							System.out.println("\nDigite o valor do limite de crédito da conta:");
							limite = leia.nextFloat();

							loop = false;
						} catch (InputMismatchException erro) {
							System.out.println("\nPor favor, digite apenas números.");
							leia.next();

							loop = true;
						}
					} while (loop);
					contas.cadastrar(new ContaCorrente(titular, tipo, contas.gerarNumero(), agencia, saldo, limite));

					System.out.println("\nConta criada com sucesso!");
					contas.getContas().get(contas.gerarNumero()).visualizar();
				}
				case 2 -> {
					do {
						try {
							System.out.println("\nDigite o dia do deu aniversário:");
							aniversario = leia.nextInt();

							loop = false;
						} catch (InputMismatchException erro) {
							System.out.println("\nPor favor, digite apenas números.");
							leia.next();

							loop = true;
						}
					} while (loop);
					contas.cadastrar(
							new ContaPoupanca(titular, tipo, contas.gerarNumero(), agencia, saldo, aniversario));

					System.out.println("\nConta criada com sucesso!");
				}
				}
				break;
			case 2:

				break;
			case 3:
				System.out.println("\nDigite o número da conta:");
				numero = leia.nextInt();
				Conta conta = contas.getContas().get(numero - 1);
				System.out.println("\nSeu saldo: R$ " + conta.getSaldo() + ".");
				System.out.println("\nDigite valor do saque (em R$):");
				float valor = leia.nextFloat();
				contas.sacar(numero, valor);
				break;
			case 4:

				break;
			case 5:

				break;
			case 6:
				do {
					try {
						do {
							System.out.println("\nDigite o número da conta:");
							numero = leia.nextInt();
						} while (numero < 0);
						loop = false;

					} catch (InputMismatchException erro) {
						System.out.println("\nPor favor, digite apenas números.");
						leia.next();

						loop = true;
					}
				} while (loop);

				var contaAtualizada = contas.procurarPorNumero(numero);

				int tipoConta = contaAtualizada.getTipo();

				if (contaAtualizada != null) {
					if (tipoConta == 1) {
						do {
							try {
								do {
									System.out
											.println("Digite qual dado deseja atualizar:\n1 - Atualizar nome do Titular"
													+ "\n2 - Atualizar Agência\n3 - Atualizar limite de crédito\n4 - Alterar para Conta Poupança");
									codigoOperacao = leia.nextInt();
								} while (codigoOperacao < 1 && codigoOperacao > 4);

								loop = false;

							} catch (InputMismatchException erro) {
								System.out.println("\nOPÇÃO INVÁLDA! Por favor, digite o número da opção.");
								leia.next();

								loop = true;
							}
						} while (loop);
					} else {
						do {
							try {
								do {
									System.out
											.println("Digite qual dado deseja atualizar:\n1 - Atualizar nome do Titular"
													+ "\n2 - Atualizar Agência\n3 - Atualizar data de aniversário"
													+ "\n4 - Alterar para Conta Corrente");
									codigoOperacao = leia.nextInt();
								} while (codigoOperacao < 1 && codigoOperacao > 4);

								loop = false;

							} catch (InputMismatchException erro) {
								System.out.println("\nOPÇÃO INVÁLDA! Por favor, digite o número da opção.");
								leia.next();

								loop = true;
							}
						} while (loop);
					}

					switch (codigoOperacao) {
					case 1:
						contas.atualizar(numero, codigoOperacao);
						break;
					case 2:
						contas.atualizar(numero, codigoOperacao);
						break;
					case 3: 
						if (tipoConta == 1) {
							do {
								try {
							System.out.println("\nDigite o novo valor do limite de crédito da conta:");
							limite = leia.nextFloat();
							
							loop = false;
								} catch (InputMismatchException erro){
									System.out.println("\nPor favor, digite apenas números.");
									leia.next();
									
									loop = true;
								}
						} while (loop);
							
							contas.atualizarLimite(numero, limite);
							
						} else if (tipoConta == 2) {
							do {
								try {
									System.out.println("\nDigite o dia do deu aniversário atualizado:");
									aniversario = leia.nextInt();

									loop = false;
								} catch (InputMismatchException erro) {
									System.out.println("\nPor favor, digite apenas números.");
									leia.next();

									loop = true;
								}
							} while (loop);
							
							contas.atualizarAniversario(numero, aniversario);
							
						}
						break;
					case 4:
						if (tipoConta == 1) {
							do {
								try {
									do {
										System.out.println("\nDeseja atualizar sua Conta Corrente para Conta Poupança?"
												+ "\n1 - Sim\n2 - Não");
										codigoOperacao = leia.nextInt();
									} while (codigoOperacao < 1 && codigoOperacao > 2);

									loop = false;
								} catch (InputMismatchException erro) {
									System.out.println("\nOPÇÃO INVÁLDA! Por favor, digite o número da opção.");
									leia.next();

									loop = true;
								}
							} while (loop);
							if (codigoOperacao == 1) {
								do {
									try {
										System.out.println("\nDigite o dia do deu aniversário:");
										aniversario = leia.nextInt();

										loop = false;
									} catch (InputMismatchException erro) {
										System.out.println("\nPor favor, digite apenas números.");
										leia.next();

										loop = true;
									}
								} while (loop);
								contas.atualizarParapoupanca(numero, aniversario);
							}
						} else if (tipoConta == 2) {
							do {
								try {
									do {
										System.out.println("\nDeseja atualizar sua Conta Poupança para Conta Corrente?"
												+ "\n1 - Sim\n2 - Não");
										codigoOperacao = leia.nextInt();
									} while (codigoOperacao < 1 && codigoOperacao > 2);

									loop = false;
								} catch (InputMismatchException erro) {
									System.out.println("\nOPÇÃO INVÁLDA! Por favor, digite o número da opção.");
									leia.next();

									loop = true;
								}
							} while (loop);
							if (codigoOperacao == 1) {
								do {
									try {
										System.out.println("\nDigite o valor do limite de crédito da conta:");
										limite = leia.nextFloat();

										loop = false;
									} catch (InputMismatchException erro) {
										System.out.println("\nPor favor, digite apenas números.");
										leia.next();

										loop = true;
									}
								} while (loop);
								contas.atualizarParaCorrente(numero, limite);
							}
						}

					}
				}
				break;
			case 7:

				break;
			case 8:
				contas.listarTodas();
				break;
			case 9:

				break;
			}
		}
	}
}
