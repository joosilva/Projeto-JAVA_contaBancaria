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

		Conta contaTeste = new ContaCorrente("João Victor", "123", 1, 1, 123, 0, 0);
		contas.cadastrar(contaTeste);

		Scanner leia = new Scanner(System.in);

		boolean loop = true;

		int codigoOperacao = 1, numero = 0, agencia = 0, tipo = 0, aniversario = 0, i = 0;
		float saldo = 0, limite = 0;
		String titular, senha = "";

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

				senha = contas.criarSenha();

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
							System.out.println("\nSelecione o tipo da conta: \n1 - Conta Corrente\n2 - Conta Poupança");
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

					numero = contas.gerarNumero();
					contas.cadastrar(new ContaCorrente(titular, senha, tipo, numero, agencia, saldo, limite));

					System.out.println("\nConta criada com sucesso!");
					contas.getContas().get(numero - 1).visualizar();
				}
				case 2 -> {
					do {
						try {
							do {
								System.out.println("\nDigite o dia do deu aniversário:");
								aniversario = leia.nextInt();
							} while (aniversario > 31);

							loop = false;
						} catch (InputMismatchException erro) {
							System.out.println("\nPor favor, digite apenas números.");
							leia.next();

							loop = true;
						}
					} while (loop);

					numero = contas.gerarNumero();
					contas.cadastrar(new ContaPoupanca(titular, senha, tipo, numero, agencia, saldo, aniversario));

					System.out.println("\nConta criada com sucesso!");
					contas.getContas().get(numero - 1).visualizar();
				}
				}
				break;
			case 2:
				do {
					try {
						do {
							System.out.println("\nDigite o número da conta:");
							numero = leia.nextInt();
						} while (numero < 0);
						
					} catch (InputMismatchException erro) {
						System.out.println("\nPor favor, digite apenas números.");
						leia.next();

						loop = true;
					}
					if(contas.procurarPorNumero(numero) == null) {
						loop = true;
					} else {
						loop = false;
					}
				} while (loop);

				i = 1;
				do {
					var conta = contas.procurarPorNumero(numero);
					System.out.println("\nDigite sua senha:");
					senha = leia.next();

					if (senha.equals(conta.getSenha())) {
						loop = false;
					} else if (i == 3) {
						System.out.println("\nNúmero de tentativas excedido!");
						loop = false;
					} else {
						System.out.println("\nSenha incorreta!");
						i++;
						loop = true;
					}

				} while (loop);
				if (senha.equals(contas.getContas().get(numero - 1).getSenha())) {
					float saldoConta = contas.procurarPorNumero(numero).getSaldo();
					System.out.println("\nSeu saldo é de R$" + saldoConta + ".");
				}
				break;
			case 3:
				do {
					try {
						do {
							System.out.println("\nDigite o número da conta:");
							numero = leia.nextInt();
						} while (numero < 0);
						
					} catch (InputMismatchException erro) {
						System.out.println("\nPor favor, digite apenas números.");
						leia.next();

						loop = true;
					}
					if(contas.procurarPorNumero(numero) == null) {
						loop = true;
					} else {
						loop = false;
					}
				} while (loop);

				i = 1;
				do {
					var conta = contas.procurarPorNumero(numero);
					System.out.println("\nDigite sua senha:");
					senha = leia.next();

					if (senha.equals(conta.getSenha())) {
						loop = false;
					} else if (i == 3) {
						System.out.println("\nNúmero de tentativas excedido!");
						loop = false;
					} else {
						System.out.println("\nSenha incorreta!");
						i++;
						loop = true;
					}

				} while (loop);
				if (senha.equals(contas.getContas().get(numero - 1).getSenha())) {
					Conta conta = contas.procurarPorNumero(numero);
					float valor = 0;
					System.out.println("\nSeu saldo: R$ " + conta.getSaldo() + ".");
					do {
						try {
							do {
								System.out.println("\nDigite valor do saque (em R$):");
								valor = leia.nextInt();

								if (valor < 0) {
									System.out
											.println("\nNão é possível realizar a operação de saque com essse valor.");
								}
							} while (valor < 0);
							loop = false;

						} catch (InputMismatchException erro) {
							System.out.println("\nPor favor, digite apenas números.");
							leia.next();

							loop = true;
						}
					} while (loop);

					i = 1;
					do {
						System.out.println("\nDigite sua senha:");
						senha = leia.next();

						if (senha.equals(contas.getContas().get(numero - 1).getSenha())) {
							loop = false;
						} else if (i == 3) {
							System.out.println("\nNúmero de tentativas excedido!");
							loop = false;
						} else {
							System.out.println("\nSenha incorreta!");
							i++;
							loop = true;
						}

					} while (loop);

					if (senha.equals(contas.getContas().get(numero - 1).getSenha())) {
						contas.sacar(numero, valor);
						System.out.println("\nSaldo atual: R$ " + conta.getSaldo() + ".");
					}
				}
				break;
			case 4:
				do {
					try {
						do {
							System.out.println("\nDigite o número da conta:");
							numero = leia.nextInt();
						} while (numero < 0);
						
					} catch (InputMismatchException erro) {
						System.out.println("\nPor favor, digite apenas números.");
						leia.next();

						loop = true;
					}
					if(contas.procurarPorNumero(numero) == null) {
						loop = true;
					} else {
						loop = false;
					}
				} while (loop);

				i = 1;
				do {
					var conta = contas.procurarPorNumero(numero);
					System.out.println("\nDigite sua senha:");
					senha = leia.next();

					if (senha.equals(conta.getSenha())) {
						loop = false;
					} else if (i == 3) {
						System.out.println("\nNúmero de tentativas excedido!");
						loop = false;
					} else {
						System.out.println("\nSenha incorreta!");
						i++;
						loop = true;
					}

				} while (loop);
				if (senha.equals(contas.getContas().get(numero - 1).getSenha())) {
					do {
						float valorDeposito = 0;
						try {
							do {
								System.out.println("\nDigite o valor a ser depositado (em R$):");
								valorDeposito = leia.nextInt();
								if (valorDeposito < 0) {
									System.out
											.println("Não é possível realizar a operação de depósito com essse valor.");
								}
							} while (valorDeposito < 0);

							loop = false;

							var contaDeposito = contas.procurarPorNumero(numero);
							contas.depositar(numero, valorDeposito);

							System.out.println("\nSaldo atual: R$ " + contaDeposito.getSaldo() + ".");

						} catch (InputMismatchException erro) {
							System.out.println("\nPor favor, digite apenas números.");
							leia.next();

							loop = true;
						}
					} while (loop);
				}
				break;
			case 5:
				Conta contaOrigem, contaDestino;

				do {
					try {
						do {
							System.out.println("\nDigite o número da conta:");
							numero = leia.nextInt();
						} while (numero < 0);

					} catch (InputMismatchException erro) {
						System.out.println("\nPor favor, digite apenas números.");
						leia.next();

						loop = true;
					}
					if (contas.procurarPorNumero(numero) == null) {
						loop = true;
					} else {
						loop = false;
					}
				} while (loop);

				i = 1;
				do {
					var conta = contas.procurarPorNumero(numero);
					System.out.println("\nDigite sua senha:");
					senha = leia.next();

					if (senha.equals(conta.getSenha())) {
						loop = false;
					} else if (i == 3) {
						System.out.println("\nNúmero de tentativas excedido!");
						loop = false;
					} else {
						System.out.println("\nSenha incorreta!");
						i++;
						loop = true;
					}

				} while (loop);
				if (senha.equals(contas.getContas().get(numero - 1).getSenha())) {
					contaOrigem = contas.procurarPorNumero(numero);
					System.out.println("\nSeu saldo é de R$" + contaOrigem.getSaldo() + ".");

					float valorTransferencia = 0;

					do {
						try {
							do {
								System.out.println("\nDigite o valor que deseja transferir:");
								valorTransferencia = leia.nextInt();

								if (valorTransferencia < 0) {
									System.out.println(
											"Não é possível realizar a operação de transferência com essse valor.");
								} else if (valorTransferencia > contaOrigem.getSaldo()) {
									System.out.println("\nSaldo insuficiente!");
								}
							} while (valorTransferencia < 0 && valorTransferencia > contaOrigem.getSaldo());
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
								System.out.println("\nDigite o número da conta para a qual deseja transferir:");
								numero = leia.nextInt();
							} while (numero < 0);
							loop = false;

						} catch (InputMismatchException erro) {
							System.out.println("\nPor favor, digite apenas números.");
							leia.next();

							loop = true;
						}
						if (contas.procurarPorNumero(numero) == null) {
							loop = true;
						} else {
							loop = false;
						}
					} while (loop);

					i = 1;
					do {
						System.out.println("\nDigite sua senha:");
						senha = leia.next();

						if (senha.equals(contaOrigem.getSenha())) {
							loop = false;
						} else if (i == 3) {
							System.out.println("\nNúmero de tentativas excedido!");
							loop = false;
						} else {
							System.out.println("\nSenha incorreta!");
							i++;
							loop = true;
						}

					} while (loop);

					if (senha.equals(contaOrigem.getSenha())) {

						contaDestino = contas.procurarPorNumero(numero);

						contas.transferir(contaOrigem.getNumero(), contaDestino.getNumero(), valorTransferencia);
					}
				}
				break;
			case 6:
				do {
					try {
						do {
							System.out.println("\nDigite o número da conta:");
							numero = leia.nextInt();
						} while (numero < 0);

					} catch (InputMismatchException erro) {
						System.out.println("\nPor favor, digite apenas números.");
						leia.next();

						loop = true;
					}
					if (contas.procurarPorNumero(numero) == null) {
						loop = true;
					} else {
						loop = false;
					}
				} while (loop);

				i = 1;
				do {
					var conta = contas.procurarPorNumero(numero);
					System.out.println("\nDigite sua senha:");
					senha = leia.next();

					if (senha.equals(conta.getSenha())) {
						loop = false;
					} else if (i == 3) {
						System.out.println("\nNúmero de tentativas excedido!");
						loop = false;
					} else {
						System.out.println("\nSenha incorreta!");
						i++;
						loop = true;
					}

				} while (loop);
				if (senha.equals(contas.getContas().get(numero - 1).getSenha())) {
					var contaAtualizada = contas.procurarPorNumero(numero);

					int tipoConta = contaAtualizada.getTipo();

					if (contaAtualizada != null) {
						if (tipoConta == 1) {
							do {
								try {
									do {
										System.out.println(
												"Digite qual dado deseja atualizar:\n1 - Atualizar nome do Titular"
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
										System.out.println(
												"Digite qual dado deseja atualizar:\n1 - Atualizar nome do Titular"
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
									} catch (InputMismatchException erro) {
										System.out.println("\nPor favor, digite apenas números.");
										leia.next();

										loop = true;
									}
								} while (loop);

								i = 1;
								do {
									System.out.println("\nDigite sua senha:");
									senha = leia.next();

									if (senha.equals(contas.getContas().get(numero - 1).getSenha())) {
										loop = false;
									} else if (i == 3) {
										System.out.println("\nNúmero de tentativas excedido!");
										loop = false;
									} else {
										System.out.println("\nSenha incorreta!");
										i++;
										loop = true;
									}

								} while (loop);

								if (senha.equals(contas.getContas().get(numero - 1).getSenha())) {
									contas.atualizarLimite(numero, limite);
								}

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
											System.out.println(
													"\nDeseja atualizar sua Conta Corrente para Conta Poupança?"
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
									i = 1;
									do {
										System.out.println("\nDigite sua senha:");
										senha = leia.next();

										if (senha.equals(contas.getContas().get(numero - 1).getSenha())) {
											loop = false;
										} else if (i == 3) {
											System.out.println("\nNúmero de tentativas excedido!");
											loop = false;
										} else {
											System.out.println("\nSenha incorreta!");
											i++;
											loop = true;
										}

									} while (loop);

									if (senha.equals(contas.getContas().get(numero - 1).getSenha())) {
										contas.atualizarParapoupanca(numero, aniversario);
									}
								}
							} else if (tipoConta == 2) {
								do {
									try {
										do {
											System.out.println(
													"\nDeseja atualizar sua Conta Poupança para Conta Corrente?"
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
									i = 1;
									do {
										System.out.println("\nDigite sua senha:");
										senha = leia.next();

										if (senha.equals(contas.getContas().get(numero - 1).getSenha())) {
											loop = false;
										} else if (i == 3) {
											System.out.println("\nNúmero de tentativas excedido!");
											loop = false;
										} else {
											System.out.println("\nSenha incorreta!");
											i++;
											loop = true;
										}

									} while (loop);

									if (senha.equals(contas.getContas().get(numero - 1).getSenha())) {
										contas.atualizarParaCorrente(numero, limite);
									}
								}
							}
						}
					}
				}
				break;
			case 7:
				do {
					try {
						do {
							System.out.println("\nDigite o número da conta:");
							numero = leia.nextInt();
						} while (numero < 0);

					} catch (InputMismatchException erro) {
						System.out.println("\nPor favor, digite apenas números.");
						leia.next();

						loop = true;
					}
					if (contas.procurarPorNumero(numero) == null) {
						loop = true;
					} else {
						loop = false;
					}
				} while (loop);

				i = 1;
				do {
					var conta = contas.procurarPorNumero(numero);
					System.out.println("\nDigite sua senha:");
					senha = leia.next();

					if (senha.equals(conta.getSenha())) {
						loop = false;
					} else if (i == 3) {
						System.out.println("\nNúmero de tentativas excedido!");
						loop = false;
					} else {
						System.out.println("\nSenha incorreta!");
						i++;
						loop = true;
					}

				} while (loop);
				if (senha.equals(contas.getContas().get(numero - 1).getSenha())) {
					contas.visualizar(numero);
				}
				break;
			case 8:
				contas.listarTodas();
				break;
			case 9:
				do {
					try {
						do {
							System.out.println("\nDigite o número da conta:");
							numero = leia.nextInt();

						} while (numero < 0);
						loop = false;

						i = 1;
						do {
							System.out.println("\nDigite sua senha:");
							senha = leia.next();

							if (senha.equals(contas.getContas().get(numero - 1).getSenha())) {
								loop = false;
							} else if (i == 3) {
								System.out.println("\nNúmero de tentativas excedido!");
								loop = false;
							} else {
								System.out.println("\nSenha incorreta!");
								i++;
								loop = true;
							}

						} while (loop);

						if (senha.equals(contas.getContas().get(numero - 1).getSenha())) {
							Conta contaApagar = contas.procurarPorNumero(numero);
							contas.visualizar(numero);
						}

					} catch (InputMismatchException erro) {
						System.out.println("\nPor favor, digite apenas números.");
						leia.next();

						loop = true;
					} catch (IndexOutOfBoundsException erro) {
						System.out.println("\nConta não encontrada.");

						loop = true;
					} catch (NullPointerException erro) {

						loop = true;
					}
				} while (loop);

				Conta contaApagar = contas.procurarPorNumero(numero);

				do {
					int opcaoApagar = 0, opcaoTransferir = 0;
					try {
						do {
							System.out.println("Deseja apagar a conta?\n1 - Sim\n2 - Não");
							opcaoApagar = leia.nextInt();

						} while (opcaoApagar < 1 || opcaoApagar > 2);

						loop = false;

						if (opcaoApagar == 1) {
							i = 1;
							do {
								System.out.println("\nDigite sua senha:");
								senha = leia.next();

								if (senha.equals(contas.getContas().get(numero - 1).getSenha())) {
									loop = false;
								} else if (i == 3) {
									System.out.println("\nNúmero de tentativas excedido!");
									loop = false;
								} else {
									System.out.println("\nSenha incorreta!");
									i++;
									loop = true;
								}

							} while (loop);

							if (senha.equals(contas.getContas().get(numero - 1).getSenha())) {
								if (contaApagar.getSaldo() > 0) {
									int numeroAReceber = 0;

									System.out.println("Você possui um saldo de R$" + contaApagar.getSaldo() + ".");
									do {
										try {
											do {
												System.out.println(
														"\nDigite o número da conta para a qual deseja transferir este valor:");
												numeroAReceber = leia.nextInt();
												var contaAReceber = contas.procurarPorNumero(numeroAReceber);
												do {
													System.out.println("\n" + contaAReceber.getTitular() + "."
															+ "\nConta: " + contaAReceber.getNumero() + "\nAgência: "
															+ contaAReceber.getAgencia() + "\nValor a transferir: R$"
															+ contaApagar.getSaldo() + "."
															+ "\n\n1 - Confirmar transferencia.\n2 - Tranferir para outra conta.");
													opcaoTransferir = leia.nextInt();
												} while (opcaoTransferir < 1 && opcaoTransferir > 2);
											} while (opcaoTransferir == 2);

											loop = false;
										} catch (InputMismatchException erro) {
											System.out.println("\nOPÇÃO INVÁLDA! Por favor, digite o número da opção.");
											leia.next();

											loop = true;
										} catch (IndexOutOfBoundsException erro) {
											System.out.println("\nConta não encontrada.");
											leia.next();

											loop = true;
										} catch (NullPointerException erro) {
											leia.next();

											loop = true;
										}
									} while (loop);

									i = 1;
									do {
										System.out.println("\nDigite sua senha:");
										senha = leia.next();

										if (senha.equals(contas.getContas().get(numero - 1).getSenha())) {
											loop = false;
										} else if (i == 3) {
											System.out.println("\nNúmero de tentativas excedido!");
											loop = false;
										} else {
											System.out.println("\nSenha incorreta!");
											i++;
											loop = true;
										}

									} while (loop);

									if (senha.equals(contas.getContas().get(numero - 1).getSenha())) {
										contas.transferir(numero, numeroAReceber, contaApagar.getSaldo());
										System.out.println("\nValor tranferido para conta " + numeroAReceber + "!"
												+ "\n\nConta " + contaApagar.getNumero() + " apagada!");
										contas.deletar(numero);
									}
								} else {
									System.out.println("\nConta " + contaApagar.getNumero() + " apagada!");
									contas.deletar(numero);
								}
							}
						}
					} catch (InputMismatchException erro) {
						System.out.println("\nOPÇÃO INVÁLDA! Por favor, digite o número da opção.");
						leia.next();

						loop = true;
					}
				} while (loop);
				break;
			}
		}
	}
}
