package reservation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import chambre.Chambre;

public class Reservation {
	private Client clt;
	private Facture fac;
	private List<Chambre> chbs;
	private int num;
	static int numd;
	private Date date;
	private int nbjours;
	
	public static Reservation creationResa(List<Chambre> chambres,Date date,int nbjour,Client clt) {
		for(Chambre chb : chambres) {
			if( ! chb.isDispo(date,nbjour)) {
				return null;
			}
		}
		return new Reservation(clt,date,nbjour,chambres);
		
	}
	private Reservation(Client clt,Date date,int nbjours,List<Chambre> chbs) {
		this.setClt(clt);
		this.setDate(date);
		this.setNbjours(nbjours);
		this.setChbs(chbs);
		
	}
	
	public List<Integer> getIdChambres() {
		List<Integer> res = new ArrayList<Integer>();
		for(Chambre chb : chbs) {
			res.add(chb.getNum());
		}
		return res;
	}
	public Client getClt() {
		return clt;
	}
	public void setClt(Client clt) {
		this.clt = clt;
	}
	public Facture getFac() {
		return fac;
	}
	public void setFac(Facture fac) {
		this.fac = fac;
	}
	public List<Chambre> getChbs() {
		return chbs;
	}
	public void setChbs(List<Chambre> chbs) {
		this.chbs = chbs;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getNbjours() {
		return nbjours;
	}
	public void setNbjours(int nbjours) {
		this.nbjours = nbjours;
	}
	
}
