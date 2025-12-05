import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FinestraLogin extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField username;
	private JTextField password;
	
	private JLabel usernameL;
	private JLabel passwordL;
	
	private JButton login;
	
	private GestioneUtenti utenti;
	
	public FinestraLogin() {
		super("Login");
		
		this.utenti = new GestioneUtenti();
		
		//Impostazione Layout
		setLayout(new BorderLayout());
		
		JPanel form = new JPanel();
		form.setLayout(new BorderLayout(4,5));
		
		JPanel etichette = new JPanel();
		etichette.setLayout(new GridLayout(2, 1, 5, 5));
		JPanel campi = new JPanel();
		campi.setLayout(new GridLayout(2, 1, 5, 5));
		
		//Campi form
		usernameL = new JLabel("username:");
		usernameL.setHorizontalAlignment(SwingConstants.CENTER);
		passwordL = new JLabel("password:");
		passwordL.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		username = new JTextField(15);
		password = new JTextField(15);
		
		etichette.add(usernameL);
		etichette.add(passwordL);

		campi.add(username);
		campi.add(password);
		
		//Bottoni
		login = new JButton("LOGIN");

		//Funzionalita' bottoni
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				logIn();
			}

		});
				

		form.add(etichette, BorderLayout.WEST);
		form.add(campi, BorderLayout.CENTER);
		add(form, BorderLayout.NORTH);
		add(login, BorderLayout.SOUTH);	
		form.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


		//Impostazioni di chiusura, dimensione e posizione
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		setSize(300,150);
		setLocationRelativeTo(null);
		setVisible(true);

	}
	
	private void logIn() {
		String usernameInserito = username.getText().trim();
		String passwordInserita = password.getText().trim();
		
		if(utenti.validaPassword(usernameInserito, passwordInserita)) {
			//login riuscito
			JOptionPane.showMessageDialog(this,  "Accesso eseguito", "Credenziali corrette", JOptionPane.INFORMATION_MESSAGE);
			dispose();
			
			new FinestraPrincipale();
		} else {
			//login fallito
			JOptionPane.showMessageDialog(this, "Login errato", 
                    "Credenziali non valide", JOptionPane.ERROR_MESSAGE);
			
			password.setText("");
		}
		
	}
	
}
