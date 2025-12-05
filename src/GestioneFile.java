import java.io.*;
import java.util.Vector;
import java.util.Scanner;

public class GestioneFile {
	
	private static final String NOME_FILE = "informazioni.txt";
	
	//Metodo per caricare contatti da file
	public static Vector<Persona> caricaDaFile() {
		Vector<Persona> persone = new Vector<>();
		File file = new File(NOME_FILE);
		
		if(!file.exists()) {
			System.out.println("FILE does not exist");
			return persone;
			
		}
		
		try(Scanner scanner = new Scanner(file)){
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
		
		return persone;
		
	}
	
	//Metodo per salvare su file i contatti (sovrascrive il file)
	public static void salvaSuFile(Vector<Persona> persone) {
		try(PrintStream printstream = new PrintStream(NOME_FILE)){
			for(Persona p : persone) {
				printstream.println(p.toFormattedString());
			}
		} catch( FileNotFoundException e) {
			System.err.println("File non trovato.");
		}
	}
	
	
}
