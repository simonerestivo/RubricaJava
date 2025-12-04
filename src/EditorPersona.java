import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class EditorPersona extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FinestraPrincipale finestra;
	private Persona p;
	
	private JTextField nome;
	private JTextField cognome;
	private JTextField indirizzo;
	private JTextField telefono;
	private JTextField eta;
	
	private JLabel nomeL;
	private JLabel cognomeL;
	private JLabel indirizzoL;
	private JLabel telefonoL;
	private JLabel etaL;
	
	
	private JButton salva;
	private JButton annulla;
	
	public EditorPersona(FinestraPrincipale finestra, Persona p) {
		
		//Costruttore
		
		super(finestra, true);
		this.finestra = finestra;
		this.p = p;
		
		//Contatto selezionato?
		if(this.p == null) {
			setTitle("Nuovo Contatto");
			this.p = new Persona();
		} else {
			setTitle("Modifica Contatto");
		}
		
		//Impostazione Layout
		setLayout(new BorderLayout());
		
		JPanel form = new JPanel();
		form.setLayout(new BorderLayout(10,5));
		
		JPanel etichette = new JPanel();
		etichette.setLayout(new GridLayout(5, 1, 5, 5));
		JPanel campi = new JPanel();
		campi.setLayout(new GridLayout(5, 1, 5, 5));
		
		//Campi form
		
		nomeL = new JLabel("Nome:");
		nomeL.setHorizontalAlignment(SwingConstants.CENTER);
		cognomeL = new JLabel("Cognome:");
		cognomeL.setHorizontalAlignment(SwingConstants.CENTER);
		indirizzoL = new JLabel("Indirizzo:");
		indirizzoL.setHorizontalAlignment(SwingConstants.CENTER);
		telefonoL = new JLabel("Telefono:");
		telefonoL.setHorizontalAlignment(SwingConstants.CENTER);
		etaL = new JLabel("Età:");
		etaL.setHorizontalAlignment(SwingConstants.CENTER);
		
		nome = new JTextField(15);
		cognome = new JTextField(15);
		indirizzo = new JTextField(15);
		telefono = new JTextField(15);
		eta = new JTextField(15);
		
		etichette.add(nomeL);
		etichette.add(cognomeL);
		etichette.add(indirizzoL);
		etichette.add(telefonoL);
		etichette.add(etaL);
		
		campi.add(nome);
		campi.add(cognome);
		campi.add(indirizzo);
		campi.add(telefono);
		campi.add(eta);
		
		if(p!=null) {
			nome.setText(p.getNome());
			cognome.setText(p.getCognome());
			indirizzo.setText(p.getIndirizzo());
			telefono.setText(p.getTelefono());
			eta.setText("" + p.getEta());
		}
		
		//Posizionamento form
		
		form.add(etichette, BorderLayout.WEST);
		form.add(campi, BorderLayout.CENTER);
		add(form, BorderLayout.CENTER);
		form.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		//Bottoni
		salva = new JButton("Salva");
		annulla = new JButton("Annulla");

		//Funzionalita' bottoni
		salva.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				salvaContatto();
			}

		});
		
		annulla.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				annullaModifica();
			}
			
		});
		
		//Posizionamento bottoni
		JPanel bottoni = new JPanel();
		bottoni.setLayout(new FlowLayout());

		bottoni.add(salva);
		bottoni.add(annulla);
		add(bottoni, BorderLayout.PAGE_END);	
		
		//Impostazione dimensione e posizione
		setSize(600,250);
		setLocationRelativeTo(finestra);
		
	}



	private void salvaContatto() {
		
		try {
			
			//Controllo informazioni inserite
			
			String nomeInserito = nome.getText().trim();
			String cognomeInserito = cognome.getText().trim();
			String indirizzoInserito = indirizzo.getText().trim();
			String telefonoInserito = telefono.getText().trim();
			int etaInserita = Integer.parseInt(eta.getText().trim());
			
			if(nomeInserito.isEmpty() || cognomeInserito.isEmpty() || indirizzoInserito.isEmpty() || telefonoInserito.isEmpty() || etaInserita < 0 || etaInserita > 123 ) {
				JOptionPane.showMessageDialog(this, "Inserire informazioni valide in tutti i campi presenti", "Errore Compilazione", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			//Aggiornamento persona
			p.setNome(nomeInserito);
			p.setCognome(cognomeInserito);
			p.setIndirizzo(indirizzoInserito);
			p.setTelefono(telefonoInserito);
			p.setEta(etaInserita);
			
			//Se la persona e' nuova aggiunta ai contatti
			if(p != null && !Contatti.getContatti().getPersone().contains(p)) {
				Contatti.getContatti().aggiungiPersona(p);
			}
			
			finestra.aggiornaTabella();
			
			dispose();
			
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Insirire un numero valido nel campo Età", "Errore Formato", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	private void annullaModifica() {
		dispose();
	}

}
