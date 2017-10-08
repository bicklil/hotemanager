package chambre;

public class Douche extends SalleDEau {
	public Douche(int surface) {
		super(surface);
	}
	@Override
	public boolean isDouche() {
		return true;
	}
}
