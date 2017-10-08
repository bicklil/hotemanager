package reservation;

public class Facture {
	int num;
	static int numd = 0;
	
	public Facture() {
		num = numd;
		numd = numd +1;
	}
}
