import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Repositorio {
	
	private List<Processador> processadores = new ArrayList<Processador>();
	
	public Repositorio() throws FileNotFoundException {
		lerArquivo();
	}
	
	private void lerArquivo() throws FileNotFoundException {
		
		Scanner scannerExternal = new Scanner(new File("processadores.csv"));		
		Processador proc;
		
		while(scannerExternal.hasNext()) {

			String linha = scannerExternal.nextLine();
			
			Scanner scannerInternal = new Scanner(linha);
			scannerInternal.useDelimiter(",");			
			String descricao = scannerInternal.next();
			String fabricante = scannerInternal.next();
			Long tempoProcessamento = scannerInternal.nextLong();
			
			proc = new Processador(descricao, fabricante, tempoProcessamento);			
			getProcessadores().add(proc);
			
			scannerInternal.close();
		}
		
		scannerExternal.close();
	}
	
	public List<Processador> getProcessadores() {
		return processadores;
	}

}
