import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class GestioneUtenti {
	private static final String NOME_FILE = "utenti.txt";
	//Mappa con chiave username e valore utente
	private Map<String, Utente> utenti;
	
	public GestioneUtenti() {
		utenti = caricaDaFile();
		
		//utente default
		if(utenti.isEmpty()) {
			utenti.put("admin", new Utente("admin", "password"));
		}
	}
	
	//Metodo per caricare gli utenti da File
	private Map<String, Utente> caricaDaFile(){
		Map<String, Utente> mappa = new HashMap<>();
		File file = new File(NOME_FILE);
		
		if(!file.exists()) {
			System.out.println("FILE does not exist");
			return mappa;
			
		}
		
		try(Scanner scanner = new Scanner(file)){
			while(scanner.hasNextLine()) {
				
				String riga = scanner.nextLine();
				
				if(riga.trim().isEmpty()) {
					continue;
				}
				
				String[] campi =riga.split(";");
				
				if(campi.length == 2) {
					String username = campi[0];
					String password = campi[1];
									
					mappa.put(username, new Utente(username, password));
				}	
			}	
		} catch (FileNotFoundException e) {
			System.err.println("File non trovato.");
		}
		
		return mappa;
	}
	
	//Metodo per validare password
	//Soluzione semplice senza hash
	
	public boolean validaPassword(String username, String password) {
		Utente u = utenti.get(username);
		if(u != null) {
			return u.getPassword().equals(password);
		}
		return false;
	}
	
}
