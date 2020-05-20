import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class TesteThread {

	public static void main(String[] args) throws FileNotFoundException {

		// Leitura de processadores via arquivo
		Repositorio repo = new Repositorio();

		// Captura de processadores
		Processador processadorCeleron = repo.getProcessadores().get(0);
		processadorCeleron.setEstaTravado(true);
		Processador processadorI3 = repo.getProcessadores().get(1);
		Processador processadorI7 = repo.getProcessadores().get(2);

		Livro livro1 = new Livro(1, "Chapeuzinho vermelho");
		Livro livro2 = new Livro(2, "Use a cabeça Java");
		Livro livro3 = new Livro(3, "A era do gelo");

		List<Livro> listLivros1 = new ArrayList<>();
		listLivros1.add(livro1);
		listLivros1.add(livro2);
		listLivros1.add(livro3);

		Livro livro4 = new Livro(1, "Serviço Social");
		Livro livro5 = new Livro(2, "Memórias póstumas de brás cubas");
		Livro livro6 = new Livro(3, "Banco de Dados");

		List<Livro> listLivros2 = new ArrayList<>();
		listLivros2.add(livro4);
		listLivros2.add(livro5);
		listLivros2.add(livro6);

		// Threads execução

		Thread processoA = new Thread(new TarefaAcessarProcessador(listLivros1, processadorCeleron),
				"PROCESSO A");

		Thread processoB = new Thread(new TarefaAcessarProcessador(listLivros2, processadorCeleron),
				"PROCESSO B");

		Thread unlock = new Thread(new TarefaDestravarProcessador(processadorCeleron));

		// Metodo garante que o thread só ficará vivo enquanto outros viverem. Check
		// JCONSOLE
		unlock.setDaemon(true);

		processoA.start();
		processoB.start();
		unlock.start();
	}
}
