package contaBancaria.repository;

import contaBancaria.model.Conta;

public interface Repository {
	
		// CRUD da Conta:
		public Conta procurarPorNumero(int numero);
		public void listarTodas();
		public void cadastrar(Conta conta);
		public void atualizar(int numero, int opcao);
		public void deletar(int numero);
		
		// Métodos Bancários:
		public void sacar(int numero, float valor);
		public void depositar(int numero, float valor);
		public void transferir(int numeroOrigem, int numeroDestino, float valor);
		void atualizarAniversario(int numero, int aniversario);
		void atualizarLimite(int numero, float limite);
		void atualizarParapoupanca(int numero, int aniversario);
		void atualizarParaCorrente(int numero, float limite);

}
