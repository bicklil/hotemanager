package chambre;

import java.util.Date;

public class Chambre {
	private SalleDEau eau;
	private int num;
	private int taille;
	private CategorieC categorie;
	
	public Chambre(int num,int taille,CategorieC categorie,SalleDEau eau) {
		this.setNum(num);
		this.setTaille(taille);
		this.setCategorie(categorie);
		this.setEau(eau);
	}
	
	public boolean isDispo(Date date,int nbjour) {
		return true;
	}

	public SalleDEau getEau() {
		return eau;
	}

	public void setEau(SalleDEau eau) {
		this.eau = eau;
	}

	public int getNum() {
		return num;
	}

	private void setNum(int num) {
		this.num = num;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public CategorieC getCategorie() {
		return categorie;
	}

	public void setCategorie(CategorieC categorie) {
		this.categorie = categorie;
	}
	
}
