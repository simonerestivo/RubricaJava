import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FinestraPrincipale  extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Contatti contatti;
	private JTable tabella;
	private TableModelPersona modello;
	private JScrollPane riquadro;
	
	private JButton nuovo;
	private JButton modifica;
	private JButton elimina;
	
	public FinestraPrincipale() {
		
		super("Rubrica");
		
		//Carica dati Rubrica
		this.contatti = Contatti.getContatti();
		this.contatti.setPersone(GestioneFile.caricaDaFile());
		
		//Impostazione Layout
		setLayout(new BorderLayout());
		
		//Tabella
		modello = new TableModelPersona(contatti.getPersone());
		tabella = new JTable(modello);
		riquadro = new JScrollPane(tabella);
		add(riquadro, BorderLayout.CENTER);

		riquadro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		//Bottoni
		nuovo = new JButton("Nuovo");
		modifica = new JButton("Modifica");
		elimina = new JButton("Elimina");
		
		//Funzionalita' bottoni
		nuovo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				aggiungiContatto();
			}

		});
		
		modifica.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				modificaContatto();
			}
			
		});
		
		elimina.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				eliminaContatto();
			}
			
		});
		
		
		//Posizionamento bottoni
		JPanel bottoni = new JPanel();
		bottoni.setLayout(new FlowLayout());

		bottoni.add(nuovo);
		bottoni.add(modifica);
		bottoni.add(elimina);
		add(bottoni, BorderLayout.PAGE_END);
		

		
		//Impostazioni di chiusura, dimensione e posizione
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(1000,600);
		setLocationRelativeTo(null);
		
		setVisible(true);
		
	}

	//Logica bottoni
	
	private void aggiungiContatto( ) {
		
		EditorPersona editor = new EditorPersona(this, null);
		editor.setVisible(true);

	}
	
	private void modificaContatto() {
		int riga = tabella.getSelectedRow();
		
		if(riga == -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un contatto da modificare", "Errore Modifica Contatto", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		Persona p = contatti.getPersone().get(riga);
		EditorPersona editor = new EditorPersona(this,p);
		editor.setVisible(true);
		
	}

	private void eliminaContatto() {
		int riga = tabella.getSelectedRow();
		
		//Se contatto non selezionato, segnalazione errore
		if(riga == -1) {
			JOptionPane.showMessageDialog(this, "Selezionare un contatto da eliminare", "Errore Eliminazione Contatto", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		//Se contatto selezionato, eliminazione contatto
		Persona p = contatti.getPersone().get(riga);
		int risposta = JOptionPane.showConfirmDialog(this, "Eliminare la persona " + p.getNome() + " " + p.getCognome() + "?", "Conferma Eliminazione", JOptionPane.YES_NO_OPTION);
		
		if(risposta == JOptionPane.YES_OPTION) {
			contatti.eliminaPersona(riga);
			aggiornaTabella();
		}
	}
	
	public void aggiornaTabella() {
		modello.aggiornaDati();
		GestioneFile.salvaSuFile(contatti.getPersone());
	}
	
	
	
}
