public class TarefaDestravarProcessador implements Runnable {

	private Processador processador;

	public TarefaDestravarProcessador(Processador cpu) {
		this.processador = cpu;
	}

	@Override
	public void run() {
		while(true) {			
			if (processador.isEstaTravado()) {
				this.processador.destravar();
			}
		}
	}
}
