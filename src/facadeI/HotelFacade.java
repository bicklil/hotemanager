package facadeI;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.event.EventListenerList;

import ihm.HotelListener;
import chambre.Chambre;
import chambre.Hotel;
import reservation.Client;
import reservation.Reservation;

public class HotelFacade implements HotelFacadeInterface{
	
	Hotel hotel;
	EventListenerList listners;
	private List<Reservation> resas;
	private List<Client> clients;
	private List<Chambre> chambres;
	
	private List<Integer> Ltailles;
	private List<Date> LdatesDebut;
	private List<Integer> LnbNuits;
	private List<Integer> LidsClient;
	
	public HotelFacade(Hotel hotel) {
		this.hotel = hotel;
		resas = new ArrayList<Reservation>();
		clients = new ArrayList<Client>();
	}
	
	private Client recupClient(int idClient) {
		for(Client clt: clients) {
			if (clt.getNum() == idClient)
				return clt;
		}
		return null;
	}
	
	private List<Chambre> recupChambres(List<Integer> idchbs){
		List<Chambre> res = new ArrayList<Chambre>();
		for(Chambre chb : chambres) {
			if (idchbs.contains(chb.getNum())){
				res.add(chb);
			}
		}
		return res;
	}
	
	@Override
	public void reserver(List<Integer> numChambre, Date d, int nbNuits, int idClient) {
		resas.add(Reservation.creationResa(recupChambres(numChambre),d,nbNuits,recupClient(idClient)));
		HashMap<Integer, List<Integer>> res = new HashMap<Integer, List<Integer>>();
		List<Date> ds = new ArrayList<Date>();
		List<Integer> nbnuitss = new ArrayList<Integer>();
		List<Integer> idclts = new ArrayList<Integer>();
		
		for (Reservation resa : resas) {
			res.put(resa.getClt().getNum(), resa.getIdChambres());
			ds.add(resa.getDate());
			nbnuitss.add(resa.getNbjours());
			idclts.add(resa.getClt().getNum());
		}
		
		for(final Object o: listners.getListenerList()) {
			HotelListener listner = (HotelListener) o;
			listner.reservationsModifiees(res, ds, nbnuitss, idclts);
		}
		
	}

	@Override
	public List<Integer> getToutesChambres() {
		return hotel.ListeChambresid();
	}
	
	public List<Integer> getToutesChambresTailles() {
		List<Integer> res = new ArrayList<Integer>();
		for(Chambre chb : hotel.getChambres()) {
			res.add(chb.getTaille());
		}
		return res;
	}

	@Override
	public void chambresDispo() {
		for(final Object o: listners.getListenerList()) {
			HotelListener listner = (HotelListener) o;
			listner.chambresModifiees(getToutesChambres(), getToutesChambresTailles());
		}
		
	}

	@Override
	public void filtrerChambresDispo(Date d, int nbNuits) {
		List<Integer> idsChambre = new ArrayList<Integer>();
		List<Integer> tailles = new ArrayList<Integer>();
		for (Chambre chb : chambres) {
			if (chbisDispo(chb,d, nbNuits)) {
				idsChambre.add(chb.getNum());
				tailles.add(chb.getTaille());
			}
		}
		for(final Object o: listners.getListenerList()) {
			HotelListener listner = (HotelListener) o;
			listner.chambresModifiees(idsChambre, tailles);
		}
		
	}
	public boolean chbisDispo(Chambre chb, Date d, int nbNuits) {
		return true;
	}
	
	@Override
	public void filtrerChambresDispo(Date d, int nbNuits, int tailleMinimum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void creerChambre(int numero, int taille) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void supprimerChambre(int numero) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifierChambre(int numero, int taille) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Integer> getTousClients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean filtrerClient(String nom) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean filtrerClient(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void creerClient(String nom, String prenom, String naissance) {
		clients.add(new Client(nom,prenom));
		
	}

	@Override
	public void filtrerReservationsClient(int idClient) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void filtrerReservationsChambre(int numChambre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListener(HotelListener l) {
		listners.add(HotelListener.class,l);
		HashMap<Integer, List<Integer>> res = new HashMap<Integer, List<Integer>>();
		for (Reservation resa : resas) {
			res.put(resa.getClt().getNum(), resa.getIdChambres());
		}
		l.init(getToutesChambres(), Ltailles, res, LdatesDebut, LnbNuits, LidsClient);
		
		
	}

	@Override
	public void removeListener(HotelListener l) {
		listners.remove(HotelListener.class, l);
	}
	
}
