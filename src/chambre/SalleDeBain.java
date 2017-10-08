package chambre;

public class SalleDeBain extends SalleDEau{
	public SalleDeBain(int surface) {
		super(surface);
	}
	
	@Override
	public boolean isBain() {
		return true;
	}
}
