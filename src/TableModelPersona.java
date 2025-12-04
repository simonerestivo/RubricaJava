import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class TableModelPersona extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Vector<Persona> persone;
	private final String[] titoliColonne = {"Nome", "Cognome", "Indirizzo", "Telefono", "Eta'"};
	
	//Costruttore
	
	public TableModelPersona(Vector<Persona> persone) {
		this.persone = persone;
	}
	

	
	//Getter
	
	@Override 
	public int getRowCount() {
		return persone.size();
	}
	
	@Override
	public int getColumnCount() {
		return titoliColonne.length;
	}
	
	@Override
	public String getColumnName(int i) {
		return titoliColonne[i];
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		Persona p = persone.get(row);
		switch (col) {
			case 0: return p.getNome();
			case 1: return p.getCognome();
			case 2: return p.getIndirizzo();
			case 3: return p.getTelefono();
			case 4: return p.getEta();
			default: return null;
		}
	}
	
	//Metodo per aggiornare i dati
	
	public void aggiornaDati() {
		fireTableDataChanged();
	}
	
	//Metodo per segnalare che la tabella e' di sola lettura
	
	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
}
