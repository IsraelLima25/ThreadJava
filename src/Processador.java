import java.util.Comparator;
import java.util.List;

public class Processador implements Processar {

	private String descricao;
	private String fabricante;
	private Long tempoProcessamento;
	private boolean estaTravado;

	public Processador(String descricao, String fabricante, Long tempoProcessamento) {
		this.descricao = descricao;
		this.fabricante = fabricante;
		this.tempoProcessamento = tempoProcessamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public Long getTempoProcessamento() {
		return tempoProcessamento;
	}

	public void setTempoProcessamento(Long tempoProcessamento) {
		this.tempoProcessamento = tempoProcessamento;
	}

	@Override
	public List<Livro> ordenarListaLexigrafica(List<Livro> listaRecebida, Long tempoProcessamento) {

		synchronized (this) {

			if (isEstaTravado()) {
				aguardarDestravar();
			}

			System.out.println("Acesso a região crítica concedido ao " + Thread.currentThread().getName());
			System.out.println("Processando...." + Thread.currentThread().getName());		

			try {
				Thread.sleep(tempoProcessamento);				
				//Ordenar Lista aqui
				listaRecebida.sort(new Comparator<Livro>() {
					@Override
					public int compare(Livro l1, Livro l2) {						
						return l1.getDescricao().compareTo(l2.getDescricao());
					}		
				});				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Resultado " + Thread.currentThread().getName());
			System.out.println("-------------------------------------------------");
			System.out.println(listaRecebida);
			System.out.println(Thread.currentThread().getName() + " finalizado com sucesso");
			return listaRecebida;
			
		}
	}

	private void aguardarDestravar() {
		try {
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void destravar() {
		System.out.println("Destravando CPU...");
		System.out.println("Aguarde..");

		synchronized (this) {

			try {
				Thread.sleep(20000);
				// Acorda todas as threads associadas a esta instancia
				this.notifyAll();
				this.estaTravado = false;
				System.out.println("CPU liberado");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public String toString() {
		return "Descricao: " + this.getDescricao() + " -------- " + "Fabricante: " + this.getFabricante() + "\n";
	}

	public boolean isEstaTravado() {
		return estaTravado;
	}

	public void setEstaTravado(boolean estaTravado) {
		this.estaTravado = estaTravado;
	}
}
