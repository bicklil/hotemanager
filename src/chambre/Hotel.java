package chambre;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
	private List<Chambre> chambres;
	
	private Hotel(List<Chambre> chbs){
		chambres = chbs;
	}
	
	public static Hotel creationHotel(List<Chambre> chbs) {
		if(chbs.size() > 1) {
			return new Hotel(chbs);
		}
		return null;
	}
	
	public List<Integer> ListeChambresid(){
		List<Integer> res = new ArrayList<Integer>();
		for (Chambre chb : chambres) {
			res.add(chb.getNum());
		}
		return res;
	}
	
	public List<Chambre> getChambres(){
		return chambres;
	}
	
	public void addChambre(Chambre c) {
		chambres.add(c);
	}
}
