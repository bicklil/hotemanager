package reservation;

public class Client {
	private int num;
	static int numd = 0;
	private String nom;
	private String prenom;
	
	public Client(String nom, String prenom) {
		this.setNom(nom);
		this.setPrenom(prenom);
		setNum(numd);
		numd = numd +1;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
}
