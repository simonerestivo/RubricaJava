public class Utente {
	private String username;
	private String password;
	
	public Utente(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
	
	//versione semplice senza hash
	public String getPassword() {
		return password;
	}
	
	public String toFormattedString() {
		return username + ";" + password;
	}

}
