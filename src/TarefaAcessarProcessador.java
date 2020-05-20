import java.util.List;

public class TarefaAcessarProcessador implements Runnable {
	
	private Processador processador;
	private List<Livro> lista;

	public TarefaAcessarProcessador(List<Livro> lista, Processador processador) {	
		this.processador = processador;
		this.lista = lista;
	}

	@Override
	public void run() {	
			System.out.println(Thread.currentThread().getName() + " solicitando acesso a região crítica ");
			this.processador.ordenarListaLexigrafica(lista, processador.getTempoProcessamento());				
		}
	}

