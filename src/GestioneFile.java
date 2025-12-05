import java.io.*;
import java.util.Vector;
import java.util.Scanner;

public class GestioneFile {
	//private static final String NOME_FILE = "informazioni.txt";
	
	private static final String NOME_CARTELLA = "informazioni";
	private static final String ESTENSIONE = ".txt";
	

	
	//Metodo per caricare contatti da file
	public static Vector<Persona> caricaDaFile() {
		Vector<Persona> persone = new Vector<>();
		File cartella = new File(NOME_CARTELLA);
		
		if(!cartella.exists() || !cartella.isDirectory()) {
			return persone;
		}
		
		File[] file = cartella.listFiles();
		
		if(file != null) {
			for(File f : file) {
				try(Scanner scanner = new Scanner(f)){
					while(scanner.hasNextLine()) {
						
						String riga = scanner.nextLine();
						
						if(riga.trim().isEmpty()) {
							continue;
						}
						
						String[] campi =riga.split(";");
						
						if(campi.length == 5) {
							
							try {
								String nome = campi[0];
								String cognome = campi[1];
								String indirizzo = campi[2];
								String telefono = campi[3];
								int eta = Integer.parseInt(campi[4].trim());
								
								persone.add(new Persona(nome, cognome, indirizzo, telefono, eta));
							} catch(NumberFormatException e) {
								System.err.println("Formato eta' non valido.");
							}
							
						} else {
							System.err.println("Formato dati non valido.");
						}
						
						
					}
				} catch(FileNotFoundException e) {
					System.err.println("File non trovato.");
				}
			}
		}
		return persone;
		
	}
	
	//Metodo per salvare su file i contatti (sovrascrive il file)
	public static void salvaSuFile(Vector<Persona> persone) {
		
		File cartella = new File(NOME_CARTELLA);
		
		if(!cartella.exists()) {
			//crea cartella
			cartella.mkdirs();
		} else {
			//Elimina i file presenti
			File[] file = cartella.listFiles();
			if(file != null) {
				for(File f : file) {
					f.delete();
				}
			}
		}
		
		for(Persona p: persone) {
			try(PrintStream printStream = new PrintStream(new File(cartella, creaNomeFile(p)))) {
				printStream.println(p.toFormattedString());
			} catch (FileNotFoundException e) {
				System.err.println("File non trovato.");
			}
		}
	}
	
	private static String creaNomeFile(Persona p) {
		String nomeCognome = p.getNome() + "-" + p.getCognome();
		String nomeFile = nomeCognome + ESTENSIONE;
		File cartella = new File(NOME_CARTELLA);
		
		if(!cartella.exists()) {
			return nomeFile;
		}
		
		int i = 1;
		while(new File(cartella, nomeFile).exists()) {
			i++;
			nomeFile = nomeCognome + i + ESTENSIONE;
		}
		
		return nomeFile;
	}
	
	
}
