import java.util.Vector;

public class Contatti {
	
	//Istanza Singleton
	
	private static Contatti contatti;
	private Vector<Persona> persone;
	
	//Costruttore
	
	private Contatti() {
		this.persone = new Vector<>();
	}
	
	public static Contatti getContatti() {
		if(contatti == null) {
			contatti = new Contatti();
		}
		return contatti;
	}
	
	//Getter
	
	public Vector<Persona> getPersone() {
		return persone;
	}
	
	//Setter
	
	public void setPersone(Vector<Persona> nuovePersone) {
		this.persone = nuovePersone;
	}
	
	//Metodo di aggiunta contatto
	
	public void aggiungiPersona(Persona p) {
		if(p != null) {
			persone.add(p);
		}
	}
	
	//Metodo di rimozione contatto (rimozione tramite indice)
	
	public void rimuoviPersona(int i) {
		if( i >= 0 && i < persone.size()) {
			persone.remove(i);
		}
	}
	
}
